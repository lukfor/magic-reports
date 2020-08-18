package lukfor.magic.reports.widgets;

public class WidgetFactory {

	public static IWidget createWidget(String id) {

		switch (id) {

		case "data_table":
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
