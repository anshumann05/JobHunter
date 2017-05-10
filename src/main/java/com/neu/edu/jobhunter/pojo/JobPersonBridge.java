package com.neu.edu.jobhunter.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "JobBridge")

public class JobPersonBridge {

	@Id
	@GeneratedValue
	@Column(name = "job_bridge_id", nullable = false, unique = true)
	private long jobBridgingID;

	@JoinColumn(name = "user_id")
	private Person person;

	@JoinColumn(name = "job_id")
	private Job job;
	

	public JobPersonBridge()
	{
		
	}


	public long getJobBridgingID() {
		return jobBridgingID;
	}


	public void setJobBridgingID(long jobBridgingID) {
		this.jobBridgingID = jobBridgingID;
	}


	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}


	public Job getJob() {
		return job;
	}


	public void setJob(Job job) {
		this.job = job;
	}
	
}
