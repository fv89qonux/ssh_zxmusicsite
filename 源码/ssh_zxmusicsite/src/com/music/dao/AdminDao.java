package com.music.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lovo.framework.persistence.IBaseDao;
import com.music.model.Admin;

public class AdminDao implements IAdminDao{
	
	private IBaseDao dao;
	
	@Override
	public Admin login(String name){
		
		List<Admin> mgrs = dao.queryEntitys(" from Admin a where a.adminName = ? ", new Object[]{ name });
		
		if (mgrs.size() > 0 ) {
			return mgrs.get(0);
		}
		return null;
	}

	public IBaseDao getDao() {
		return dao;
	}

	public void setDao(IBaseDao dao) {
		this.dao = dao;
	}
	
	

}
