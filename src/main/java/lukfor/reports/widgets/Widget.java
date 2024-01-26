package lukfor.reports.widgets;

import lukfor.reports.HtmlWidgetsReport;
import lukfor.reports.dsl.ParamsMap;

public class Widget extends AbstractWidget {

	private static int instances;

	protected HtmlWidgetsReport report;

	protected ParamsMap options;

	protected String createId(String name) {
		instances++;
		return "widget_" + name + "_" + instances;
	}

	@Override
	public String getKeyword() {
		return null;
	}

	@Override
	public String getHtml() {
		return "";
	}

	@Override
	public String getInitScript() {
		return "";
	}

	@Override
	public String[] getStyles() {
		return new String[0];
	}

	@Override
	public String[] getScripts() {
		return new String[0];
	}

	@Override
	public void setup(HtmlWidgetsReport report, ParamsMap options) {
		this.report = report;
		this.options = options;
	}


}
