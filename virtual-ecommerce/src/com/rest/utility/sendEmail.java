package com.rest.utility;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;

	@Component
	public class sendEmail {
		   
	    private String host;
	
	    private Properties properties;
	
	    private MimeMessage message;
	    private BodyPart    messageBodyPart;
	    private Multipart   multipart;
	
	    private Authenticator authenticator;
	         
	    public sendEmail() {
	    	    
	    	host = "smtpout.secureserver.net";
	
	        authenticator = new SMTPAuthenticator ();
	        properties = System.getProperties ();
	        properties.put("mail.transport.protocol", "smtps");
	        properties.put("mail.smtp.host", host );
	        properties.put("mail.smtp.starttls.enable", "true" );
	        properties.put("mail.smtp.port", "465" );
	        properties.put("mail.smtp.auth", "true" );
	        properties.put("mail.smtp.socketFactory.port","465");
	        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        properties.put("mail.smtp.socketFactory.fallback", "false");	 
	        properties.put("mail.smtps.ssl.checkserveridentity", "false");
	        properties.put("mail.smtp.ssl.trust", "*"); 
	    }
	
	    public void sendMail (String from, String to, String subject, String messageBody) {
	        try {
	            Session session = Session.getDefaultInstance ( properties, authenticator );
	            message = new MimeMessage ( session );
	            message.setFrom ( new InternetAddress ( from , "Gembomart Team" ) );
	            message.addRecipient ( Message.RecipientType.TO,  new InternetAddress ( to ) );
	            message.setSubject ( subject );
	
	            multipart = new MimeMultipart ();
	            messageBodyPart = new MimeBodyPart ();
	            messageBodyPart.setContent ( messageBody, "text/html" );
	            multipart.addBodyPart ( messageBodyPart );
	            message.setContent ( multipart );
	
	            Transport.send (message);
	            System.out.println ( "Message send successfully...." );
	           
	        } catch ( Exception me ) {
	            me.printStackTrace ();
	        }
	    } 
	    
	    public static void main(String[] args) { 
	    	 sendEmail sd = new sendEmail();
			 sd.sendMail(ConstantsUtil.EMAIL_FROM, "nilson4588@gmail.com", "Successful Registration", EmailTemplate.prepareCRMEmailTemplate(7875073698l, "abc123",ConstantsUtil.DELIVERY_GEMBOMART));
		}
	}

	class SMTPAuthenticator extends Authenticator {

		private static final String SMTP_AUTH_USER     = "customercare@gembomart.com";
		private static final String SMTP_AUTH_PASSWORD = "ABCxyz@123";
		
		//private static final String SMTP_AUTH_USER     = "support@ysmsoftware.com";
		//private static final String SMTP_AUTH_PASSWORD = "support123";

        public PasswordAuthentication getPasswordAuthentication () {
    		String username = SMTP_AUTH_USER;
    		String password = SMTP_AUTH_PASSWORD;

        	return new PasswordAuthentication( username,  password );
    	}
	}
