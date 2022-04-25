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

import com.rest.model.ProductPrice;
import com.rest.service.ProductPriceService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductPriceController {

	  @Autowired
	  ProductPriceService productPriceService;

	  static final Logger log = Logger.getLogger(ProductPriceController.class);

	  @RequestMapping(value = "/addOrUpdateProductPrice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	  public void saveOrUpdateProductPrice(@RequestBody ProductPrice productPrice) throws Exception {
	  		 
		  try { 
			    productPriceService.saveOrUpdateProductPrice(productPrice);
			  	log.info("Product Price record saved successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("Product Price failed to saved: "+ e.toString());
		  }
	  }
	  
	  @RequestMapping(value = "/getProductPrice/{productPriceId}", method = RequestMethod.GET) 
	  public @ResponseBody ProductPrice getProductPriceById(@PathVariable("productPriceId") int productPriceId ) throws Exception {
	  
		  ProductPrice productPrice = null;
		  try {
		      log.info("product Price Details fetched"); 
		      productPrice = productPriceService.getProductPriceById(productPriceId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching Product Price data : "+e.toString());
		  } 
		  return productPrice;
	  }
	  
	  @RequestMapping(value = "/getProductPriceByProduct/{productId}", method = RequestMethod.GET) 
	  public @ResponseBody ProductPrice getProductPriceByProductId(@PathVariable("productId") int productId ) throws Exception {
	  
		  ProductPrice productPrice = null;
		  try {
		      log.info("product Price Details fetched"); 
		      productPrice = productPriceService.getProductPriceByProductId(productId);
		  } catch(Exception e) {	  
			  log.info("Exception while fetching Product Price data : "+e.toString());
		  } 
		  return productPrice;
	  }
	  
	  @RequestMapping(value = "/productPriceActiveList", method = RequestMethod.GET)
	  public @ResponseBody List<ProductPrice> productPriceActiveList() throws Exception {
	  
		  List<ProductPrice> productPriceActiveList = null;
		  try { 
			  productPriceActiveList = productPriceService.getProductPriceActiveList();
			  log.info("Display all Product Price List");
			  	
		  }catch(Exception e){
			  log.info("Exception while fetching Product Price data : "+e.toString()); 
		  } 
		  return productPriceActiveList;
	  }	  
	
	  @RequestMapping(value = "/productPriceAllList", method = RequestMethod.GET)
	  public @ResponseBody List<ProductPrice> productPriceAllList() throws Exception {
	  
		  List<ProductPrice> productPriceActiveList = null;
		  try { 
			  productPriceActiveList = productPriceService.getProductPriceAllList();
			  log.info("Display all Product Price List");
			  	
		  }catch(Exception e){
			  log.info("Exception while fetching Product Price data : "+e.toString()); 
		  } 
		  return productPriceActiveList;
	  }	  
}