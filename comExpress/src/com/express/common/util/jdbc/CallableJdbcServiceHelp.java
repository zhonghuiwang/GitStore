/**
 * 
 */
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

//import oracle.jdbc.driver.OracleCallableStatement;

/**
 * @author SILENCE
 *
 */
public class CallableJdbcServiceHelp {	
	
		private static CallableJdbcServiceHelp jdbcService = null;
		
		public static CallableJdbcServiceHelp getInstance(){
			if(null==jdbcService){
				jdbcService = new CallableJdbcServiceHelp();
			}
			return jdbcService;
		}
		
	/*
		 * (non-Javadoc)
		 * 
		 * @see com.topcheer.jdbc.JdbcServiceManager#insert(java.lang.String,
		 *      java.lang.String, java.util.List)
		 */
		public void insert(Connection conn ,String tableName, String insertString, List parameters)
				throws Exception {
			saveOrUpdate( conn ,new String("insert"), tableName, insertString, parameters);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.topcheer.jdbc.JdbcServiceManager#update(java.lang.String,
		 *      java.lang.String, java.util.List)
		 */
		public void update(Connection conn ,String tableName, String updateString, List parameters)
				throws Exception {
			saveOrUpdate( conn ,new String("update"), tableName, updateString, parameters);

		}
		
		public void delete(Connection conn ,String tableName, String updateString, List parameters)
		throws Exception {
			saveOrUpdate( conn ,new String("delete"), tableName, updateString, parameters);

	}
		public Connection getConnect() throws Exception
		{
			 Connection conn = JdbcServiceHelper.getConnection();
			 conn.setAutoCommit(false);
			 return conn;
		}
		private void saveOrUpdate(Connection conn ,String type, String tableName,
			String saveOrUpdateString, List parameters) throws Exception {
			
	        String title = new String(type +" table "+tableName+" :");
			JdbcServiceHelper.printLog("info",title,"Begin");
			JdbcServiceHelper.printLog("info",title, saveOrUpdateString,parameters);
			PreparedStatement pStmt = null;
	   	try {
				pStmt = conn.prepareStatement(saveOrUpdateString);
				int i = 1;
				for (Iterator iterator = parameters.iterator(); iterator.hasNext();) {
					pStmt.setString(i++, (String) iterator.next());
				}           
				pStmt.executeUpdate();

				JdbcServiceHelper.printLog("info",title,"Success");
	    
			} catch (Exception e) {
				JdbcServiceHelper.printLog("info",title,"Failed");
				JdbcServiceHelper.printLog("error",title,e.getMessage());
				//conn.rollback();
				JdbcServiceHelper.closeStatement(pStmt);
				//JdbcServiceHelper.closeConnection(conn);
	    		throw e;
			} 
			finally {
				JdbcServiceHelper.closeStatement(pStmt);                      
				JdbcServiceHelper.printLog("info",title,"End");
			}
			
			

		}
		
		
		
		public void CommitAction(Connection conn) 
		{
			try {
				
				conn.commit();
			
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {	
				JdbcServiceHelper.closeConnection(conn);
			}
		}
		public void RollbackAction(Connection conn)
		{
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				JdbcServiceHelper.closeConnection(conn);
			}
		}
		
		public static void main(String [] args){
			JdbcServiceManager jsm = JdbcServiceManagerImpl.getInstance();
			List stepParaList = new ArrayList();
			stepParaList.add("152");
			try {
				jsm.delete(
						"FD_ATTACHMENTINFO",
						"delete from FD_ATTACHMENTINFO where BPM_ATTACHMENTID=?",
						stepParaList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
