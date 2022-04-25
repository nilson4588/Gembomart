package com.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.BalanceSheet;
import com.rest.model.CategoryTotal;
import com.rest.model.CommisionCalculation;
import com.rest.model.DashboardTotal;
import com.rest.model.DashboardTotalProductwise;
import com.rest.model.Order;
import com.rest.model.Referral;
import com.rest.model.ReferralCommision;
import com.rest.model.SellerOrder;
import com.rest.model.SupplierOrder;

@Component
public interface DashboardService {
	
	public List<DashboardTotal> getSellerDashboardTotal(String date) throws Exception;
	
	public List<DashboardTotal> getSupplierDashboardTotal(String date) throws Exception;
	
	public List<DashboardTotalProductwise> getSellerDashboardTotalProductwise(String date, int productId) throws Exception;
	
	public List<DashboardTotalProductwise> getSupplierDashboardTotalProductwise(String date, int productId) throws Exception;
	
	public List<DashboardTotal> getCustomerDashboardTotal(String date) throws Exception;
	
	public List<BalanceSheet> getBalanceSheet(String date) throws Exception;
	
	public List<BalanceSheet> getBalanceSheetBetweenDates(String fromDate, String toDate) throws Exception; 
	
	public List<CategoryTotal> getSellerProductCategorywiseTotal(String tdate) throws Exception;
	
	public List<CategoryTotal> getSupplierProductCategorywiseTotal(String tdate) throws Exception;
	
	public List<CategoryTotal> getCustomerProductCategorywiseTotal(String tdate) throws Exception;
	
	
	
	public List<SellerOrder> getNewSellerOrderList(String fromDate, String toDate) throws Exception;
	
	public List<SellerOrder> getShippedSellerOrderList(String fromDate, String toDate) throws Exception; 
	
	public List<SellerOrder> getDeliveredSellerOrderList(String fromDate, String toDate) throws Exception;
	
	public List<SellerOrder> getCanceledSellerOrderList(String fromDate, String toDate) throws Exception;
	
	
	
	public List<SupplierOrder> getNewSupplierOrderList(String fromDate, String toDate) throws Exception;
	
	public List<SupplierOrder> getShippedSupplierOrderList(String fromDate, String toDate) throws Exception;
	
	public List<SupplierOrder> getDeliveredSupplierOrderList(String fromDate, String toDate) throws Exception;
	
	public List<SupplierOrder> getCanceledSupplierOrderList(String fromDate, String toDate) throws Exception;
	
	
	
	public List<Order> getNewCustomerOrderList(String fromDate, String toDate) throws Exception;
	
	public List<Order> getDeliveredCustomerOrderList(String fromDate, String toDate) throws Exception;
	
	public List<Order> getShippedCustomerOrderList(String fromDate, String toDate) throws Exception;
	
	public List<Order> getCanceledCustomerOrderList(String fromDate, String toDate) throws Exception;
	
	public List<Referral> getMyReferral(Long contactNo, String role) throws Exception;
	
	
	
   public List<CommisionCalculation> getNationalCommisionBetweenDates(String fromDate, String toDate, int nationalFranchiseId) throws Exception;
	
	public List<CommisionCalculation> getStateCommisionBetweenDates(String fromDate, String toDate, int stateCode, int stateFranchiseId) throws Exception;
	
	public List<CommisionCalculation> getDistrictCommisionBetweenDates(String fromDate, String toDate, int districtCode, int districtFranchiseId) throws Exception;
	
	public List<CommisionCalculation> getTalukaCommisionBetweenDates(String fromDate, String toDate, int talukaCode, int talukaFranchiseId) throws Exception;
		 
	public List<ReferralCommision> getReffaralCommisionBetweenDates(String fromDate, String toDate, String mobileNo) throws Exception;
}
