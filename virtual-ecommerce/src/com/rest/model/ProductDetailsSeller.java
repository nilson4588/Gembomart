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
@Table(name = "tbl_product_details_seller")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ProductDetailsSeller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_details_seller_id")
	private int productDetailsSellerId;

	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "gembo_benefit")
	private double gemboBenefit;
	
	@Column(name = "product_image")
	private String productImage;
	
	@Column(name = "seller_id")
	private int sellerId;

	@Transient
	private String productName;
	
	@Transient
	private String sellerName;

	public int getProductDetailsSellerId() {
		return productDetailsSellerId;
	}

	public void setProductDetailsSellerId(int productDetailsSellerId) {
		this.productDetailsSellerId = productDetailsSellerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getGemboBenefit() {
		return gemboBenefit;
	}

	public void setGemboBenefit(double gemboBenefit) {
		this.gemboBenefit = gemboBenefit;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
}