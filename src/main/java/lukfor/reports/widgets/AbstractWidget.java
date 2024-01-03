package lukfor.reports.widgets;

import groovy.lang.Closure;
import lukfor.reports.HtmlWidgetsReport;
import org.codehaus.groovy.runtime.InvokerHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWidget implements IWidget {

	private static int instances;
	
	protected String createId(String name) {
		instances++;
		return "widget_" + name + "_" + instances;
	}

	@Override
	public void init(HtmlWidgetsReport report, Object args) {
		List list = InvokerHelper.asList(args);
		if (list.isEmpty()){
			throw new RuntimeException("Widget '" + getKeyword() + "'. No parameter set.");
		} else if (list.size() > 1){
			throw new RuntimeException("Widget '" + getKeyword() + "'. More than one parameter: " + list);
		}
		Object arg = list.get(0);
		if (arg instanceof  Closure){
			initWithClosure(report, (Closure) arg);
		} else if (arg instanceof  HashMap){
			initWithOptions(report, (HashMap) arg);
		} else {
			throw new RuntimeException("Widget '" + getKeyword() + "'. Options have to be a closure or a map: " + arg.getClass());
		}
	}

	public abstract void initWithClosure(HtmlWidgetsReport report, Closure closure);

	public abstract void initWithOptions(HtmlWidgetsReport report, HashMap<String, Object> options);
}
