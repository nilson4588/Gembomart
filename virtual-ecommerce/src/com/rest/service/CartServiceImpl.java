package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.CartDao;
import com.rest.model.Cart;
import com.rest.model.ProductSeller;

@Service 
public class CartServiceImpl implements CartService {

	@Autowired 
	CartDao cartDao;
	
	@Autowired 
	ProductSellerService productSellerService;
	
	@Autowired
	SellerService sellerService;
	
	@Autowired
	CityService cityService;
	
	@Override
	public int saveCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		
		int productId = cart.getProductId();
		int sellerId = cart.getSellerId();
		int quantity = cart.getQuantity();
	
		int talukaCode = sellerService.getTalukaCodeBySellerId(sellerId);
		int deliveryCharges = cityService.getDeliveryChargesByTalukaCode(talukaCode);
	
		
		ProductSeller prodSeller = productSellerService.getProductSellerByProductAndSeller(sellerId, productId);
		
		int rate  = (int) prodSeller.getProductPrice();
		int discount = (int) prodSeller.getProductDiscount();
		
		int amount = (quantity*rate) - discount;
		
		cart.setRate(rate);
		cart.setDiscount(discount);
		cart.setAmount(amount);
		cart.setDeliveryCharges(deliveryCharges);
				
		return cartDao.saveCart(cart);
	}

	@Override
	public Cart getCartById(int cartId) throws Exception {
		// TODO Auto-generated method stub
		Cart cart  = cartDao.getCartById(cartId);
		int productId = cart.getProductId();
		int sellerId = cart.getSellerId();
		int quantity = cart.getQuantity();
		String shopName = sellerService.getShopNameBySellerId(sellerId);
		int areaCode = sellerService.getTalukaCodeBySellerId(sellerId);
		int deliveryCharges = cityService.getDeliveryChargesByTalukaCode(areaCode);
				
		ProductSeller prodSeller = productSellerService.getProductSellerByProductAndSeller(sellerId, productId);
		
		int rate  = (int) prodSeller.getProductPrice();
		int discount = (int) prodSeller.getProductDiscount();
		
		int amount = (quantity*rate) - discount;
		
		cart.setRate(rate);
		cart.setDiscount(discount);
		cart.setAmount(amount);
		cart.setShopName(shopName);
		cart.setAreaCode(areaCode);
		cart.setDeliveryCharges(deliveryCharges);
		
		return cart; 
	}

	@Override
	public List<Cart> getCartListByCustomerId(int customerId) throws Exception {
		// TODO Auto-generated method stub
		List<Cart> cartList = cartDao.getCartListByCustomerId(customerId);
		List<Cart> cartList1 = new ArrayList<Cart>();
		for (Cart cart : cartList) {
			
			int productId = cart.getProductId();
			int sellerId = cart.getSellerId();
			int quantity = cart.getQuantity();
			String shopName = sellerService.getShopNameBySellerId(sellerId);
			int areaCode = sellerService.getTalukaCodeBySellerId(sellerId);
			int deliveryCharges = cityService.getDeliveryChargesByTalukaCode(areaCode);
			
			ProductSeller prodSeller = productSellerService.getProductSellerByProductAndSeller(sellerId, productId);
			
			int rate  = (int) prodSeller.getProductPrice();
			int discount = (int) prodSeller.getProductDiscount();
			
			int amount = (quantity*rate) - discount;
			
			cart.setRate(rate);
			cart.setDiscount(discount);
			cart.setAmount(amount);
			
			cart.setShopName(shopName);
			cart.setAreaCode(areaCode);
			cart.setDeliveryCharges(deliveryCharges);
			
			cartList1.add(cart);
		}
		return cartList1;
	}

	@Override
	public int deleteCartList(int customerId) throws Exception {
		// TODO Auto-generated method stub
		return cartDao.deleteCartList(customerId);
	}
	
	@Override
	public int deleteCartById(int cartId) throws Exception {
		return cartDao.deleteCartById(cartId);
	}
	
	@Override
	public int getCartListCount(int customerId) throws Exception {
		return cartDao.getCartListCount(customerId);
	}
	
	@Override
	public Cart getCartByProductId(int productId, int customerId) throws Exception{
		return cartDao.getCartByProductId(productId, customerId);
	}
	
	@Override
	public int updateCart(int customerId, int productId, int quantity) throws Exception {
		return cartDao.updateCart(customerId, productId, quantity);
	}
}