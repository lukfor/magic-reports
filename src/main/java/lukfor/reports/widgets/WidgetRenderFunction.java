package lukfor.reports.widgets;

import java.util.HashMap;
import java.util.function.Function;

import com.google.gson.Gson;

import lukfor.reports.HtmlWidgetsReport;

public class WidgetRenderFunction implements Function<Object, String> {

	private HtmlWidgetsReport report;

	private IWidget widget;

	public WidgetRenderFunction(HtmlWidgetsReport report, IWidget widget) {
		this.report = report;
		this.widget = widget;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String apply(Object config) {
				
		if (config instanceof String) {
			// load config from provided json file

			String content = report.renderTemplate((String) config);
			Gson gson = new Gson();
			HashMap map = gson.fromJson(content, HashMap.class);
			WidgetInstance instance = widget.createInstance(map);
			report.addInstance(instance);
			return instance.getHtml();
		} else {
			// use provided config
			WidgetInstance instance = widget.createInstance((HashMap<String, Object>) config);
			report.addInstance(instance);
			return instance.getHtml();
		}
	}

}
