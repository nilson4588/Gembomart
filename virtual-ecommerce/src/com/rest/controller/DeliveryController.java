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

import com.rest.model.Delivery;
import com.rest.model.DeliveryOrder;
import com.rest.model.Status;
import com.rest.service.DeliveryService;
import com.rest.utility.ConstantsUtil;
import com.rest.utility.EmailTemplate;
import com.rest.utility.SendSMS;
import com.rest.utility.sendEmail;

@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class DeliveryController {

	//Ladrajesh860 8055056096
	
	@Autowired
	DeliveryService deliveryService;
	
	static final Logger log = Logger.getLogger(DeliveryController.class);
	  
	  @RequestMapping(value = "/addOrUpdateDelivery", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	  public void saveOrUpdateDelivery(@RequestBody Delivery delivery) throws Exception {
	  		  
		  try { 
			    int deliveryId = delivery.getDeliveryId();
			    deliveryService.saveOrUpdateDelivery(delivery);
			    
			    
			    if(deliveryId == 0) {
			    	long contactNo = delivery.getDeliveryContactNo();
			    	String password = delivery.getDeliveryPassword();
			    	
			    	SendSMS.sendSms(String.valueOf(contactNo), "Dear "+delivery.getDeliveryFullName()+", You are successfully registered with GemboMart. Your Login Details are: Registration ID : "+contactNo+" Password : "+password+" Thanks GemboMart");
				    		
				    SendSMS.sendSms(String.valueOf(contactNo), "Thanks for registration. Your Account will be active in next 24 hrs.");
				    
				    sendEmail sd = new sendEmail();
				    sd.sendMail(ConstantsUtil.EMAIL_FROM, delivery.getDeliveryEmailId(), "Successful Registration", EmailTemplate.prepareCRMEmailTemplate(contactNo, password,ConstantsUtil.DELIVERY_GEMBOMART));
			    }			    			
			    		/*
			    		"Dear "+delivery.getDeliveryFullName()+",\r\n"			    		
			    		+ "You are successfully registered with GemboMart.\r\n"
			    		+ "Your Login Details are:\r\n"
			    		+ "Registration ID : "+delivery.getDeliveryContactNo()+"\r\n"
			    		+ "Password : "+delivery.getDeliveryPassword()+"\r\n"
			    		+ "Thanks\r\n"
			    		+ "GemboMart");*/
			  	log.info("Delivery record saved successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("Delivery failed to saved: "+ e.toString());
		  }
	  }
	  
	  @RequestMapping(value = "/getDelivery/{deliveryId}", method = RequestMethod.GET) 
	  public @ResponseBody Delivery getDeliveryById(@PathVariable("deliveryId") int deliveryId ) throws Exception {
	  
		  Delivery delivery = null;
		  try {
		      log.info("Delivery Details fetched"); 
		      delivery = deliveryService.getDeliveryById(deliveryId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching Delivery data : "+e.toString());
		  } 
		  return delivery;
	  }
	  
	  @RequestMapping(value = "/deliveryList", method = RequestMethod.GET)
	  public @ResponseBody List<Delivery> getDeliveryList() throws Exception {
	  
		  List<Delivery> deliveryList = null;
		  try { 
			    deliveryList = deliveryService.getDeliveryList();
			  	log.info("Display all Delivery List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching Delivery data : "+e.toString()); 
		  } 
		  return deliveryList;
	  }	
	  
	  @RequestMapping(value = "/getDeliveryByMobileNo/{mobileno}", method = RequestMethod.GET)  
	  public @ResponseBody Delivery getDeliveryByMobileNo(@PathVariable("mobileno") long mobileno ) throws Exception {  	
			
		  Delivery delivery = null;
			try {
				    log.info("Delivery Details by mobile no");
				    delivery =  deliveryService.getDeliveryByMobileNo(mobileno);  
				    
		    } catch(Exception e) { 	
		    	    log.info("Exception while fetching Delivery by mobile no : "+e.toString());               
		    }
			return delivery;
	   }  	  
	  
	  @RequestMapping(value = "/activateOrDeactivateDelivery", method = RequestMethod.POST) 
	  public void activateOrDeactivateDelivery(@RequestBody Delivery delivery) throws Exception {
	  
		  try {
			    deliveryService.activateOrDeactivateDelivery(delivery);
			    log.info("delivery activation status changed successfully.");   
		  
		  } catch (Exception e) { 
			  e.printStackTrace();
			    log.info("Exception at delivery activation status. "+e.toString());
		  } 
	  }
	  
	  @RequestMapping(value = "/deliveryListByFranchiseLocal/{localFranchiseId}", method = RequestMethod.GET)
	  public @ResponseBody List<Delivery> deliveryListByFranchiseLocal(@PathVariable("localFranchiseId") int localFranchiseId ) throws Exception {
	  
			  List<Delivery> deliveryList = null;
			  try { 
				    deliveryList = deliveryService.deliveryListByFranchiseLocal(localFranchiseId);
				  	log.info("Display all Delivery List");
				  	
			  }catch(Exception e){
				    log.info("Exception while fetching Delivery data : "+e.toString()); 
			  } 
			  return deliveryList;
	  }	
	  
	  @RequestMapping(value = "/deliveryListByFranchiseTaluka/{talukaFranchiseId}", method = RequestMethod.GET)
	  public @ResponseBody List<Delivery> deliveryListByFranchiseTaluka(@PathVariable("talukaFranchiseId") int talukaFranchiseId ) throws Exception {
	  
			  List<Delivery> deliveryList = null;
			  try { 
				    deliveryList = deliveryService.deliveryListByFranchiseTaluka(talukaFranchiseId);
				  	log.info("Display all Delivery List");
				  	
			  }catch(Exception e){
				    log.info("Exception while fetching Delivery data : "+e.toString()); 
			  } 
			  return deliveryList;
	  }	
	  
	  
	  @RequestMapping(value = "/deliveryListByFranchiseDistrict/{districtFranchiseId}", method = RequestMethod.GET)
	  public @ResponseBody List<Delivery> deliveryListByFranchiseDistrict(@PathVariable("districtFranchiseId") int districtFranchiseId ) throws Exception {
	  
			  List<Delivery> deliveryList = null;
			  try { 
				    deliveryList = deliveryService.deliveryListByFranchiseDistrict(districtFranchiseId);
				  	log.info("Display all Delivery List");
				  	
			  }catch(Exception e){
				    log.info("Exception while fetching Delivery data : "+e.toString()); 
			  } 
			  return deliveryList;
	  }	
	  
	  @RequestMapping(value = "/getDeliveryOrders/{localFranchiseId}", method = RequestMethod.GET)
	  public @ResponseBody List<DeliveryOrder> getDeliveryOrders(@PathVariable("localFranchiseId") int localFranchiseId) throws Exception {
	  
			  List<DeliveryOrder> deliveryList = null;
			  try { 
				    deliveryList = deliveryService.getDeliveryOrders(localFranchiseId);
				  	log.info("Display all Delivery List");
				  	
			  }catch(Exception e){
				    log.info("Exception while fetching Delivery data : "+e.toString()); 
			  } 
			  return deliveryList;
	  }
	  
	  @RequestMapping(value = "/loginDelivery", method = RequestMethod.POST)
	  public @ResponseBody Status loginDelivery(@RequestBody Delivery delivery) throws Exception {
		      
			  long mobileNumber = delivery.getDeliveryContactNo();
			  String password   = delivery.getDeliveryPassword();
			  Delivery del      = null;  	    
			  try {		  
				      del = deliveryService.getDeliveryByMobileAndPassword(mobileNumber, password);
				    		  
			  	      if(del != null) {
			     		     log.info("Login Successful");
			  	  		     return new Status(1, "Login successful.", del);		 
			  	      } else {
			  	  		     log.error("Wrong email or password."); 
			  	  		     return new Status(0, "Wrong email or password. Try again", del);
			  	  	  } 
			   
			  } catch(Exception ex) { 
			  		  log.error("Exception @Login"+ex.toString()); 
			  		  return new Status("Login failed. Try Again.", del);
			  }	  
		}
	  
	    @RequestMapping(value = "/isDeliveryMobileNumberExists/{deliveryContactNo}", method = RequestMethod.GET)
		public @ResponseBody Status isDeliveryMobileNumberExists(@PathVariable("deliveryContactNo") long deliveryContactNo) throws Exception {
					  	    
			  try {
			  
				   Delivery delivery = deliveryService.getDeliveryByMobileNo(deliveryContactNo);
			       if(delivery != null) {
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
	    
	    
	    @RequestMapping(value = "/changePasswordByDeliveryMobile", method = RequestMethod.POST)  
		public @ResponseBody Status  changePasswordByDeliveryMobile(@RequestBody Delivery delivery) throws Exception {  		
		   try { 
			            deliveryService.changePasswordByDeliveryMobile(delivery);
					    log.info("Password changed successfully.");
					    return new Status(1, "Password changed successfully.");	
		   } catch (Exception e) {  
			            log.info("Exception at changePasswordByMobileNumber : "+e.toString());
			            return new Status(0, "Failed to changed password.");
	       }  
		}
	    
	    
	    @RequestMapping(value = "/updateDeliveryToken", method = RequestMethod.POST) 
		  public @ResponseBody Status updateDeliveryToken(@RequestBody Delivery delivery) throws Exception {
		  
			  try { 
				    deliveryService.updateDeliveryToken(delivery);
				    log.info("Delivery token updated successfully."); 
				    return new Status(1, "Delivery token updated successfully."); 
			  
			  } catch (Exception e) { 
				    e.printStackTrace();
				    log.info("Exception at Delivery token updation failed. "+e.toString());
				    return new Status(0, "Failed to update token."); 
			  } 
		  } 
}