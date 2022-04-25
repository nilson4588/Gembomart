package com.rest.model;

public class ReferralCommision {

	private String mobileNo;
	private int    customerState;
	private int    customerCity;
	private int    customerTaluka;
	private int    orderId;
	private String orderDate;
	private int    productId;
	private String productTitle;
	private double productPrice;
	private double per15Price;	
	private int    sellerId;
	private String shopName;
	private String referralId;
	private String referralRole;
	private double percentReferralCommision;
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public int getCustomerState() {
		return customerState;
	}
	public void setCustomerState(int customerState) {
		this.customerState = customerState;
	}
	public int getCustomerCity() {
		return customerCity;
	}
	public void setCustomerCity(int customerCity) {
		this.customerCity = customerCity;
	}
	public int getCustomerTaluka() {
		return customerTaluka;
	}
	public void setCustomerTaluka(int customerTaluka) {
		this.customerTaluka = customerTaluka;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public double getPer15Price() {
		return per15Price;
	}
	public void setPer15Price(double per15Price) {
		this.per15Price = per15Price;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getReferralId() {
		return referralId;
	}
	public void setReferralId(String referralId) {
		this.referralId = referralId;
	}
	public String getReferralRole() {
		return referralRole;
	}
	public void setReferralRole(String referralRole) {
		this.referralRole = referralRole;
	}
	public double getPercentReferralCommision() {
		return percentReferralCommision;
	}
	public void setPercentReferralCommision(double percentReferralCommision) {
		this.percentReferralCommision = percentReferralCommision;
	}
}
