package lukfor.reports.widgets.plots;

import java.util.HashMap;

import groovy.lang.Closure;
import lukfor.reports.HtmlWidgetsReport;
import lukfor.reports.data.DataWrapper;
import lukfor.reports.dsl.ParamsMap;
import lukfor.reports.widgets.AbstractWidget;

public class PlotlyWidget extends AbstractWidget {

	public static final String KEYWORD = "plotly";

	private String id;

	private PlotlyConfig config;

	private ParamsMap options;

	public PlotlyWidget() {
		id = createId(KEYWORD);
	}

	@Override
	public String getKeyword() {
		return KEYWORD;
	}

	@Override
	public void setup(HtmlWidgetsReport report, ParamsMap options) {
		this.options = options;
		this.options.setDefault("height", "100%");
		config = new PlotlyConfig();
		if (!options.containsKey("body")){
			return;
		}
		Closure closure = (Closure) options.get("body");
		closure.setDelegate(config);
		closure.setResolveStrategy(Closure.DELEGATE_FIRST);
		closure.call();
	}

	@Override
	public String getHtml() {
		return "<div id=\"" + id + "\" style=\"height: " + options.get("height") + "\"></div>";
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
