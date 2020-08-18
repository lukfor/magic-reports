package lukfor.magic.reports.widgets;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.function.Function;

import lukfor.magic.reports.data.DataWrapper;

public class PlotlyWidget implements IWidget {

	private RenderFunction renderFunction = new RenderFunction();

	@Override
	public String getId() {
		return "plotly";
	}

	@Override
	public Function<HashMap<String, Object>, String> getRenderFunction() {
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

	public static class RenderFunction implements Function<HashMap<String, Object>, String> {

		private List<String> inits = new Vector<String>();;

		@Override
		public String apply(HashMap<String, Object> data) {

			String id = "magic_plot_" + inits.size();

			Object traces = data.get("traces");
			Object layout = data.get("layout");

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
