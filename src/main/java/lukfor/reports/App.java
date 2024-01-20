package lukfor.reports;

import lukfor.reports.commands.RenderCommand;
import lukfor.reports.commands.ServeCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = App.NAME, version = App.VERSION)
public class App {

    public static final String NAME = "magic-reports";

    public static final String VERSION = "0.1.0";

    public static final String URL = "https://www.forer.it/magic-reports";

    public static final String COPYRIGHT = "(c) 2023 - 2024 Lukas Forer";

    public static String[] args;

    public int run(String... args) {

        App.args = args;

        CommandLine commandLine = new CommandLine(new App());
        commandLine.addSubcommand("render", new RenderCommand());
        commandLine.addSubcommand("build", new RenderCommand());
        commandLine.addSubcommand("serve", new ServeCommand());
        commandLine.setExecutionStrategy(new CommandLine.RunLast());
        return commandLine.execute(args);

    }

    public static void main(String[] args) throws Exception {

        System.out.println();
        System.out.println(NAME + " " + VERSION);
        if (URL != null && !URL.isEmpty()) {
            System.out.println(URL);
        }
        if (COPYRIGHT != null && !COPYRIGHT.isEmpty()) {
            System.out.println(COPYRIGHT);
        }
        System.out.println();

        App app = new App();
        int exitCode = app.run(args);
        //System.exit(exitCode);
    }

}