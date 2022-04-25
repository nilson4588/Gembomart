package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.ProductDao;
import com.rest.dao.ProductSellerDao;
import com.rest.model.Product;
import com.rest.model.ProductSeller;

@Service
public class ProductSellerServiceImpl implements ProductSellerService {

	
	@Autowired
	ProductSellerDao productSellerDao;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductDao productDao;
	
	@Override
	public int saveOrUpdateProductSeller(ProductSeller productSeller) throws Exception {
		// TODO Auto-generated method stub
		
		double productPrice = productSeller.getProductPrice();
		double benefit = productSeller.getGembomartBenefit();
				
		double per15Price = (productPrice*benefit)/100;
		double gst18 = (per15Price*18)/100;
		double totalDeduction = per15Price+gst18;
		double purchasePrice = productPrice - totalDeduction;
		
		productSeller.setPer15price(per15Price);
		productSeller.setGst18(gst18);
		productSeller.setTotalDed(totalDeduction);
		productSeller.setPurchasePrice(purchasePrice);
		productSeller.setIsActive(1);
		
		return productSellerDao.saveOrUpdateProductSeller(productSeller);
	}

	@Override
	public ProductSeller getProductSellerById(int productSellerId) throws Exception {
		// TODO Auto-generated method stub
		ProductSeller productSeller = productSellerDao.getProductSellerById(productSellerId);
		int productId = productSeller.getProductId();
		
		Product product = productService.getProductById(productId);
		productSeller.setProductName(product.getTitle()); 
		productSeller.setCategoryName(product.getCategory());
		productSeller.setProductImage(productDao.getImagesList(product.getId()).get(0).getSrc());
		return productSeller;
	}

	@Override
	public List<ProductSeller> getProductSellerListBySellerId(int sellerId) throws Exception {
		// TODO Auto-generated method stub
		List<ProductSeller> productSellerList = productSellerDao.getProductSellerListBySellerId(sellerId);
		List<ProductSeller> productSellerList1 = new ArrayList<ProductSeller>();
		
		for (ProductSeller productSeller : productSellerList) {
			
			int productId = productSeller.getProductId();
			
			Product product = productService.getProductById(productId);
			productSeller.setProductName(product.getTitle()); 
			productSeller.setCategoryName(product.getCategory());
			productSeller.setProductImage(productDao.getImagesList(product.getId()).get(0).getSrc());
			productSellerList1.add(productSeller);
		}
		
		return productSellerList1;
	}
	
	public List<ProductSeller> getProductSellerListByDeactiveSellerId(int sellerId) throws Exception{
		// TODO Auto-generated method stub
		List<ProductSeller> productSellerList = productSellerDao.getProductSellerListByDeactiveSellerId(sellerId);
		List<ProductSeller> productSellerList1 = new ArrayList<ProductSeller>();
		
		for (ProductSeller productSeller : productSellerList) {
			
			int productId = productSeller.getProductId();
			
			Product product = productService.getProductById(productId);
			productSeller.setProductName(product.getTitle()); 
			productSeller.setCategoryName(product.getCategory());
			
			productSellerList1.add(productSeller);
		}
		
		return productSellerList1;
	}

	@Override
	public int activateOrDeactivateProductSeller(ProductSeller productSeller) throws Exception {
		// TODO Auto-generated method stub
		return productSellerDao.activateOrDeactivateProductSeller(productSeller);
	}
	
	@Override
	public ProductSeller getProductSellerByProductAndSeller(int sellerId, int productId) throws Exception {
		return productSellerDao.getProductSellerByProductAndSeller(sellerId, productId);
	}

}