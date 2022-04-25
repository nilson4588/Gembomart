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
@Table(name = "tbl_order_details")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class OrderDetails { //OrderItemData

	  @Id  
	  @GeneratedValue(strategy = GenerationType.IDENTITY)  
	  @Column(name ="order_details_id") 
	  private int orderDetailsId;
	  
	  @Column(name ="order_id") 
	  private int orderId;
	  
	  @Column(name ="product_id") 
	  private int productId;
	  
	  @Column(name ="quantity") 
	  private int quantity;
	  
	  @Column(name ="rate") 
	  private int rate;
	  
	  @Column(name ="discount") 
	  private int discount;
	 
	  @Column(name ="amount") 
	  private int amount;
	  
	  @Column(name ="seller_id") 
	  private int sellerId;
	  
	  @Column(name ="order_status") 
	  private String orderStatus;  
	  
	  @Column(name ="order_date") 
	  private String orderDate;
	  
	  @Column(name ="expected_delivery_date") 
	  private String expectedDeliveryDate;
	  
	  @Column(name ="ready_to_ship_date") 
	  private String readyToShipDate;
		
	  @Column(name ="delivered_date") 
	  private String deliveredDate;
	  
	  @Column(name ="peak_up_date") 
	  private String peakUpDate;

	  @Column(name ="canceled_date") 
	  private String canceledDate;
	  
	  
	  @Transient
	  private String productName;
	  
	  @Transient
	  private String productImage;
	  
	  @Transient
	  private String shopName;
	  
	  @Transient
	  private String shopAddress;
	  
	  @Transient
	  private String shopMobile;
	  
	  @Transient
		private String latitude;
		
	  @Transient
		private String longitude;
	  
	  @Transient
		private double purchasePrice;
	  
	  	  
	  
	  
	  public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getOrderStatus() {
		return orderStatus;
		}
	
		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
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
	
		public String getReadyToShipDate() {
			return readyToShipDate;
		}
	
		public void setReadyToShipDate(String readyToShipDate) {
			this.readyToShipDate = readyToShipDate;
		}
	
		public String getDeliveredDate() {
			return deliveredDate;
		}
	
		public void setDeliveredDate(String deliveredDate) {
			this.deliveredDate = deliveredDate;
		}
		
		public String getPeakUpDate() {
			return peakUpDate;
		}

		public void setPeakUpDate(String peakUpDate) {
			this.peakUpDate = peakUpDate;
		}

		public String getCanceledDate() {
			return canceledDate;
		}
	
		public void setCanceledDate(String canceledDate) {
			this.canceledDate = canceledDate;
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

	  public String getShopName() {
		return shopName;
	  }

	  public void setShopName(String shopName) {
		this.shopName = shopName;
	  }

		public int getSellerId() {
		  	return sellerId;
	  	}

	    public void setSellerId(int sellerId) {
	    	this.sellerId = sellerId;
		}

		public int getOrderDetailsId() {	
			return orderDetailsId;
	    }
	
		public void setOrderDetailsId(int orderDetailsId) {
			this.orderDetailsId = orderDetailsId;
		}
	
		public int getOrderId() {
			return orderId;
		}
	
		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}
	
		public int getProductId() {
			return productId;
		}
	
		public void setProductId(int productId) {
			this.productId = productId;
		}
	
		public int getQuantity() {
			return quantity;
		}
	
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
	
		public int getRate() {
			return rate;
		}
	
		public void setRate(int rate) {
			this.rate = rate;
		}

		public int getDiscount() {
			return discount;
		}

		public void setDiscount(int discount) {
			this.discount = discount;
		}
		
		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}	

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
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

		@Override
		public String toString() {
			return "OrderDetails [orderDetailsId=" + orderDetailsId + ", orderId=" + orderId + ", productId="
					+ productId + ", quantity=" + quantity + ", rate=" + rate + ", discount=" + discount + ", amount="
					+ amount + ", sellerId=" + sellerId + ", orderStatus=" + orderStatus + ", orderDate=" + orderDate
					+ ", expectedDeliveryDate=" + expectedDeliveryDate + ", readyToShipDate=" + readyToShipDate
					+ ", deliveredDate=" + deliveredDate + ", peakUpDate=" + peakUpDate + ", canceledDate="
					+ canceledDate + ", productName=" + productName + ", productImage=" + productImage + ", shopName="
					+ shopName + ", shopAddress=" + shopAddress + ", shopMobile=" + shopMobile + ", latitude="
					+ latitude + ", longitude=" + longitude + "]";
		}	  		
}