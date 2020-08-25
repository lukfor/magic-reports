package lukfor.reports.functions.text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.TestCase;
import lukfor.reports.HtmlReport;
import lukfor.reports.util.FileUtil;

public class DecimalFunctionTest extends TestCase {

	public void testPercentage() throws IOException {

		File folder = new File("test-output");
		FileUtil.deleteFolder(folder);
		folder.mkdirs();

		File output = new File(folder, "decimal.html");

		HtmlReport report = new HtmlReport("/decimal");
		report.setSelfContained(false);
		report.set("integer", 1000000);
		report.set("long", 454444444L);
		report.set("double", 0.11111d);
		report.set("float", 0.7121f);
		report.set("string", "text");
		report.generate(output);

		assertTrue(output.exists());
		assertEqualsContent("/decimal/index.expected.html", output);

	}

	protected void assertEqualsContent(String expected, File actual) throws FileNotFoundException, IOException {
		String contentExpected = FileUtil.readString(DecimalFunctionTest.class.getResourceAsStream(expected));
		String contentActual = FileUtil.readString(new FileInputStream(actual));
		assertEquals(contentExpected, contentActual);
	}

}
