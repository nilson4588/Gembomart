package com.rest.model;

public class DashboardTotalProductwise {

	private int    productId;
	private String productName;
	private String productUnit;
	private int    quantity;
	private double amount;
	private long   mobileNumber;
	private String fullName;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}	
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Override
	public String toString() {
		return "DashboardTotalProductwise [productId=" + productId + ", productName=" + productName + ", productUnit="
				+ productUnit + ", quantity=" + quantity + ", amount=" + amount + ", mobileNumber=" + mobileNumber
				+ ", fullName=" + fullName + "]";
	}
}