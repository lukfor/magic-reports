package lukfor.magic.reports;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import lukfor.magic.reports.util.FileUtil;

public class HtmlReportAssets {

	private String folderFilename;

	private String folderName;

	public HtmlReportAssets(File outputFile) {
		this.folderFilename = outputFile.getPath().replaceAll(".html", "_files");
		this.folderName = outputFile.getName().replaceAll(".html", "_files");

		File folderFile = new File(folderFilename);
		if (folderFile.exists()) {
			System.out.println("Clean up assets folder.");
			FileUtil.deleteFolder(folderFile);
		}
	}

	public String addToAssets(String path, byte[] bytes) throws IOException {
		File file = new File(folderFilename + "/" + path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		FileOutputStream out = new FileOutputStream(file);
		out.write(bytes);
		out.close();

		// use name, to make it relative
		return folderName + "/" + path;
	}

}
