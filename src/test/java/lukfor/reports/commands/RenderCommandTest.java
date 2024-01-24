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

	public void testNavbar() throws Exception {
		App app = new App();
		int exitCode = app.run("render","-i","test-data/report06/report06.report");
		assertEquals(0, exitCode);
	}

	public void testExampleIris() throws Exception {
		App app = new App();
		int exitCode = app.run("render","-i","examples/iris.report","-o","docs/examples/iris.html");
		assertEquals(0, exitCode);
	}

	public void testExampleWhisky() throws Exception {
		App app = new App();
		int exitCode = app.run("serve","-i","examples/whisky.report","-o","docs/examples/whisky.html");
		assertEquals(0, exitCode);
	}

	public void testExampleVcfStatistics() throws Exception {
		App app = new App();
		int exitCode = app.run("render","-i","examples/vcf-statistics.report","-o","docs/examples/vcf-statistics.html", "--vcf","/Users/lukfor/Data/projects/humangen/breast-pgs/output/humangen-reference/vcf-single/humangen-reference.merged.vcf.gz");
		assertEquals(0, exitCode);
	}

	public void testExampleBim() throws Exception {
		App app = new App();
		int exitCode = app.run("render","-i","examples/microarray.report","-o","examples/microarray.html", "--bim","/Users/lukfor/Genotypen/GenMed2024_pm_raw.bim","--strand","/Users/lukfor/Data/projects/gen-med-2024/data/GSAMD-24v3-0-EA_20034606_A1.b37.strand");
		//int exitCode = app.run("render","-i","examples/microarray.report","-o","examples/microarray.html", "--bim","/Users/lukfor/Data/projects/gen-med-2023/data/GenMed2023_pm_raw.bim","--strand","/Users/lukfor/Data/projects/gen-med-2024/data/GSAMD-24v3-0-EA_20034606_A1.b37.strand");
		//int exitCode = app.run("render","-i","examples/microarray.report","-o","examples/microarray.html", "--bim","/Users/lukfor/humangen_old.bim","--strand","/Users/lukfor/Data/projects/gen-med-2024/data/GSAMD-24v3-0-EA_20034606_A1.b37.strand");
		assertEquals(0, exitCode);

	}


	public void testExampleHelloWorld() throws Exception {
		App app = new App();
		int exitCode = app.run("render","-i","examples/hello-world.report");
		assertEquals(0, exitCode);
	}

}
