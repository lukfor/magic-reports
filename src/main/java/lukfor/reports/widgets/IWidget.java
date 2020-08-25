package lukfor.reports.widgets;

import java.util.HashMap;

public interface IWidget {

	public String getId();

	public WidgetInstance createInstance(HashMap<String, Object> config);

	public String[] getStyles();

	public String[] getScripts();
	
}
