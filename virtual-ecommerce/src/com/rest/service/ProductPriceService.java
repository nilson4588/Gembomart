package com.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.ProductPrice;

@Component
public interface ProductPriceService {

	  public void saveOrUpdateProductPrice(ProductPrice productPrice) throws Exception;
	  
	  public ProductPrice getProductPriceById(int productPriceId) throws Exception;
	  
	  public ProductPrice getProductPriceByProductId(int productId) throws Exception;
	  
	  public List<ProductPrice> getProductPriceActiveList() throws Exception;
	  
	  public List<ProductPrice> getProductPriceAllList() throws Exception;	
	
}
