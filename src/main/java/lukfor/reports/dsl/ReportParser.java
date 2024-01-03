package lukfor.reports.dsl;

import groovy.lang.Script;
import lukfor.reports.HtmlWidgetsReport;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;

import java.io.File;
import java.util.Map;

public class ReportParser {


    public static HtmlWidgetsReport parse(File file, Map<String, String> params) throws Exception {

        ImportCustomizer customizer = new ImportCustomizer();
        customizer.addImports("lukfor.reports.Component");

        CompilerConfiguration compilerConfiguration = new CompilerConfiguration();
        compilerConfiguration.addCompilationCustomizers(customizer);
        compilerConfiguration.setScriptBaseClass(ReportDSL.class.getName());

        GroovyShell shell = new GroovyShell(ReportParser.class.getClassLoader(), compilerConfiguration);

        Script script = shell.parse(file);
        ReportDSL reportDsl = (ReportDSL) script;
        reportDsl.setParams(params);
        reportDsl.setScript(file);

        Object result = script.run();
        if (!(result instanceof  HtmlWidgetsReport)){
            throw new RuntimeException("Last statement in script has to be report{}.");
        }

        return (HtmlWidgetsReport) result;

    }

}
