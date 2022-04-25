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

import com.rest.model.SellerOrder;
import com.rest.model.SellerOrderDetails;
import com.rest.model.SellerProductList;
import com.rest.service.SellerOrderService;

@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class SellerOrderController {

	  @Autowired
	  SellerOrderService sellerOrderService;
	
	  static final Logger log = Logger.getLogger(SellerOrderController.class);
	
	  @RequestMapping(value = "/createSellerOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	  public void createSellerOrder(@RequestBody List<SellerOrderDetails> sellerOrderDetailsList) throws Exception {
	  
		  try { 
			    sellerOrderService.createSellerOrder(sellerOrderDetailsList);
			  	log.info("Seller Order record saved successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("Seller Order failed to saved: "+ e.toString());
		  }
	  }
	  
	  @RequestMapping(value = "/sellerProductList", method = RequestMethod.GET)
	  public @ResponseBody List<SellerProductList> sellerProductList() throws Exception {
	  
		  List<SellerProductList> sellerProductList = null;
		  try { 
			  	sellerProductList = sellerOrderService.getSellerProductList();
			  	log.info("Display all Seller Product List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching Seller Product data : "+e.toString()); 
		  } 
		  return sellerProductList;
	  }
	  
	  @RequestMapping(value = "/getTodaysSellerOrderList", method = RequestMethod.GET) 
	  public @ResponseBody List<SellerOrder> getTodaysSellerOrderList() throws Exception {
		  
			  List<SellerOrder> sellerOrderList = null;
			  try {			     
				  sellerOrderList = sellerOrderService.getTodaysSellerOrderList();
				  log.info("Today's Seller Order List fetched"); 
		  
			  } catch(Exception e) {
				  e.printStackTrace();
				  log.info("Exception while fetching today's Seller Order list : "+e.toString());
			  } 
			  return sellerOrderList;
	  }
	  
	  @RequestMapping(value = "/changeSellerOrderStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	  public void changeSellerOrderStatus(@RequestBody SellerOrder order) throws Exception {
		  
			  try { 
				    sellerOrderService.changeSellerOrderStatus(order);
				  	log.info("Seller order status successfully.");		  	
		  
			  } catch(Exception e) {  
				  e.printStackTrace();
				    log.error("Seller order failed to cancelled: "+ e.toString());
			  }
	  }
	  	  
	  @RequestMapping(value = "/removeProductFromOrder/{sellerOrderDetailsId}", method = RequestMethod.GET) 
	  public void removeProductFromOrder(@PathVariable("sellerOrderDetailsId") int sellerOrderDetailsId) throws Exception {
	  		 
		  try {
		      log.info("product removed"); 
		      sellerOrderService.removeProductFromSellerOrderDetails(sellerOrderDetailsId);
		  } catch(Exception e) {	  
			  log.info("Exception while product remove : "+e.toString());
		  } 		  
	  }
	  
	  @RequestMapping(value = "/getTodaysSellerOrderByMobileNo/{mobileNumber}", method = RequestMethod.GET) 
	  public @ResponseBody List<SellerOrderDetails> getTodaysSellerOrderByMobileNo(@PathVariable("mobileNumber") long mobileNumber) throws Exception {
		  
		  List<SellerOrderDetails> sellerOrderDetailsList = null;
			  try {			     
				  sellerOrderDetailsList = sellerOrderService.getTodaysSellerOrderByMobileNo(mobileNumber);
				  log.info("Today's Seller Order List fetched"); 
		  
			  } catch(Exception e) {
				  e.printStackTrace();
				  log.info("Exception while fetching today's Seller Order list : "+e.toString());
			  } 
			  return sellerOrderDetailsList;
	  }
	  
	  @RequestMapping(value = "/getSellerOrderById/{sellerOrderId}", method = RequestMethod.GET) 
	  public @ResponseBody List<SellerOrderDetails> getSellerOrderById(@PathVariable("sellerOrderId") int sellerOrderId) throws Exception {
		  
		      List<SellerOrderDetails> sellerOrderDetailsList = null;
			  try {			     
				  sellerOrderDetailsList = sellerOrderService.getSellerOrderById(sellerOrderId);
				  log.info("Today's seller Order List fetched"); 
		  
			  } catch(Exception e) {
				  e.printStackTrace();
				  log.info("Exception while fetching today's seller Order list : "+e.toString());
			  } 
			  return sellerOrderDetailsList;
	  }
	  
	  @RequestMapping(value = "/getSellerOrderLocalFranchiseComission/{localFranchiseId}", method = RequestMethod.GET) 
	  public @ResponseBody List<SellerOrder> getSellerOrderLocalFranchiseComission(@PathVariable("localFranchiseId") int localFranchiseId) throws Exception {
		  
			  List<SellerOrder> sellerOrderList = null;
			  try {			     
				  sellerOrderList = sellerOrderService.getSellerOrderLocalFranchiseComission(localFranchiseId);
				  log.info("Today's Seller Order List fetched"); 
		  
			  } catch(Exception e) {
				  e.printStackTrace();
				  log.info("Exception while fetching today's Seller Order list : "+e.toString());
			  } 
			  return sellerOrderList;
	  }
	  
	  @RequestMapping(value = "/getSellerOrderTalukaFranchiseComission/{talukaFranchiseId}", method = RequestMethod.GET) 
	  public @ResponseBody List<SellerOrder> getSellerOrderTalukaFranchiseComission(@PathVariable("talukaFranchiseId") int talukaFranchiseId) throws Exception {
		  
			  List<SellerOrder> sellerOrderList = null;
			  try {			     
				  sellerOrderList = sellerOrderService.getSellerOrderTalukaFranchiseComission(talukaFranchiseId);
				  log.info("Today's Seller Order List fetched"); 
		  
			  } catch(Exception e) {
				  e.printStackTrace();
				  log.info("Exception while fetching today's Seller Order list : "+e.toString());
			  } 
			  return sellerOrderList;
	  }
	  
	  
	  @RequestMapping(value = "/getSellerOrderDistrictFranchiseComission/{districtFranchiseId}", method = RequestMethod.GET) 
	  public @ResponseBody List<SellerOrder> getSellerOrderDistrictFranchiseComission(@PathVariable("districtFranchiseId") int districtFranchiseId) throws Exception {
		  
			  List<SellerOrder> sellerOrderList = null;
			  try {			     
				  sellerOrderList = sellerOrderService.getSellerOrderDistrictFranchiseComission(districtFranchiseId);
				  log.info("Today's Seller Order List fetched"); 
		  
			  } catch(Exception e) {
				  e.printStackTrace();
				  log.info("Exception while fetching today's Seller Order list : "+e.toString());
			  } 
			  return sellerOrderList;
	  }
	  
	  /******************  Old 
	  
	  @RequestMapping(value = "/saveOrUpdateSellerOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	  public void saveOrUpdateSellerOrder(@RequestBody SellerOrder sellerOrder) throws Exception {
	  
		  try { 
			    sellerOrderService.saveOrUpdateSellerOrder(sellerOrder);
			  	log.info("Seller Order record saved successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("Seller Order failed to saved: "+ e.toString());
		  }
	  }
	  
	  @RequestMapping(value = "/getSellerOrder/{sellerOrderId}", method = RequestMethod.GET) 
	  public @ResponseBody SellerOrder getSellerOrderById(@PathVariable("sellerOrderId") int sellerOrderId) throws Exception {
	  
		  SellerOrder sellerOrder = null;
		  try {
		      log.info("seller Order fetched"); 
		      sellerOrder = sellerOrderService.getSellerOrderById(sellerOrderId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching seller Order data : "+e.toString());
		  } 
		  return sellerOrder;
	  }
	  
	  @RequestMapping(value = "/sellerOrderList", method = RequestMethod.GET)
	  public @ResponseBody List<SellerOrder> sellerOrderList() throws Exception {
	  
		  List<SellerOrder> sellerOrderList = null;
		  try { 
			    sellerOrderList = sellerOrderService.getSellerOrderList();
			  	log.info("Display all Seller Order List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching Seller Order data : "+e.toString()); 
		  } 
		  return sellerOrderList;
	  }
 	  
	  @RequestMapping(value = "/activateOrDeactivateSellerOrder", method = RequestMethod.POST) 
	  public void activateOrDeactivateSellerOrder(@RequestBody SellerOrder sellerOrder) throws Exception {
	  
		  try { 
			    sellerOrderService.activateOrDeactivateSellerOrder(sellerOrder);
			    log.info("Seller Order activation status changed successfully.");   
		  
		  } catch (Exception e) { 
			    e.printStackTrace();
			    log.info("Exception at Seller Order activation status : "+e.toString());
		  } 
	  } 
	  
	  @RequestMapping(value = "/getSellerOrderList/{mobileNumber}/{date}", method = RequestMethod.GET) 
	  public @ResponseBody Seller getSellerOrderList(@PathVariable("mobileNumber") long mobileNumber, @PathVariable("date") String date) throws Exception {
	  
		  Seller Seller = null;
		  try {
		      log.info("seller Order fetched"); 
		      Seller = sellerOrderService.getSellerOrderList(mobileNumber, date);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching seller Order data : "+e.toString());
		  } 
		  return Seller;
	  }
	  
	  *******************/ 
}
