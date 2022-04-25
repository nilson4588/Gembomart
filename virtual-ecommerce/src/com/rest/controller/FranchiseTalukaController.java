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
import com.rest.model.FranchiseTaluka;
import com.rest.model.SellerOrder;
import com.rest.model.Status;
import com.rest.model.SupplierOrder;
import com.rest.model.Taluka;
import com.rest.service.FranchiseTalukaService;
import com.rest.utility.ConstantsUtil;
import com.rest.utility.EmailTemplate;
import com.rest.utility.SendSMS;
import com.rest.utility.sendEmail;

@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class FranchiseTalukaController {

	  @Autowired 
	  FranchiseTalukaService franchiseTalukaService;
	   
	  static final Logger log = Logger.getLogger(FranchiseTalukaController.class);
	  
	  @RequestMapping(value = "/saveOrUpdateFranchiseTaluka", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	  public void saveOrUpdatefranchise(@RequestBody FranchiseTaluka franchiseTaluka) throws Exception {
	  
		  try { 
			  int franchiseTalukaId = franchiseTaluka.getTalukaFranchiseId();
			  franchiseTalukaService.saveOrUpdateFranchiseTaluka(franchiseTaluka);			 
			  if(franchiseTalukaId == 0) {
				  
				  long contactNo  = franchiseTaluka.getFranchiseContactNo();
		    	  String password = franchiseTaluka.getTalukaFranchisePassword();
				  
				  SendSMS.sendSms(String.valueOf(contactNo), 
						  "Dear "+franchiseTaluka.getFullName()+", You are successfully registered with GemboMart. Your Login Details are: Registration ID : "+contactNo+" Password : "+password+" Thanks GemboMart");

				  SendSMS.sendSms(String.valueOf(contactNo), 
				    		"Thanks for registration. Your Account will be active in next 24 hrs.");
			  
				  sendEmail sd = new sendEmail();
				  sd.sendMail(ConstantsUtil.EMAIL_FROM, franchiseTaluka.getFranchiseEmailId(), "Successful Registration", EmailTemplate.prepareCRMEmailTemplate(contactNo, password,ConstantsUtil.TALUKA_GEMBOMART));
			   
			  }
			  
				/*
				 * "Dear "+franchiseTaluka.getFullName()+",\r\n" +
				 * "You are successfully registered with GemboMart.\r\n" +
				 * "Your Login Details are:\r\n" +
				 * "Registration ID : "+franchiseTaluka.getFranchiseContactNo()+"\r\n" +
				 * "Password : "+franchiseTaluka.getTalukaFranchisePassword()+"\r\n" +
				 * "Thanks\r\n" + "GemboMart");
				 */
			  	log.info("franchise record saved successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("franchise failed to saved: "+ e.toString());
		  }
	  }
	  
	  @RequestMapping(value = "/getTalukaFranchise/{talukaFranchiseId}", method = RequestMethod.GET) 
	  public @ResponseBody FranchiseTaluka  getTalukaFranchiseById(@PathVariable("talukaFranchiseId") int talukaFranchiseId ) throws Exception {
	  
		  FranchiseTaluka franchise = null;
		  try {
		      log.info("franchise Details fetched"); 
		      franchise = franchiseTalukaService.getFranchiseTalukaById(talukaFranchiseId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return franchise;
	  }
	  
	  @RequestMapping(value = "/franchiseTalukaList", method = RequestMethod.GET)
	  public @ResponseBody List<FranchiseTaluka> getFranchiseTalukaList() throws Exception {
	  
		  List<FranchiseTaluka> franchiseList = null;
		  try { 
			    franchiseList = franchiseTalukaService.getFranchiseTalukaList();
			  	log.info("Display all franchise List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching franchise district data : "+e.toString()); 
		  } 
		  return franchiseList;
	  }
	  
	  
	  @RequestMapping(value = "/activateOrDeactivateFranchiseTaluka", method = RequestMethod.POST) 
	  public void activateOrDeactivateFranchiseTaluka(@RequestBody FranchiseTaluka franchiseTaluka) throws Exception {
	  
		  try { 
			    franchiseTalukaService.activateOrDeactivateFranchiseTaluka(franchiseTaluka);
			    log.info("franchise activation status changed successfully.");   
		  
		  } catch (Exception e) { 
			  e.printStackTrace();
			    log.info("Exception at franchise activation status. "+e.toString());
		  } 
	  } 
	  
	  
	  @RequestMapping(value = "/getLocalFranchiseListByTaluka/{talukaFranchiseId}", method = RequestMethod.GET) 
	  public @ResponseBody List<FranchiseLocal>  getLocalFranchiseListByTaluka(@PathVariable("talukaFranchiseId") int talukaFranchiseId ) throws Exception {
	  
		  List<FranchiseLocal> franchiseList = null;
		  try {
		      log.info("franchise Details fetched"); 
		      franchiseList = franchiseTalukaService.getLocalFranchiseListByTaluka(talukaFranchiseId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return franchiseList;
	  }
	  
	  @RequestMapping(value = "/getSupplierOrderByFranchiseTaluka/{talukaFranchiseId}", method = RequestMethod.GET) 
	  public @ResponseBody List<SupplierOrder> getSupplierOrderByFranchiseTaluka(@PathVariable("talukaFranchiseId") int talukaFranchiseId ) throws Exception {
	  
		  List<SupplierOrder> supplierOrderList = null;
		  try {
		      log.info("franchise Details fetched"); 
		      supplierOrderList = franchiseTalukaService.getSupplierOrderByFranchiseTaluka(talukaFranchiseId) ;
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return supplierOrderList;
	  }
	  
	  @RequestMapping(value = "/getSellerOrderByFranchiseTaluka/{talukaFranchiseId}", method = RequestMethod.GET) 
	  public @ResponseBody List<SellerOrder> getSellerOrderByFranchiseTaluka(@PathVariable("talukaFranchiseId") int talukaFranchiseId ) throws Exception {
	  
		  List<SellerOrder> sellerOrderList = null;
		  try {
		      log.info("franchise Details fetched"); 
		      sellerOrderList = franchiseTalukaService.getSellerOrderByFranchiseTaluka(talukaFranchiseId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return sellerOrderList;
	  }
	  
	  @RequestMapping(value = "/getDeliveryOrderByFranchiseTaluka/{talukaFranchiseId}", method = RequestMethod.GET) 
	  public @ResponseBody List<SellerOrder> getDeliveryOrderByFranchiseTaluka(@PathVariable("talukaFranchiseId") int talukaFranchiseId ) throws Exception {
	  
		  List<SellerOrder> sellerOrderList = null;
		  try {
		      log.info("franchise Details fetched"); 
		      sellerOrderList = franchiseTalukaService.getDeliveryOrderByFranchiseTaluka(talukaFranchiseId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching franchise data : "+e.toString());
		  } 
		  return sellerOrderList;
	  }	 
	  
	  @RequestMapping(value = "/loginTalukaFranchise", method = RequestMethod.POST)
	  public @ResponseBody Status loginTalukaFranchise(@RequestBody FranchiseTaluka franchiseTaluka) throws Exception {
		      
			  long mobileNumber = franchiseTaluka.getFranchiseContactNo();
			  String password = franchiseTaluka.getTalukaFranchisePassword();
			  FranchiseTaluka flocal   = null;  	    
			  try {		  
				       flocal = franchiseTalukaService.getFranchiseTalukaByMobileAndPassword(mobileNumber, password);
				    		  
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
	  
	  
	    @RequestMapping(value = "/isFranchiseTalukaMobileNumberExists/{franchiseContactNo}", method = RequestMethod.GET)
		public @ResponseBody Status isFranchiseTalukaMobileNumberExists(@PathVariable("franchiseContactNo") long franchiseContactNo) throws Exception {
					  	    
			  try {
			  
				   FranchiseTaluka franchiseTaluka = franchiseTalukaService.getFranchiseTalukaByMobileNo(franchiseContactNo);
			       if(franchiseTaluka != null) {
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
	    
	    
	    @RequestMapping(value = "/talukaListByCategoryIdDistrictCode/{categoryId}/{districtCode}", method = RequestMethod.GET)
		public List<Taluka> talukaListByCategoryIdDistrictCode(@PathVariable("categoryId") int categoryId, @PathVariable("districtCode") int districtCode) throws Exception {
				
	    	 List<Taluka> talukaList = null;	    	 
			  try {			  
				  talukaList = franchiseTalukaService.talukaListByCategoryIdDistrictCode(categoryId, districtCode);
			       log.error("taluka  List By CategoryId & state Code fetched."); 
			  		     	  
			  } catch(Exception ex) { 
				   ex.printStackTrace();
			  	   log.error("Exception @talukaListByCategoryIdDistrictCode :"+ex.toString()); 
			  	  
			  }	  
			  return talukaList;
		}
}
