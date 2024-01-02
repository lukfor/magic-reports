package lukfor.reports;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import lukfor.reports.functions.IncludeScriptFunction;
import lukfor.reports.functions.IncludeStyleFunction;
import lukfor.reports.widgets.HtmlBlock;
import lukfor.reports.widgets.HtmlBlockBuilder;
import lukfor.reports.widgets.IWidget;
import org.codehaus.groovy.runtime.InvokerHelper;

import java.beans.Transient;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

public class Component {

	private Closure closure;

	public static Component build(Closure closure) {
		return new Component(closure);
	}

	public Component(Closure closure){
		this.closure = closure;
	}

	public String render(HtmlWidgetsReport report){
		StringWriter writer = new StringWriter();
		HtmlBlockBuilder builder = new HtmlBlockBuilder(report, writer);
		builder.build("component-container", closure);
		return writer.toString();
	}

}
