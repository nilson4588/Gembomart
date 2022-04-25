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
@Table(name = "tbl_complaint")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  
@JsonInclude(Include.NON_NULL)
public class Complaint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="complaint_id") 
	private int complaintId;	
	
	@Column(name ="mobile_number") 
	private long mobileNumber;
	
	@Column(name ="complaint_type") 
	private int complaintType;
	
	@Transient
	private String complaintTypeDesc;
	
	@Column(name ="complaint_description") 
	private String complaintDescription;
	
	@Column(name ="complaint_datetime") 
	private String complaintDatetime;
	
	@Column(name ="complaint_status") 
	private String complaintStatus;
	
	@Column(name ="complaint_solved_datetime") 
	private String complaintSolvedDatetime;
	

	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(int complaintType) {
		this.complaintType = complaintType;
	}

	public String getComplaintTypeDesc() {
		return complaintTypeDesc;
	}

	public void setComplaintTypeDesc(String complaintTypeDesc) {
		this.complaintTypeDesc = complaintTypeDesc;
	}

	public String getComplaintDescription() {
		return complaintDescription;
	}

	public void setComplaintDescription(String complaintDescription) {
		this.complaintDescription = complaintDescription;
	}

	public String getComplaintDatetime() {
		return complaintDatetime;
	}

	public void setComplaintDatetime(String complaintDatetime) {
		this.complaintDatetime = complaintDatetime;
	}

	public String getComplaintStatus() {
		return complaintStatus;
	}

	public void setComplaintStatus(String complaintStatus) {
		this.complaintStatus = complaintStatus;
	}
	
	public String getComplaintSolvedDatetime() {
		return complaintSolvedDatetime;
	}

	public void setComplaintSolvedDatetime(String complaintSolvedDatetime) {
		this.complaintSolvedDatetime = complaintSolvedDatetime;
	}

	@Override
	public String toString() {
		return "Complaint [complaintId=" + complaintId + ", mobileNumber=" + mobileNumber + ", complaintType="
				+ complaintType + ", complaintTypeDesc=" + complaintTypeDesc + ", complaintDescription="
				+ complaintDescription + ", complaintDatetime=" + complaintDatetime + ", complaintStatus="
				+ complaintStatus + ", complaintSolvedDatetime=" + complaintSolvedDatetime + "]";
	}
}