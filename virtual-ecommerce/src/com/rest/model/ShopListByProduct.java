package com.rest.model;

public class ShopListByProduct {

	private int sellerId;
	private String shopName;
	private int productId; 
	private String productTitle;
	private String productPackSize; 
	private String productDescription;
	private double productPrice; 
	private double productDiscount;
	//private double deliveryCharges;
	
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
	public String getProductPackSize() {
		return productPackSize;
	}
	public void setProductPackSize(String productPackSize) {
		this.productPackSize = productPackSize;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public double getProductDiscount() {
		return productDiscount;
	}
	public void setProductDiscount(double productDiscount) {
		this.productDiscount = productDiscount;
	}	
	
	/*
	 * public double getDeliveryCharges() { return deliveryCharges; } public void
	 * setDeliveryCharges(double deliveryCharges) { this.deliveryCharges =
	 * deliveryCharges; }
	 */
	
	
	@Override
	public String toString() {
		return "ShopListByProduct [sellerId=" + sellerId + ", shopName=" + shopName + ", productId=" + productId
				+ ", productTitle=" + productTitle + ", productPackSize=" + productPackSize + ", productDescription="
				+ productDescription + ", productPrice=" + productPrice + ", productDiscount=" + productDiscount+ "]";
	}
}