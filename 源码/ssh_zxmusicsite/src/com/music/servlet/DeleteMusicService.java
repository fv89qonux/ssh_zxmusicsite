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

import com.music.dao.IMusicDao;
import com.music.dao.IUserDao;

/**
 * Servlet implementation class DeleteUserService
 */
@WebServlet("/DeleteMusicService")
public class DeleteMusicService extends HttpServlet {
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
    public DeleteMusicService() {
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
			musicDao.delete(Integer.parseInt(id));
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
