package com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity  
@Table(name = "tbl_franchise_district")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
@JsonInclude(Include.NON_NULL)
public class FranchiseDistrict { //franchise

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="district_franchise_id") 
	private int districtFranchiseId;
	
	@Column(name ="full_name") 
	private String fullName;
	
	@Column(name ="franchise_firm_name") 
	private String franchiseFirmName;
		
	@Column(name ="franchise_address") 
	private String franchiseAddress;
	
	@Column(name ="franchise_contact_no") 
	private long franchiseContactNo;
	
	@Column(name ="franchise_whatsapp_no") 
	private long franchiseWhatsappNo; 
	
	@Column(name ="franchise_email_id") 
	private String franchiseEmailId;
	
	@Column(name ="bank_name") 
	private String bankName;
	
	@Column(name ="ifsc_code") 
	private String ifscCode;  
	
	@Column(name ="account_number") 
	private String accountNumber;
	
	@Column(name ="pan_card_number") 
	private String panCardNumber;
	
	@Column(name ="aadhar_card_number") 
	private String aadharCardNumber;
			
	@Column(name ="is_active") 
	private int isActive;

	@Column(name ="franchise_commision") 
	private int franchiseCommision;
	
	@Column(name ="pincode") 
	private String pincode;
	
	@Column(name ="district_franchise_payment_id") 
	private String districtFranchisePaymentId;
	
	@Column(name ="district_franchise_registration_datetime") 
	private String districtFranchiseRegistrationDatetime;
		
	@Column(name ="registration_amount") 
	private double registrationAmount;
	
	@Column(name ="district_franchise_password") 
	private String districtFranchisePassword;
	
	@Column(name ="national_franchise_id") 
	private int nationalFranchiseId;
	
	@Transient
	private String nationalFranchiseName;
	
	@Column(name ="referral_id") 
	private long referralId;
	
	@Column(name ="percent_benefit") 
	private double percentBenefit;
	
	@Column(name ="referral_role") 
	private String referralRole;
	
	@Column(name ="state_code") 
	private int stateCode;
	
	@Transient
	private String stateName;
	
	@Transient
	private int categoryId;
		
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getReferralRole() {
		return referralRole;
	}

	public void setReferralRole(String referralRole) {
		this.referralRole = referralRole;
	}
	
	public long getReferralId() {
		return referralId;
	}

	public void setReferralId(long referralId) {
		this.referralId = referralId;
	}

	public double getPercentBenefit() {
		return percentBenefit;
	}

	public void setPercentBenefit(double percentBenefit) {
		this.percentBenefit = percentBenefit;
	}

	public String getNationalFranchiseName() {
		return nationalFranchiseName;
	}

	public void setNationalFranchiseName(String nationalFranchiseName) {
		this.nationalFranchiseName = nationalFranchiseName;
	}

	public int getNationalFranchiseId() {
		return nationalFranchiseId;
	}

	public void setNationalFranchiseId(int nationalFranchiseId) {
		this.nationalFranchiseId = nationalFranchiseId;
	}

	public int getDistrictFranchiseId() {
		return districtFranchiseId;
	}

	public void setDistrictFranchiseId(int districtFranchiseId) {
		this.districtFranchiseId = districtFranchiseId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFranchiseFirmName() {
		return franchiseFirmName;
	}

	public void setFranchiseFirmName(String franchiseFirmName) {
		this.franchiseFirmName = franchiseFirmName;
	}

	public String getFranchiseAddress() {
		return franchiseAddress;
	}

	public void setFranchiseAddress(String franchiseAddress) {
		this.franchiseAddress = franchiseAddress;
	}

	public long getFranchiseContactNo() {
		return franchiseContactNo;
	}

	public void setFranchiseContactNo(long franchiseContactNo) {
		this.franchiseContactNo = franchiseContactNo;
	}

	public long getFranchiseWhatsappNo() {
		return franchiseWhatsappNo;
	}

	public void setFranchiseWhatsappNo(long franchiseWhatsappNo) {
		this.franchiseWhatsappNo = franchiseWhatsappNo;
	}

	public String getFranchiseEmailId() {
		return franchiseEmailId;
	}

	public void setFranchiseEmailId(String franchiseEmailId) {
		this.franchiseEmailId = franchiseEmailId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPanCardNumber() {
		return panCardNumber;
	}

	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}

	public String getAadharCardNumber() {
		return aadharCardNumber;
	}

	public void setAadharCardNumber(String aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getFranchiseCommision() {
		return franchiseCommision;
	}

	public void setFranchiseCommision(int franchiseCommision) {
		this.franchiseCommision = franchiseCommision;
	}
	
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getDistrictFranchisePaymentId() {
		return districtFranchisePaymentId;
	}

	public void setDistrictFranchisePaymentId(String districtFranchisePaymentId) {
		this.districtFranchisePaymentId = districtFranchisePaymentId;
	}

	public String getDistrictFranchiseRegistrationDatetime() {
		return districtFranchiseRegistrationDatetime;
	}

	public void setDistrictFranchiseRegistrationDatetime(String districtFranchiseRegistrationDatetime) {
		this.districtFranchiseRegistrationDatetime = districtFranchiseRegistrationDatetime;
	}

	public double getRegistrationAmount() {
		return registrationAmount;
	}

	public void setRegistrationAmount(double registrationAmount) {
		this.registrationAmount = registrationAmount;
	}

	public String getDistrictFranchisePassword() {
		return districtFranchisePassword;
	}

	public void setDistrictFranchisePassword(String districtFranchisePassword) {
		this.districtFranchisePassword = districtFranchisePassword;
	}

	@Override
	public String toString() {
		return "FranchiseDistrict [districtFranchiseId=" + districtFranchiseId + ", fullName=" + fullName
				+ ", franchiseFirmName=" + franchiseFirmName + ", franchiseAddress=" + franchiseAddress
				+ ", franchiseContactNo=" + franchiseContactNo + ", franchiseWhatsappNo=" + franchiseWhatsappNo
				+ ", franchiseEmailId=" + franchiseEmailId + ", bankName=" + bankName + ", ifscCode=" + ifscCode
				+ ", accountNumber=" + accountNumber + ", panCardNumber=" + panCardNumber + ", aadharCardNumber="
				+ aadharCardNumber + ", isActive=" + isActive + ", franchiseCommision=" + franchiseCommision
				+ ", pincode=" + pincode + ", districtFranchisePaymentId=" + districtFranchisePaymentId
				+ ", districtFranchiseRegistrationDatetime=" + districtFranchiseRegistrationDatetime
				+ ", registrationAmount=" + registrationAmount + ", districtFranchisePassword="
				+ districtFranchisePassword + "]";
	}
}