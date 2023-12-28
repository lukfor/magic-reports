package lukfor.reports.widgets.plots;

import java.util.HashMap;

import lukfor.reports.data.DataWrapper;
import lukfor.reports.widgets.AbstractWidget;
import lukfor.reports.widgets.HtmlNode;
import lukfor.reports.widgets.WidgetInstance;

public class PlotlyWidget extends AbstractWidget {

	@Override
	public String getId() {
		return "plotly";
	}

	@Override
	public String[] getStyles() {
		return new String[] {};
	}

	@Override
	public String[] getScripts() {
		return new String[] { "https://cdn.plot.ly/plotly-latest.min.js" };
	}

	@Override
	public WidgetInstance createInstance(HashMap<String, Object> config) {
		String id = createId();

		HtmlNode html = new HtmlNode("div");
		html.setAttribute("id", id);

		Object traces = config.get("traces");
		Object layout = config.get("layout");

		String code = "Plotly.newPlot('" + id + "', " + new DataWrapper(traces).json() + ", "
				+ new DataWrapper(layout).json() + ");";

		return new WidgetInstance(id, html, code);
	}

}
