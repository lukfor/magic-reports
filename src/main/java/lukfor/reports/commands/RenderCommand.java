package lukfor.reports.commands;

import lukfor.reports.HtmlWidgetsReport;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import lukfor.reports.widgets.ReportBuilder;

import java.io.File;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "render")
public class RenderCommand implements Callable<Integer> {

    @Option(names = {
            "--input","-i" }, description = "Input file", required = true)
    private File input = null;

    @CommandLine.Option(names = {
            "--output","-o" }, description = "Output file", required = true)
    private File output = null;

    @Override
    public Integer call() throws Exception {
        ReportBuilder.setScript(input);
        HtmlWidgetsReport report = ReportBuilder.parse(input);
        report.render(output);
        return 0;
    }
}