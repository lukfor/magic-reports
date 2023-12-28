package lukfor.reports.widgets;

import groovy.lang.Closure;
import lukfor.reports.HtmlWidgetsReport;

import java.io.StringWriter;

public class HtmlBlock {

	protected HtmlBlockBuilder builder;

	private StringWriter writer = new StringWriter();

	private String name = null;

	public HtmlBlock(HtmlWidgetsReport report, String name) {
		this.name = name;
		writer.append("\n<!-- HtmlBlock " + name + "-->\n");
		builder = new HtmlBlockBuilder(report, writer);
	}

	public String getName() {
		return name;
	}

	public void build(Closure closure){
		builder.build(name, closure);
	}

	public String getContent(){
		return writer.toString();
	}

}
