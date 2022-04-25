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

import com.rest.model.Districts;
import com.rest.model.FranchiseDistrict;
import com.rest.model.FranchiseTaluka;
import com.rest.model.FranchiseType;
import com.rest.model.SellerOrder;
import com.rest.model.Status;
import com.rest.model.SupplierOrder;
import com.rest.service.FranchiseDistrictService;
import com.rest.utility.ConstantsUtil;
import com.rest.utility.EmailTemplate;
import com.rest.utility.SendSMS;
import com.rest.utility.sendEmail;

@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class FranchiseDistrictController {
	
	  @Autowired 
	  FranchiseDistrictService franchiseDistrictService;
	   
	  static final Logger log = Logger.getLogger(FranchiseDistrictController.class);
	  
	  @RequestMapping(value = "/saveOrUpdateFranchiseDistrict", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	  public void saveOrUpdatefranchise(@RequestBody FranchiseDistrict franchiseDistrict) throws Exception {
	  
		  try { 
			   int franchiseDistrictId = franchiseDistrict.getDistrictFranchiseId();
			    franchiseDistrictService.saveOrUpdateFranchiseDistrict(franchiseDistrict);
			    
			    
			    if(franchiseDistrictId == 0) {
			    	
			    		long contactNo  = franchiseDistrict.getFranchiseContactNo();
			    		String password = franchiseDistrict.getDistrictFranchisePassword();
			    	
			    	    SendSMS.sendSms(String.valueOf(contactNo), 
					    		"Dear "+franchiseDistrict.getFullName()+", You are successfully registered with GemboMart. Your Login Details are: Registration ID : "+contactNo+" Password : "+password+" Thanks GemboMart");
					    
					    SendSMS.sendSms(String.valueOf(contactNo), 
					    		"Thanks for registration. Your Account will be active in next 24 hrs.");
					    
					    sendEmail sd = new sendEmail();
					    sd.sendMail(ConstantsUtil.EMAIL_FROM, franchiseDistrict.getFranchiseEmailId(), "Successful Registration", EmailTemplate.prepareCRMEmailTemplate(contactNo, password,ConstantsUtil.DISTRICT_GEMBOMART));
			    }
			   
			    			    
				/*
				 * "Dear "++",\r\n" + "You are successfully registered with GemboMart.\r\n" +
				 * "Your Login Details are:\r\n" + "Registration ID : "++"\r\n" +
				 * "Password : "++"\r\n" + "Thanks\r\n" + "GemboMart");
				 */
			  	log.info("franchise record saved successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("franchise failed to saved: "+ e.toString());
		  }
	  }
	  
	  @RequestMapping(value = "/getDistrictFranchise/{districtFranchiseId}", method = RequestMethod.GET) 
	  public @ResponseBody FranchiseDistrict  getFranchiseDistrictById(@PathVariable("districtFranchiseId") int districtFranchiseId ) throws Exception {
	  
		  FranchiseDistrict franchise = null;
		  try {
		      log.info("franchise Details fetched"); 
		      franchise = franchiseDistrictService.getFranchiseDistrictById(districtFranchiseId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return franchise;
	  }
	  
	  @RequestMapping(value = "/getFranchiseDistrictListByNationalId/{nationalFranchiseId}", method = RequestMethod.GET) 
	  public @ResponseBody List<FranchiseDistrict>  getFranchiseDistrictListByNationalId(@PathVariable("nationalFranchiseId") int nationalFranchiseId ) throws Exception {
	  
		  List<FranchiseDistrict> franchise = null;
		  try {
		      log.info("franchise Details fetched"); 
		      franchise = franchiseDistrictService.getFranchiseDistrictListByNationalId(nationalFranchiseId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return franchise;
	  }
	  
	  @RequestMapping(value = "/franchiseDistrictList", method = RequestMethod.GET)
	  public @ResponseBody List<FranchiseDistrict> getFranchiseDistrictList() throws Exception {
	  
		  List<FranchiseDistrict> franchiseList = null;
		  try { 
			    franchiseList = franchiseDistrictService.getFranchiseDistrictList();
			  	log.info("Display all franchise List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching franchise district data : "+e.toString()); 
		  } 
		  return franchiseList;
	  }
	  
	  
	  @RequestMapping(value = "/activateOrDeactivateFranchiseDistrict", method = RequestMethod.POST) 
	  public void activateOrDeactivatefranchise(@RequestBody FranchiseDistrict franchiseDistrict) throws Exception {
	  
		  try { 
			    franchiseDistrictService.activateOrDeactivateFranchiseDistrict(franchiseDistrict);
			    log.info("franchise activation status changed successfully.");   
		  
		  } catch (Exception e) { 
			  e.printStackTrace();
			    log.info("Exception at franchise activation status. "+e.toString());
		  } 
	  } 
	  
	  @RequestMapping(value = "/franchiseTypeList", method = RequestMethod.GET)
	  public @ResponseBody List<FranchiseType> getfranchiseTypeList() throws Exception {
	  
		  List<FranchiseType> franchiseTypeList = null;
		  try { 
			    franchiseTypeList = franchiseDistrictService.getfranchiseTypeList();
			  	log.info("Display all franchise Type List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching data : "+e.toString()); 
		  } 
		  return franchiseTypeList;
	  }
	  
	  @RequestMapping(value = "/getTalukaFranchiseListByDistrict/{districtFranchiseId}", method = RequestMethod.GET) 
	  public @ResponseBody List<FranchiseTaluka> getTalukaFranchiseListByDistrict(@PathVariable("districtFranchiseId") int districtFranchiseId ) throws Exception {
	  
		  List<FranchiseTaluka> franchiseList = null;
		  try {
		      log.info("franchise Details fetched"); 
		      franchiseList = franchiseDistrictService.getTalukaFranchiseListByDistrict(districtFranchiseId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return franchiseList;
	  }	 
	  
	  @RequestMapping(value = "/getSupplierOrderByFranchiseDistrict/{districtFranchiseId}", method = RequestMethod.GET) 
	  public @ResponseBody List<SupplierOrder> getSupplierOrderByFranchiseDistrict(@PathVariable("districtFranchiseId") int districtFranchiseId ) throws Exception {
	  
		  List<SupplierOrder> supplierOrderList = null;
		  try {
		      log.info("franchise Details fetched"); 
		      supplierOrderList = franchiseDistrictService.getSupplierOrderByFranchiseDistrict(districtFranchiseId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return supplierOrderList;
	  }
	  
	  @RequestMapping(value = "/getSellerOrderByFranchiseDistrict/{districtFranchiseId}", method = RequestMethod.GET) 
	  public @ResponseBody List<SellerOrder> getSellerOrderByFranchiseDistrict(@PathVariable("districtFranchiseId") int districtFranchiseId ) throws Exception {
	  
		  List<SellerOrder> sellerOrderList = null;
		  try {
		      log.info("franchise Details fetched"); 
		      sellerOrderList = franchiseDistrictService.getSellerOrderByFranchiseDistrict(districtFranchiseId);
		    		  
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return sellerOrderList;
	  }
	  
	  @RequestMapping(value = "/getDeliveryOrderByFranchiseDistrict/{districtFranchiseId}", method = RequestMethod.GET) 
	  public @ResponseBody List<SellerOrder> getDeliveryOrderByFranchiseDistrict(@PathVariable("districtFranchiseId") int districtFranchiseId ) throws Exception {
	  
		  List<SellerOrder> sellerOrderList = null;
		  try {
		      log.info("franchise Details fetched"); 
		      sellerOrderList = franchiseDistrictService.getDeliveryOrderByFranchiseDistrict(districtFranchiseId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return sellerOrderList;
	  }	
	  
	  @RequestMapping(value = "/loginDistrictFranchise", method = RequestMethod.POST)
	  public @ResponseBody Status loginDistrictFranchise(@RequestBody FranchiseDistrict franchiseDistrict) throws Exception {
		      
			  long mobileNumber = franchiseDistrict.getFranchiseContactNo();
			  String password = franchiseDistrict.getDistrictFranchisePassword();
			  FranchiseDistrict flocal   = null;  	    
			  try {		  
				       flocal = franchiseDistrictService.getFranchiseDistrictByMobileAndPassword(mobileNumber, password);
				    		  
			  	      if(flocal != null) {
			     		     log.info("Login Successful");
			  	  		     return new Status(1,"Login successful.", flocal);		 
			  	      } else {
			  	  		     log.error("Wrong email or password."); 
			  	  		     return new Status(0,"Wrong email or password. Try again", flocal);
			  	  	  } 
			   
			  } catch(Exception ex) { 
			  		  log.error("Exception @Login"+ex.toString()); 
			  		  return new Status("Login failed. Try Again.", flocal);
			  }	  
	  }
	  
	  
	  @RequestMapping(value = "/isDistrictFranchiseMobileNumberExists/{franchiseContactNo}", method = RequestMethod.GET)
	  public @ResponseBody Status isDistrictFranchiseMobileNumberExists(@PathVariable("franchiseContactNo") long franchiseContactNo) throws Exception {
					  	    
			  try {
			  
				   FranchiseDistrict franchiseDistrict = franchiseDistrictService.getFranchiseDistrictByMobileNo(franchiseContactNo);
			       if(franchiseDistrict != null) {
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
	  
	    @RequestMapping(value = "/districtListByCategoryState/{categoryId}/{stateCode}", method = RequestMethod.GET)
		public List<Districts> districtListByCategoryState(@PathVariable("categoryId") int categoryId, @PathVariable("stateCode") int stateCode) throws Exception {
				
	    	 List<Districts> districtsList = null;	    	 
			  try {			  
				   districtsList = franchiseDistrictService.districtListByCategoryIdStateCode(categoryId, stateCode);
			       log.error("district List By CategoryId & state Code fetched."); 
			  		     	  
			  } catch(Exception ex) { 
				   ex.printStackTrace();
			  	   log.error("Exception @districtListByCategoryState :"+ex.toString()); 
			  	  
			  }	  
			  return districtsList;
		}
}