package lukfor.reports.widgets.tables;

import java.util.HashMap;

import groovy.lang.Closure;
import lukfor.reports.HtmlWidgetsReport;
import lukfor.reports.data.DataWrapper;
import lukfor.reports.widgets.AbstractWidget;

public class DataTableWidget extends AbstractWidget {

	public static final String KEYWORD = "datatable";

	private String id;

	private DataTableConfig config;

	public DataTableWidget() {
		id = createId(KEYWORD);
	}

	@Override
	public String getKeyword() {
		return KEYWORD;
	}

	@Override
	public void init(HtmlWidgetsReport report, Closure closure) {
		throw new RuntimeException("Not supported! please use datatable()");
	}

	@Override
	public void init(HtmlWidgetsReport report, HashMap<String, Object> options) {
		config = new DataTableConfig(options);
	}

	@Override
	public String getHtml() {
		return "<table id=\"" + id + "\" class=\"display\"></table>";
	}

	@Override
	public String getInitScript() {
		return  "$('#" + id + "').DataTable(" + new DataWrapper(config.getOptions()).json() + ");";
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


}
