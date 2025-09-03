package com.gopi.EventDrivenArch;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="Company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Company Information
	@Column(nullable = false)
	private String companyName;

	@Column
	private String dba;

	@Column
	private String companyUrl;

	// Address Information
	@Column(nullable = false)
	private String street1;

	@Column
	private String street2;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String state;

	@Column(nullable = false)
	private String zipCode;

	@Column(nullable = false)
	private String country;

	// Contact Information
	@Column(nullable = false)
	private String contactName;

	@Column(nullable = false)
	private String contactTitle;

	@Column(nullable = false)
	private String contactEmail;

	@Column(nullable = false)
	private String contactPhone;

	// Metadata
	@Column
	private LocalDateTime submissionTime;

	// Constructors
	public Company() {}

	public Company(String companyName, String dba, String companyUrl,
				   String street1, String street2, String city, String state,
				   String zipCode, String country, String contactName,
				   String contactTitle, String contactEmail, String contactPhone) {
		this.companyName = companyName;
		this.dba = dba;
		this.companyUrl = companyUrl;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
		this.contactName = contactName;
		this.contactTitle = contactTitle;
		this.contactEmail = contactEmail;
		this.contactPhone = contactPhone;
		this.submissionTime = LocalDateTime.now();
	}

	// Getters and Setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getCompanyName() { return companyName; }
	public void setCompanyName(String companyName) { this.companyName = companyName; }

	public String getDba() { return dba; }
	public void setDba(String dba) { this.dba = dba; }

	public String getCompanyUrl() { return companyUrl; }
	public void setCompanyUrl(String companyUrl) { this.companyUrl = companyUrl; }

	public String getStreet1() { return street1; }
	public void setStreet1(String street1) { this.street1 = street1; }

	public String getStreet2() { return street2; }
	public void setStreet2(String street2) { this.street2 = street2; }

	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }

	public String getState() { return state; }
	public void setState(String state) { this.state = state; }

	public String getZipCode() { return zipCode; }
	public void setZipCode(String zipCode) { this.zipCode = zipCode; }

	public String getCountry() { return country; }
	public void setCountry(String country) { this.country = country; }

	public String getContactName() { return contactName; }
	public void setContactName(String contactName) { this.contactName = contactName; }

	public String getContactTitle() { return contactTitle; }
	public void setContactTitle(String contactTitle) { this.contactTitle = contactTitle; }

	public String getContactEmail() { return contactEmail; }
	public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

	public String getContactPhone() { return contactPhone; }
	public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

	public LocalDateTime getSubmissionTime() { return submissionTime; }
	public void setSubmissionTime(LocalDateTime submissionTime) { this.submissionTime = submissionTime; }

	@Override
	public String toString() {
		return "Company{" +
				"id=" + id +
				", companyName='" + companyName + '\'' +
				", contactName='" + contactName + '\'' +
				", contactEmail='" + contactEmail + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				'}';
	}
}