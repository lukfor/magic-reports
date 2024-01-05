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

        String libDir = file.getAbsoluteFile().getParentFile().getAbsolutePath() + "/lib";
        GroovyShell shell = createGroovyShell(libDir);

        Script script = shell.parse(file);
        ReportDSL reportDsl = (ReportDSL) script;
        reportDsl.setParams(params);
        reportDsl.setScript(file);
        reportDsl.setLibDir(libDir);
        reportDsl.setOutput(output);

        script.run();

    }

    public static void include(File file, String libDir) throws Exception {

        GroovyShell shell = createGroovyShell(libDir);

        Script script = shell.parse(file);
        ReportDSL reportDsl = (ReportDSL) script;
        reportDsl.setLibDir(libDir);
        reportDsl.setScript(file);

        script.run();

    }

    public static void include(InputStream stream, String libDir) throws Exception {

        GroovyShell shell = createGroovyShell(libDir);

        Script script = shell.parse(new InputStreamReader(stream));
        ReportDSL reportDsl = (ReportDSL) script;
        reportDsl.setLibDir(libDir);
        //reportDsl.setScript(file);

        script.run();

    }

    private static GroovyShell createGroovyShell(String libDir) {
        ImportCustomizer customizer = new ImportCustomizer();
        customizer.addImports("lukfor.reports.widgets.Component");

        CompilerConfiguration compilerConfiguration = new CompilerConfiguration();
        compilerConfiguration.addCompilationCustomizers(customizer);
        compilerConfiguration.setScriptBaseClass(ReportDSL.class.getName());
        compilerConfiguration.setClasspath(libDir);
        System.out.println("LibDir: " + libDir);
        GroovyShell shell = new GroovyShell(ReportParser.class.getClassLoader(), compilerConfiguration);
        return shell;
    }

}
