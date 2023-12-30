package lukfor.reports;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import io.marioslab.basis.template.Error;
import io.marioslab.basis.template.Template;
import io.marioslab.basis.template.TemplateContext;
import io.marioslab.basis.template.TemplateLoader;
import io.marioslab.basis.template.TemplateLoader.CachingTemplateLoader;
import io.marioslab.basis.template.parsing.Span;
import lukfor.reports.functions.ArrayHelperFunction;
import lukfor.reports.functions.ImageFunction;
import lukfor.reports.functions.ImageUrlFunction;
import lukfor.reports.functions.IncludeScriptFunction;
import lukfor.reports.functions.IncludeStyleFunction;
import lukfor.reports.functions.JsonFunction;
import lukfor.reports.functions.text.DecimalFunction;
import lukfor.reports.functions.text.PercentageFunction;
import lukfor.reports.util.FileUtil;

public class HtmlReport {

	private boolean selfContained = true;

	private boolean useClasspath = true;

	private String inputDirectory;

	private Date createdOn;

	public String mainFilename = "index.html";

	private TemplateLoader loader;

	private TemplateContext context;

	private HtmlReportAssets assets;

	public HtmlReport(File inputDirectory) {
		this(inputDirectory.getAbsolutePath(), false);
	}

	public HtmlReport(String inputDirectory) {
		this(inputDirectory, true);
	}

	public HtmlReport(String inputDirectory, boolean useClasspath) {
		this.useClasspath = useClasspath;
		this.inputDirectory = inputDirectory;
		if (useClasspath) {
			System.out.println("Template Loader: Classpath");
			this.loader = new MyClasspathTemplateLoader();
		}else{
			System.out.println("Template Loader: Files");
			this.loader = new TemplateLoader.FileTemplateLoader();
		}
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

	public String renderTemplateAndCopyToAssets(String path) throws Exception {

		String content = renderTemplate(path);
		String filename = assets.addToAssets(path, content.getBytes());

		return filename;
	}
	
	public void generate(File outputFile) throws IOException {
		if (useClasspath) {
			System.out.println("Process resource " + inputDirectory + "/" + mainFilename + "...");
		}else {
			System.out.println("Process file " + inputDirectory + "/" + mainFilename + "...");
		}

		createdOn = new Date();

		context.set("report", this);

		context.set("include_style", new IncludeStyleFunction(this));
		context.set("import_style", new IncludeStyleFunction(this));
		context.set("include_script", new IncludeScriptFunction(this));
		context.set("import_script", new IncludeScriptFunction(this));

		context.set("json", new JsonFunction());
		
		context.set("array", new ArrayHelperFunction());
		
		context.set("image", new ImageFunction(this));
		context.set("image_url", new ImageUrlFunction(this));

		context.set("percentage", new PercentageFunction());
		context.set("decimal", new DecimalFunction());
		
		assets = new HtmlReportAssets(outputFile);

		Template template = loadTemplate(mainFilename);
		FileOutputStream outputStream = new FileOutputStream(outputFile);
		template.render(context, outputStream);
		outputStream.close();

		System.out.println("HTML Report written to " + outputFile.getAbsolutePath() + ".");

	}
	
	/** A TemplateLoader to load templates from the classpath. Extended to support relative paths with ../ **/
	public static class MyClasspathTemplateLoader extends CachingTemplateLoader {
		@Override
		protected Source loadSource (String path) {
			try {

				String resolvedPath = FileUtil.resolvePath(path);
				
				return new Source(path, MyStreamUtils.readString(MyClasspathTemplateLoader.class.getResourceAsStream(resolvedPath)));
			} catch (Throwable t) {
				t.printStackTrace();
				Error.error("Couldn't load template '" + path + "'.", new Span(new Source(path, " "), 0, 0), t);
				throw new RuntimeException(""); // never reached
			}
		}
	}
	
	static class MyStreamUtils {
		private static String readString (InputStream in) throws IOException {
			byte[] buffer = new byte[1024 * 10];
			int read = 0;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			while ((read = in.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}
			return new String(out.toByteArray(), "UTF-8");
		}
	}

}
