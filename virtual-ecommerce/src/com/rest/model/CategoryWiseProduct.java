package com.rest.model;

public class CategoryWiseProduct {

	private int productId; 
	private String productTitle; 
	private String imageSource; 
	private String productPackSize;
	private String productDescription;
	
	
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
	public String getImageSource() {
		return imageSource;
	}
	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
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
	@Override
	public String toString() {
		return "CategoryWiseProduct [productId=" + productId + ", productTitle=" + productTitle + ", imageSource="
				+ imageSource + ", productPackSize=" + productPackSize + ", productDescription=" + productDescription
				+ "]";
	}
}
