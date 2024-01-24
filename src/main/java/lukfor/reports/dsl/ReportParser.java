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


    public static void run(File file, File output, ParamsMap params) throws Exception {

        System.out.println("Render file " + file.getAbsoluteFile() + "...");

        long time0 = System.currentTimeMillis();

        String libDir = file.getAbsoluteFile().getParentFile().getAbsolutePath() + "/lib";
        GroovyShell shell = createGroovyShell(libDir);

        Script script = shell.parse(file);
        ReportDSL reportDsl = (ReportDSL) script;
        reportDsl.setParams(params);
        reportDsl.setScript(file);
        reportDsl.setLibDir(libDir);
        reportDsl.setOutput(output);
        script.run();

        long time1 = System.currentTimeMillis();

        System.out.println("Rendered in " + (time1 - time0) / 1000.0 + " secs\n");

    }

    public static void include(File file, String libDir, ParamsMap params) throws Exception {

        GroovyShell shell = createGroovyShell(libDir);

        Script script = shell.parse(file);
        ReportDSL reportDsl = (ReportDSL) script;
        reportDsl.setParams(params);
        reportDsl.setLibDir(libDir);
        reportDsl.setScript(file);

        script.run();

    }

    public static void include(InputStream stream, String libDir, ParamsMap params) throws Exception {

        GroovyShell shell = createGroovyShell(libDir);

        Script script = shell.parse(new InputStreamReader(stream));
        ReportDSL reportDsl = (ReportDSL) script;
        reportDsl.setParams(params);
        reportDsl.setLibDir(libDir);
        //reportDsl.setScript(file);

        script.run();

    }

    private static GroovyShell createGroovyShell(String libDir) {
        ImportCustomizer customizer = new ImportCustomizer();
        customizer.addImports("lukfor.reports.widgets.Component");
        customizer.addStarImports("tech.tablesaw.api");

        CompilerConfiguration compilerConfiguration = new CompilerConfiguration();
        compilerConfiguration.addCompilationCustomizers(customizer);
        compilerConfiguration.setScriptBaseClass(ReportDSL.class.getName());
        compilerConfiguration.setClasspath(libDir);
        GroovyShell shell = new GroovyShell(ReportParser.class.getClassLoader(), compilerConfiguration);
        return shell;
    }

}
