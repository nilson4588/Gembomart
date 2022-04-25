package com.rest.model;

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
@Table(name = "tbl_product_price")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
@JsonInclude(Include.NON_NULL)
public class ProductPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="product_price_id") 
	private int productPriceId;
	
	@Column(name ="created_datetime") 
	private String createdDateTime;
	
	@Column(name ="product_id") 
	private int productId;
	
	@Column(name ="supplier_price") 
	private double supplierPrice;
	
	@Column(name ="supplier_unit") 
	private String supplierUnit;
	
	@Column(name ="seller_price") 
	private double sellerPrice;
	
	@Column(name ="seller_unit") 
	private String sellerUnit;
	
	@Column(name ="customer_price") 
	private double customerPrice;
	
	@Column(name ="customer_unit") 
	private String customerUnit;
	
	@Column(name ="is_active") 
	private int isActive;

	@Transient
	private String productName;	
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	
	public int getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}

	public String getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(double supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public String getSupplierUnit() {
		return supplierUnit;
	}

	public void setSupplierUnit(String supplierUnit) {
		this.supplierUnit = supplierUnit;
	}

	public double getSellerPrice() {
		return sellerPrice;
	}

	public void setSellerPrice(double sellerPrice) {
		this.sellerPrice = sellerPrice;
	}

	public String getSellerUnit() {
		return sellerUnit;
	}

	public void setSellerUnit(String sellerUnit) {
		this.sellerUnit = sellerUnit;
	}

	public double getCustomerPrice() {
		return customerPrice;
	}

	public void setCustomerPrice(double customerPrice) {
		this.customerPrice = customerPrice;
	}

	public String getCustomerUnit() {
		return customerUnit;
	}

	public void setCustomerUnit(String customerUnit) {
		this.customerUnit = customerUnit;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "ProductPrice [productPriceId=" + productPriceId + ", createdDateTime=" + createdDateTime
				+ ", productId=" + productId + ", supplierPrice=" + supplierPrice + ", supplierUnit=" + supplierUnit
				+ ", sellerPrice=" + sellerPrice + ", sellerUnit=" + sellerUnit + ", customerPrice=" + customerPrice
				+ ", customerUnit=" + customerUnit + ", isActive=" + isActive + "]";
	}
}