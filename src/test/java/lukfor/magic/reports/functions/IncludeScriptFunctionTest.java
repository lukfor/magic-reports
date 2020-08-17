package lukfor.magic.reports.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.TestCase;
import lukfor.magic.reports.HtmlReport;
import lukfor.magic.reports.util.FileUtil;

public class IncludeScriptFunctionTest extends TestCase {

	public void testLink() throws IOException {

		File folder = new File("test-output");
		FileUtil.deleteFolder(folder);
		folder.mkdirs();

		File output = new File(folder, "include_script_link.html");

		HtmlReport report = new HtmlReport("/include_script/link");
		report.setSelfContained(false);
		report.generate(output);

		assertTrue(output.exists());
		assertEqualsContent("/include_script/link/index.expected.html", output);

	}

	public void testLinkSelfContained() throws IOException {

		File folder = new File("test-output");
		FileUtil.deleteFolder(folder);
		folder.mkdirs();

		File output = new File(folder, "include_script_link.html");

		HtmlReport report = new HtmlReport("/include_script/link");
		report.setSelfContained(true);
		report.generate(output);

		assertTrue(output.exists());
		assertEqualsContent("/include_script/link/index.expected.html", output);

	}

	public void testLocal() throws IOException {

		File folder = new File("test-output");
		FileUtil.deleteFolder(folder);
		folder.mkdirs();

		File output = new File(folder, "include_script_local.html");

		HtmlReport report = new HtmlReport("/include_script/local");
		report.setSelfContained(false);
		report.set("text", "Hello World");
		report.generate(output);

		assertTrue(output.exists());
		assertEqualsContent("/include_script/local/index.expected.html", output);

		File assets = new File(folder, "include_script_local_files");
		assertTrue(assets.exists());

		File script = new File(assets, "js/index.js");
		assertTrue(script.exists());
		assertEqualsContent("/include_script/local/js/index.expected.js", script);

	}

	public void testLocalSelfContained() throws IOException {

		File folder = new File("test-output");
		FileUtil.deleteFolder(folder);
		folder.mkdirs();

		File output = new File(folder, "include_script_local.html");

		HtmlReport report = new HtmlReport("/include_script/local");
		report.setSelfContained(true);
		report.set("text", "Hello World");
		report.generate(output);

		assertTrue(output.exists());
		assertEqualsContent("/include_script/local/index.expected.self_contained.html", output);

		// no assets folder
		File assets = new File(folder, "include_script_local_files");
		assertFalse(assets.exists());

	}

	protected void assertEqualsContent(String expected, File actual) throws FileNotFoundException, IOException {
		String contentExpected = FileUtil.readString(IncludeScriptFunctionTest.class.getResourceAsStream(expected));
		String contentActual = FileUtil.readString(new FileInputStream(actual));
		assertEquals(contentExpected, contentActual);
	}

}
