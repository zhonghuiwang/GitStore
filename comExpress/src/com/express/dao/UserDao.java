package com.express.dao;

import java.util.List;

import com.express.model.Admin;
import com.express.model.Page;
import com.express.model.Users;

public interface UserDao {
	public List<Users> getAllUser();
	public List<Users> userSelect(String text,String key,Page page);
	public List<Users> getUser(Page page);
	public boolean addUser(Users user);
	public boolean updateUser(Users user,String uid);
	public boolean updateUser(String uid,String state);
	public int getPageTotalNum();
	public int getPageTotalNum(String text,String key);
	public boolean userLogin(String uid,String pwd);
}
