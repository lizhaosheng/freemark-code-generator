package com.lzs.auto.freemark.util;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.lzs.auto.freemark.model.Config;

public class XMLUtil {


	public static Config parseXml(String fileName) throws DocumentException {

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(fileName));
		Element root = document.getRootElement();
		List<Element> elements = root.elements();
		Config config = new Config();
		for (Element element : elements) {
			if ("targetPath".equals(element.getName())) {
				config.setTargetPath(element.getTextTrim());
			}
			else if ("params".equals(element.getName())) {
				List<Element> paramElements = element.elements();
				for (Element param : paramElements) {
					config.getParamsMap().put(param.getName(), param.getTextTrim());
				}
			} else if ("dataSource".equals(element.getName())) {
				List<Element> ele = element.elements();
				for (Element e : ele) {
					if ("driver".equals(e.getName())) {
						config.getDataSource().setDriver(e.attributeValue("value"));
					} else if ("url".equals(e.getName())) {
						config.getDataSource().setUrl(e.attributeValue("value"));
					} else if ("username".equals(e.getName())) {
						config.getDataSource().setUsername(e.attributeValue("value"));
					} else if ("password".equals(e.getName())) {
						config.getDataSource().setPassword(e.attributeValue("value"));
					}
				}
			} else {
				System.err.println("ERROR: UNKNOW XML node[" + element.getName() + "]");
			}
			// } else if ("tables".equals(element.getName())) {
			// List<Element> tables = element.elements();
			// for (Element table : tables) {
			// if ("table".equals(table.getName())) {
			// if
			// ("yes".equalsIgnoreCase(table.attributeValue("value"))) {
			// List<String> tableNames = UtilDB.getTableNames();
			// for (String tableName : tableNames) {
			// Tables t = new Tables();
			// t.setTableName(tableName);
			// tableList.add(t);
			// }
			// break;
			// } else if
			// ("no".equalsIgnoreCase(table.attributeValue("value"))) {
			// continue;
			// }
			// Tables t = new Tables();
			// t.setTableName(table.attributeValue("value"));
			// tableList.add(t);
			// }
			// }
		}
		return config;
	}

}
