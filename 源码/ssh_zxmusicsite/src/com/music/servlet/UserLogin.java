package com.music.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.comm.CommonsResponse;
import com.music.dao.IUserDao;
import com.music.model.Admin;
import com.music.model.User;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IUserDao userDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
        ServletContext servletContext = this.getServletContext();  
          
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);  
          
        userDao = (IUserDao)ctx.getBean("userDao");  
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CommonsResponse myResp = new CommonsResponse();
		ObjectMapper om = new ObjectMapper();
		
		String userName = request.getParameter("userName");
		
		String passWord = request.getParameter("passWord");
	
		User user=null;
		try {
			user = user= userDao.login(userName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
		if(user != null){
			
			user.setUserMusicLists(null);
			

			if(user.getPassWord().equals(passWord)){
				myResp.setData(user);
				myResp.setCode(1);
				
			}else{
				
				myResp.setCode(2);
				
			}
		}else{
			myResp.setCode(3);
		}
		
		response.setContentType("text/json"); 
		response.setCharacterEncoding("UTF-8"); 
		
		om.writeValue(response.getOutputStream(), myResp); 
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
