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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DotfileService {

	private static final String DOT = ".dot";

	// @Value("${dotfiles.pattern}")
	// private Resource[] resources;

	private String resourcesPattern;

	@Autowired
	public DotfileService(@Value("${dotfiles.pattern:EMPTY}") String resourcesPattern,
			@Value("${dotfiles.folder:EMPTY}") String dotfilesRootFolder) {

		if (!"EMPTY".equals(dotfilesRootFolder)) {			
			// Fall back to the deprecated property: dotfiles.folder		
			this.resourcesPattern = String.format("file:/%s/**", dotfilesRootFolder);
		} else {
			this.resourcesPattern = resourcesPattern;
		}
	}

	public Resource[] getResources() throws IOException {

		return new PathMatchingResourcePatternResolver().getResources(resourcesPattern);
	}

	private boolean isEligableFilename(String fileName, String wildCard) {

		if (StringUtils.isEmpty(wildCard)) {
			return fileName.endsWith(DOT);
		}

		return fileName.contains(wildCard) && fileName.endsWith(DOT);
	}

	public String[] findDotfiles(String wildCard) throws IOException {

		List<String> result = new ArrayList<String>();

		for (Resource res : getResources()) {
			if (isEligableFilename(res.getFilename(), wildCard)) {
				result.add(res.getFilename());
			}
		}

		return result.toArray(new String[result.size()]);
	}

	public String getDotfileContent(String fileName) throws IOException {

		for (Resource res : getResources()) {
			if (res.getFilename().contains(fileName)) {
				return IOUtils.toString(res.getInputStream());
				// return Files.toString(res.getFile(),
				// Charset.forName("UTF-8"));
			}
		}

		return "";
	}

	public String getResourcesPattern() {
		return resourcesPattern;
	}
}
