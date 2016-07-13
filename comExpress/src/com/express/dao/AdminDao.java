package com.express.dao;

import java.util.List;

import com.express.model.Admin;

public interface AdminDao {
	public List<Admin> getAllInfo();
	public List<Admin> getInfoById(String id,String pwd);
	public List getInfos(Admin info,String start,String limit);
	public boolean addServersInfo(Admin nodes);
	public boolean updateInfo(Admin info,String sid);
	public boolean delInfoById(String id);
}
