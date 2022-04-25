
package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.ProductDao;
import com.rest.model.CategoryWiseProduct;
import com.rest.model.Images;
import com.rest.model.Product;
import com.rest.model.ProductCategory;
import com.rest.model.ProductListByShop;
import com.rest.model.ShopListByProduct;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	
	@Override 
	public int saveOrUpdateProduct(Product product) throws Exception{
	
		String category = product.getCategory();
		
		product.setDescription("Good for Health");
		product.setType(category);
		product.setBrand("gembomart");
		product.setPacksize("Full");
		product.setPrice(0);
		product.setSale(false);
		product.setDiscount("0");
		product.setStock(0);
		product.setIsnew(false);
		product.setIsActive(1);
		
		int count = productDao.saveOrUpdateProduct(product);
		
		return count;
	}

	@Override 
	public Product getProductById(int productId) throws Exception { 
		//TODO Auto-generated method stub 
		Product product = productDao.getProductById(productId);
		  
		product.setCollection(productDao.getCollectionsList(productId));
    	product.setTags(productDao.getTagsList(productId));
    	product.setVariants(productDao.getVariantsList(productId));
  
    	List<Images> imagesList  = productDao.getImagesList(productId); 
    	List<Images> imagesList1 = new ArrayList<Images>(); 
    	for(Images images : imagesList) { 
    		int imageId = images.getImage_id();
    		images.setVariant_id(productDao.getVariantListByImageId(imageId));
    		imagesList1.add(images);
    	} 
    	product.setImages(imagesList1);
	  
		return product; 
	}

	@Override 
	public Product getProductByName(String productName) throws Exception{
		return productDao.getProductByName(productName);
	}
	
	@Override 
	public List<Product> getProductList() throws Exception { 
		// TODO  Auto-generated method stub 
		List<Product> productList = productDao.getProductList();
        List<Product> productList1 = new ArrayList<Product>();
        
        for (Product product : productList) {
        		  
        	int productId = product.getId();
        	// System.out.println("productId :"+productId);
        	product.setCollection(productDao.getCollectionsList(productId));
        	product.setTags(productDao.getTagsList(productId));
        	product.setVariants(productDao.getVariantsList(productId));
	  
        	List<Images> imagesList  = productDao.getImagesList(productId); 
        	List<Images> imagesList1 = new ArrayList<Images>(); 
        	for(Images images : imagesList) { 
        		int imageId = images.getImage_id();
        		images.setVariant_id(productDao.getVariantListByImageId(imageId));
        		imagesList1.add(images);
        	} 
        	product.setImages(imagesList1);
	  
        	productList1.add(product); 
	    }
       
        return productList1; 
    }
	
	@Override
	public  List<ProductCategory> getProductAndCategoryList() throws Exception {
		return productDao.getProductAndCategoryList();
	}
	
	@Override
	public String getProductNameById(int productId) throws Exception {
		return productDao.getProductNameById(productId);
	}
	
	@Override
	public List<ProductCategory> getProductListByCategory(String categoryName) throws Exception{
		return productDao.getProductListByCategory(categoryName);
	}
	
	@Override
	public List<CategoryWiseProduct> getCategoryWiseProductList(int stateCode, int districtCode, int talukaCode, String categoryName) throws Exception {
		return productDao.getCategoryWiseProductList(stateCode, districtCode, talukaCode, categoryName);
	}
	
	@Override
	public List<ShopListByProduct> getShopListByProduct(int stateCode, int districtCode, int talukaCode, int productId) throws Exception {
		return productDao.getShopListByProduct(stateCode, districtCode, talukaCode, productId);
	}
	
	@Override
	public List<ProductListByShop> getProductListByShop(int sellerId) throws Exception {
		return productDao.getProductListByShop(sellerId);
	}
}