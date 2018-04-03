package com.lzs.auto.freemark;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lzs.auto.freemark.model.ColumnModel;
import com.lzs.auto.freemark.model.Config;
import com.lzs.auto.freemark.util.StringUtil;

public class MappingManager {

	private Connection connection = null;
	private Statement st = null;
	private ResultSet rs = null;
	private DatabaseMetaData dmd = null;
	private Config config = null;
	public MappingManager(Config config) {
		try {
			this.config = config;
			Class.forName(config.getDataSource().getDriver());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 链接数据库
	 * 
	 * @return
	 */
	private void connection() {
		try {
			connection = DriverManager.getConnection(config.getDataSource().getUrl(),
					config.getDataSource().getUsername(), config.getDataSource().getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭链接
	 * 
	 * @param rs
	 * @param st
	 * @param conn
	 */
	private void release(ResultSet rs, Statement st, Connection conn) {
		try {
			try {
				if (null != rs) {
					rs.close();
				}
			} catch (Exception e) {
				rs = null;
			}
			try {
				if (null != st) {
					st.close();
				}
			} catch (Exception e) {
				st = null;
			}
			try {
				if (null != conn) {
					conn.close();
				}
			} catch (Exception e) {
				conn = null;
			}
		} finally {
			rs = null;
			st = null;
			conn = null;
		}
	}

	/**
	 * 根据表名查询表中列注释
	 * 
	 * @param tableName
	 *            表名
	 * @param config
	 * @return List<String>
	 */
	public List<ColumnModel> mappingProps(String tableName, Config config) {
		List<ColumnModel> list = new ArrayList<ColumnModel>();
		try {
			connection();
			dmd = connection.getMetaData();
			rs = dmd.getColumns(null, dmd.getUserName(), tableName, null);
			ColumnModel model = null;
			while (rs.next()) {
				model = new ColumnModel();
				// 注释
				model.setRemark(rs.getString("REMARKS"));
				// 列名
				model.setColName(rs.getString("COLUMN_NAME"));
				// 类型
				model.setColType(StringUtil.colTypeToMybatisType(rs.getString("TYPE_NAME")));

				// java 属性名
				model.setJavaName(StringUtil.toJavaName(model.getColName(), false));
				// java 类型
				model.setJavaType(StringUtil.mybatisTypeToJavaType(model.getColType()));

				list.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, st, connection);
		return list;
	}

}
