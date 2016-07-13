package com.express.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.express.common.util.StringUtil;
import com.express.common.util.jdbc.JdbcServiceManager;
import com.express.common.util.jdbc.JdbcServiceManagerImpl;
import com.express.dao.ImageDao;
import com.express.dao.OrderDao;
import com.express.model.Images;
import com.express.model.Orders;
import com.express.model.Users;

public class ImageDaoImpl implements ImageDao {
private static ImageDao ImageDao= null;
	
	public static ImageDao getInstance(){
		if(null == ImageDao){
			ImageDao = new ImageDaoImpl();
		}
		return ImageDao;
	}
	
	private static final Logger logger = Logger.getLogger(AdminDaoImpl.class);

	private static JdbcServiceManager jdbc = JdbcServiceManagerImpl.getInstance();
	@Override
	public boolean addImage(String id, String image,String year,String month,String date,String time) {
		String tableName = "image";
		List parameters = new ArrayList();
		StringBuffer SELECTSTRING=new StringBuffer();
		if(!"".equals(id)&&!"".equals(image)){
		SELECTSTRING.append("insert into image(id,image,year,month,date,time) values('"+id+"','"+image+"','"+year+"','"+month+"','"+date+"','"+time+"');");
		System.out.println(SELECTSTRING.toString());
		}
		try {
			 jdbc.insert("image",SELECTSTRING.toString(), parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
