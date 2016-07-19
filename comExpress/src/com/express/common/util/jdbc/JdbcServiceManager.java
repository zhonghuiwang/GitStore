/**
 * 
 */
package com.express.common.util.jdbc;

import java.util.List;

/**
 * @author pengjiong
 *
 */
public interface JdbcServiceManager {
	
	List query(String tableName,String queryString,List parameters);
	
	
	boolean insert(String tableName,String insertString,List parameters) throws Exception;
	
	
	
	void update(String tableName,String updateString,List parameters) throws Exception;
	
	
	void delete(String tableName,String updateString,List parameters) throws Exception;
	
	
	
	
}
