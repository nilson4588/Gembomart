package com.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rest.service.CityService;
import com.rest.service.ProductSellerService;
import com.rest.service.SellerService;
import com.rest.utility.ConstantsUtil;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {

	static final Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	CityService cityService;
	
	@Autowired
	SellerService sellerService;
	
	@Autowired
	ProductSellerService productSellerService;
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView forgetPasswordOnWeb(ModelAndView model) {       
       try{
    	   		 model.addObject("stateList", cityService.getStateList());
    	   		 model.addObject("imgLocation", ConstantsUtil.SERVER_IMG_LOCATION);
    	         model.setViewName("home");		  
	 	   }catch(Exception ex){
	 		     logger.error("Exceptions @home : "+ex.toString());
           }
       return model;
    }
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model) {       
       try{
    	         model.addObject("stateList", cityService.getStateList());
    	         model.setViewName("login");		  
	 	   }catch(Exception ex){
	 		     logger.error("Exceptions @login : "+ex.toString());
           }
       return model;
    }
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(ModelAndView model) {       
       try{
    	   		model.addObject("stateList", cityService.getStateList());
    	         model.setViewName("register");		  
	 	   }catch(Exception ex){
	 		     logger.error("Exceptions @register : "+ex.toString());
           }
       return model;
    }
	
	@RequestMapping(value = "/product/{productSellerId}", method = RequestMethod.GET)
    public ModelAndView product(ModelAndView model, @PathVariable("productSellerId") int productSellerId) {       
       try{
    	         model.addObject("imgLocation", ConstantsUtil.SERVER_IMG_LOCATION1);
    	         model.addObject("product", productSellerService.getProductSellerById(productSellerId));
    	   		 model.addObject("stateList", cityService.getStateList());
    	         model.setViewName("product");		  
	 	   }catch(Exception ex){
	 		     logger.error("Exceptions @product : "+ex.toString());
           }
       return model;
    }
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView cart(ModelAndView model) {       
       try{
    	   		 model.addObject("stateList", cityService.getStateList());
    	         model.setViewName("cart");		  
	 	   }catch(Exception ex){
	 		     logger.error("Exceptions @cart : "+ex.toString());
           }
       return model;
    }
	
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView checkout(ModelAndView model) {       
       try{
    	   		model.addObject("stateList", cityService.getStateList());
    	         model.setViewName("checkout");		  
	 	   }catch(Exception ex){
	 		     logger.error("Exceptions @checkout : "+ex.toString());
           }
       return model;
    }
	
	@RequestMapping(value = "/order-success", method = RequestMethod.GET)
    public ModelAndView orderSuccess(ModelAndView model) {       
       try{
    	   		 model.addObject("stateList", cityService.getStateList());
    	         model.setViewName("order-success");		  
	 	   }catch(Exception ex){
	 		     logger.error("Exceptions @order-success : "+ex.toString());
           }
       return model;
    }
	
	@RequestMapping(value = "/wishlist", method = RequestMethod.GET)
    public ModelAndView wishlist(ModelAndView model) {       
       try{
    	   		 model.addObject("stateList", cityService.getStateList());
    	         model.setViewName("wishlist");		  
	 	   }catch(Exception ex){
	 		     logger.error("Exceptions @wishlist : "+ex.toString());
           }
       return model;
    }
	
	@RequestMapping(value = "/productlist/{sellerId}", method = RequestMethod.GET)
    public ModelAndView productlist(ModelAndView model, @PathVariable("sellerId") int sellerId ) {       
       try{
    	       // logger.info("object : "+productSellerService.getProductSellerListBySellerId(sellerId));
    	   
    	   		 model.addObject("imgLocation", ConstantsUtil.SERVER_IMG_LOCATION1);
    	   		 model.addObject("productList", productSellerService.getProductSellerListBySellerId(sellerId));
    	   		 model.addObject("sellerDetails", sellerService.getSellerById(sellerId));
    	   		 model.addObject("stateList", cityService.getStateList());
    	         model.setViewName("product-list");		  
	 	   }catch(Exception ex){
	 		     logger.error("Exceptions @productlist : "+ex.toString());
           }
       return model;
    }
}
