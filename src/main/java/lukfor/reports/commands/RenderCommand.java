package lukfor.reports.commands;

import lukfor.reports.HtmlWidgetsReport;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import lukfor.reports.dsl.ReportParser;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "render")
public class RenderCommand implements Callable<Integer> {

    @Option(names = {
            "--input","-i" }, description = "Input file", required = true)
    private File input = null;

    @CommandLine.Option(names = {
            "--output","-o" }, description = "Output file", required = true)
    private File output = null;

    @CommandLine.Unmatched()
    private List<String> unmatchedParams;


    @Override
    public Integer call() throws Exception {

        Map<String, String> paramMap = new HashMap<>();

        // Add unmatched parameters starting with --
        if (unmatchedParams != null) {
            for (int i = 0; i < unmatchedParams.size(); i++) {
                String param = unmatchedParams.get(i);
                if (param.startsWith("--")) {
                    String key = param.substring(2);
                    String value = (i + 1 < unmatchedParams.size()) ? unmatchedParams.get(i + 1) : null;
                    paramMap.put(key, value);
                    i++; // Skip the next element as it has been consumed as the value
                }
            }
        }

        HtmlWidgetsReport report = ReportParser.parse(input, paramMap);
        report.render(output);
        return 0;
    }
}