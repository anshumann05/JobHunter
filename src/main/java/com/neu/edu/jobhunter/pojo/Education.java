package com.neu.edu.jobhunter.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "Education")
public class Education {
	@Id
	@GeneratedValue
	// @GenericGenerator(name = "generator", strategy = "foreign", parameters =
	// @Parameter(name = "property", value = "person"))
	@Column(name = "education_id", unique = true, nullable = false)
	private long educationId;

	@Column(name = "college_name")
	private String collegeName;

	@Column(name = "degree_level")
	private String degreeLevel;

	@ManyToOne(optional = false,cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Person person;

	/*
	 * private long userID; public long getUserID() { return userID; }
	 * 
	 * public void setUserID(long userID) { this.userID = userID; }
	 * 
	 * 
	 */

	public Education(String collegeName, String degreeLevel) {
		this.collegeName = collegeName;
		this.degreeLevel = degreeLevel;
	}

	public Education() {
	}

	public long getEducationId() {
		return educationId;
	}

	public void setEducationId(long educationId) {
		this.educationId = educationId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getDegreeLevel() {
		return degreeLevel;
	}

	public void setDegreeLevel(String degreeLevel) {
		this.degreeLevel = degreeLevel;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Education(Person person) {
		this.person = person;

	}

}
