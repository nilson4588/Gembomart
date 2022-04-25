package com.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.Cart;
import com.rest.model.Order;
import com.rest.model.OrderDetails;

@Component
public interface OrderService {

	public void saveOrder(Order order) throws Exception;
	
	public void createNewOrder(Order order, List<Cart> cartList) throws Exception;
	
	public void updateOrder(Order order) throws Exception;
	
	public Order getOrderById(int orderId, int sellerId) throws Exception;
	
	public List<Order> getOrderList(Order order) throws Exception;	
	
	public List<Order> getTodaysOrderList(Order order) throws Exception;	
	
	public void changeOrderStatus(Order order) throws Exception;
	
	public void changeOrderDetailsStatus(OrderDetails orderDetails) throws Exception;
	
	public List<Order> getTodaysOrderList(int sellerId) throws Exception;
	
	public List<OrderDetails> getOrderDetailsListByOrderId(int orderId, int sellerId) throws Exception;
	
	public List<OrderDetails> getOrderDetailsByOrderId(int orderId) throws Exception ;
	
	public List<Order> getTodaysDeliveryOrderList(int deliveryId) throws Exception;
	
	public Order getOrderForDeliveryByOrderId(int orderId) throws Exception;
	
	public List<Order> getOrderListBySellerId(int sellerId, String orderDate, String orderStatus) throws Exception; 
	
	public String getOrderStatusById(int orderId) throws Exception;
	
	public List<Order> getOrderListByLocalFranchiseId(int localFranchiseId, String orderDate, String orderStatus) throws Exception;
	
	public Order orderOTPVerification(int orderId, String otp) throws Exception;
	
	public void updateDeliveryBoyMargin(int orderId, double margin)  throws Exception;
}