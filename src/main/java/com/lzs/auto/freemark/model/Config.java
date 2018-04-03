package com.lzs.auto.freemark.model;

import java.util.HashMap;
import java.util.Map;

public class Config {
	private DataSource dataSource = new DataSource();

	private Map<String, String> paramsMap = new HashMap<>();

	private String targetPath;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Map<String, String> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, String> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		if (targetPath != null && (!targetPath.endsWith("/") && !targetPath.endsWith("\\"))) {
			targetPath = targetPath+"/";
		}
		if (targetPath != null && !targetPath.endsWith("/")) {
			targetPath = targetPath + "\\";
		}
		this.targetPath = targetPath;
	}

}
