package com.lzs.auto.freemark;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.lzs.auto.freemark.model.Config;
import com.lzs.auto.freemark.util.FileUtil;
import com.lzs.auto.freemark.util.FreemarkerUtil;
import com.lzs.auto.freemark.util.StringUtil;
import com.lzs.auto.freemark.util.XMLUtil;

import freemarker.template.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
	// 递归遍历
	private static List<File> getDirectoryFile(File file) {
	  File flist[] = file.listFiles();
	  if (flist == null || flist.length == 0) {
			return new ArrayList<>(0);
	  }
		List<File> list = new ArrayList<>();
	  for (File f : flist) {
	      if (f.isDirectory()) {
				// 文件夹忽略
	      } else {
				// 所有的模板文件
				list.add(f);
	      }
	  }
		return list;
	}
		 
    public static void main( String[] args )
    {
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat fmt = new SimpleDateFormat(format);

		String directory = App.class.getResource("/").getPath() + "auto-config.xml";
		// File directory = new File(rootPath);// 当前文件夹
		// 获取模板-默认在当前文件夹的子文件夹tpl下
		File tpl = new File(App.class.getResource("/").getPath() + "tpl/");// 当前文件夹
		// File tpl = new File("tpl/");// 当前文件夹
		List<File> tpls = getDirectoryFile(tpl);
		
		System.out.println("默认配置文件：" + directory);
		System.out.println("默认配置模板文件目录：" + tpl.getAbsolutePath());

		while (true) {
			// 创建目标路径
			System.out.println("请输入配置文件路径（回车使用当前目录下的auto-config.xml文件）：");
			Scanner sc = new Scanner(System.in);
			String line = sc.nextLine();
			if (line == null) {
				continue;
			}
			if (line.trim().equals("")) {
				line = directory;
			}
			// 初始化配置
			Config config = null;
			Configuration fc = null;
			try {
				config = XMLUtil.parseXml(line);
				// 根据模板文件和参数创建文件
				fc = FreemarkerUtil.getConfig(tpl);
			} catch (Exception e) {
				System.err.println("文件解析异常：" + e.getMessage());
				continue;
			}
			try {
				FileUtil.createDir(config.getTargetPath());
			} catch (Exception e) {
				System.err.println("创建目标路径异常：" + e.getMessage());
				continue;
			}

			MappingManager manage = new MappingManager(config);

			// 接收用户输入，输入表名以及一些参数
			System.out.println("请输入表名，多个表请使用个空格分隔");
			while (!"exit".equals((line = sc.nextLine()))) {
				String[] array = line.trim().split(" ");
				for (String a : array) {
					if (a.trim().equals("")) {
						continue;
					}
					System.out.println("正在处理表：" + a);

					// 模板文件中使用的参数
					Map<String, Object> map = new HashMap<>();

					// 获取表列相关属性
					map.put("props", manage.mappingProps(a, config));

					// 配置文件中的公共参数
					Map<String, String> params = config.getParamsMap();
					map.putAll(params);

					// 其他系统属性
					// 表名转换为java类名
					String javaClassName = StringUtil.toJavaName(a, true);
					map.put("className", javaClassName);
					map.put("tableName", a);
					map.put("createTime", fmt.format(new Date()));

					for (File tplFileName : tpls) {
						try {
							String name = tplFileName.getName();
							String jname = name.substring(0, name.lastIndexOf('.'));
							FreemarkerUtil.generateFile(fc, name, config.getTargetPath() + javaClassName + jname,
									map);
						} catch (IOException e) {
							System.err.println("模板文件" + tplFileName + "获取异常：" + e.getMessage());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println("处理完成");
				}
				System.out.println("请输入表名，多个表请使用个空格分隔");
			}
			break;
		}

    }
}
