/*
 * package com.rest.model;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.Id; import
 * javax.persistence.Table;
 * 
 * import org.hibernate.annotations.Proxy;
 * 
 * import com.fasterxml.jackson.annotation.JsonIgnoreProperties; import
 * com.fasterxml.jackson.annotation.JsonInclude; import
 * com.fasterxml.jackson.annotation.JsonInclude.Include;
 * 
 * @Entity
 * 
 * @Table(name = "tbl_area")
 * 
 * @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
 * 
 * @Proxy(lazy = false)
 * 
 * @JsonInclude(Include.NON_NULL) public class CityArea {
 * 
 * @Id
 * 
 * @GeneratedValue
 * 
 * @Column(name ="area_id") private int areaId;
 * 
 * @Column(name ="area_name") private String areaName;
 * 
 * @Column(name ="city_id") private int cityId;
 * 
 * public int getAreaId() { return areaId; }
 * 
 * public void setAreaId(int areaId) { this.areaId = areaId; }
 * 
 * public String getAreaName() { return areaName; }
 * 
 * public void setAreaName(String areaName) { this.areaName = areaName; }
 * 
 * public int getCityId() { return cityId; }
 * 
 * public void setCityId(int cityId) { this.cityId = cityId; }
 * 
 * @Override public String toString() { return "CityArea [areaId=" + areaId +
 * ", areaName=" + areaName + ", cityId=" + cityId + "]"; } }
 */