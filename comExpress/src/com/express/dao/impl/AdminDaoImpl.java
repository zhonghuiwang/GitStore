package com.express.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.express.dao.AdminDao;
import com.express.model.Admin;
import com.express.common.util.StringUtil;
import com.express.common.util.jdbc.JdbcServiceManager;
import com.express.common.util.jdbc.JdbcServiceManagerImpl;
import com.express.model.Admin;
import com.express.dao.AdminDao;
import com.express.dao.impl.AdminDaoImpl;

public class AdminDaoImpl implements AdminDao {
private static AdminDao AdminDao= null;
	
	public static AdminDao getInstance(){
		if(null == AdminDao){
			AdminDao = new AdminDaoImpl();
		}
		return AdminDao;
	}
	
	private static final Logger logger = Logger.getLogger(AdminDaoImpl.class);

	private static JdbcServiceManager jdbc = JdbcServiceManagerImpl.getInstance();

	
	@Override
	public List<Admin> getAllInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Admin> getInfoById(String id,String pwd) {
		String tableName="admin";
		List<Admin> list=new ArrayList<Admin>();
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING=new StringBuffer();
		SELECTSTRING.append("select suid,password,suname from admin where suid='"+id+"' and password='"+pwd+"';");
		Map dataMap = null;
		List results = jdbc.query("admin",SELECTSTRING.toString(), parameters);
		if(results.size()>0){
			for(int i=0;i<results.size();i++){
			Admin info=new Admin();
			dataMap = (HashMap)results.get(i);
			info.setSuid(Integer.parseInt(StringUtil.null2String(dataMap.get("suid"))));
			info.setPassword(StringUtil.null2String(dataMap.get("password")));
			info.setSuname(StringUtil.null2String(dataMap.get("suname")));
			list.add(info);
			}
			return list;
		}else{
			Admin info=new Admin();
			info.setPassword(null);
			info.setPassword(null);
			list.add(info);
			return list;
		}
	}

	@Override
	public List getInfos(Admin info, String start, String limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addServersInfo(Admin nodes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateInfo(Admin info, String sid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delInfoById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
