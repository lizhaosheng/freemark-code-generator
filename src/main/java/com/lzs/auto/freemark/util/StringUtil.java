package com.lzs.auto.freemark.util;

public class StringUtil {
	/**
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String capitalize(String str) {
		if (null == str) {
			return null;
		} else if ("".equals(str)) {
			return str;
		}
		return Character.toTitleCase(str.charAt(0)) + str.substring(1);
	}

	/**
	 * 将数据库命名风格的字符串转换为java风格的变量名，例如将表中列名去下划线且下划线后首字母大写其他字母小写
	 *
	 * @param dbName
	 *            - 数据库风格的变量名
	 * @param isFirstUpperCase
	 *            - 是否首字母大写
	 * @return
	 */
	public static String toJavaName(String dbName, boolean isFirstUpperCase) {
		if (dbName == null) {
			return null;
		}
		StringBuilder fieldName = new StringBuilder();
		boolean toUpper = false;
		for (int i = 0; i < dbName.length(); i++) {
			char ch = dbName.charAt(i);
			if (ch == '_') {
				toUpper = true;
			} else if (toUpper == true) {
				fieldName.append(Character.toUpperCase(ch));
				toUpper = false;
			} else {
				fieldName.append(Character.toLowerCase(ch));
			}
		}

		if (fieldName.length() == 0) {
			return "";
		}
		if (isFirstUpperCase) {
			fieldName.setCharAt(0, Character.toTitleCase(fieldName.charAt(0)));
		}
		return fieldName.toString();
	}

	/**
	 * 将数据库类型转换成java类型
	 * 
	 * @param columnType
	 *            数据库类型
	 * @return java类型
	 */
	public static String mybatisTypeToJavaType(String columnType) {
		String type = "";
		if (columnType == null || columnType.trim().equals("")) {
			return null;
		}
		if (columnType.equals("CHAR")) {
			type = "String";
		} else if (columnType.equals("VARCHAR")) {
			type = "String";
		} else if (columnType.equals("LONGVARCHAR")) {
			type = "String";
		}

		else if (columnType.equals("DATE")) {
			type = "Date";
		} else if (columnType.equals("TIME")) {
			type = "Date";
		} else if (columnType.equals("TIMESTAMP")) {
			type = "Date";
		}

		else if (columnType.equals("BIT") || columnType.equals("BOOLEAN")) {
			type = "Boolean";
		}

		else if (columnType.equals("TINYINT")) {
			type = "Integer";
		} else if (columnType.equals("SMALLINT")) {
			type = "Integer";
		} else if (columnType.equals("INTEGER")) {
			type = "Integer";
		} else if (columnType.equals("BIGINT")) {
			type = "Long";
		} else if (columnType.equals("REAL")) {
			type = "Float";
		} else if (columnType.equals("FLOAT") || columnType.equals("DOUBLE")) {
			type = "Double";
		} else if (columnType.equals("DECIMAL") || columnType.equals("NUMERIC")) {
			type = "BigDecimal";
		}

		else if (columnType.equals("BLOB")) {
			type = "byte[]";
		} else if (columnType.equals("TINYBLOB")) {
			type = "byte[]";
		} else if (columnType.equals("MEDIUMBLOB")) {
			type = "byte[]";
		} else {
			System.out.println("Error: columnType[" + columnType + "] not found!");
			type = "UNKNOW";
		}
		return type;
	}

	/**
	 * 将数据库类型转换成mybatis配置文件类型
	 * 
	 * @param sqlTypeName
	 *            数据库类型
	 * @return mybatis配置文件类型
	 */
	public static String colTypeToMybatisType(String sqlTypeName) {
		String type = "";
		if (sqlTypeName == null || sqlTypeName.trim().equals("")) {
			return null;
		}

		if (sqlTypeName.equals("VARCHAR")) {
			type = "VARCHAR";
		} else if (sqlTypeName.equals("TEXT")) {
			type = "LONGVARCHAR";
		} else if (sqlTypeName.equals("CHAR")) {
			type = "CHAR";
		}

		else if (sqlTypeName.equals("DATETIME") || sqlTypeName.equals("TIMESTAMP")) {
			type = "TIMESTAMP";
		} else if (sqlTypeName.equals("DATE")) {
			type = "DATE";
		} else if (sqlTypeName.equals("TIME")) {
			type = "TIME";
		}

		else if (sqlTypeName.equals("TINYBLOB") || sqlTypeName.equals("BLOB") || sqlTypeName.equals("MEDIUMBLOB")) {
			type = "BLOB";
		}

		else if (sqlTypeName.equals("TINYINT") || sqlTypeName.equals("TINYINT UNSIGNED")) {
			type = "TINYINT";
		} else if (sqlTypeName.equals("SMALLINT")) {
			type = "SMALLINT";
		} else if (sqlTypeName.equals("INT") || sqlTypeName.equals("INT UNSIGNED")
				|| sqlTypeName.equals("MEDIUMINT") || sqlTypeName.equals("MEDIUMINT UNSIGNED")
				|| sqlTypeName.equals("INTEGER") || sqlTypeName.equals("INTEGER UNSIGNED")) {
			type = "INTEGER";
		} else if (sqlTypeName.equals("BIGINT")) {
			type = "BIGINT";
		} else if (sqlTypeName.equals("FLOAT") || sqlTypeName.equals("FLOAT UNSIGNED")) {
			type = "FLOAT";
		} else if (sqlTypeName.equals("REAL") || sqlTypeName.equals("REAL UNSIGNED")) {
			type = "REAL";
		} else if (sqlTypeName.equals("DOUBLE") || sqlTypeName.equals("DOUBLE UNSIGNED")) {
			type = "DOUBLE";
		} else if (sqlTypeName.equals("DECIMAL") || sqlTypeName.equals("DECIMAL UNSIGNED")) {
			type = "DECIMAL";
		} else if (sqlTypeName.equals("NUMERIC") || sqlTypeName.equals("NUMERIC UNSIGNED")) {
			type = "NUMERIC";
		} else if (sqlTypeName.equals("BIT")) {
			type = "BIT";
		}
		else {
			System.out.println("Error: sqlTypeName[" + sqlTypeName + "] not found!");
			type = "UNKNOW";
		}
		return type;
	}

	public static String getFileName(String tableName, String flag) {
		String retName;
		switch (flag) {
		case "po":
			retName = tableName + "_po";
			break;
		case "vo":
			retName = tableName + "_vo";
			break;
		default:
			retName = null;
		}
		return retName;
	}
}
