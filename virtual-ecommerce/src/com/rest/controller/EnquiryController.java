package com.rest.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.model.Enquiry;
import com.rest.service.EnquiryService;

@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class EnquiryController {
	   
	  @Autowired
	  EnquiryService enquiryService;
	
	  static final Logger log = Logger.getLogger(EnquiryController.class);

	  @RequestMapping(value = "/saveOrUpdateEnquiry", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	  public void saveOrUpdateEnquiry(@RequestBody Enquiry enquiry) throws Exception {
	  
		  try { 
			    enquiryService.saveOrUpdateEnquiry(enquiry);
			  	log.info("Enquiry record saved successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("Enquiry failed to saved: "+ e.toString());
		  }
	  }
	  
	  @RequestMapping(value = "/getEnquiry/{enquiryId}", method = RequestMethod.GET) 
	  public @ResponseBody Enquiry  getEnquiryById(@PathVariable("enquiryId") int enquiryId ) throws Exception {
	  
		  Enquiry enquiry = null;
		  try {
		     log.info("Enquiry Details fetched"); 
		     enquiry = enquiryService.getEnquiryById(enquiryId);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Enquiry data : "+e.toString());
		  } 
		  return enquiry;
	  }
	  
	  @RequestMapping(value = "/enquiryList", method = RequestMethod.GET)
	  public @ResponseBody List<Enquiry> getEnquiryList() throws Exception {
	  
		  List<Enquiry> enquiryList = null;
		  try { 
			    enquiryList = enquiryService.getEnquiryList();
			  	log.info("Display all Enquiry List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching Enquiry data : "+e.toString()); 
		  } 
		  return enquiryList;
	  }
	  	  
	  @RequestMapping(value = "/activateOrDeactivateEnquiry", method = RequestMethod.POST) 
	  public void activateOrDeactivateEnquiry(@RequestBody Enquiry enquiry) throws Exception {
	  
		  try { 
			    enquiryService.activateOrDeactivateEnquiry(enquiry);
		        log.info("Enquiry activation status changed successfully.");   
		  
		  } catch (Exception e) { 
			  e.printStackTrace();
			    log.info("Exception at Enquiry activation status. "+e.toString());
		  } 
	  } 
}
