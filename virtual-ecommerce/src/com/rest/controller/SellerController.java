package com.rest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.model.Seller;
import com.rest.model.Status;
import com.rest.service.SellerService;
import com.rest.utility.ConstantsUtil;
import com.rest.utility.DateTimeUtil;
import com.rest.utility.EmailTemplate;
import com.rest.utility.SendSMS;
import com.rest.utility.sendEmail;

@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class SellerController {

	@Autowired
	SellerService sellerService;
	
	static final Logger log = Logger.getLogger(SellerController.class);
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/addSeller", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
	public void addSeller(@RequestParam(required = true, value= "sellerDetailsData") String sellerDetailsData, @RequestParam(required = true, value= "bannerImage") MultipartFile bannerImage) throws Exception {
	  		//System.out.println("Hellooooooooo"); 
		try {   				
	          String fileName = "";
	         	       // System.out.println(productImage.getSize());			          
	          if(bannerImage.getSize() > 0) {
	          
				  fileName =
				  "show_"+DateTimeUtil.getTimeStampInMiliseconds()+bannerImage.getOriginalFilename().substring(
						  bannerImage.getOriginalFilename().lastIndexOf("."));
				  //System.out.println(fileName); 
				  File file = new File(ConstantsUtil.IMAGE_LOCATION+"seller\\"+fileName); 
				  try {
					  FileOutputStream fos = new FileOutputStream(file);
					  fos.write(bannerImage.getBytes()); fos.close(); 
				  } catch (IOException e) {
					  e.printStackTrace(); 
				  }
	          }
		 
	          ObjectMapper objectMapper = new ObjectMapper();
	          Seller seller = objectMapper.readValue(sellerDetailsData, Seller.class);
	          
	          seller.setBannerImage(fileName);
	          
	           int sellerId = seller.getSellerId();
			   sellerService.saveOrUpdateSeller(seller);
			  
			   if(sellerId == 0) {
			    	
			    	long contactNo  = seller.getSellerContactNo();
		    		String password = seller.getSellerPassword();
			    	
			    	SendSMS.sendSms(String.valueOf(contactNo), 
				    		"Dear "+seller.getSellerFullName()+", You are successfully registered with GemboMart. Your Login Details are: Registration ID : "+contactNo+" Password : "+password+" Thanks GemboMart");
				    		
				    SendSMS.sendSms(String.valueOf(contactNo), 
						    		"Thanks for registration. Your Account will be active in next 24 hrs.");
				    
				    sendEmail sd = new sendEmail();
				    sd.sendMail(ConstantsUtil.EMAIL_FROM, seller.getSellerEmailId(), "Successful Registration", EmailTemplate.prepareCRMEmailTemplate(contactNo, password,ConstantsUtil.SELLER_GEMBOMART));
		        }
			   
			   log.info("Seller record saved successfully.");		
	   
		 } catch (Exception e) {  
			 log.error("Seller failed to saved: "+ e.toString());
		 }		
	}
	  
	  @RequestMapping(value = "/addOrUpdateSeller", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	  public void saveOrUpdateSeller(@RequestBody Seller seller) throws Exception {
	  		  
		  try {
			    int sellerId = seller.getSellerId();
			    sellerService.saveOrUpdateSeller(seller);
			    			    
			    if(sellerId == 0) {
			    	
			    	long contactNo  = seller.getSellerContactNo();
		    		String password = seller.getSellerPassword();
			    	
			    	SendSMS.sendSms(String.valueOf(contactNo), 
				    		"Dear "+seller.getSellerFullName()+", You are successfully registered with GemboMart. Your Login Details are: Registration ID : "+contactNo+" Password : "+password+" Thanks GemboMart");
				    		
				    SendSMS.sendSms(String.valueOf(contactNo), 
						    		"Thanks for registration. Your Account will be active in next 24 hrs.");
				    
				    sendEmail sd = new sendEmail();
				    sd.sendMail(ConstantsUtil.EMAIL_FROM, seller.getSellerEmailId(), "Successful Registration", EmailTemplate.prepareCRMEmailTemplate(contactNo, password,ConstantsUtil.SELLER_GEMBOMART));
		        }
			    
			    /*
				 * "Dear "+seller.getSellerFullName()+",\r\n" +
				 * "You are successfully registered with GemboMart.\r\n" +
				 * "Your Login Details are:\r\n" +
				 * "Registration ID : "+seller.getSellerContactNo()+"\r\n" +
				 * "Password : "+seller.getSellerPassword()+"\r\n" + "Thanks\r\n" + "GemboMart");
				 */ 
			  	log.info("Seller record saved successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("Seller failed to saved: "+ e.toString());
		  }
	  }
	  
	  @RequestMapping(value = "/getSeller/{sellerId}", method = RequestMethod.GET) 
	  public @ResponseBody Seller getSellerById(@PathVariable("sellerId") int sellerId ) throws Exception {
	  
		  Seller seller = null;
		  try {
		      log.info("seller Details fetched"); 
		      seller = sellerService.getSellerById(sellerId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching seller data : "+e.toString());
		  } 
		  return seller;
	  }
	  
	  
	  @RequestMapping(value = "/getSellerDetailsById/{sellerId}", method = RequestMethod.GET) 
	  public @ResponseBody Seller getSellerDetailsById(@PathVariable("sellerId") int sellerId ) throws Exception {
	  
		  Seller seller = null;
		  try {
		      log.info("seller Details fetched"); 
		      seller = sellerService.getSellerDetailsById(sellerId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching seller data : "+e.toString());
		  } 
		  return seller;
	  }
	  
	  @RequestMapping(value = "/sellerList", method = RequestMethod.GET)
	  public @ResponseBody List<Seller> getSellerList() throws Exception {
	  
		  List<Seller> sellerList = null;
		  try { 
			  sellerList = sellerService.getSellerList();
			  	log.info("Display all Seller List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching Seller data : "+e.toString()); 
		  } 
		  return sellerList;
	  }	
	  
	  @RequestMapping(value = "/getSellerByMobileNo/{mobileno}", method = RequestMethod.GET)  
	  public @ResponseBody Seller getSellerByMobileNo(@PathVariable("mobileno") long mobileno ) throws Exception {  	
			
		    Seller seller = null;
			try {
				    log.info("Seller Details by mobile no");
				    seller =  sellerService.getSellerByMobileNo(mobileno);  
				    
		    } catch(Exception e) { 	
		    	    log.info("Exception while fetching Seller by mobile no : "+e.toString());               
		    }
			return seller;
	   } 
	  
	  @RequestMapping(value = "/activateOrDeactivateSeller", method = RequestMethod.POST) 
	  public void activateOrDeactivateSeller(@RequestBody Seller seller) throws Exception {
	  
		  try { 
			    sellerService.activateOrDeactivateSeller(seller);
			    log.info("seller activation status changed successfully.");   
		  
		  } catch (Exception e) { 
			    e.printStackTrace();
			    log.info("Exception at seller activation status. "+e.toString());
		  } 
	  } 
	  
	  @RequestMapping(value = "/sellerListByFranchiseLocal/{localFranchiseId}", method = RequestMethod.GET)
	  public @ResponseBody List<Seller> sellerListByFranchiseLocal(@PathVariable("localFranchiseId") int localFranchiseId ) throws Exception {
	  
			  List<Seller> sellerList = null;
			  try { 
				    sellerList = sellerService.sellerListByFranchiseLocal(localFranchiseId);
				  	log.info("Display Franchise Local seller List");
				  	
			  }catch(Exception e){
				    log.info("Exception while fetching seller data : "+e.toString()); 
			  } 
			  return sellerList;
	  }	 
	  
	  @RequestMapping(value = "/sellerListByFranchiseTaluka/{talukaFranchiseId}", method = RequestMethod.GET)
	  public @ResponseBody List<Seller> sellerListByFranchiseTaluka(@PathVariable("talukaFranchiseId") int talukaFranchiseId ) throws Exception {
	  
			  List<Seller> sellerList = null;
			  try { 
				    sellerList = sellerService.sellerListByFranchiseTaluka(talukaFranchiseId);
				  	log.info("Display Franchise Taluka seller List");
				  	
			  }catch(Exception e){
				    log.info("Exception while fetching seller data : "+e.toString()); 
			  } 
			  return sellerList;
	  }	 
	  
	  @RequestMapping(value = "/sellerListByFranchiseDistrict/{districtFranchiseId}", method = RequestMethod.GET)
	  public @ResponseBody List<Seller> sellerListByFranchiseDistrict(@PathVariable("districtFranchiseId") int districtFranchiseId ) throws Exception {
	  
			  List<Seller> sellerList = null;
			  try { 
				    sellerList = sellerService.sellerListByFranchiseDistrict(districtFranchiseId);
				  	log.info("Display Franchise district seller List");
				  	
			  }catch(Exception e){
				    log.info("Exception while fetching seller data : "+e.toString()); 
			  } 
			  return sellerList;
	  }	 
	  
	  @RequestMapping(value = "/loginSeller", method = RequestMethod.POST)
	  public @ResponseBody Status loginSeller(@RequestBody Seller seller) throws Exception {
		      
			  long mobileNumber = seller.getSellerContactNo();
			  String password = seller.getSellerPassword();
			  Seller sell   = null;  	    
			  try {		  
				      sell = sellerService.getSellerByMobileAndPassword(mobileNumber, password);
				    		  
			  	      if(sell != null) {
			     		     log.info("Login Successful");
			  	  		     return new Status(1, "Login successful.", sell);		 
			  	      } else {
			  	  		     log.error("Wrong email or password."); 
			  	  		     return new Status(0, "Wrong email or password. Try again", sell);
			  	  	  } 
			   
			  } catch(Exception ex) { 
			  		  log.error("Exception @Login"+ex.toString()); 
			  		  return new Status("Login failed. Try Again.", sell);
			  }	  
		}
	  
	    @RequestMapping(value = "/isSellerMobileNumberExists/{sellerContactNo}", method = RequestMethod.GET)
		public @ResponseBody Status isSellerMobileNumberExists(@PathVariable("sellerContactNo") long sellerContactNo) throws Exception {
					  	    
			  try {
			  
				  Seller seller = sellerService.getSellerByMobileNo(sellerContactNo);
			       if(seller != null) {
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
	    
	    
	      @RequestMapping(value = "/updateSellerToken", method = RequestMethod.POST) 
		  public @ResponseBody Status updateSellerToken(@RequestBody Seller seller) throws Exception {
		  
			  try { 
				     sellerService.updateSellerToken(seller);
				    log.info("Seller token updated successfully."); 
				    return new Status(1, "Seller token updated successfully."); 
			  
			  } catch (Exception e) { 
				    e.printStackTrace();
				    log.info("Exception at Seller token updation failed. "+e.toString());
				    return new Status(0, "Failed to update token."); 
			  } 
		  } 
	      
	      
	    @RequestMapping(value = "/getSellerCurrentStatus/{sellerId}", method = RequestMethod.GET) 
	  	public  @ResponseBody Status getSellerCurrentStatus(@PathVariable("sellerId") int sellerId) throws Exception  {
	  	  
	  		  try {	  			    			    
	  			    int status = sellerService.getSellerCurrentStatus(sellerId);
	  			    
	  			  	log.info("Seller status for id - "+sellerId+" "+status);	
	  			  	return new Status(sellerId, ""+status);
	  	  
	  		  } catch(Exception e) {  
	  			    log.error("Order status failed to get."+ e.toString());
	  			    return new Status(sellerId, "Order status failed to get.");
	  		  }
	  	 }
	    
	    @RequestMapping(value = "/getSellerListByArea/{areaCode}", method = RequestMethod.GET) 
		public @ResponseBody List<Seller> getSellerListByArea(@PathVariable("areaCode") int areaCode) throws Exception {
		  
	    	 List<Seller> sellerList = null;
			  try {
			      log.info("seller List fetched"); 
			      sellerList = sellerService.getSellerListByArea(areaCode);
			  } catch(Exception e) {	  
				  log.info("Exception while fetching seller List data : "+e.toString());
			  } 
			  return sellerList;
		}
}