  package com.rest.model;
  
  import javax.persistence.Column; import javax.persistence.Entity; import
  javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
  import javax.persistence.Id; import javax.persistence.Table;
  
  import com.fasterxml.jackson.annotation.JsonIgnoreProperties; import
  com.fasterxml.jackson.annotation.JsonInclude; import
  com.fasterxml.jackson.annotation.JsonInclude.Include;
  
  @Entity  
  @Table(name = "tbl_districts")  
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
  @JsonInclude(Include.NON_NULL) public class Districts {
  
	  @Id  
	  @GeneratedValue(strategy = GenerationType.IDENTITY)  
	  @Column(name ="district_id") private int districtId;
	  
	  @Column(name ="state_code") private int stateCode;
	  
	  @Column(name ="district_code") private int districtCode;
	  
	  @Column(name ="district_name") private String districtName;
	  
	  public int getDistrictId() { return districtId; }
	  
	  public void setDistrictId(int districtId) { this.districtId = districtId; }
	  
	  public int getStateCode() { return stateCode; }
	  
	  public void setStateCode(int stateCode) { this.stateCode = stateCode; }
	  
	  public int getDistrictCode() { return districtCode; }
	  
	  public void setDistrictCode(int districtCode) { this.districtCode =  districtCode; }
	  
	  public String getDistrictName() { return districtName; }
	  
	  public void setDistrictName(String districtName) { this.districtName =  districtName; }
  
}