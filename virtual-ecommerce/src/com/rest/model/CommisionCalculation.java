package com.rest.model;

public class CommisionCalculation {
	
	private String mobileNo;
	private int    customerState;
	private int    customerCity;
	private int    customerTaluka;
	private int    orderId;
	private String orderDate;
	private int    productId;
	private String productTitle;
	private double productPrice;
	private double purchasePrice;
	private double profit;
	private double talukaFranchiseCommision;
	private double districtFranchiseCommision;
	private double stateFranchiseCommision;
	private double nationalFranchiseCommision;
	private int    sellerId;
	private String shopName;
	
	
	
	
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
	public double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public double getTalukaFranchiseCommision() {
		return talukaFranchiseCommision;
	}
	public void setTalukaFranchiseCommision(double talukaFranchiseCommision) {
		this.talukaFranchiseCommision = talukaFranchiseCommision;
	}
	public double getDistrictFranchiseCommision() {
		return districtFranchiseCommision;
	}
	public void setDistrictFranchiseCommision(double districtFranchiseCommision) {
		this.districtFranchiseCommision = districtFranchiseCommision;
	}
	public double getStateFranchiseCommision() {
		return stateFranchiseCommision;
	}
	public void setStateFranchiseCommision(double stateFranchiseCommision) {
		this.stateFranchiseCommision = stateFranchiseCommision;
	}
	public double getNationalFranchiseCommision() {
		return nationalFranchiseCommision;
	}
	public void setNationalFranchiseCommision(double nationalFranchiseCommision) {
		this.nationalFranchiseCommision = nationalFranchiseCommision;
	}
	
	@Override
	public String toString() {
		return "CommisionCalculation [mobileNo=" + mobileNo + ", customerState=" + customerState + ", customerCity="
				+ customerCity + ", customerTaluka=" + customerTaluka + ", orderId=" + orderId + ", orderDate="
				+ orderDate + ", productId=" + productId + ", productTitle=" + productTitle + ", productPrice="
				+ productPrice + ", purchasePrice=" + purchasePrice + ", profit=" + profit
				+ ", talukaFranchiseCommision=" + talukaFranchiseCommision + ", districtFranchiseCommision="
				+ districtFranchiseCommision + ", stateFranchiseCommision=" + stateFranchiseCommision
				+ ", nationalFranchiseCommision=" + nationalFranchiseCommision + ", sellerId=" + sellerId
				+ ", shopName=" + shopName + "]";
	}	
}