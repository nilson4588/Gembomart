package com.rest.utility;

import java.util.LinkedHashMap;
import java.util.Random;
import java.util.regex.Pattern;

public class CommonUtil {
	
   public static String generateOTP(int length){
			
		String numbers="0123456789";
		Random random = new Random();
		char[] otp = new char[length];
		for(int i=0; i<length; i++){
			otp[i] = numbers.charAt(random.nextInt(numbers.length()));
		}
		char[] OTP1 = otp;
		String OTP = new String(OTP1);
		return OTP;
	}	
    
    public static int checkEmailOrMobile(String inputstr){
    	
    	 int result = 0;
    	 if(Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}").matcher(inputstr).matches()){
        	 result = 2;             
         } else if(inputstr.chars().allMatch(Character::isDigit) && inputstr.trim().length() == 10){
         	 result = 1;         
         }          
         return result;
    }
    
    public static LinkedHashMap<String, String> getOrderStatusCss(){
		
		    LinkedHashMap<String, String> orderStatus = new LinkedHashMap<String, String>();
		 
		    orderStatus.put("New", "<span class='badge badge-primary'>New</span>");
		    orderStatus.put("Shipped", "<span class='badge badge-warning'>Shipped</span>");
		    orderStatus.put("Delivered", "<span class='badge badge-success'>Delivered</span>");
		    orderStatus.put("Canceled", "<span class='badge badge-danger'>Canceled</span>");
		    orderStatus.put("Received", "<span class='badge badge-success'>Received</span>");
		
		    return orderStatus;
	}
    
    public static LinkedHashMap<Integer, String> getComplaintType(){
		
	    LinkedHashMap<Integer, String> complaintType = new LinkedHashMap<Integer, String>();
	 
	    complaintType.put(1, "PRODUCT COMPLAINT");
	    complaintType.put(2, "SERVICE COMPLAINT");
	    complaintType.put(3, "PACKING COMPLAINT");
	    complaintType.put(4, "APPLICATION COMPLAINT");
	    complaintType.put(5, "PAYMENT COMPLAINT");
	
	    return complaintType;
    }
}