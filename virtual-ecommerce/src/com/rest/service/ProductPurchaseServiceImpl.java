package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.ProductPurchaseDao;
import com.rest.model.ProductPurchase;

@Service
public class ProductPurchaseServiceImpl implements ProductPurchaseService {

	@Autowired
	ProductPurchaseDao productPurchaseDao;
	
	@Autowired
	ProductService productService;
	
	@Override
	public int saveOrUpdateProductPurchase(ProductPurchase productPurchase) throws Exception {
		// TODO Auto-generated method stub
		return productPurchaseDao.saveOrUpdateProductPurchase(productPurchase);
	}

	@Override
	public ProductPurchase getProductPurchaseById(int productPurchaseId) throws Exception {
		// TODO Auto-generated method stub
		ProductPurchase productPurchase = productPurchaseDao.getProductPurchaseById(productPurchaseId);
		int productId = productPurchase.getProductId();
		String productName = productService.getProductNameById(productId);
		productPurchase.setProductName(productName);
				
		return productPurchase;
	}

	@Override
	public List<ProductPurchase> getProductPurchaseList() throws Exception {
		// TODO Auto-generated method stub
		
		List<ProductPurchase> productPurchaseList = productPurchaseDao.getProductPurchaseList();
		List<ProductPurchase> productPurchaseList1 = new ArrayList<ProductPurchase>();
		
		for (ProductPurchase productPurchase : productPurchaseList) {
			
			int productId = productPurchase.getProductId();
			String productName = productService.getProductNameById(productId);
			productPurchase.setProductName(productName);
			productPurchaseList1.add(productPurchase);
		}
		return productPurchaseList1;
	}

	@Override
	public int activateOrDeactivateProductPurchase(ProductPurchase productPurchase) throws Exception {
		// TODO Auto-generated method stub
		return productPurchaseDao.activateOrDeactivateProductPurchase(productPurchase);
	}

}
