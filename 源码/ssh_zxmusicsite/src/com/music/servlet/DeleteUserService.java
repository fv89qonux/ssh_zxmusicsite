package com.music.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.music.dao.IUserDao;
import com.music.model.User;

/**
 * Servlet implementation class DeleteUserService
 */
@WebServlet("/DeleteUserService")
public class DeleteUserService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserDao userDao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
        ServletContext servletContext = this.getServletContext();  
          
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);  
          
        userDao = (IUserDao)ctx.getBean("userDao");  
	}
    public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	/**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		PrintWriter out=response.getWriter();
		try {
//			userDao.delete(Integer.parseInt(id));
			User user = userDao.queryById(Integer.parseInt(id));
			user.setState(1);
			
			userDao.update(user);
			
		} catch (NumberFormatException e) {
			out.write("1");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write("2");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
