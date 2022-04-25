package com.rest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.SupplierDao;
import com.rest.model.Supplier;
import com.rest.utility.DateTimeUtil;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	SupplierDao supplierDao;
	
	@Autowired
	FranchiseLocalService franchiseLocalService;
		
	@Override
	public int saveOrUpdateSupplier(Supplier supplier) throws Exception {
		// TODO Auto-generated method stub
		String supplierProducts = Arrays.toString(supplier.getSupplierProductList());
		supplier.setSupplierProducts(supplierProducts);
		supplier.setSupplierRegistrationDatetime(DateTimeUtil.getSysDateTime());
		return supplierDao.saveOrUpdateSupplier(supplier);
	}

	@Override
	public Supplier getSupplierById(int supplierId) throws Exception {
		// TODO Auto-generated method stub
		return supplierDao.getSupplierById(supplierId);
	}

	@Override
	public List<Supplier> getSupplierList() throws Exception {
		// TODO Auto-generated method stub
		List<Supplier> supplierList = supplierDao.getSupplierList();
		List<Supplier> supplierList1 = new ArrayList<Supplier>();
		for (Supplier supplier : supplierList) {
			int localfranchiseId = supplier.getLocalFranchiseId();
			String localfranchiseName = franchiseLocalService.getFranchiseLocalById(localfranchiseId).getFullName();
			supplier.setLocalFranchiseName(localfranchiseName);
			supplierList1.add(supplier);
		}
		return supplierList1;
	}
	
	@Override
	public Supplier getSupplierByMobileNo(long mobileNo) throws Exception {
		// TODO Auto-generated method stub
		return supplierDao.getSupplierByMobileNo(mobileNo);
	}
	
	@Override
	public int activateOrDeactivateSupplier(Supplier supplier) throws Exception {
		return supplierDao.activateOrDeactivateSupplier(supplier);
	}
	
	@Override
	public List<Supplier> supplierListByFranchiseLocal(int localFranchiseId) throws Exception {
		return supplierDao.supplierListByFranchiseLocal(localFranchiseId);
	}
	
	@Override
	public List<Supplier> supplierListByFranchiseTaluka(int talukaFranchiseId) throws Exception {
		return supplierDao.supplierListByFranchiseTaluka(talukaFranchiseId);
	}
	 
	@Override
	public List<Supplier> supplierListByFranchiseDistrict(int districtFranchiseId) throws Exception {
		 return supplierDao.supplierListByFranchiseDistrict(districtFranchiseId);
	}
	
	@Override
	public Supplier getSupplierByMobileAndPassword(long mobileNumber, String password) throws Exception {
		
	    Supplier supplier =	supplierDao.getSupplierByMobileAndPassword(mobileNumber, password);
	    int localFranchiseId = supplier.getLocalFranchiseId();
		String categoryName = franchiseLocalService.getCategoryNameByFranchiseLocal(localFranchiseId);
		supplier.setCategoryName(categoryName);
		
		return supplier;
	}
}