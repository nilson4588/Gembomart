package com.rest.model;
  
  
import java.io.Serializable; 
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.Id; 
import javax.persistence.Table; 
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude; 
import com.fasterxml.jackson.annotation.JsonInclude.Include;
  
import org.hibernate.annotations.Proxy; 
  
@Entity
@Table(name = "tbl_user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Proxy(lazy = false)
@JsonInclude(Include.NON_NULL) public class User implements Serializable {
  
  private static final long serialVersionUID = -7907643233783083441L;
  
  public User(){ }
  
  @Id  
  @GeneratedValue  
  @Column(name ="user_id") private int userId;
  
  @Column(name ="user_name") private String userName;
  
  @Column(name ="user_email") private String userEmail;
  
  @Column(name ="user_mobile") private String userMobile;  
  
  @Column(name ="user_password") private String userPassword;
  
  @Column(name ="user_otp") private String userOTP;
  
  @Column(name ="user_status") private int userStatus;
  
  @Column(name ="user_type") private int userType;
  
  @Column(name ="city_id") private int cityId;
  
  @Column(name ="pincode") private String pincode;
  
  @Column(name ="user_address") private String userAddress;
  
  @Column(name ="user_gender") private int userGender;
  
  @Column(name ="date_of_birth") private String dateOfBirth;  
   
  @Column(name ="occupation") private String occupation;
  
  @Column(name ="home_contact_number") private String homeContactNumber;
  
  @Column(name ="work_location_name") private String workLocationName;
  
  @Column(name ="work_location_address") private String workLocationAddress;
  
  @Column(name ="work_location_contact_number") private String workLocationContactNumber;
  
  @Column(name ="device") private String device;
   
  @Column(name ="device_id") private String deviceId;
   
  @Column(name ="os") private String os;
  
  @Column(name ="location") private String location;
  
  @Column(name ="created_datetime") private String createdDatetime;
   
  @Column(name ="updated_datetime") private String updatedDatetime;
    
  @Transient private String emailOrMobile;
  
  @Transient private String newUserPassword;

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getUserMobile() {
		return userMobile;
	}
	
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getUserOTP() {
		return userOTP;
	}
	
	public void setUserOTP(String userOTP) {
		this.userOTP = userOTP;
	}
	
	public int getUserStatus() {
		return userStatus;
	}
	
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	
	public int getUserType() {
		return userType;
	}
	
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	public int getCityId() {
		return cityId;
	}
	
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	public String getPincode() {
		return pincode;
	}
	
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	public String getUserAddress() {
		return userAddress;
	}
	
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	public int getUserGender() {
		return userGender;
	}
	
	public void setUserGender(int userGender) {
		this.userGender = userGender;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getOccupation() {
		return occupation;
	}
	
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	public String getHomeContactNumber() {
		return homeContactNumber;
	}
	
	public void setHomeContactNumber(String homeContactNumber) {
		this.homeContactNumber = homeContactNumber;
	}
	
	public String getWorkLocationName() {
		return workLocationName;
	}
	
	public void setWorkLocationName(String workLocationName) {
		this.workLocationName = workLocationName;
	}
	
	public String getWorkLocationAddress() {
		return workLocationAddress;
	}
	
	public void setWorkLocationAddress(String workLocationAddress) {
		this.workLocationAddress = workLocationAddress;
	}
	
	public String getWorkLocationContactNumber() {
		return workLocationContactNumber;
	}
	
	public void setWorkLocationContactNumber(String workLocationContactNumber) {
		this.workLocationContactNumber = workLocationContactNumber;
	}
	
	public String getDevice() {
		return device;
	}
	
	public void setDevice(String device) {
		this.device = device;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getOs() {
		return os;
	}
	
	public void setOs(String os) {
		this.os = os;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
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
		this.updatedDatetime = updatedDatetime;
	}
	
	public String getEmailOrMobile() {
		return emailOrMobile;
	}
	
	public void setEmailOrMobile(String emailOrMobile) {
		this.emailOrMobile = emailOrMobile;
	}
	
	public String getNewUserPassword() {
		return newUserPassword;
	}
	
	public void setNewUserPassword(String newUserPassword) {
		this.newUserPassword = newUserPassword;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userMobile="
				+ userMobile + ", userPassword=" + userPassword + ", userOTP=" + userOTP + ", userStatus=" + userStatus
				+ ", userType=" + userType + ", cityId=" + cityId + ", pincode=" + pincode + ", userAddress="
				+ userAddress + ", userGender=" + userGender + ", dateOfBirth=" + dateOfBirth + ", occupation="
				+ occupation + ", homeContactNumber=" + homeContactNumber + ", workLocationName=" + workLocationName
				+ ", workLocationAddress=" + workLocationAddress + ", workLocationContactNumber="
				+ workLocationContactNumber + ", device=" + device + ", deviceId=" + deviceId + ", os=" + os
				+ ", location=" + location + ", createdDatetime=" + createdDatetime + ", updatedDatetime="
				+ updatedDatetime + ", emailOrMobile=" + emailOrMobile + ", newUserPassword=" + newUserPassword + "]";
	}  
}