package com.rest.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.Comission;
import com.rest.model.SellerOrder;
import com.rest.model.SellerOrderDetails;
import com.rest.model.SellerProductList;

@Component
public interface SellerOrderDao {
	
	 public void saveOrUpdateSellerOrder(SellerOrder sellerOrder) throws Exception; 
	 
	 public void saveOrUpdateSellerOrderDetails(SellerOrderDetails sellerOrderDetails) throws Exception;
	 
	 public void updateSellerOrderTotalAmount(int sellerOrderId, String totalAmount) throws Exception; 
	 
	 public List<SellerProductList> getSellerProductList() throws Exception;
	 
	 public List<SellerOrder> getTodaysSellerOrderList(String createdDateTime) throws Exception;
	 
	 public List<SellerOrderDetails> getSellerOrderDetailsListBySellerOrderId(int sellerOrderId) throws Exception;
	 
	 public void removeProductFromSellerOrderDetails(int sellerOrderDetailsId) throws Exception;
	 
	 public void changeSellerOrderStatus(SellerOrder order) throws Exception;
	 
	 public SellerOrder getSellerOrderById(int sellerOrderId) throws Exception;  
	 
	 public SellerOrderDetails getSellerOrderDetailsById(int sellerOrderDetailsId) throws Exception; 
	 
	 public List<SellerOrder> getTodaysSellerOrderListByMobileNumber(String createdDateTime, long mobileNumber) throws  Exception; 
	 
	 public void saveOrUpdateComission(Comission comission) throws Exception;
	 
	 public List<SellerOrder> getSellerOrderLocalFranchiseComission(int localFranchiseId) throws Exception; 
	 
	 public List<SellerOrder> getSellerOrderTalukaFranchiseComission(int talukaFranchiseId) throws Exception;
	 
	 public List<SellerOrder> getSellerOrderDistrictFranchiseComission(int districtFranchiseId) throws Exception;
	 
	 /******************  Old
	 
	 public List<SellerOrder> getSellerOrderList() throws Exception; 
	
	 public void activateOrDeactivateSellerOrder(SellerOrder sellerOrder) throws Exception; 
	 
	 public List<SellerOrder> getSellerOrderByMobile(long mobileNumber, String date) throws Exception;	
	 
	 *******************/ 
}