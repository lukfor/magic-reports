package lukfor.magic.reports.functions;

import java.util.function.Function;

import lukfor.magic.reports.HtmlReport;
import lukfor.magic.reports.util.FileUtil;

public class IncludeStyleFunction implements Function<String, String> {

	private HtmlReport report;

	public IncludeStyleFunction(HtmlReport report) {
		this.report = report;
	}

	@Override
	public String apply(final String url) {
		
		String href= "";
		
		if (url.startsWith("https://") || url.startsWith("http://")) {

			href = url;

		} else {

			if (report.isSelfContained()) {

				System.out.println("  Include style " + url + "...");

				try {
					String content = report.renderTemplate(url);
					href = FileUtil.encodeBase64("text/css", content);					
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

			} else {

				System.out.println("  Copy style " + url + " into assets...");

				try {
					href = report.copyToAssets(url);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

			}
		}
		
		StringBuilder html = new StringBuilder();
		html.append("<!-- " + url + " -->\n");
		html.append("<link rel=\"stylesheet\" href=\"" + href + "\">");
		return html.toString();
	}

}
