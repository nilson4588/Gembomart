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
@Table(name = "tbl_comission")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
@JsonInclude(Include.NON_NULL)
public class Comission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="comission_id") 
	private int comissionId;	
	
	@Column(name ="seller_order_id") 
	private int sellerOrderId;
	
	@Column(name ="franchise_local") 
	private double franchiseLocal;
	
	@Column(name ="franchise_taluka") 
	private double franchiseTaluka;
	
	@Column(name ="franchise_district") 
	private double franchiseDistrict;
	
	@Column(name ="delivery_person") 
	private double deliveryPerson;
	
	@Column(name ="gembomart") 
	private double gembomart;

	public int getComissionId() {
		return comissionId;
	}

	public void setComissionId(int comissionId) {
		this.comissionId = comissionId;
	}

	public int getSellerOrderId() {
		return sellerOrderId;
	}

	public void setSellerOrderId(int sellerOrderId) {
		this.sellerOrderId = sellerOrderId;
	}

	public double getFranchiseLocal() {
		return franchiseLocal;
	}

	public void setFranchiseLocal(double franchiseLocal) {
		this.franchiseLocal = franchiseLocal;
	}

	public double getFranchiseTaluka() {
		return franchiseTaluka;
	}

	public void setFranchiseTaluka(double franchiseTaluka) {
		this.franchiseTaluka = franchiseTaluka;
	}

	public double getFranchiseDistrict() {
		return franchiseDistrict;
	}

	public void setFranchiseDistrict(double franchiseDistrict) {
		this.franchiseDistrict = franchiseDistrict;
	}

	public double getDeliveryPerson() {
		return deliveryPerson;
	}

	public void setDeliveryPerson(double deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}

	public double getGembomart() {
		return gembomart;
	}

	public void setGembomart(double gembomart) {
		this.gembomart = gembomart;
	}

	@Override
	public String toString() {
		return "Comission [comissionId=" + comissionId + ", sellerOrderId=" + sellerOrderId + ", franchiseLocal="
				+ franchiseLocal + ", franchiseTaluka=" + franchiseTaluka + ", franchiseDistrict=" + franchiseDistrict
				+ ", deliveryPerson=" + deliveryPerson + ", gembomart=" + gembomart + "]";
	}
}