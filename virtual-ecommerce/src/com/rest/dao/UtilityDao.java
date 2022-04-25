
package com.rest.dao;

//import java.util.List;

import org.springframework.stereotype.Component;

//import com.rest.model.FinanceYear;

@Component
public interface UtilityDao {

	public Object getRecentObject(@SuppressWarnings("rawtypes") Class objclass, String pid);

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
