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

import com.rest.model.ProductPurchase;
import com.rest.service.ProductPurchaseService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductPurchaseController {
	  
	  @Autowired 
	  ProductPurchaseService productPurchaseService;
	   
	  static final Logger log = Logger.getLogger(ProductPurchaseController.class);
	  
	  @RequestMapping(value = "/saveOrUpdateProductPurchase", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	  public void productPurchaseService(@RequestBody ProductPurchase productPurchase) throws Exception {
	  
		  try { 
			    productPurchaseService.saveOrUpdateProductPurchase(productPurchase);  
			  	log.info("Purchase record saved successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("Purchase failed to saved: "+ e.toString());
		  }
	  }
	  
	  @RequestMapping(value = "/getProductPurchase/{productPurchaseId}", method = RequestMethod.GET) 
	  public @ResponseBody ProductPurchase  getProductPurchaseById(@PathVariable("productPurchaseId") int productPurchaseId ) throws Exception {
	  
		  ProductPurchase productPurchase = null;
		  try {
		     log.info("Purchase Details fetched"); 
		     productPurchase = productPurchaseService.getProductPurchaseById(productPurchaseId);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Purchase data : "+e.toString());
		  } 
		  return productPurchase;
	  }
	  
	  @RequestMapping(value = "/productPurchaseList", method = RequestMethod.GET)
	  public @ResponseBody List<ProductPurchase> getProductPurchaseList() throws Exception {
	  
		  List<ProductPurchase> productPurchaseList = null;
		  try { 
			    productPurchaseList = productPurchaseService.getProductPurchaseList();
			  	log.info("Display all Purchase List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching Purchase data : "+e.toString()); 
		  } 
		  return productPurchaseList;
	  }
	  
	  
	  @RequestMapping(value = "/activateOrDeactivateProductPurchaseList", method = RequestMethod.POST) 
	  public void activateOrDeactivateProductPurchase(@RequestBody ProductPurchase productPurchase) throws Exception {
	  
		  try { 
			    productPurchaseService.activateOrDeactivateProductPurchase(productPurchase);
		        log.info("Product Purchase activation status changed successfully.");   
		  
		  } catch (Exception e) { 
			    e.printStackTrace();
			    log.info("Exception at Product Purchase activation status. "+e.toString());
		  } 
	  } 
}
