package com.rest.utility;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class PushNotification {

	
	public final static String AUTH_KEY_FCM_DELIVERY = "AAAAViAR2zE:APA91bH0pnL6GKxoT4MvSxlez6TJx_imlg_7DOUUePsR3POEUP75BPPXxuNJRzP32uj-_W9xqAxZcXD9YaM0TQ7a48nzTHZC0dA5kNqGkc3LT5QxSXUI6G8ntRkhq8Pga7B3BAPvrawS";
	
	public final static String AUTH_KEY_FCM_SELLER = "AAAAqipoh7E:APA91bHbwhbsNd95LB_PRBmpCaRXfGbcZ0kyi2Z1CKd3NKeW94_DmAROQ0CkI7eL0-TzV-dj0BRgcqtE45YGPbUnXdR6bVAifljjZU8IgIwYe7ix3z-qrnA-SgG6nY7jole_3VTk15VI"; //sms key
	public final static String API_URL_FCM  = "https://fcm.googleapis.com/fcm/send";
	
	static final Logger log = Logger.getLogger(PushNotification.class);
	
	public void pushFCMNotification(String[] userDeviceIdKey, String title, String body, String key_1, String key_2) throws Exception {
		
		String authKey = AUTH_KEY_FCM_SELLER;   // You FCM AUTH key
		String FMCurl  = API_URL_FCM; 
		
		System.out.println("Keys : "+userDeviceIdKey);
		for(int i=0; i<userDeviceIdKey.length; i++){
			log.info(" i: " +userDeviceIdKey[i]);
		}
		
		URL url = new URL(FMCurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization","key="+authKey);
		conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
		
		JSONObject json = new JSONObject();		
		json.put("registration_ids",userDeviceIdKey); //registration_ids
		JSONObject info = new JSONObject();
		info.put("title", title);  
		//info.put("subject", subject); 
		info.put("body", body); 	
		//info.put("type", ntfType); 
		info.put("key_1", key_1);
		info.put("key_2", key_2);
		//info.put("creator", creator);
		//info.put("thumbnail", "	https://gembomart.com/gembomart/assets/images/icon/logo.png");
		json.put("data", info);
		//json.put("notification", info);		
		
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		//System.out.println(json.toString());
		wr.write(json.toString());  //json.toString()
		wr.flush();
		
		conn.getInputStream();
		
		log.info("Seller Notification complete ");
	}
	
	
	public void pushFCMNotificationDelivery(String[] userDeviceIdKey, String title, String body, String key_1, String key_2) throws Exception {
		
		String authKey = AUTH_KEY_FCM_DELIVERY;   // You FCM AUTH key
		String FMCurl  = API_URL_FCM; 
		
		System.out.println("Keys : "+userDeviceIdKey);
		for(int i=0; i<userDeviceIdKey.length; i++){
			log.info(" i: " +userDeviceIdKey[i]);
		}
		
		URL url = new URL(FMCurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization","key="+authKey);
		conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
		
		JSONObject json = new JSONObject();		
		json.put("registration_ids",userDeviceIdKey); //registration_ids
		JSONObject info = new JSONObject();
		info.put("title", title);  
		//info.put("subject", subject); 
		info.put("body", body); 	
		//info.put("type", ntfType); 
		info.put("key_1", key_1);
		info.put("key_2", key_2);
		//info.put("creator", creator);
		//info.put("thumbnail", "	https://gembomart.com/gembomart/assets/images/icon/logo.png");
		json.put("data", info);
		//json.put("notification", info);		
		
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		//System.out.println(json.toString());
		wr.write(json.toString());  //json.toString()
		wr.flush();
		
		conn.getInputStream();
		
		log.info(" Notification complete ");
	}
	/*
	 * public static void main(String args[]) throws Exception {
	 * 
	 * String[] userDevice =
	 * {"cE1HEcifSMavSspmTqfaYP:APA91bHFdouhoy7YVZZzlFt1NEqkD7wywW0WNXATAot0pctexMzkmoH06e-9dFuGSZ1AZtJWEiHoJILdfwDh_ZS4f4uHfnpFXs1P2-Mt8t2HHB9xFW9K3YJbHYt8FoNyVmjXO9dIeoDM"
	 * }; PushNotification p = new PushNotification(); System.out.println("start");
	 * p.pushFCMNotification(userDevice, "New Order", "First Notification", "","");
	 * System.out.println("done"); }
	 */
}