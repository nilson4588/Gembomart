package com.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.FranchiseLocal;
import com.rest.model.FranchiseTaluka;
import com.rest.model.SellerOrder;
import com.rest.model.SupplierOrder;
import com.rest.model.Taluka;

@Component
public interface FranchiseTalukaService {
	
	  public int saveOrUpdateFranchiseTaluka(FranchiseTaluka franchiseTaluka) throws Exception;
	  
	  public FranchiseTaluka getFranchiseTalukaById(int talukaFranchiseId) throws Exception; 
	  
	  public List<FranchiseTaluka> getFranchiseTalukaList() throws Exception;
	  
	  public int activateOrDeactivateFranchiseTaluka(FranchiseTaluka franchiseTaluka) throws Exception;
	  
	  public List<FranchiseLocal> getLocalFranchiseListByTaluka(int talukaFranchiseId) throws Exception;
	  
      public List<SupplierOrder> getSupplierOrderByFranchiseTaluka(int talukaFranchiseId) throws Exception;
	  
	  public List<SellerOrder> getSellerOrderByFranchiseTaluka(int talukaFranchiseId) throws Exception;
	  
	  public List<SellerOrder> getDeliveryOrderByFranchiseTaluka(int talukaFranchiseId) throws Exception;
	  
	  public FranchiseTaluka getFranchiseTalukaByMobileAndPassword(long mobileNumber, String password) throws Exception; 
	  
	  public FranchiseTaluka getFranchiseTalukaByMobileNo(long franchiseContactNo) throws Exception;
	  
	  public List<Taluka> talukaListByCategoryIdDistrictCode(int categoryId, int districtCode) throws Exception;
}