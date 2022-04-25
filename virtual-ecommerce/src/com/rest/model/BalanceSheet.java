package com.rest.model;

public class BalanceSheet {
	
	private double supplierAmount;
	private double sellerAmount;
	private double customerAmount;
	private double profitAmount;
	
	@Override
	public String toString() {
		return "BalanceSheet [supplierAmount=" + supplierAmount + ", sellerAmount=" + sellerAmount + ", customerAmount="
				+ customerAmount + ", profitAmount=" + profitAmount + "]";
	}
	
	public double getSupplierAmount() {
		return supplierAmount;
	}
	public void setSupplierAmount(double supplierAmount) {
		this.supplierAmount = supplierAmount;
	}
	public double getSellerAmount() {
		return sellerAmount;
	}
	public void setSellerAmount(double sellerAmount) {
		this.sellerAmount = sellerAmount;
	}
	public double getCustomerAmount() {
		return customerAmount;
	}
	public void setCustomerAmount(double customerAmount) {
		this.customerAmount = customerAmount;
	}
	public double getProfitAmount() {
		return profitAmount;
	}
	public void setProfitAmount(double profitAmount) {
		this.profitAmount = profitAmount;
	}
}