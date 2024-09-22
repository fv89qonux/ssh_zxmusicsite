package com.music.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

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
import com.music.dao.IUserMusicListDao;
import com.music.model.User;
import com.music.model.UserMusicList;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateAndAddUser")
public class UpdateAndAddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
private IUserDao userDao;

private IUserMusicListDao userMusicListDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	
	
	public IUserMusicListDao getUserMusicListDao() {
		return userMusicListDao;
	}
	public void setUserMusicListDao(IUserMusicListDao userMusicListDao) {
		this.userMusicListDao = userMusicListDao;
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
        ServletContext servletContext = this.getServletContext();  
          
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);  
          
        userDao = (IUserDao)ctx.getBean("userDao"); 
        
        userMusicListDao = (IUserMusicListDao)ctx.getBean("userMusicListDao"); 
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAndAddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");

		String userName=request.getParameter("userName");
		
		
		
		
		String passWord=request.getParameter("passWord");
		User user=new User();
		PrintWriter out=response.getWriter();
		if(Integer.parseInt(type)==2){
			String id=request.getParameter("id");
			try {
				user = userDao.queryById(Integer.parseInt(id));
				user.setUserName(userName);
				user.setPassWord(passWord);
				userDao.update(user);
				
				
				
			} catch (Exception e) {
				out.write("1");
				e.printStackTrace();
			}
			out.write("2");
		}else if(Integer.parseInt(type)==1){
			
			
			try {
				boolean b=userDao.getUserByName(userName);
				if(!b){
					user.setUserName(userName);
					user.setPassWord(passWord);
					UserMusicList userMusicList = new UserMusicList();
					userMusicList.setName("±¾µØ¸èµ¥");
					
					
					
					user.getUserMusicLists().add(userMusicList);
					
					userDao.add(user);
					
					userMusicListDao.addUserMusicList(userMusicList, user.getId());
					out.write("2");
				}else{
					out.write("3");
				}
				
			} catch (Exception e) {
				out.write("1");
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
