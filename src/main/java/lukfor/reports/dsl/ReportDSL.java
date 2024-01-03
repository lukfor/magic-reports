package lukfor.reports.dsl;

import groovy.lang.Closure;
import groovy.lang.Script;
import lukfor.reports.HtmlWidgetsReport;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ReportDSL extends Script {

    private File script;

    private Map<String, String> params;

    private String baseDir;

    public void setScript(File script) {
        this.script = script;
        baseDir = script.getAbsoluteFile().getParent();
    }

    public HtmlWidgetsReport report(final Closure closure) throws IOException {
        HtmlWidgetsReport report = new HtmlWidgetsReport();
        report.setFile(script);
        closure.setDelegate(report);
        closure.call();

        //TODO: render here! enables to create multiple instances of the same report!

        return report;
    }

    public void component(String name, final Closure closure) throws IOException {

    }

    @Override
    public Object run() {
        System.out.println("RUN!");
        return null;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getBaseDir() {
        return baseDir;
    }

}
