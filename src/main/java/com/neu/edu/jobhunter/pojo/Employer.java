package com.neu.edu.jobhunter.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Employer")
@PrimaryKeyJoinColumn(name = "User_Id")

public class Employer extends User {

	@Column(name = "Employer_Name")
	private String employerName;

	@Column(name = "Employer_Category")
	private String employerCategory;

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getEmployerCategory() {
		return employerCategory;
	}

	public void setEmployerCategory(String employerCategory) {
		this.employerCategory = employerCategory;
	}

	public Employer(String employerName, String employerCategory) {
		this.employerName = employerName;
		this.employerCategory = employerCategory;
	}

	Employer() {

	}
	
	
	@OneToMany(mappedBy = "employer", cascade = CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval=true)
	private Set<Job> jobList;

	public Set<Job> getJobList() {
		if (jobList == null) {
			return new HashSet<Job>();
		}
		return jobList;
	}

	public void setJobList(Set<Job> jobList) {
		this.jobList = jobList;
	}
}
