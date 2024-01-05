package lukfor.reports.dsl;

import groovy.lang.Closure;
import groovy.lang.Script;
import lukfor.reports.widgets.Component;
import lukfor.reports.HtmlWidgetsReport;
import lukfor.reports.widgets.ComponentRegistry;
import lukfor.reports.widgets.WidgetRegistry;
import lukfor.reports.widgets.components.CardWidget;
import lukfor.reports.widgets.plots.PlotlyWidget;
import lukfor.reports.widgets.tables.DataTableWidget;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ReportDSL extends Script {

    private File script;

    private Map<String, String> params;

    private String baseDir;

    private String libDir;

    private File output;

    static {
        WidgetRegistry.getInstance().register(DataTableWidget.KEYWORD, DataTableWidget.class);
        WidgetRegistry.getInstance().register(PlotlyWidget.KEYWORD, PlotlyWidget.class);
        WidgetRegistry.getInstance().register(CardWidget.KEYWORD, CardWidget.class);
    }

    public ReportDSL() {

    }

    public void setScript(File script) {
        this.script = script;
        baseDir = script.getAbsoluteFile().getParent();
    }

    public void setOutput(File output) {
        this.output = output;
    }

    public void report(final Closure closure) throws IOException {
        HtmlWidgetsReport report = new HtmlWidgetsReport(this);
        closure.setDelegate(report);
        closure.call();
        report.render();
    }


    public void component(String name, final Closure closure) throws IOException {
        ComponentRegistry.getInstance().register(name, new Component(name, closure));
    }

    public void include(String filename) throws Exception {
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("Include filename " + filename);
            ReportParser.include(file, libDir);
            return;
        }

        InputStream stream = ReportDSL.class.getResourceAsStream(filename.startsWith("/") ? filename : "/" + filename);
        if (stream == null){
            throw new RuntimeException("Component not found '" + filename + "'");
        }

        ReportParser.include(stream, libDir);

    }

    public void exit(int code, String message){
        System.out.println(message);
        System.exit(code);
    }

    public void exit(String message){
        exit(0, message);
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

    public File getOutput() {
        return output;
    }

    public void setLibDir(String libDir) {
        this.libDir = libDir;
    }

    public String getLibDir() {
        return libDir;
    }
}
