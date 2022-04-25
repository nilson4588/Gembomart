package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.ProductDetailsDao;
import com.rest.model.ProductDetails;
import com.rest.model.ProductDetailsSeller;
import com.rest.utility.ConstantsUtil;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

	@Autowired
	ProductDetailsDao productDetailsDao;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	SupplierService supplierService;
	
	@Autowired
	SellerService sellerService;
	
	@Override
	public int saveProductDetails(ProductDetails productDetails) throws Exception {
		// TODO Auto-generated method stub
		return productDetailsDao.saveProductDetails(productDetails);
	}
	
	@Override
	public List<ProductDetails> getProductDetailsBySupplierId(int supplierId) throws Exception {
		
		List<ProductDetails>  productDetailsList = productDetailsDao.getProductDetailsBySupplierId(supplierId);
		List<ProductDetails>  productDetailsList1 = new ArrayList<ProductDetails>();
		for (ProductDetails productDetails : productDetailsList) {
			 			
			 int sId = productDetails.getSupplierId();
			 int pId = productDetails.getProductId();
			 String pImage = productDetails.getProductImage();
			 ProductDetails pd = productDetails;
			 
			 String upldateProductImage = ConstantsUtil.SERVER_IMG_LOCATION+"supplier/"+pImage;			 
			 String pName = productService.getProductNameById(pId);
			 String sName = supplierService.getSupplierById(sId).getSupplierFullName();
			 
			 pd.setProductImage(upldateProductImage);
			 pd.setProductName(pName);
			 pd.setSupplierName(sName);
			 
			 productDetailsList1.add(pd);
		}
		return productDetailsList1;
	}
	
	@Override
	public int saveProductDetailsSeller(ProductDetailsSeller productDetailsSeller) throws Exception {
		return productDetailsDao.saveProductDetailsSeller(productDetailsSeller);
	}
	 
	@Override
	public List<ProductDetailsSeller> getProductDetailsBySellerId(int sellerId) throws Exception {
		
		List<ProductDetailsSeller>  productDetailsList = productDetailsDao.getProductDetailsBySellerId(sellerId);
		List<ProductDetailsSeller>  productDetailsList1 = new ArrayList<ProductDetailsSeller>();
		for (ProductDetailsSeller productDetails : productDetailsList) {
			 			
			 int sId = productDetails.getSellerId();
			 int pId = productDetails.getProductId();
			 String pImage = productDetails.getProductImage();
			 ProductDetailsSeller pd = productDetails;
			 
			 String upldateProductImage = ConstantsUtil.SERVER_IMG_LOCATION+"seller/"+pImage;			 
			 String pName = productService.getProductNameById(pId);
			 String sName = sellerService.getSellerById(sId).getSellerFullName();
			 
			 pd.setProductImage(upldateProductImage);
			 pd.setProductName(pName);
			 pd.setSellerName(sName);
			 
			 productDetailsList1.add(pd);
		}
		return productDetailsList1;
	}
}