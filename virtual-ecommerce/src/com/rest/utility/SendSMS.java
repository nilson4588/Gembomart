package com.rest.utility;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SendSMS {
	
		public static void sendSms(String mobile, String msg) {
		
			try {
					String username    = "gembomart"; 
					String password    = "Akola@3535";			
					String sender      = "GEMBOM";
					String type        = "0";
					String destination = "91"+mobile;
					String message     = msg;
					String peid        = "1301160871383887790";			
					
		            String requestUrl  = "http://theprservices.com/api/mt/SendSMS?" +
										 "username=" + URLEncoder.encode(username, "UTF-8") +
										 "&password=" + URLEncoder.encode(password, "UTF-8") +
										 "&senderid=" + URLEncoder.encode(sender, "UTF-8") +
										 "&type=" + URLEncoder.encode(type, "UTF-8") +
										 "&destination=" + URLEncoder.encode(destination, "UTF-8") +
										 "&text=" + URLEncoder.encode(message, "UTF-8") +
										 "&peid=" + URLEncoder.encode(peid, "UTF-8");
		
		           //  System.out.println(requestUrl);
		            
		            URL url = new URL(requestUrl);
		            HttpURLConnection uc = (HttpURLConnection)url.openConnection();
		
		            System.out.println(uc.getResponseMessage());
		
		            uc.disconnect();
		
		    } catch(Exception ex) {
		            System.out.println(ex.getMessage());			
		    }
		}
		
		
		public static void main(String args[]) { 
			  System.out.println("send sms");
			  sendSms("8983083698",  "Welcome to Gembomart. Your OTP for verification is :"+5678 );
			  System.out.println("success"); 
		}		 
}