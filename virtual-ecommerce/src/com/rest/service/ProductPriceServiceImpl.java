package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.ProductPriceDao;
import com.rest.model.ProductPrice;
import com.rest.utility.DateTimeUtil;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

	@Autowired
	ProductPriceDao productPriceDao;
	
	@Autowired
	ProductService productService;
	
	@Override
	public void saveOrUpdateProductPrice(ProductPrice productPrice) throws Exception {
		// TODO Auto-generated method stub
		int productId = productPrice.getProductId();
		int oldProductPriceId = productPriceDao.getActiveProductPriceIdByProductId(productId);
		if(oldProductPriceId != 0) {
			productPriceDao.changeProductPriceStatus(oldProductPriceId);
		}
		 
	    productPrice.setCreatedDateTime(DateTimeUtil.getSysDateTime());
		productPrice.setIsActive(1);
		productPriceDao.saveOrUpdateProductPrice(productPrice);
	}

	@Override
	public ProductPrice getProductPriceById(int productPriceId) throws Exception {
		// TODO Auto-generated method stub
		return productPriceDao.getProductPriceById(productPriceId);
	}

	@Override
	public ProductPrice getProductPriceByProductId(int productId) throws Exception {
		// TODO Auto-generated method stub
		return productPriceDao.getProductPriceByProductId(productId);
	}

	@Override
	public List<ProductPrice> getProductPriceActiveList() throws Exception {
		// TODO Auto-generated method stub
		List<ProductPrice> productPriceList = productPriceDao.getProductPriceActiveList();
		List<ProductPrice> productPriceList1 = new ArrayList<ProductPrice>();
		for (ProductPrice productPrice : productPriceList) {
			int    prodId   = productPrice.getProductId();
			String prodName = productService.getProductById(prodId).getTitle();
			productPrice.setProductName(prodName);
			productPriceList1.add(productPrice);
		}
		return productPriceList1;
	}

	@Override
	public List<ProductPrice> getProductPriceAllList() throws Exception {
		// TODO Auto-generated method stub
		return productPriceDao.getProductPriceAllList();
	}
}