package lukfor.reports;

import lukfor.reports.commands.RenderCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = App.NAME, version = App.VERSION)
public class App {

    public static final String NAME = "magic-reports";

    public static final String VERSION = "0.1.0";

    public static String[] args;

    public int run(String[] args) {

        App.args = args;

        CommandLine commandLine = new CommandLine(new App());
        commandLine.addSubcommand("render", new RenderCommand());
        commandLine.setExecutionStrategy(new CommandLine.RunLast());
        return commandLine.execute(args);

    }

    public static void main(String[] args) throws Exception {

        App app = new App();
        int exitCode = app.run(args);
        System.exit(exitCode);
    }

}