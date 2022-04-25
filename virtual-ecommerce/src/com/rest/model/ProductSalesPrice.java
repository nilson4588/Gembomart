package com.rest.model;

public class ProductSalesPrice {
	
	private int    supplierId;
	private String supplierName;
	private int    productId;
	private double productRate;
	private double saleRate;
	
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public double getProductRate() {
		return productRate;
	}
	public void setProductRate(double productRate) {
		this.productRate = productRate;
	}
	public double getSaleRate() {
		return saleRate;
	}
	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
	}
	
	@Override
	public String toString() {
		return "ProductSalesPrice [supplierId=" + supplierId + ", supplierName=" + supplierName + ", productId="
				+ productId + ", productRate=" + productRate + ", saleRate=" + saleRate + "]";
	}
}
