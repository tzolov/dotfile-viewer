/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.crunch.dotfile;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DotfileController {

	private DotfileService dotfileService;

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

	@Autowired
	public DotfileController(DotfileService dotfileService) {
		this.dotfileService = dotfileService;
	}

	@RequestMapping("/about")
	public @ResponseBody About about() throws IOException {
		int size = dotfileService.getResources().length;
		return new About(dotfileService.getResourcesPattern(), size);
	}

	@RequestMapping("/dotfile")
	public @ResponseBody FileNameClass[] dofileList2(
			@RequestParam(value = "wildcard", required = false) String wildcard)
			throws IOException {

		String[] list = dotfileService.findDotfiles(wildcard);

		FileNameClass[] jsonArray = new FileNameClass[list.length];
		for (int i = 0; i < list.length; i++) {
			jsonArray[i] = new FileNameClass(list[i]);
		}

		return jsonArray;
	}

	@RequestMapping("/dot/{fileName}")
	public String dot(final @PathVariable("fileName") String fileName)
			throws IOException {

		return dotfileService.getDotfileContent(fileName);
	}
}