package com.express.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.express.common.util.StringUtil;
import com.express.common.util.jdbc.JdbcServiceManager;
import com.express.common.util.jdbc.JdbcServiceManagerImpl;
import com.express.dao.OrderDao;
import com.express.dao.UserDao;
import com.express.model.Images;
import com.express.model.Orders;
import com.express.model.Page;
import com.express.model.Users;

public class OrderDaoImpl implements OrderDao {
private static OrderDao OrderDao= null;
	
	public static OrderDao getInstance(){
		if(null == OrderDao){
			OrderDao = new OrderDaoImpl();
		}
		return OrderDao;
	}
	
	private static final Logger logger = Logger.getLogger(AdminDaoImpl.class);

	private static JdbcServiceManager jdbc = JdbcServiceManagerImpl.getInstance();

	@Override
	public List<Orders> getAllOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> orderSelect(String text,String key,Page page) {
		String tableName = "imageinfo";
		List<Orders> list=new ArrayList<Orders>();
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING3=new StringBuffer();
		if(key.equals("1")){
			SELECTSTRING3.append("select imageinfo.id,imageinfo.inaddr,imageinfo.outaddr,imageinfo.state,imageinfo.year,imageinfo.month,imageinfo.date,imageinfo.time,user.uid,user.name,user.tel,user.email,user.idcard,user.state,image.image,image.iyear,image.imonth,image.idate,image.itime from imageinfo,user,image where imageinfo.uid=user.uid and imageinfo.id =image.id and image.id in(select id from imageinfo where id like '%"+text+"%')");
		}else{
			SELECTSTRING3.append("select imageinfo.id,imageinfo.inaddr,imageinfo.outaddr,imageinfo.state,imageinfo.year,imageinfo.month,imageinfo.date,imageinfo.time,user.uid,user.name,user.tel,user.email,user.idcard,user.state,image.image,image.iyear,image.imonth,image.idate,image.itime from imageinfo,user,image where imageinfo.uid=user.uid and imageinfo.id =image.id and user.uid in(select uid from imageinfo where uid like '%"+text+"%')");
		}
		Map dataMap = null;
		if(!"".equals(page)){
			SELECTSTRING3.append(" order by id asc limit " +page.getSelectIndex()+","+page.getShowNumber()+";");
		}
		List results = jdbc.query("imageinfo",SELECTSTRING3.toString(), parameters);
		for(int i=0;i<results.size();i++){
			Orders info=new Orders();
			dataMap = (HashMap)results.get(i);
			info.setId(StringUtil.null2String(dataMap.get("id")));
			info.setInaddr(StringUtil.null2String(dataMap.get("inaddr")));
			info.setOutaddr(StringUtil.null2String(dataMap.get("outaddr")));
			info.setState(StringUtil.null2String(dataMap.get("state")));
			info.setYear(StringUtil.null2String(dataMap.get("year")));
			info.setMonth(StringUtil.null2String(dataMap.get("month")));
			info.setDate(StringUtil.null2String(dataMap.get("date")));
			info.setTime(StringUtil.null2String(dataMap.get("time")));
			Users user = new Users();
			user.setUid(Integer.parseInt(StringUtil.null2String(dataMap.get("uid"))));
			user.setPassword(StringUtil.null2String(dataMap.get("password")));
			user.setEmail(StringUtil.null2String(dataMap.get("email")));
			user.setTel(StringUtil.null2String(dataMap.get("tel")));
			user.setIdCard(StringUtil.null2String(dataMap.get("idcard")));
			user.setName(StringUtil.null2String(dataMap.get("name")));
			user.setState(StringUtil.null2String(dataMap.get("state")));
			info.setUsers(user);
			Images image = new Images();
			image.setId(StringUtil.null2String(dataMap.get("id")));
			image.setImage(StringUtil.null2String(dataMap.get("image")));
			image.setIyear(StringUtil.null2String(dataMap.get("iyear")));
			image.setImonth(StringUtil.null2String(dataMap.get("imonth")));
			image.setIdate(StringUtil.null2String(dataMap.get("idate")));
			image.setItime(StringUtil.null2String(dataMap.get("itime")));
			info.setImage(image);
			list.add(info);		
	}
		return list;
	}

