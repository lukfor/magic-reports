package lukfor.magic.reports.widgets;

public interface IWidget {

	public String getId();

	public WidgetRenderFunction getRenderFunction();

	public String[] getStyles();
	
	public String[] getScripts();

	public String getInitializer();

}
