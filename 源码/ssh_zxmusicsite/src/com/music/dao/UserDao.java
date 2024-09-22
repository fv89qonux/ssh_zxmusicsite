package com.music.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.comm.Page;
import com.lovo.framework.persistence.IBaseDao;
import com.music.model.Admin;
import com.music.model.User;

@Transactional(readOnly = false)
public class UserDao implements IUserDao{

	private IBaseDao dao;
		
	@Override
	public Page queryUserByPage(int pageNo, int pageSize, Map<String, String> queryParam) {
		return  dao.getHibernateTemplate().execute(new HibernateCallback<Page>() {

			@SuppressWarnings("unchecked")
			@Override
			public Page doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Page page = new Page();
				page.setPageNo(pageNo);
				page.setPageSize(pageSize);
				
				Map paramMap=new HashMap();
				String hql="from User u  where 1 = 1 and u.state = 0  ";
				
				if(!queryParam.get("userName").equals("") ){
					hql += " and u.userName like :userName ";
					paramMap.put("userName", "%" + queryParam.get("userName") + "%");
				}
				
				hql += " order by id desc";
				
				@SuppressWarnings("unchecked")
				List<User> mps=session.createQuery(hql).
						setFirstResult((pageNo-1)*pageSize).setProperties(paramMap).
						setMaxResults(pageSize).list();
				
				page.setDatas(mps);
				
				Number num=(Number) session.createQuery("select count(*) "+hql).setProperties(paramMap).
						uniqueResult();
				int countnum=num.intValue();
				
				//×ÜÌõÊý
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
		dao.deleteEntityById(User.class, id);
		
		
	}
	@Override
	public User queryById(int id) {
		return (User) dao.queryEntityById(User.class, id);
	}

	@Override
	public void update(User user) {
		System.out.println("-------------"+user.getId());
		dao.updateEntity(user);
	}
	@Override
	public void add(User user) {
		dao.addEntity(user);
	}

	@Override
	public User login(String name) {
		List<User> mgrs = dao.queryEntitys(" from User a where a.userName = ? and a.state = 0", new Object[]{ name });
		
		if (mgrs.size() > 0 ) {
			return mgrs.get(0);
		}
		return null;
	}
	
	@Override
	public boolean getUserByName(String name) throws Exception {

		return  dao.getHibernateTemplate().execute(new HibernateCallback<Boolean>() {

			@SuppressWarnings("unchecked")
			@Override
			public Boolean doInHibernate(Session session) throws HibernateException,
					SQLException {
				String sql="select count(*) from User u where u.userName='"+name+"' and u.state=0";
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
}
