package lukfor.reports.widgets.plots;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import lukfor.reports.HtmlReport;
import lukfor.reports.data.DataWrapper;
import lukfor.reports.widgets.IWidget;
import lukfor.reports.widgets.WidgetRenderFunction;

public class PlotlyWidget implements IWidget {

	private RenderFunction renderFunction;

	public PlotlyWidget(HtmlReport report) {
		renderFunction = new RenderFunction(report);
	}

	@Override
	public String getId() {
		return "plotly";
	}

	@Override
	public WidgetRenderFunction getRenderFunction() {
		return renderFunction;
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
	public String getInitializer() {

		String html = "";
		for (String code : renderFunction.getInits()) {
			html += code + "\n";
		}
		return html;

	}

	public static class RenderFunction extends WidgetRenderFunction {

		public RenderFunction(HtmlReport report) {
			super(report);
		}

		private List<String> inits = new Vector<String>();;

		@Override
		public String render(HashMap<String, Object> config) {

			String id = "magic_plot_" + inits.size();

			Object traces = config.get("traces");
			Object layout = config.get("layout");

			String code = "Plotly.newPlot('" + id + "', " + new DataWrapper(traces).json() + ", "
					+ new DataWrapper(layout).json() + ");";
			inits.add(code);

			return "<div id=\"" + id + "\"></div>";

		}

		public List<String> getInits() {
			return inits;
		}

	}

}
