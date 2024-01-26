package lukfor.reports.widgets.plots;

import groovy.lang.Closure;
import lukfor.reports.HtmlWidgetsReport;
import lukfor.reports.data.DataWrapper;
import lukfor.reports.dsl.ParamsMap;
import lukfor.reports.widgets.AbstractWidget;

import java.util.HashMap;

public class LeafletWidget extends AbstractWidget {

	public static final String KEYWORD = "leaflet";

	private String id;

	private LeafletConfig config;

	private ParamsMap options;

	public LeafletWidget() {
		id = createId(KEYWORD);
	}

	@Override
	public String getKeyword() {
		return KEYWORD;
	}

	@Override
	public void setup(HtmlWidgetsReport report, ParamsMap options) {
		this.options = options;
		this.options.setDefault("height", "400px");
		config = new LeafletConfig();
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
		return "<div id=\"" + id + "\" style=\"height: " + options.get("height") + ";\"></div>";
	}

	@Override
	public String getInitScript() {
		double latitude = config.getMarkers().get(0)[0];
		double longitude = config.getMarkers().get(0)[1];
		int zoom = 5;
		String initScript =
						"var map = L.map('"+id+"').setView([\"" + latitude + "\", \"" + longitude + "\"], \"" + zoom + "\");\n" +
						"L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {\n" +
						"    attribution: '© OpenStreetMap contributors'\n" +
						"}).addTo(map);\n\n";
		for (Double[] marker: config.getMarkers()){
			initScript += "L.marker(" + new DataWrapper(marker).json() + ").addTo(map);";
		}
		return initScript;
	}

	@Override
	public String[] getStyles() {
		return new String[] {"https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"};
	}

	@Override
	public String[] getScripts() {
		return new String[] { "https://unpkg.com/leaflet@1.7.1/dist/leaflet.js" };
	}

}
