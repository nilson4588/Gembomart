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
@Table(name = "tbl_delivery")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
@JsonInclude(Include.NON_NULL)
public class Delivery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="delivery_id") 
	private int deliveryId;
		
	@Column(name ="delivery_full_name") 
	private String deliveryFullName;
	
	@Column(name ="delivery_dob") 
	private String deliveryDob;
	
	@Column(name ="delivery_email_id") 
	private String deliveryEmailId;
	
	@Column(name ="delivery_address") 
	private String deliveryAddress;
	
	@Column(name ="delivery_contact_no") 
	private long deliveryContactNo;
	
	@Column(name ="delivery_whatsapp_no") 
	private long deliveryWhatsappNo;
			 
	@Column(name ="delivery_pincode") 
	private int deliveryPincode;
	
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
	
	@Column(name ="local_franchise_id") 
	private int localFranchiseId;
	
	@Column(name ="is_active") 
	private int isActive;
	
	@Column(name ="delivery_payment_id") 
	private String deliveryPaymentId;
	
	@Column(name ="delivery_registration_datetime") 
	private String deliveryRegistrationDatetime;
		
	@Column(name ="registration_amount") 
	private double registrationAmount;
	
	@Column(name ="delivery_password") 
	private String deliveryPassword;
	
	@Column(name ="delivery_commision") 
	private int deliveryCommision;
	
	@Transient
	private String localFranchiseName;
	
	@Column(name ="referral_id") 
	private long referralId;
	
	@Column(name ="percent_benefit") 
	private double percentBenefit;
	
	@Column(name ="referral_role") 
	private String referralRole;
			
	@Column(name ="token") 
	private String token;	
	
	@Column(name ="delivery_type") 
	private String deliveryType;	
	
	@Column(name ="delivery_package") 
	private int deliveryPackage;
	 
	
	
	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public int getDeliveryPackage() {
		return deliveryPackage;
	}

	public void setDeliveryPackage(int deliveryPackage) {
		this.deliveryPackage = deliveryPackage;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	
	public String getLocalFranchiseName() {
		return localFranchiseName;
	}

	public void setLocalFranchiseName(String localFranchiseName) {
		this.localFranchiseName = localFranchiseName;
	}

	public int getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getDeliveryFullName() {
		return deliveryFullName;
	}

	public void setDeliveryFullName(String deliveryFullName) {
		this.deliveryFullName = deliveryFullName;
	}

	public String getDeliveryDob() {
		return deliveryDob;
	}

	public void setDeliveryDob(String deliveryDob) {
		this.deliveryDob = deliveryDob;
	}

	public String getDeliveryEmailId() {
		return deliveryEmailId;
	}

	public void setDeliveryEmailId(String deliveryEmailId) {
		this.deliveryEmailId = deliveryEmailId;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public long getDeliveryContactNo() {
		return deliveryContactNo;
	}

	public void setDeliveryContactNo(long deliveryContactNo) {
		this.deliveryContactNo = deliveryContactNo;
	}

	public long getDeliveryWhatsappNo() {
		return deliveryWhatsappNo;
	}

	public void setDeliveryWhatsappNo(long deliveryWhatsappNo) {
		this.deliveryWhatsappNo = deliveryWhatsappNo;
	}

	public int getDeliveryPincode() {
		return deliveryPincode;
	}

	public void setDeliveryPincode(int deliveryPincode) {
		this.deliveryPincode = deliveryPincode;
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

	public int getLocalFranchiseId() {
		return localFranchiseId;
	}

	public void setLocalFranchiseId(int localFranchiseId) {
		this.localFranchiseId = localFranchiseId;
	}
	
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getDeliveryCommision() {
		return deliveryCommision;
	}

	public void setDeliveryCommision(int deliveryCommision) {
		this.deliveryCommision = deliveryCommision;
	}

	public String getDeliveryPaymentId() {
		return deliveryPaymentId;
	}

	public void setDeliveryPaymentId(String deliveryPaymentId) {
		this.deliveryPaymentId = deliveryPaymentId;
	}

	public String getDeliveryRegistrationDatetime() {
		return deliveryRegistrationDatetime;
	}

	public void setDeliveryRegistrationDatetime(String deliveryRegistrationDatetime) {
		this.deliveryRegistrationDatetime = deliveryRegistrationDatetime;
	}

	public double getRegistrationAmount() {
		return registrationAmount;
	}

	public void setRegistrationAmount(double registrationAmount) {
		this.registrationAmount = registrationAmount;
	}

	public String getDeliveryPassword() {
		return deliveryPassword;
	}

	public void setDeliveryPassword(String deliveryPassword) {
		this.deliveryPassword = deliveryPassword;
	}

	@Override
	public String toString() {
		return "Delivery [deliveryId=" + deliveryId + ", deliveryFullName=" + deliveryFullName + ", deliveryDob="
				+ deliveryDob + ", deliveryEmailId=" + deliveryEmailId + ", deliveryAddress=" + deliveryAddress
				+ ", deliveryContactNo=" + deliveryContactNo + ", deliveryWhatsappNo=" + deliveryWhatsappNo
				+ ", deliveryPincode=" + deliveryPincode + ", bankName=" + bankName + ", ifscCode=" + ifscCode
				+ ", accountNumber=" + accountNumber + ", panCardNumber=" + panCardNumber + ", aadharCardNumber="
				+ aadharCardNumber + ", localFranchiseId=" + localFranchiseId + ", isActive=" + isActive
				+ ", deliveryPaymentId=" + deliveryPaymentId + ", deliveryRegistrationDatetime="
				+ deliveryRegistrationDatetime + ", registrationAmount=" + registrationAmount + ", deliveryPassword="
				+ deliveryPassword + ", deliveryCommision=" + deliveryCommision + "]";
	}
}