package com.rest.model;

import java.util.List;

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
@Table(name = "tbl_supplier_order")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonInclude(Include.NON_NULL)
public class SupplierOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_order_id")
	private int supplierOrderId;
		
	@Column(name ="mobile_number") 
	private long mobileNumber;
		  
	@Transient
	private String supplierName;

	@Transient
	private String supplierAddress;
	
	@Column(name = "order_date")
	private String orderDate;			
	
	@Column(name = "order_status")
	private String orderStatus;
	
	@Column(name ="order_type") 
	private String orderType;
	  
	@Column(name="total_amount")
	private String totalAmount;
		
	@Column(name ="payment_method") 
	private String paymentMethod;
	
	@Column(name ="expected_delivery_date") 
	private String expectedDeliveryDate;
		
	@Column(name ="delivered_date") 
	private String deliveredDate;
	  
	@Column(name ="shipped_date") 
	private String shippedDate;

	@Column(name ="canceled_date") 
	private String canceledDate;
	  
	@Transient
	private List<SupplierOrderDetails> orderItemData;

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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	 
	public String getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(String expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public String getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(String deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public List<SupplierOrderDetails> getOrderItemData() {
		return orderItemData;
	}

	public void setOrderItemData(List<SupplierOrderDetails> orderItemData) {
		this.orderItemData = orderItemData;
	}
	
	public String getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(String shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getCanceledDate() {
		return canceledDate;
	}

	public void setCanceledDate(String canceledDate) {
		this.canceledDate = canceledDate;
	}

	@Override
	public String toString() {
		return "SupplierOrder [supplierOrderId=" + supplierOrderId + ", mobileNumber=" + mobileNumber
				+ ", supplierName=" + supplierName + ", supplierAddress=" + supplierAddress + ", orderDate=" + orderDate
				+ ", orderStatus=" + orderStatus + ", orderType=" + orderType + ", totalAmount=" + totalAmount
				+ ", paymentMethod=" + paymentMethod + ", expectedDeliveryDate=" + expectedDeliveryDate
				+ ", deliveredDate=" + deliveredDate + ", shippedDate=" + shippedDate + ", canceledDate=" + canceledDate
				+ ", orderItemData=" + orderItemData + "]";
	}
}