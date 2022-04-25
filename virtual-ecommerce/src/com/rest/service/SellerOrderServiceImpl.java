package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.SellerOrderDao;
import com.rest.model.Comission;
import com.rest.model.Seller;
import com.rest.model.SellerOrder;
import com.rest.model.SellerOrderDetails;
import com.rest.model.SellerProductList;
import com.rest.utility.CommonUtil;
import com.rest.utility.ConstantsUtil;
import com.rest.utility.DateTimeUtil;

@Service
public class SellerOrderServiceImpl implements SellerOrderService {
	
	@Autowired
	SellerOrderDao sellerOrderDao;
	
	@Autowired
	SellerService sellerService;
	
	@Autowired
	UtilityService utilityService;
	
	@Autowired
    ProductService productService;
	
	@Override
	public void createSellerOrder(List<SellerOrderDetails> sellerOrderDetailsList) throws Exception {
		
		long mobileNumber = sellerOrderDetailsList.get(0).getMobileNumber();	
		String vcRequest = sellerOrderDetailsList.get(0).getVideoCallRequest();
		String orderStatus = ConstantsUtil.NEW_ORDER;		
		
		SellerOrder sellerOrder = new SellerOrder();
		sellerOrder.setMobileNumber(mobileNumber);
		sellerOrder.setOrderDate(DateTimeUtil.getSysDateTime());
		sellerOrder.setOrderType(orderStatus);
		sellerOrder.setOrderStatus(orderStatus);		
		sellerOrder.setTotalAmount("0");
		sellerOrder.setPaymentMethod("COD");
		sellerOrder.setExpectedDeliveryDate(DateTimeUtil.getNextDay());
		sellerOrder.setShippedDate("");
		sellerOrder.setDeliveredDate("");
		sellerOrder.setCanceledDate("");
		sellerOrder.setVideoCallRequest(vcRequest);
		
		sellerOrderDao.saveOrUpdateSellerOrder(sellerOrder);
		
		double totalAmount   = 0;		
		int    sellerOrderId = utilityService.getRecentId(SellerOrder.class, "sellerOrderId");
		for (SellerOrderDetails sellerOrderDetails : sellerOrderDetailsList) {
			
			totalAmount = totalAmount + Double.parseDouble(sellerOrderDetails.getOrderAmount());			
			sellerOrderDetails.setSellerOrderId(sellerOrderId);
			sellerOrderDetails.setIsActive(1);
			sellerOrderDao.saveOrUpdateSellerOrderDetails(sellerOrderDetails);
		}
		
		sellerOrderDao.updateSellerOrderTotalAmount(sellerOrderId, String.valueOf(totalAmount));
	}
	
	@Override
	public List<SellerProductList> getSellerProductList() throws Exception {
		return sellerOrderDao.getSellerProductList();
	}
	
	@Override
	public List<SellerOrder> getTodaysSellerOrderList() throws Exception {
		
		String createdDateTime = DateTimeUtil.getSysDate();
		List<SellerOrder> sellerOrderList  = sellerOrderDao.getTodaysSellerOrderList(createdDateTime);
		List<SellerOrder> sellerOrderList1 = new ArrayList<SellerOrder>();
		
		for (SellerOrder ord : sellerOrderList) {
			
			long mobileNumber = ord.getMobileNumber();			
			
			Seller seller  = sellerService.getSellerByMobileNo(mobileNumber); 
			String name    = seller.getSellerFullName(); 
			String address = seller.getSellerAddress();
			  
		    ord.setSellerName(name); 
			ord.setSellerAddress(address);
			 			
			/*
			 * int sellerOrderId = ord.getSellerOrderId(); List<SellerOrderDetails>
			 * sellerOrderDetailsList =
			 * sellerOrderDao.getSellerOrderDetailsListBySellerOrderId(sellerOrderId);
			 * List<SellerOrderDetails> sellerOrderDetailsList1 = new
			 * ArrayList<SellerOrderDetails>(); for (SellerOrderDetails sellerOrderDetails :
			 * sellerOrderDetailsList) {
			 * 
			 * int productId = sellerOrderDetails.getProductId(); String productName =
			 * productService.getProductNameById(productId);
			 * sellerOrderDetails.setProductName(productName);
			 * 
			 * sellerOrderDetailsList1.add(sellerOrderDetails); }
			 * 
			 * ord.setOrderItemData(sellerOrderDetailsList1);
			 */
			 
			String orderStatus = ord.getOrderStatus();		
			String totalAmount = ord.getTotalAmount();
			
			String orderStatusCss = CommonUtil.getOrderStatusCss().get(orderStatus);				
			ord.setOrderStatus(orderStatusCss);
			ord.setTotalAmount("<span class='fa fa-inr'>"+totalAmount+"</span>");
			
			sellerOrderList1.add(ord);
		}
		
		return sellerOrderList1;
	}
	
