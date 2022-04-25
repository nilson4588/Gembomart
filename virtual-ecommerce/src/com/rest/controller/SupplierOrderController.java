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

import com.rest.model.ProductSalesPrice;
import com.rest.model.SupplierOrder;
import com.rest.model.SupplierOrderDetails;
import com.rest.model.SupplierProductList;
import com.rest.service.SupplierOrderService;

@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class SupplierOrderController {

	@Autowired
	SupplierOrderService supplierOrderService;
	
	static final Logger log = Logger.getLogger(SupplierOrderController.class);
	
	 @RequestMapping(value = "/createSupplierOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	 public void createSupplierOrder(@RequestBody List<SupplierOrderDetails> supplierOrderDetailsList) throws Exception {
	  
		  try { 
			    supplierOrderService.createSupplierOrder(supplierOrderDetailsList);
			  	log.info("Supplier Order record saved successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("Supplier Order failed to saved: "+ e.toString());
		  }
	  }
	  
	  @RequestMapping(value = "/supplierProductList/{supplierId}", method = RequestMethod.GET)
	  public @ResponseBody List<SupplierProductList> supplierProductList(@PathVariable("supplierId") int supplierId) throws Exception {
	  
		  List<SupplierProductList> supplierProductList = null;
		  try { 
			    supplierProductList = supplierOrderService.getSupplierProductList(supplierId);
			  	log.info("Display all Supplier Product List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching Supplier Product data : "+e.toString()); 
		  } 
		  return supplierProductList;
	  }
	  
	  @RequestMapping(value = "/getTodaysSupplierOrderList", method = RequestMethod.GET) 
	  public @ResponseBody List<SupplierOrder> getTodaysSupplierOrderList() throws Exception {
		  
			  List<SupplierOrder> sellerOrderList = null;
			  try {			     
				  sellerOrderList = supplierOrderService.getTodaysSupplierOrderList();
				  log.info("Today's Supplier Order List fetched"); 
		  
			  } catch(Exception e) {
				  e.printStackTrace();
				  log.info("Exception while fetching today's Supplier Order list : "+e.toString());
			  } 
			  return sellerOrderList;
	  }
	  
	  @RequestMapping(value = "/changeSupplierOrderStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	  public void changeSupplierOrderStatus(@RequestBody SupplierOrder order) throws Exception {
		  
			  try { 
				    supplierOrderService.changeSupplierOrderStatus(order);
				  	log.info("Supplier order status successfully.");		  	
		  
			  } catch(Exception e) {  
				    log.error("Supplier order failed to cancelled: "+ e.toString());
			  }
	  }
	  	  
	  @RequestMapping(value = "/removeProductFromSupplierOrder/{supplierOrderDetailsId}", method = RequestMethod.GET) 
	  public void removeProductFromSupplierOrder(@PathVariable("supplierOrderDetailsId") int supplierOrderDetailsId) throws Exception {
	  		 
		  try {
		      log.info("product removed"); 
		      supplierOrderService.removeProductFromSupplierOrderDetails(supplierOrderDetailsId);
		  } catch(Exception e) {	  
			  log.info("Exception while product remove : "+e.toString());
		  } 		  
	  }
	
	  @RequestMapping(value = "/getTodaysSupplierOrderByMobileNo/{mobileNumber}", method = RequestMethod.GET) 
	  public @ResponseBody List<SupplierOrderDetails> getTodaysSupplierOrderByMobileNo(@PathVariable("mobileNumber") long mobileNumber) throws Exception {
		  
		  List<SupplierOrderDetails> supplierOrderDetailsList = null;
			  try {			     
				  supplierOrderDetailsList = supplierOrderService.getTodaysSupplierOrderByMobileNo(mobileNumber);
				  log.info("Today's Supplier Order List fetched"); 
		  
			  } catch(Exception e) {
				  e.printStackTrace();
				  log.info("Exception while fetching today's Supplier Order list : "+e.toString());
			  } 
			  return supplierOrderDetailsList;
	  }
	  
	  @RequestMapping(value = "/getSupplierOrderById/{supplierOrderId}", method = RequestMethod.GET) 
	  public @ResponseBody List<SupplierOrderDetails> getSupplierOrderById(@PathVariable("supplierOrderId") int supplierOrderId) throws Exception {
		  
		      List<SupplierOrderDetails> supplierOrderDetailsList = null;
			  try {			     
				  supplierOrderDetailsList = supplierOrderService.getSupplierOrderById(supplierOrderId);
				  log.info("Today's Supplier Order List fetched"); 
		  
			  } catch(Exception e) {
				  e.printStackTrace();
				  log.info("Exception while fetching today's Supplier Order list : "+e.toString());
			  } 
			  return supplierOrderDetailsList;
	  }
	  
	  @RequestMapping(value = "/getProductSalesPrice/{productId}", method = RequestMethod.GET) 
	  public @ResponseBody List<ProductSalesPrice> getProductSalesPriceList(@PathVariable("productId") int productId) throws Exception {
		  
		      List<ProductSalesPrice> productSalesPriceList = null;
			  try {			     
				  productSalesPriceList = supplierOrderService.getProductSalesPriceList(productId);
				  log.info("Today's Supplier price List fetched"); 
		  
			  } catch(Exception e) {
				  e.printStackTrace();
				  log.info("Exception while fetching today's Supplier price list : "+e.toString());
			  } 
			  return productSalesPriceList;
	  }
	
	/*
	 * @RequestMapping(value = "/saveOrUpdateSupplierOrder", method =
	 * RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) public void
	 * saveOrUpdateSupplier(@RequestBody SupplierOrder supplierOrder) throws
	 * Exception {
	 * 
	 * try { supplierOrderService.saveOrUpdateSupplierOrder(supplierOrder);
	 * log.info("Supplier Order record saved successfully.");
	 * 
	 * } catch(Exception e) { log.error("Supplier Order failed to saved: "+
	 * e.toString()); } }
	 * 
	 * @RequestMapping(value = "/getSupplierOrder/{supplierOrderId}", method =
	 * RequestMethod.GET) public @ResponseBody SupplierOrder
	 * getSupplierOrderById(@PathVariable("supplierOrderId") int supplierOrderId )
	 * throws Exception {
	 * 
	 * SupplierOrder supplierOrder = null; try { log.info("Supplier Order fetched");
	 * supplierOrder = supplierOrderService.getSupplierOrderById(supplierOrderId); }
	 * catch(Exception e) {
	 * log.info("Exception while fetching Supplier Order data : "+e.toString()); }
	 * return supplierOrder; }
	 * 
	 * @RequestMapping(value = "/supplierOrderList", method = RequestMethod.GET)
	 * public @ResponseBody List<SupplierOrder> sellerOrderList() throws Exception {
	 * 
	 * List<SupplierOrder> supplierOrderList = null; try { supplierOrderList =
	 * supplierOrderService.getSupplierOrderList();
	 * log.info("Display all Supplier Order List");
	 * 
	 * }catch(Exception e){
	 * log.info("Exception while fetching Supplier Order data : "+e.toString()); }
	 * return supplierOrderList; }
	 * 
	 * @RequestMapping(value = "/activateOrDeactivateSupplierOrder", method =
	 * RequestMethod.POST) public void activateOrDeactivateSellerOrder(@RequestBody
	 * SupplierOrder supplierOrder) throws Exception {
	 * 
	 * try { supplierOrderService.activateOrDeactivateSupplierOrder(supplierOrder);
	 * log.info("Supplier Order activation status changed successfully.");
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * log.info("Exception at Supplier Order activation status : "+e.toString()); }
	 * }
	 * 
	 * @RequestMapping(value = "/supplierProductList/{supplierId}", method =
	 * RequestMethod.GET) public @ResponseBody List<SupplierProductList>
	 * supplierProductList(@PathVariable("supplierId") int supplierId ) throws
	 * Exception {
	 * 
	 * List<SupplierProductList> supplierProudctList = null; try {
	 * log.info("Supplier Product List fetched"); supplierProudctList =
	 * supplierOrderService.getSupplierProductList(supplierId); } catch(Exception e)
	 * { log.info("Exception while fetching Supplier Product List : "+e.toString());
	 * } return supplierProudctList; }
	 * 
	 * @RequestMapping(value = "/getTodaysSupplierOrderList", method =
	 * RequestMethod.GET) public @ResponseBody List<Supplier> getSupplierOrderList()
	 * throws Exception {
	 * 
	 * List<Supplier> supplierList = null; try { log.info("supplier Order fetched");
	 * supplierList = supplierOrderService.getTodaysSupplierOrderList(); }
	 * catch(Exception e) { e.printStackTrace();
	 * log.info("Exception while fetching supplier Order data : "+e.toString()); }
	 * return supplierList; }
	 */
	  
	/*
	 * @RequestMapping(value = "/getTodaysSupplierOrderList", method =
	 * RequestMethod.GET) public @ResponseBody List<SupplierOrder>
	 * getTodaysSupplierOrderList() throws Exception {
	 * 
	 * List<SupplierOrder> supplierOrderList = null; try { supplierOrderList =
	 * supplierOrderService.getTodaysSupplierOrderList();
	 * log.info("Today's Supplier Order List fetched");
	 * 
	 * } catch(Exception e) { e.printStackTrace();
	 * log.info("Exception while fetching today's supplier order list : "+e.toString
	 * ()); } return supplierOrderList; }
	 */
	  
	/*
	 * @RequestMapping(value = "/changeSupplierOrderStatus", method =
	 * RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) public void
	 * changeSupplierOrderStatus(@RequestBody SupplierOrder order) throws Exception
	 * {
	 * 
	 * try { supplierOrderService.changeSupplierOrderStatus(order);
	 * log.info("Supplier order status successfully.");
	 * 
	 * } catch(Exception e) { log.error("Supplier order failed to cancelled: "+
	 * e.toString()); } }
	 */
}