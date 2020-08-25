package lukfor.reports.widgets;

public abstract class AbstractWidget implements IWidget {

	private int instances;
	
	protected String createId() {
		instances++;
		return "widget_" + getId() + "_" + instances;
	}
	
}
