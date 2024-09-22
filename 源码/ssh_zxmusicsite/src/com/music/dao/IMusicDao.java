package com.music.dao;

import java.util.Map;

import com.comm.Page;
import com.music.model.Music;
import com.music.model.User;

public interface IMusicDao {
	public Page queryMusicByPage(final int pageNo, final int pageSize,
			final Map<String, String> queryParam);
	
	public void delete(int id)throws Exception;
	
	public Music queryById(int id)throws Exception;
	
	public void update(Music music)throws Exception;
	
	public void add(Music music)throws Exception;
}
