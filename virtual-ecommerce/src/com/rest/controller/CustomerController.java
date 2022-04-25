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

import com.rest.model.Address;
import com.rest.model.Customer;
import com.rest.model.Status;
import com.rest.service.CustomerService;
import com.rest.service.UtilityService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	UtilityService utilityService;
		
	static final Logger log = Logger.getLogger(CustomerController.class);  
	
	@RequestMapping(value = "/saveOrUpdateCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)  
	public @ResponseBody Status saveOrUpdateCustomer(@RequestBody Customer customer) throws Exception {
		
		    int customerId = customer.getId();
		    try { 
		    	
				customerService.saveOrUpdateCustomer(customer);	
				
				if(customerId == 0) {
					
					customerId = utilityService.getRecentId(Customer.class, "id"); 
				    log.info("Customer record saved successfully.");			    	         
				    return new Status(customerId, "Registered successfully");
				
				} else {
					
					log.info("Customer record Updated successfully.");			    	         
				    return new Status(customerId, "Updated successfully");
				}
			    
	        } catch(Exception e) {  	        	   
		        log.error("Customer record saved failed: "+ e.toString());
		        return new Status(customerId, "Failed to process. Please try again.");	
	        }  
	}
	
		
	@RequestMapping(value = "/login-customer", method = RequestMethod.POST)
	public @ResponseBody Status loginCustomer(@RequestBody Customer customer) throws Exception {
	      
		  String newEmailOrMobile = customer.getEmail();
		  String password = customer.getPwd(); 
		  Customer cust   = null;  	    
		  try {		  
		  	  	  cust = customerService.getCustomerByEmailAndPassword(newEmailOrMobile, password);		
		   	     
		  	      if(cust != null) {
		     		     log.info("Login Successful");
		  	  		     return new Status(1,"Login successful.", cust);		 
		  	      } else {
		  	  		     log.error("Wrong email or password."); 
		  	  		     return new Status(0,"Wrong email or password. Try again", cust);
		  	  	  } 
		   
		  } catch(Exception ex) { 
		  		  log.error("Exception @Login"+ex.toString()); 
		  		  return new Status("Login failed. Try Again.", cust);
		  }	  
	}
	
	@RequestMapping(value = "/loginCustomerByMobile", method = RequestMethod.POST)
	public @ResponseBody Status loginCustomerByMobile(@RequestBody Customer customer) throws Exception {
	      
		  String newEmailOrMobile = customer.getMobileno();
		  String password = customer.getPwd(); 
		  Customer cust   = null;  	    
		  try {		  
		  	  	  cust = customerService.getCustomerByMobileAndPassword(newEmailOrMobile, password); 
		   	     
		  	      if(cust != null) {
		     		     log.info("Login Successful");
		  	  		     return new Status(1,"Login successful.", cust);		 
		  	      } else {
		  	  		     log.error("Wrong mobile number or password."); 
		  	  		     return new Status(0,"Wrong mobile number or password. Try again", cust);
		  	  	  } 
		   
		  } catch(Exception ex) { 
		  		  log.error("Exception @Login"+ex.toString()); 
		  		  return new Status("Login failed. Try Again.", cust);
		  }	  
	}
	
	
	@RequestMapping(value = "/isMobileNumberExists/{mobileNumber}", method = RequestMethod.GET)
	public @ResponseBody Status isMobileNumberExists(@PathVariable("mobileNumber") String mobileNumber) throws Exception {
				  	    
		  try {
		  
		  	   Customer cust = customerService.getCustomerByMobileNo(mobileNumber);	   
		       if(cust != null) {
		  		     log.info("Mobile Number already registered");
		  		     return new Status(0, "Mobile Number already registered.");		 
		       } else {
		  		     log.error("Mobile Number already registered"); 
		  		     return new Status(1, "Available for registration.");
		  	   } 
		  
		  } catch(Exception ex) { 
			         ex.printStackTrace();
		  			 log.error("Exception @isMobileNumberExists "+ex.toString()); 
		  			 return new Status(0, "Failed to recognize. Please try again."); 
		  }	  
	}
	
	
	@RequestMapping(value = "/getCustomer/{customerId}", method = RequestMethod.GET)  
	public @ResponseBody Customer getCustomerById(@PathVariable("customerId") int customerId ) throws Exception {  	
		
		Customer customer = null;
		try {
			    log.info("Customer Details");
			    customer =  customerService.getCustomerById(customerId);	    
			    
	    } catch(Exception e) { 	
	    	   log.info("Exception while fetching Customer data : "+e.toString());               
	    }
		return customer;
	}  
			
	@RequestMapping(value = "/getCustomerByMobileNo/{mobileno}", method = RequestMethod.GET)  
	public @ResponseBody Customer getCustomerByMobileNo(@PathVariable("mobileno") String mobileno ) throws Exception {  	
		
		Customer customer = null;
		try {
			    log.info("Customer Details by mobile no");
			    customer =  customerService.getCustomerByMobileNo(mobileno);  
			    
	    } catch(Exception e) { 	
	    	    log.info("Exception while fetching Customer by mobile no : "+e.toString());               
	    }
		return customer;
	}  

	@RequestMapping(value = "/customerList", method = RequestMethod.GET)  
	public @ResponseBody List<Customer> getCustomerList() throws Exception {  
	       	
		List<Customer> customerList = null;
        try {          	   
			     customerList = customerService.getCustomerList();
				 log.info("Display all Customer List");

	    }catch(Exception e){  
	    	 log.info("Exception while fetching list of Customers : "+e.toString());
        }  
        return customerList;
	}
	
	@RequestMapping(value = "/activateOrDeactivateCustomer", method = RequestMethod.POST)  
	public void activateOrDeactivateCustomer(@RequestBody Customer customer) throws Exception {  		
	   try { 
		            customerService.activateOrDeactivateCustomer(customer);
				    log.info("Customer activation status changed successfully.");
	   } catch (Exception e) {  
		            log.info("Exception at Customer activation status : "+e.toString());
       }  
	}
	
	@RequestMapping(value = "/changePasswordByMobileNumber", method = RequestMethod.POST)  
	public @ResponseBody Status  changePasswordByMobileNumber(@RequestBody Customer customer) throws Exception {  		
	   try { 
		            customerService.changePasswordByMobileNumber(customer);
				    log.info("Password changed successfully.");
				    return new Status(1, "Password changed successfully.");	
	   } catch (Exception e) {  
		            log.info("Exception at changePasswordByMobileNumber : "+e.toString());
		            return new Status(0, "Failed to changed password.");
       }  
	}
	
	
	 @RequestMapping(value = "/updateCustomerToken", method = RequestMethod.POST) 
	  public @ResponseBody Status updateCustomerToken(@RequestBody Customer customer) throws Exception {
	  
		  try { 
			    customerService.updateCustomerToken(customer);
			    log.info("Customer token updated successfully."); 
			    return new Status(1, "Customer token updated successfully."); 
		  
		  } catch (Exception e) { 
			    e.printStackTrace();
			    log.info("Exception at Customer token updation failed. "+e.toString());
			    return new Status(0, "Failed to update token."); 
		  } 
	  } 
	 
	 @RequestMapping(value = "/getAddressListByCustomerId/{customerId}", method = RequestMethod.GET)  
	 public @ResponseBody List<Address> getCustomerByMobileNo(@PathVariable("customerId") int customerId ) throws Exception {  	
			
		    List<Address> addrList = null;
			try {
				    log.info("address list by mobile no");
				    addrList =  customerService.getAddressList(customerId); 
				    
		    } catch(Exception e) { 	
		    	    log.info("Exception while fetching address list by mobile no : "+e.toString());               
		    }
			return addrList;
	  }  
     
	 
	 @RequestMapping(value = "/removeAddressById/{addressId}", method = RequestMethod.GET)  
	 public @ResponseBody Status removeAddressById(@PathVariable("addressId") int addressId ) throws Exception {  	
			
		    try {
				    customerService.removeAddress(addressId); 
				    log.info("Customer address deleted successfully."); 
				    return new Status(1, "Customer address deleted successfully."); 
				    
		    } catch(Exception e) { 	
		    	    log.info("Exception while deleting customer address : "+e.toString());  
		    	    return new Status(0, "Exception while deleting customer address.");  
		    }			
	  } 
	 
	 
	  @RequestMapping(value = "/addOrUpdateAddress", method = RequestMethod.POST) 
	  public @ResponseBody Status addOrUpdateAddress(@RequestBody Address address) throws Exception {
	  
		  try { 
			    customerService.addOrUpdateAddress(address);
			    log.info("Customer address updated successfully."); 
			    return new Status(1, "Customer address updated successfully."); 
		  
		  } catch (Exception e) { 
			    e.printStackTrace();
			    log.info("Exception at Customer address updation failed. "+e.toString());
			    return new Status(0, "Failed to update address."); 
		  } 
	  } 
	  
	  @RequestMapping(value = "/getAddressById/{addressId}", method = RequestMethod.GET)  
	  public @ResponseBody Status getAddressById(@PathVariable("addressId") int addressId ) throws Exception {  	
				
			    try {
					    Address address = customerService.getAddressById(addressId);
					    log.info(" successfully."); 
					    return new Status(1, "Customer address fetched by id.", address); 
					    
			    } catch(Exception e) { 	
			    	    log.info("Exception while deleting customer address : "+e.toString());  
			    	    return new Status(0, "Exception while fetching customer address.");  
			    }			
	  }
}