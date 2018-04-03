package com.lzs.auto.freemark.model;

import com.lzs.auto.freemark.util.StringUtil;

/**
 * 数据库表列转换为java bean的数据模型
 *
 * @author lizhaosheng 2017年7月21日.
 */
public class ColumnModel {

	/**
	 * 列对应到java bean中的属性名，采用小驼峰命名规则，去掉列名中的下划线并将下划线后的第一个字母设置为大写
	 */
	private String javaName;
	/**
	 * 列对应到java bean中的属性类型，详见{@link StringUtil.dbTypeToJavaType}
	 */
	private String javaType;
	/**
	 * 数据库表的列名
	 */
	private String colName;
	/**
	 * 数据库中的列类型（实际上应该是数据库类型映射到mybatis配置文件中的类型）
	 */
	private String colType;
	/**
	 * 数据库中列的注释
	 */
	private String remark;

	public String getJavaName() {
		return javaName;
	}

	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColType() {
		return colType;
	}

	public void setColType(String colType) {
		this.colType = colType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
