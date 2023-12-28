package lukfor.reports.widgets;

public class WidgetInstance {

	private String id;

	private HtmlNode html = null;

	private String script = "";

	public WidgetInstance(String id, HtmlNode html, String script) {
		this.id = id;
		this.html = html;
		this.script = script;
	}

	public String getId() {
		return id;
	}

	public HtmlNode getHtml() {
		return html;
	}

	public String getScript() {
		return script;
	}

}
