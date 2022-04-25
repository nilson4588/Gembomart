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

import com.rest.model.Complaint;
import com.rest.model.Status;
import com.rest.service.ComplaintService;

@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class ComplaintController {

	@Autowired
    ComplaintService complaintService; 
	
	static final Logger log = Logger.getLogger(ComplaintController.class);
	
	@RequestMapping(value = "/saveOrUpdateComplaint", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	public  @ResponseBody Status saveOrUpdateComplaint(@RequestBody Complaint complaint) throws Exception {
	  
		  try { 
			    complaintService.saveOrUpdateComplaint(complaint);
			  	log.info("Complaint record saved successfully.");	
			  	
			  	return new Status(1, "Complaint registered.");
	  
		  } catch(Exception e) {  
			    log.error("Complaint failed to saved: "+ e.toString());
			    return new Status(0, "Complaint failed to registered.");
          }
	}
	
	@RequestMapping(value = "/getComplaint/{complaintId}", method = RequestMethod.GET) 
    public @ResponseBody Complaint getComplaintById(@PathVariable("complaintId") int complaintId ) throws Exception {
	  
		  Complaint complaint = null;
		  try {
		      log.info("complaint Details fetched"); 
		      complaint = complaintService.getComplaintById(complaintId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching complaint data : "+e.toString());
		  } 
		  return complaint;
	}
	
	@RequestMapping(value = "/getComplaintList", method = RequestMethod.POST)
	public @ResponseBody List<Complaint> getComplaintList(@RequestBody Complaint complaint) throws Exception {
	  
		  List<Complaint> complaintList = null;
		  try { 
			    complaintList = complaintService.getComplaintList(complaint);
			  	log.info("Display all complaint List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching complaintService data : "+e.toString()); 
		  } 
		  return complaintList;
	}
	
	@RequestMapping(value = "/changeComplaintStatus", method = RequestMethod.POST) 
	public void changeComplaintStatus(@RequestBody Complaint complaint) throws Exception {
	  
		  try { 
			    complaintService.changeComplaintStatus(complaint);
			    log.info("complaint status changed successfully.");   
		  
		  } catch (Exception e) { 
			  e.printStackTrace();
			    log.info("Exception at complaint status change process. "+e.toString());
		  } 
	} 
	
	@RequestMapping(value = "/complaintList", method = RequestMethod.GET)
	public @ResponseBody List<Complaint> complaintList() throws Exception {
	  
		  List<Complaint> complaintList = null;
		  try { 
			    complaintList = complaintService.complaintList();
			  	log.info("Display all complaint List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching complaintService data : "+e.toString()); 
		  } 
		  return complaintList;
	}
	 
}
