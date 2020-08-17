package lukfor.magic.reports.functions;

import java.util.function.Function;

import lukfor.magic.reports.HtmlReport;
import lukfor.magic.reports.util.FileUtil;

public class IncludeScriptFunction implements Function<String, String> {

	private HtmlReport report;

	public IncludeScriptFunction(HtmlReport report) {
		this.report = report;
	}

	@Override
	public String apply(final String url) {

		String source = "";

		if (url.startsWith("https://") || url.startsWith("http://")) {

			source = url;

		} else {

			if (report.isSelfContained()) {

				System.out.println("  Include javascript " + url + "...");

				String content = report.renderTemplate(url);
				source = FileUtil.encodeBase64("text/javascript", content);

			} else {

				System.out.println("  Copy javascript " + url + " into assets...");

				try {
					source = report.renderTemplateAndCopyToAssets(url);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

			}

		}

		StringBuilder html = new StringBuilder();
		html.append("<!-- " + url + " -->\n");
		html.append("<script src=\"" + source + "\"></script>");
		return html.toString();

	}

}
