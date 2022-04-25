package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.OrderDao;
import com.rest.model.Cart;
import com.rest.model.Customer;
import com.rest.model.Delivery;
import com.rest.model.FranchiseLocal;
import com.rest.model.Order;
import com.rest.model.OrderDetails;
import com.rest.model.Product;
import com.rest.model.ProductSeller;
import com.rest.model.Seller;
import com.rest.utility.CommonUtil;
import com.rest.utility.ConstantsUtil;
import com.rest.utility.DateTimeUtil;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	UtilityService utilityService;
	
	@Autowired
	CustomerService customerService;
		
	@Autowired
	ProductService productService;
	
	@Autowired 
	SellerService sellerService;
	
	@Autowired
	DeliveryService deliveryService;
	
	@Autowired
	FranchiseLocalService franchiseLocalService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	ProductSellerService productSellerService;
	
	@Override
	public void saveOrder(Order order) throws Exception {
		
		double totAmt = Double.parseDouble(order.getTotalAmount());		
		double subTot = totAmt - 25;
			
		order.setSubTotal(String.valueOf(subTot));
		order.setDeliveryCharges("25");
		
		order.setOrderStatus(ConstantsUtil.NEW_ORDER);
		order.setOrderDate(DateTimeUtil.getSysDateTime());
		order.setOrderType(ConstantsUtil.NEW_ORDER);
		order.setExpectedDeliveryDate(DateTimeUtil.getNextDay());
		order.setPaymentMethod("Online");
		order.setShippedDate("");
		order.setDeliveredDate("");
		order.setCanceledDate("");		
		
		orderDao.saveOrder(order);
				
		int orderId = utilityService.getRecentId(Order.class, "orderId");			
		List<OrderDetails> orderDetailsList = order.getOrderItemData();		
		for (OrderDetails orderDetails : orderDetailsList) {
			
			orderDetails.setOrderId(orderId);
			orderDetails.setOrderStatus(ConstantsUtil.NEW_ORDER);
			orderDetails.setOrderDate(DateTimeUtil.getSysDateTime());
			orderDetails.setExpectedDeliveryDate(DateTimeUtil.getNextDay());
			orderDetails.setReadyToShipDate("");
			orderDetails.setPeakUpDate("");
			orderDetails.setDeliveredDate("");
			orderDetails.setCanceledDate("");
			orderDao.saveOrderDetails(orderDetails);
		}		
	}
	
	@Override
	public void createNewOrder(Order order, List<Cart> cartList) throws Exception {
		
		double totAmt = Double.parseDouble(order.getTotalAmount());			
		int deliveryCharges = Integer.parseInt(order.getDeliveryCharges());	
		String paymentMethod = order.getPaymentMethod();
		double subTot = totAmt - deliveryCharges;			
		order.setSubTotal(String.valueOf(subTot));
		String otp = CommonUtil.generateOTP(4);
		
		order.setOrderStatus(ConstantsUtil.NEW_ORDER);
		order.setOrderDate(DateTimeUtil.getSysDateTime());
		order.setOrderType(ConstantsUtil.NEW_ORDER);
		order.setExpectedDeliveryDate(DateTimeUtil.getNextDay());
		order.setOrderOtp(otp);
	   		
		if(paymentMethod.equals("COD")) {
			order.setPaymentId("");
		}
		
		//order.setPaymentMethod("Online");
		order.setShippedDate("");
		order.setDeliveredDate("");
		order.setCanceledDate("");	
		order.setLatitude("");
		order.setLongitude("");
		
		int sellerId = cartList.get(0).getSellerId();
		
		orderDao.saveOrder(order);
				
		int orderId = utilityService.getRecentId(Order.class, "orderId");

		double margin = 0.0;
		
		//List<OrderDetails> orderDetailsList = order.getOrderItemData();		
		for (Cart cart : cartList) {
			
			OrderDetails orderDetails = new OrderDetails();
			
			int qty  = cart.getQuantity();
			int rate = cart.getRate();
	        int productId = cart.getProductId(); 
	        
	        ProductSeller ps = productSellerService.getProductSellerByProductAndSeller(sellerId, productId);
	        margin += ((ps.getPer15price()*2)/100); 
	        
			orderDetails.setProductId(productId);
			orderDetails.setQuantity(qty);
			orderDetails.setRate(rate);
			orderDetails.setDiscount(cart.getDiscount());
			orderDetails.setAmount(qty*rate);
			orderDetails.setSellerId(sellerId);			
			orderDetails.setOrderId(orderId);
			orderDetails.setOrderStatus(ConstantsUtil.NEW_ORDER);
			orderDetails.setOrderDate(DateTimeUtil.getSysDateTime());
			orderDetails.setExpectedDeliveryDate(DateTimeUtil.getNextDay());
			orderDetails.setReadyToShipDate("");
			orderDetails.setPeakUpDate("");
			orderDetails.setDeliveredDate("");
			orderDetails.setCanceledDate("");
			orderDao.saveOrderDetails(orderDetails);
		}		
		
		orderDao.updateDeliveryBoyMargin(orderId, margin);
	}
	
	@Override
	public void updateOrder(Order order) throws Exception {
		
		orderDao.updateOrder(order);
		
		int orderId = order.getOrderId();
		orderDao.removeOrderDetails(orderId);
		
		List<OrderDetails> orderDetailsList = order.getOrderItemData();
		for (OrderDetails orderDetails : orderDetailsList) {			
			orderDetails.setOrderId(orderId);
			orderDao.saveOrderDetails(orderDetails);
		}
	}
	
	@Override
	public Order getOrderById(int orderId, int sellerId) throws Exception {
				
		Order order = orderDao.getOrderById(orderId);
		long mobileNumber = order.getMobileNo();
				
		Customer customer = customerService.getCustomerByMobileNo(String.valueOf(mobileNumber));
		order.setCustomerName(customer.getFname()+" "+customer.getLname());
		//order.setCustomerAddress(customer.getAddr());		
		order.setOrderItemData(getOrderDetailsListByOrderId(orderId, sellerId));
				
		return order;
	}
	
	@Override
	public Order getOrderForDeliveryByOrderId(int orderId) throws Exception {
				
		Order order = orderDao.getOrderById(orderId);
		long mobileNumber = order.getMobileNo();
		
		int deliveryBoyId = order.getDeliveryBoyId();
		
		List<OrderDetails> orderDetailsList = getOrderDetailsByOrderId(orderId);
		
		int sellerId = orderDetailsList.get(0).getSellerId();
		int localfranchiseId = sellerService.getSellerById(sellerId).getLocalFranchiseId();
		FranchiseLocal franchiseLocal = franchiseLocalService.getFranchiseLocalById(localfranchiseId);
		String localFranchiseName = franchiseLocal.getFullName();
		long localFranchiseMobile = franchiseLocal.getFranchiseContactNo();
		int talukaCode = franchiseLocal.getTalukaCode();
		String talukaName = cityService.getTalukaName(talukaCode);
		
		order.setTalukaFranchiseName(localFranchiseName);
		order.setTalukaFranchiseContact(localFranchiseMobile);
		order.setTalukaName(talukaName);
				
		Customer customer = customerService.getCustomerByMobileNo(String.valueOf(mobileNumber));
		order.setCustomerName(customer.getFname()+" "+customer.getLname());
		//order.setCustomerAddress(customer.getAddr());		
		order.setOrderItemData(orderDetailsList);
		
		if(deliveryBoyId == 0) {
			order.setDeliveryBoyName("");
			order.setDeliveryBoyMobileNumber(0);
		} else {
			Delivery delivery = deliveryService.getDeliveryById(deliveryBoyId);
			order.setDeliveryBoyName(delivery.getDeliveryFullName());
			order.setDeliveryBoyMobileNumber(delivery.getDeliveryContactNo());
		}
		
				
		return order;
	}
	
	@Override
	public List<Order> getOrderList(Order order) throws Exception 	{
		
		if(order.getOrderDate().equals("0")) {
			String currentDate = DateTimeUtil.getSysDate();
			order.setOrderDate(currentDate);
		}
				
		List<Order> orderList = orderDao.getOrderList(order);
		List<Order> orderList1 = new ArrayList<Order>();
		
		for (Order ord : orderList) {
			
			int orderId = ord.getOrderId();
			long mobileNumber = ord.getMobileNo();
			int deliveryBoyId = ord.getDeliveryBoyId();
			
			Customer customer = customerService.getCustomerByMobileNo(String.valueOf(mobileNumber));
			ord.setCustomerName(customer.getFname()+" "+customer.getLname());
			//ord.setCustomerAddress(customer.getAddr());
			
			if(deliveryBoyId == 0) {
				ord.setDeliveryBoyName("");
				ord.setDeliveryBoyMobileNumber(0);
			} else {
				Delivery delivery = deliveryService.getDeliveryById(deliveryBoyId);
				ord.setDeliveryBoyName(delivery.getDeliveryFullName());
				ord.setDeliveryBoyMobileNumber(delivery.getDeliveryContactNo());
			}
			 
			List<OrderDetails> orderDetailsList = getOrderDetailsByOrderId(orderId);
			
			int sellerId = orderDetailsList.get(0).getSellerId();
			Seller seller = sellerService.getSellerById(sellerId);
			ord.setShopName(seller.getShopName());
			ord.setShopAddress(seller.getSellerAddress());
			ord.setShopMobile(String.valueOf(seller.getSellerContactNo()));
			ord.setLatitude(seller.getLatitude());
			ord.setLongitude(seller.getLongitude());
									
			ord.setOrderItemData(orderDetailsList);
			orderList1.add(ord);
		}
		
		return orderList1;
	}
	
	@Override
	public List<Order> getTodaysOrderList(Order order) throws Exception {
		
		String currentDate = DateTimeUtil.getSysDate();
		order.setOrderDate(currentDate);
		order.setMobileNo(0);
		
		List<Order> orderList = orderDao.getOrderList(order);
		List<Order> orderList1 = new ArrayList<Order>();
		
		for (Order ord : orderList) {
			
			int orderId = ord.getOrderId();
			long mobileNumber = ord.getMobileNo();
			int deliveryBoyId = ord.getDeliveryBoyId();
			
			Customer customer = customerService.getCustomerByMobileNo(String.valueOf(mobileNumber));
			ord.setCustomerName(customer.getFname()+" "+customer.getLname());
			//ord.setCustomerAddress(customer.getAddr());
			
			if(deliveryBoyId == 0) {
				ord.setDeliveryBoyName("");
				ord.setDeliveryBoyMobileNumber(0);
			} else {
				Delivery delivery = deliveryService.getDeliveryById(deliveryBoyId);
				ord.setDeliveryBoyName(delivery.getDeliveryFullName());
				ord.setDeliveryBoyMobileNumber(delivery.getDeliveryContactNo());
			}
			 
			List<OrderDetails> orderDetailsList = getOrderDetailsByOrderId(orderId);
									
			ord.setOrderItemData(orderDetailsList);
			orderList1.add(ord);
		}
		
		return orderList1;
	}
	
	@Override
	public void changeOrderStatus(Order order) throws Exception {		
		orderDao.changeOrderStatus(order);
	}
	
	@Override
	public void changeOrderDetailsStatus(OrderDetails orderDetails) throws Exception {
		orderDao.changeOrderDetailsStatus(orderDetails);
	}
	
	@Override
	public List<Order> getTodaysOrderList(int sellerId) throws Exception {
		
		String createdDateTime = DateTimeUtil.getSysDate();
		List<Order> orderList  = orderDao.getTodaysOrderList(sellerId, createdDateTime);
		List<Order> orderList1 = new ArrayList<Order>();
				
		  for (Order ord : orderList) {
		  			  
			  String shopName = sellerService.getShopNameBySellerId(sellerId);
			  ord.setShopName(shopName);
			  
			  long mobileNumber = ord.getMobileNo();
			  int deliveryBoyId = ord.getDeliveryBoyId();
				
			  Customer customer = customerService.getCustomerByMobileNo(String.valueOf(mobileNumber));
			  ord.setCustomerName(customer.getFname()+" "+customer.getLname());
			//  ord.setCustomerAddress(customer.getAddr());
			  
			  if(deliveryBoyId == 0) {
					ord.setDeliveryBoyName("");
					ord.setDeliveryBoyMobileNumber(0);
				} else {
					Delivery delivery = deliveryService.getDeliveryById(deliveryBoyId);
					ord.setDeliveryBoyName(delivery.getDeliveryFullName());
					ord.setDeliveryBoyMobileNumber(delivery.getDeliveryContactNo());
				}
				 
			  
		      orderList1.add(ord); 
		  }		 
		
		return orderList1;
	}
	
	@Override
	public List<Order> getTodaysDeliveryOrderList(int deliveryId) throws Exception {
		
		String createdDateTime = DateTimeUtil.getSysDate();
		List<Order> orderList  = orderDao.getTodaysDeliveryOrderList(deliveryId, createdDateTime);
		List<Order> orderList1 = new ArrayList<Order>();
				
		  for (Order ord : orderList) {
		  			  
			  int orderId = ord.getOrderId();
			  
			  long mobileNumber = ord.getMobileNo();
				
			  Customer customer = customerService.getCustomerByMobileNo(String.valueOf(mobileNumber));
			  ord.setCustomerName(customer.getFname()+" "+customer.getLname());
			 // ord.setCustomerAddress(customer.getAddr());		
			  
			  ord.setOrderItemData(getOrderDetailsByOrderId(orderId));
			  
		      orderList1.add(ord); 
		  }
		 
		
		return orderList1;
	}
	
	@Override
	public List<OrderDetails> getOrderDetailsListByOrderId(int orderId, int sellerId) throws Exception {
		
		List<OrderDetails> OrderDetailsList  = orderDao.getOrderDetailsListByOrderId(orderId, sellerId);
        List<OrderDetails> OrderDetailsList1 = new ArrayList<OrderDetails>();
		  
		for (OrderDetails orderDetails : OrderDetailsList) {
			
			  int productId = orderDetails.getProductId();
			  String productName = productService.getProductNameById(productId);
			  orderDetails.setProductName(productName);
			  
			  int seller = orderDetails.getSellerId();
			  String shopName = sellerService.getShopNameBySellerId(seller);
			  orderDetails.setShopName(shopName);
			  
			  OrderDetailsList1.add(orderDetails);
		}
		  
		return OrderDetailsList1;
	}
	
	@Override
	public List<OrderDetails> getOrderDetailsByOrderId(int orderId) throws Exception {
		
		List<OrderDetails> OrderDetailsList  = orderDao.getOrderDetailsByOrderId(orderId);
        List<OrderDetails> OrderDetailsList1 = new ArrayList<OrderDetails>();
		  
		for (OrderDetails orderDetails : OrderDetailsList) {
			
			  int productId = orderDetails.getProductId();
			  Product product =  productService.getProductById(productId);
			  String productImage = product.getImages().get(0).getSrc();
			  String productName = product.getTitle(); //productService.getProductNameById(productId);
			  orderDetails.setProductName(productName);
			  orderDetails.setProductImage(productImage);
			  
			  int sellerId = orderDetails.getSellerId();
			  Seller seller = sellerService.getSellerById(sellerId);
			  String shopName = seller.getShopName();
			  String shopAddress = seller.getSellerAddress();
			  String shopMobile =  String.valueOf(seller.getSellerContactNo());
			  String lat = seller.getLatitude();
			  String lon = seller.getLongitude();
			  orderDetails.setShopName(shopName);
			  orderDetails.setShopAddress(shopAddress);
			  orderDetails.setShopMobile(shopMobile);
			  orderDetails.setLatitude(lat);
			  orderDetails.setLongitude(lon);
			  
			  OrderDetailsList1.add(orderDetails);
		}
		  
		return OrderDetailsList1;
	}
	
	@Override
	public List<Order> getOrderListBySellerId(int sellerId, String orderDate, String orderStatus) throws Exception {
		
		if(orderDate.equals("0")) {
			String currentDate = DateTimeUtil.getSysDate();
			orderDate = currentDate;
		}
		
		List<Order> orderList = orderDao.getOrderListBySellerId(sellerId, orderDate, orderStatus);
		List<Order> orderList1 = new ArrayList<Order>();
		
		for (Order ord : orderList) {
			
			int orderId       = ord.getOrderId();
			long mobileNumber = ord.getMobileNo();
			int deliveryBoyId = ord.getDeliveryBoyId();
									
			Customer customer = customerService.getCustomerByMobileNo(String.valueOf(mobileNumber));
			ord.setCustomerName(customer.getFname()+" "+customer.getLname());
			//ord.setCustomerAddress(customer.getAddr());
			 
			List<OrderDetails> orderDetailsList = getOrderDetailsByOrderId(orderId);									
			ord.setOrderItemData(orderDetailsList);
			
			double sellerCharges = 0;
			
			for (OrderDetails od : orderDetailsList) {
				
				int productId = od.getProductId();
				
				ProductSeller ps = productSellerService.getProductSellerByProductAndSeller(sellerId, productId);
				double purchase = ps.getPurchasePrice();
				od.setPurchasePrice(purchase);
				sellerCharges += purchase;
			}
			
			ord.setSellerCharges(sellerCharges);
			
			if(deliveryBoyId == 0) {
				ord.setDeliveryBoyName("");
				ord.setDeliveryBoyMobileNumber(0);
			} else {
				Delivery delivery = deliveryService.getDeliveryById(deliveryBoyId);
				ord.setDeliveryBoyName(delivery.getDeliveryFullName());
				ord.setDeliveryBoyMobileNumber(delivery.getDeliveryContactNo());
			}
						
			orderList1.add(ord);
		}
		
		return orderList1;
	}
	
	@Override
	public String getOrderStatusById(int orderId) throws Exception {
		return orderDao.getOrderStatusById(orderId);
	}
	
	@Override
	public List<Order> getOrderListByLocalFranchiseId(int localFranchiseId, String orderDate, String orderStatus) throws Exception {
		
		if(orderDate.equals("0")) {
			String currentDate = DateTimeUtil.getSysDate();
			orderDate = currentDate;
		}		
		
		List<Order> orderList = orderDao.getOrderListByLocalFranchiseId(localFranchiseId, orderDate, orderStatus);
		List<Order> orderList1 = new ArrayList<Order>();
		
		for (Order ord : orderList) {
			
			int orderId = ord.getOrderId();
			long mobileNumber = ord.getMobileNo();
			int deliveryBoyId = ord.getDeliveryBoyId();
						
			Customer customer = customerService.getCustomerByMobileNo(String.valueOf(mobileNumber));
			ord.setCustomerName(customer.getFname()+" "+customer.getLname());
			//ord.setCustomerAddress(customer.getAddr());
			 
			List<OrderDetails> orderDetailsList = getOrderDetailsByOrderId(orderId);									
			ord.setOrderItemData(orderDetailsList);
			
			if(deliveryBoyId == 0) {
				ord.setDeliveryBoyName("");
				ord.setDeliveryBoyMobileNumber(0);
			} else {
				Delivery delivery = deliveryService.getDeliveryById(deliveryBoyId);
				ord.setDeliveryBoyName(delivery.getDeliveryFullName());
				ord.setDeliveryBoyMobileNumber(delivery.getDeliveryContactNo());
			}
			
			int sellerId = orderDetailsList.get(0).getSellerId();
			String shopName = sellerService.getShopNameBySellerId(sellerId);
			ord.setShopName(shopName);
			
			orderList1.add(ord);
		}
		
		return orderList1;
	}
	
	public Order orderOTPVerification(int orderId, String otp) throws Exception {
		
		   Order ord = orderDao.orderOTPVerification(orderId, otp);
		   if(ord != null) {
			   orderDao.changeOrderStatusCustom(orderId, "Verified");
		   }
		
		  return ord;
	}
	
	public void updateDeliveryBoyMargin(int orderId, double margin)  throws Exception{
		  orderDao.updateDeliveryBoyMargin(orderId, margin);
	}
}