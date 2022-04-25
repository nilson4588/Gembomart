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
@Table(name = "tbl_supplier_order_details")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonInclude(Include.NON_NULL)
public class SupplierOrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_order_details_id")	
	private int  supplierOrderDetailsId;
	
	@Column(name = "supplier_order_id")
	private int  supplierOrderId;
	
	@Transient
	private long mobileNumber;
	
	@Column(name = "product_id")
	private int  productId;
	
	@Transient
	private String productName;
	
	@Column(name = "order_unit")
	private String orderUnit;
	
	@Column(name = "order_quantity")
	private int orderQuantity;
	
	@Column(name = "product_rate")
	private double productRate;
	
	@Column(name = "order_amount")
	private String orderAmount;
	
	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "sale_rate")
	private double saleRate;
	
	public int getSupplierOrderDetailsId() {
		return supplierOrderDetailsId;
	}

	public void setSupplierOrderDetailsId(int supplierOrderDetailsId) {
		this.supplierOrderDetailsId = supplierOrderDetailsId;
	}

	public int getSupplierOrderId() {
		return supplierOrderId;
	}

	public void setSupplierOrderId(int supplierOrderId) {
		this.supplierOrderId = supplierOrderId;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

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

	public String getOrderUnit() {
		return orderUnit;
	}

	public void setOrderUnit(String orderUnit) {
		this.orderUnit = orderUnit;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public double getProductRate() {
		return productRate;
	}

	public void setProductRate(double productRate) {
		this.productRate = productRate;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public double getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
	}

	@Override
	public String toString() {
		return "SupplierOrderDetails [supplierOrderDetailsId=" + supplierOrderDetailsId + ", supplierOrderId="
				+ supplierOrderId + ", mobileNumber=" + mobileNumber + ", productId=" + productId + ", productName="
				+ productName + ", orderUnit=" + orderUnit + ", orderQuantity=" + orderQuantity + ", productRate="
				+ productRate + ", orderAmount=" + orderAmount + ", isActive=" + isActive + "]";
	}
}