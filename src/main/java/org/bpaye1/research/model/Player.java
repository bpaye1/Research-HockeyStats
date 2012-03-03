package org.bpaye1.research.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="PLAYER")
public class Player {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@NotEmpty(message="Last Name is required.")
	@Column(name="LAST_NAME")
	private String lastName;
	
	@NotEmpty(message="First Name is required.")
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@NotEmpty(message="Email is required.")
	@Column(name="EMAIL")
	private String email;
	
	@NotEmpty(message="Date of Birth is required.")
	@Column(name="DATE_OF_BIRTH")
	private Date dateOfBirth;
	
	@Embedded
	private Address address;
	
	@Column(name="PHONE_NUMBER")
	private Long phoneNumber;
	
	@NotNull(message="Jersey Number is required.")
	@Column(name="JERSEY_NUMBER")
	private Integer jerseyNumber;
	
	@Column(name="STATUS")
	private String status;
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getJerseyNumber() {
		return jerseyNumber;
	}
	
	public void setJerseyNumber(Integer jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}
}
