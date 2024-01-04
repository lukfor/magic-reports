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


	public void testWithParam() throws Exception {
		App app = new App();
		int exitCode = app.run("render","-i","test-data/report03/report03.report","-o","report03.html","--name","World");
		assertEquals(0, exitCode);
	}


	public void testWithImport() throws Exception {
		App app = new App();
		int exitCode = app.run("render","-i","test-data/report04/report04.report","-o","report03.html");
		assertEquals(0, exitCode);
	}

	public void testMultiPage() throws Exception {
		App app = new App();
		int exitCode = app.run("render","-i","test-data/report05/report05.report","--output","results");
		assertEquals(0, exitCode);
	}

	public void testExampleIris() throws Exception {
		App app = new App();
		int exitCode = app.run("render","-i","examples/iris.report","-o","docs/examples/iris.html");
		assertEquals(0, exitCode);
	}

	public void testExampleWhisky() throws Exception {
		App app = new App();
		int exitCode = app.run("render","-i","examples/whisky.report","-o","docs/examples/whisky.html");
		assertEquals(0, exitCode);
	}

	public void testExampleHelloWorld() throws Exception {
		App app = new App();
		int exitCode = app.run("render","-i","examples/hello-world.report");
		assertEquals(0, exitCode);
	}

}
