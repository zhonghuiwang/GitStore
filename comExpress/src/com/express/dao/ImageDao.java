package com.express.dao;

import com.express.model.Orders;

public interface ImageDao {
	public boolean addImage(String id,String image,String year,String month,String date,String time);
}
