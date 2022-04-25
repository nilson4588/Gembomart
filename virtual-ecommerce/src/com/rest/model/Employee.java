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
@Table(name = "tbl_employee")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
@JsonInclude(Include.NON_NULL)
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="employee_id") 
	private int employeeId;
		
	@Column(name ="employee_name") 
	private String employeeName;
	
	@Column(name ="address") 
	private String address;
	
	@Column(name ="contact_number") 
	private String contactNumber;
	
	@Column(name ="whatsapp_number") 
	private String whatsappNumber;
	
	@Column(name ="email_id") 
	private String emailId;
	
	@Column(name ="aadhar_card_number") 
	private String aadharCardNumber;
	
	@Column(name ="created_datetime") 
	private String createdDatetime;
	
	@Column(name ="password") 
	private String password;
	
	@Column(name ="updated_datetime") 
	private String updatedDatetime;
	
	@Column(name ="is_active") 
	private int isActive;
	
	@Column(name ="registration_amount") 
	private double registrationAmount;
	
	@Column(name ="payment_id") 
	private String paymentId;
	
	@Column(name ="common_id") 
	private String commonId;
	
	@Column(name ="franchise_name") 
	private String franchiseName;
	
	@Column(name ="employee_designation") 
	private String employeeDesignation;
	
	@Column(name ="state_code") 
	private int stateCode;
	
	@Column(name ="district_code") 
	private int districtCode;
	
	@Column(name ="taluka_code") 
	private int talukaCode;
	
	@Column(name ="working_area") 
	private String workingArea;
	
	@Column(name ="employee_photo") 
	private String employeePhoto;
	
	
			
	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public int getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}

	public int getTalukaCode() {
		return talukaCode;
	}

	public void setTalukaCode(int talukaCode) {
		this.talukaCode = talukaCode;
	}

	public String getWorkingArea() {
		return workingArea;
	}

	public void setWorkingArea(String workingArea) {
		this.workingArea = workingArea;
	}

	public String getEmployeePhoto() {
		return employeePhoto;
	}

	public void setEmployeePhoto(String employeePhoto) {
		this.employeePhoto = employeePhoto;
	}

	public double getRegistrationAmount() {
		return registrationAmount;
	}

	public void setRegistrationAmount(double registrationAmount) {
		this.registrationAmount = registrationAmount;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getCommonId() {
		return commonId;
	}

	public void setCommonId(String commonId) {
		this.commonId = commonId;
	}

	public String getFranchiseName() {
		return franchiseName;
	}

	public void setFranchiseName(String franchiseName) {
		this.franchiseName = franchiseName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getAadharCardNumber() {
		return aadharCardNumber;
	}

	public void setAadharCardNumber(String aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}

	public String getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(String updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}
	
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", address=" + address
				+ ", contactNumber=" + contactNumber + ", whatsappNumber=" + whatsappNumber + ", emailId=" + emailId
				+ ", aadharCardNumber=" + aadharCardNumber + ", createdDatetime=" + createdDatetime + ", password="
				+ password + ", updatedDatetime=" + updatedDatetime + ", isActive=" + isActive + ", registrationAmount="
				+ registrationAmount + ", paymentId=" + paymentId + ", commonId=" + commonId + ", franchiseName="
				+ franchiseName + ", employeeDesignation=" + employeeDesignation + ", stateCode=" + stateCode
				+ ", districtCode=" + districtCode + ", talukaCode=" + talukaCode + ", workingArea=" + workingArea
				+ ", employeePhoto=" + employeePhoto + "]";
	}
}