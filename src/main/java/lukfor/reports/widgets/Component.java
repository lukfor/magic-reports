package lukfor.reports.widgets;

import groovy.lang.Closure;
import lukfor.reports.HtmlWidgetsReport;
import lukfor.reports.dsl.ParamsMap;
import lukfor.reports.dsl.ReportDSL;
import org.codehaus.groovy.runtime.InvokerHelper;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Component {

	private  ReportDSL dsl;

	private String name;

	private Closure closure;

	private static int ids = 0;

	public static String uniqueId() {
		return "element_" + ids++;
	}

	public Component(String name, ReportDSL dsl, Closure closure){
		this.name = name;
		this.closure = closure;
		this.dsl =dsl;
	}

	public String render(HtmlWidgetsReport report, Object args) {

		try {

			ParamsMap options = ParamsMap.buildFromArgs(args);
			StringWriter writer = new StringWriter();
			HtmlBlockBuilder builder = new HtmlBlockBuilder(report, writer);
			closure.setProperty("options", options);
			closure.setProperty("params", dsl.getParams());
			builder.build(closure);
			return writer.toString();

		} catch (Exception e) {
			throw new RuntimeException("Component " + name + ": " + e.getMessage());
		}
	}

}
