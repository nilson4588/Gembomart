package com.rest.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.Delivery;
import com.rest.model.DeliveryOrder;


@Component
public interface DeliveryDao {

	 public int saveOrUpdateDelivery(Delivery delivery) throws Exception; 
	 
	 public Delivery getDeliveryById(int deliveryId) throws Exception;  
	 
	 public List<Delivery> getDeliveryList() throws Exception;
	 
	 public Delivery getDeliveryByMobileNo(long mobileNo) throws Exception;
	 
	 public int activateOrDeactivateDelivery(Delivery delivery) throws Exception;
	 
	 public List<Delivery> deliveryListByFranchiseLocal(int localFranchiseId) throws Exception;
	 
	 public List<DeliveryOrder> getDeliveryOrders(String todaysDate, int localFranchiseId) throws Exception;
	 
	 public List<Delivery> deliveryListByFranchiseTaluka(int talukaFranchiseId) throws Exception;
	 
	 public List<Delivery> deliveryListByFranchiseDistrict(int districtFranchiseId) throws Exception;
	 
	 public Delivery getDeliveryByMobileAndPassword(long mobileNumber, String password) throws Exception;
	 
	 public int changePasswordByDeliveryMobile(Delivery delivery) throws Exception; 
	 
	 public List<String> deliveryTokenByOrderSellerId(int sellerId) throws Exception; 
	 
	 public String getTokenByDeliveryId(int deliveryId) throws Exception;
	 
	 public int updateDeliveryToken(Delivery delivery) throws Exception; 
}
