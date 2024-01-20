package lukfor.reports.widgets.plots;

import groovy.lang.Closure;
import lukfor.reports.HtmlWidgetsReport;
import lukfor.reports.data.DataWrapper;
import lukfor.reports.widgets.AbstractWidget;

import java.util.HashMap;

public class LeafletWidget extends AbstractWidget {

	public static final String KEYWORD = "leaflet";

	private String id;

	private LeafletConfig config;

	public LeafletWidget() {
		id = createId(KEYWORD);
	}

	@Override
	public String getKeyword() {
		return KEYWORD;
	}

	@Override
	public void initWithClosure(HtmlWidgetsReport report, Closure closure) {
		config = new LeafletConfig();
		closure.setDelegate(config);
		closure.setResolveStrategy(Closure.DELEGATE_FIRST);
		closure.call();
	}

	@Override
	public void initWithOptions(HtmlWidgetsReport report, HashMap<String, Object> options) {
		throw new RuntimeException("Not supported! please use leaflet{}");
	}

	@Override
	public String getHtml() {
		return "<div id=\"" + id + "\" style=\"height: 400px;\"></div>";
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
