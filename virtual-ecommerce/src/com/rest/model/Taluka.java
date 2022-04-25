  package com.rest.model;
  
  import javax.persistence.Column; import javax.persistence.Entity; import
  javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
  import javax.persistence.Id; import javax.persistence.Table;
  
  import com.fasterxml.jackson.annotation.JsonIgnoreProperties; import
  com.fasterxml.jackson.annotation.JsonInclude; import
  com.fasterxml.jackson.annotation.JsonInclude.Include;
  
  
  @Entity  
  @Table(name = "tbl_taluka")  
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
  @JsonInclude(Include.NON_NULL) public class Taluka {
  
	  @Id  
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  
	  @Column(name ="taluka_id") private int talukaId;
	  
	  @Column(name ="state_code") private int stateCode;
	  
	  @Column(name ="district_code") private int districtCode;
	  
	  @Column(name ="taluka_code") private int talukaCode;
	  
	  @Column(name ="taluka_name") private String talukaName;
	  
	  @Column(name ="delivery_charges") private int deliveryCharges;
	  
	  public int getTalukaId() { return talukaId; }
	  
	  public void setTalukaId(int talukaId) { this.talukaId = talukaId; }
	  
	  public int getStateCode() { return stateCode; }
	  
	  public void setStateCode(int stateCode) { this.stateCode = stateCode; }
	  
	  public int getDistrictCode() { return districtCode; }
	  
	  public void setDistrictCode(int districtCode) { this.districtCode =  districtCode; }
	  
	  public int getTalukaCode() { return talukaCode; }
	  
	  public void setTalukaCode(int talukaCode) { this.talukaCode = talukaCode; }
	  
	  public String getTalukaName() { return talukaName; }
	  
	  public void setTalukaName(String talukaName) { this.talukaName = talukaName;  }

	public int getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(int deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}
  }