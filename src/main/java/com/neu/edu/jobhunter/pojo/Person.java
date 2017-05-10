package com.neu.edu.jobhunter.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Person")
@PrimaryKeyJoinColumn(name = "user_Id")

public class Person extends User {

	@Column(name = "First_Name")
	private String firstName;

	@Column(name = "Last_Name")
	private String lastName;

	@Column(name = "Person_Email", unique = true)
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Person(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;

	}

	Person() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private Set<Education> educationList;

	public Set<Education> getEducationList() {
		if (educationList == null) {
			return new HashSet<Education>();
		}
		return educationList;
	}

	public void setEducationList(Set<Education> educationList) {
		this.educationList = educationList;
	}

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private Set<Experience> experienceList;

	public Set<Experience> getExperienceList() {
		return experienceList;
	}

	public void setExperienceList(Set<Experience> experienceList) {
		this.experienceList = experienceList;
	}

	
}
