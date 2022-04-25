package com.rest.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.Enquiry;

@Component
public interface EnquiryDao {
	
	  public int saveOrUpdateEnquiry(Enquiry enquiry) throws Exception;
	  
	  public Enquiry getEnquiryById(int enquiryId) throws Exception;
	  
	  public List<Enquiry> getEnquiryList() throws Exception;
	  
	  public int activateOrDeactivateEnquiry(Enquiry enquiry) throws Exception;
}
