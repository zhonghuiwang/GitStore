package com.express.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.express.dao.OrderDao;
import com.express.dao.UserDao;
import com.express.dao.impl.OrderDaoImpl;
import com.express.dao.impl.UserDaoImpl;
import com.express.model.Images;
import com.express.model.Orders;
import com.express.model.Page;
import com.express.model.Users;
import com.express.service.OrderService;

@SuppressWarnings("unchecked")
@Service("OrderService")
public class OrderServiceImpl implements OrderService {
	private static OrderDao infoDao=OrderDaoImpl.getInstance();
	@Override
	public String queryOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addOrder(String id, String inaddr, String outaddr,
			String state, Users user, Images image) {
		Orders order = new Orders();
		order.setId(id);
		order.setInaddr(inaddr);
		order.setOutaddr(outaddr);
		order.setState(state);
		order.setUsers(user);
		order.setImage(image);
		boolean b = infoDao.addOrder(order);
		return b;
	}

	@Override
	public boolean updateOrder(String id,String st) {
		
		return infoDao.updateOrder(id, st);
	}

	@Override
	public boolean updateOrder(String id, String inaddr, String outaddr,
			String state, Users user, Images image) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Orders> getOrder(Page page) {
		List<Orders> olist =infoDao.getOrder(page);
		return olist;
	}

	@Override
	public List<Orders> orderSelect(String text,String key,Page page) {
		List<Orders> list = infoDao.orderSelect(text, key,page);
		return list;
	}

	@Override
	public List<Orders> getOrderCount(String text, String year, String month,
			String key,Page page) {
		// TODO Auto-generated method stub
		return infoDao.getOrderCount(text, year, month, key, page);
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
	public int getPageTotalNum(String text, String year, String month,
			String key) {
		// TODO Auto-generated method stub
		return infoDao.getPageTotalNum(text, year, month, key);
	}

}
