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

	public Component(String name, Closure closure){
		this.name = name;
		this.closure = closure;
	}

	public String render(HtmlWidgetsReport report, Object args){

		Map<String, Object> options = new HashMap<>();

		List list = InvokerHelper.asList(args);
		if (list.size() == 1){
			Object arg = list.get(0);
			if (!(arg instanceof Map)){
				throw new RuntimeException("Component " + name + ". Options are not a map: " + arg.getClass());
			}
			options = (Map) arg;
		} else if (list.size() > 1){
			throw new RuntimeException("Component " + name + ". More than one parameter: " + list);
		}
		StringWriter writer = new StringWriter();
		HtmlBlockBuilder builder = new HtmlBlockBuilder(report, writer);
		closure.setProperty("options", options);
		builder.build("component-container", closure);
		return writer.toString();
	}

}
