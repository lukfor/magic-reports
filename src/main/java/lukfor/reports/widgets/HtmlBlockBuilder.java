package lukfor.reports.widgets;

import groovy.lang.Closure;
import groovy.xml.MarkupBuilder;
import lukfor.reports.HtmlWidgetsReport;
import lukfor.reports.widgets.components.CardConfig;
import lukfor.reports.widgets.components.CardWidget;
import lukfor.reports.widgets.plots.PlotlyConfig;
import lukfor.reports.widgets.plots.PlotlyWidget;
import lukfor.reports.widgets.tables.DataTableConfig;
import lukfor.reports.widgets.tables.DataTableWidget;

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
		PlotlyWidget widget = new PlotlyWidget();
		widget.init(report, closure);
		widget(widget);
	}

	public void plotly(HashMap<String, Object> config) {
		PlotlyWidget widget = new PlotlyWidget();
		widget.init(report, config);
		widget(widget);
	}

	public void datatable(Closure closure) {
		DataTableWidget widget = new DataTableWidget();
		widget.init(report, closure);
		widget(widget);
	}

	public void datatable(HashMap<String, Object> config) {
		DataTableWidget widget = new DataTableWidget();
		widget.init(report, config);
		widget(widget);
	}

	public void card(Closure closure) {
		CardWidget widget = new CardWidget();
		widget.init(report, closure);
		widget(widget);
	}

	public void card(HashMap<String, Object> config) {
		CardWidget widget = new CardWidget();
		widget.init(report, config);
		widget(widget);
	}

	public void widget(IWidget widget){
		report.addWidget(widget);
		String html = widget.getHtml();
		if (html != null) {
			getMkp().yieldUnescaped(html);
		}
	}

}
