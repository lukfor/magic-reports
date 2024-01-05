package lukfor.reports.widgets;

import groovy.lang.Closure;
import lukfor.reports.HtmlWidgetsReport;
import org.codehaus.groovy.runtime.InvokerHelper;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Component {

	private String name;

	private Closure closure;

	private static int ids = 0;
	public static String uniqueId() {
		return "element_" + ids++;
	}

	public Component(String name, Closure closure){
		this.name = name;
		this.closure = closure;
	}

	public String render(HtmlWidgetsReport report, Object args) {

		Map<String, Object> options = new HashMap<>();

		List list = InvokerHelper.asList(args);

		if (list.size() > 2) {
			throw new RuntimeException("Component " + name + ". More than one parameter: " + list);
		}

		if (!list.isEmpty()) {
			Object arg1 = list.get(0);
			if (!(arg1 instanceof Map) && !(arg1 instanceof Closure)) {
				throw new RuntimeException("Component " + name + ". Provided argument is not a map or closure: " + arg1.getClass());
			}

			if (arg1 instanceof Map){
				options = (Map) arg1;
			} else{
				options.put("body", arg1);
			}

			if (list.size() == 2) {
				Object arg2 = list.get(1);
				if (!(arg2 instanceof Closure)) {
					throw new RuntimeException("Component " + name + ". Body is not a closure: " + arg2.getClass());
				}
				options.put("body", arg2);
			}
		}

		StringWriter writer = new StringWriter();
		HtmlBlockBuilder builder = new HtmlBlockBuilder(report, writer);
		closure.setProperty("options", options);
		builder.build(closure);
		return writer.toString();
	}

}
