package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.DashboardDao;
import com.rest.model.BalanceSheet;
import com.rest.model.CategoryTotal;
import com.rest.model.CommisionCalculation;
import com.rest.model.DashboardTotal;
import com.rest.model.DashboardTotalProductwise;
import com.rest.model.Order;
import com.rest.model.Referral;
import com.rest.model.ReferralCommision;
import com.rest.model.SellerOrder;
import com.rest.model.SellerProductList;
import com.rest.model.SupplierOrder;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	DashboardDao dashbaordDao;
	
	@Override
	public List<DashboardTotal> getSellerDashboardTotal(String date) throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getSellerDashboardTotal(date);
	}

	@Override
	public List<DashboardTotal> getSupplierDashboardTotal(String date) throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getSupplierDashboardTotal(date);
	}

	@Override
	public List<DashboardTotalProductwise> getSellerDashboardTotalProductwise(String date, int productId)
			throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getSellerDashboardTOtalProductwise(date, productId);
	}

	@Override
	public List<DashboardTotalProductwise> getSupplierDashboardTotalProductwise(String date, int productId)
			throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getSupplierDashboardTOtalProductwise(date, productId);
	}
	
	@Override
	public List<DashboardTotal> getCustomerDashboardTotal(String date) throws Exception {
		return dashbaordDao.getCustomerDashboardTotal(date);
	}
	
	@Override
	public List<BalanceSheet> getBalanceSheet(String date) throws Exception {
		return dashbaordDao.getBalanceSheet(date);
	}
	
	public List<BalanceSheet> getBalanceSheetBetweenDates(String fromDate, String toDate) throws Exception  {
		return dashbaordDao.getBalanceSheetBetweenDates(fromDate, toDate);
	}
	
	@Override
	public List<CategoryTotal> getSellerProductCategorywiseTotal(String tdate) throws Exception {
		
		List<SellerProductList> categorywiseTotal = dashbaordDao.getSellerProductCategorywiseTotal(tdate);
		CategoryTotal ct = new CategoryTotal();
		ct.setFruits(0);
		ct.setGrocery(0);
		ct.setVegetables(0);
		
		for (SellerProductList categoryTotal : categorywiseTotal) {
			
			String categoryName  = categoryTotal.getProductName();
			double categotyTotal = Double.parseDouble(categoryTotal.getProductId());
			
			if(categoryName.equals("fruits")) {
			       ct.setFruits(categotyTotal);
			} else if(categoryName.equals("grocery")) {
				   ct.setGrocery(categotyTotal);
			} else if(categoryName.equals("vegetables")) {
				   ct.setVegetables(categotyTotal);
			}
		}
		List<CategoryTotal> ctList = new ArrayList<CategoryTotal>();
		ctList.add(ct);
		return ctList;
	}
	
	@Override
	public List<CategoryTotal> getSupplierProductCategorywiseTotal(String tdate) throws Exception{
		
		List<SellerProductList> categorywiseTotal = dashbaordDao.getSupplierProductCategorywiseTotal(tdate);
		CategoryTotal ct = new CategoryTotal();
		ct.setFruits(0);
		ct.setGrocery(0);
		ct.setVegetables(0);
		
		for (SellerProductList categoryTotal : categorywiseTotal) {
			
			String categoryName  = categoryTotal.getProductName();
			double categotyTotal = Double.parseDouble(categoryTotal.getProductId());
			
			if(categoryName.equals("fruits")) {
			       ct.setFruits(categotyTotal);
			} else if(categoryName.equals("grocery")) {
				   ct.setGrocery(categotyTotal);
			} else if(categoryName.equals("vegetables")) {
				   ct.setVegetables(categotyTotal);
			}
		}
		
		List<CategoryTotal> ctList = new ArrayList<CategoryTotal>();
		ctList.add(ct);
		return ctList;
	}
	
	@Override
	public List<CategoryTotal> getCustomerProductCategorywiseTotal(String tdate) throws Exception{
		
		List<SellerProductList> categorywiseTotal = dashbaordDao.getCustomerProductCategorywiseTotal(tdate);
		CategoryTotal ct = new CategoryTotal();
		ct.setFruits(0);
		ct.setGrocery(0);
		ct.setVegetables(0);
		 
		for (SellerProductList categoryTotal : categorywiseTotal) {
			
			String categoryName  = categoryTotal.getProductName();
			double categotyTotal = Double.parseDouble(categoryTotal.getProductId());
			
			if(categoryName.equals("fruits")) {
			       ct.setFruits(categotyTotal);
			} else if(categoryName.equals("grocery")) {
				   ct.setGrocery(categotyTotal);
			} else if(categoryName.equals("vegetables")) {
				   ct.setVegetables(categotyTotal);
			}
		}
		List<CategoryTotal> ctList = new ArrayList<CategoryTotal>();
		ctList.add(ct);
		return ctList;
	}

	
	
	
	
	@Override
	public List<SellerOrder> getNewSellerOrderList(String fromDate, String toDate) throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getNewSellerOrderList(fromDate, toDate);
	}
	
	@Override
	public List<SellerOrder> getShippedSellerOrderList(String fromDate, String toDate) throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getShippedSellerOrderList(fromDate, toDate);
	}

	@Override
	public List<SellerOrder> getDeliveredSellerOrderList(String fromDate, String toDate) throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getDeliveredSellerOrderList(fromDate, toDate);
	}	
	
	@Override
	public List<SellerOrder> getCanceledSellerOrderList(String fromDate, String toDate) throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getCanceledSellerOrderList(fromDate, toDate);
	}

	
		
	
	
	@Override
	public List<SupplierOrder> getNewSupplierOrderList(String fromDate, String toDate) throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getNewSupplierOrderList(fromDate, toDate);
	}

	@Override
	public List<SupplierOrder> getDeliveredSupplierOrderList(String fromDate, String toDate) throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getDeliveredSupplierOrderList(fromDate, toDate);
	}

	@Override
	public List<SupplierOrder> getShippedSupplierOrderList(String fromDate, String toDate) throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getShippedSupplierOrderList(fromDate, toDate);
	}
	
	@Override
	public List<SupplierOrder> getCanceledSupplierOrderList(String fromDate, String toDate) throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getCanceledSupplierOrderList(fromDate, toDate);
	}
	
	
		
	

	@Override
	public List<Order> getNewCustomerOrderList(String fromDate, String toDate) throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getNewCustomerOrderList(fromDate, toDate);
	}

	@Override
	public List<Order> getDeliveredCustomerOrderList(String fromDate, String toDate) throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getDeliveredCustomerOrderList(fromDate, toDate);
	}
	
	@Override
	public List<Order> getShippedCustomerOrderList(String fromDate, String toDate) throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getShippedCustomerOrderList(fromDate, toDate);
	}

	@Override
	public List<Order> getCanceledCustomerOrderList(String fromDate, String toDate) throws Exception {
		// TODO Auto-generated method stub
		return dashbaordDao.getCanceledCustomerOrderList(fromDate, toDate);
	}
	
	@Override
	public List<Referral> getMyReferral(Long contactNo, String role) throws Exception {
		return dashbaordDao.getMyReferral(contactNo, role);
	}
	
	@Override
	public List<CommisionCalculation> getNationalCommisionBetweenDates(String fromDate, String toDate, int nationalFranchiseId) throws Exception {
		return dashbaordDao.getNationalCommisionBetweenDates(fromDate, toDate, nationalFranchiseId);
	}
		
	@Override
	public List<CommisionCalculation> getStateCommisionBetweenDates(String fromDate, String toDate, int stateCode, int stateFranchiseId) throws Exception {
		return dashbaordDao.getStateCommisionBetweenDates(fromDate, toDate, stateCode, stateFranchiseId);
	}
	
	@Override
	public List<CommisionCalculation> getDistrictCommisionBetweenDates(String fromDate, String toDate, int districtCode, int districtFranchiseId) throws Exception{
		return dashbaordDao.getDistrictCommisionBetweenDates(fromDate, toDate, districtCode, districtFranchiseId);
	}
	
	@Override
	public List<CommisionCalculation> getTalukaCommisionBetweenDates(String fromDate, String toDate, int talukaCode, int talukaFranchiseId) throws Exception{
		return dashbaordDao.getTalukaCommisionBetweenDates(fromDate, toDate, talukaCode, talukaFranchiseId);
	}
	
	@Override
	public List<ReferralCommision> getReffaralCommisionBetweenDates(String fromDate, String toDate, String mobileNo) throws Exception {
		return dashbaordDao.getReffaralCommisionBetweenDates(fromDate, toDate, mobileNo);
	}
}