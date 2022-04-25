package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.FranchiseTalukaDao;
import com.rest.model.FranchiseLocal;
import com.rest.model.FranchiseTaluka;
import com.rest.model.SellerOrder;
import com.rest.model.SupplierOrder;
import com.rest.model.Taluka;
import com.rest.utility.DateTimeUtil;

@Service
public class FranchiseTalukaServiceImpl implements FranchiseTalukaService {

	@Autowired
	FranchiseTalukaDao franchiseTalukaDao;
	
	@Autowired
	FranchiseDistrictService franchiseDistrictService;
	
	@Autowired
	CityService cityService;
	
	@Override
	public int saveOrUpdateFranchiseTaluka(FranchiseTaluka franchiseTaluka) throws Exception {
		// TODO Auto-generated method stub
		franchiseTaluka.setTalukaFranchiseRegistrationDatetime(DateTimeUtil.getSysDateTime());
		return franchiseTalukaDao.saveOrUpdateFranchiseTaluka(franchiseTaluka);
	}

	@Override
	public FranchiseTaluka getFranchiseTalukaById(int talukaFranchiseId) throws Exception {
		// TODO Auto-generated method stub
		return franchiseTalukaDao.getFranchiseTalukaById(talukaFranchiseId);
	}

	@Override
	public List<FranchiseTaluka> getFranchiseTalukaList() throws Exception {
		// TODO Auto-generated method stub
		List<FranchiseTaluka> franchiseTalukaList = franchiseTalukaDao.getFranchiseTalukaList();
		List<FranchiseTaluka> franchiseTalukaList1 = new ArrayList<FranchiseTaluka>();
		for (FranchiseTaluka franchiseTaluka : franchiseTalukaList) {
			int districtFranchiseId = franchiseTaluka.getDistrictFranchiseId();
			String franchiseDistrictName = franchiseDistrictService.getFranchiseDistrictById(districtFranchiseId).getFullName();
			franchiseTaluka.setDistrictFranchiseName(franchiseDistrictName);
			String districtName = cityService.getDistrictName(franchiseTaluka.getDistrictCode());
			franchiseTaluka.setDistrictName(districtName);
			franchiseTalukaList1.add(franchiseTaluka);
		}
		return franchiseTalukaList1;
	}

	@Override
	public int activateOrDeactivateFranchiseTaluka(FranchiseTaluka franchiseTaluka) throws Exception {
		// TODO Auto-generated method stub
		return franchiseTalukaDao.activateOrDeactivateFranchiseTaluka(franchiseTaluka);
	}
	
	@Override
	public List<FranchiseLocal> getLocalFranchiseListByTaluka(int talukaFranchiseId) throws Exception {
		
		List<FranchiseLocal> franchiseLocalList = franchiseTalukaDao.getLocalFranchiseListByTaluka(talukaFranchiseId);
		List<FranchiseLocal> franchiseLocalList1 = new ArrayList<FranchiseLocal>();
		
		for (FranchiseLocal franchiseLocal : franchiseLocalList) {
			int talukaCode = franchiseLocal.getTalukaCode();
			String talukaName = franchiseTalukaDao.getTalukaNameByCode(talukaCode);
			franchiseLocal.setTalukaName(talukaName);
			franchiseLocalList1.add(franchiseLocal);
		}
		
		return franchiseLocalList1;
	}
	
	@Override
	public List<SupplierOrder> getSupplierOrderByFranchiseTaluka(int talukaFranchiseId) throws Exception {
		String createdDateTime = DateTimeUtil.getSysDate();
		return franchiseTalukaDao.getSupplierOrderByFranchiseTaluka(talukaFranchiseId, createdDateTime);
	}
	  
	@Override
	public List<SellerOrder> getSellerOrderByFranchiseTaluka(int talukaFranchiseId) throws Exception {
		String createdDateTime = DateTimeUtil.getSysDate();
		return franchiseTalukaDao.getSellerOrderByFranchiseTaluka(talukaFranchiseId, createdDateTime);
	}
	  
	@Override
	public List<SellerOrder> getDeliveryOrderByFranchiseTaluka(int talukaFranchiseId) throws Exception {
		String createdDateTime = DateTimeUtil.getSysDate();
		return franchiseTalukaDao.getDeliveryOrderByFranchiseTaluka(talukaFranchiseId, createdDateTime);
	}
	
	@Override
	public FranchiseTaluka getFranchiseTalukaByMobileAndPassword(long mobileNumber, String password) throws Exception {
		FranchiseTaluka franchiseTaluka  = franchiseTalukaDao.getFranchiseTalukaByMobileAndPassword(mobileNumber, password);
		int talukaFranchiseId = franchiseTaluka.getTalukaFranchiseId();
		int categoryId = franchiseDistrictService.getCategoryIdByTalukaFranchiseId(talukaFranchiseId);
		franchiseTaluka.setCategoryId(categoryId);
		return franchiseTaluka;
	}
	
	@Override
	public FranchiseTaluka getFranchiseTalukaByMobileNo(long franchiseContactNo) throws Exception {
		 return franchiseTalukaDao.getFranchiseTalukaByMobileNo(franchiseContactNo);
	}
	
	@Override
	public List<Taluka> talukaListByCategoryIdDistrictCode(int categoryId, int districtCode) throws Exception {
		 return franchiseTalukaDao.talukaListByCategoryIdDistrictCode(categoryId, districtCode);
	}
}