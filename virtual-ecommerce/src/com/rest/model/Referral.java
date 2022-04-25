package com.rest.model;

public class Referral {

	private String franchiseType;
	private String fullName;
	private String franchiseFirmName;
	private long   franchiseContactNo;
	private String franchiseAddress;
	private double percentBenefit;
	
	public String getFranchiseType() {
		return franchiseType;
	}
	public void setFranchiseType(String franchiseType) {
		this.franchiseType = franchiseType;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFranchiseFirmName() {
		return franchiseFirmName;
	}
	public void setFranchiseFirmName(String franchiseFirmName) {
		this.franchiseFirmName = franchiseFirmName;
	}
	public long getFranchiseContactNo() {
		return franchiseContactNo;
	}
	public void setFranchiseContactNo(long franchiseContactNo) {
		this.franchiseContactNo = franchiseContactNo;
	}
	public String getFranchiseAddress() {
		return franchiseAddress;
	}
	public void setFranchiseAddress(String franchiseAddress) {
		this.franchiseAddress = franchiseAddress;
	}
	public double getPercentBenefit() {
		return percentBenefit;
	}
	public void setPercentBenefit(double percentBenefit) {
		this.percentBenefit = percentBenefit;
	}	
}