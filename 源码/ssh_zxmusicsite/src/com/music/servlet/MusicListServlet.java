package com.music.servlet;

import java.io.IOException;
import java.util.HashMap;
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
import com.music.dao.IAdminDao;
import com.music.dao.IMusicDao;
import com.music.dao.IUserDao;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/MusicListServlet")
public class MusicListServlet extends HttpServlet {
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
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MusicListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageNo =Integer.parseInt(request.getParameter("pno"));
		
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		String songlist=request.getParameter("songlist");
		
		String musicName = request.getParameter("musicName");
		
		
		
		Map<String, String> map = new HashMap<>();
		map.put("musicName", musicName);
		map.put("songlist", songlist);
		
		Page page = musicDao.queryMusicByPage(pageNo, pageSize, map);
		
		CommonsResponse myResp = new CommonsResponse();
		ObjectMapper om = new ObjectMapper();
		
		
		response.setContentType("text/json"); 
		response.setCharacterEncoding("UTF-8"); 
		myResp.setData(page);
		
		om.writeValue(response.getOutputStream(), myResp); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public IMusicDao getMusicDao() {
		return musicDao;
	}

	public void setMusicDao(IMusicDao musicDao) {
		this.musicDao = musicDao;
	}


}
