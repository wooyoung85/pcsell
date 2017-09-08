package com.pcsell.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 20, unique=true)
	private String userId;
	private String password;
	private String name;
	private String email;
	
	public User() {
	}

	public User(String userId, String password, String name, String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public boolean matchId(Long newId){
		if(newId == null){
			return false;
		}	
		return true;
	}
	
	public boolean matchPassword(String newPassword){
		if(newPassword == null){
			return false;
		}	
		return true;
	}
	
	public void update(User updateUser) {
		this.password =updateUser.password;
		this.name =updateUser.name;
		this.email =updateUser.email;
	}

}
