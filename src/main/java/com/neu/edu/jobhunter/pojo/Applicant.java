package com.neu.edu.jobhunter.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="applicant")
//@PrimaryKeyJoinColumn(name = "userSerialNumber")

public class Applicant implements Serializable{
	
//	@OneToOne(fetch=FetchType.EAGER, mappedBy="applicant", cascade=CascadeType.ALL)
//	private Address address;
//	
//	@OneToMany(fetch=FetchType.EAGER, mappedBy="applicant", cascade=CascadeType.ALL)
//	private Experience experience;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="applicantId",unique=true,nullable=false)
	private long applicantID;
	
	
	public long getApplicantID() {
		return applicantID;
	}

	public void setApplicantID(long applicantID) {
		this.applicantID = applicantID;
	}

	 
	private Set<EducationMapping> educationMapping = new HashSet<EducationMapping>();

	@OneToMany(fetch=FetchType.LAZY, mappedBy="pk.applicant", cascade=CascadeType.ALL)
	public Set<EducationMapping> getEducationMapping() {
		return educationMapping;
	}

	public void setEducationMapping(Set<EducationMapping> educationMapping) {
		this.educationMapping = educationMapping;
	}

	

	/*public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

//	public Education getEducation() {
//		return education;
//	}
//
//	public void setEducation(Education education) {
//		this.education = education;
//	}
 */
 
	
	
	

}
