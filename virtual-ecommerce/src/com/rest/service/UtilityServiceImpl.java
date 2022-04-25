
package com.rest.service;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.UtilityDao;

@Service
public class UtilityServiceImpl implements UtilityService {

	@Autowired
	UtilityDao utilityDao;

	@SuppressWarnings("rawtypes")

	@Override
	public Object getRecentObject(Class objclass, String pid) {
		return utilityDao.getRecentObject(objclass, pid);
	}
	
	@SuppressWarnings("rawtypes")	  
	@Override 
	public int getRecentId(Class objclass, String pid) { 
		return	utilityDao.getRecentId(objclass, pid); 
	}
	  
	@SuppressWarnings("rawtypes")	  
	@Override public boolean isIdExists(Class objclass, String pid, int id) {
		return utilityDao.isIdExists(objclass, pid, id); 
	}
	 

	public int getAmountByFranchiseName(String franchiseName) {
		return utilityDao.getAmountByFranchiseName(franchiseName);
	}
	/*
	 * public FinanceYear getCurrentFinancialYear() { return
	 * utilityDao.getCurrentFinancialYear(); }
	 * 
	 * public List<FinanceYear> getAllFinancialYears() { return
	 * utilityDao.getAllFinancialYears(); }
	 */
}
