
  package com.rest.model;
  
  import javax.persistence.Column; 
  import javax.persistence.Entity; 
  import javax.persistence.GeneratedValue;
  import javax.persistence.GenerationType;
  import javax.persistence.Id;
  import javax.persistence.Table;
  
  import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
  
  @Entity
  @Table(name = "tbl_category")
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
  public class Category {
  
  @Id  
  @GeneratedValue(strategy = GenerationType.IDENTITY)  
  @Column(name ="category_id") 
  private int id;
  
  @Column(name ="category_name") 
  private String categoryName;
  
  @Column(name="is_active") 
  private int isActive;
  
  @Column(name ="created_datetime") 
  private String createdDatetime;
  
  @Column(name ="updated_datetime")
  private String updatedDatetime;
  
  @Column(name ="industry_id") 
  private int categoryIndustryId;
	/*
	 * @Column(name ="category_industry") private String categoryIndustry;
	 */
  
    
  public int getCategoryIndustryId() {
	return categoryIndustryId;
  }
	
  public void setCategoryIndustryId(int categoryIndustryId) {
		this.categoryIndustryId = categoryIndustryId;
  }
	
	/*
	 * public String getCategoryIndustry() { return categoryIndustry; }
	 * 
	 * public void setCategoryIndustry(String categoryIndustry) {
	 * this.categoryIndustry = categoryIndustry; }
	 */

  public String getCreatedDatetime() { 
	  return createdDatetime; 
  }
  
  public void setCreatedDatetime(String createdDatetime) { 
	  this.createdDatetime = createdDatetime; 
  }
  
  public String getUpdatedDatetime() { 
	  return updatedDatetime; 
  }
  
  public void setUpdatedDatetime(String updatedDatetime) { 
	  this.updatedDatetime  = updatedDatetime; 
  }
     
  public String getCategoryName() { 
	  return categoryName;
  }
  
  public void setCategoryName(String categoryName) { 
	  this.categoryName =  categoryName; 
  }
  
  public int getIsActive() { 
	  return isActive; 
  }
  
  public void setIsActive(int isActive) { 
	  this.isActive = isActive; 
  }
  
  @Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", isActive=" + isActive + ", createdDatetime="
				+ createdDatetime + ", updatedDatetime=" + updatedDatetime + "]";
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	} 
}