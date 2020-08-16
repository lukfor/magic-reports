package lukfor.magic.reports;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import io.marioslab.basis.template.Template;
import io.marioslab.basis.template.TemplateContext;
import io.marioslab.basis.template.TemplateLoader;
import io.marioslab.basis.template.TemplateLoader.ClasspathTemplateLoader;
import lukfor.magic.reports.functions.DataWrapperFunction;
import lukfor.magic.reports.functions.ImageFunction;
import lukfor.magic.reports.functions.ImageUrlFunction;
import lukfor.magic.reports.functions.IncludeScriptFunction;
import lukfor.magic.reports.functions.IncludeStyleFunction;
import lukfor.magic.reports.util.FileUtil;

public class HtmlReport {

	private boolean selfContained = true;

	private boolean useClasspath = true;

	private String inputDirectory;

	private Date createdOn;

	public String mainFilename = "index.html";

	private TemplateLoader loader;

	private TemplateContext context;

	private HtmlReportAssets assets;

	public HtmlReport(String inputDirectory) {
		this.inputDirectory = inputDirectory;
		this.loader = new ClasspathTemplateLoader();
		this.context = new TemplateContext();
	}

	public void set(String name, Object value) {
		this.context.set(name, value);
	}

	public void setMainFilename(String mainFilename) {
		this.mainFilename = mainFilename;
	}

	public void setSelfContained(boolean selfContained) {
		this.selfContained = selfContained;
	}

	public boolean isSelfContained() {
		return selfContained;
	}

	public void setUseClasspath(boolean useClasspath) {
		this.useClasspath = useClasspath;
	}

	public boolean isUseClasspath() {
		return useClasspath;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public Template loadTemplate(String path) {
		String filename = inputDirectory + "/" + path;
		return loader.load(filename);
	}

	public String renderTemplate(String path) {
		Template template = loadTemplate(path);
		return template.render(context);
	}

	public String getContent(String path) throws Exception {
		String filename = inputDirectory + "/" + path;
		if (useClasspath) {
			return FileUtil.readStringFromClasspath(filename);
		} else {
			return FileUtil.readStringFromFile(filename);
		}
	}

	public byte[] getBytes(String path) throws Exception {
		String filename = inputDirectory + "/" + path;
		if (useClasspath) {
			return FileUtil.readBytesFromClasspath(filename);
		} else {
			return FileUtil.readBytesFromFile(filename);
		}

	}

	public String copyToAssets(String path) throws Exception {

		byte[] bytes = getBytes(path);
		String filename = assets.addToAssets(path, bytes);

		return filename;
	}

	public void generate(File outputFile) throws IOException {

		System.out.println("Process file " + inputDirectory + "/" + mainFilename + "...");

		createdOn = new Date();

		context.set("report", this);

		context.set("include_style", new IncludeStyleFunction(this));
		context.set("include_script", new IncludeScriptFunction(this));

		context.set("data", new DataWrapperFunction());

		context.set("image", new ImageFunction(this));
		context.set("image_url", new ImageUrlFunction(this));

		assets = new HtmlReportAssets(outputFile);

		Template template = loadTemplate(mainFilename);
		FileOutputStream outputStream = new FileOutputStream(outputFile);
		template.render(context, outputStream);
		outputStream.close();

		System.out.println("HTML Report written to " + outputFile.getAbsolutePath() + ".");

	}

}