	@Override
	public List<Orders> getOrder(Page page) {
		String tableName = "imageinfo";
		List<Orders> list=new ArrayList<Orders>();
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING3=new StringBuffer();
		SELECTSTRING3.append("select imageinfo.id,imageinfo.inaddr,imageinfo.outaddr,imageinfo.state,imageinfo.year,imageinfo.month,imageinfo.date,imageinfo.time,user.uid,user.name,user.tel,user.email,user.idcard,user.state,image.image,image.iyear,image.imonth,image.idate,image.itime from imageinfo,user,image where imageinfo.uid=user.uid and image.id = imageinfo.id");
		Map dataMap = null;
		if(!"".equals(page)){
			SELECTSTRING3.append(" order by id asc limit " +page.getSelectIndex()+","+page.getShowNumber()+";");
		}
		List results = jdbc.query("imageinfo",SELECTSTRING3.toString(), parameters);
		for(int i=0;i<results.size();i++){
			Orders info=new Orders();
			dataMap = (HashMap)results.get(i);
			info.setId(StringUtil.null2String(dataMap.get("id")));
			info.setInaddr(StringUtil.null2String(dataMap.get("inaddr")));
			info.setOutaddr(StringUtil.null2String(dataMap.get("outaddr")));
			info.setState(StringUtil.null2String(dataMap.get("state")));
			info.setYear(StringUtil.null2String(dataMap.get("year")));
			info.setMonth(StringUtil.null2String(dataMap.get("month")));
			info.setDate(StringUtil.null2String(dataMap.get("date")));
			info.setTime(StringUtil.null2String(dataMap.get("time")));
			Users user = new Users();
			user.setUid(Integer.parseInt(StringUtil.null2String(dataMap.get("uid"))));
			user.setPassword(StringUtil.null2String(dataMap.get("password")));
			user.setEmail(StringUtil.null2String(dataMap.get("email")));
			user.setTel(StringUtil.null2String(dataMap.get("tel")));
			user.setIdCard(StringUtil.null2String(dataMap.get("idcard")));
			user.setName(StringUtil.null2String(dataMap.get("name")));
			user.setState(StringUtil.null2String(dataMap.get("state")));
			info.setUsers(user);
			Images image = new Images();
			image.setId(StringUtil.null2String(dataMap.get("id")));
			image.setImage(StringUtil.null2String(dataMap.get("image")));
			image.setIyear(StringUtil.null2String(dataMap.get("iyear")));
			image.setImonth(StringUtil.null2String(dataMap.get("imonth")));
			image.setIdate(StringUtil.null2String(dataMap.get("idate")));
			image.setItime(StringUtil.null2String(dataMap.get("itime")));
			info.setImage(image);
			list.add(info);		
	}
		return list;
	}

