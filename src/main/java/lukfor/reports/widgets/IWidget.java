package lukfor.reports.widgets;

import groovy.lang.Closure;
import lukfor.reports.HtmlWidgetsReport;

import java.util.HashMap;
import java.util.Map;

public interface IWidget {

	public String getKeyword();

	public void init(HtmlWidgetsReport report, Object args);

	public String getHtml();

	public String getInitScript();

	public String[] getStyles();

	public String[] getScripts();

}
