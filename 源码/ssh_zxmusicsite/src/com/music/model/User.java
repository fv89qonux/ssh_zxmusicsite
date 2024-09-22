package com.music.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * 作者：
 * 描述：用户
 *
 */
@Entity
@Table(name="user")
@JsonIgnoreProperties(value={"userMusicLists"})
public class User  implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="passWord")
	private String passWord;
	
	@Column(name="state")
	private int state = 0;
	
	@OneToMany(mappedBy="user")
	@JoinColumn(name="user_id")
	private List<UserMusicList> userMusicLists = new ArrayList<UserMusicList>();
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public List<UserMusicList> getUserMusicLists() {
		return userMusicLists;
	}

	public void setUserMusicLists(List<UserMusicList> userMusicLists) {
		this.userMusicLists = userMusicLists;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	
	
	
	
	
	
	

}
