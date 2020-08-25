package lukfor.reports;

import java.io.File;
import java.io.FileOutputStream;

import lukfor.reports.util.FileUtil;

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

	public String addToAssets(String path, byte[] bytes) {
		File file = new File(folderFilename + "/" + path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try {
			FileOutputStream out = new FileOutputStream(file);
			out.write(bytes);
			out.close();
		}catch (Exception e) {
			//TODO:
		}

		// use name, to make it relative
		return folderName + "/" + path;
	}

}
