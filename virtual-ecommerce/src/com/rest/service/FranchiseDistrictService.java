package com.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.Districts;
import com.rest.model.FranchiseDistrict;
import com.rest.model.FranchiseTaluka;
import com.rest.model.FranchiseType;
import com.rest.model.SellerOrder;
import com.rest.model.SupplierOrder;

@Component
public interface FranchiseDistrictService {

	  public int saveOrUpdateFranchiseDistrict(FranchiseDistrict franchiseDistrict) throws Exception;
	  
	  public FranchiseDistrict getFranchiseDistrictById(int districtFranchiseId) throws Exception; 
	  
	  public List<FranchiseDistrict> getFranchiseDistrictList() throws Exception;
	  
	  public int activateOrDeactivateFranchiseDistrict(FranchiseDistrict franchiseDistrict) throws Exception;
	  
	  public List<FranchiseType> getfranchiseTypeList() throws Exception;
	  
	  public List<FranchiseTaluka> getTalukaFranchiseListByDistrict(int districtFranchiseId) throws Exception; 
	  
	  public List<SupplierOrder> getSupplierOrderByFranchiseDistrict(int districtFranchiseId) throws Exception;
	  
	  public List<SellerOrder> getSellerOrderByFranchiseDistrict(int districtFranchiseId) throws Exception ;
	  
	  public List<SellerOrder> getDeliveryOrderByFranchiseDistrict(int districtFranchiseId) throws Exception;
	  
	  public FranchiseDistrict getFranchiseDistrictByMobileAndPassword(long mobileNumber, String password) throws Exception;
	  
	  public FranchiseDistrict getFranchiseDistrictByMobileNo(long franchiseContactNo) throws Exception;
	  
	  public List<FranchiseDistrict> getFranchiseDistrictListByNationalId(int nationalFranchiseId) throws Exception;
	  
	  public List<Districts> districtListByCategoryIdStateCode(int categoryId, int stateCode) throws Exception;
	  
	  public int getCategoryIdByTalukaFranchiseId(int talukaFranchiseId) throws Exception;
	  
	  public String getDistrictNameByCode(int districtCode) throws Exception;
}