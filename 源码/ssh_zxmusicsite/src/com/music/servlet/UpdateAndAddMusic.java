package com.music.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.comm.CommonsResponse;
import com.music.dao.IMusicDao;
import com.music.dao.IUserDao;
import com.music.model.Music;
import com.music.model.User;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateAndAddMusic")
public class UpdateAndAddMusic extends HttpServlet {
	
	private String TEMP_FOLDER = "D:/test/";
	
	private static final long serialVersionUID = 1L;
	private IMusicDao musicDao;
	public IMusicDao getMusicDao() {
		return musicDao;
	}
	public void setMusicDao(IMusicDao musicDao) {
		this.musicDao = musicDao;
	}
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
    public UpdateAndAddMusic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String root=request.getSession().getServletContext().getRealPath("/upload");
		
		System.out.println("type : " + request.getParameter("type"));
        
        System.out.println("musicname : " + request.getParameter("musicname"));
        
        System.out.println("musicer : " + request.getParameter("musicer"));
        
		
		System.out.println(URLDecoder.decode(request.getParameter("musicname"), "UTF-8"));
        
        String type = request.getParameter("type");
        String musicname = URLDecoder.decode(request.getParameter("musicname"), "UTF-8");
        String musicer = URLDecoder.decode(request.getParameter("musicer"), "UTF-8");
        
        CommonsResponse myResp = new CommonsResponse();
		ObjectMapper om = new ObjectMapper();
        
        request.setCharacterEncoding("utf-8"); // ���ñ���  
        Music music=new Music();
	if(Integer.parseInt(type)==2){
		String id=request.getParameter("id");
		
			
			// ��ô����ļ���Ŀ����  
	        DiskFileItemFactory factory = new DiskFileItemFactory();  
	       
	        factory.setRepository(new File(TEMP_FOLDER));
	        // ���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��  
	        factory.setSizeThreshold(1024 * 1024);  
	  
	        // ��ˮƽ��API�ļ��ϴ�����  
	        ServletFileUpload upload = new ServletFileUpload(factory);  
			
			try {
				
				music = musicDao.queryById(Integer.parseInt(id));
				music.setMusicName(musicname);
				music.setMusicer(musicer);
				
	            // �ύ��������Ϣ�������list����  
	            // ����ζ�ſ����ϴ�����ļ�  
	            // ��������֯����  
	            List<FileItem> list = upload.parseRequest(request); 
	            
//	            System.out.println(list.get(0).getSize());
//	            System.out.println(list.get(0).getName());
	            if(list.get(0).getSize() > 0){
	            	if (list != null && list.size() > 0) {
		                // ����������
		                for (FileItem item : list) {
		                	
		                    // �����ڱ��е��ֶ�
		                    if (!item.isFormField()) {
		                        //���ﴦ���ļ���������û�á�����������������
		                        System.out.println(new String(item.getName().getBytes("utf-8"),"iso-8859-1"));
		                        String fileName = new File(item.getName()).getName();
		                        
		                        music.setMusicUrl(fileName);
		                        
		                        String filePath = root + File.separator + fileName;
		                        File storeFile = new File(filePath);
		                        // �ڿ���̨����ļ����ϴ�·��
		                        System.out.println(filePath);
		                        // �����ļ���Ӳ��
		                        item.write(storeFile);
		                        
		                        
		                    }
		                }
		            }
	            }else{
	            	System.out.println("δ�ϴ��ļ�");
	            }
	            
	            musicDao.update(music);
	            
	            myResp.setCode(1);
                
                //ע����룬��Ȼ����ǰ�˻����룡����
                response.setContentType("text/json"); 
                response.setCharacterEncoding("UTF-8"); 
        		
        		om.writeValue(response.getOutputStream(), myResp); 
	         
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }
			
		
		
	}else if(Integer.parseInt(type)==1){
		music.setMusicName(musicname);
		music.setMusicer(musicer);
		
		
	 
		
        // ��ô����ļ���Ŀ����  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
       
        factory.setRepository(new File(TEMP_FOLDER));
        // ���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��  
        factory.setSizeThreshold(1024 * 1024);  
  
        // ��ˮƽ��API�ļ��ϴ�����  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        
        try {  
            // �ύ��������Ϣ�������list����  
            // ����ζ�ſ����ϴ�����ļ�  
            // ��������֯����  
            List<FileItem> list = upload.parseRequest(request); 
            
            if(list.get(0).getSize() > 0){
            
	            if (list != null && list.size() > 0) {
	                // ����������
	                for (FileItem item : list) {
	                	
	                    // �����ڱ��е��ֶ�
	                    if (!item.isFormField()) {
	                        //���ﴦ���ļ���������û�á�����������������
	                        System.out.println(new String(item.getName().getBytes("utf-8"),"iso-8859-1"));
	                        String fileName = new File(item.getName()).getName();
	                        
	                        music.setMusicUrl(fileName);
	                        
	                        String filePath = root + File.separator + fileName;
	                        File storeFile = new File(filePath);
	                        // �ڿ���̨����ļ����ϴ�·��
	                        System.out.println(filePath);
	                        // �����ļ���Ӳ��
	                        item.write(storeFile);
	                        
	                        
	                    }
	                }
	            }
            }else{
            	music.setMusicUrl("");
            }
            
            musicDao.add(music);
            
            myResp.setCode(1);
            
            //ע����룬��Ȼ����ǰ�˻����룡����
            response.setContentType("text/json"); 
            response.setCharacterEncoding("UTF-8"); 
    		
    		om.writeValue(response.getOutputStream(), myResp);
         
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
	}
		
		
		
		
		
		
		
//		String type=request.getParameter("type");
//
//		String musicname=request.getParameter("musicname");
//		String musicer=request.getParameter("musicer");
//		Music music=new Music();
//		PrintWriter out=response.getWriter();
//		if(Integer.parseInt(type)==2){
//			String id=request.getParameter("id");
//			try {
//				music = musicDao.queryById(Integer.parseInt(id));
//				music.setMusicName(musicname);
//				music.setMusicer(musicer);
//				musicDao.update(music);
//			} catch (Exception e) {
//				out.write("1");
//				e.printStackTrace();
//			}
//			out.write("2");
//		}else if(Integer.parseInt(type)==1){
//			music.setMusicName(musicname);
//			music.setMusicer(musicer);
//			try {
//				musicDao.add(music);
//			} catch (Exception e) {
//				out.write("1");
//				e.printStackTrace();
//			}
//			out.write("2");
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
