package com.rest.model;

public class ProductListByShop {

	private int    sellerId;
	private String shopName;
	private int    productId; 
	private String productTitle;
	private String productCategory;
	private String productPackSize; 
	private String productDescription;
	private double productPrice; 
	private double productDiscount;
	private String imageSource; 
	
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
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
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
	public String getImageSource() {
		return imageSource;
	}
	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}
}