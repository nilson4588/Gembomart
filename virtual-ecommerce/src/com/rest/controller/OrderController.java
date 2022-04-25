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

import com.google.gson.Gson;
import com.rest.model.Cart;
import com.rest.model.Customer;
import com.rest.model.Order;
import com.rest.model.OrderDetails;
import com.rest.model.Status;
import com.rest.service.CartService;
import com.rest.service.CityService;
import com.rest.service.CustomerService;
import com.rest.service.DeliveryService;
import com.rest.service.OrderService;
import com.rest.service.SellerService;
import com.rest.utility.PushNotification;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	SellerService sellerService;
	
	@Autowired
	DeliveryService deliveryService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CityService cityService;
		
	static final Logger log = Logger.getLogger(OrderController.class);
	
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	public void addOrder(@RequestBody Order order) throws Exception {
	  
		  try { 
			    int sellerId = order.getOrderItemData().get(0).getSellerId();
			   
			    orderService.saveOrder(order);
			  	log.info("Order added successfully.");
			  	
			  	String sellerToken = sellerService.getTokenBySellerId(sellerId);
			  	
			  	PushNotification pn = new PushNotification();
			  	
			  	log.info("sellerToken : "+new String[] {sellerToken});
			  	
			  	pn.pushFCMNotification(new String[] {sellerToken}, "Gembomart", "You have received new order", "Hello", "Hi");
			  	
			  	String[] deliveryToken = deliveryService.deliveryTokenByOrderSellerId(sellerId).stream().toArray(String[]::new); 
			  	
			  	pn.pushFCMNotificationDelivery(deliveryToken , "Gembomart", "New order placed near by your location", "Hello", "Hi");
			  	
			  	log.info(" Order complete ");
	  
		  } catch(Exception e) { 
			    e.printStackTrace();
			    log.error("Order failed to saved: "+ e.toString());
		  }
	 }
	
	@RequestMapping(value = "/createNewOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	public @ResponseBody Status createNewOrder(@RequestBody Order order) throws Exception {
	  
		  try { 
			  
			    int customerId = order.getCustomerId();
			    
			    List<Cart> cartList = cartService.getCartListByCustomerId(customerId);
			    
			    int sellerId = cartList.get(0).getSellerId();
			    int talukaCode = cartList.get(0).getAreaCode();
			    
			    int deliveryCharges = cityService.getDeliveryChargesByTalukaCode(talukaCode);			    
			  
			    order.setDeliveryCharges(String.valueOf(deliveryCharges));
			  			   
			    orderService.createNewOrder(order, cartList);
			    
			  	log.info("Order added successfully.");
			  	
			  	cartService.deleteCartList(customerId);
			  	
			  	String sellerToken = sellerService.getTokenBySellerId(sellerId);
			  	
			  	PushNotification pn = new PushNotification();
			  	
			  	log.info("sellerToken : "+new String[] {sellerToken});
			  	
			  	pn.pushFCMNotification(new String[] {sellerToken}, "Gembomart", "You have received new order", "Hello", "Hi");
			  	
			  	String[] deliveryToken = deliveryService.deliveryTokenByOrderSellerId(sellerId).stream().toArray(String[]::new); 
			  	
			  	pn.pushFCMNotificationDelivery(deliveryToken , "Gembomart", "New order placed near by your location", "Hello", "Hi");
			  	
			  	log.info(" Order complete ");
			  	return new Status(1, "Order placed successfully.");
	  
		  } catch(Exception e) { 
			    e.printStackTrace();
			    log.error("Order failed to saved: "+ e.toString());
			    return new Status(0, "Sorry, Order failed to placed.");
		  }
	 }
	
	@RequestMapping(value = "/updateOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	public void updateOrder(@RequestBody Order order) throws Exception {
	  
		  try { 
			    orderService.updateOrder(order);
			  	log.info("Order updated successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("Order failed to update: "+ e.toString());
		  }
	 }
	
	@RequestMapping(value = "/changeOrderStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	public  @ResponseBody Status changeOrderStatus(@RequestBody Order order) throws Exception {
	  
		  int orderId = order.getOrderId();
		  Order ord = orderService.getOrderForDeliveryByOrderId(orderId);
		  String mobileno = String.valueOf(order.getMobileNo());
		  int deliveryBoyId = ord.getDeliveryBoyId();
		  String deliveryBoyName = ord.getDeliveryBoyName();
		  int sellerId = ord.getOrderItemData().get(0).getSellerId();
		  
		  try {			  
			    
			    String orderStatusCurrent = order.getOrderStatus();
			    String ordStatus = orderService.getOrderStatusById(orderId);
			    
			    if(ordStatus.equals("New") && orderStatusCurrent.equals("Ready To Ship")) {
			    	return new Status(4, "Sorry. Order not accept by any delivery partner.");
			    }
			    
			    if(!ordStatus.equals("New") && orderStatusCurrent.equals("Assigned")) {
			    	return new Status(3, "Order already assigned by other delivery partner.");
			    }		
			    
			    if(!ordStatus.equals("Ready To Ship") && orderStatusCurrent.equals("Shipped")) {
			    	return new Status(2, "Order is not ready to deliver. Please wait.");
			    }
			    
			    orderService.changeOrderStatus(order);
			  	log.info("Order status successfully updated.");	
			  	
				
				  if(orderStatusCurrent.equals("Assigned")) { 
					  String sellerToken =  sellerService.getTokenBySellerId(sellerId); 
					  PushNotification pn = new PushNotification(); 
					  pn.pushFCMNotification(new String[] {sellerToken}, "Gembomart", "Order#"+orderId+" accepted by delivery person - "+deliveryBoyName, "Hello", "Hi");
				  }
				  
				  if(orderStatusCurrent.equals("Ready To Ship")) { 
					  String deliveryToken = deliveryService.getTokenByDeliveryId(deliveryBoyId);
					  PushNotification pn =  new PushNotification(); 
					  pn.pushFCMNotificationDelivery(new String[] {deliveryToken}, "Gembomart", "Order#"+orderId+" ready to delivered. Please pick-up order." , "Hello", "Hi"); }
				  
					/*
					 * if(orderStatusCurrent.equals("Shipped")) { String customerToken =
					 * customerService.getTokenByCustomerMobileNo(mobileno); PushNotification pn =
					 * new PushNotification(); pn.pushFCMNotification(new String[] {customerToken},
					 * "Gembomart", "Your order is out for delivery.", "", ""); }
					 */
				  
				  if(orderStatusCurrent.equals("Delivered")) { 
					  String sellerToken =  sellerService.getTokenBySellerId(sellerId); 
					  //String customerToken =  customerService.getTokenByCustomerMobileNo(mobileno); {sellerToken,customerToken}
					  PushNotification pn =  new PushNotification(); 
					  pn.pushFCMNotification(new String[]{sellerToken}, "Gembomart", "Order#"+orderId+" delivered successfuly.", "Hello", "Hi"); 
				  }
				 
			  	
			  	return new Status(1, "Order status successfully changed.");			  	
	  
		  } catch(Exception e) {  
			    log.error("Order status failed to update: "+ e.toString());
			    return new Status(5, "Order status failed to change.");
		  }
	 }
	
	@RequestMapping(value = "/changeOrderDetailsStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	public void cancelOrder(@RequestBody OrderDetails orderDetails) throws Exception {
	  
		  try { 
			    orderService.changeOrderDetailsStatus(orderDetails);
			  	log.info("Order status successfully updated.");		  	
	  
		  } catch(Exception e) {  
			    log.error("Order failed to update: "+ e.toString());
		  }
	 }
	
	@RequestMapping(value = "/getOrderById/{orderId}/{sellerId}", method = RequestMethod.GET) 
	public @ResponseBody Order getOrderById(@PathVariable("orderId") int orderId, @PathVariable("sellerId") int sellerId) throws Exception {
	  
		  Order order = null;
		  try {
		     
		     order = orderService.getOrderById(orderId, sellerId);
		     log.info("Order Details fetched"); 
	  
		  } catch(Exception e) {	  
			  e.printStackTrace();
			  log.info("Exception while fetching order data : "+e.toString());
		  } 
		  return order;
	}
	
	@RequestMapping(value = "/getOrderForDeliveryByOrderId/{orderId}", method = RequestMethod.GET) 
	public @ResponseBody Order getOrderForDeliveryByOrderId(@PathVariable("orderId") int orderId) throws Exception {
	  
		  Order order = null;
		  try {
		     
		     order = orderService.getOrderForDeliveryByOrderId(orderId);
		     log.info("Order Details fetched"); 
	  
		  } catch(Exception e) {	  
			  e.printStackTrace();
			  log.info("Exception while fetching order data : "+e.toString());
		  } 
		  return order;
	}
	
	@RequestMapping(value = "/getOrderList", method = RequestMethod.POST) 
	public @ResponseBody List<Order> getOrderList(@RequestBody Order order) throws Exception {
	  
		  List<Order> orderList = null;
		  try {
		     
			  orderList = orderService.getOrderList(order);
			  log.info("Order List fetched"); 
	  
		  } catch(Exception e) {
			  e.printStackTrace();
			  log.info("Exception while fetching order list : "+e.toString());
		  } 
		  return orderList;
	}
	
	@RequestMapping(value = "/getCurrentOrderList", method = RequestMethod.POST) 
	public @ResponseBody List<Order> getCurrentOrderList(@RequestBody Order order) throws Exception {
	  
		  List<Order> orderList = null;
		  try {
		     
			  orderList = orderService.getTodaysOrderList(order);
			  log.info("Order List fetched"); 
	  
		  } catch(Exception e) {
			  e.printStackTrace();
			  log.info("Exception while fetching order list : "+e.toString());
		  } 
		  return orderList;
	}
	
	@RequestMapping(value = "/getOrderListJSONObject", method = RequestMethod.POST) 
	public @ResponseBody Status getJSONObjectOrderList(@RequestBody Order order) throws Exception {
	  
		  List<Order> orderList = null;
		 // JSONObject jo  = null;
		  try {
		     
			  orderList = orderService.getOrderList(order);
			 // jo = new JSONObject();
			  // populate the array
			 // jo.put("Order",orderList);
			  log.info("Order List fetched"); 
			  return new Status("Order List fetched.",orderList);
	  
		  } catch(Exception e) {
			  e.printStackTrace();
			  log.info("Exception while fetching order list : "+e.toString());
			  return new Status("Exception while fetching order list.",orderList);
		  } 		 
	}
	
		
	@RequestMapping(value = "/getOrderListForDelivery", method = RequestMethod.POST) 
	public @ResponseBody String getOrderListForDelivery(@RequestBody Order order) throws Exception {
	  
		  List<Order> orderList = null;
		  String json = "";
		  try {
		     
			  orderList = orderService.getOrderList(order);
			  Gson gson = new Gson();
			  json = gson.toJson(orderList);
			  log.info("Order List fetched"); 
	  
		  } catch(Exception e) {
			  e.printStackTrace();
			  log.info("Exception while fetching order list : "+e.toString());
		  } 
		  return (String) json;
	}
	
	 
	
	@RequestMapping(value = "/getTodaysOrderList/{sellerId}", method = RequestMethod.GET) 
	public @ResponseBody List<Order> getTodaysOrderList(@PathVariable("sellerId") int sellerId) throws Exception {
	  
		  List<Order> orderList = null;
		  try {
		     
			  orderList = orderService.getTodaysOrderList(sellerId);
			  log.info("Today's Order List fetched"); 
	  
		  } catch(Exception e) {
			  e.printStackTrace();
			  log.info("Exception while fetching today's order list : "+e.toString());
		  } 
		  return orderList;
	}
	
	
	@RequestMapping(value = "/getTodaysDeliveryOrderList/{deliveryId}", method = RequestMethod.GET) 
	public @ResponseBody List<Order> getTodaysDeliveryOrderList(@PathVariable("deliveryId") int deliveryId) throws Exception {
	  
		  List<Order> orderList = null;
		  try {
		     
			  orderList = orderService.getTodaysDeliveryOrderList(deliveryId);
			  log.info("Today's Order List fetched"); 
	  
		  } catch(Exception e) {
			  e.printStackTrace();
			  log.info("Exception while fetching today's order list : "+e.toString());
		  } 
		  return orderList;
	}
	
	
	@RequestMapping(value = "/getOrderDetailsByOrderId/{orderId}", method = RequestMethod.GET) 
	public @ResponseBody List<OrderDetails> getOrderDetailsByOrderId(@PathVariable("orderId") int orderId) throws Exception {
	  
		 List<OrderDetails> order = null;
		  try {
		     
		     order = orderService.getOrderDetailsByOrderId(orderId);
		     log.info("Order Details fetched"); 
	  
		  } catch(Exception e) {	  
			  e.printStackTrace();
			  log.info("Exception while fetching order data : "+e.toString());
		  } 
		  return order;
	}
	
	
	@RequestMapping(value = "/getOrderListBySellerId/{sellerId}/{orderDate}/{orderStatus}", method = RequestMethod.GET) 
	public @ResponseBody List<Order> getOrderListBySellerId(@PathVariable("sellerId") int sellerId,@PathVariable("orderDate") String orderDate, @PathVariable("orderStatus") String orderStatus) throws Exception {
	  
		  List<Order> orderList = null;
		  try {
		     
			  orderList = orderService.getOrderListBySellerId(sellerId, orderDate, orderStatus);
			  log.info("Order List By Seller fetched"); 
	  
		  } catch(Exception e) {
			  e.printStackTrace();
			  log.info("Exception while fetching order list By Seller : "+e.toString());
		  } 
		  return orderList;
	}
	
	@RequestMapping(value = "/getOrderListByLocalFranchiseId/{localFranchiseId}/{orderDate}/{orderStatus}", method = RequestMethod.GET) 
	public @ResponseBody List<Order> getOrderListByLocalFranchiseId(@PathVariable("localFranchiseId") int localFranchiseId,@PathVariable("orderDate") String orderDate, @PathVariable("orderStatus") String orderStatus) throws Exception {
	  
		  List<Order> orderList = null;
		  try {
			  log.info("Local Franchise parameter : "+localFranchiseId+"-"+orderDate+"-"+orderStatus); 
			  orderList = orderService.getOrderListByLocalFranchiseId(localFranchiseId, orderDate, orderStatus);
			  log.info("Order List By Local Franchise fetched"); 
	  
		  } catch(Exception e) {
			  e.printStackTrace();
			  log.info("Exception while fetching order list By Local Franchise : "+e.toString());
		  } 
		  return orderList;
	}
	
	@RequestMapping(value = "/orderOTPVerification", method = RequestMethod.POST)
	public @ResponseBody Status orderOTPVerification(@RequestBody Order order) throws Exception {
	      
		  int orderId = order.getOrderId();
		  String otp  = order.getOrderOtp();
		  Order ord   = null;  	    
		  try {		  
			      ord = orderService.orderOTPVerification(orderId, otp);		
		   	     
		  	      if(ord != null) {
		     		     log.info("OTP Verified");
		  	  		     return new Status(1,"OTP Verified.");		 
		  	      } else {
		  	  		     log.error("OTP Verification failed. Try again."); 
		  	  		     return new Status(0,"OTP Verification failed. Try again.");
		  	  	  } 
		   
		  } catch(Exception ex) { 
		  		  log.error("Exception OTP Verification"+ex.toString()); 
		  		return new Status(0,"OTP Verification failed. Try again.");
		  }	  
	}
	
}