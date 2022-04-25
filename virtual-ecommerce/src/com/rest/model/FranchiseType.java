package com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity  
@Table(name = "tbl_franchise_type")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
@JsonInclude(Include.NON_NULL)
public class FranchiseType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="franchise_type_id") 
	private int franchiseTypeId;
		
	@Column(name ="franchise_type_desc") 
	private String franchiseTypeDesc;
		
	@Column(name ="supplier_cnt") 
	private int supplierCnt;
	
	@Column(name ="seller_cnt") 
	private int sellerCnt;
	
	@Column(name ="delivery_cnt") 
	private int deliveryCnt;

	public int getFranchiseTypeId() {
		return franchiseTypeId;
	}

	public void setFranchiseTypeId(int franchiseTypeId) {
		this.franchiseTypeId = franchiseTypeId;
	}

	public String getFranchiseTypeDesc() {
		return franchiseTypeDesc;
	}

	public void setFranchiseTypeDesc(String franchiseTypeDesc) {
		this.franchiseTypeDesc = franchiseTypeDesc;
	}

	public int getSupplierCnt() {
		return supplierCnt;
	}

	public void setSupplierCnt(int supplierCnt) {
		this.supplierCnt = supplierCnt;
	}

	public int getSellerCnt() {
		return sellerCnt;
	}

	public void setSellerCnt(int sellerCnt) {
		this.sellerCnt = sellerCnt;
	}

	public int getDeliveryCnt() {
		return deliveryCnt;
	}

	public void setDeliveryCnt(int deliveryCnt) {
		this.deliveryCnt = deliveryCnt;
	}

	@Override
	public String toString() {
		return "FranchiseType [franchiseTypeId=" + franchiseTypeId + ", franchiseTypeDesc=" + franchiseTypeDesc
				+ ", supplierCnt=" + supplierCnt + ", sellerCnt=" + sellerCnt + ", deliveryCnt=" + deliveryCnt + "]";
	}
}