package com.rest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.model.CategoryWiseProduct;
import com.rest.model.Product;
import com.rest.model.ProductCategory;
import com.rest.model.ProductDetails;
import com.rest.model.ProductDetailsSeller;
import com.rest.model.ProductListByShop;
import com.rest.model.ShopListByProduct;
import com.rest.model.Status;
import com.rest.service.ProductDetailsService;
import com.rest.service.ProductService;
import com.rest.utility.ConstantsUtil;
import com.rest.utility.DateTimeUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductDetailsService productDetailsService;

	static final Logger log = Logger.getLogger(ProductController.class);
		
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/addOrUpdateProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	public @ResponseBody Status saveOrUpdateProduct(@RequestBody Product product) throws Exception {
	  	
		Product prod = productService.getProductByName(product.getTitle());
		try {   
			  if(prod != null) {
				  productService.saveOrUpdateProduct(product);
				  log.info("Product saved successfully.");	
				  return new Status(1, "Product saved successfully.");
				  
			  }else {
				  log.info("Product already registered.");	
				  return new Status(2, "Product already registered.");
			  }			
	   
		} catch (Exception e) {  
			  e.printStackTrace();
			  log.error("Product failed to saved: "+ e.toString());
			  return new Status(0, "Product failed to saved.");
		}		
	}
	
	@RequestMapping(value = "/getProduct/{productId}", method = RequestMethod.GET)
	public @ResponseBody Product getProductById(@PathVariable("productId") int productId) throws Exception {

		Product product = null;
		try {
			
			product = productService.getProductById(productId);
			log.info("Product Details");

		} catch (Exception e) {
			log.info("Exception while fetching Product data : " + e.toString());			
		}
		return product;
	}

	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	public @ResponseBody List<Product> getProductList() throws Exception {

		List<Product> productList = null;
		try {
			productList = productService.getProductList();
			log.info("Display all Product List");

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception while fetching Product list : " + e.getMessage());

		}
		return productList;
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/addOrUpdateProductDetails", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
	public void saveOrUpdateProductDetails(@RequestParam(required = true, value= "productDetailsData") String productDetailsData, @RequestParam(required = true, value= "productImage") MultipartFile productImage) throws Exception {
	  		//System.out.println("Hellooooooooo"); 
		try {   				
	          String fileName = "";
	         	       // System.out.println(productImage.getSize());			          
	          if(productImage.getSize() > 0) {
	          
				  fileName =
				  "product_"+DateTimeUtil.getTimeStampInMiliseconds()+productImage.getOriginalFilename().substring(
						  productImage.getOriginalFilename().lastIndexOf("."));
				  //System.out.println(fileName); 
				  File file = new File(ConstantsUtil.IMAGE_LOCATION+"supplier\\"+fileName); 
				  try {
					  FileOutputStream fos = new FileOutputStream(file);
					  fos.write(productImage.getBytes()); fos.close(); 
				  } catch (IOException e) {
					  e.printStackTrace(); 
				  }
	          }
		 
	          ObjectMapper objectMapper = new ObjectMapper();
	          ProductDetails productDetails = objectMapper.readValue(productDetailsData, ProductDetails.class);
	          
	          productDetails.setProductImage(fileName);
	          productDetailsService.saveProductDetails(productDetails);
			  log.info("Product Details saved successfully.");	
	   
		 } catch (Exception e) {  
			  e.printStackTrace();
			  log.error("Product Details failed to saved: "+ e.toString());
		 }		
	}
	
	@RequestMapping(value = "/getProductDetailsBySupplierId/{supplierId}", method = RequestMethod.GET)
	public @ResponseBody List<ProductDetails> getProductDetailsBySupplierId(@PathVariable("supplierId") int supplierId) throws Exception {

		List<ProductDetails> products = null;
		try {
			
			products = productDetailsService.getProductDetailsBySupplierId(supplierId);
			log.info("Product Details");

		} catch (Exception e) {
			log.info("Exception while fetching Product data : " + e.toString());			
		}
		return products;
	}

	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/addOrUpdateProductDetailsSeller", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
	public void saveOrUpdateProductDetailsSeller(@RequestParam(required = true, value= "productDetailsData") String productDetailsData, @RequestParam(required = true, value= "productImage") MultipartFile productImage) throws Exception {
	  		//System.out.println("Hellooooooooo"); 
		try {   				
	          String fileName = "";
	         	       // System.out.println(productImage.getSize());			          
	          if(productImage.getSize() > 0) {
	          
				  fileName =
				  "product_"+DateTimeUtil.getTimeStampInMiliseconds()+productImage.getOriginalFilename().substring(
						  productImage.getOriginalFilename().lastIndexOf("."));
				  //System.out.println(fileName); 
				  File file = new File(ConstantsUtil.IMAGE_LOCATION+"seller\\"+fileName); 
				  try {
					  FileOutputStream fos = new FileOutputStream(file);
					  fos.write(productImage.getBytes()); fos.close(); 
				  } catch (IOException e) {
					  e.printStackTrace(); 
				  }
	          }
		 
	          ObjectMapper objectMapper = new ObjectMapper();
	          ProductDetailsSeller productDetails = objectMapper.readValue(productDetailsData, ProductDetailsSeller.class);
	          
	          productDetails.setProductImage(fileName);
	          productDetailsService.saveProductDetailsSeller(productDetails);
			  log.info("Product Details saved successfully.");	
	   
		 } catch (Exception e) {  
			  e.printStackTrace();
			  log.error("Product Details failed to saved: "+ e.toString());
		 }		
	}
	
	@RequestMapping(value = "/getProductDetailsBySellerId/{sellerId}", method = RequestMethod.GET)
	public @ResponseBody List<ProductDetailsSeller> getProductDetailsBySellerId(@PathVariable("sellerId") int sellerId) throws Exception {

		List<ProductDetailsSeller> products = null;
		try {
			
			products = productDetailsService.getProductDetailsBySellerId(sellerId);
			log.info("Product Details");

		} catch (Exception e) {
			log.info("Exception while fetching Product data : " + e.toString());			
		}
		return products;
	}
		
	@RequestMapping(value = "/productAndCategoryList", method = RequestMethod.GET)
	public @ResponseBody  List<ProductCategory> productAndCategoryList() throws Exception {

		List<ProductCategory> productList = null;
		try {
			productList = productService.getProductAndCategoryList();
			log.info("Display all Product And Category List");

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception while fetching  Product And Category list : " + e.toString());

		}
		return productList;
	}
	
	@RequestMapping(value = "/getProductListByCategory/{categoryName}", method = RequestMethod.GET)
	public @ResponseBody  List<ProductCategory> getProductListByCategory(@PathVariable("categoryName") String categoryName) throws Exception {

		List<ProductCategory> productList = null;
		try {
			productList = productService.getProductListByCategory(categoryName);
			log.info("Display all Product List");

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception while fetching Product list : " + e.toString());
		}
		return productList;
	}
	
	@RequestMapping(value = "/getCategoryWiseProduct/{stateCode}/{districtCode}/{talukaCode}/{categoryName}", method = RequestMethod.GET) 
	public @ResponseBody List<CategoryWiseProduct> getCategoryWiseProducts(@PathVariable("stateCode") int stateCode,
			@PathVariable("districtCode") int districtCode,
			@PathVariable("talukaCode")   int talukaCode,
			@PathVariable("categoryName") String categoryName) throws Exception {
	  
		  List<CategoryWiseProduct> categoryWiseProduct = null;
		  try {
		     log.info("category Wise Product fetched"); 
		     categoryWiseProduct = productService.getCategoryWiseProductList(stateCode, districtCode, talukaCode, categoryName);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching category Wise Product: "+e.toString());
		  } 
		  return categoryWiseProduct;
	}
	
	@RequestMapping(value = "/getShopListByProduct/{stateCode}/{districtCode}/{talukaCode}/{productId}", method = RequestMethod.GET) 
	public @ResponseBody List<ShopListByProduct> getCategoryWiseProducts(@PathVariable("stateCode") int stateCode,
			@PathVariable("districtCode") int districtCode,
			@PathVariable("talukaCode")   int talukaCode,
			@PathVariable("productId") int productId) throws Exception {
	  
		  List<ShopListByProduct> shopListByProduct = null;
		  try {
		     log.info("shop List By Product fetched"); 
		     shopListByProduct = productService.getShopListByProduct(stateCode, districtCode, talukaCode, productId);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching shop List By Product: "+e.toString());
		  } 
		  return shopListByProduct;
	}
	
	@RequestMapping(value = "/getProductListByShop/{sellerId}", method = RequestMethod.GET) 
	public @ResponseBody List<ProductListByShop> getProductListByShop(@PathVariable("sellerId") int sellerId) throws Exception {
	  
		  List<ProductListByShop> productListByShop = null;
		  try {
		     log.info("product List By Shop fetched"); 
		     productListByShop = productService.getProductListByShop(sellerId);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching productListByShop: "+e.toString());
		  } 
		  return productListByShop;
	}
}