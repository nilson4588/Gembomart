package com.rest.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.Seller;

@Component
public interface SellerDao {

	 public int saveOrUpdateSeller(Seller seller) throws Exception; 
	 
	 public Seller getSellerById(int sellerId) throws Exception;  
	 
	 public List<Seller> getSellerList() throws Exception;
	 
	 public Seller getSellerByMobileNo(long mobileNo) throws Exception;
	 
	 public int activateOrDeactivateSeller(Seller seller) throws Exception;
	 
	 public List<Seller> sellerListByFranchiseLocal(int localFranchiseId) throws Exception;
	 
	 public List<Seller> sellerListByFranchiseTaluka(int talukaFranchiseId) throws Exception;
	 
	 public List<Seller> sellerListByFranchiseDistrict(int districtFranchiseId) throws Exception;
	 
	 public Seller getSellerByMobileAndPassword(long mobileNumber, String password) throws Exception;
	 
	 public String getShopNameBySellerId(int sellerId) throws Exception;
	 
	 public int updateSellerToken(Seller seller) throws Exception;
	 
	 public String getTokenBySellerId(int sellerId) throws Exception;
	 
	 public int getSellerCurrentStatus(int sellerId) throws Exception;
	 
	 public List<Seller> getSellerListByArea(int talukaCode) throws Exception;
	 
	 public int getTalukaCodeBySellerId(int sellerId) throws Exception;
}
