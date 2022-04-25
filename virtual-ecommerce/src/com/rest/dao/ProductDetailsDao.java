package com.rest.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.ProductDetails;
import com.rest.model.ProductDetailsSeller;

@Component
public interface ProductDetailsDao {

	 public int saveProductDetails(ProductDetails productDetails) throws Exception;
	 
	 public List<ProductDetails> getProductDetailsBySupplierId(int supplierId) throws Exception;
	 
	 public int saveProductDetailsSeller(ProductDetailsSeller productDetailsSeller) throws Exception;
	 
	 public List<ProductDetailsSeller> getProductDetailsBySellerId(int sellerId) throws Exception; 
}