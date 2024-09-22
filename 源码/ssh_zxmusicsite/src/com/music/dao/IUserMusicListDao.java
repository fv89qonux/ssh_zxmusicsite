package com.music.dao;

import java.util.List;
import java.util.Map;

import com.comm.Page;
import com.music.model.UserMusicList;

public interface IUserMusicListDao {
	public List<UserMusicList> getUserMusicList(int userID)throws Exception;
	public void addUserMusicList(UserMusicList userMusicList,int userid)throws Exception;
	public void uodateUserMusicList(UserMusicList userMusicList)throws Exception;
	public Page queryMusicByPage(final int pageNo, final int pageSize,
			final Map<String, String> queryParam);
	public void CollectionMusic(int musicId,int musicListId)throws Exception;

	public void DeleteMusic(int musicId,int musicListId)throws Exception;
	
	public UserMusicList getUserMusicListById(int id)throws Exception;
	public boolean getMusicbylist(int musicId,int musicListId)throws Exception;
}
