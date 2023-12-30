package lukfor.reports.commands;

import junit.framework.TestCase;
import lukfor.reports.App;

import java.io.IOException;

public class RenderCommandTest extends TestCase {

	public void testTemplateExternalFile() throws Exception {
		App app = new App();
		int exitCode = app.run("render","-i","test-data/report01/report01.report","-o","report01.html");
		assertEquals(0, exitCode);
	}

	public void testTemplate() throws Exception {
		App app = new App();
		int exitCode = app.run("render","-i","test-data/report02/report02.report","-o","report02.html");
		assertEquals(0, exitCode);
	}

	public void testExample() throws Exception {
		App app = new App();
		int exitCode = app.run("render","-i","examples/iris.report","-o","examples/iris.html");
		assertEquals(0, exitCode);
	}

}
