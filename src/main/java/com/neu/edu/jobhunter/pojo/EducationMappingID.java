package com.neu.edu.jobhunter.pojo;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class EducationMappingID implements java.io.Serializable{
	private Education education;
	private Applicant applicant;

	@ManyToOne
	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	@ManyToOne
	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		EducationMappingID that = (EducationMappingID) o;

		if (education != null ? !education.equals(that.education) : that.education != null)
			return false;
		if (applicant != null ? !applicant.equals(that.applicant) : that.applicant != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (education != null ? education.hashCode() : 0);
		result = 31 * result + (applicant != null ? applicant.hashCode() : 0);
		return result;
	}

}
