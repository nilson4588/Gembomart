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

import com.rest.model.Status;
import com.rest.model.Supplier;
import com.rest.service.SupplierService;
import com.rest.utility.ConstantsUtil;
import com.rest.utility.EmailTemplate;
import com.rest.utility.SendSMS;
import com.rest.utility.sendEmail;

@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class SupplierController {

	  @Autowired
	  SupplierService supplierService;
	
	  static final Logger log = Logger.getLogger(SupplierController.class);
	  
	  @RequestMapping(value = "/addOrUpdateSupplier", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	  public void saveOrUpdateSupplier(@RequestBody Supplier supplier) throws Exception {
	  
			  try { 
				    int supplierId = supplier.getSupplierId();
				    supplierService.saveOrUpdateSupplier(supplier);
				   
				    if(supplierId == 0) {
				    	long contactNo = supplier.getSupplierContactNo();
				    	String password = supplier.getSupplierPassword();
				    	
				    	SendSMS.sendSms(String.valueOf(contactNo), 
					    		"Dear "+supplier.getSupplierFullName()+", You are successfully registered with GemboMart. Your Login Details are: Registration ID : "+contactNo+" Password : "+password+" Thanks GemboMart");
						
					    SendSMS.sendSms(String.valueOf(contactNo), 
							    		"Thanks for registration. Your Account will be active in next 24 hrs.");
					    
					    sendEmail sd = new sendEmail();
					    sd.sendMail(ConstantsUtil.EMAIL_FROM, supplier.getSupplierEmailId(), "Successful Registration", EmailTemplate.prepareCRMEmailTemplate(contactNo, password,ConstantsUtil.SUPPLIER_GEMBOMART));
				    }
				    	
				   /*
					 * "Dear "+supplier.getSupplierFullName()+",\r\n" +;
					 * "You are successfully registered with GemboMart.\r\n" +
					 * "Your Login Details are:\r\n" +
					 * "Registration ID : "+supplier.getSupplierContactNo()+"\r\n" +
					 * "Password : "+supplier.getSupplierPassword()+"\r\n" + "Thanks\r\n" +
					 * "GemboMart");
					 */
				  	log.info("Supplier record saved successfully.");		  	
		  
			  } catch(Exception e) {  
				    log.error("Supplier failed to saved: "+ e.toString());
			  }
	  }
	  
	  @RequestMapping(value = "/getSupplier/{supplierId}", method = RequestMethod.GET) 
	  public @ResponseBody Supplier  getSupplierById(@PathVariable("supplierId") int supplierId ) throws Exception {
	  
			  Supplier supplier = null;
			  try {
			      log.info("Supplier Details fetched"); 
			      supplier = supplierService.getSupplierById(supplierId);
			  } catch(Exception e) {	  
				  log.info("Exception while fetching Supplier data : "+e.toString());
			  } 
			  return supplier;
	  }
	  
	  @RequestMapping(value = "/supplierList", method = RequestMethod.GET)
	  public @ResponseBody List<Supplier> getSupplierList() throws Exception {
	  
			  List<Supplier> supplierList = null;
			  try { 
				    supplierList = supplierService.getSupplierList();
				  	log.info("Display all Supplier List");
				  	
			  }catch(Exception e){
				    log.info("Exception while fetching Supplier data : "+e.toString()); 
			  } 
			  return supplierList;
	  }	  
	  
	  @RequestMapping(value = "/getSupplierByMobileNo/{mobileno}", method = RequestMethod.GET)  
	  public @ResponseBody Supplier getSupplierByMobileNo(@PathVariable("mobileno") long mobileno) throws Exception {  	
			
			    Supplier supplier = null;
				try {
					    log.info("Supplier Details by mobile no");
					    supplier =  supplierService.getSupplierByMobileNo(mobileno);  
					    
			    } catch(Exception e) { 	
			    	    log.info("Exception while fetching Supplier by mobile no : "+e.toString());               
			    }
				return supplier;
	  }  
	  
	  @RequestMapping(value = "/activateOrDeactivateSupplier", method = RequestMethod.POST) 
	  public void activateOrDeactivateSeller(@RequestBody Supplier supplier) throws Exception {
	  
			  try { 
				    supplierService.activateOrDeactivateSupplier(supplier);
				    log.info("supplier activation status changed successfully.");   
			  
			  } catch (Exception e) { 
				  e.printStackTrace();
				    log.info("Exception at supplier activation status. "+e.toString());
			  } 
	  } 
	  
	  @RequestMapping(value = "/supplierListByFranchiseLocal/{localFranchiseId}", method = RequestMethod.GET)
	  public @ResponseBody List<Supplier> supplierListByFranchiseLocal(@PathVariable("localFranchiseId") int localFranchiseId ) throws Exception {
	  
			  List<Supplier> supplierList = null;
			  try { 
				    supplierList = supplierService.supplierListByFranchiseLocal(localFranchiseId);
				  	log.info("Display all Supplier List");
				  	
			  }catch(Exception e){
				    log.info("Exception while fetching Supplier data : "+e.toString()); 
			  } 
			  return supplierList;
	  }	 
	  
	  @RequestMapping(value = "/supplierListByFranchiseTaluka/{talukaFranchiseId}", method = RequestMethod.GET)
	  public @ResponseBody List<Supplier> supplierListByFranchiseTaluka(@PathVariable("talukaFranchiseId") int talukaFranchiseId ) throws Exception {
	  
			  List<Supplier> supplierList = null;
			  try { 
				    supplierList = supplierService.supplierListByFranchiseTaluka(talukaFranchiseId);
				  	log.info("Display all Supplier List");
				  	
			  }catch(Exception e){
				    log.info("Exception while fetching Supplier data : "+e.toString()); 
			  } 
			  return supplierList;
	  }
	  
	  @RequestMapping(value = "/supplierListByFranchiseDistrict/{districtFranchiseId}", method = RequestMethod.GET)
	  public @ResponseBody List<Supplier> supplierListByFranchiseDistrict(@PathVariable("districtFranchiseId") int districtFranchiseId ) throws Exception {
	  
			  List<Supplier> supplierList = null;
			  try { 
				    supplierList = supplierService.supplierListByFranchiseDistrict(districtFranchiseId);
				  	log.info("Display all Supplier List");
				  	
			  }catch(Exception e){
				    log.info("Exception while fetching Supplier data : "+e.toString()); 
			  } 
			  return supplierList;
	  }
	  
	  @RequestMapping(value = "/loginSupplier", method = RequestMethod.POST)
	  public @ResponseBody Status loginSupplier(@RequestBody Supplier supplier) throws Exception {
		      
			  long mobileNumber = supplier.getSupplierContactNo();
			  String password = supplier.getSupplierPassword();
			  Supplier sup   = null;  	    
			  try {		  
				      sup = supplierService.getSupplierByMobileAndPassword(mobileNumber, password);
				    		  
			  	      if(sup != null) {
			     		     log.info("Login Successful");
			  	  		     return new Status(1, "Login successful.", sup);		 
			  	      } else {
			  	  		     log.error("Wrong email or password."); 
			  	  		     return new Status(0, "Wrong email or password. Try again", sup);
			  	  	  } 
			   
			  } catch(Exception ex) { 
			  		  log.error("Exception @Login"+ex.toString()); 
			  		  return new Status("Login failed. Try Again.", sup);
			  }	  
		}
	  
	    @RequestMapping(value = "/isSupplierMobileNumberExists/{supplierContactNo}", method = RequestMethod.GET)
		public @ResponseBody Status isSupplierMobileNumberExists(@PathVariable("supplierContactNo") long supplierContactNo) throws Exception {
					  	    
			  try {
			  
				  Supplier supplier = supplierService.getSupplierByMobileNo(supplierContactNo);
			       if(supplier != null) {
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
}