package lukfor.magic.reports.widgets;

import java.util.HashMap;
import java.util.function.Function;

public interface IWidget {

	public String getId();

	public Function<HashMap<String, Object>, String> getRenderFunction();

	public String[] getStyles();
	
	public String[] getScripts();

	public String getInitializer();

}
