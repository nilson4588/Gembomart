
package com.rest.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.CategoryWiseProduct;
import com.rest.model.Images;
import com.rest.model.Product;
import com.rest.model.ProductCategory;
import com.rest.model.ProductListByShop;
import com.rest.model.ShopListByProduct;
import com.rest.model.Variants;

@Component
public interface ProductDao {
	
	public int saveOrUpdateProduct(Product product) throws Exception; 

	public Product getProductById(int productId) throws Exception;
	
	public String getProductNameById(int productId) throws Exception;
	
	public Product getProductByName(String productName) throws Exception;

	public List<Product> getProductList() throws Exception;

	public List<Variants> getVariantsList(int productId) throws Exception;

	public List<Images> getImagesList(int productId) throws Exception;

	public List<String> getCollectionsList(int productId) throws Exception;

	public List<String> getTagsList(int productId) throws Exception;

	public List<Integer> getVariantListByImageId(int imageId) throws Exception;
	
	public List<ProductCategory> getProductAndCategoryList() throws Exception;
	
	public List<ProductCategory> getProductListByCategory(String categoryName) throws Exception;
	
	public List<CategoryWiseProduct> getCategoryWiseProductList(int stateCode, int districtCode, int talukaCode, String categoryName) throws Exception;

	public List<ShopListByProduct> getShopListByProduct(int stateCode, int districtCode, int talukaCode, int productId) throws Exception;

	public List<ProductListByShop> getProductListByShop(int sellerId) throws Exception;
}
