package com.neu.edu.jobhunter.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "Experience")
public class Experience {

	@Id
	@GeneratedValue
	@Column(name = "expereince_id", unique = true, nullable = false)
	private long experienceID;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "designation")
	private String designation;

	@JoinColumn(name = "user_id")
	@ManyToOne(optional = false,cascade = CascadeType.ALL)
	private Person person;

	public Experience() {

	}

	public long getExperienceID() {
		return experienceID;
	}

	public void setExperienceID(long experienceID) {
		this.experienceID = experienceID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
