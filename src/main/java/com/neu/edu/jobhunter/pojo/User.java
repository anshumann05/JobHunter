package com.neu.edu.jobhunter.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.JOINED)

public class User {

	@Id
	@GeneratedValue
	@Column(name = "User_Id", nullable = false, unique = true)
	private long userID;

	@Column(name = "User_Name", unique = true)
	private String userName;

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	@Column(name = "User_Password")
	private String password;

	// public String getEmail() {
	// return email;
	// }
	//
	// public void setEmail(String email) {
	// this.email = email;
	// }
	//
	// @Column(name = "email")
	// private String email;

	// @OneToOne(fetch = FetchType.LAZY,mappedBy="user")
	@Column(name = "userRole")
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public User() {

	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
