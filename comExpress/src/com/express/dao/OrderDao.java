package com.express.dao;

import java.util.List;

import com.express.model.Orders;
import com.express.model.Page;

public interface OrderDao {
	public List<Orders> getAllOrder();
	public List<Orders> orderSelect(String text,String key,Page page);
	public List<Orders> getOrder(Page page);
	public List<Orders> getOrderCount(String text,String year,String month,String key,Page page);
	public boolean addOrder(Orders order);
	public boolean updateOrder(Orders order,String id);
	public boolean updateOrder(String id,String state);
	public int getPageTotalNum();
	public int getPageTotalNum(String text,String key);
	public int getPageTotalNum(String text,String year,String month,String key);
}
