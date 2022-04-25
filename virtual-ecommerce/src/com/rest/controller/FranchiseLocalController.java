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

import com.rest.model.FranchiseLocal;
import com.rest.model.SellerOrder;
import com.rest.model.Status;
import com.rest.model.Status1;
import com.rest.model.SupplierOrder;
import com.rest.service.FranchiseLocalService;
import com.rest.utility.ConstantsUtil;
import com.rest.utility.EmailTemplate;
import com.rest.utility.SendSMS;
import com.rest.utility.sendEmail;

@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class FranchiseLocalController {

	   @Autowired 
	   FranchiseLocalService franchiseLocalService;
	   	
	   static final Logger log = Logger.getLogger(FranchiseLocalController.class);
	  	  
	   @RequestMapping(value = "/saveOrUpdateFranchiseLocal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	   public void saveOrUpdatefranchiseLocal(@RequestBody FranchiseLocal franchiseLocal) throws Exception {
	  
		  try { 
			    int franchiseLocalId = franchiseLocal.getLocalFranchiseId();
			    franchiseLocal.setEmpCode("gemboemp");
			    franchiseLocalService.saveOrUpdateFranchiseLocal(franchiseLocal);
			    
			    if(franchiseLocalId == 0) {
			    	
			    	long contactNo  = franchiseLocal.getFranchiseContactNo();
		    		String password = franchiseLocal.getLocalFranchisePassword();
			    	
			    	 SendSMS.sendSms(String.valueOf(contactNo), 
					    		"Dear "+franchiseLocal.getFullName()+", You are successfully registered with GemboMart. Your Login Details are: Registration ID : "+contactNo+" Password : "+password+" Thanks GemboMart");
						
					 SendSMS.sendSms(String.valueOf(contactNo), 
					    		"Thanks for registration. Your Account will be active in next 24 hrs.");
					 
					 sendEmail sd = new sendEmail();
					 sd.sendMail(ConstantsUtil.EMAIL_FROM, franchiseLocal.getFranchiseEmailId(), "Successful Registration", EmailTemplate.prepareCRMEmailTemplate(contactNo, password,ConstantsUtil.LOCAL_GEMBOMART));
			    }
			   
			    /*
				 * "Dear "+franchiseLocal.getFullName()+",\r\n" +
				 * "You are successfully registered with GemboMart.\r\n" +
				 * "Your Login Details are:\r\n" +
				 * "Registration ID : "+franchiseLocal.getFranchiseContactNo()+"\r\n" +
				 * "Password : "+franchiseLocal.getLocalFranchisePassword()+"\r\n" +
				 * "Thanks\r\n" + "GemboMart");
				 */
			  	log.info("franchise record saved successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("franchise failed to saved: "+ e.toString());
		  }
	   }
	  
	   @RequestMapping(value = "/employeeWiseFranchiseLocalRegistration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	   public void employeeWiseFranchiseLocalRegister(@RequestBody FranchiseLocal franchiseLocal) throws Exception {
	  
		  try { 
			    franchiseLocalService.saveOrUpdateFranchiseLocal(franchiseLocal);
			  	log.info("franchise record saved successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("franchise failed to saved: "+ e.toString());
		  }
	   }
	  	  
	   @RequestMapping(value = "/getLocalFranchise/{localFranchiseId}", method = RequestMethod.GET) 
	   public @ResponseBody FranchiseLocal getFranchiseLocalById(@PathVariable("localFranchiseId") int localFranchiseId ) throws Exception {
	  
		  FranchiseLocal franchise = null;
		  try {
		      log.info("Franchise Details fetched"); 
		      franchise = franchiseLocalService.getFranchiseLocalById(localFranchiseId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return franchise;
	   }
	  	  
	   @RequestMapping(value = "/franchiseLocalList", method = RequestMethod.GET)
	   public @ResponseBody List<FranchiseLocal> getFranchiseLocalList() throws Exception {
	  
		  List<FranchiseLocal> franchiseList = null;
		  try { 
			    franchiseList = franchiseLocalService.getFranchiseLocalList();
			  	log.info("Display all franchise List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching franchise district data : "+e.toString()); 
		  } 
		  return franchiseList;
	   }
	  	  
	   @RequestMapping(value = "/activateOrDeactivateFranchiseLocal", method = RequestMethod.POST) 
	   public void activateOrDeactivateFranchiseLocal(@RequestBody FranchiseLocal franchiseLocal) throws Exception {
	  
		  try { 
			    franchiseLocalService.activateOrDeactivateFranchiseLocal(franchiseLocal);
			    log.info("franchise activation status changed successfully.");   
		  
		  } catch (Exception e) { 
			  e.printStackTrace();
			    log.info("Exception at franchise activation status. "+e.toString());
		  } 
	   } 
	  	  
	   @RequestMapping(value = "/getSupplierOrderByFranchiseLocal/{localFranchiseId}", method = RequestMethod.GET) 
	   public @ResponseBody List<SupplierOrder> getSupplierOrderByFranchiseLocal(@PathVariable("localFranchiseId") int localFranchiseId ) throws Exception {
	  
		  List<SupplierOrder> supplierOrderList = null;
		  try {
		      log.info("franchise Details fetched"); 
		      supplierOrderList = franchiseLocalService.getSupplierOrderByFranchiseLocal(localFranchiseId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return supplierOrderList;
	   }
	  
	   @RequestMapping(value = "/getSellerOrderByFranchiseLocal/{localFranchiseId}", method = RequestMethod.GET) 
	   public @ResponseBody List<SellerOrder> getSellerOrderByFranchiseLocal(@PathVariable("localFranchiseId") int localFranchiseId ) throws Exception {
	  
		  List<SellerOrder> sellerOrderList = null;
		  try {
		      log.info("franchise Details fetched"); 
		      sellerOrderList = franchiseLocalService.getSellerOrderByFranchiseLocal(localFranchiseId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return sellerOrderList;
	   }
	  
	   @RequestMapping(value = "/getDeliveryOrderByFranchiseLocal/{localFranchiseId}", method = RequestMethod.GET) 
	   public @ResponseBody List<SellerOrder> getDeliveryOrderByFranchiseLocal(@PathVariable("localFranchiseId") int localFranchiseId ) throws Exception {
	  
		  List<SellerOrder> sellerOrderList = null;
		  try {
		      log.info("franchise Details fetched"); 
		      sellerOrderList = franchiseLocalService.getDeliveryOrderByFranchiseLocal(localFranchiseId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return sellerOrderList;
	   }	  
	  
	   @RequestMapping(value = "/loginLocalFranchise", method = RequestMethod.POST)
	   public @ResponseBody Status loginLocalFranchise(@RequestBody FranchiseLocal franchiseLocal) throws Exception {
		      
			  long   mobileNumber = franchiseLocal.getFranchiseContactNo();
			  String password     = franchiseLocal.getLocalFranchisePassword();
			  FranchiseLocal flocal = null;  	    
			  try {		  
				      flocal = franchiseLocalService.getFranchiseLocalByMobileAndPassword(mobileNumber, password);
				    		  
			  	      if(flocal != null) {
			     		     log.info("Login Successful");
			  	  		     return new Status(1, "Login successful.", flocal);		 
			  	      } else {
			  	  		     log.error("Wrong email or password."); 
			  	  		     return new Status(0, "Wrong mobile or password. Try again", flocal);
			  	  	  } 
			   
			  } catch(Exception ex) { 
			  		  log.error("Exception @Login "+ex.toString()); 
			  		  return new Status("Login failed. Try Again.", flocal);
			  }	  
	    }
	  
	    @RequestMapping(value = "/isLocalFranchiseMobileNumberExists/{franchiseContactNo}", method = RequestMethod.GET)
		public @ResponseBody Status isLocalFranchiseMobileNumberExists(@PathVariable("franchiseContactNo") long franchiseContactNo) throws Exception {
					  	    
			  try {			  
				   FranchiseLocal franchiseLocal = franchiseLocalService.getFranchiseLocalByMobileNo(franchiseContactNo);
			       if(franchiseLocal != null) {
			    	     log.info("Mobile Number already registered");
			  		     return new Status(0, "Mobile Number already registered.");		 
			       } else {
			  		     log.error("Available for registration."); 
			  		     return new Status(1, "Mobile Number available for registration.");
			  	   } 
			       
			  } catch(Exception ex) { 
				         ex.printStackTrace();
			  			 log.error("Exception @isMobileNumberExists "+ex.toString()); 
			  			 return new Status(0, "Mobile Number already registered."); 
			  }	  
	    }
	    
	    
	    @RequestMapping(value = "/getFranchiseLocalListByTaluka/{talukaCode}", method = RequestMethod.GET)
		public @ResponseBody Status1 getFranchiseLocalListByTaluka(@PathVariable("talukaCode") int talukaCode) throws Exception {
					  	    
			  try {
				  		List<FranchiseLocal> franchiseLocalList =  franchiseLocalService.getFranchiseLocalListByTaluka(talukaCode);
				  		 if(franchiseLocalList.size() > 0) {
				  			log.info("Fetched FranchiseLocal List By Taluka");
				  		     return new Status1(1, "Fetched FranchiseLocal List By Taluka", franchiseLocalList);	 
				       } else {
				  		     log.error("Available for registration."); 
				  		     return new Status1(0, "No record found.", franchiseLocalList);
				  	   } 
			    	     
			  		     
			  } catch(Exception ex) { 
				  
			  			 log.error("Exception @getFranchiseLocalListByTaluka "+ex.toString()); 
			  			 return new Status1(0, "Failed to fetch FranchiseLocal List By Taluka"); 
			  }
			 
	    }
	    
	  
}