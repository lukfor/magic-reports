package lukfor.reports.examples;

import java.io.IOException;

import lukfor.reports.SimpleHtmlReport;

public class ExampleSimpleReport {

	static class MyBean {

		int a = 0;
		int b = 0;

		public MyBean(int a) {
			this.a = a;
			this.b = 2 * a;
		}

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

		MyBean[] beans = new MyBean[] { new MyBean(1), new MyBean(2), new MyBean(3) };

		SimpleHtmlReport report = new SimpleHtmlReport();
		report.table(beans, "a", "b");
		report.plot(beans, "a", "b");
		report.saveAndOpen("simple-example.html");

	}

}
