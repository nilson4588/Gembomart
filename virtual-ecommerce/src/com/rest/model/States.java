  package com.rest.model;
  
  import javax.persistence.Column; import javax.persistence.Entity; import
  javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
  import javax.persistence.Id; import javax.persistence.Table;
  
  import com.fasterxml.jackson.annotation.JsonIgnoreProperties; import
  com.fasterxml.jackson.annotation.JsonInclude; import
  com.fasterxml.jackson.annotation.JsonInclude.Include;
  
  @Entity  
  @Table(name = "tbl_states")  
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
  @JsonInclude(Include.NON_NULL) public class States {
  
	  @Id  
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  
	  @Column(name ="state_id") private int stateId;
	  
	  @Column(name ="state_code") private int stateCode;
	  
	  @Column(name ="state_name") private String stateName;
	  
	  public int getStateId() { return stateId; }
	  
	  public void setStateId(int stateId) { this.stateId = stateId; }
	  
	  public int getStateCode() { return stateCode; }
	  
	  public void setStateCode(int stateCode) { this.stateCode = stateCode; }
	  
	  public String getStateName() { return stateName; }
	  
	  public void setStateName(String stateName) { this.stateName = stateName; }
  
}