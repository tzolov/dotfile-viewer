package org.apache.crunch.dotfile;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.io.Files;

@RestController
public class DotfileController {

	@Value("${dotfiles.folder}")
	private File dotfilesRootFolder;

	public static class About {
		
		private final String rootFolder;
		private final int dotfilesCount;

		public String getRootFolder() {
			return rootFolder;
		}

		public int getDotfilesCount() {
			return dotfilesCount;
		}

		public About(String rootFolder, int dotfilesCount) {
			this.rootFolder = rootFolder;
			this.dotfilesCount = dotfilesCount;
		}
	}

	public static class FileNameClass {
		private String fileName;

		public FileNameClass(String fileName) {
			super();
			this.fileName = fileName;
		}

		public String getFileName() {
			return fileName;
		}
	}
	
	private static final class DotFilenameFilter implements FilenameFilter {

		private final String wildCard;

		public DotFilenameFilter(String wildCard) {
			this.wildCard = wildCard;
		}

		public DotFilenameFilter() {
			wildCard = "";
		}

		@Override
		public boolean accept(File dir, String name) {

			if (StringUtils.isEmpty(wildCard)) {
				return name.endsWith(".dot");
			}

			return name.contains(wildCard) && name.endsWith(".dot");
		}
	}

	@RequestMapping("/about")
	public @ResponseBody About about() throws IOException {
		int size = dotfilesRootFolder.list(new DotFilenameFilter()).length;
		return new About(dotfilesRootFolder.getAbsolutePath(), size);
	}

	@RequestMapping("/dotfile")
	public @ResponseBody FileNameClass[] dofileList2(
			@RequestParam(value = "wildcard", required = false) String wildcard)
			throws IOException {

		String[] list = dotfilesRootFolder
				.list(new DotFilenameFilter(wildcard));

		FileNameClass[] jsonArray = new FileNameClass[list.length];
		for (int i = 0; i < list.length; i++) {
			jsonArray[i] = new FileNameClass(list[i]);
		}

		return jsonArray;
	}

	@RequestMapping("/dot/{fileName}")
	public String dot(final @PathVariable("fileName") String fileName)
			throws IOException {

		String dotfilePath = String.format("%s/%s.dot",
				dotfilesRootFolder.getAbsoluteFile(), fileName);

		return Files.toString(new File(dotfilePath), Charset.forName("UTF-8"));
	}
}