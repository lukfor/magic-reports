package lukfor.reports.widgets;

import groovy.lang.Closure;
import groovy.xml.MarkupBuilder;
import lukfor.reports.HtmlWidgetsReport;
import lukfor.reports.widgets.components.CardConfig;
import lukfor.reports.widgets.plots.PlotlyConfig;
import lukfor.reports.widgets.tables.DataTableConfig;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

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

	public void plotly(Closure closure) {
		PlotlyConfig plotlyConfig = new PlotlyConfig();
		closure.setDelegate(plotlyConfig);
		closure.setResolveStrategy(Closure.DELEGATE_FIRST);
		closure.call();
		widget("plotly", plotlyConfig.getMap());
	}

	public void plotly(HashMap<String, Object> config) {
		throw new RuntimeException("Not supported! please use plotly{}");
	}

	public void datatable(Closure closure) {
		throw new RuntimeException("Not supported! please use datatable()");
	}

	public void datatable(HashMap<String, Object> config) {
		DataTableConfig dataTableConfig = new DataTableConfig(config);
		widget("datatable", dataTableConfig.getMap());
	}


	public void card(Closure closure) {
		CardConfig cardConfig = new CardConfig(report);
		closure.setDelegate(cardConfig);
		closure.setResolveStrategy(Closure.DELEGATE_ONLY);
		closure.call();
		//doInvokeMethod("div", "div", new Object[0]);
		getMkp().yieldUnescaped(cardConfig.getHtml());
	}

	public void card(HashMap<String, Object> config) {
		throw new RuntimeException("Not supported! please use card{}");
	}


	public void vegalite(HashMap<String, Object> config) {
		widget("vega_lite", config);
	}

	public void widget(String name, HashMap<String, Object> config){
		IWidget widget = report.importWidget(name);
		WidgetInstance instance = widget.createInstance(config);
		report.addInstance(instance);
		String html = instance.getHtml();
		if (html != null) {
			getMkp().yieldUnescaped(html);
		}
	}

}
