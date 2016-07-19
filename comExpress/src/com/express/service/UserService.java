package com.express.service;

import java.util.List;

import com.express.model.Page;
import com.express.model.Users;

public interface UserService {
	public String queryUser();
	public boolean addUser(Users user);
	public boolean updateUser(String uid,String state);
	public boolean updateUser(String uid,String password,String name,String tel,String email,String idcard,String state);
	public List<Users> getUser(Page page);
	public List<Users> userSelect(String text,String key,Page page);
	public int getPageTotalNum();
	public int getPageTotalNum(String text,String key);
	public boolean userLogin(String uid,String pwd);
}
