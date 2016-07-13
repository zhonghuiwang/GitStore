package com.express.service.impl;

import org.springframework.stereotype.Service;

import com.express.dao.impl.AdminDaoImpl;
import com.express.model.Admin;
import com.express.service.AdminService;
import com.express.dao.AdminDao;;

@SuppressWarnings("unchecked")
@Service("AdminService")
public class AdminServiceImpl implements AdminService {

	private static AdminDao AdminDao=AdminDaoImpl.getInstance();
	@Override
	public String queryAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAdminById(String id,String pwd) {
		Admin info=new Admin();
		info=AdminDao.getInfoById(id,pwd).get(0);
		return info;
	}

}
