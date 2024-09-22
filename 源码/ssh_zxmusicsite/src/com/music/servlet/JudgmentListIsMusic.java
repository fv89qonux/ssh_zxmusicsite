package com.music.servlet;

import java.io.IOException;

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

/**
 * Servlet implementation class JudgmentListIsMusic
 */
@WebServlet("/JudgmentListIsMusic")
public class JudgmentListIsMusic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserMusicListDao userMusicListDao;
	private IUserDao userDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	/**
     * @see HttpServlet#HttpServlet()
     */
    @Override
 	public void init(ServletConfig config) throws ServletException {
 		// TODO Auto-generated method stub
 		super.init(config);
 		
         ServletContext servletContext = this.getServletContext();  
           
         WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);  
           
         userMusicListDao = (IUserMusicListDao)ctx.getBean("userMusicListDao");  
         userDao = (IUserDao)ctx.getBean("userDao");  
 	}
    public IUserMusicListDao getUserMusicListDao() {
		return userMusicListDao;
	}
	public void setUserMusicListDao(IUserMusicListDao userMusicListDao) {
		this.userMusicListDao = userMusicListDao;
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JudgmentListIsMusic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			boolean b=userMusicListDao.getMusicbylist(1, 1);
			System.err.println("----"+b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
