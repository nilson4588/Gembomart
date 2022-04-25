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

import com.rest.model.ProductSeller;
import com.rest.model.Status;
import com.rest.service.ProductSellerService;

@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductSellerController {

	@Autowired
	ProductSellerService productSellerService;
	
	static final Logger log = Logger.getLogger(ProductSellerController.class);
	
	@RequestMapping(value = "/saveOrUpdateProductSeller", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	public @ResponseBody Status saveOrUpdateProductSeller(@RequestBody ProductSeller productSeller) throws Exception {
	  
		  int successflag = 0;
		  int productSellerId = productSeller.getProductSellerId();
		  int sellerId = productSeller.getSellerId();
		  int productId = productSeller.getProductId();
		  try {			  
			    ProductSeller ps = productSellerService.getProductSellerByProductAndSeller(sellerId, productId);	
			    if(ps != null && productSellerId == 0) {
		  		     log.info("Product already registered");
		  		     return new Status(0, "Product already registered.");		 
		       } else {
		    	    successflag = productSellerService.saveOrUpdateProductSeller(productSeller);
				  	log.info("Seller Product added");			  	  
			   } 
			  		  
		  } catch(Exception e) {  
			    
			    log.error("Seller Product failed to add: "+ e.toString());
			    return new Status(successflag, "Seller Product failed to ad");
		  }
		  
		  if(productSellerId>0) {
			  return new Status(successflag, "Seller Product updated");
		  } else {
			  return new Status(successflag, "Seller Product added");
		  }  
	}
	
	@RequestMapping(value = "/getProductSellerById/{productSellerId}", method = RequestMethod.GET)  
	public @ResponseBody ProductSeller getProductSellerById(@PathVariable("productSellerId") int productSellerId ) throws Exception {  	
		
		ProductSeller productSeller = null;
		try {
			    log.info("get Product Seller By Id");
			    productSeller =  productSellerService.getProductSellerById(productSellerId);  
			    
	    } catch(Exception e) { 	
	    	   log.info("Exception while fetching Product Seller By Id : "+e.toString());               
	    }
		return productSeller;
	}
	
	
	@RequestMapping(value = "/getProductSellerListBySellerId/{sellerId}", method = RequestMethod.GET)  
	public @ResponseBody List<ProductSeller> getProductSellerListBySellerId(@PathVariable("sellerId") int sellerId ) throws Exception {  	
		
		List<ProductSeller> productSellerList = null;
		try {
			    log.info("get Product Seller List By Seller Id");
			    productSellerList =  productSellerService.getProductSellerListBySellerId(sellerId);
			    
	    } catch(Exception e) { 	
	    	   log.info("Exception while Product Seller List By Seller Id : "+e.toString());               
	    }
		return productSellerList;
	}
	
	@RequestMapping(value = "/getProductSellerListByDeactiveSellerId/{sellerId}", method = RequestMethod.GET)  
	public @ResponseBody List<ProductSeller> getProductSellerListByDeactiveSellerId(@PathVariable("sellerId") int sellerId ) throws Exception {  	
		
		List<ProductSeller> productSellerList = null;
		try {
			    log.info("get Product Seller List By deactive Seller Id");
			    productSellerList =  productSellerService.getProductSellerListByDeactiveSellerId(sellerId);
			    
	    } catch(Exception e) { 	
	    	   log.info("Exception while Product Seller List By deactive Seller Id : "+e.toString());               
	    }
		return productSellerList;
	}
	
	
	  @RequestMapping(value = "/activateOrDeactivateProductSeller", method = RequestMethod.POST) 
	  public void activateOrDeactivateProductSeller(@RequestBody ProductSeller productSeller) throws Exception {
	  
		  try { 
			     productSellerService.activateOrDeactivateProductSeller(productSeller);
			     log.info("Product activation status changed successfully.");   
		  
		  } catch (Exception e) { 
			    e.printStackTrace();
			    log.info("Exception at Seller Product activation status : "+e.toString());
		  } 
	  } 
	  
	  
	    @RequestMapping(value = "/isSellerProductExists/{sellerId}/{productId}", method = RequestMethod.GET)
		public @ResponseBody Status isSellerProductExists(@PathVariable("sellerId") int sellerId,@PathVariable("productId") int productId) throws Exception {
					  	    
			  try {
			  
				   ProductSeller productSeller = productSellerService.getProductSellerByProductAndSeller(sellerId, productId);	   
			       if(productSeller != null) {
			  		     log.info("Product already registered");
			  		     return new Status(0, "Product already registered.");		 
			       } else {
			  		     log.error("Product available for registration"); 
			  		     return new Status(1, "Product available for registration.");
			  	   } 
			  
			  } catch(Exception ex) { 
				         ex.printStackTrace();
			  			 log.error("Exception @isSellerProductExists "+ex.toString()); 
			  			 return new Status(0, "Failed to recognize. Please try again."); 
			  }	  
		}
}
