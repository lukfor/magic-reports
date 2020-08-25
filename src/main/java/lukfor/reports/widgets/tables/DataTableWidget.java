package lukfor.reports.widgets.tables;

import java.util.HashMap;

import lukfor.reports.data.DataWrapper;
import lukfor.reports.widgets.AbstractWidget;
import lukfor.reports.widgets.WidgetInstance;

public class DataTableWidget extends AbstractWidget {

	@Override
	public String getId() {
		return "data_table";
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
	public WidgetInstance createInstance(HashMap<String, Object> config) {

		String id = createId();		
		String html = "<table id=\"" + id + "\" class=\"display\"></table>";
		String code = "$('#" + id + "').DataTable(" + new DataWrapper(config).json() + ");";

		return new WidgetInstance(id, html, code);

	}

}
