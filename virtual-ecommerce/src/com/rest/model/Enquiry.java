package com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity  
@Table(name = "tbl_enquiry")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
@JsonInclude(Include.NON_NULL)
public class Enquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="enquiry_id") 
	private int enquiryId;
	
	@Column(name ="enquiry_user_type") 
	private String enquiryUserType;
	
	@Column(name ="name") 
	private String name;
	
	@Column(name ="contact_number") 
	private String contactNumber;
	
	@Column(name ="whatsapp_number") 
	private String whatsappNumber;
	
	@Column(name ="email_id") 
	private String emailId;
	
	@Column(name ="city") 
	private String city;
	
	@Column(name ="pincode") 
	private String pincode;
	
	@Column(name ="record_datetime") 
	private String recordDatetime;
	
	@Column(name="is_active")
	private int isActive;

	public int getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getWhatsappNumber() {
		return whatsappNumber;
	}

	public void setWhatsappNumber(String whatsappNumber) {
		this.whatsappNumber = whatsappNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getRecordDatetime() {
		return recordDatetime;
	}

	public void setRecordDatetime(String recordDatetime) {
		this.recordDatetime = recordDatetime;
	}
	
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getEnquiryUserType() {
		return enquiryUserType;
	}

	public void setEnquiryUserType(String enquiryUserType) {
		this.enquiryUserType = enquiryUserType;
	}

	@Override
	public String toString() {
		return "Enquiry [enquiryId=" + enquiryId + ", enquiryUserType=" + enquiryUserType + ", name=" + name
				+ ", contactNumber=" + contactNumber + ", whatsappNumber=" + whatsappNumber + ", emailId=" + emailId
				+ ", city=" + city + ", pincode=" + pincode + ", recordDatetime=" + recordDatetime + ", isActive="
				+ isActive + "]";
	}	
}