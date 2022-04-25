package com.rest.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.FranchiseLocal;
import com.rest.model.SellerOrder;
import com.rest.model.SupplierOrder;

@Component 
public interface FranchiseLocalDao {

	  public int saveOrUpdateFranchiseLocal(FranchiseLocal franchiseLocal) throws Exception;
	  
	  public FranchiseLocal getFranchiseLocalById(int localFranchiseId) throws Exception; 
	  
	  public List<FranchiseLocal> getFranchiseLocalList() throws Exception;
	  
	  public int activateOrDeactivateFranchiseLocal(FranchiseLocal franchiseLocal) throws Exception;	
	  
	  public List<SupplierOrder> getSupplierOrderByFranchiseLocal(int localFranchiseId, String orderDate) throws Exception; 
	  
	  public List<SellerOrder> getSellerOrderByFranchiseLocal(int localFranchiseId, String orderDate) throws Exception;
	  
	  public List<SellerOrder> getDeliveryOrderByFranchiseLocal(int localFranchiseId, String orderDate) throws Exception;
	  
	  public FranchiseLocal getFranchiseLocalByMobileAndPassword(long mobileNumber, String password) throws Exception;
	  
	  public FranchiseLocal getFranchiseLocalByMobileNo(long franchiseContactNo) throws Exception;
	  
	  public String getCategoryNameByFranchiseLocal(int localFranchiseId) throws Exception; 
	  
	  public List<FranchiseLocal> getFranchiseLocalListByTaluka(int talukaCode) throws Exception;
}