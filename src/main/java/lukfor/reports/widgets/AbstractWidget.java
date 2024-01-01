package lukfor.reports.widgets;

public abstract class AbstractWidget implements IWidget {

	private static int instances;
	
	protected String createId(String name) {
		instances++;
		return "widget_" + name + "_" + instances;
	}
	
}
