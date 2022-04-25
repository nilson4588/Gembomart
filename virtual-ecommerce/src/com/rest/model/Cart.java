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
@Table(name = "tbl_cart")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Cart {
	
	  @Id  
	  @GeneratedValue(strategy = GenerationType.IDENTITY)  
	  @Column(name ="cart_id") 
	  private int cartId;
	
	  @Column(name ="customer_id") 
	  private int customerId;
	  
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
	  
	  @Column(name ="product_name") 
	  private String productName;
	  
	  @Column(name ="product_image") 
	  private String productImage;
	  
	  @Transient
	  private String shopName;
	  
	  @Transient
	  private int areaCode;
	    
	  @Transient
	  private int deliveryCharges;
	  	  
	  public int getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(int deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public int getAreaCode() {
		return areaCode;
	  }

	  public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	  }

	  public String getShopName() {
		return shopName;
	  }

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

		public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

		public int getCartId() {
			return cartId;
		}
	
		public void setCartId(int cartId) {
			this.cartId = cartId;
		}
	
		public int getCustomerId() {
			return customerId;
		}
	
		public void setCustomerId(int customerId) {
			this.customerId = customerId;
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
	
		public int getSellerId() {
			return sellerId;
		}
	
		public void setSellerId(int sellerId) {
			this.sellerId = sellerId;
		}

		@Override
		public String toString() {
			return "Cart [cartId=" + cartId + ", customerId=" + customerId + ", productId=" + productId + ", quantity="
					+ quantity + ", rate=" + rate + ", discount=" + discount + ", amount=" + amount + ", sellerId="
					+ sellerId + ", productName=" + productName + ", productImage=" + productImage + "]";
		}	 
		
}