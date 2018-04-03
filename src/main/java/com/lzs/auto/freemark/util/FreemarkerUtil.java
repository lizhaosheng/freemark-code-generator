package com.lzs.auto.freemark.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerUtil {

	public static Configuration getConfig(File tplDir) throws IOException {
		Configuration config = new Configuration();
		config.setDefaultEncoding("UTF-8");
		config.setTemplateLoader(new ClassTemplateLoader(FreemarkerUtil.class, "/"));
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		config.setDirectoryForTemplateLoading(tplDir); // 文件夹
		return config;
	}

	public static Configuration getConfig(String classPath) throws IOException {
		Configuration config = new Configuration();
		config.setDefaultEncoding("UTF-8");
		config.setTemplateLoader(new ClassTemplateLoader(FreemarkerUtil.class, "/"));
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		// 基于类路径
		config.setClassForTemplateLoading(FreemarkerUtil.class, classPath);
		return config;
	}

	public static void generateFile(Configuration config, String tpl, String fileName, Map<String, Object> map)
			throws Exception {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
			Template template = config.getTemplate(tpl);
			template.process(map, out);
			out.close();
		} catch (Exception e) {

			if (out != null) {
				try {
					out.close();
				} catch (IOException e1) {

				}
			}

			throw e;
		}
	}

}
