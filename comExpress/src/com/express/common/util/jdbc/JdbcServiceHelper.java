package com.express.common.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * @author pengjiong
 * 
 */
public class JdbcServiceHelper {
	

	
	public final static String LEVEL="info";
	private static DataSource ds = null;
	static {
		try {
			ds = JdbcServiceHelper.getMysqlDataSource();
		} catch (Exception e) {
			//LogFactory.sqlError("��ȡ DataSource �����쳣��", e);
			e.printStackTrace();
		}
	}
	


	public static void printLog(String type, String title, String sqlString,
			List sqlParameters) {
		try{
			String regex = sqlString;
			System.out.println("regex:"+regex);
			for (Iterator iterator = sqlParameters.iterator();iterator.hasNext();) {
				String name = (String) iterator.next();
				regex = regex.replaceFirst("\\?","'" + name +"'");
			}
			printLog(type, title, regex);
		}catch(Exception e){
			System.out.println("printLog error");
			e.printStackTrace();
		}
		

	}

	public static void printLog(String type, String title, String message) {
		//System.out.println("printLog:"+type+"title:"+title+"message:"+message);
		if ("info".equalsIgnoreCase(type)) {
			//LogFactory.sqlInfo(title+" "+message);
		} else if ("error".equalsIgnoreCase(type)) {
			//LogFactory.sqlError(title+" "+message);
		} else if ("debug".equalsIgnoreCase(type)) {
			//LogFactory.sqlDebug(title+" "+message);
		}
		System.out.println(title + message);
	}

	/**
	 * �ر����.
	 * 
	 * @param stmt
	 */
	public static void closeStatement(Statement stmt) {
		try {
			if (null != stmt) {
				stmt.close();
			}
		} catch (SQLException e) {
			//LogFactory.sqlError("Closing Statement �����쳣��", e);
			e.printStackTrace();
		}
	}

	/**
	 * �رսṹ��.
	 * 
	 * @param rs
	 */
	public static void closeResultSet(ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				//LogFactory.sqlError("Closing ResultSet �����쳣��", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * �ر�����.
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		try {
			if (null != conn) {
				conn.close();
			}
		} catch (SQLException e) {
			//LogFactory.sqlError("Closing Connection �����쳣��", e);
			e.printStackTrace();
		}
	}

	public static DataSource getDataSource() throws Exception {
		if (null != ds) {
			return ds;
		} else {
			// log.error("getDataSource", "��ȡ���ݿ�����ӳ�ʧ��");
			throw new Exception("��ȡ���ݿ�����ӳ�ʧ��");
		}
	}

	public static void setDataSource(DataSource dataSource) throws Exception {
		if (null != dataSource) {
			ds = dataSource;
		} else {
			// log.error("getDataSource", "��ʼ�������ݵ����ӳ�ʧ��");
			throw new Exception("��ʼ�������ݵ����ӳ�ʧ��");
		}

	}
	
	private static DataSource getMysqlDataSource() throws Exception {
		Properties props;
		String url;
		String user;
		String password;
		String driver;
		
		if (null != ds) {
			System.out.println("jdbc---->ds:"+ds);
			return ds;
		}
       //1.��ȡproperties�ļ�
		props = new Properties();
		props.load(JdbcServiceHelper.class.getClassLoader().getResourceAsStream("jdbc.properties"));
		driver = props.getProperty("jdbc.driverClassName");
		url = props.getProperty("jdbc.url");
		user = props.getProperty("jdbc.username");
		password = props.getProperty("jdbc.password");
     //2.��������Դ
		MysqlDataSource ds = new MysqlDataSource();
		 ds.setPassword(password);
		 ds.setUser(user);
		 ds.setURL(url);

		return ds;
	}
	

	public static Connection getConnection() {
		Connection conn = null;
		try{
			conn = ds.getConnection();
		}catch(Exception e){
			try{
				conn = ds.getConnection();
			}catch(Exception e1){
				try{
					conn =ds.getConnection();
				}catch(Exception e2){
					try{
						conn = ds.getConnection();
					}catch(Exception e3){
						//LogFactory.sqlError("��ȡ Connection �����쳣��", e3);
						e.printStackTrace();
					}
				}
				
			}
		}
		return conn;
	}
	
	private static Connection getSqlServerConnection() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		String sqlServerUrl = "'jdbc:mysql://localhost:3306/kuaidi','root','123456'";
		Connection conn = DriverManager.getConnection(sqlServerUrl);
		return conn;
	}
	
}
