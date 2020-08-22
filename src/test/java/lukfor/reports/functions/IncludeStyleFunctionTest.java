package lukfor.reports.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.TestCase;
import lukfor.reports.HtmlReport;
import lukfor.reports.util.FileUtil;

public class IncludeStyleFunctionTest extends TestCase {

	public void testLink() throws IOException {

		File folder = new File("test-output");
		FileUtil.deleteFolder(folder);
		folder.mkdirs();
		
		File output = new File(folder, "include_style_link.html");

		HtmlReport report = new HtmlReport("/include_style/link");
		report.setSelfContained(false);
		report.generate(output);

		assertTrue(output.exists());
		assertEqualsContent("/include_style/link/index.expected.html", output);

	}
	
	public void testLinkSelfContained() throws IOException {

		File folder = new File("test-output");
		FileUtil.deleteFolder(folder);
		folder.mkdirs();
		
		File output = new File(folder, "include_style_link.html");

		HtmlReport report = new HtmlReport("/include_style/link");
		report.setSelfContained(true);
		report.generate(output);

		assertTrue(output.exists());	
		assertEqualsContent("/include_style/link/index.expected.html", output);

	}

	public void testLocal() throws IOException {

		File folder = new File("test-output");
		FileUtil.deleteFolder(folder);
		folder.mkdirs();
		
		File output = new File(folder, "include_style_local.html");

		HtmlReport report = new HtmlReport("/include_style/local");
		report.setSelfContained(false);
		report.set("color", "#ff0000");
		report.generate(output);

		assertTrue(output.exists());
		assertEqualsContent("/include_style/local/index.expected.html", output);
		
		File assets = new File(folder, "include_style_local_files");		
		assertTrue(assets.exists());

		File style = new File(assets, "index.css");		
		assertTrue(style.exists());
		assertEqualsContent("/include_style/local/index.expected.css", style);

	}
	
	public void testLocalSelfContained() throws IOException {

		File folder = new File("test-output");
		FileUtil.deleteFolder(folder);
		folder.mkdirs();
		
		File output = new File(folder, "include_style_local.html");

		HtmlReport report = new HtmlReport("/include_style/local");
		report.setSelfContained(true);
		report.set("color", "#ff0000");
		report.generate(output);

		assertTrue(output.exists());
		assertEqualsContent("/include_style/local/index.expected.self_contained.html", output);
		
		//no assets folder
		File assets = new File(folder, "include_style_local_files");		
		assertFalse(assets.exists());

		
		
	}
	
	
	protected void assertEqualsContent(String expected, File actual) throws FileNotFoundException, IOException {		
		String contentExpected = FileUtil.readString(IncludeStyleFunctionTest.class.getResourceAsStream(expected));
		String contentActual = FileUtil.readString(new FileInputStream(actual));
		assertEquals(contentExpected, contentActual);
	}

}
