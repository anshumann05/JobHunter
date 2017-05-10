package com.neu.edu.jobhunter.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="role")
public class Role {
	@Id
	@GenericGenerator(name="myGenerator",strategy="foreign",parameters = @Parameter(name = "property", value = "user"))
	@GeneratedValue(generator = "myGenerator")
	@Column(name="roleID",unique=true,nullable=false)
	private long roleID;
	
	@Column(name="roleName")
	private String roleName;
	
	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn(name="roleID")
	private User user;
	
	public long getRoleID() {
		return roleID;
	}
	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	

}
