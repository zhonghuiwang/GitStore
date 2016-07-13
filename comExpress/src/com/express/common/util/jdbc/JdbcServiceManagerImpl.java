/**
 * 
 */
package com.express.common.util.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @author pengjiong
 * 
 */
public class JdbcServiceManagerImpl implements JdbcServiceManager {

	private static JdbcServiceManager jdbcService= null;
	
	public static JdbcServiceManager getInstance(){
		if(null == jdbcService){
		jdbcService = new JdbcServiceManagerImpl();
		}
		return jdbcService;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.topcheer.jdbc.JdbcServiceManager#query(java.lang.String,
	 *      java.lang.String, java.util.Map)
	 */
	public List query(String tableName, String queryString, List parameters) {
		String title = new String("query table "+tableName+" :");
		//JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL,title,"Begin");
		//JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL,title, queryString,parameters);
		List results = new ArrayList();
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		//System.out.println("queryString:"+queryString);
		try {
			conn = JdbcServiceHelper.getConnection();
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
			//JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL,title,"Success");
		} catch (Exception e) {
			//JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL,title,"Failed");
			JdbcServiceHelper.printLog("error",title,e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcServiceHelper.closeResultSet(rs);
			JdbcServiceHelper.closeStatement(pStmt);
			JdbcServiceHelper.closeConnection(conn);
		}
		//JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL,title,"End");
		return results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.topcheer.jdbc.JdbcServiceManager#insert(java.lang.String,
	 *      java.lang.String, java.util.List)
	 */
	public boolean insert(String tableName, String insertString, List parameters)
			throws Exception {
		 return saveOrUpdate(new String("insert"), tableName, insertString, parameters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.topcheer.jdbc.JdbcServiceManager#update(java.lang.String,
	 *      java.lang.String, java.util.List)
	 */
	public void update(String tableName, String updateString, List parameters)
			throws Exception {
		saveOrUpdate(new String("update"), tableName, updateString, parameters);

	}
	
	public void delete(String tableName, String updateString, List parameters)
	throws Exception {
		saveOrUpdate(new String("delete"), tableName, updateString, parameters);

}

	private boolean saveOrUpdate(String type, String tableName,
			String saveOrUpdateString, List parameters) throws Exception {
		
		String title = new String(type +" table "+tableName+" :");
    	JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL,title,"Begin");
    	JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL,title, saveOrUpdateString,parameters);
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcServiceHelper.getConnection();
			pStmt = conn.prepareStatement(saveOrUpdateString);
			int i = 1;
			for (Iterator iterator = parameters.iterator(); iterator.hasNext();) {
				pStmt.setString(i++, (String) iterator.next());
			}
			int result  = pStmt.executeUpdate();
			if(result>0){
				return true;
			}else{
				return false;
			}

			//JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL,title,"Success");
		} catch (Exception e) {
			JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL,title,"Failed");
			JdbcServiceHelper.printLog("error",title,e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcServiceHelper.closeResultSet(rs);
			JdbcServiceHelper.closeStatement(pStmt);
			JdbcServiceHelper.closeConnection(conn);
		}
		return false;
		
		//JdbcServiceHelper.printLog(JdbcServiceHelper.LEVEL,title,"End");

	}
	
	public static void main(String [] args){
		JdbcServiceManager jsm = JdbcServiceManagerImpl.getInstance();
		List stepParaList = new ArrayList();
		stepParaList.add("3");
		try {
			jsm.delete(
					"goods",
					"delete from goods where userid=?",
					stepParaList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



