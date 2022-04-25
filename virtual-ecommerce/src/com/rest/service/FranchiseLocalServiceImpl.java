package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.FranchiseLocalDao;
import com.rest.model.FranchiseLocal;
import com.rest.model.SellerOrder;
import com.rest.model.SupplierOrder;
import com.rest.utility.DateTimeUtil;

@Service
public class FranchiseLocalServiceImpl implements FranchiseLocalService {

	@Autowired
	FranchiseLocalDao franchiseLocalDao;
	
	@Autowired
	FranchiseTalukaService franchiseTalukaService;
	
	@Autowired
	CityService cityService;
	
	@Override
	public int saveOrUpdateFranchiseLocal(FranchiseLocal franchiseLocal) throws Exception {
		// TODO Auto-generated method stub
		franchiseLocal.setLocalFranchiseRegistrationDatetime(DateTimeUtil.getSysDateTime());		
		return franchiseLocalDao.saveOrUpdateFranchiseLocal(franchiseLocal);
	}

	@Override
	public FranchiseLocal getFranchiseLocalById(int localFranchiseId) throws Exception {
		// TODO Auto-generated method stub
		return franchiseLocalDao.getFranchiseLocalById(localFranchiseId);
	}

	@Override
	public List<FranchiseLocal> getFranchiseLocalList() throws Exception {
		// TODO Auto-generated method stub
		List<FranchiseLocal> franchiseLocal = franchiseLocalDao.getFranchiseLocalList();
		List<FranchiseLocal> franchiseLocalList = new ArrayList<FranchiseLocal>(); 
		for (FranchiseLocal franchiseLocal2 : franchiseLocal) {
			int talukaFranchiseId = franchiseLocal2.getTalukaFranchiseId();
			String talukaFranchiseName = franchiseTalukaService.getFranchiseTalukaById(talukaFranchiseId).getFullName();
			franchiseLocal2.setTalukaFranchiseName(talukaFranchiseName);
			String talukaName = cityService.getTalukaName(franchiseLocal2.getTalukaCode());
			franchiseLocal2.setTalukaName(talukaName);
			franchiseLocalList.add(franchiseLocal2);
		}
		return franchiseLocalList;
	}

	@Override
	public int activateOrDeactivateFranchiseLocal(FranchiseLocal franchiseLocal) throws Exception {
		// TODO Auto-generated method stub
		return franchiseLocalDao.activateOrDeactivateFranchiseLocal(franchiseLocal);
	}
	
	@Override
	public List<SupplierOrder> getSupplierOrderByFranchiseLocal(int localFranchiseId) throws Exception  {
		// TODO Auto-generated method stub
		String createdDateTime = DateTimeUtil.getSysDate();
		return franchiseLocalDao.getSupplierOrderByFranchiseLocal(localFranchiseId, createdDateTime);
	}
	
	@Override
	public List<SellerOrder> getSellerOrderByFranchiseLocal(int localFranchiseId) throws Exception {
		String createdDateTime = DateTimeUtil.getSysDate();
		return franchiseLocalDao.getSellerOrderByFranchiseLocal(localFranchiseId, createdDateTime);
	}
	
	@Override
	public List<SellerOrder> getDeliveryOrderByFranchiseLocal(int localFranchiseId) throws Exception {
		String createdDateTime = DateTimeUtil.getSysDate();
		return franchiseLocalDao.getDeliveryOrderByFranchiseLocal(localFranchiseId, createdDateTime);
	}
	
	@Override
	public FranchiseLocal getFranchiseLocalByMobileAndPassword(long mobileNumber, String password) throws Exception{
		
		FranchiseLocal franchiseLocal = franchiseLocalDao.getFranchiseLocalByMobileAndPassword(mobileNumber, password);
		int localFranchiseId = franchiseLocal.getLocalFranchiseId();
		String categoryName = franchiseLocalDao.getCategoryNameByFranchiseLocal(localFranchiseId);
		franchiseLocal.setCategoryName(categoryName);
		return franchiseLocal;
	}	
	
	@Override
	public FranchiseLocal getFranchiseLocalByMobileNo(long franchiseContactNo) throws Exception {
		return franchiseLocalDao.getFranchiseLocalByMobileNo(franchiseContactNo);
	}
	
	@Override
	public String getCategoryNameByFranchiseLocal(int localFranchiseId) throws Exception {
		return franchiseLocalDao.getCategoryNameByFranchiseLocal(localFranchiseId);
	}
	
	@Override
	public List<FranchiseLocal> getFranchiseLocalListByTaluka(int talukaCode) throws Exception{
		return franchiseLocalDao.getFranchiseLocalListByTaluka(talukaCode);
	}
}