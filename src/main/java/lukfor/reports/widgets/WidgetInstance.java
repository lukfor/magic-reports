package lukfor.reports.widgets;

public class WidgetInstance {

	private String id;

	private String html = null;

	private String script = "";

	public WidgetInstance(String id, String html, String script) {
		this.id = id;
		this.html = html;
		this.script = script;
	}

	public String getId() {
		return id;
	}

	public String getHtml() {
		return html;
	}

	public String getScript() {
		return script;
	}

}
