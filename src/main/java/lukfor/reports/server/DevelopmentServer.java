package lukfor.reports.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import lukfor.reports.dsl.ParamsMap;
import lukfor.reports.dsl.ReportParser;
import org.springframework.boot.devtools.livereload.LiveReloadServer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DevelopmentServer {

    private File input;

    private File output;

    private ParamsMap params;

    public DevelopmentServer(File input, File output, ParamsMap params) {
        this.input = input;
        this.output = output;
        this.params = params;
    }


    public void start(String hostname, int httpPort) throws Exception {

        //TODO: prerender needed?
        render();

       // LiveReloadServer reloadServer = new LiveReloadServer();
       // reloadServer.start();

        HttpServer server = HttpServer.create(new InetSocketAddress(hostname, httpPort), 0);
        server.createContext("/", new MyHttpHandler(input, output, params));
        ExecutorService executor = Executors.newCachedThreadPool();
        server.setExecutor(executor);

        // Get the address where the server is started
        InetSocketAddress serverAddress = server.getAddress();
        String protocol = "http";
        String address = protocol + "://" + serverAddress.getHostString() + ":" + serverAddress.getPort();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Server is shutting down...");
            server.stop(5);
            System.out.println("Server stopped. Bye bye.");
        }));

        server.start();

        System.out.println("\nReport is provided at " + address + "\n");


        //WatchService watchService = FileSystems.getDefault().newWatchService();
        //Path directory = new File(input.getAbsolutePath()).getParentFile().toPath();
        //directory.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        //System.out.println("Watching directory: " + directory);

       // while (true) {
            /*WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                    Path changedFile = (Path) event.context();
                    if (! changedFile.equals(output.toPath())) {
                        System.out.println("File " + changedFile + " changed...");
                        //render();
                        reloadServer.triggerReload();
                    }

                }
            }
            key.reset();

             */
        //}
    }

    public void render() throws Exception {
        try {
            ReportParser.run(input, output, params);
        } catch (Exception e) {
            //TODO: better error message
            //e.printStackTrace();
            String error = e.getMessage();
            System.out.println("Error: " + error);
        }
    }


    static class MyHttpHandler implements HttpHandler {

        private File input;

        private File output;

        private ParamsMap defaultParams;

        public MyHttpHandler(File input, File output, ParamsMap defaultParams) {
            this.input = input;
            this.output = output;
            this.defaultParams = defaultParams;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            String query = exchange.getRequestURI().getQuery();

            System.out.println("Process request " + method + exchange.getRequestURI().toString());

            if (!method.equalsIgnoreCase("GET")) {
                String error = "Method " + method + " not supported.";
                System.out.println(error);
                exchange.sendResponseHeaders(500, 0);
                exchange.getResponseBody().write(error.getBytes());
                exchange.getResponseBody().close();
                return;
            }

            if (!path.equalsIgnoreCase("/")){
                String error = "Path " + path + " not found.";
                System.out.println(error);
                exchange.sendResponseHeaders(404, 0);
                exchange.getResponseBody().write(error.getBytes());
                exchange.getResponseBody().close();
                return;
            }

            try {

                ParamsMap params = getQueryParams(query);
                for (String param: defaultParams.keySet()) {
                    params.putIfAbsent(param, defaultParams.get(param));
                }
                ReportParser.run(input, output, params);

                byte[] response = getHtmlContent(output, false).getBytes();
                exchange.sendResponseHeaders(200, response.length);
                OutputStream os = exchange.getResponseBody();
                os.write(response);
                os.close();

            } catch (Exception e) {
                //TODO: better error message
                //e.printStackTrace();
                String error = e.getMessage();
                System.out.println("Error: " + error);
                e.printStackTrace();
                exchange.sendResponseHeaders(500, 0);
                exchange.getResponseBody().write(error.getBytes());
                exchange.getResponseBody().close();
            }

        }

        private String getHtmlContent(File filePath, boolean addReload) throws IOException {
            String content = new String(Files.readAllBytes(filePath.toPath()));
            if (addReload){
                StringBuffer jsCodeBuffer = new StringBuffer();
                jsCodeBuffer.append("<script src=\"http://localhost:35729/livereload.js\"></script>");
                String jsCode = jsCodeBuffer.toString();
                return content + jsCode;
            }

            return content;

        }


        private ParamsMap getQueryParams(String queryString) {
            ParamsMap queryParams = new ParamsMap();

            if (queryString != null) {
                String[] pairs = queryString.split("&");

                for (String pair : pairs) {
                    int idx = pair.indexOf("=");
                    String key = idx > 0 ? pair.substring(0, idx) : pair;
                    String value = idx > 0 && pair.length() > idx + 1 ? pair.substring(idx + 1) : null;
                    if (value != null && value.startsWith("!magic-param:")){
                        if (value.equalsIgnoreCase("!magic-param:true")){
                            queryParams.put(key, true);
                        } else if (value.equalsIgnoreCase("!magic-param:false")) {
                            queryParams.put(key, false);
                        } else {
                            throw new RuntimeException("Unknown value for parameter " + key + ": " + value);
                        }
                    } else {
                        queryParams.put(key, value);
                    }
                }
            }

            return queryParams;
        }
    }

}
