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

import com.rest.model.Cart;
import com.rest.model.Status;
import com.rest.service.CartService;
import com.rest.utility.PushNotification;
//import com.rest.utility.PushNotification;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartController {

	@Autowired
	CartService cartService;

	static final Logger log = Logger.getLogger(CartController.class);

	@RequestMapping(value = "/saveOrUpdateCart", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status saveCart(@RequestBody Cart cart) throws Exception {

		int successflag = 0;
		int cartId = cart.getCartId();
		int custId = cart.getCustomerId();
		int cartSellerId = cart.getSellerId();
		int productId = cart.getProductId();
		int quantity = cart.getQuantity();
		
		Cart prodCart = cartService.getCartByProductId(productId, custId);
		
		if(prodCart != null) {
			cartId = prodCart.getCartId();
			cart.setCartId(cartId);			
		}
		
		if(quantity != 0) {	

			int count = 0;
			int existingSellerId = 0;

			log.info("cart " + cart);

			List<Cart> crtList = cartService.getCartListByCustomerId(custId);
			log.info("crtList " + crtList);

			if (crtList.size() > 0) {
				count = crtList.size();
				existingSellerId = crtList.get(0).getSellerId();
			}

			try {
				if (count == 0 || (count > 0 && cartSellerId == existingSellerId)) {
					successflag = cartService.saveCart(cart);
					log.info("Product added to cart");

				} else if (count > 0 && cartSellerId != existingSellerId) {
					return new Status(3, "Are you sure want to discard current order and placed new one?");
				}

			} catch (Exception e) {

				log.error("Product failed to add to cart: " + e.toString());
				return new Status(successflag, "Product failed to add to cart");
			}

			
		} else if(quantity == 0) {
			
			successflag = cartService.deleteCartById(cartId);
			log.error("Cart removed "+cartId);
		}
				

		if (cartId > 0) {
			return new Status(successflag, "Cart updated");
		} else {
			return new Status(successflag, "Product added to cart");
		}
	}

	@RequestMapping(value = "/getCartProduct/{cartId}", method = RequestMethod.GET)
	public @ResponseBody Cart getCustomerById(@PathVariable("cartId") int cartId) throws Exception {

		Cart cart = null;
		try {
			log.info("get cart product");
			cart = cartService.getCartById(cartId);

		} catch (Exception e) {
			log.info("Exception while fetching cart product : " + e.toString());
		}
		return cart;
	}

	@RequestMapping(value = "/getCartListByCustomerId/{customerId}", method = RequestMethod.GET)
	public @ResponseBody List<Cart> getCartListByCustomerId(@PathVariable("customerId") int customerId)
			throws Exception {

		List<Cart> cartList = null;
		try {
			log.info("get Cart List By Customer Id");
			cartList = cartService.getCartListByCustomerId(customerId);

		} catch (Exception e) {
			log.info("Exception while fetching Cart List By Customer Id : " + e.toString());
		}
		return cartList;
	}

	@RequestMapping(value = "/deleteCartList/{customerId}", method = RequestMethod.GET)
	public @ResponseBody Status deleteCartList(@PathVariable("customerId") int customerId) throws Exception {

		int successflag = 0;
		try {
			log.info("delete Cart List");
			successflag = cartService.deleteCartList(customerId);

		} catch (Exception e) {
			log.info("Exception while deleting Cart List : " + e.toString());
			return new Status(successflag, "Failed to deleting Cart List");
		}
		return new Status(successflag, "delete Cart List");
	}

	@RequestMapping(value = "/deleteCartById/{cartId}", method = RequestMethod.GET)
	public @ResponseBody Status deleteCartById(@PathVariable("cartId") int cartId) throws Exception {

		int successflag = 0;
		try {
			log.info("delete Cart by id");
			successflag = cartService.deleteCartById(cartId);

		} catch (Exception e) {
			log.info("Exception while deleting Cart List : " + e.toString());
			return new Status(successflag, "Failed to delete cart by id");
		}
		return new Status(successflag, "Removed cart item");
	}

	
	@RequestMapping(value = "/getCartCount/{customerId}", method = RequestMethod.GET)
	public @ResponseBody Status getCartListCount(@PathVariable("customerId") int customerId) throws Exception {

		int count = 0;
		try {
			
			count = cartService.getCartListCount(customerId);
			log.info("Cart count :"+count);

		} catch (Exception e) {
			log.info("Exception to get Cart count : " + e.toString());
			return new Status(count, "Failed to get Cart count");
		}
		return new Status(count, "Cart count");
	}
	
	  @RequestMapping(value = "/notificationTesting/{token}", method = RequestMethod.GET)
	  public @ResponseBody Status notificationTesting(@PathVariable("token") String token) throws Exception {
	  
	  try { 
		  String[] userDevice = new String[] {token};
		  	  
	  PushNotification p = new PushNotification();
	  log.info("start notificationTesting");
	  p.pushFCMNotificationDelivery(userDevice, "title", "body", "key_1", "key_2");
	  System.out.println("done"); 
	  log.info("notificationTesting done");
	  return new Status(1, "Notification Test Success");
	  } catch(Exception e) {
	  log.info("Exception while notificationTesting done : "+e.toString()); }
	  return new Status(0, "Notification Test Failed");
	  }
	 
}
