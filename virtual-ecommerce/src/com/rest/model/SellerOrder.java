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
@Table(name = "tbl_seller_order")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonInclude(Include.NON_NULL)
public class SellerOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seller_order_id")
	private int sellerOrderId;
		
	@Column(name ="mobile_number") 
	private long mobileNumber;
		  
	@Transient
	private String sellerName;

	@Transient
	private String sellerAddress;
	
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
	
	@Column(name ="video_call_request") 
	private String videoCallRequest;
	
	@Transient
	private double franchiseLocalComission;
	
	@Transient
	private double franchiseTalukaComission;
	
	@Transient
	private double franchiseDistrictComission;
	
	@Transient
	private List<SellerOrderDetails> orderItemData;

	public String getVideoCallRequest() {
		return videoCallRequest;
	}

	public void setVideoCallRequest(String videoCallRequest) {
		this.videoCallRequest = videoCallRequest;
	}

	public int getSellerOrderId() {
		return sellerOrderId;
	}

	public void setSellerOrderId(int sellerOrderId) {
		this.sellerOrderId = sellerOrderId;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
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

	public List<SellerOrderDetails> getOrderItemData() {
		return orderItemData;
	}

	public void setOrderItemData(List<SellerOrderDetails> orderItemData) {
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

	public double getFranchiseLocalComission() {
		return franchiseLocalComission;
	}

	public void setFranchiseLocalComission(double franchiseLocalComission) {
		this.franchiseLocalComission = franchiseLocalComission;
	}
	
	public double getFranchiseTalukaComission() {
		return franchiseTalukaComission;
	}

	public void setFranchiseTalukaComission(double franchiseTalukaComission) {
		this.franchiseTalukaComission = franchiseTalukaComission;
	}

	public double getFranchiseDistrictComission() {
		return franchiseDistrictComission;
	}

	public void setFranchiseDistrictComission(double franchiseDistrictComission) {
		this.franchiseDistrictComission = franchiseDistrictComission;
	}
	
	

	@Override
	public String toString() {
		return "SellerOrder [sellerOrderId=" + sellerOrderId + ", mobileNumber=" + mobileNumber + ", sellerName="
				+ sellerName + ", sellerAddress=" + sellerAddress + ", orderDate=" + orderDate + ", orderStatus="
				+ orderStatus + ", orderType=" + orderType + ", totalAmount=" + totalAmount + ", paymentMethod="
				+ paymentMethod + ", expectedDeliveryDate=" + expectedDeliveryDate + ", deliveredDate=" + deliveredDate
				+ ", shippedDate=" + shippedDate + ", canceledDate=" + canceledDate + ", franchiseLocalComission="
				+ franchiseLocalComission + ", franchiseTalukaComission=" + franchiseTalukaComission
				+ ", franchiseDistrictComission=" + franchiseDistrictComission + ", orderItemData=" + orderItemData
				+ "]";
	}
}