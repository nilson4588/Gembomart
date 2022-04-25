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
@Table(name = "tbl_product_details")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ProductDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_details_id")
	private int productDetailsId;

	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "gembo_benefit")
	private double gemboBenefit;
	
	@Column(name = "product_image")
	private String productImage;
	
	@Column(name = "supplier_id")
	private int supplierId;

	@Transient
	private String productName;
	
	@Transient
	private String supplierName;
	
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public int getProductDetailsId() {
		return productDetailsId;
	}

	public void setProductDetailsId(int productDetailsId) {
		this.productDetailsId = productDetailsId;
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

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	@Override
	public String toString() {
		return "ProductDetails [productDetailsId=" + productDetailsId + ", productId=" + productId + ", gemboBenefit="
				+ gemboBenefit + ", productImage=" + productImage + ", supplierId=" + supplierId + "]";
	}	
}