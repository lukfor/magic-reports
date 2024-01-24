package lukfor.reports.commands;

import lukfor.reports.dsl.ParamsMap;
import lukfor.reports.server.DevelopmentServer;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "serve")
public class ServeCommand implements Callable<Integer> {

    @Option(names = {
            "-i" }, description = "Input file", required = true)
    private File input = null;

    @Option(names = {
            "-o" }, description = "Output file", required = false)
    private File output = null;

    @Option(names = {
            "-h","-hostname" }, description = "Hostname", required = false, showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
    private String hostname = "localhost";

    @Option(names = {
            "-p","-port" }, description = "Output file", required = false, showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
    private int port = 19085;

    @CommandLine.Unmatched()
    private List<String> unmatchedParams;


    @Override
    public Integer call() throws Exception {

        ParamsMap params = ParamsMap.buildFromArgs(unmatchedParams);
        System.out.println(params);

        if (output == null) {
            output = new File(input.getAbsolutePath().replaceAll("\\.report","\\.html"));
        }

        DevelopmentServer server = new DevelopmentServer(input, output, params);
        
        server.start(hostname, port);

        return 0;
    }

}