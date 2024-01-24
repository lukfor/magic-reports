package lukfor.reports.commands;

import lukfor.reports.dsl.ParamsMap;
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
            "-i" }, description = "Input file", required = true)
    private File input = null;

    @CommandLine.Option(names = {
            "-o" }, description = "Output file", required = false)
    private File output = null;

    @CommandLine.Unmatched()
    private List<String> unmatchedParams;


    @Override
    public Integer call() throws Exception {

        ParamsMap params = ParamsMap.buildFromArgs(unmatchedParams);

        if (output == null) {
            output = new File(input.getAbsolutePath().replaceAll("\\.report","\\.html"));
        }

        ReportParser.run(input, output, params);

        return 0;
    }

}