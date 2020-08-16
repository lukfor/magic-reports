package lukfor.magic.reports.examples;

import java.io.File;
import java.io.IOException;

import lukfor.magic.reports.HtmlReport;

public class ExampleReport {

	static class MyBean {

		int a = 2;
		int b = 5;

		public int getA() {
			return a;
		}

		public void setA(int a) {
			this.a = a;
		}

		public int getB() {
			return b;
		}

		public void setB(int b) {
			this.b = b;
		}

	}

	public static void main(String[] args) throws IOException {

		MyBean[] beans = new MyBean[] { new MyBean(), new MyBean(), new MyBean() };

		HtmlReport report = new HtmlReport("/example");
		report.setSelfContained(false);
		report.set("name", "Lukas");
		report.set("bean", beans);
		report.generate(new File("example.html"));
	}

}
