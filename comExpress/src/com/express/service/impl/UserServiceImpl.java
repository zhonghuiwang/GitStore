package com.express.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.express.dao.UserDao;
import com.express.dao.impl.UserDaoImpl;
import com.express.model.Page;
import com.express.model.Users;
import com.express.service.UserService;

@SuppressWarnings("unchecked")
@Service("UserService")
public class UserServiceImpl implements UserService {
	private static UserDao infoDao=UserDaoImpl.getInstance();
	@Override
	public String queryUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(Users user) {
		// TODO Auto-generated method stub
		return infoDao.addUser(user);
	}

	@Override
	public boolean updateUser(String uid,String state) {
		
		return infoDao.updateUser(uid, state);
	}

	@Override
	public boolean updateUser(String uid, String password, String name,
			String tel, String email, String idcard,String state) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public List<Users> getUser(Page page) {
		List<Users> ulist =infoDao.getUser(page);
		
		return ulist;
	}

	@Override
	public List<Users> userSelect(String text,String key,Page page) {
		
		return infoDao.userSelect(text, key,page);
	}

	@Override
	public int getPageTotalNum() {
		
		return infoDao.getPageTotalNum();
	}

	@Override
	public int getPageTotalNum(String text, String key) {
		// TODO Auto-generated method stub
		return infoDao.getPageTotalNum(text, key);
	}

	@Override
	public boolean userLogin(String uid, String pwd) {
		// TODO Auto-generated method stub
		return infoDao.userLogin(uid, pwd);
	}

}
