package com.rest.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.ProductPrice;

@Component
public interface ProductPriceDao {
	
	  public void saveOrUpdateProductPrice(ProductPrice productPrice) throws Exception;
	  
	  public ProductPrice getProductPriceById(int productPriceId) throws Exception;
	  
	  public ProductPrice getProductPriceByProductId(int productId) throws Exception;
	  
	  public List<ProductPrice> getProductPriceActiveList() throws Exception;
	  
	  public List<ProductPrice> getProductPriceAllList() throws Exception;	
	  
	  public int getActiveProductPriceIdByProductId(int productId) throws Exception;
	  
	  public void changeProductPriceStatus(int productPriceId) throws Exception;
}
