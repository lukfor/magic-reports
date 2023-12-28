package lukfor.reports.widgets;

import groovy.lang.Closure;
import groovy.xml.MarkupBuilder;
import lukfor.reports.HtmlWidgetsReport;
import lukfor.reports.functions.IncludeScriptFunction;
import lukfor.reports.functions.IncludeStyleFunction;
import lukfor.reports.widgets.HtmlNode;
import lukfor.reports.widgets.IWidget;
import lukfor.reports.widgets.WidgetFactory;
import lukfor.reports.widgets.WidgetInstance;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class HtmlBlockBuilder extends MarkupBuilder {

	private HtmlWidgetsReport report;

	public HtmlBlockBuilder(HtmlWidgetsReport report, StringWriter writer) {
		super(writer);
		this.report = report;
		setDoubleQuotes(true);
		setExpandEmptyElements(true);
		setEscapeAttributes(false);
	}

	public void build(String id, Closure closure){
		closure.setDelegate(this);
		closure.setResolveStrategy(Closure.DELEGATE_FIRST);
		Map<String, String> attributes = new HashMap<>();
		attributes.put("id", id);
		invokeMethod("div",  new Object[]{attributes, closure});
	}

	public void plotly(HashMap<String, Object> config) {
		widget("plotly", config);
	}

	public void datatable(HashMap<String, Object> config) {
		widget("datatable", config);
	}

	public void widget(String name, HashMap<String, Object> config){
		IWidget widget = report.importWidget(name);
		WidgetInstance instance = widget.createInstance(config);
		report.addInstance(instance);
		HtmlNode node = instance.getHtml();
		if (node != null) {
			doInvokeMethod(node.getTag(), node.getTag(), new Object[]{node.getAttributes()});
		}
	}

}
