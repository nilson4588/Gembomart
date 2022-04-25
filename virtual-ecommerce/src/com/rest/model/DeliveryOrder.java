package com.rest.model;

public class DeliveryOrder {
	
	private int    productId;
	private String productName;
	private String orderUnit;
	private int    orderQuantity;
	private String orderAmount;
	private String supplierName;
	private long   supplierContact;
	private String supplierAddress;
	private String supplierPincode;
	private String sellerName;
	private long sellerContact;
	private String sellerAddress;
	private String sellerPincode;
	
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
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	public String getSupplierPincode() {
		return supplierPincode;
	}
	public void setSupplierPincode(String supplierPincode) {
		this.supplierPincode = supplierPincode;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	public String getSellerPincode() {
		return sellerPincode;
	}
	public void setSellerPincode(String sellerPincode) {
		this.sellerPincode = sellerPincode;
	}	
	public String getSupplierAddress() {
		return supplierAddress;
	}
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	public String getSellerAddress() {
		return sellerAddress;
	}
	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}
	
	
	public long getSupplierContact() {
		return supplierContact;
	}
	public void setSupplierContact(long supplierContact) {
		this.supplierContact = supplierContact;
	}
	public long getSellerContact() {
		return sellerContact;
	}
	public void setSellerContact(long sellerContact) {
		this.sellerContact = sellerContact;
	}
	@Override
	public String toString() {
		return "DeliveryOrder [productId=" + productId + ", productName=" + productName + ", orderUnit=" + orderUnit
				+ ", orderQuantity=" + orderQuantity + ", orderAmount=" + orderAmount + ", supplierName=" + supplierName
				+ ", supplierContact=" + supplierContact + ", supplierAddress=" + supplierAddress + ", supplierPincode="
				+ supplierPincode + ", sellerName=" + sellerName + ", sellerContact=" + sellerContact
				+ ", sellerAddress=" + sellerAddress + ", sellerPincode=" + sellerPincode + "]";
	}
}