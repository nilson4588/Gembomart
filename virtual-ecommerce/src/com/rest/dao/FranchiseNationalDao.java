package com.rest.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.FranchiseNational;
import com.rest.model.States;

@Component
public interface FranchiseNationalDao {

	  public int saveOrUpdateFranchiseNational(FranchiseNational franchiseNational) throws Exception;
	  
	  public FranchiseNational getFranchiseNationalById(int nationalFranchiseId) throws Exception; 
	  
	  public List<FranchiseNational> getFranchiseNationalList() throws Exception;
	  
	  public int activateOrDeactivateFranchiseNational(FranchiseNational franchiseNational) throws Exception;
	  	 	  
		/*
		 * public List<SupplierOrder> getSupplierOrderByFranchiseNational(int
		 * nationalFranchiseId, String orderDate) throws Exception;
		 * 
		 * public List<SellerOrder> getSellerOrderByFranchiseNational(int
		 * nationalFranchiseId, String orderDate) throws Exception;
		 * 
		 * public List<SellerOrder> getDeliveryOrderByFranchiseNational(int
		 * nationalFranchiseId, String orderDate) throws Exception;
		 */
	  
	  public FranchiseNational getFranchiseNationalByMobileAndPassword(long mobileNumber, String password) throws Exception;
	  
	  public FranchiseNational getFranchiseNationalByMobileNo(long franchiseContactNo) throws Exception;
	  
	  public String getFranchiseNameById(int nationalFranchiseId) throws Exception;
	  
	  public List<States> statesListByCategoryId(int categoryId) throws Exception;
	  
	  public int getCategoryIdByNationalFranchiseId(int nationalFranchiseId) throws Exception;
}