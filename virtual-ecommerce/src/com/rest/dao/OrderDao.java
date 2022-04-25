package com.rest.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.Order;
import com.rest.model.OrderDetails;

@Component
public interface OrderDao {
	
	  public void saveOrder(Order order) throws Exception;
	  
	  public void saveOrderDetails(OrderDetails orderDetails) throws Exception;	  
	  
	  public void updateOrder(Order order) throws Exception;
	  
	  public void removeOrderDetails(int orderId) throws Exception;
	  
	  public Order getOrderById(int orderId) throws Exception;	  
	 
	  public List<Order> getOrderList(Order order) throws Exception;
	  
	  public List<OrderDetails> getOrderDetailsListByOrderId(int orderId, int sellerId) throws Exception;  
	  
	  public void changeOrderStatus(Order order) throws Exception;
	  
	  public void changeOrderStatusCustom(int orderId, String orderStatus) throws Exception;
	  
	  public void changeOrderDetailsStatus(OrderDetails orderDetails) throws Exception;
	  
	  public List<Order> getTodaysOrderList(int sellerId, String orderDate) throws Exception;
	  
	  public List<Order> getTodaysDeliveryOrderList(int deliveryId, String orderDate) throws Exception;
	  
	  public List<OrderDetails> getOrderDetailsListBySellerId(int sellerId) throws Exception;
	  
	  public List<OrderDetails> getOrderDetailsByOrderId(int orderId) throws Exception; 
	  
	  public Order orderOTPVerification(int orderId, String otp) throws Exception;
	  
	  public List<Order> getOrderListBySellerId(int sellerId, String orderDate, String orderStatus) throws Exception; 
	  
	  public String getOrderStatusById(int orderId) throws Exception;
	  
	  public List<Order> getOrderListByLocalFranchiseId(int localFranchiseId, String orderDate, String orderStatus) throws Exception;
	 
	  public void updateDeliveryBoyMargin(int orderId, double margin)  throws Exception;
}