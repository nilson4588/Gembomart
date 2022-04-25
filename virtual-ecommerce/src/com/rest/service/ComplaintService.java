package com.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.Complaint;

@Component
public interface ComplaintService {

	  public int saveOrUpdateComplaint(Complaint complaint) throws Exception;
	  
	  public Complaint getComplaintById(int complaintId) throws Exception;
	  
	  public List<Complaint> getComplaintList(Complaint complaint) throws Exception;	  
	  
	  public void changeComplaintStatus(Complaint complaint) throws Exception;
	  
	  public List<Complaint> complaintList() throws Exception;
}
