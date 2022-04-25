
  package com.rest.model;
  
  import java.io.Serializable;
  
  import javax.persistence.Column; import javax.persistence.Entity; import
  javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
  import javax.persistence.Id; import javax.persistence.Table;
  
  import org.hibernate.annotations.Proxy;
  
  import com.fasterxml.jackson.annotation.JsonIgnoreProperties; import
  com.fasterxml.jackson.annotation.JsonInclude; import
  com.fasterxml.jackson.annotation.JsonInclude.Include;
  
  @Entity
  
  @Table(name = "tbl_city")
  
  @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
  
  @Proxy(lazy = false)
  
  @JsonInclude(Include.NON_NULL) public class City implements Serializable {
  
  private static final long serialVersionUID = 5604881076406083328L;
  
  public City() { }
  
  @Id
  
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  
  @Column(name = "city_id") private int cityId;
  
  @Column(name = "city_name") private String cityName;
  
  @Column(name="is_active") private int isActive;
  
  public int getCityId() { return cityId; }
  
  public void setCityId(int cityId) { this.cityId = cityId; }
  
  public String getCityName() { return cityName; }
  
  public void setCityName(String cityName) { this.cityName = cityName; }
  
  public int isActive() { return isActive; }
  
  public void setActive(int isActive) { this.isActive = isActive; }
  
  @Override public String toString() { return "City [cityId=" + cityId +
  ", cityName=" + cityName + ", isActive=" + isActive + "]"; } }
 