	@Override
	public void changeSellerOrderStatus(SellerOrder order) throws Exception {
		
		int    orderId      = order.getSellerOrderId();  
		String orderStatus  = order.getOrderStatus();
				
		sellerOrderDao.changeSellerOrderStatus(order);
		
		if(orderStatus.equals("Delivered")) {
			
			SellerOrder sellerOrder = sellerOrderDao.getSellerOrderById(orderId);
						
			double orderAmount       = Double.parseDouble(sellerOrder.getTotalAmount());	
			double franchiseLocal    = (orderAmount*2)/100; //2
			double franchiseTaluka   = (orderAmount*2)/100; //2
			double franchiseDistrict = (orderAmount*2)/100; //2
			double deliveryPerson    = (orderAmount*2)/100; //2
			double gembomart         = (orderAmount*12)/100; //12
						
			Comission comission = new Comission();
			comission.setSellerOrderId(orderId);
			comission.setFranchiseLocal(franchiseLocal);
			comission.setFranchiseTaluka(franchiseTaluka);
			comission.setFranchiseDistrict(franchiseDistrict);
			comission.setDeliveryPerson(deliveryPerson);
			comission.setGembomart(gembomart);
			
			saveOrUpdateComission(comission);
		}		
	}
	
	@Override
	public void removeProductFromSellerOrderDetails(int sellerOrderDetailsId) throws Exception {
		
		sellerOrderDao.removeProductFromSellerOrderDetails(sellerOrderDetailsId);
		SellerOrderDetails sellerOrderDetails = sellerOrderDao.getSellerOrderDetailsById(sellerOrderDetailsId);
		
		int sellerOrderId =  sellerOrderDetails.getSellerOrderId();
		double amount     =  Double.parseDouble(sellerOrderDetails.getOrderAmount());
		
		SellerOrder sellerOrder = sellerOrderDao.getSellerOrderById(sellerOrderId);
		double totalAmount = Double.parseDouble(sellerOrder.getTotalAmount());
		
		totalAmount = totalAmount - amount;
		
		sellerOrderDao.updateSellerOrderTotalAmount(sellerOrderId, String.valueOf(totalAmount));
	}
	
	
	@Override
	public List<SellerOrderDetails> getTodaysSellerOrderByMobileNo(long mobileNumber) throws Exception {
		
		String createdDateTime = DateTimeUtil.getSysDate();
		List<SellerOrder> sellerOrderList  = sellerOrderDao.getTodaysSellerOrderListByMobileNumber(createdDateTime, mobileNumber);
		
		List<SellerOrderDetails> sellerOrderDetailsList = new ArrayList<SellerOrderDetails>();
		List<SellerOrderDetails> sellerOrderDetailsList1 = new ArrayList<SellerOrderDetails>();
		
		for (SellerOrder ord : sellerOrderList) {
									 			
			int sellerOrderId = ord.getSellerOrderId();			
			sellerOrderDetailsList = sellerOrderDao.getSellerOrderDetailsListBySellerOrderId(sellerOrderId);
			
			for (SellerOrderDetails sellerOrderDetails : sellerOrderDetailsList) {
				
				     int    productId   =  sellerOrderDetails.getProductId(); 
				     String productName =  productService.getProductNameById(productId);
				     sellerOrderDetails.setProductName(productName);
				     sellerOrderDetails.setMobileNumber(mobileNumber);
				     
				     sellerOrderDetailsList1.add(sellerOrderDetails);
			}
		}
		
		return sellerOrderDetailsList1;
	}
	
	
	@Override
	public List<SellerOrderDetails> getSellerOrderById(int sellerOrderId) throws Exception {
				
		List<SellerOrderDetails> sellerOrderDetailsList = new ArrayList<SellerOrderDetails>();
		List<SellerOrderDetails> sellerOrderDetailsList1 = new ArrayList<SellerOrderDetails>();
		
		SellerOrder sellerOrder = sellerOrderDao.getSellerOrderById(sellerOrderId);
							
		sellerOrderDetailsList = sellerOrderDao.getSellerOrderDetailsListBySellerOrderId(sellerOrderId);
		
		for (SellerOrderDetails sellerOrderDetails : sellerOrderDetailsList) {
			
			     int    productId   =  sellerOrderDetails.getProductId();
			     String productName =  productService.getProductNameById(productId);
			     sellerOrderDetails.setProductName(productName);
			     sellerOrderDetails.setMobileNumber(sellerOrder.getMobileNumber());
			     
			     sellerOrderDetailsList1.add(sellerOrderDetails);
		}
		
		return sellerOrderDetailsList1;
	}
	
