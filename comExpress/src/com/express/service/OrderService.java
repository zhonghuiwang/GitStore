package com.express.service;

import java.util.List;

import com.express.model.Images;
import com.express.model.Orders;
import com.express.model.Page;
import com.express.model.Users;

public interface OrderService {
	public String queryOrder();
	public boolean addOrder(Orders order);
	public boolean updateOrder(String id,String state);
	public boolean updateOrder(String id,String inaddr,String outaddr,String state,Users user,Images image);
	public List<Orders> getOrder(Page page);
	public List<Orders> orderSelect(String text,String key,Page page);
	public List<Orders> getOrderCount(String text,String year,String month,String key,Page page);
	public int getPageTotalNum();
	public int getPageTotalNum(String text,String key);
	public int getPageTotalNum(String text,String year,String month,String key);
}
