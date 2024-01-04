package lukfor.reports.widgets.plots;

import java.util.HashMap;

import groovy.lang.Closure;
import lukfor.reports.HtmlWidgetsReport;
import lukfor.reports.data.DataWrapper;
import lukfor.reports.widgets.AbstractWidget;

public class PlotlyWidget extends AbstractWidget {

	public static final String KEYWORD = "plotly";

	private String id;

	private PlotlyConfig config;

	public PlotlyWidget() {
		id = createId(KEYWORD);
	}

	@Override
	public String getKeyword() {
		return KEYWORD;
	}

	@Override
	public void initWithClosure(HtmlWidgetsReport report, Closure closure) {
		config = new PlotlyConfig();
		closure.setDelegate(config);
		closure.setResolveStrategy(Closure.DELEGATE_FIRST);
		closure.call();
	}

	@Override
	public void initWithOptions(HtmlWidgetsReport report, HashMap<String, Object> options) {
		throw new RuntimeException("Not supported! please use plotly{}");
	}

	@Override
	public String getHtml() {
		return "<div id=\"" + id + "\"></div>";
	}

	@Override
	public String getInitScript() {
		return "Plotly.newPlot('" + id + "', " + new DataWrapper(config.getTraces()).json() + ", "
				+ new DataWrapper(config.getLayout()).json() + ");";
	}

	@Override
	public String[] getStyles() {
		return new String[] {};
	}

	@Override
	public String[] getScripts() {
		return new String[] { "https://cdn.plot.ly/plotly-latest.min.js" };
	}

}
