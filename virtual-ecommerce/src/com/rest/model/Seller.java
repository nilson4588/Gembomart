package com.rest.model;

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
@Table(name = "tbl_seller")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
@JsonInclude(Include.NON_NULL)
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="seller_id") 
	private int sellerId;
		
	@Column(name ="seller_full_name") 
	private String sellerFullName;
	
	@Column(name ="seller_dob") 
	private String sellerDob;
	
	@Column(name ="seller_email_id") 
	private String sellerEmailId;
	
	@Column(name ="seller_address") 
	private String sellerAddress;
	
	@Column(name ="seller_contact_no") 
	private long sellerContactNo;
	
	@Column(name ="seller_whatsapp_no") 
	private long sellerWhatsappNo;
			 
	@Column(name ="seller_pincode") 
	private int sellerPincode;
	
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
	
	@Column(name ="seller_code") 
	private String sellerCode;
	
	@Column(name ="local_franchise_id") 
	private int localFranchiseId;
	
	@Column(name ="is_active") 
	private int isActive;
	
	@Column(name ="seller_payment_id") 
	private String sellerPaymentId;
	
	@Column(name ="seller_registration_datetime") 
	private String sellerRegistrationDatetime;
		
	@Column(name ="registration_amount") 
	private double registrationAmount;
	
	@Column(name ="seller_password") 
	private String sellerPassword;
	
	@Column(name ="category_id") 
	private int categoryId;
	
	@Column(name ="dm_package") 
	private int dmPackage;
	
	@Column(name ="gst_number") 
	private String gstNumber;
	
	@Column(name ="banner_image") 
	private String bannerImage;
			
	@Transient
	private List<SellerOrder> orderItemData;
	
	@Transient
	private List<ProductListByShop> productListByShop;
	
	@Transient
	private String localFranchiseName;
	
	@Transient
	private String shopImage;
	
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
	
	@Column(name ="token") 
	private String token;
		
	@Transient
	private String categoryName;
	

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public int getDmPackage() {
		return dmPackage;
	}

	public void setDmPackage(int dmPackage) {
		this.dmPackage = dmPackage;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getShopImage() {
		return shopImage;
	}

	public void setShopImage(String shopImage) {
		this.shopImage = shopImage;
	}

		
	public List<ProductListByShop> getProductListByShop() {
		return productListByShop;
	}

	public void setProductListByShop(List<ProductListByShop> productListByShop) {
		this.productListByShop = productListByShop;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

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


	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerFullName() {
		return sellerFullName;
	}

	public void setSellerFullName(String sellerFullName) {
		this.sellerFullName = sellerFullName;
	}

	public String getSellerDob() {
		return sellerDob;
	}

	public void setSellerDob(String sellerDob) {
		this.sellerDob = sellerDob;
	}

	public String getSellerEmailId() {
		return sellerEmailId;
	}

	public void setSellerEmailId(String sellerEmailId) {
		this.sellerEmailId = sellerEmailId;
	}

	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public long getSellerContactNo() {
		return sellerContactNo;
	}

	public void setSellerContactNo(long sellerContactNo) {
		this.sellerContactNo = sellerContactNo;
	}

	public long getSellerWhatsappNo() {
		return sellerWhatsappNo;
	}

	public void setSellerWhatsappNo(long sellerWhatsappNo) {
		this.sellerWhatsappNo = sellerWhatsappNo;
	}

	public int getSellerPincode() {
		return sellerPincode;
	}

	public void setSellerPincode(int sellerPincode) {
		this.sellerPincode = sellerPincode;
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
	
	public List<SellerOrder> getOrderItemData() {
		return orderItemData;
	}

	public void setOrderItemData(List<SellerOrder> orderItemData) {
		this.orderItemData = orderItemData;
	}
	
	public String getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}

	public int getLocalFranchiseId() {
		return localFranchiseId;
	}

	public void setLocalFranchiseId(int localFranchiseId) {
		this.localFranchiseId = localFranchiseId;
	}

	public String getSellerPaymentId() {
		return sellerPaymentId;
	}

	public void setSellerPaymentId(String sellerPaymentId) {
		this.sellerPaymentId = sellerPaymentId;
	}

	public String getSellerRegistrationDatetime() {
		return sellerRegistrationDatetime;
	}

	public void setSellerRegistrationDatetime(String sellerRegistrationDatetime) {
		this.sellerRegistrationDatetime = sellerRegistrationDatetime;
	}

	public double getRegistrationAmount() {
		return registrationAmount;
	}

	public void setRegistrationAmount(double registrationAmount) {
		this.registrationAmount = registrationAmount;
	}

	public String getSellerPassword() {
		return sellerPassword;
	}

	public void setSellerPassword(String sellerPassword) {
		this.sellerPassword = sellerPassword;
	}

	@Override
	public String toString() {
		return "Seller [sellerId=" + sellerId + ", sellerFullName=" + sellerFullName + ", sellerDob=" + sellerDob
				+ ", sellerEmailId=" + sellerEmailId + ", sellerAddress=" + sellerAddress + ", sellerContactNo="
				+ sellerContactNo + ", sellerWhatsappNo=" + sellerWhatsappNo + ", sellerPincode=" + sellerPincode
				+ ", bankName=" + bankName + ", ifscCode=" + ifscCode + ", accountNumber=" + accountNumber
				+ ", panCardNumber=" + panCardNumber + ", aadharCardNumber=" + aadharCardNumber + ", sellerCode="
				+ sellerCode + ", localFranchiseId=" + localFranchiseId + ", isActive=" + isActive
				+ ", sellerPaymentId=" + sellerPaymentId + ", sellerRegistrationDatetime=" + sellerRegistrationDatetime
				+ ", registrationAmount=" + registrationAmount + ", sellerPassword=" + sellerPassword
				+ ", orderItemData=" + orderItemData + "]";
	}
}