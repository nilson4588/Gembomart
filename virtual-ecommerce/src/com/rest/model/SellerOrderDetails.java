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
@Table(name = "tbl_seller_order_details")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonInclude(Include.NON_NULL)
public class SellerOrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seller_order_details_id")	
	private int  sellerOrderDetailsId;
	
	@Column(name = "seller_order_id")
	private int  sellerOrderId;
	
	@Transient
	private long mobileNumber;
	
	@Transient
	private String videoCallRequest;
		
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
	
	@Column(name = "supplier_id")
	private int supplierId;

	public String getVideoCallRequest() {
		return videoCallRequest;
	}

	public void setVideoCallRequest(String videoCallRequest) {
		this.videoCallRequest = videoCallRequest;
	}

	public int getSellerOrderDetailsId() {
		return sellerOrderDetailsId;
	}

	public void setSellerOrderDetailsId(int sellerOrderDetailsId) {
		this.sellerOrderDetailsId = sellerOrderDetailsId;
	}

	public int getSellerOrderId() {
		return sellerOrderId;
	}

	public void setSellerOrderId(int sellerOrderId) {
		this.sellerOrderId = sellerOrderId;
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

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	@Override
	public String toString() {
		return "SellerOrderDetails [sellerOrderDetailsId=" + sellerOrderDetailsId + ", sellerOrderId=" + sellerOrderId
				+ ", mobileNumber=" + mobileNumber + ", productId=" + productId + ", productName=" + productName
				+ ", orderUnit=" + orderUnit + ", orderQuantity=" + orderQuantity + ", productRate=" + productRate
				+ ", orderAmount=" + orderAmount + ", isActive=" + isActive + ", supplierId=" + supplierId + "]";
	}
}