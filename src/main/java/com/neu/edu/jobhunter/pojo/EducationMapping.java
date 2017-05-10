package com.neu.edu.jobhunter.pojo;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Education_bridge")
@AssociationOverrides({ @AssociationOverride(name = "pk.applicant", joinColumns = @JoinColumn(name = "applicantId")),
		@AssociationOverride(name = "pk.education", joinColumns = @JoinColumn(name = "educationID")) })
public class EducationMapping implements java.io.Serializable {

	private EducationMappingID pk = new EducationMappingID();
	private Date startedOn;
	private String finishedBy;

	public EducationMapping() {

	}

	@EmbeddedId
	public EducationMappingID getPk() {
		return pk;
	}

	public void setPk(EducationMappingID pk) {
		this.pk = pk;
	}

	@Transient
	public Applicant getApplicant() {
		return getPk().getApplicant();
	}

	public void setApplicant(Applicant applicant) {
		getPk().setApplicant(applicant);
	}

	@Transient
	public Education getEducation() {
		return getPk().getEducation();
	}

	public void setEducation(Education education) {
		getPk().setEducation(education);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "started_date", nullable = false, length = 10)
	public Date getStartedOn() {
		return startedOn;
	}

	public void setStartedOn(Date startedOn) {
		this.startedOn = startedOn;
	}

	@Column(name = "finished_date", nullable = false, length = 10)

	public String getFinishedBy() {
		return finishedBy;
	}

	public void setFinishedBy(String finishedBy) {
		this.finishedBy = finishedBy;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		EducationMapping that = (EducationMapping) o;

		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}

}