	@Override
	public void saveOrUpdateComission(Comission comission) throws Exception{
		sellerOrderDao.saveOrUpdateComission(comission);
	}
	
	@Override
	public List<SellerOrder> getSellerOrderLocalFranchiseComission(int localFranchiseId) throws Exception {
		return sellerOrderDao.getSellerOrderLocalFranchiseComission(localFranchiseId);
	}
	
	@Override
    public List<SellerOrder> getSellerOrderTalukaFranchiseComission(int talukaFranchiseId) throws Exception {
		return sellerOrderDao.getSellerOrderTalukaFranchiseComission(talukaFranchiseId);
	}
	 
	@Override
	public List<SellerOrder> getSellerOrderDistrictFranchiseComission(int districtFranchiseId) throws Exception {
		 return sellerOrderDao.getSellerOrderDistrictFranchiseComission(districtFranchiseId);
	}
	
	/******************  Old
	
	@Override
	public void saveOrUpdateSellerOrder(SellerOrder sellerOrder) throws Exception {
		// TODO Auto-generated method stuOb
		String orderDate = DateTimeUtil.getSysDateTime();
		sellerOrder.setIsActive(1);
		sellerOrder.setOrderDate(orderDate);
		sellerOrder.setOrderStatus("New");
		sellerOrderDao.saveOrUpdateSellerOrder(sellerOrder);
	}

	@Override
	public SellerOrder getSellerOrderById(int sellerOrderId) throws Exception {
		// TODO Auto-generated method stub
		SellerOrder sellerOrder = sellerOrderDao.getSellerOrderById(sellerOrderId);
		
		int productId = sellerOrder.getProductId();
		String productName = productService.getProductNameById(productId);
		
		sellerOrder.setProductName(productName);		
		
		return sellerOrder;
	}

	@Override
	public List<SellerOrder> getSellerOrderList() throws Exception {
		
		List<SellerOrder> sellerOrderList = sellerOrderDao.getSellerOrderList();
		List<SellerOrder> sellerOrderList1 = new ArrayList<SellerOrder>();
		
		for (SellerOrder sellerOrder : sellerOrderList) {
			int productId = sellerOrder.getProductId();
			String productName = productService.getProductNameById(productId);
			
			sellerOrder.setProductName(productName);
			sellerOrderList1.add(sellerOrder);
		}
		return sellerOrderList1;
	}

	@Override
	public void activateOrDeactivateSellerOrder(SellerOrder sellerOrder) throws Exception {
		// TODO Auto-generated method stub
		sellerOrderDao.activateOrDeactivateSellerOrder(sellerOrder);
	}
	
	
	
	@Override
	public Seller getSellerOrderList(long mobileNumber, String tdate) throws Exception {
		
		Seller seller = sellerDao.getSellerByMobileNo(mobileNumber);
		
	    List<SellerOrder> sellerOrderList = sellerOrderDao.getSellerOrderByMobile(mobileNumber, tdate);		
	    List<SellerOrder> sellerOrderList1 = new ArrayList<SellerOrder>();
		
		for (SellerOrder sellerOrder : sellerOrderList) {
			int productId = sellerOrder.getProductId();
			String productName = productService.getProductNameById(productId);
			
			sellerOrder.setProductName(productName);
			sellerOrderList1.add(sellerOrder);
		}
	    
		seller.setOrderItemData(sellerOrderList1);
		
		return seller;
	}
		
	*******************/ 
}