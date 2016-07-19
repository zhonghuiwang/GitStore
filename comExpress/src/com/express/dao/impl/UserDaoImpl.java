package com.express.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.express.common.util.jdbc.JdbcServiceManager;
import com.express.common.util.jdbc.JdbcServiceManagerImpl;
import com.express.dao.AdminDao;
import com.express.dao.UserDao;
import com.express.model.Admin;
import com.express.model.Page;
import com.express.model.Users;
import com.express.common.util.StringUtil;

public class UserDaoImpl implements UserDao {
private static UserDao UserDao= null;
	
	public static UserDao getInstance(){
		if(null == UserDao){
			UserDao = new UserDaoImpl();
		}
		return UserDao;
	}
	
	private static final Logger logger = Logger.getLogger(AdminDaoImpl.class);

	private static JdbcServiceManager jdbc = JdbcServiceManagerImpl.getInstance();


	@Override
	public List<Users> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> userSelect(String text,String key,Page page) {
		String tableName = "user";
		List<Users> list=new ArrayList<Users>();
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING3=new StringBuffer();
		if(key.equals("1")){
			SELECTSTRING3.append("select * from user where uid like '%"+text+"%'");
		}else{
			SELECTSTRING3.append("select * from user where name like '%"+text+"%'");
		}
		Map dataMap = null;
		if(!"".equals(page)){
			SELECTSTRING3.append(" order by uid asc limit " +page.getSelectIndex()+","+page.getShowNumber()+";");
		}
		List results = jdbc.query("user",SELECTSTRING3.toString(), parameters);
		for(int i=0;i<results.size();i++){
			Users info=new Users();
			dataMap = (HashMap)results.get(i);
			info.setUid(Integer.parseInt(StringUtil.null2String(dataMap.get("uid"))));
			info.setPassword(StringUtil.null2String(dataMap.get("password")));
			info.setEmail(StringUtil.null2String(dataMap.get("email")));
			info.setTel(StringUtil.null2String(dataMap.get("tel")));
			info.setIdCard(StringUtil.null2String(dataMap.get("idcard")));
			info.setName(StringUtil.null2String(dataMap.get("name")));
			info.setState(StringUtil.null2String(dataMap.get("state")));
			list.add(info);		
	}
		return list;
	}

	public List<Users> getUser(Page page) {
		String tableName = "user";
		List<Users> list=new ArrayList<Users>();
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING3=new StringBuffer();
		SELECTSTRING3.append("select * from user where 1=1");
		Map dataMap = null;
		if(!"".equals(page)){
			SELECTSTRING3.append(" order by uid asc limit " +page.getSelectIndex()+","+page.getShowNumber()+";");
		}
		List results = jdbc.query("user",SELECTSTRING3.toString(), parameters);
		for(int i=0;i<results.size();i++){
			Users info=new Users();
			dataMap = (HashMap)results.get(i);
			info.setUid(Integer.parseInt(StringUtil.null2String(dataMap.get("uid"))));
			info.setPassword(StringUtil.null2String(dataMap.get("password")));
			info.setEmail(StringUtil.null2String(dataMap.get("email")));
			info.setTel(StringUtil.null2String(dataMap.get("tel")));
			info.setIdCard(StringUtil.null2String(dataMap.get("idcard")));
			info.setName(StringUtil.null2String(dataMap.get("name")));
			info.setState(StringUtil.null2String(dataMap.get("state")));
			list.add(info);		
	}
		
		return list;
	}

	@Override
	public boolean addUser(Users user) {
		String tableName = "user";
		List parameters = new ArrayList();
		boolean b = true;
		StringBuffer SELECTSTRING=new StringBuffer();
		if(!"".equals(user.getUid())&&!"".equals(user.getPassword())){
			SELECTSTRING.append("insert into user(uid,password,name,tel,email,idcard,state) values('"+user.getUid()+"','"+user.getPassword()+"','"+user.getName()+"','"+user.getTel()+"','"+user.getEmail()+"','"+user.getIdCard()+"','1');");
		}
		try {
			 b=jdbc.insert("user",SELECTSTRING.toString(), parameters);
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return b;
	}

	@Override
	public boolean updateUser(Users user, String uid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(String uid,String state) {
		String tableName = "user";
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING=new StringBuffer();
		 if(state.equals("'1'")){
			 SELECTSTRING.append("update user set state='0' where uid='"+uid+"';");
		 }else{
			 SELECTSTRING.append("update user set state='1' where uid='"+uid+"';");
		 }
		
		try {
			 jdbc.update("user",SELECTSTRING.toString(), parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public int getPageTotalNum() {
		String tableName = "user";
		List<Users> list=new ArrayList<Users>();
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING3=new StringBuffer();
		SELECTSTRING3.append("select * from user;");
		Map dataMap = null;
		List results = jdbc.query("user",SELECTSTRING3.toString(), parameters);
		
		return results.size();
	}

	@Override
	public int getPageTotalNum(String text, String key) {
		String tableName = "user";
		List<Users> list=new ArrayList<Users>();
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING3=new StringBuffer();
		if(key.equals("1")){
			SELECTSTRING3.append("select * from user where uid like '%"+text+"%';");
		}else{
			SELECTSTRING3.append("select * from user where name like '%"+text+"%';");
		}
		Map dataMap = null;
		List results = jdbc.query("user",SELECTSTRING3.toString(), parameters);
		
		return results.size();
	}

	@Override
	public boolean userLogin(String uid, String pwd) {
		String tableName="user";
		List<Admin> list=new ArrayList<Admin>();
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING=new StringBuffer();
		SELECTSTRING.append("select uid,password from user where uid='"+uid+"' and password='"+pwd+"';");
		List results = jdbc.query("admin",SELECTSTRING.toString(), parameters);
		if(results.size()>0){
			return true;
		}else{
			return false;
		}
	}

}
