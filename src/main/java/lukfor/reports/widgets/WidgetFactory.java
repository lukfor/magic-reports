package lukfor.reports.widgets;

import lukfor.reports.HtmlReport;
import lukfor.reports.widgets.plots.PlotlyWidget;
import lukfor.reports.widgets.plots.VegaLiteWidget;
import lukfor.reports.widgets.tables.DataTableWidget;

public class WidgetFactory {

	public static IWidget createWidget(String id) {

		switch (id) {

		case "datatable":
			return new DataTableWidget();

		case "plotly":
			return new PlotlyWidget();
			
		case "vega_lite":
			return new VegaLiteWidget();

		default:
			throw new RuntimeException("Widget '" + id + "' not found.");

		}

	}

}
