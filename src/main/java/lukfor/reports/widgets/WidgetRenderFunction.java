package lukfor.reports.widgets;

import java.util.HashMap;
import java.util.function.Function;

import com.google.gson.Gson;

import lukfor.reports.HtmlReport;

public abstract class WidgetRenderFunction implements Function<Object, String> {

	private HtmlReport report;

	public WidgetRenderFunction(HtmlReport report) {
		this.report = report;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String apply(Object config) {
		if (config instanceof String) {
			// load config from provided json file

			String content = report.renderTemplate((String) config);
			Gson gson = new Gson();
			HashMap map = gson.fromJson(content, HashMap.class);
			return render(map);
		} else {
			// use provided config
			return render((HashMap<String, Object>) config);
		}
	}

	public abstract String render(HashMap<String, Object> config);

}
