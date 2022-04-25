package com.rest.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.Cart;

@Component 
public interface CartDao {

	  public int saveCart(Cart cart) throws Exception;
	  
	  public Cart getCartById(int cartId) throws Exception;
	  
	  public List<Cart> getCartListByCustomerId(int customerId) throws Exception;
	  
	  public int deleteCartList(int customerId) throws Exception;
	  
	  public int deleteCartById(int cartId) throws Exception;
	  
	  public int getCartListCount(int customerId) throws Exception;
	  
	  public Cart getCartByProductId(int productId, int customerId) throws Exception;
	  
	  public int updateCart(int customerId, int productId, int quantity) throws Exception; 
}
