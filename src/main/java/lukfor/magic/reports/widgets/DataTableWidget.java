package lukfor.magic.reports.widgets;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.function.Function;

import lukfor.magic.reports.data.DataWrapper;

public class DataTableWidget implements IWidget {

	private RenderFunction renderFunction = new RenderFunction();

	@Override
	public String getId() {
		return "data_table";
	}

	@Override
	public Function<HashMap<String, Object>, String> getRenderFunction() {
		return renderFunction;
	}

	@Override
	public String[] getStyles() {
		return new String[] { "https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" };
	}

	@Override
	public String[] getScripts() {
		return new String[] { "https://code.jquery.com/jquery-3.5.1.slim.min.js",
				"https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js" };
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
		public String apply(HashMap<String, Object> config) {

			String id = "magic_table_" + inits.size();

			String code = "$('#" + id + "').DataTable(" + new DataWrapper(config).json() + ");";
			inits.add(code);

			return "<table id=\"" + id + "\" class=\"display\"></table>";

		}

		public List<String> getInits() {
			return inits;
		}

	}

}
