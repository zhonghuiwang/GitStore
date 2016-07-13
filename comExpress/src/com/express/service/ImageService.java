package com.express.service;

import com.express.model.Images;
import com.express.model.Users;

public interface ImageService {
	public boolean addImage(String id,String image,String year,String month,String date,String time);
}
