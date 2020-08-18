package lukfor.magic.reports.widgets;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.function.Function;

import lukfor.magic.reports.data.DataWrapper;

public class VegaLiteWidget implements IWidget {

	private RenderFunction renderFunction = new RenderFunction();

	@Override
	public String getId() {
		return "vega_lite";
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
		return new String[] { "https://cdn.jsdelivr.net/npm/vega@5.13.0","https://cdn.jsdelivr.net/npm/vega-lite@4.14.1","https://cdn.jsdelivr.net/npm/vega-embed@6.10.0" };
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
		public String apply(HashMap<String, Object> spec) {

			String id = "magic_vega_" + inits.size();

			String code = "vegaEmbed('#" + id + "', " + new DataWrapper(spec).json() + ");";
			inits.add(code);

			return "<div id=\"" + id + "\" style=\"width: 100%;\"></div>";

		}

		public List<String> getInits() {
			return inits;
		}

	}

}
