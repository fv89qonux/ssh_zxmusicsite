package com.music.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Transactional;

import com.comm.Page;
import com.lovo.framework.persistence.IBaseDao;
import com.music.model.Music;
import com.music.model.User;
import com.music.model.UserMusicList;



@Transactional(readOnly = false)
public class UserMusicListDao implements IUserMusicListDao {
	private IBaseDao dao;
	
	public IBaseDao getDao() {
		return dao;
	}

	public void setDao(IBaseDao dao) {
		this.dao = dao;
	}

	@Override
	public List<UserMusicList> getUserMusicList(int userID) throws Exception {
		 List<UserMusicList> list=dao.queryEntitys("from UserMusicList u where u.user.id=?", new Object[]{ userID });
		 List<UserMusicList> list1=new ArrayList<UserMusicList>();
		 for (UserMusicList userMusicList : list) {
			 UserMusicList userMusicList1=new UserMusicList();
			 userMusicList1.setId(userMusicList.getId());
			 userMusicList1.setName(userMusicList.getName());
			 list1.add(userMusicList1);
			/* List<Music> list2=userMusicList.getMusics();
			 for (Music music : list2) {
				 music.setUserMusicLists(null);
			}
			 userMusicList.setMusics(list2);
			 userMusicList.getUser().setUserMusicLists(null);*/
		}
		return list1;
	}

	@Override
	public void addUserMusicList(UserMusicList userMusicList,int userid) throws Exception {
		
		User user=(User) dao.queryEntityById(User.class, userid);
		userMusicList.setUser(user);
		dao.addEntity(userMusicList);
	}

	@Override
	public void uodateUserMusicList(UserMusicList userMusicList) throws Exception {
		
		System.out.println("userMusicList.getId() : " + userMusicList.getId());
		System.out.println("userMusicList.getName() : " + userMusicList.getName());
		
		dao.updateEntity(userMusicList);
		
		System.out.println("--------------");
	}
	
	//得到指定歌单ID的歌曲分页
	@Override
	public Page queryMusicByPage(int pageNo, int pageSize,Map<String, String> queryParam) {
		
		
		
		return  dao.getHibernateTemplate().execute(new HibernateCallback<Page>() {

			
			
			
			@SuppressWarnings("unchecked")
			@Override
			public Page doInHibernate(Session session) throws HibernateException,
					SQLException {
				UserMusicList userMusicList=(UserMusicList) dao.queryEntityById(UserMusicList.class, Integer.parseInt(queryParam.get("musicListID")));
				
				List<Music> list=userMusicList.getMusics();
				
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setPageSize(pageSize);
				List<Music> list1=new ArrayList<>();
				if(list.size()<pageNo*pageSize){
					list1=list.subList((pageNo-1)*pageSize, list.size());
				}else{
					list1=list.subList((pageNo-1)*pageSize, pageNo*pageSize);
				}
				
				
				page.setDatas(list1);
				
				//总条数
				page.setRowcounts(list.size());
					
				if(page.getRowcounts()%pageSize==0){
					page.setPageCount(page.getRowcounts()/pageSize);
				}else{
					page.setPageCount(page.getRowcounts()/pageSize+1);
				}
				return page;
			}
		});
	}

	@Override
	public void CollectionMusic(int musicId, int musicListId) throws Exception {
		UserMusicList musicList=(UserMusicList) dao.queryEntityById(UserMusicList.class, musicListId);
		Music music=(Music) dao.queryEntityById(Music.class, musicId);
		//music.getUserMusicLists().add(musicList);
		musicList.getMusics().add(music);
		dao.updateEntity(musicList);
		
	}

	@Override
	public void DeleteMusic(int musicId, int musicListId) throws Exception {
		UserMusicList userMusicList=(UserMusicList) dao.queryEntityById(UserMusicList.class, musicListId);
		Music music=(Music) dao.queryEntityById(Music.class, musicId);
	
		userMusicList.getMusics().remove(music);
		dao.updateEntity(userMusicList);
	}

	@Override
	public boolean getMusicbylist(int musicId, int musicListId) throws Exception {
		return  dao.getHibernateTemplate().execute(new HibernateCallback<Boolean>() {

			@SuppressWarnings("unchecked")
			@Override
			public Boolean doInHibernate(Session session) throws HibernateException,
					SQLException {
				String sql="select count(*) from muisclist_music m where m.lid="+musicListId+" and m.mid="+musicId;
				@SuppressWarnings("unchecked")
				String i= session.createSQLQuery(sql).uniqueResult().toString();
				System.err.println("i==="+i);
				if(Integer.parseInt(i)!=0){
					return true;
				}else{
					return false;
				}
				
				
				
				
				
			}
		});	
		
	}

	@Override
	public UserMusicList getUserMusicListById(int id) throws Exception {
		UserMusicList list=(UserMusicList) dao.queryEntityById(UserMusicList.class, id);
		return list;
	}

	

}
