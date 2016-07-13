package com.express.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.express.model.Page;
import com.express.model.Users;
import com.express.service.UserService;

@Controller
public class UserAct {
	@Qualifier("UserService")
    @Autowired
    private UserService UserService;
	static final String limit = "8"; //分页，每页显示数
	    
		/**
		 * 
		 *过渡
		 * @param request
		 * @param response
		 */
		@RequestMapping(value = "/adduserpass.act")
	    public void adduserpass(HttpServletRequest request, HttpServletResponse response){
			try {
				request.getRequestDispatcher("view/userform.jsp").forward(request,response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		 @RequestMapping(value = "/passon.act")
		    public void addFunTreeNote(HttpServletRequest request, HttpServletResponse response){
				try {
					request.getRequestDispatcher("view/uselect.jsp").forward(request,response);
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
		 * 根据分页数查询所有信息
		 * @param request
		 * @param response
		 */
		@RequestMapping(value = "queryUsers.act")
		public void queryUser(HttpServletRequest request, HttpServletResponse response){
			    response.setCharacterEncoding("UTF-8");
			    String pg = request.getParameter("pg");
			    String pgNum = "1";
			    if(pg!=null && !"".equals(pg)){
			    	pgNum = pg;
			    }
			    int count = this.UserService.getPageTotalNum();
			    
				Page page = new Page(Integer.parseInt(pgNum),Integer.parseInt(limit),count);
				List<Users> list = this.UserService.getUser(page);
				request.setAttribute("userinfo", list);
				request.setAttribute("pgInfo", page);
				request.setAttribute("count", count);
					try {
						request.getRequestDispatcher("view/userlist.jsp").forward(request,response);
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
		 */
			@RequestMapping(value = "/addUsers.act")
			public void addUser(HttpServletRequest request, HttpServletResponse response){
				try {
					request.setCharacterEncoding("UTF-8");
					response.setContentType("text/json;charset=UTF-8");
					Users user = new Users();
					user.setUid(Integer.parseInt(request.getParameter("uid")));
					user.setName(request.getParameter("name"));
					user.setPassword(request.getParameter("pwd"));
					user.setIdCard(request.getParameter("idcard"));
					user.setTel(request.getParameter("tel"));
					user.setEmail(request.getParameter("email"));
					boolean b = false;
					b = this.UserService.addUser(user);
					if(b){
						request.setAttribute("msg", "100");
						request.getRequestDispatcher("view/userform.jsp").forward(request,response);
					}else{
						request.setAttribute("msg", "400");
						request.getRequestDispatcher("view/userform.jsp").forward(request,response);
					}
					
					
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
				@RequestMapping(value = "/userSelect.act")
				public void userSelect(HttpServletRequest request, HttpServletResponse response){
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
					    int count = this.UserService.getPageTotalNum(text,key);
					    
						Page page = new Page(Integer.parseInt(pgNum),Integer.parseInt(limit),count);
						List<Users> list = this.UserService.userSelect(text, key, page);
						request.setAttribute("selectinfo", list);
						request.setAttribute("key", key);
						request.setAttribute("text", text);
						request.setAttribute("pgInfo",page);
						request.setAttribute("count", count);
						request.getRequestDispatcher("view/uselect.jsp").forward(request,response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				    
				}
			
				
				
				/**
				 * 
				 * 删除所选择的信息
				 * @param request
				 * @param response
				 */	 
					@RequestMapping(value = "/updateUser.act")
					public void updateUser(HttpServletRequest request, HttpServletResponse response){
						try {
							request.setCharacterEncoding("UTF-8");
							request.setCharacterEncoding("UTF-8");
						    String  uid = request.getParameter("id");
						    String  st = request.getParameter("st");
							this.UserService.updateUser(uid,st);
							response.sendRedirect("queryUsers.act");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					/**
					 * 
					 * 修改所选择的信息
					 * @param request
					 * @param response
					 *//*	 
						@RequestMapping(value = "/updateInfoById.act")
						public void updateInfoById(HttpServletRequest request, HttpServletResponse response){
						
						}*/
	
}
