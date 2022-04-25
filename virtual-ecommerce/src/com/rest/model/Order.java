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
@Table(name = "tbl_order")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
@JsonInclude(Include.NON_NULL)
public class Order {

	  @Id  
	  @GeneratedValue(strategy = GenerationType.IDENTITY)  
	  @Column(name ="order_id") 
	  private int orderId;
	  
	  @Column(name ="mobile_no") 
	  private long mobileNo;
	  	  	 
	  @Column(name ="order_type") 
	  private String orderType;
	  
	  @Column(name ="order_status") 
	  private String orderStatus;
	  	  
	  @Column(name="sub_total")
	  private String subTotal;
	  
	  @Column(name="delivery_charges")
	  private String deliveryCharges;
	  
	  @Column(name="total_amount")
	  private String totalAmount;  	  
	  
	  @Column(name ="payment_method") 
	  private String paymentMethod;
	  
	  @Column(name ="order_date") 
	  private String orderDate;
	  
	  @Column(name ="expected_delivery_date") 
	  private String expectedDeliveryDate;
		
	  @Column(name ="delivered_date") 
	  private String deliveredDate;
	  
	  @Column(name ="shipped_date") 
	  private String shippedDate;

	  @Column(name ="canceled_date") 
	  private String canceledDate;
	  
	  @Column(name ="payment_id") 
	  private String paymentId;
	  
	  @Column(name ="delivery_boy_id") 
	  private int deliveryBoyId;
	  	  
	  @Column(name ="customer_address") 
	  private String customerAddress;
	  
	  @Column(name ="latitude") 
	  private String latitude;
		
	  @Column(name ="longitude") 
	  private String longitude;
	  
	  @Column(name ="order_mobile_no") 
	  private long orderMobileNo;
	  
	  @Column(name ="order_otp") 
	  private String orderOtp;
	  
	  @Column(name="delivery_boy_commission")
	  private String deliveryBoyCommission;  
	  
	  @Transient
	  private int customerId;
	  
	  @Transient
	  private String customerName;	  
	  
	  @Transient
	  private List<OrderDetails> orderItemData;
	  
	  @Transient
	  private String shopName;
	  
	  @Transient
	  private String deliveryBoyName;

	  @Transient
	  private long deliveryBoyMobileNumber;
	  
	  @Transient
	  private String talukaFranchiseName;

	  @Transient
	  private long talukaFranchiseContact;
	  
	  @Transient
	  private String talukaName;
		  
	  @Transient
	  private String shopAddress;
	  
	  @Transient
	  private String shopMobile;
	  
	  @Transient
	  private double sellerCharges;
	  	  	
	public String getDeliveryBoyCommission() {
		return deliveryBoyCommission;
	}

	public void setDeliveryBoyCommission(String deliveryBoyCommission) {
		this.deliveryBoyCommission = deliveryBoyCommission;
	}

	public String getOrderOtp() {
		return orderOtp;
	}

	public void setOrderOtp(String orderOtp) {
		this.orderOtp = orderOtp;
	}

	public long getOrderMobileNo() {
		return orderMobileNo;
	}

	public void setOrderMobileNo(long orderMobileNo) {
		this.orderMobileNo = orderMobileNo;
	}

	public double getSellerCharges() {
		return sellerCharges;
	}

	public void setSellerCharges(double sellerCharges) {
		this.sellerCharges = sellerCharges;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopMobile() {
		return shopMobile;
	}

	public void setShopMobile(String shopMobile) {
		this.shopMobile = shopMobile;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getTalukaFranchiseName() {
		return talukaFranchiseName;
	}

	public void setTalukaFranchiseName(String talukaFranchiseName) {
		this.talukaFranchiseName = talukaFranchiseName;
	}

	public long getTalukaFranchiseContact() {
		return talukaFranchiseContact;
	}

	public void setTalukaFranchiseContact(long talukaFranchiseContact) {
		this.talukaFranchiseContact = talukaFranchiseContact;
	}

	public String getTalukaName() {
		return talukaName;
	}

	public void setTalukaName(String talukaName) {
		this.talukaName = talukaName;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public String getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(String deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public String getDeliveryBoyName() {
		return deliveryBoyName;
	}

	public void setDeliveryBoyName(String deliveryBoyName) {
		this.deliveryBoyName = deliveryBoyName;
	}

	public long getDeliveryBoyMobileNumber() {
		return deliveryBoyMobileNumber;
	}

	public void setDeliveryBoyMobileNumber(long deliveryBoyMobileNumber) {
		this.deliveryBoyMobileNumber = deliveryBoyMobileNumber;
	}

	public int getDeliveryBoyId() {
		return deliveryBoyId;
	}

	public void setDeliveryBoyId(int deliveryBoyId) {
		this.deliveryBoyId = deliveryBoyId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getPaymentId() {
		return paymentId;
	  }

	  public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	  }

	  public int getOrderId() {
		return orderId;
	  }

	  public void setOrderId(int orderId) {
		this.orderId = orderId;
	  }

	  public long getMobileNo() {
	 	return mobileNo;
	  }

	  public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	  }

	  public String getOrderType() {
		return orderType;
	  }

	  public void setOrderType(String orderType) {
		this.orderType = orderType;
	  }

	  public String getOrderStatus() {
		return orderStatus;
	  }

	  public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

	  public String getOrderDate() {
		return orderDate;
	  }

	  public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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

	  public String getCustomerName() {
		return customerName;
	  }

	  public void setCustomerName(String customerName) {
		this.customerName = customerName;
	  }

	  public String getCustomerAddress() {
		return customerAddress;
	  }

	  public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	  }

	  public List<OrderDetails> getOrderItemData() {
		return orderItemData;
	  }

	  public void setOrderItemData(List<OrderDetails> orderItemData) {
	 	this.orderItemData = orderItemData;
	  }
	  
	  public int getCustomerId() {
		return customerId;
	  }

	  public void setCustomerId(int customerId) {
		this.customerId = customerId;
	  }

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", mobileNo=" + mobileNo + ", orderType=" + orderType + ", orderStatus="
				+ orderStatus + ", subTotal=" + subTotal + ", deliveryCharges=" + deliveryCharges + ", totalAmount="
				+ totalAmount + ", paymentMethod=" + paymentMethod + ", orderDate=" + orderDate
				+ ", expectedDeliveryDate=" + expectedDeliveryDate + ", deliveredDate=" + deliveredDate
				+ ", shippedDate=" + shippedDate + ", canceledDate=" + canceledDate + ", paymentId=" + paymentId
				+ ", deliveryBoyId=" + deliveryBoyId + ", customerAddress=" + customerAddress + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", orderMobileNo=" + orderMobileNo + ", customerId=" + customerId
				+ ", customerName=" + customerName + ", orderItemData=" + orderItemData + ", shopName=" + shopName
				+ ", deliveryBoyName=" + deliveryBoyName + ", deliveryBoyMobileNumber=" + deliveryBoyMobileNumber
				+ ", talukaFranchiseName=" + talukaFranchiseName + ", talukaFranchiseContact=" + talukaFranchiseContact
				+ ", talukaName=" + talukaName + ", shopAddress=" + shopAddress + ", shopMobile=" + shopMobile
				+ ", sellerCharges=" + sellerCharges + "]";
	}
}