package com.express.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.express.model.Orders;
import com.express.service.ImageService;

@Controller
public class ImageAct {
	@Qualifier("ImageService")
    @Autowired
    private ImageService ImageService; 
	
	@RequestMapping(value = "/addImages.act")
	public void addImage(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		// 创建文件项目工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 设置文件上传路径
		String upload = request.getSession().getServletContext().getRealPath("\\upload\\");
		// 获取系统默认的临时文件保存路径，该路径为Tomcat根目录下的temp文件夹
		String temp = System.getProperty("user.dir") ;
		// 设置缓冲区大小为 5M
		factory.setSizeThreshold(1024 * 1024 * 5);
		// 设置临时文件夹为temp
		factory.setRepository(new File(temp));
		// 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		String idname = null;
		// 解析结果放在List中
		boolean b = false;
		Date d = new Date();
		Calendar c = Calendar.getInstance();

		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH); 
		int date = c.get(Calendar.DATE); 
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String dateNowStr = sdf.format(d);
		try
		{
			List<FileItem> list = servletFileUpload.parseRequest(request);

			for (FileItem item : list)
			{
				String name = item.getFieldName();
				InputStream is = item.getInputStream();
			
				if (name.contains("id"))
				{
					idname = inputStream2String(is);
					
				} 
				if(name.contains("picture"))
				{
					try
					{
						inputStream2File(is, upload + "\\"+ item.getName());
						
						b= this.ImageService.addImage(idname, "upload\\" +"\\"+ item.getName(),String.valueOf(year),String.valueOf(month+1),String.valueOf(date),dateNowStr);
						System.out.println("upload\\" +"\\"+ item.getName());
						if(b){
							System.out.println("nice");
						}else{
							System.out.println("what a pity");
						}
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			
			out.write("success");
		} catch (FileUploadException e)
		{
			e.printStackTrace();
			out.write("failure");
		}

		
		out.flush();
		out.close();
	}

	// 流转化成字符串
	public String inputStream2String(InputStream is) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1)
		{
			baos.write(i);
		}
		return baos.toString();
	}

	// 流转化成文件
	public static void inputStream2File(InputStream is, String savePath)
			throws Exception
	{
		System.out.println("文件保存路径为:" + savePath);
		File file = new File(savePath);
		InputStream inputSteam = is;
		BufferedInputStream fis = new BufferedInputStream(inputSteam);
		FileOutputStream fos = new FileOutputStream(file);
		int f;
		while ((f = fis.read()) != -1)
		{
			fos.write(f);
		}
		fos.flush();
		fos.close();
		fis.close();
		inputSteam.close();
		
	    
	}
}
