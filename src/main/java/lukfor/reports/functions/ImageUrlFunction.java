package lukfor.reports.functions;

import java.util.function.Function;

import lukfor.reports.HtmlReport;
import lukfor.reports.util.FileUtil;

public class ImageUrlFunction implements Function<String, String> {

	private HtmlReport report;

	public ImageUrlFunction(HtmlReport report) {
		this.report = report;
	}

	@Override
	public String apply(String url) {

		if (url.startsWith("https://") || url.startsWith("http://")) {

			return url;

		} else {

			if (report.isSelfContained()) {

				System.out.println("  Include image " + url + "...");

				try {

					String mimeType = "image";
					if (url.endsWith(".gif")) {
						mimeType = "image/gif";
					} else if (url.endsWith(".png")) {
						mimeType = "image/png";
					} else if (url.endsWith(".jpg") || url.endsWith(".jpeg")) {
						mimeType = "image/jpeg";
					} else if (url.endsWith(".svg")) {
						mimeType = "image/svg+xml";
					}

					byte[] bytes = null;
					
					if (url.startsWith("file://")) {
						bytes =  FileUtil.readBytesFromFile(url.replaceAll("file://", ""));;
					}else {
						bytes =report.getBytes(url);
					}
					String encodedContent = FileUtil.encodeBase64(mimeType, bytes);

					return encodedContent;

				} catch (Exception e) {
e.printStackTrace();
					throw new RuntimeException(e);

				}

			} else {

				System.out.println("  Copy image " + url + " into assets...");

				try {

					String assetsFilename = report.copyToAssets(url);
					return assetsFilename;

				} catch (Exception e) {

					throw new RuntimeException(e);

				}

			}
		}
	}

}
