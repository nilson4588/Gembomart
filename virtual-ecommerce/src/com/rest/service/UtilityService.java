package com.rest.service;

import org.springframework.stereotype.Component;

@Component
public interface UtilityService {

	@SuppressWarnings("rawtypes")
	public Object getRecentObject(Class objclass, String pid);

	@SuppressWarnings("rawtypes")
	public int getRecentId(Class objclass, String pid);

	@SuppressWarnings("rawtypes")
	public boolean isIdExists(Class objclass, String pid, int id);

	
	public int getAmountByFranchiseName(String franchiseName);
	/*
	 * public FinanceYear getCurrentFinancialYear();
	 * 
	 * public List<FinanceYear> getAllFinancialYears();
	 */
}
