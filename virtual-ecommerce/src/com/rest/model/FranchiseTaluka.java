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
@Table(name = "tbl_franchise_taluka")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
@JsonInclude(Include.NON_NULL)
public class FranchiseTaluka {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="taluka_franchise_id") 
	private int talukaFranchiseId;
	
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
	
	@Column(name ="district_franchise_id") 
	private int districtFranchiseId;
	
	@Column(name ="pincode") 
	private String pincode;
	
	@Column(name ="taluka_franchise_payment_id") 
	private String talukaFranchisePaymentId;
	
	@Column(name ="taluka_franchise_registration_datetime") 
	private String talukaFranchiseRegistrationDatetime;
		
	@Column(name ="registration_amount") 
	private double registrationAmount;
	
	@Column(name ="taluka_franchise_password") 
	private String talukaFranchisePassword;
	
	@Transient 
	private String districtFranchiseName;
	
	@Column(name ="referral_id") 
	private long referralId;
	
	@Column(name ="percent_benefit") 
	private double percentBenefit;
	
	@Column(name ="referral_role") 
	private String referralRole;
	
	@Column(name ="district_code") 
	private int districtCode;
	
	@Transient 
	private int categoryId;

	@Transient
	private String districtName;
		
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
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

	
	public String getDistrictFranchiseName() {
		return districtFranchiseName;
	}

	public void setDistrictFranchiseName(String districtFranchiseName) {
		this.districtFranchiseName = districtFranchiseName;
	}

	public int getTalukaFranchiseId() {
		return talukaFranchiseId;
	}

	public void setTalukaFranchiseId(int talukaFranchiseId) {
		this.talukaFranchiseId = talukaFranchiseId;
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

	public int getDistrictFranchiseId() {
		return districtFranchiseId;
	}

	public void setDistrictFranchiseId(int districtFranchiseId) {
		this.districtFranchiseId = districtFranchiseId;
	}
	
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getTalukaFranchisePaymentId() {
		return talukaFranchisePaymentId;
	}

	public void setTalukaFranchisePaymentId(String talukaFranchisePaymentId) {
		this.talukaFranchisePaymentId = talukaFranchisePaymentId;
	}

	public String getTalukaFranchiseRegistrationDatetime() {
		return talukaFranchiseRegistrationDatetime;
	}

	public void setTalukaFranchiseRegistrationDatetime(String talukaFranchiseRegistrationDatetime) {
		this.talukaFranchiseRegistrationDatetime = talukaFranchiseRegistrationDatetime;
	}

	public double getRegistrationAmount() {
		return registrationAmount;
	}

	public void setRegistrationAmount(double registrationAmount) {
		this.registrationAmount = registrationAmount;
	}

	public String getTalukaFranchisePassword() {
		return talukaFranchisePassword;
	}

	public void setTalukaFranchisePassword(String talukaFranchisePassword) {
		this.talukaFranchisePassword = talukaFranchisePassword;
	}

	@Override
	public String toString() {
		return "FranchiseTaluka [talukaFranchiseId=" + talukaFranchiseId + ", fullName=" + fullName
				+ ", franchiseFirmName=" + franchiseFirmName + ", franchiseAddress=" + franchiseAddress
				+ ", franchiseContactNo=" + franchiseContactNo + ", franchiseWhatsappNo=" + franchiseWhatsappNo
				+ ", franchiseEmailId=" + franchiseEmailId + ", bankName=" + bankName + ", ifscCode=" + ifscCode
				+ ", accountNumber=" + accountNumber + ", panCardNumber=" + panCardNumber + ", aadharCardNumber="
				+ aadharCardNumber + ", isActive=" + isActive + ", franchiseCommision=" + franchiseCommision
				+ ", districtFranchiseId=" + districtFranchiseId + ", pincode=" + pincode
				+ ", talukaFranchisePaymentId=" + talukaFranchisePaymentId + ", talukaFranchiseRegistrationDatetime="
				+ talukaFranchiseRegistrationDatetime + ", registrationAmount=" + registrationAmount
				+ ", talukaFranchisePassword=" + talukaFranchisePassword + "]";
	}
}