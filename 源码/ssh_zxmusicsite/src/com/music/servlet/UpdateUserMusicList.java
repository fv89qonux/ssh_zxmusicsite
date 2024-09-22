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

import com.music.dao.IUserMusicListDao;
import com.music.model.UserMusicList;

/**
 * Servlet implementation class UpdateAndAddUserMusicList
 */
@WebServlet("/UpdateUserMusicList")
public class UpdateUserMusicList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserMusicListDao userMusicListDao;
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
    public UpdateUserMusicList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userMusicListName=request.getParameter("userMusicListName");
		String userMusicListid=request.getParameter("userMusicListid");
		UserMusicList userMusicList;
		PrintWriter out =response.getWriter();
		try {
			userMusicList = userMusicListDao.getUserMusicListById(Integer.parseInt(userMusicListid));
			
			
			
			userMusicList.setId(Integer.parseInt(userMusicListid));
			userMusicList.setName(userMusicListName);
			userMusicListDao.uodateUserMusicList(userMusicList);
			out.write("1");
		
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
