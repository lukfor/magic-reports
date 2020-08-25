package lukfor.reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.function.Function;

import lukfor.reports.functions.IncludeScriptFunction;
import lukfor.reports.functions.IncludeStyleFunction;
import lukfor.reports.widgets.IWidget;
import lukfor.reports.widgets.WidgetInstance;
import lukfor.reports.widgets.WidgetFactory;
import lukfor.reports.widgets.WidgetRenderFunction;

public class HtmlWidgetsReport extends HtmlReport {

	protected Map<String, IWidget> importedWidgets = new HashMap<String, IWidget>();

	private List<WidgetInstance> instances = new Vector<>();

	public HtmlWidgetsReport(String inputDirectory) {

		super(inputDirectory);

		set("import_widget", new Function<String, String>() {

			@Override
			public String apply(String id) {

				// import widget
				IWidget widget = importWidget(id);

				// register render function
				set(widget.getId(), new WidgetRenderFunction(HtmlWidgetsReport.this, widget));

				// return head
				return getHead(widget);
			}

		});

		set("activate_widgets", new Function<String, String>() {

			@Override
			public String apply(String arg0) {
				return getScript();
			}

		});

	}

	public void addInstance(WidgetInstance instance) {
		instances.add(instance);
	}

	protected IWidget importWidget(String id) {
		IWidget widget = importedWidgets.get(id);
		if (widget == null) {
			widget = WidgetFactory.createWidget(id, this);
			importedWidgets.put(id, widget);
		}
		return widget;
	}

	protected String getHead(IWidget widget) {
		final IncludeStyleFunction styleFunction = new IncludeStyleFunction(this);
		String html = "<!-- Widget: " + widget.getId() + " -->\n";
		for (String style : widget.getStyles()) {
			html += styleFunction.apply(style) + "\n";
		}
		return html;
	}

	protected String getHead() {
		String head = "";
		for (IWidget widget : importedWidgets.values()) {
			head += getHead(widget);
		}
		return head;
	}

	protected String getScript() {

		final IncludeScriptFunction scriptFunction = new IncludeScriptFunction(this);

		// activate and init only imported widgets
		String html = "";

		for (IWidget widget : importedWidgets.values()) {
			html += "\n";
			html += "<!-- Widget: " + widget.getId() + " -->\n";
			for (String script : widget.getScripts()) {
				html += scriptFunction.apply(script) + "\n";
			}
		}

		html += "\n";
		html += "<!-- Init Widgets -->\n";
		html += "<script>\n";
		html += "$(document).ready( function () {\n";
		for (WidgetInstance instance : instances) {
			html += "\n";
			html += instance.getScript();
		}
		html += "});\n";
		html += "</script>";

		return html;
	}

}
