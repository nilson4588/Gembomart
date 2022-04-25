package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.FranchiseDistrictDao;
import com.rest.model.Districts;
import com.rest.model.FranchiseDistrict;
import com.rest.model.FranchiseTaluka;
import com.rest.model.FranchiseType;
import com.rest.model.SellerOrder;
import com.rest.model.SupplierOrder;
import com.rest.utility.DateTimeUtil;

@Service 
public class FranchiseDistrictServiceImpl implements FranchiseDistrictService {

	@Autowired
	FranchiseDistrictDao franchiseDao;
	
	@Autowired
	FranchiseNationalService franchiseNationalService; 
	
	@Autowired
	CityService cityService;
	
	@Override
	public int saveOrUpdateFranchiseDistrict(FranchiseDistrict franchiseDistrict) throws Exception {
		// TODO Auto-generated method stub
		franchiseDistrict.setDistrictFranchiseRegistrationDatetime(DateTimeUtil.getSysDateTime());
		return franchiseDao.saveOrUpdateFranchiseDistrict(franchiseDistrict);
	}

	@Override
	public FranchiseDistrict getFranchiseDistrictById(int districtFranchiseId) throws Exception {
		// TODO Auto-generated method stub
		FranchiseDistrict franchiseDistrict = franchiseDao.getFranchiseDistrictById(districtFranchiseId);
		
		int nationalFranchiseId   = franchiseDistrict.getNationalFranchiseId();
		String nationalFranchName = franchiseNationalService.getFranchiseNameById(nationalFranchiseId);
		franchiseDistrict.setNationalFranchiseName(nationalFranchName);
				
		return franchiseDistrict;
	}
	
	@Override
	public List<FranchiseDistrict> getFranchiseDistrictListByNationalId(int nationalFranId) throws Exception {
		// TODO Auto-generated method stub
		List<FranchiseDistrict> franchiseList  = franchiseDao.getFranchiseDistrictListByNationalId(nationalFranId); 
		List<FranchiseDistrict> franchiseList1 = new ArrayList<FranchiseDistrict>();
		
		for (FranchiseDistrict franchiseDistrict : franchiseList) {
			int nationalFranchiseId   = franchiseDistrict.getNationalFranchiseId();
			String nationalFranchName = franchiseNationalService.getFranchiseNameById(nationalFranchiseId);
			franchiseDistrict.setNationalFranchiseName(nationalFranchName);
			String stateName = franchiseDao.getStateNameByCode(franchiseDistrict.getStateCode());
			franchiseDistrict.setStateName(stateName);
			franchiseList1.add(franchiseDistrict);
		}
		return franchiseList1;
	}

	@Override
	public List<FranchiseDistrict> getFranchiseDistrictList() throws Exception {
		// TODO Auto-generated method stub
		List<FranchiseDistrict> franchiseList  = franchiseDao.getFranchiseDistrictList();
		List<FranchiseDistrict> franchiseList1 = new ArrayList<FranchiseDistrict>();
		
		for (FranchiseDistrict franchiseDistrict : franchiseList) {
			int nationalFranchiseId   = franchiseDistrict.getNationalFranchiseId();
			//System.out.println("nationalFranchiseId : "+nationalFranchiseId);
			String nationalFranchName = franchiseNationalService.getFranchiseNameById(nationalFranchiseId);
			//System.out.println("nationalFranchName : "+nationalFranchName);
			franchiseDistrict.setNationalFranchiseName(nationalFranchName);
			String stateName = cityService.getStateName(franchiseDistrict.getStateCode());
			franchiseDistrict.setStateName(stateName);
			franchiseList1.add(franchiseDistrict);
		}
		return franchiseList1;
	}
	
	@Override
	public List<FranchiseType> getfranchiseTypeList() throws Exception {
		// TODO Auto-generated method stub
		return franchiseDao.getfranchiseTypeList();
	}
		
	@Override
	public int activateOrDeactivateFranchiseDistrict(FranchiseDistrict franchiseDistrict) throws Exception {
		// TODO Auto-generated method stub
		return franchiseDao.activateOrDeactivateFranchiseDistrict(franchiseDistrict);
	}

	@Override
	public List<FranchiseTaluka> getTalukaFranchiseListByDistrict(int districtFranchiseId) throws Exception {
		
		List<FranchiseTaluka> franchiseTaluka = franchiseDao.getTalukaFranchiseListByDistrict(districtFranchiseId);
		List<FranchiseTaluka> franchiseTaluka1 = new ArrayList<FranchiseTaluka>();
		
		for (FranchiseTaluka taluka : franchiseTaluka) {
			int districtCode = taluka.getDistrictCode();
			String districtName = franchiseDao.getDistrictNameByCode(districtCode);
			taluka.setDistrictName(districtName);
			franchiseTaluka1.add(taluka);
		}
		
		return franchiseTaluka1;
	}
	
	@Override
	public List<SupplierOrder> getSupplierOrderByFranchiseDistrict(int districtFranchiseId) throws Exception {
		String createdDateTime = DateTimeUtil.getSysDate();
		return franchiseDao.getSupplierOrderByFranchiseDistrict(districtFranchiseId, createdDateTime);
	}
	
	@Override
	public List<SellerOrder> getSellerOrderByFranchiseDistrict(int districtFranchiseId) throws Exception {
		String createdDateTime = DateTimeUtil.getSysDate();
		return franchiseDao.getSellerOrderByFranchiseDistrict(districtFranchiseId, createdDateTime);
	}
	
	@Override
	public List<SellerOrder> getDeliveryOrderByFranchiseDistrict(int districtFranchiseId) throws Exception {
		String createdDateTime = DateTimeUtil.getSysDate();
		return franchiseDao.getDeliveryOrderByFranchiseDistrict(districtFranchiseId, createdDateTime);
	}
	
	@Override
	public FranchiseDistrict getFranchiseDistrictByMobileAndPassword(long mobileNumber, String password) throws Exception {
		FranchiseDistrict franchiseDistrict = franchiseDao.getFranchiseDistrictByMobileAndPassword(mobileNumber, password);
		int categoryId = franchiseNationalService.getCategoryIdByNationalFranchiseId(franchiseDistrict.getNationalFranchiseId());
		franchiseDistrict.setCategoryId(categoryId);
		return franchiseDistrict;
	}
	
	@Override
	public FranchiseDistrict getFranchiseDistrictByMobileNo(long franchiseContactNo) throws Exception {
		return franchiseDao.getFranchiseDistrictByMobileNo(franchiseContactNo);
	}
	
	@Override
	public List<Districts> districtListByCategoryIdStateCode(int categoryId, int stateCode) throws Exception {
		return franchiseDao.districtListByCategoryIdStateCode(categoryId, stateCode);
	}
	
	@Override
	public int getCategoryIdByTalukaFranchiseId(int talukaFranchiseId) throws Exception {
		return franchiseDao.getCategoryIdByTalukaFranchiseId(talukaFranchiseId);
	}
	
	@Override
	public String getDistrictNameByCode(int districtCode) throws Exception {
		return franchiseDao.getDistrictNameByCode(districtCode);
	}
}