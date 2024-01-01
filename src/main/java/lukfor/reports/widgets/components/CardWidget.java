package lukfor.reports.widgets.components;

import groovy.lang.Closure;
import lukfor.reports.HtmlWidgetsReport;
import lukfor.reports.data.DataWrapper;
import lukfor.reports.widgets.AbstractWidget;
import lukfor.reports.widgets.plots.PlotlyConfig;

import java.util.HashMap;

public class CardWidget extends AbstractWidget {

	public static final String KEYWORD = "card";

	private String id;

	private CardConfig config;

	public CardWidget() {
		id = createId(KEYWORD);
	}

	@Override
	public String getKeyword() {
		return KEYWORD;
	}

	@Override
	public void init(HtmlWidgetsReport report, Closure closure) {
		config = new CardConfig(report);
		closure.setDelegate(config);
		closure.setResolveStrategy(Closure.DELEGATE_FIRST);
		closure.call();
	}

	@Override
	public void init(HtmlWidgetsReport report, HashMap<String, Object> options) {
		throw new RuntimeException("Not supported! please use card{}");
	}

	@Override
	public String getHtml() {
		String html = "<div class=\"card\"><h4>" + config.getTitle() + "</h4>";
		if (config.getDescription() != null){
			html += "<p>" + config.getDescription() + "</p>";
		}
		html += config.getBody() + "</div>";
		return html;
	}

	@Override
	public String getInitScript() {
		return "";
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
