package com.rest.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
import com.rest.service.DashboardService;
import com.rest.service.UtilityService;

@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class DashboardController {

	@Autowired
	DashboardService dashboardService;
	
	@Autowired
	UtilityService utilityService;
	
	static final Logger log = Logger.getLogger(DashboardController.class);
	
	@RequestMapping(value = "/getSellerDashboardTotal/{date}", method = RequestMethod.GET) 
	public @ResponseBody List<DashboardTotal> getSellerDashboardTotal(@PathVariable("date") String date ) throws Exception {
	  
		  List<DashboardTotal> dashboardTotalList = null;
		  try {
		     log.info("Seller Dashboard Total fetched"); 
		     dashboardTotalList = dashboardService.getSellerDashboardTotal(date);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Seller Dashboard Total : "+e.toString());
		  } 
		  return dashboardTotalList;
	}
	
	@RequestMapping(value = "/getSupplierDashboardTotal/{date}", method = RequestMethod.GET) 
	public @ResponseBody List<DashboardTotal> getSupplierDashboardTotal(@PathVariable("date") String date ) throws Exception {
	  
		  List<DashboardTotal> dashboardTotalList = null;
		  try {
		     log.info("Supplier Dashboard Total fetched"); 
		     dashboardTotalList = dashboardService.getSupplierDashboardTotal(date);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Supplier Dashboard Total : "+e.toString());
		  } 
		  return dashboardTotalList;
	}
	
	@RequestMapping(value = "/getSupplierDashboardTotalProductwise/{date}/{productId}", method = RequestMethod.GET) 
	public @ResponseBody List<DashboardTotalProductwise> getSupplierDashboardTotal(@PathVariable("date") String date, @PathVariable("productId") int productId) throws Exception {
	  
		  List<DashboardTotalProductwise> dashboardTotalProductList = null;
		  try {
		     log.info("Supplier Dashboard Productwise Total fetched"); 
		     dashboardTotalProductList = dashboardService.getSupplierDashboardTotalProductwise(date, productId);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Supplier Dashboard Productwise Total : "+e.toString());
		  } 
		  return dashboardTotalProductList;
	}
	
	@RequestMapping(value = "/getSellerDashboardTotalProductwise/{date}/{productId}", method = RequestMethod.GET) 
	public @ResponseBody List<DashboardTotalProductwise> getSellerDashboardTotalProductwise(@PathVariable("date") String date, @PathVariable("productId") int productId) throws Exception {
	  
		  List<DashboardTotalProductwise> dashboardTotalProductList = null;
		  try {
		     log.info("Supplier Dashboard Productwise Total fetched"); 
		     dashboardTotalProductList = dashboardService.getSellerDashboardTotalProductwise(date, productId);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Supplier Dashboard Productwise Total : "+e.toString());
		  } 
		  return dashboardTotalProductList;
	}
	
	@RequestMapping(value = "/getCustomerDashboardTotal/{date}", method = RequestMethod.GET) 
	public @ResponseBody List<DashboardTotal> getCustomerDashboardTotal(@PathVariable("date") String date ) throws Exception {
	  
		  List<DashboardTotal> dashboardTotalList = null;
		  try {
		     log.info("Customer Dashboard Total fetched"); 
		     dashboardTotalList = dashboardService.getCustomerDashboardTotal(date);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Customer Dashboard Total : "+e.toString());
		  } 
		  return dashboardTotalList;
	}
	
	@RequestMapping(value = "/getBalanceSheet/{date}", method = RequestMethod.GET) 
	public @ResponseBody List<BalanceSheet> getBalanceSheet(@PathVariable("date") String date ) throws Exception {
	  
		  List<BalanceSheet> balanceSheet = null;
		  try {
		     log.info("Balance Sheet fetched"); 
		     balanceSheet = dashboardService.getBalanceSheet(date);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Balance Sheet: "+e.toString());
		  } 
		  return balanceSheet;
	}
	
	@RequestMapping(value = "/getBalanceSheetBetweenDates/{fromDate}/{toDate}", method = RequestMethod.GET) 
	public @ResponseBody List<BalanceSheet> getBalanceSheet(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate ) throws Exception {
	  
		  List<BalanceSheet> balanceSheet = null;
		  try {
		     log.info("Balance Sheet fetched"); 
		     balanceSheet = dashboardService.getBalanceSheetBetweenDates(fromDate, toDate);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Balance Sheet: "+e.toString());
		  } 
		  return balanceSheet;
	}
	
	@RequestMapping(value = "/sellerCategorywiseTotal/{date}", method = RequestMethod.GET)
	public @ResponseBody List<CategoryTotal> sellerCategoryTotal(@PathVariable("date") String date) throws Exception {
	  
		  List<CategoryTotal> categoryTotal = null;
		  try { 
			    categoryTotal = dashboardService.getSellerProductCategorywiseTotal(date);
			  	log.info("Display seller categary total");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching Supplier categary total : "+e.toString()); 
		  } 
		  return categoryTotal;
	}
	
	@RequestMapping(value = "/supplierCategorywiseTotal/{date}", method = RequestMethod.GET)
	public @ResponseBody List<CategoryTotal> supplierCategorywiseTotal(@PathVariable("date") String date) throws Exception {
	  
		  List<CategoryTotal> categoryTotal = null;
		  try { 
			    categoryTotal = dashboardService.getSupplierProductCategorywiseTotal(date);
			  	log.info("Display seller categary total");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching Supplier categary total : "+e.toString()); 
		  } 
		  return categoryTotal;
	}
	
	@RequestMapping(value = "/customerCategorywiseTotal/{date}", method = RequestMethod.GET)
	  public @ResponseBody List<CategoryTotal> customerCategorywiseTotal(@PathVariable("date") String date) throws Exception {
	  
		  List<CategoryTotal> categoryTotal = null;
		  try { 
			    categoryTotal = dashboardService.getCustomerProductCategorywiseTotal(date);
			  	log.info("Display customer categary total");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching customer categary total : "+e.toString()); 
		  } 
		  return categoryTotal;
	}
	
	/********************************** SELLER ********************************/
	
	@RequestMapping(value = "/getNewSellerOrderList/{fromDate}/{toDate}", method = RequestMethod.GET) 
	public @ResponseBody List<SellerOrder> getNewSellerOrderList(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) throws Exception {
	  
		  List<SellerOrder> sellerOrderList = null;
		  try {
		     log.info("New Seller Order list datewise fetched"); 
		     sellerOrderList = dashboardService.getNewSellerOrderList(fromDate, toDate);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching New Seller Order list datewise  : "+e.toString());
		  } 
		  return sellerOrderList;
	}
	
	@RequestMapping(value = "/getShippedSellerOrderList/{fromDate}/{toDate}", method = RequestMethod.GET) 
	public @ResponseBody List<SellerOrder> getShippedSellerOrderList(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) throws Exception {
	  
		  List<SellerOrder> sellerOrderList = null;
		  try {
		     log.info("Shipped Seller Order list datewise fetched"); 
		     sellerOrderList = dashboardService.getShippedSellerOrderList(fromDate, toDate);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Shipped Seller Order list datewise  : "+e.toString());
		  } 
		  return sellerOrderList;
	}
	
	@RequestMapping(value = "/getCanceledSellerOrderList/{fromDate}/{toDate}", method = RequestMethod.GET) 
	public @ResponseBody List<SellerOrder> getCanceledSellerOrderList(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) throws Exception {
	  
		  List<SellerOrder> sellerOrderList = null;
		  try {
		     log.info("Canceled Seller Order list datewise fetched"); 
		     sellerOrderList = dashboardService.getCanceledSellerOrderList(fromDate, toDate);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Canceled Seller Order list datewise  : "+e.toString());
		  } 
		  return sellerOrderList;
	}
	
	@RequestMapping(value = "/getDeliveredSellerOrderList/{fromDate}/{toDate}", method = RequestMethod.GET) 
	public @ResponseBody List<SellerOrder> getDeliveredSellerOrderList(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) throws Exception {
	  
		  List<SellerOrder> sellerOrderList = null;
		  try {
		     log.info("Delivered Seller Order list datewise fetched"); 
		     sellerOrderList = dashboardService.getDeliveredSellerOrderList(fromDate, toDate);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Delivered Seller Order list datewise  : "+e.toString());
		  } 
		  return sellerOrderList;
	}
	
	
	/********************************** SUPPLIER ********************************/
	
	@RequestMapping(value = "/getNewSupplierOrderList/{fromDate}/{toDate}", method = RequestMethod.GET) 
	public @ResponseBody List<SupplierOrder> getNewSupplierOrderList(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) throws Exception {
	  
		  List<SupplierOrder> supplierOrderList = null;
		  try {
		     log.info("New supplier Order list datewise fetched"); 
		     supplierOrderList = dashboardService.getNewSupplierOrderList(fromDate, toDate);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching New supplier Order list datewise  : "+e.toString());
		  } 
		  return supplierOrderList;
	}
	
	@RequestMapping(value = "/getShippedSupplierOrderList/{fromDate}/{toDate}", method = RequestMethod.GET) 
	public @ResponseBody List<SupplierOrder> getShippedSupplierOrderList(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) throws Exception {
	  
		  List<SupplierOrder> supplierOrderList = null;
		  try {
		     log.info("Shipped supplier Order list datewise fetched"); 
		     supplierOrderList = dashboardService.getShippedSupplierOrderList(fromDate, toDate);
	  
		  } catch(Exception e) {
	          
			  log.info("Exception while fetching Shipped Supplier Order list datewise  : "+e.toString());
		  } 
		  return supplierOrderList;
	}
	
	@RequestMapping(value = "/getDeliveredSupplierOrderList/{fromDate}/{toDate}", method = RequestMethod.GET) 
	public @ResponseBody List<SupplierOrder> getDeliveredSupplierOrderList(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) throws Exception {
	  
		  List<SupplierOrder> supplierOrderList = null;
		  try {
		     log.info("Shipped supplier Order list datewise fetched"); 
		     supplierOrderList = dashboardService.getDeliveredSupplierOrderList(fromDate, toDate);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Shipped supplier Order list datewise  : "+e.toString());
		  } 
		  return supplierOrderList;
	}
	
	@RequestMapping(value = "/getCanceledSupplierOrderList/{fromDate}/{toDate}", method = RequestMethod.GET) 
	public @ResponseBody List<SupplierOrder> getCanceledSupplierOrderList(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) throws Exception {
	  
		  List<SupplierOrder> supplierOrderList = null;
		  try {
		     log.info("Canceled supplier Order list datewise fetched"); 
		     supplierOrderList = dashboardService.getCanceledSupplierOrderList(fromDate, toDate);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Canceled supplier Order list datewise  : "+e.toString());
		  } 
		  return supplierOrderList;
	}
	
	/********************************** CUSTOMER ********************************/
	
	@RequestMapping(value = "/getNewCustomerOrderList/{fromDate}/{toDate}", method = RequestMethod.GET) 
	public @ResponseBody List<Order> getNewCustomerOrderList(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) throws Exception {
	  
		  List<Order> customerOrderList = null;
		  try {
		     log.info("New Customer Order list datewise fetched"); 
		     customerOrderList = dashboardService.getNewCustomerOrderList(fromDate, toDate);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching New Customer Order list datewise  : "+e.toString());
		  } 
		  return customerOrderList;
	}
	
	@RequestMapping(value = "/getDeliveredCustomerOrderList/{fromDate}/{toDate}", method = RequestMethod.GET) 
	public @ResponseBody List<Order> getDeliveredCustomerOrderList(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) throws Exception {
	  
		  List<Order> customerOrderList = null;
		  try {
		     log.info("Shipped customer Order list datewise fetched"); 
		     customerOrderList = dashboardService.getDeliveredCustomerOrderList(fromDate, toDate);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Shipped customer Order list datewise  : "+e.toString());
		  } 
		  return customerOrderList;
	}
	
	@RequestMapping(value = "/getShippedCustomerOrderList/{fromDate}/{toDate}", method = RequestMethod.GET) 
	public @ResponseBody List<Order> getShippedCustomerOrderList(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) throws Exception {
	  
		  List<Order> customerOrderList = null;
		  try {
		     log.info("Shipped customer Order list datewise fetched"); 
		     customerOrderList = dashboardService.getShippedCustomerOrderList(fromDate, toDate);
	  
		  } catch(Exception e) {
	          
			  log.info("Exception while fetching Shipped customer Order list datewise  : "+e.toString());
		  } 
		  return customerOrderList;
	}
	
	@RequestMapping(value = "/getCanceledCustomerOrderList/{fromDate}/{toDate}", method = RequestMethod.GET) 
	public @ResponseBody List<Order> getCanceledCustomerOrderList(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) throws Exception {
	  
		  List<Order> customerOrderList = null;
		  try {
		     log.info("Canceled customer Order list datewise fetched"); 
		     customerOrderList = dashboardService.getCanceledCustomerOrderList(fromDate, toDate);
	  
		  } catch(Exception e) {
	          
			  log.info("Exception while fetching Canceled customer Order list datewise  : "+e.toString());
		  } 
		  return customerOrderList;
	}
	
	@RequestMapping(value = "/getRegistrationCharges/{franchiseName}", method = RequestMethod.GET) 
	public @ResponseBody int getRegistrationCharges(@PathVariable("franchiseName") String franchiseName) throws Exception {
	  
		  int amount = 0;
		  try {
		     log.info("Supplier Dashboard Productwise Total fetched"); 
		     amount = utilityService.getAmountByFranchiseName(franchiseName);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Supplier Dashboard Productwise Total : "+e.toString());
		  } 
		  return amount;
	}
	
	
	@RequestMapping(value = "/getMyReferral/{contactNo}/{role}", method = RequestMethod.GET) 
	public @ResponseBody List<Referral> getMyReferral(@PathVariable("contactNo") long contactNo, @PathVariable("role") String role) throws Exception {
	  
		  List<Referral> myReferral = null;
		  try {
		     log.info("myReferral fetched"); 
		     myReferral = dashboardService.getMyReferral(contactNo, role);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching myReferral: "+e.toString());
		  } 
		  return myReferral;
	}
	
	
	@RequestMapping(value = "/getNationalComissionBetweenDates/{fromDate}/{toDate}/{nationalFranchiseId}", method = RequestMethod.GET) 
	public @ResponseBody List<CommisionCalculation> getNationalComission(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate, @PathVariable("nationalFranchiseId") int nationalFranchiseId ) throws Exception {
	  
		  List<CommisionCalculation> commisionCalculation = null;
		  try {
		     log.info("getNationalComission fetched"); 
		     commisionCalculation = dashboardService.getNationalCommisionBetweenDates(fromDate, toDate, nationalFranchiseId);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching getNationalComission Calculation: "+e.toString());
		  } 
		  return commisionCalculation;
	}
	
	
	@RequestMapping(value = "/getStateComissionBetweenDates/{fromDate}/{toDate}/{stateCode}/{stateFranchiseId}", method = RequestMethod.GET) 
	public @ResponseBody List<CommisionCalculation> getStateComission(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate , @PathVariable("stateCode") int stateCode, @PathVariable("stateFranchiseId") int stateFranchiseId) throws Exception {
	  
		  List<CommisionCalculation> commisionCalculation = null;
		  try {
		     log.info("getStateComissionBetweenDates fetched"); 
		     commisionCalculation = dashboardService.getStateCommisionBetweenDates(fromDate, toDate, stateCode, stateFranchiseId);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching getStateComissionBetweenDates Calculation: "+e.toString());
		  } 
		  return commisionCalculation;
	}
	
	
	@RequestMapping(value = "/getDistrictComissionBetweenDates/{fromDate}/{toDate}/{districtCode}/{districtFranchiseId}", method = RequestMethod.GET) 
	public @ResponseBody List<CommisionCalculation> getDistrictComission(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate , @PathVariable("districtCode") int districtCode, @PathVariable("districtFranchiseId") int districtFranchiseId) throws Exception {
	  
		  List<CommisionCalculation> commisionCalculation = null;
		  try {
		     log.info("getDistrictComissionBetweenDates fetched"); 
		     commisionCalculation = dashboardService.getDistrictCommisionBetweenDates(fromDate, toDate, districtCode, districtFranchiseId);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching getDistrictComissionBetweenDates Calculation: "+e.toString());
		  } 
		  return commisionCalculation;
	}
	
	
	@RequestMapping(value = "/getTalukaComissionBetweenDates/{fromDate}/{toDate}/{talukaCode}/{talukaFranchiseId}", method = RequestMethod.GET) 
	public @ResponseBody List<CommisionCalculation> getTalukaComission(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate , @PathVariable("talukaCode") int talukaCode, @PathVariable("talukaFranchiseId") int talukaFranchiseId) throws Exception {
	  
		  List<CommisionCalculation> commisionCalculation = null;
		  try {
		     log.info("getTalukaComissionBetweenDates fetched"); 
		     commisionCalculation = dashboardService.getTalukaCommisionBetweenDates(fromDate, toDate, talukaCode, talukaFranchiseId);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching getTalukaComissionBetweenDates Calculation: "+e.toString());
		  } 
		  return commisionCalculation;
	}
	
	
	@RequestMapping(value = "/getReferralCommisionBetweenDates/{fromDate}/{toDate}/{mobileNo}", method = RequestMethod.GET) 
	public @ResponseBody List<ReferralCommision> getReferralCommisionBetweenDates(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate , @PathVariable("mobileNo") String mobileNo) throws Exception {
	  
		  List<ReferralCommision> referralCommision = null;
		  try {
		     log.info("getReferralCommisionBetweenDates fetched"); 
		     referralCommision = dashboardService.getReffaralCommisionBetweenDates(fromDate, toDate, mobileNo);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching getReferralCommisionBetweenDates Calculation: "+e.toString());
		  } 
		  return referralCommision;
	}
}