package com.rest.utility;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

	static final Logger logger = Logger.getLogger(PasswordEncoder.class);
	
	public String getHashPassword(String password) {  
	
		  String hashedPassword = null;
		  try{				  
		        if(password != ""){
		    
				  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
				  hashedPassword = passwordEncoder.encode(password);  
				    
				  return hashedPassword;
				  
			    }else{
				   logger.error("Password can not be null");				 
			    }
		        
		  } catch(Exception ex) {
				
			  //ex.printStackTrace();
			  logger.error("Error while password encryption.");
		  }
		  return hashedPassword;
	}		
} 