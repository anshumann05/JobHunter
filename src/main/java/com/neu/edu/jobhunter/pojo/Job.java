package com.neu.edu.jobhunter.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Jobs")
public class Job {

	@Id
	@GeneratedValue
	@Column(name = "Job_ID", nullable = false, unique = true)
	private long jobID;

	@Column(name = "Job_Name")
	private String jobName;

	@Column(name = "Job_Category")
	private String jobCategory;

	@Column(name = "Job_Description")
	private String jobDescription;
	

	public long getJobID() {
		return jobID;
	}

	public void setJobID(long jobID) {
		this.jobID = jobID;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	@ManyToOne(optional = false,fetch=FetchType.EAGER)
	@JoinColumn(name = "user_id",nullable = false)
	private Employer employer;

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

}
