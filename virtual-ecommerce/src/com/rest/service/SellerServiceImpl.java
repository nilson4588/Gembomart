package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.controller.ProductController;
import com.rest.dao.SellerDao;
import com.rest.model.ProductListByShop;
import com.rest.model.Seller;
import com.rest.utility.DateTimeUtil;

@Service 
public class SellerServiceImpl implements SellerService {

	@Autowired 
	SellerDao sellerDao;
	
	@Autowired
	FranchiseLocalService franchiseLocalService;
	
	@Autowired
	ProductService productService;
	
	//static final Logger log = Logger.getLogger(SellerServiceImpl.class);
	
	@Override
	public int saveOrUpdateSeller(Seller seller) throws Exception {
		// TODO Auto-generated method stub
		String sellerCode = "SLR"+seller.getSellerContactNo();
		seller.setSellerCode(sellerCode);
		seller.setSellerRegistrationDatetime(DateTimeUtil.getSysDateTime()); 
		seller.setIsActive(1);
		return sellerDao.saveOrUpdateSeller(seller);
	}

	@Override
	public Seller getSellerById(int sellerId) throws Exception {
		// TODO Auto-generated method stub
		Seller seller = sellerDao.getSellerById(sellerId);
	
		return seller;
	}

	@Override
	public List<Seller> getSellerList() throws Exception {
		// TODO Auto-generated method stub
		List<Seller> sellerList = sellerDao.getSellerList();
		List<Seller> sellerList1 = new ArrayList<Seller>();
		for (Seller seller : sellerList) {
			int localfranchiseId = seller.getLocalFranchiseId();
			//log.info("localfranchiseId : "+localfranchiseId);
			String localfranchiseName = franchiseLocalService.getFranchiseLocalById(localfranchiseId).getFullName();
			//log.info("localfranchiseName : "+localfranchiseName);
			seller.setLocalFranchiseName(localfranchiseName);
			//log.info("seller : "+seller);
			sellerList1.add(seller);
		}
		return sellerList1;
	}
	
	@Override
	public Seller getSellerByMobileNo(long mobileNo) throws Exception{
		return sellerDao.getSellerByMobileNo(mobileNo);
	}
	
	@Override
	public int activateOrDeactivateSeller(Seller seller) throws Exception {
		return sellerDao.activateOrDeactivateSeller(seller);
	}	
	
	@Override
	public List<Seller> sellerListByFranchiseLocal(int localFranchiseId) throws Exception {
		 return sellerDao.sellerListByFranchiseLocal(localFranchiseId);
	}
	
	@Override
	public List<Seller> sellerListByFranchiseTaluka(int talukaFranchiseId) throws Exception {
		 return sellerDao.sellerListByFranchiseTaluka(talukaFranchiseId);
	}
	 
	@Override
	public List<Seller> sellerListByFranchiseDistrict(int districtFranchiseId) throws Exception {
		 return sellerDao.sellerListByFranchiseDistrict(districtFranchiseId);
	}
	
	@Override
	public Seller getSellerByMobileAndPassword(long mobileNumber, String password) throws Exception {
		
		Seller seller = sellerDao.getSellerByMobileAndPassword(mobileNumber, password);
		int localFranchiseId = seller.getLocalFranchiseId();
		String categoryName = franchiseLocalService.getCategoryNameByFranchiseLocal(localFranchiseId);
		seller.setCategoryName(categoryName);
		return seller;
	}
	
	@Override
	public String getShopNameBySellerId(int sellerId) throws Exception{
		return sellerDao.getShopNameBySellerId(sellerId);
	}
	
	@Override
	public int updateSellerToken(Seller seller) throws Exception {
		return sellerDao.updateSellerToken(seller);
	}
	
	@Override
	public String getTokenBySellerId(int sellerId) throws Exception {
		return sellerDao.getTokenBySellerId(sellerId);
	}
	
	@Override
	public int getSellerCurrentStatus(int sellerId) throws Exception {
		return sellerDao.getSellerCurrentStatus(sellerId);
	}
	
	@Override
	public Seller getSellerDetailsById(int sellerId) throws Exception {
		
		Seller seller = sellerDao.getSellerById(sellerId);
	    List<ProductListByShop> ps = productService.getProductListByShop(sellerId);
	    seller.setProductListByShop(ps);
	    
	    return seller;
	}
	
	@Override
	public List<Seller> getSellerListByArea(int talukaCode) throws Exception {
		
		List<Seller> sellerList = sellerDao.getSellerListByArea(talukaCode);
		List<Seller> sellerList1 = new ArrayList<Seller>();
				
		for (Seller seller : sellerList) {
			
			seller.setShopImage("restaurant.png");
			sellerList1.add(seller);
		}
		
		return sellerList1;
	}
	
	@Override
	public int getTalukaCodeBySellerId(int sellerId) throws Exception {
		return sellerDao.getTalukaCodeBySellerId(sellerId);
	}
}