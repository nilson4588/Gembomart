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

import com.rest.model.FranchiseNational;
import com.rest.model.States;
import com.rest.model.Status;
import com.rest.service.FranchiseNationalService;
import com.rest.utility.ConstantsUtil;
import com.rest.utility.EmailTemplate;
import com.rest.utility.SendSMS;
import com.rest.utility.sendEmail;

@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class FranchiseNationalController {
	  
	  @Autowired 
	  FranchiseNationalService franchiseNationalService;
	   
	  static final Logger log = Logger.getLogger(FranchiseNationalController.class);
	  	  
	  @RequestMapping(value = "/saveOrUpdateFranchiseNational", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	  public void saveOrUpdateNationalfranchise(@RequestBody FranchiseNational franchiseNational) throws Exception {
	  
		  try { 
			  
			    int franchiseNationalId = franchiseNational.getNationalFranchiseId();
			    franchiseNationalService.saveOrUpdateFranchiseNational(franchiseNational);			    
			    
			    if(franchiseNationalId == 0) {
			    	
			    	long contactNo  = franchiseNational.getFranchiseContactNo();
		    		String password = franchiseNational.getNationalFranchisePassword();
			    	
			    	SendSMS.sendSms(String.valueOf(contactNo), 
				    		"Dear "+franchiseNational.getFullName()+", You are successfully registered with GemboMart. Your Login Details are: Registration ID : "+contactNo+" Password : "+password+" Thanks GemboMart");
					
				    SendSMS.sendSms(String.valueOf(contactNo), 
				    		"Thanks for registration. Your Account will be active in next 24 hrs.");
			    
				    sendEmail sd = new sendEmail();
					sd.sendMail(ConstantsUtil.EMAIL_FROM, franchiseNational.getFranchiseEmailId(), "Successful Registration", EmailTemplate.prepareCRMEmailTemplate(contactNo, password,ConstantsUtil.NATIONAL_GEMBOMART));
			    }
			    
			    /*
				 * "Dear "+franchiseNational.getFullName()+",\r\n" +
				 * "You are successfully registered with GemboMart.\r\n" +
				 * "Your Login Details are:\r\n" +
				 * "Registration ID : "+franchiseNational.getFranchiseContactNo()+"\r\n" +
				 * "Password : "+franchiseNational.getNationalFranchisePassword()+"\r\n" +
				 * "Thanks\r\n" + "GemboMart");
				 */
			  	log.info("franchise record saved successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("franchise failed to saved: "+ e.toString());
		  }
	  }
	  
	  @RequestMapping(value = "/getNationalFranchise/{nationalFranchiseId}", method = RequestMethod.GET) 
	  public @ResponseBody FranchiseNational  getNationalFranchiseById(@PathVariable("nationalFranchiseId") int nationalFranchiseId ) throws Exception {
	  
		  FranchiseNational franchise = null;
		  try {
		      log.info("franchise Details fetched"); 
		      franchise = franchiseNationalService.getFranchiseNationalById(nationalFranchiseId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return franchise;
	  }
	  
	  @RequestMapping(value = "/franchiseNationalList", method = RequestMethod.GET)
	  public @ResponseBody List<FranchiseNational> getFranchiseNationalList() throws Exception {
	  
		  List<FranchiseNational> franchiseList = null;
		  try { 
			    franchiseList = franchiseNationalService.getFranchiseNationalList();
			  	log.info("Display all franchise List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching franchise district data : "+e.toString()); 
		  } 
		  return franchiseList;
	  }
	  	  
	  @RequestMapping(value = "/activateOrDeactivateFranchiseNational", method = RequestMethod.POST) 
	  public void activateOrDeactivateFranchiseNational(@RequestBody FranchiseNational franchiseNational) throws Exception {
	  
		  try { 
			    franchiseNationalService.activateOrDeactivateFranchiseNational(franchiseNational);
			    log.info("franchise activation status changed successfully.");   
		  
		  } catch (Exception e) { 
			  e.printStackTrace();
			    log.info("Exception at franchise activation status. "+e.toString());
		  } 
	  } 
	  
	  @RequestMapping(value = "/loginNationalFranchise", method = RequestMethod.POST)
	  public @ResponseBody Status loginNationalFranchise(@RequestBody FranchiseNational franchiseNational) throws Exception {
		      
			  long mobileNumber = franchiseNational.getFranchiseContactNo();
			  String password = franchiseNational.getNationalFranchisePassword();
			  FranchiseNational flocal   = null;  	    
			  try {		  
				      flocal = franchiseNationalService.getFranchiseNationalByMobileAndPassword(mobileNumber, password);
				    		  
			  	      if(flocal != null) {
			     		     log.info("Login Successful");
			  	  		     return new Status(1, "Login successful.", flocal);		 
			  	      } else {
			  	  		     log.error("Wrong email or password."); 
			  	  		     return new Status(0, "Wrong email or password. Try again", flocal);
			  	  	  } 
			   
			  } catch(Exception ex) { 
			  		  log.error("Exception @Login"+ex.toString()); 
			  		  return new Status("Login failed. Try Again.", flocal);
			  }	  
		}
	  
	    @RequestMapping(value = "/isFranchiseNationalMobileNumberExists/{franchiseContactNo}", method = RequestMethod.GET)
		public @ResponseBody Status isFranchiseNationalMobileNumberExists(@PathVariable("franchiseContactNo") long franchiseContactNo) throws Exception {
					  	    
			  try {			  
				   FranchiseNational franchiseNational = franchiseNationalService.getFranchiseNationalByMobileNo(franchiseContactNo);
			       if(franchiseNational != null) {
			    	     log.info("Mobile Number already registered");
			  		     return new Status(0, "Mobile Number already registered.");		 
			       } else {
			  		     log.error("Available for registration."); 
			  		     return new Status(1, "Mobile Number available for registration.");
			  	   } 			  
			  } catch(Exception ex) { 
				   ex.printStackTrace();
			  	   log.error("Exception @isMobileNumberExists :"+ex.toString()); 
			  	   return new Status(0, "Mobile Number already registered."); 
			  }	  
		}
	    
	    @RequestMapping(value = "/statesListByCategoryId/{categoryId}", method = RequestMethod.GET)
		public List<States> statesListByCategoryId(@PathVariable("categoryId") int categoryId) throws Exception {
				
	    	 List<States> statesList = null;	    	 
			  try {			  
				    statesList = franchiseNationalService.statesListByCategoryId(categoryId);
			       log.error("States List By CategoryId fetched."); 
			  		     	  
			  } catch(Exception ex) { 
				   ex.printStackTrace();
			  	   log.error("Exception @statesListByCategoryId :"+ex.toString()); 
			  	  
			  }	  
			  return statesList;
		}
}