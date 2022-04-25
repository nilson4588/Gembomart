package com.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.ProductSeller;

@Component
public interface ProductSellerService {

      public int saveOrUpdateProductSeller(ProductSeller productSeller) throws Exception;
	  
	  public ProductSeller getProductSellerById(int productSellerId) throws Exception;
	  
	  public List<ProductSeller> getProductSellerListBySellerId(int sellerId) throws Exception;
	  
	  public List<ProductSeller> getProductSellerListByDeactiveSellerId(int sellerId) throws Exception;
	  
	  public int activateOrDeactivateProductSeller(ProductSeller productSeller) throws Exception;
	  
	  public ProductSeller getProductSellerByProductAndSeller(int sellerId, int productId) throws Exception;
}
