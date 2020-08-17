package lukfor.magic.reports.widgets;

import java.util.HashMap;
import java.util.function.Function;

import lukfor.magic.reports.MagicReport;
import lukfor.magic.reports.data.DataWrapper;

public class DataTableWidget {

	public DataTableWidget(MagicReport report) {

		report.addStyle("https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css");

		report.addScript("https://code.jquery.com/jquery-3.5.1.slim.min.js");
		report.addScript("https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js");

		report.set("data_table", new RenderFunction(report));

	}

	public static class RenderFunction implements Function<HashMap<String, Object>, String> {

		private int elements = 0;

		private MagicReport report;

		public RenderFunction(MagicReport report) {
			this.report = report;
		}

		@Override
		public String apply(HashMap<String, Object> config) {

			elements++;

			String id = "magic_table_" + elements;

			String code = "$('#" + id + "').DataTable(" + new DataWrapper(config).json() + ");";
			report.addActivationCode(code);

			return "<table id=\"" + id + "\" class=\"display\"></table>";

		}

	}

}
