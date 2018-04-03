package com.lzs.auto.freemark.util;

import java.io.File;

public class FileUtil {
	public static void createDir(String path) {
		if (null != path && !"".equals(path)) {
			File file = new File(path);
			file.mkdirs();
		}
	}

}
