package com.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.Comission;
import com.rest.model.SellerOrder;
import com.rest.model.SellerOrderDetails;
import com.rest.model.SellerProductList;

@Component
public interface SellerOrderService {
	
	public void createSellerOrder(List<SellerOrderDetails> sellerOrderDetails) throws Exception; 

	 public List<SellerProductList> getSellerProductList() throws Exception;
	 
	 public List<SellerOrder> getTodaysSellerOrderList() throws Exception;
	 
	 public void changeSellerOrderStatus(SellerOrder order) throws Exception;
	 
	 public void removeProductFromSellerOrderDetails(int sellerOrderDetailsId) throws Exception;
	 
	 public List<SellerOrderDetails> getTodaysSellerOrderByMobileNo(long mobileNumber) throws Exception;
	 
	 public List<SellerOrderDetails> getSellerOrderById(int sellerOrderId) throws Exception;
	 
	 public void saveOrUpdateComission(Comission comission) throws Exception;
	 
	 public List<SellerOrder> getSellerOrderLocalFranchiseComission(int localFranchiseId) throws Exception;
	 
     public List<SellerOrder> getSellerOrderTalukaFranchiseComission(int talukaFranchiseId) throws Exception;
	 
	 public List<SellerOrder> getSellerOrderDistrictFranchiseComission(int districtFranchiseId) throws Exception;
	 
	/******************  Old 
	 
	 public void saveOrUpdateSellerOrder(SellerOrder sellerOrder) throws Exception; 
	 
	 public SellerOrder getSellerOrderById(int sellerOrderId) throws Exception;  
	 
	 public List<SellerOrder> getSellerOrderList() throws Exception; 
	
	 public void activateOrDeactivateSellerOrder(SellerOrder sellerOrder) throws Exception;
	 
	 
	 
	 public Seller getSellerOrderList(long mobileNumber, String tdate) throws Exception;
	 
	 
	 
	 public void changeSellerOrderStatus(SellerOrder order) throws Exception;
	 
	*******************/ 
}
