package lukfor.reports.widgets;

import groovy.lang.Closure;
import lukfor.reports.HtmlWidgetsReport;
import lukfor.reports.dsl.ParamsMap;
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

		ParamsMap params = ParamsMap.buildFromArgs(args);
		setup(report, params);
	}

	public abstract void setup(HtmlWidgetsReport report, ParamsMap params);

}
