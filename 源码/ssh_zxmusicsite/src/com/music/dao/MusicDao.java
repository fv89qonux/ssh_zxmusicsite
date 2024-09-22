package com.music.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comm.Page;
import com.lovo.framework.persistence.IBaseDao;
import com.music.model.Music;
import com.music.model.User;


@Transactional(readOnly = false)
public class MusicDao implements IMusicDao {
	private IBaseDao dao;
	@Override
	public Page queryMusicByPage(int pageNo, int pageSize, Map<String, String> queryParam) {
		return  dao.getHibernateTemplate().execute(new HibernateCallback<Page>() {

			@SuppressWarnings("unchecked")
			@Override
			public Page doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setPageSize(pageSize);
				
				Map paramMap=new HashMap();
//				String hql="from Music u where 1 = 1 ";
				String sql="select * from music where 1=1";
				String sql1="";
				if(!queryParam.get("songlist").equals("0")&&!queryParam.get("songlist").equals("")){
					
					sql1+=" and id in(select mid from muisclist_music mm where mm.lid="+queryParam.get("songlist")+")";
					
				}
				if(!queryParam.get("musicName").equals("") ){
					sql1+=" and musicName like '%"+queryParam.get("musicName")+"%'";
				}
				sql1 +=" order by id desc";
//				if(!queryParam.get("musicName").equals("") ){
//					hql += " and u.musicName like :musicName ";
//					paramMap.put("musicName", "%" + queryParam.get("musicName") + "%");
//				}
//				
//				hql += " order by id desc";
//				
				@SuppressWarnings("unchecked")
				List list=session.createSQLQuery(sql+sql1).addScalar("id",Hibernate.INTEGER).addScalar("musicName",Hibernate.STRING).addScalar("musicUrl",Hibernate.STRING).addScalar("musicer",Hibernate.STRING).
						setFirstResult((pageNo-1)*pageSize).setProperties(paramMap).
						setMaxResults(pageSize).list();
				
				List<Music> mps=new ArrayList<Music>(); 
				 for(Iterator iterator = list.iterator();iterator.hasNext();){  
			            //每个集合元素都是一个数组，数组元素师person_id,person_name,person_age三列值  
			            Object[] objects = (Object[]) iterator.next();  
			            Music music=new Music();
			            music.setId((int) objects[0]);
			            music.setMusicName((String) objects[1]);
			            music.setMusicUrl((String) objects[2]);
			            music.setMusicer((String) objects[3]);
			            mps.add(music);
			        }  
				page.setDatas(mps);
				
				Number num=(Number) session.createSQLQuery("select count(*) from music where 1=1"+sql1).setProperties(paramMap).
						uniqueResult();
				int countnum=num.intValue();
				
				//总条数
				page.setRowcounts(countnum);
					
				if(countnum%pageSize==0){
					page.setPageCount(countnum/pageSize);
				}else{
					page.setPageCount(countnum/pageSize+1);
				}
				return page;
			}
		});	
	}
	
	public IBaseDao getDao() {
		return dao;
	}

	public void setDao(IBaseDao dao) {
		this.dao = dao;
	}

	@Override
	public void delete(int id) throws Exception {
		dao.deleteEntityById(Music.class, id);
	}
	@Override
	public Music queryById(int id) {
		return (Music) dao.queryEntityById(Music.class, id);
	}

	@Override
	public void update(Music music) {
		dao.updateEntity(music);
	}
	@Override
	public void add(Music music) {
		dao.addEntity(music);
	}

}
