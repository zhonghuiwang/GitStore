package com.express.service.impl;

import org.springframework.stereotype.Service;

import com.express.dao.ImageDao;
import com.express.dao.impl.ImageDaoImpl;
import com.express.service.ImageService;

@SuppressWarnings("unchecked")
@Service("ImageService")
public class ImageServiceImpl implements ImageService {
	private static ImageDao ImageDao=ImageDaoImpl.getInstance();
	
	@Override
	public boolean addImage(String id, String image,String year,String month,String date,String time) {
		boolean b = ImageDao.addImage(id, image,year,month,date,time);
		return b;
	}

}
