
package com.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.CategoryWiseProduct;
import com.rest.model.Product;
import com.rest.model.ProductCategory;
import com.rest.model.ProductListByShop;
import com.rest.model.ShopListByProduct;

@Component
public interface ProductService {
	
	public int saveOrUpdateProduct(Product product) throws Exception;

	public Product getProductById(int productId) throws Exception;
	
	public Product getProductByName(String productName) throws Exception;
	
	public String getProductNameById(int productId) throws Exception;

	public List<Product> getProductList() throws Exception;
	
	public  List<ProductCategory> getProductAndCategoryList() throws Exception ;
	
	public List<ProductCategory> getProductListByCategory(String categoryName) throws Exception;
	
	public List<CategoryWiseProduct> getCategoryWiseProductList(int stateCode, int districtCode, int talukaCode, String categoryName) throws Exception;
	
	public List<ShopListByProduct> getShopListByProduct(int stateCode, int districtCode, int talukaCode, int productId) throws Exception;

	public List<ProductListByShop> getProductListByShop(int sellerId) throws Exception;

	
}
