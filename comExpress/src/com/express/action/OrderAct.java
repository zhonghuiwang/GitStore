package com.express.action;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.express.model.Images;
import com.express.model.Orders;
import com.express.model.Page;
import com.express.model.Users;
import com.express.service.OrderService;
import com.express.service.UserService;

@Controller
public class OrderAct {
	@Qualifier("OrderService")
    @Autowired
    private OrderService OrderService;
	static final String limit = "5"; //分页，每页显示数
	
	@RequestMapping(value = "/opasson.act")
    public void addFunTreeNote(HttpServletRequest request, HttpServletResponse response){
		try {
			request.getRequestDispatcher("view/oselect.jsp").forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	@RequestMapping(value = "/Passon.act")
    public void passOn(HttpServletRequest request, HttpServletResponse response){
		try {
			request.getRequestDispatcher("view/ordercount.jsp").forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	/**
	 * 
	 * 新增
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/addOrder.act")
    public void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        HttpSession session = request.getSession(true);
        String acceptjson = "";
        Date d = new Date();
		Calendar c = Calendar.getInstance();

		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH); 
		int date = c.get(Calendar.DATE); 
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(d);
		
		Orders order = new Orders();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String temp;
            boolean b = true;
            while((temp = br.readLine()) != null){
                sb.append(temp);
            }
            br.close();                     
                  acceptjson = sb.toString();
                  System.out.println("=======json is==========="+acceptjson);
                  if(acceptjson != ""){
                      JSONObject jo = JSONObject.fromObject(acceptjson);
                      JSONObject jo2 = JSONObject.fromObject(jo.get("Message"));
                      String id = jo2.get("id").toString();
                      String outaddr = jo2.get("outaddr").toString();
                      String inaddr = jo2.get("inaddr").toString();
                      String uid = jo2.get("uid").toString();
               
                      
             
                     Users user  = new Users();
                     Images image = new Images();
                     user.setUid(Integer.parseInt(uid));
                     image.setId(id);
                     order.setId(id);
                     order.setInaddr(inaddr);
                     order.setOutaddr(outaddr);
                     order.setImage(image);
                     order.setUsers(user);
                     order.setState("1");
                     order.setYear(String.valueOf(year));
                     order.setMonth(String.valueOf(month+1));
                     order.setDate(String.valueOf(date));
                     order.setTime(time);
                     this.OrderService.addOrder(order);
                      if(b){
                    	  response.getWriter().write("上传成功！");
                    	  System.out.println("ok!");
                      }else{
                    	  response.getWriter().write("上传失败！");
                    	  System.out.println("fail!");
                      	}
            
                  }
                  else{
                      System.out.println("get the json failed");
                  }
              } catch (Exception e) {
                  // TODO: handle exception
                  e.printStackTrace();
              }
	}

	
	/**
	 * 
	 * 查询所有信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "queryOrders.act")
	public void queryUser(HttpServletRequest request, HttpServletResponse response){
		    response.setCharacterEncoding("UTF-8");
		    String pg = request.getParameter("pg");
		    String pgNum = "1";
		    if(pg!=null && !"".equals(pg)){
		    	pgNum = pg;
		    }
		    int count = this.OrderService.getPageTotalNum();
		    
			Page page = new Page(Integer.parseInt(pgNum),Integer.parseInt(limit),count);
			List<Orders> list = this.OrderService.getOrder(page);
			request.setAttribute("imageinfo", list);
			request.setAttribute("pgInfo", page);
			request.setAttribute("count", count);
				try {
					request.getRequestDispatcher("view/imglist.jsp").forward(request,response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	}
	
	/**
	 * 
	 * 根据条件和分页数来查询统计信息
	 * @param request
	 * @param response
	 */	
	
		@RequestMapping(value = "/getOrderCount.act")
		public void getServerInfo(HttpServletRequest request, HttpServletResponse response){
			try {
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/json;charset=UTF-8");
			    String text = request.getParameter("search");
			    String year = request.getParameter("selectY");
			    String month = request.getParameter("selectM");
			    String key = request.getParameter("select");
			    String pg = request.getParameter("pg");
			    String pgNum = "1";
			    if(pg!=null && !"".equals(pg)){
			    	pgNum = pg;
			    }
			    int count = this.OrderService.getPageTotalNum(text, year, month, key);
			    
				Page page = new Page(Integer.parseInt(pgNum),Integer.parseInt(limit),count);
				List<Orders> list = this.OrderService.getOrderCount(text, year, month, key, page);
				request.setAttribute("countinfo", list);
				request.setAttribute("pgInfo", page);
				request.setAttribute("count", count);
				request.setAttribute("text", text);
				request.setAttribute("key", key);
				request.setAttribute("year", year);
				request.setAttribute("month", month);
				request.getRequestDispatcher("view/ordercount.jsp").forward(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			
			
			
			
		}


	
		/**
		 * 
		 * 根据id来查询信息
		 * @param request
		 * @param response
		 */	 
			@RequestMapping(value = "/orderSelect.act")
			public void orderselect(HttpServletRequest request, HttpServletResponse response){
				try {
					request.setCharacterEncoding("UTF-8");
					response.setContentType("text/json;charset=UTF-8");
				    String text = request.getParameter("search");
				    String key = request.getParameter("select");
				    String pg = request.getParameter("pg");
				    String pgNum = "1";
				    if(pg!=null && !"".equals(pg)){
				    	pgNum = pg;
				    }
				    int count = this.OrderService.getPageTotalNum(text,key);
				 
					Page page = new Page(Integer.parseInt(pgNum),Integer.parseInt(limit),count);
					List<Orders> list = this.OrderService.orderSelect(text,key,page);
					request.setAttribute("selectinfo", list);
					request.setAttribute("key", key);
					request.setAttribute("text", text);
					request.setAttribute("pgInfo", page);
					request.setAttribute("count", count);
					request.getRequestDispatcher("view/oselect.jsp").forward(request,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			}
	
			
			/**
			 * 
			 * 修改所选择的信息
			 * @param request
			 * @param response
			 */	 
				@RequestMapping(value = "/updateOrder.act")
				public void deleteById(HttpServletRequest request, HttpServletResponse response){
					try {
						request.setCharacterEncoding("UTF-8");
					    String  id = request.getParameter("id");
					    String  st = request.getParameter("st");
						this.OrderService.updateOrder(id, st);
						response.sendRedirect("queryOrders.act");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				/**
				 * 
				 * 修改所选择的信息
				 * @param request
				 * @param response
				 */
				/*	 
					@RequestMapping(value = "/updateInfoById.act")
					public void updateInfoById(HttpServletRequest request, HttpServletResponse response){
					
				}*/
}
