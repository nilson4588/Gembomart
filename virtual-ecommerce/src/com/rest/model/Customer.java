package com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity  
@Table(name = "tbl_customer")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
@JsonInclude(Include.NON_NULL)
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="customer_id") 
	private int id;
		
	@Column(name ="first_name") 
	private String fname;
	
	@Column(name ="last_name") 
	private String lname;	
	
	@Column(name ="email_id") 
	private String email;	
		
	@Column(name="mobile_no")
	private String mobileno;
	
	@Column(name="whatsapp_no")
	private String whatsappno;
	
	@Column(name="password")
	private String pwd;
	
	@Transient	
	private String cpwd;
	
	@Column(name="customer_address")
	private String addr;
	
	@Column(name="customer_city")
	private int city;
	
	@Column(name="customer_area")
	private String area;
	
	@Column(name="customer_pincode")
	private int pincode;
	
	@Column(name="is_active")
	private int isActive;
	
	@Column(name ="created_datetime") 
	private String createdDatetime;
	
	@Column(name ="updated_datetime") 
	private String updatedDatetime;
	
	@Column(name="customer_state")
	private int state;
	
	@Column(name="customer_taluka")
	private int taluka;
	
	
	 @Column(name ="token") private String token;
	
	
	@Transient
	private String stateName;
	
	@Transient
	private String cityName;
	
	@Transient
	private String talukaName;
	
	
	public String getToken() { return token; }
	public void setToken(String token) { this.token = token; }
	 

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getTaluka() {
		return taluka;
	}

	public void setTaluka(int taluka) {
		this.taluka = taluka;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getTalukaName() {
		return talukaName;
	}

	public void setTalukaName(String talukaName) {
		this.talukaName = talukaName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getWhatsappno() {
		return whatsappno;
	}

	public void setWhatsappno(String whatsappno) {
		this.whatsappno = whatsappno;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCpwd() {
		return cpwd;
	}

	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(String updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", mobileno="
				+ mobileno + ", whatsappno=" + whatsappno + ", pwd=" + pwd + ", cpwd=" + cpwd + ", addr=" + addr
				+ ", city=" + city + ", area=" + area + ", pincode=" + pincode + ", isActive=" + isActive
				+ ", createdDatetime=" + createdDatetime + ", updatedDatetime=" + updatedDatetime + ", cityName="
				+ cityName + "]";
	}
}