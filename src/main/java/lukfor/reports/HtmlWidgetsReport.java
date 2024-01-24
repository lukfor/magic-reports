package lukfor.reports;

import java.beans.Transient;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import lukfor.reports.dsl.ReportDSL;
import lukfor.reports.functions.IncludeScriptFunction;
import lukfor.reports.functions.IncludeStyleFunction;
import lukfor.reports.widgets.HtmlBlock;
import lukfor.reports.widgets.IWidget;
import org.codehaus.groovy.runtime.InvokerHelper;

public class HtmlWidgetsReport implements GroovyObject {

	protected Map<String, IWidget> importedWidgets = new HashMap<String, IWidget>();

	private List<IWidget> instances = new Vector<IWidget>();

	private List<HtmlBlock> blocks = new Vector<HtmlBlock>();

	private String templateDirectory = "/templates/github-readme";

	private String templateIndex= "index.html";

	private boolean useClassPath = true;

	private boolean selfContained = true;

	private ReportDSL reportDsl;

	private Map<String, Object> variables = new HashMap<>();

	private File output;

	public HtmlWidgetsReport(ReportDSL reportDsl) {
		this.reportDsl = reportDsl;
		output = reportDsl.getOutput();
	}

	public void selfContained(boolean selfContained){
		this.selfContained = selfContained;
	}

	public void setOutput(File output) {
		this.output = output;
	}

	public void output(File output){
		this.output = output;
	}

	public void output(String output){
		this.output = new File(output);
	}

	public void template(String template){
		if (new File(template).exists()){
			template(new File(template));
			return;
		}

		if (!template.startsWith("/")){
			this.templateDirectory = "/templates/" + template;
		} else {
			this.templateDirectory = template;
		}
	}

	public void template(File template){
		if (template.isAbsolute()){
			templateDirectory = template.getAbsoluteFile().getParent();
		} else {
			String relativeTemplateDirectory = template.getParent();
			templateDirectory = reportDsl.getBaseDir() + "/" + relativeTemplateDirectory;
		}
		templateIndex = template.getName();
		this.useClassPath = false;
	}

	public File file(Map<String, Object> params, String filename){
		File result = new File(filename);
		if (!result.isAbsolute()){
			result = new File(reportDsl.getBaseDir(), result.getPath());
		}
		boolean checkIfExists = (boolean)params.getOrDefault("checkIfExists",false);
		if (checkIfExists && !result.exists()){
			throw new RuntimeException("File '" + filename + "' not found.");
		}
		return result;
	}

	public File file(String filename){
		return file(new HashMap<String, Object>(), filename);
	}

	public File path(String filename){
		return path(new HashMap<String, Object>(), filename);
	}

	public File path(Map<String, Object> params, String filename){
		return file(params, filename);
	}

	public HtmlBlock addBlock(String name, Closure closure){
		HtmlBlock block = new HtmlBlock(this, name);
		block.build(closure);
		blocks.add(block);
		return block;
	}

	public void render() throws IOException {

		HtmlReport report = new HtmlReport(templateDirectory, useClassPath);
		report.setMainFilename(templateIndex);
		report.setSelfContained(selfContained);
		variables.put("head", getHead(report));
		variables.put("script", getScript(report));
		report.set("report", variables);
		report.generate(output);
	}

	private void importWidget(IWidget widget) {
        importedWidgets.putIfAbsent(widget.getKeyword(), widget);
	}

	public void addWidget(IWidget widget){
		importedWidgets.putIfAbsent(widget.getKeyword(), widget);
		instances.add(widget);
	}

	protected String getHead(HtmlReport report, IWidget widget) {
		final IncludeStyleFunction styleFunction = new IncludeStyleFunction(report);
		StringBuilder html = new StringBuilder("<!-- Widget: " + widget.getKeyword() + " -->\n");
		for (String style : widget.getStyles()) {
			html.append(styleFunction.apply(style)).append("\n");
		}
		return html.toString();
	}

	public String getHead(HtmlReport report) {
		StringBuilder head = new StringBuilder();
		for (IWidget widget : importedWidgets.values()) {
			head.append(getHead(report, widget));
		}

		final IncludeScriptFunction scriptFunction = new IncludeScriptFunction(report);
		for (IWidget widget : importedWidgets.values()) {
			head.append("\n");
			head.append("<!-- Widget: ").append(widget.getKeyword()).append(" -->\n");
			for (String script : widget.getScripts()) {
				head.append(scriptFunction.apply(script)).append("\n");
			}
		}
		return head.toString();
	}

	public String getScript(HtmlReport report) {

		final IncludeScriptFunction scriptFunction = new IncludeScriptFunction(report);

		// activate and init only imported widgets
		StringBuilder html = new StringBuilder();
		html.append("\n");
		html.append("<!-- Init Widgets -->\n");
		html.append("<script>\n");
		html.append("$(document).ready( function () {\n");
		for (IWidget instance : instances) {
			html.append("\n");
			html.append(instance.getInitScript());
		}
		html.append("});\n");
		html.append("</script>");

		return html.toString();
	}

	@Override
	public Object getProperty(String propertyName) {
		return GroovyObject.super.getProperty(propertyName);
	}

	@Override
	public void setProperty(String propertyName, Object newValue) {
		GroovyObject.super.setProperty(propertyName, newValue);
	}

	// never persist the MetaClass
	private transient MetaClass metaClass = getDefaultMetaClass();

	@Override
	@Transient
	public MetaClass getMetaClass() {
		return this.metaClass;
	}

	@Override
	public void setMetaClass(/* @Nullable */ final MetaClass metaClass) {
		this.metaClass = Optional.ofNullable(metaClass).orElseGet(this::getDefaultMetaClass);
	}

	private MetaClass getDefaultMetaClass() {
		return InvokerHelper.getMetaClass(this.getClass());
	}

	public Object invokeMethod(String name, Object args) {
		try {
			return GroovyObject.super.invokeMethod(name, args);
		} catch (groovy.lang.MissingMethodException e) {
			List list = InvokerHelper.asList(args);
			if (list.isEmpty()){
				throw e;
			} else if (list.size() > 1){
				throw new RuntimeException("Parameter '" + name + "'. More than one parameter: " + list);
			}
			Object arg = list.get(0);
			if (arg instanceof  Closure){
				// If closure, render as html block and add html to template
				HtmlBlock block = addBlock(name, (Closure) arg);
				variables.put(block.getName(), block.getContent());
			} else {
				// all other objects are passed to template
				variables.put(name, arg);
			}
			return null;
		}
	}
}
