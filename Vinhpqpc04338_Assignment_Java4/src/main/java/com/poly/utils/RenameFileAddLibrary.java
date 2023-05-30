package com.poly.utils;

import org.apache.commons.io.FilenameUtils;

public class RenameFileAddLibrary {
	public static String renameFile(String fileName) {
		return FilenameUtils.getBaseName(fileName) + ".png";
	}
}
