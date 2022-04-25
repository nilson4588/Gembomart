package com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_product_seller")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ProductSeller {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_seller_id")
	private int productSellerId;

	@Column(name = "seller_id")
	private int sellerId;
	
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "product_price")
	private double productPrice;
	
	@Column(name = "product_discount")
	private double productDiscount;
	
	/*
	 * @Column(name = "product_image") private String productImage;
	 */
	
	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "purchase_price")
	private double purchasePrice;
	
	@Column(name = "per_15_price")
	private double per15price;
	
	@Column(name = "gst_18")
	private double gst18;
	
	@Column(name = "total_ded")
	private double totalDed;
	
	@Column(name = "gembomart_benefit")
	private double gembomartBenefit;
		
	@Transient
	private String shopName;
	
	@Transient
	private String productName;
	
	@Transient
	private String categoryName;
	
	@Transient
	private String productImage;
	
	
	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "ProductSeller [productSellerId=" + productSellerId + ", sellerId=" + sellerId + ", productId="
				+ productId + ", productPrice=" + productPrice + ", productDiscount=" + productDiscount + ", isActive="
				+ isActive + ", purchasePrice=" + purchasePrice + ", per15price=" + per15price + ", gst18=" + gst18
				+ ", totalDed=" + totalDed + ", gembomartBenefit=" + gembomartBenefit + ", shopName=" + shopName
				+ ", productName=" + productName + ", categoryName=" + categoryName + "]";
	}

	public double getGembomartBenefit() {
		return gembomartBenefit;
	}

	public void setGembomartBenefit(double gembomartBenefit) {
		this.gembomartBenefit = gembomartBenefit;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getPer15price() {
		return per15price;
	}

	public void setPer15price(double per15price) {
		this.per15price = per15price;
	}

	public double getGst18() {
		return gst18;
	}

	public void setGst18(double gst18) {
		this.gst18 = gst18;
	}

	public double getTotalDed() {
		return totalDed;
	}

	public void setTotalDed(double totalDed) {
		this.totalDed = totalDed;
	}

	public int getProductSellerId() {
		return productSellerId;
	}

	public void setProductSellerId(int productSellerId) {
		this.productSellerId = productSellerId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
}
