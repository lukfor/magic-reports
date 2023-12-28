package lukfor.reports.widgets;

import lukfor.reports.HtmlReport;
import lukfor.reports.HtmlWidgetsReport;
import groovy.lang.Closure;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;

import java.io.File;
import java.io.IOException;

public class ReportBuilder {

    public static HtmlWidgetsReport report(final Closure closure) throws IOException {
        HtmlWidgetsReport report = new HtmlWidgetsReport();
        closure.setDelegate(report);
        closure.call();
        return report;
    }

    public static HtmlWidgetsReport parse(File script) throws Exception {

        ImportCustomizer customizer = new ImportCustomizer();
        customizer.addStaticImport("lukfor.reports.widgets.ReportBuilder", "report");
        customizer.addStaticImport("lukfor.reports.widgets.ReportBuilder", "report2");

        CompilerConfiguration compilerConfiguration = new CompilerConfiguration();
        compilerConfiguration.addCompilationCustomizers(customizer);

        GroovyShell shell = new GroovyShell(ReportBuilder.class.getClassLoader(), compilerConfiguration);
        return (HtmlWidgetsReport) shell.evaluate(script);

    }

}
