package com.neu.edu.jobhunter.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "Address")
public class Address {
	@GenericGenerator(name = "myGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "applicant"))
	@Id
	@GeneratedValue(generator = "myGenerator")
	@Column(name = "addressId", unique = true, nullable = false)
	private long addressID;

	@Column(name = "street")
	private String street;

	@Column(name = "zipCode")
	private String zipCode;

	@Column(name = "City")
	private String City;

	public long getAddressID() {
		return addressID;
	}

	public void setAddressID(long addressID) {
		this.addressID = addressID;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

}
