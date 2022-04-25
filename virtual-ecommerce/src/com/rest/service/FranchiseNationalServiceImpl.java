package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.FranchiseNationalDao;
import com.rest.model.FranchiseNational;
import com.rest.model.States;
import com.rest.utility.DateTimeUtil;

@Service
public class FranchiseNationalServiceImpl implements FranchiseNationalService {

	@Autowired
	FranchiseNationalDao franchiseNationalDao;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Override
	public int saveOrUpdateFranchiseNational(FranchiseNational franchiseNational) throws Exception {
		// TODO Auto-generated method stub
		franchiseNational.setNationalFranchiseRegistrationDatetime(DateTimeUtil.getSysDateTime());
		franchiseNational.setIsActive(1);
		return franchiseNationalDao.saveOrUpdateFranchiseNational(franchiseNational);
	}

	@Override
	public FranchiseNational getFranchiseNationalById(int nationalFranchiseId) throws Exception {
		// TODO Auto-generated method stub
		FranchiseNational fn = franchiseNationalDao.getFranchiseNationalById(nationalFranchiseId);
		FranchiseNational franchiseNational1 = new FranchiseNational();
		String productName = productService.getProductNameById(fn.getProductId());
		franchiseNational1.setProductName(productName);
        String categoryName = categoryService.getCategoryNameById(fn.getCategoryId());
        franchiseNational1.setCategoryName(categoryName);
       		
		return franchiseNational1;
	}

	@Override
	public List<FranchiseNational> getFranchiseNationalList() throws Exception {
		
		List<FranchiseNational> franchiseNationalList  =  franchiseNationalDao.getFranchiseNationalList();
		List<FranchiseNational> franchiseNationalList2 =  new ArrayList<FranchiseNational>(); 
		for (FranchiseNational fn : franchiseNationalList) {
			String productName = productService.getProductNameById(fn.getProductId());
	        fn.setProductName(productName);
	        String categoryName = categoryService.getCategoryNameById(fn.getCategoryId());
	        fn.setCategoryName(categoryName);
	        franchiseNationalList2.add(fn);
		}		
		return franchiseNationalList2;
	}

	@Override
	public int activateOrDeactivateFranchiseNational(FranchiseNational franchiseNational) throws Exception {
		// TODO Auto-generated method stub
		return franchiseNationalDao.activateOrDeactivateFranchiseNational(franchiseNational);
	}

	@Override
	public FranchiseNational getFranchiseNationalByMobileAndPassword(long mobileNumber, String password) throws Exception {
		// TODO Auto-generated method stub
		return franchiseNationalDao.getFranchiseNationalByMobileAndPassword(mobileNumber, password);
	}
	
	@Override
	public FranchiseNational getFranchiseNationalByMobileNo(long franchiseContactNo) throws Exception {
		// TODO Auto-generated method stub
		return franchiseNationalDao.getFranchiseNationalByMobileNo(franchiseContactNo);
	}
	
	@Override
	public String getFranchiseNameById(int nationalFranchiseId) throws Exception{
		return franchiseNationalDao.getFranchiseNameById(nationalFranchiseId);
	}
	
	@Override
	public List<States> statesListByCategoryId(int categoryId) throws Exception{
		return franchiseNationalDao.statesListByCategoryId(categoryId);
	}
	
	@Override
	public int getCategoryIdByNationalFranchiseId(int nationalFranchiseId) throws Exception{
		return franchiseNationalDao.getCategoryIdByNationalFranchiseId(nationalFranchiseId);
	}
}