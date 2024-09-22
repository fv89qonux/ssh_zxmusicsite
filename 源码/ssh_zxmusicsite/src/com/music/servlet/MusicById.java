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

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.comm.CommonsResponse;
import com.google.gson.Gson;
import com.music.dao.IMusicDao;
import com.music.dao.IUserDao;
import com.music.model.Music;
import com.music.model.User;

/**
 * Servlet implementation class UserById
 */
@WebServlet("/MusicById")
public class MusicById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IMusicDao musicDao;
       
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
        ServletContext servletContext = this.getServletContext();  
          
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);  
          
        musicDao = (IMusicDao)ctx.getBean("musicDao");  
	}
    public IMusicDao getMusicDao() {
		return musicDao;
	}
	public void setMusicDao(IMusicDao musicDao) {
		this.musicDao = musicDao;
	}
	/**
     * @see HttpServlet#HttpServlet()
     */
    public MusicById() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		CommonsResponse myResp = new CommonsResponse();
		ObjectMapper om = new ObjectMapper();
		
		String id=request.getParameter("id");
		Music music=new Music();
		try {
			music=musicDao.queryById(Integer.parseInt(id));
			
			myResp.setData(music);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setContentType("text/json"); 
		resp.setCharacterEncoding("UTF-8"); 
		
		om.writeValue(resp.getOutputStream(), myResp); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
