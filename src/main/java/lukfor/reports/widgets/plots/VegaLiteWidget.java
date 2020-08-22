package lukfor.reports.widgets.plots;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import lukfor.reports.HtmlReport;
import lukfor.reports.data.DataWrapper;
import lukfor.reports.widgets.IWidget;
import lukfor.reports.widgets.WidgetRenderFunction;

public class VegaLiteWidget implements IWidget {

	private RenderFunction renderFunction;

	public VegaLiteWidget(HtmlReport report) {
		renderFunction = new RenderFunction(report);
	}

	@Override
	public String getId() {
		return "vega_lite";
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
		return new String[] { "https://cdn.jsdelivr.net/npm/vega@5.13.0",
				"https://cdn.jsdelivr.net/npm/vega-lite@4.14.1", "https://cdn.jsdelivr.net/npm/vega-embed@6.10.0" };
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

		private List<String> inits = new Vector<String>();;

		public RenderFunction(HtmlReport report) {
			super(report);
		}

		@Override
		public String render(HashMap<String, Object> spec) {

			String id = "magic_vega_" + inits.size();

			String code = "vegaEmbed('#" + id + "', " + new DataWrapper(spec).json() + ");";
			inits.add(code);

			return "<div id=\"" + id + "\"></div>";

		}

		public List<String> getInits() {
			return inits;
		}

	}

}
