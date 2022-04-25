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
@Table(name = "tbl_product_purchase")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ProductPurchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_id")
	private int purchaseId;
	
	@Column(name = "purchase_date")
	private String purchaseDate;

	@Column(name = "product_id")
	private int productId;
		
	@Column(name = "purchase_unit")
	private String purchaseUnit;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "purchase_price")
	private double purchasePrice;
		
	@Column(name = "sale_unit")
	private String saleUnit;
		
	@Column(name = "sale_price")
	private double salePrice;
	
	@Column(name = "is_active")
	private int isActive;
	
	@Transient
	private String productName;

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getPurchaseUnit() {
		return purchaseUnit;
	}

	public void setPurchaseUnit(String purchaseUnit) {
		this.purchaseUnit = purchaseUnit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getSaleUnit() {
		return saleUnit;
	}

	public void setSaleUnit(String saleUnit) {
		this.saleUnit = saleUnit;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "ProductPurchase [purchaseId=" + purchaseId + ", purchaseDate=" + purchaseDate + ", productId="
				+ productId + ", purchaseUnit=" + purchaseUnit + ", quantity=" + quantity + ", purchasePrice="
				+ purchasePrice + ", saleUnit=" + saleUnit + ", salePrice=" + salePrice + ", isActive=" + isActive
				+ ", productName=" + productName + "]";
	}
}