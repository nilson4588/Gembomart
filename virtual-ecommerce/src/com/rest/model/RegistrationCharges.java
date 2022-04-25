package com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_registration_charges")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RegistrationCharges {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "registration_charges_id")
	private int registrationChargesId;

	@Column(name = "franchise_name")
	private String franchiseName;

	@Column(name = "registration_amount")
	private int registrationAmount;

	public int getRegistrationChargesId() {
		return registrationChargesId;
	}

	public void setRegistrationChargesId(int registrationChargesId) {
		this.registrationChargesId = registrationChargesId;
	}

	public String getFranchiseName() {
		return franchiseName;
	}

	public void setFranchiseName(String franchiseName) {
		this.franchiseName = franchiseName;
	}

	public int getRegistrationAmount() {
		return registrationAmount;
	}

	public void setRegistrationAmount(int registrationAmount) {
		this.registrationAmount = registrationAmount;
	}
}