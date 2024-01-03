package lukfor.reports.widgets;

import groovy.lang.Closure;
import groovy.xml.MarkupBuilder;
import lukfor.reports.Component;
import lukfor.reports.HtmlWidgetsReport;
import lukfor.reports.widgets.components.CardConfig;
import lukfor.reports.widgets.components.CardWidget;
import lukfor.reports.widgets.plots.PlotlyConfig;
import lukfor.reports.widgets.plots.PlotlyWidget;
import lukfor.reports.widgets.tables.DataTableConfig;
import lukfor.reports.widgets.tables.DataTableWidget;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HtmlBlockBuilder extends MarkupBuilder {

	private HtmlWidgetsReport report;

	private WidgetRegistry widgetRegistry = WidgetRegistry.getInstance();

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

	public void markdown(String text) {
		Parser parser = Parser.builder().build();
		Node document = parser.parse(text);
		HtmlRenderer renderer = HtmlRenderer.builder().build();
		String html = renderer.render(document);
		getMkp().yieldUnescaped(html);
	}

	public void render(Component component) {
		String html = component.render(report);
		getMkp().yieldUnescaped(html);
	}

	public void render(Closure closure) {
		String html = Component.build(closure).render(report);
		getMkp().yieldUnescaped(html);
	}

	public void md(String text) {
		markdown(text);
	}

	@Override
	protected Object doInvokeMethod(String methodName, Object name, Object args) {
		if (!widgetRegistry.contains(methodName)){
			return super.doInvokeMethod(methodName, name, args);
		}

		IWidget widget = widgetRegistry.getInstance(methodName);
		widget.init(report, args);
		widget(widget);

		return null;
	}

	public void widget(IWidget widget){
		report.addWidget(widget);
		String html = widget.getHtml();
		if (html != null) {
			getMkp().yieldUnescaped(html);
		}
	}

}
