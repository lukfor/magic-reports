package lukfor.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lukfor.reports.widgets.IWidget;
import lukfor.reports.widgets.WidgetInstance;

public class SimpleHtmlReport extends HtmlWidgetsReport {

	public static final String DEFAULT_LAYOUT = "/layouts/default";

	private String layout = DEFAULT_LAYOUT;

	private StringBuilder content = new StringBuilder();

	private String title = "Table Report";

	public SimpleHtmlReport() {
		super(DEFAULT_LAYOUT);
		importWidget("data_table");
	}

	public void title(String title) {
		this.title = title;
		add(tag("h1", title));
	}

	public void section(String section) {
		add(tag("h2", section));
	}

	public void text(String text) {
		add(tag("p", text));
	}

	public void code(String text) {
		add(tag("pre", tag("code", text)));
	}

	public void table(Object[] objects, String... columns) {
		Gson gson = new Gson();
		List<JsonElement> data = new Vector<JsonElement>();
		for (int i = 0; i < objects.length; i++) {
			data.add(gson.toJsonTree(objects[i]));
		}
		table(columns, data);
	}

	public void table(String[] header, List<JsonElement> objects) {

		List<Object> configColumns = new Vector<Object>();

		for (int i = 0; i < header.length; i++) {
			HashMap<String, Object> configColumn = new HashMap<String, Object>();
			configColumn.put("data", "_" + i);
			configColumn.put("title", header[i]);
			configColumns.add(configColumn);
		}

		List<Object> dataObjects = new Vector<Object>();
		for (int i = 0; i < objects.size(); i++) {
			HashMap<String, Object> dataObject = new HashMap<String, Object>();
			for (int j = 0; j < header.length; j++) {
				dataObject.put("_" + j, objects.get(i).getAsJsonObject().get(header[j]));
			}
			dataObjects.add(dataObject);
		}

		HashMap<String, Object> config = new HashMap<String, Object>();
		config.put("columns", configColumns);
		config.put("data", dataObjects);

		widget("data_table", config);

	}

	public void plot(Object[] objects, String x, String y) {
		plot(objects, x, y, "markers");
	}

	public void plot(Object[] objects, String x, String y, String type) {

		HashMap<String, Object> config = new HashMap<>();

		Gson gson = new Gson();
		List<Object> xData = new Vector<Object>();
		List<Object> yData = new Vector<Object>();
		for (int i = 0; i < objects.length; i++) {
			JsonObject object = gson.toJsonTree(objects[i]).getAsJsonObject();
			xData.add(object.get(x).getAsNumber());
			yData.add(object.get(y).getAsNumber());
		}

		List<Object> traces = new Vector<>();

		HashMap<String, Object> trace = new HashMap<>();
		trace.put("x", xData);
		trace.put("y", yData);
		trace.put("mode", type);
		trace.put("type", type);
		traces.add(trace);

		config.put("traces", traces);

		HashMap<String, Object> layout = new HashMap<>();
		// layout.put("title", x + " vs. " + y);

		HashMap<String, Object> xaxis = new HashMap<>();
		xaxis.put("title", x);
		layout.put("xaxis", xaxis);

		HashMap<String, Object> yaxis = new HashMap<>();
		yaxis.put("title", y);
		layout.put("yaxis", yaxis);

		config.put("layout", layout);

		widget("plotly", config);
	}

	public void widget(String name, HashMap<String, Object> config) {
		IWidget widget = importWidget(name);
		WidgetInstance instance = widget.createInstance(config);
		addInstance(instance);
		content.append(instance.getHtml());
	}

	public void add(String tag) {
		this.content.append(tag);
	}

	public String tag(String tag, String content) {
		StringBuilder html = new StringBuilder();
		html.append("<");
		html.append(tag);
		html.append(">");
		html.append(content);
		html.append("</");
		html.append(tag);
		html.append(">");
		return html.toString();
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getLayout() {
		return layout;
	}

	public void save(String filename) throws IOException {
		generate(new File(filename));
	}

	public void saveAndOpen(String filename) throws IOException {
		save(filename);
		File htmlFile = new File(filename);
		Desktop.getDesktop().browse(htmlFile.toURI());
	}

	@Override
	public void generate(File outputFile) throws IOException {

		set("title", title);
		set("head", getHead());
		set("content", content.toString());
		set("date", new Date());

		super.generate(outputFile);
	}

}
