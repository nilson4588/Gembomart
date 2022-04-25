package com.rest.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.ProductPurchase;

@Component
public interface ProductPurchaseDao {

	  public int saveOrUpdateProductPurchase(ProductPurchase productPurchase) throws Exception;
	  
	  public ProductPurchase getProductPurchaseById(int productPurchaseId) throws Exception;
	  
	  public List<ProductPurchase> getProductPurchaseList() throws Exception;
	  
	  public int activateOrDeactivateProductPurchase(ProductPurchase productPurchase) throws Exception;
}