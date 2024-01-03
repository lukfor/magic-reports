package lukfor.reports.dsl;

import groovy.lang.Script;
import lukfor.reports.HtmlWidgetsReport;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class ReportParser {


    public static void run(File file, File output, Map<String, String> params) throws Exception {

        GroovyShell shell = createGroovyShell();

        Script script = shell.parse(file);
        ReportDSL reportDsl = (ReportDSL) script;
        reportDsl.setParams(params);
        reportDsl.setScript(file);
        reportDsl.setOutput(output);

        script.run();

    }

    public static void include(File file) throws Exception {

        GroovyShell shell = createGroovyShell();

        Script script = shell.parse(file);
        ReportDSL reportDsl = (ReportDSL) script;
        reportDsl.setScript(file);

        script.run();

    }

    public static void include(InputStream stream) throws Exception {

        GroovyShell shell = createGroovyShell();

        Script script = shell.parse(new InputStreamReader(stream));
        ReportDSL reportDsl = (ReportDSL) script;
        //reportDsl.setScript(file);

        script.run();

    }

    private static GroovyShell createGroovyShell() {
        ImportCustomizer customizer = new ImportCustomizer();
        customizer.addImports("lukfor.reports.widgets.Component");

        CompilerConfiguration compilerConfiguration = new CompilerConfiguration();
        compilerConfiguration.addCompilationCustomizers(customizer);
        compilerConfiguration.setScriptBaseClass(ReportDSL.class.getName());

        GroovyShell shell = new GroovyShell(ReportParser.class.getClassLoader(), compilerConfiguration);
        return shell;
    }

}
