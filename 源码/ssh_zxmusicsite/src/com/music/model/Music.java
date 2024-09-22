package com.music.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 
 * ×÷Õß£º
 * ÃèÊö£º¸èÇú
 *
 */
@Entity
@Table(name="music")
public class Music  implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="musicName")
	private String musicName;
	
	@Column(name="musicer")
	private String musicer;
	
	@Column(name="musicUrl")
	private String musicUrl;
	
	@ManyToMany(targetEntity = UserMusicList.class, fetch = FetchType.LAZY)  
	@JoinTable(
            name = "muisclist_music",
            joinColumns =@JoinColumn(name = "mid"),
            inverseJoinColumns = @JoinColumn(name = "lid")
    )  
	private List<UserMusicList> userMusicLists = new ArrayList<UserMusicList>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getMusicer() {
		return musicer;
	}

	public void setMusicer(String musicer) {
		this.musicer = musicer;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public List<UserMusicList> getUserMusicLists() {
		return userMusicLists;
	}

	public void setUserMusicLists(List<UserMusicList> userMusicLists) {
		this.userMusicLists = userMusicLists;
	}
	
	

}
