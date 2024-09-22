package com.music.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.comm.Page;
import com.music.dao.IUserDao;
import com.music.dao.IUserMusicListDao;
import com.music.model.UserMusicList;

/**
 * Servlet implementation class UserMusicListServlet
 */
@WebServlet("/UserMusicListServlet")
public class UserMusicListServlet extends HttpServlet {
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
    public UserMusicListServlet(){
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("userID");

		try {
			List<UserMusicList> list=userMusicListDao.getUserMusicList(Integer.parseInt(id));
			CommonsResponse myResp = new CommonsResponse();
			ObjectMapper om = new ObjectMapper();
			
			
			response.setContentType("text/json"); 
			response.setCharacterEncoding("UTF-8"); 
			myResp.setData(list);
			
			om.writeValue(response.getOutputStream(), myResp); 
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
