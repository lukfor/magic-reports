package lukfor.reports.widgets;

import lukfor.reports.HtmlReport;
import lukfor.reports.widgets.plots.PlotlyWidget;
import lukfor.reports.widgets.plots.VegaLiteWidget;
import lukfor.reports.widgets.tables.DataTableWidget;

public class WidgetFactory {

	public static IWidget createWidget(String id, HtmlReport report) {

		switch (id) {

		case "data_table":
			return new DataTableWidget(report);

		case "plotly":
			return new PlotlyWidget(report);
			
		case "vega_lite":
			return new VegaLiteWidget(report);

		default:
			throw new RuntimeException("Widget '" + id + "' not found.");

		}

	}

}
