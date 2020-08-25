package lukfor.reports.functions;

import java.util.function.Function;

import lukfor.reports.HtmlReport;

public class ImageFunction implements Function<String, String> {

	private ImageUrlFunction imageUrl;

	public ImageFunction(HtmlReport report) {
		this.imageUrl = new ImageUrlFunction(report);
	}

	@Override
	public String apply(String url) {

		return "<img src=\"" + imageUrl.apply(url) + "\">";

	}

}
