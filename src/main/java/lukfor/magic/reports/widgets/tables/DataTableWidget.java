package lukfor.magic.reports.widgets.tables;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import lukfor.magic.reports.HtmlReport;
import lukfor.magic.reports.data.DataWrapper;
import lukfor.magic.reports.widgets.IWidget;
import lukfor.magic.reports.widgets.WidgetRenderFunction;

public class DataTableWidget implements IWidget {

	private RenderFunction renderFunction;

	public DataTableWidget(HtmlReport report) {
		 renderFunction = new RenderFunction(report);
	}
	
	@Override
	public String getId() {
		return "data_table";
	}

	@Override
	public WidgetRenderFunction getRenderFunction() {
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

	public static class RenderFunction extends WidgetRenderFunction {

		private List<String> inits = new Vector<String>();;
		
		public RenderFunction(HtmlReport report) {
			super(report);
		}

		@Override
		public String render(HashMap<String, Object> config) {

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
