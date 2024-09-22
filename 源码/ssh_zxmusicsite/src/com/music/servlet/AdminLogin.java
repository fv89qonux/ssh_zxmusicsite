package com.music.servlet;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.music.dao.AdminDao;
import com.music.dao.IAdminDao;
import com.music.model.Admin;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	
	
	private IAdminDao adminDao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
        ServletContext servletContext = this.getServletContext();  
          
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);  
          
        adminDao = (IAdminDao)ctx.getBean("adminDao");  
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
//		IAdminDao adminDao = new AdminDao();
		
		String adminName = request.getParameter("mname");
		
		String adminPassword = request.getParameter("mpwd");
		
		Admin admin = adminDao.login(adminName);
		if(admin != null){
			
			if(admin.getAdminPassword().equals(adminPassword)){
				System.out.println("111111111111111");
				request.getSession().setAttribute("admin_1", admin);
				
				request.getRequestDispatcher("context.jsp").forward(request, response);
				
				return;
			}else{
				System.out.println("222222222222");
				request.setAttribute("msg", "密码错误");
			}
		}else{
			System.out.println("33333333333333");
			request.setAttribute("msg", "没有此管理员");
		}
		
		
		System.out.println(request.getParameter("mname"));
		
		System.out.println(request.getParameter("mpwd"));
		
		
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public IAdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	

}
