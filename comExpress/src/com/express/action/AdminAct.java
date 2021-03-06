package com.express.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




import com.express.common.util.ExtHelper;
import com.express.service.AdminService;
import com.express.model.Admin;

@Controller
public class AdminAct {
	@Qualifier("AdminService")
    @Autowired
    
    private AdminService AdminService;
	/**
	 * 
	 * 新增
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/searchAdmin.act")
    public void addFunTreeNote(HttpServletRequest request, HttpServletResponse response){


    
}
	
	/**
	 * 
	 * 查询所有信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/logout.act")
	public void queryAdminInfo(HttpServletRequest request, HttpServletResponse response){
			HttpSession session = request.getSession(true);
			session.removeAttribute("admin");
			try {
				response.sendRedirect("index.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	/**
	 * 
	 * 根据id来查询信息
	 * @param request
	 * @param response
	 */	 
	@RequestMapping(value = "/queryAdminById.act")
	public void queryAdminById(HttpServletRequest request, HttpServletResponse response){
	try {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
	    HttpSession session = request.getSession(true);
	    session.setMaxInactiveInterval(-1);
		String id = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		Admin sinfo= (Admin)this.AdminService.getAdminById(id,pwd);
		String json = null;
		if(sinfo.getPassword()== null){  
			json = "{success:false}";
			response.getWriter().write(json);
			response.sendRedirect("index.jsp?msg=400");
		}else{
			json = "{success:true,data:"+ExtHelper.getJsonFromBean(sinfo)+"}";
			response.getWriter().write(json);
			session.setAttribute("admin", sinfo.getSuname());
			response.sendRedirect("view/main.jsp");
		}
		
	    
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}		

}