	@Override
	public boolean addOrder(Orders order) {
		String tableName = "imageinfo";
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING=new StringBuffer();
		boolean b = false;
		if(!"".equals(order.getId())&&!"".equals(order.getUsers())){
		SELECTSTRING.append("insert into imageinfo(id,inaddr,outaddr,state,uid,year,month,date,time) values('"+order.getId()+"','"+order.getInaddr()+"','"+order.getOutaddr()+"','"+order.getState()+"','"+order.getUsers().getUid()+"','"+order.getYear()+"','"+order.getMonth()+"','"+order.getDate()+"','"+order.getTime()+"');");
		}
		try {
			 b = jdbc.insert("imageinfo",SELECTSTRING.toString(), parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean updateOrder(Orders order, String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOrder(String id,String state) {
		String tableName = "imageinfo";
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING=new StringBuffer();
		 if(state.equals("'1'")){
			 SELECTSTRING.append("update imageinfo set state='0' where id='"+id+"';");
		 }else{
			 SELECTSTRING.append("update imageinfo set state='1' where id='"+id+"';");
		 }
		
		try {
			 jdbc.update("imageinfo",SELECTSTRING.toString(), parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public List<Orders> getOrderCount(String text, String year, String month,
			String key, Page page) {
		String tableName = "imageinfo";
		List<Orders> list=new ArrayList<Orders>();
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING3=new StringBuffer();
		if("0".equals(month)){
			SELECTSTRING3.append("select info.id,info.inaddr,info.outaddr,info.state,info.year,info.month,info.date,info.time,info.uid,user.uid,user.name,user.tel,user.email,user.idcard,user.state,image.image,image.iyear,image.imonth,image.idate,image.itime from imageinfo as info,user,image where info.uid=user.uid and image.id = info.id and user.uid in(select uid from imageinfo where uid like '%"+text+"%' and imageinfo.year='"+year+"')");
		}else{
			SELECTSTRING3.append("select info.id,info.inaddr,info.outaddr,info.state,info.year,info.month,info.date,info.time,info.uid,user.uid,user.name,user.tel,user.email,user.idcard,user.state,image.image,image.iyear,image.imonth,image.idate,image.itime from imageinfo as info,user,image where info.uid=user.uid and image.id = info.id and user.uid in(select uid from imageinfo where uid like '%"+text+"%' and imageinfo.year='"+year+"' and imageinfo.month='"+month+"')");
		}
		Map dataMap = null;
		if(!"".equals(page)){
			SELECTSTRING3.append(" order by id asc limit " +page.getSelectIndex()+","+page.getShowNumber()+"; ");
		}
		List results = jdbc.query("imageinfo",SELECTSTRING3.toString(), parameters);
		for(int i=0;i<results.size();i++){
			Orders info=new Orders();
			dataMap = (HashMap)results.get(i);
			info.setId(StringUtil.null2String(dataMap.get("id")));
			info.setInaddr(StringUtil.null2String(dataMap.get("inaddr")));
			info.setOutaddr(StringUtil.null2String(dataMap.get("outaddr")));
			info.setState(StringUtil.null2String(dataMap.get("state")));
			info.setYear(StringUtil.null2String(dataMap.get("year")));
			info.setMonth(StringUtil.null2String(dataMap.get("month")));
			info.setDate(StringUtil.null2String(dataMap.get("date")));
			info.setTime(StringUtil.null2String(dataMap.get("time")));
			Users user = new Users();
			user.setUid(Integer.parseInt(StringUtil.null2String(dataMap.get("uid"))));
			user.setPassword(StringUtil.null2String(dataMap.get("password")));
			user.setEmail(StringUtil.null2String(dataMap.get("email")));
			user.setTel(StringUtil.null2String(dataMap.get("tel")));
			user.setIdCard(StringUtil.null2String(dataMap.get("idcard")));
			user.setName(StringUtil.null2String(dataMap.get("name")));
			user.setState(StringUtil.null2String(dataMap.get("state")));
			info.setUsers(user);
			Images image = new Images();
			image.setId(StringUtil.null2String(dataMap.get("id")));
			image.setImage(StringUtil.null2String(dataMap.get("image")));
			image.setIyear(StringUtil.null2String(dataMap.get("iyear")));
			image.setImonth(StringUtil.null2String(dataMap.get("imonth")));
			image.setIdate(StringUtil.null2String(dataMap.get("idate")));
			image.setItime(StringUtil.null2String(dataMap.get("itime")));
			info.setImage(image);
			list.add(info);		
	}
		return list;
	}
	@Override
	public int getPageTotalNum() {
		String tableName = "imageinfo";
		List<Users> list=new ArrayList<Users>();
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING3=new StringBuffer();
		SELECTSTRING3.append("select imageinfo.id,imageinfo.inaddr,imageinfo.outaddr,imageinfo.state,imageinfo.year,imageinfo.month,imageinfo.date,imageinfo.time,user.uid,user.name,user.tel,user.email,user.idcard,user.state,image.image,image.iyear,image.imonth,image.idate,image.itime from imageinfo,user,image where imageinfo.uid=user.uid and image.id = imageinfo.id");
		Map dataMap = null;
		List results = jdbc.query("imageinfo",SELECTSTRING3.toString(), parameters);
		
		return results.size();
	}

	@Override
	public int getPageTotalNum(String text, String key) {
		String tableName = "imageinfo";
		List<Users> list=new ArrayList<Users>();
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING3=new StringBuffer();
		if(key.equals("1")){
			SELECTSTRING3.append("select * from imageinfo where id like '%"+text+"%';");
		}else{
			SELECTSTRING3.append("select * from imageinfo where uid like '%"+text+"%';");
		}
		Map dataMap = null;
		List results = jdbc.query("imageinfo",SELECTSTRING3.toString(), parameters);
		
		return results.size();
	}

	@Override
	public int getPageTotalNum(String text, String year, String month,
			String key) {
		String tableName = "imageinfo";
		List<Orders> list=new ArrayList<Orders>();
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING3=new StringBuffer();
		if("0".equals(month)){
			SELECTSTRING3.append("select imageinfo.id,imageinfo.year,imageinfo.month,imageinfo.uid from imageinfo,user,image where imageinfo.uid=user.uid and image.id = imageinfo.id and user.uid in(select uid from imageinfo where uid like '%"+text+"%' and imageinfo.year='"+year+"');");
		}else{
			SELECTSTRING3.append("select imageinfo.id,imageinfo.year,imageinfo.month,imageinfo.uid from imageinfo,user,image where imageinfo.uid=user.uid and image.id = imageinfo.id and user.uid in(select uid from imageinfo where uid like '%"+text+"%' and imageinfo.year='"+year+"' and imageinfo.month='"+month+"');");
		}
		Map dataMap = null;
		List results = jdbc.query("imageinfo",SELECTSTRING3.toString(), parameters);
		
		return results.size();
	}

}
