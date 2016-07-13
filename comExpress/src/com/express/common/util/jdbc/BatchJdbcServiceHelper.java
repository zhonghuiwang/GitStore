package com.express.common.util.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;



public class BatchJdbcServiceHelper {
	private static final Logger logger = Logger.getLogger(BatchJdbcServiceHelper.class);

	private static BatchJdbcServiceHelper jdbcService = null;

	public static BatchJdbcServiceHelper getInstance() {
		if (null == jdbcService) {
			jdbcService = new BatchJdbcServiceHelper();
		}
		return jdbcService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.topcheer.jdbc.JdbcServiceManager#insert(java.lang.String,
	 * java.lang.String, java.util.List)
	 */
	public void insert(Connection conn, String tableName, String insertString,
			List parameters) throws Exception {
		saveOrUpdate(conn, new String("insert"), tableName, insertString,
				parameters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.topcheer.jdbc.JdbcServiceManager#update(java.lang.String,
	 * java.lang.String, java.util.List)
	 */
	public void update(Connection conn, String tableName, String updateString,
			List parameters) throws Exception {
		saveOrUpdate(conn, new String("update"), tableName, updateString,
				parameters);

	}

	public void delete(Connection conn, String tableName, String updateString,
			List parameters) throws Exception {
		saveOrUpdate(conn, new String("delete"), tableName, updateString,
				parameters);

	}

	public Connection getConnect() throws Exception {
		Connection conn = JdbcServiceHelper.getConnection();
		conn.setAutoCommit(false);
		return conn;
	}

	private void saveOrUpdate(Connection conn, String type, String tableName,
			String saveOrUpdateString, List parameters) throws Exception {

		String title = new String(type + " table " + tableName + " :");
		JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL, title, "Begin");
		JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL, title,
				saveOrUpdateString, parameters);
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(saveOrUpdateString);
			int i = 1;
			for (Iterator iterator = parameters.iterator(); iterator.hasNext();) {
				pStmt.setString(i++, (String) iterator.next());
			}
			pStmt.executeUpdate();

			JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL, title,
					"Success");

		} catch (Exception e) {
			JdbcServiceHelper
					.printLog(JdbcServiceHelper.LEVEL, title, "Failed");
			JdbcServiceHelper.printLog("error", title, e.getMessage());
			// conn.rollback();
			JdbcServiceHelper.closeStatement(pStmt);
			// JdbcServiceHelper.closeConnection(conn);
			throw e;
		} finally {
			JdbcServiceHelper.closeStatement(pStmt);
			JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL, title, "End");
		}

	}

	public void CommitAction(Connection conn) {
		try {

			conn.commit();

		} catch (SQLException e) {
			
			    System.out.println("Commit Connection 发生异常!"+e);

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				conn = null;
				System.out.println("Commit Connection 发生异常!"+e);
			}
		}
	}

	public void RollbackAction(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			logger.error("Rollback Connection 鍙戠敓寮傚父锟?", e);
		} finally {

			JdbcServiceHelper.closeConnection(conn);
		}
	}

	public List query(Connection conn, String tableName, String queryString,
			List parameters) {
		String title = new String("query table " + tableName + " :");
		JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL, title, "Begin");
		JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL, title, queryString,
				parameters);
		List results = new ArrayList();
		// Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {
			// conn = JdbcServiceHelper.getConnection();
			pStmt = conn.prepareStatement(queryString);
			int i = 1;
			for (Iterator iterator = parameters.iterator(); iterator.hasNext();) {
				pStmt.setString(i++, (String) iterator.next());
			}
			rs = pStmt.executeQuery();
			ResultSetMetaData rsMetaData = rs.getMetaData();
			i = rsMetaData.getColumnCount();
			while (rs.next()) {
				Map map = new HashMap();
				String columnName = null;
				for (int j = 1; j <= i; j++) {
					columnName = rsMetaData.getColumnName(j);
					map.put(columnName, rs.getString(columnName));
				}
				results.add(map);
			}
			JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL, title,
					"Success");
		} catch (Exception e) {
			JdbcServiceHelper
					.printLog(JdbcServiceHelper.LEVEL, title, "Failed");
			JdbcServiceHelper.printLog("error", title, e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcServiceHelper.closeResultSet(rs);
			JdbcServiceHelper.closeStatement(pStmt);
			// JdbcServiceHelper.closeConnection(conn);
		}
		JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL, title, "End");
		return results;
	}

	public static void main(String[] args) {
		JdbcServiceManager jsm = JdbcServiceManagerImpl.getInstance();
		List stepParaList = new ArrayList();
		stepParaList.add("152");
		try {
			jsm.delete("FD_ATTACHMENTINFO",
					"delete from FD_ATTACHMENTINFO where BPM_ATTACHMENTID=?",
					stepParaList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
