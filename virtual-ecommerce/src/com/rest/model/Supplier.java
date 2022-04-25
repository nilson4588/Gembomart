package com.rest.model;

import java.util.Arrays;
import java.util.List;

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
@Table(name = "tbl_supplier")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
@JsonInclude(Include.NON_NULL)
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="supplier_id") 
	private int supplierId;
		
	@Column(name ="supplier_full_name") 
	private String supplierFullName;
	
	@Column(name ="supplier_dob") 
	private String supplierDob;
	
	@Column(name ="supplier_email_id") 
	private String supplierEmailId;
	
	@Column(name ="supplier_address") 
	private String supplierAddress;
	
	@Column(name ="supplier_contact_no") 
	private long supplierContactNo;
	
	@Column(name ="supplier_whatsapp_no") 
	private long supplierWhatsappNo;
			
	@Column(name ="supplier_pincode") 
	private int supplierPincode;
	
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

	@Column(name ="supplier_products") 
	private String supplierProducts;
	
	@Column(name ="local_franchise_id") 
	private int localFranchiseId;
	
	@Column(name ="is_active") 
	private int isActive;
	
	@Column(name ="supplier_payment_id") 
	private String supplierPaymentId;
	
	@Column(name ="supplier_registration_datetime") 
	private String supplierRegistrationDatetime;
		
	@Column(name ="registration_amount") 
	private double registrationAmount;
	
	@Column(name ="supplier_password") 
	private String supplierPassword;
		
	@Transient
	private String[] supplierProductList;
	
	@Transient
	private String orderStatus;
	
	@Transient
	private List<SupplierOrder> orderItemData;
	
	@Transient
	private String localFranchiseName;
		
	@Column(name ="referral_id") 
	private long referralId;
	
	@Column(name ="percent_benefit") 
	private double percentBenefit;
	
	@Column(name ="referral_role") 
	private String referralRole;
	
	@Column(name ="shop_name") 
	private String shopName;
	
	@Column(name ="latitude") 
	private String latitude;
	
	@Column(name ="longitude") 
	private String longitude;
	
	@Transient
	private String categoryName;
	
		
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
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

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierFullName() {
		return supplierFullName;
	}

	public void setSupplierFullName(String supplierFullName) {
		this.supplierFullName = supplierFullName;
	}

	public String getSupplierDob() {
		return supplierDob;
	}

	public void setSupplierDob(String supplierDob) {
		this.supplierDob = supplierDob;
	}

	public String getSupplierEmailId() {
		return supplierEmailId;
	}

	public void setSupplierEmailId(String supplierEmailId) {
		this.supplierEmailId = supplierEmailId;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public long getSupplierContactNo() {
		return supplierContactNo;
	}

	public void setSupplierContactNo(long supplierContactNo) {
		this.supplierContactNo = supplierContactNo;
	}

	public long getSupplierWhatsappNo() {
		return supplierWhatsappNo;
	}

	public void setSupplierWhatsappNo(long supplierWhatsappNo) {
		this.supplierWhatsappNo = supplierWhatsappNo;
	}

	public int getSupplierPincode() {
		return supplierPincode;
	}

	public void setSupplierPincode(int supplierPincode) {
		this.supplierPincode = supplierPincode;
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

	public String[] getSupplierProductList() {
		return supplierProductList;
	}

	public void setSupplierProductList(String[] supplierProductList) {
		this.supplierProductList = supplierProductList;
	}
	
	public String getSupplierProducts() {
		return supplierProducts;
	}

	public void setSupplierProducts(String supplierProducts) {
		this.supplierProducts = supplierProducts;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<SupplierOrder> getOrderItemData() {
		return orderItemData;
	}

	public void setOrderItemData(List<SupplierOrder> orderItemData) {
		this.orderItemData = orderItemData;
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

	public String getSupplierPaymentId() {
		return supplierPaymentId;
	}

	public void setSupplierPaymentId(String supplierPaymentId) {
		this.supplierPaymentId = supplierPaymentId;
	}

	public String getSupplierRegistrationDatetime() {
		return supplierRegistrationDatetime;
	}

	public void setSupplierRegistrationDatetime(String supplierRegistrationDatetime) {
		this.supplierRegistrationDatetime = supplierRegistrationDatetime;
	}
	
	public double getRegistrationAmount() {
		return registrationAmount;
	}

	public void setRegistrationAmount(double registrationAmount) {
		this.registrationAmount = registrationAmount;
	}

	public String getSupplierPassword() {
		return supplierPassword;
	}

	public void setSupplierPassword(String supplierPassword) {
		this.supplierPassword = supplierPassword;
	}

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierFullName=" + supplierFullName + ", supplierDob="
				+ supplierDob + ", supplierEmailId=" + supplierEmailId + ", supplierAddress=" + supplierAddress
				+ ", supplierContactNo=" + supplierContactNo + ", supplierWhatsappNo=" + supplierWhatsappNo
				+ ", supplierPincode=" + supplierPincode + ", bankName=" + bankName + ", ifscCode=" + ifscCode
				+ ", accountNumber=" + accountNumber + ", panCardNumber=" + panCardNumber + ", aadharCardNumber="
				+ aadharCardNumber + ", supplierProducts=" + supplierProducts + ", localFranchiseId=" + localFranchiseId
				+ ", isActive=" + isActive + ", supplierPaymentId=" + supplierPaymentId
				+ ", supplierRegistrationDatetime=" + supplierRegistrationDatetime + ", registrationAmount="
				+ registrationAmount + ", supplierPassword=" + supplierPassword + ", supplierProductList="
				+ Arrays.toString(supplierProductList) + ", orderStatus=" + orderStatus + ", orderItemData="
				+ orderItemData + "]";
	}
}