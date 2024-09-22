package com.music.dao;

import java.util.Map;

import com.comm.Page;
import com.music.model.User;
public interface IUserDao {
	
	public Page queryUserByPage(final int pageNo, final int pageSize,
			final Map<String, String> queryParam);
	
	public void delete(int id)throws Exception;
	
	public User queryById(int id)throws Exception;
	
	public void update(User user)throws Exception;
	
	public void add(User user)throws Exception;
	
	public User login(String name)throws Exception;
	
	public boolean getUserByName(String name)throws Exception;
	
}
