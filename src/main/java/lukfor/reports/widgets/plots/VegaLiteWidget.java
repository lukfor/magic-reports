package lukfor.reports.widgets.plots;

import java.util.HashMap;

import lukfor.reports.data.DataWrapper;
import lukfor.reports.widgets.AbstractWidget;
import lukfor.reports.widgets.WidgetInstance;

public class VegaLiteWidget extends AbstractWidget {

	@Override
	public String getId() {
		return "vega_lite";
	}

	@Override
	public String[] getStyles() {
		return new String[] {};
	}

	@Override
	public String[] getScripts() {
		return new String[] { "https://cdn.jsdelivr.net/npm/vega@5.13.0",
				"https://cdn.jsdelivr.net/npm/vega-lite@4.14.1", "https://cdn.jsdelivr.net/npm/vega-embed@6.10.0" };
	}

	@Override
	public WidgetInstance createInstance(HashMap<String, Object> spec) {

		String id = createId();
		String html = "<div id=\"" + id + "\"></div>";
		String code = "vegaEmbed('#" + id + "', " + new DataWrapper(spec).json() + ");";

		return new WidgetInstance(id, html, code);

	}

}
