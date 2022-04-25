package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.SupplierOrderDao;
import com.rest.model.ProductSalesPrice;
import com.rest.model.Supplier;
import com.rest.model.SupplierOrder;
import com.rest.model.SupplierOrderDetails;
import com.rest.model.SupplierProductList;
import com.rest.utility.CommonUtil;
import com.rest.utility.ConstantsUtil;
import com.rest.utility.DateTimeUtil;

@Service
public class SupplierOrderServiceImpl implements SupplierOrderService {
	
	@Autowired
	SupplierOrderDao supplierOrderDao;
	
	@Autowired
	SupplierService supplierService;
	
	@Autowired
	UtilityService utilityService;
	
	@Autowired
    ProductService productService;
	
	@Override
	public void createSupplierOrder(List<SupplierOrderDetails> supplierOrderDetailsList) throws Exception {
		
		long mobileNumber = supplierOrderDetailsList.get(0).getMobileNumber();		
		String orderStatus = ConstantsUtil.NEW_ORDER;		
				
		SupplierOrder supplierOrder = new SupplierOrder();
		supplierOrder.setMobileNumber(mobileNumber);
		supplierOrder.setOrderDate(DateTimeUtil.getSysDateTime());
		supplierOrder.setOrderType(orderStatus);
		supplierOrder.setOrderStatus(orderStatus);		
		supplierOrder.setTotalAmount("0");
		supplierOrder.setPaymentMethod("COD");
		supplierOrder.setExpectedDeliveryDate(DateTimeUtil.getNextDay());
		supplierOrder.setShippedDate("");
		supplierOrder.setDeliveredDate("");
		supplierOrder.setCanceledDate("");
		
		supplierOrderDao.saveOrUpdateSupplierOrder(supplierOrder);
		
		double totalAmount   = 0;		
		int    supplierOrderId = utilityService.getRecentId(SupplierOrder.class, "supplierOrderId");
		for (SupplierOrderDetails supplierOrderDetails : supplierOrderDetailsList) {
			
			double productRate = supplierOrderDetails.getProductRate();
			double sellerRatePercentage = (productRate * 20)/100;
			double sellerRate = productRate + sellerRatePercentage;
			
			totalAmount = totalAmount + Double.parseDouble(supplierOrderDetails.getOrderAmount());			
			supplierOrderDetails.setSupplierOrderId(supplierOrderId);
			supplierOrderDetails.setIsActive(1);
			supplierOrderDetails.setSaleRate(sellerRate);
			supplierOrderDao.saveOrUpdateSupplierOrderDetails(supplierOrderDetails); 
		}
		
		supplierOrderDao.updateSupplierOrderTotalAmount(supplierOrderId, String.valueOf(totalAmount));
	}
	
	@Override
	 public List<SupplierProductList> getSupplierProductList(int supplierId) throws Exception {
		return supplierOrderDao.getSupplierProductList(supplierId);
	}
	
	@Override
	public List<SupplierOrder> getTodaysSupplierOrderList() throws Exception {
		
		String createdDateTime = DateTimeUtil.getSysDate();
		List<SupplierOrder> supplierOrderList  = supplierOrderDao.getTodaysSupplierOrderList(createdDateTime);
		List<SupplierOrder> supplierOrderList1 = new ArrayList<SupplierOrder>();
		
		for (SupplierOrder ord : supplierOrderList) {
			
			long mobileNumber = ord.getMobileNumber();			
			
			Supplier supplier  = supplierService.getSupplierByMobileNo(mobileNumber); 
			String name    = supplier.getSupplierFullName(); 
			String address = supplier.getSupplierAddress();
			  
		    ord.setSupplierName(name); 
			ord.setSupplierAddress(address);
			 			
			/*
			 * int supplierOrderId = ord.getSupplierOrderId(); List<SupplierOrderDetails>
			 * SupplierOrderDetailsList =
			 * supplierOrderDao.getSupplierOrderDetailsListBySellerOrderId(supplierOrderId);
			 * List<SupplierOrderDetails> supplierOrderDetailsList1 = new
			 * ArrayList<SupplierOrderDetails>(); for (SupplierOrderDetails
			 * supplierOrderDetails : SupplierOrderDetailsList) {
			 * 
			 * int productId = supplierOrderDetails.getProductId(); String productName =
			 * productService.getProductNameById(productId);
			 * supplierOrderDetails.setProductName(productName);
			 * 
			 * supplierOrderDetailsList1.add(supplierOrderDetails); }
			 * 
			 * ord.setOrderItemData(supplierOrderDetailsList1);
			 */
			 
			String orderStatus = ord.getOrderStatus();		
			String totalAmount = ord.getTotalAmount();
			
			String orderStatusCss = CommonUtil.getOrderStatusCss().get(orderStatus);				
			ord.setOrderStatus(orderStatusCss);
			ord.setTotalAmount("<span class='fa fa-inr'>"+totalAmount+"</span>");
			
			supplierOrderList1.add(ord);
		}
		
		return supplierOrderList1;
	}
	
	@Override
	public void changeSupplierOrderStatus(SupplierOrder order) throws Exception {
		supplierOrderDao.changeSupplierOrderStatus(order);
	}
	
	@Override
	public void removeProductFromSupplierOrderDetails(int supplierOrderDetailsId) throws Exception {
		
		supplierOrderDao.removeProductFromSupplierOrderDetails(supplierOrderDetailsId);
		SupplierOrderDetails supplierOrderDetails = supplierOrderDao.getSupplierOrderDetailsById(supplierOrderDetailsId);
		
		int supplierOrderId =  supplierOrderDetails.getSupplierOrderId();
		double amount     =  Double.parseDouble(supplierOrderDetails.getOrderAmount());
		
		SupplierOrder supplierOrder = supplierOrderDao.getSupplierOrderById(supplierOrderId);
		double totalAmount = Double.parseDouble(supplierOrder.getTotalAmount());
		
		totalAmount = totalAmount - amount;
		
		supplierOrderDao.updateSupplierOrderTotalAmount(supplierOrderId, String.valueOf(totalAmount));
	}
	
	@Override
	public List<SupplierOrderDetails> getTodaysSupplierOrderByMobileNo(long mobileNumber) throws Exception {
		
		String createdDateTime = DateTimeUtil.getSysDate();
		List<SupplierOrder> supplierOrderList  = supplierOrderDao.getTodaysSupplierOrderListByMobileNumber(createdDateTime, mobileNumber);
		
		List<SupplierOrderDetails> supplierOrderDetailsList = new ArrayList<SupplierOrderDetails>();
		List<SupplierOrderDetails> supplierOrderDetailsList1 = new ArrayList<SupplierOrderDetails>();
		
		for (SupplierOrder ord : supplierOrderList) {
									 			
			int supplierOrderId = ord.getSupplierOrderId();			
			supplierOrderDetailsList = supplierOrderDao.getSupplierOrderDetailsListBySellerOrderId(supplierOrderId);
			
			for (SupplierOrderDetails supplierOrderDetails : supplierOrderDetailsList) {
				
				     int    productId   =  supplierOrderDetails.getProductId();
				     String productName =  productService.getProductNameById(productId);
				     supplierOrderDetails.setProductName(productName);
				     supplierOrderDetails.setMobileNumber(mobileNumber);
				     
				     supplierOrderDetailsList1.add(supplierOrderDetails);
			}
		}
		
		return supplierOrderDetailsList1;
	}
	
	@Override
	public List<SupplierOrderDetails> getSupplierOrderById(int supplierOrderId) throws Exception {
		
		
		List<SupplierOrderDetails> supplierOrderDetailsList = new ArrayList<SupplierOrderDetails>();
		List<SupplierOrderDetails> supplierOrderDetailsList1 = new ArrayList<SupplierOrderDetails>();
		
		SupplierOrder supplierOrder = supplierOrderDao.getSupplierOrderById(supplierOrderId);
							
		supplierOrderDetailsList = supplierOrderDao.getSupplierOrderDetailsListBySellerOrderId(supplierOrderId);
		
		for (SupplierOrderDetails supplierOrderDetails : supplierOrderDetailsList) {
			
			     int    productId   =  supplierOrderDetails.getProductId();
			     String productName =  productService.getProductNameById(productId);
			     supplierOrderDetails.setProductName(productName);
			     supplierOrderDetails.setMobileNumber(supplierOrder.getMobileNumber());
			     
			     supplierOrderDetailsList1.add(supplierOrderDetails);
		}
		
		return supplierOrderDetailsList1;
	}
	
	public List<ProductSalesPrice> getProductSalesPriceList(int productId) throws Exception {
		
		String createdDateTime = DateTimeUtil.getSysDate();
		return supplierOrderDao.getProductSalesPriceList(productId, createdDateTime);
	}

	
	/*
	 * @Override public void saveOrUpdateSupplierOrder(SupplierOrder supplierOrder)
	 * throws Exception { // TODO Auto-generated method stub String orderDate =
	 * DateTimeUtil.getSysDateTime(); supplierOrder.setIsActive(1);
	 * supplierOrder.setOrderStatus("New"); supplierOrder.setOrderDate(orderDate);
	 * supplierOrderDao.saveOrUpdateSupplierOrder(supplierOrder); }
	 * 
	 * @Override public SupplierOrder getSupplierOrderById(int supplierOrderId)
	 * throws Exception { // TODO Auto-generated method stub SupplierOrder
	 * supplierOrder = supplierOrderDao.getSupplierOrderById(supplierOrderId); int
	 * productId = supplierOrder.getProductId(); String productName =
	 * productService.getProductNameById(productId);
	 * 
	 * supplierOrder.setProductName(productName);
	 * 
	 * return supplierOrder; }
	 * 
	 * @Override public List<SupplierOrder> getSupplierOrderList() throws Exception
	 * { // TODO Auto-generated method stub List<SupplierOrder> supplierOrderList =
	 * supplierOrderDao.getSupplierOrderList(); List<SupplierOrder>
	 * supplierOrderList1 = new ArrayList<SupplierOrder>();
	 * 
	 * for (SupplierOrder supplierOrder : supplierOrderList) {
	 * 
	 * int productId = supplierOrder.getProductId(); String productName =
	 * productService.getProductNameById(productId);
	 * 
	 * supplierOrder.setProductName(productName);
	 * supplierOrderList1.add(supplierOrder); } return supplierOrderList1; }
	 * 
	 * @Override public void activateOrDeactivateSupplierOrder(SupplierOrder
	 * supplierOrder) throws Exception { // TODO Auto-generated method stub
	 * supplierOrderDao.activateOrDeactivateSupplierOrder(supplierOrder); }
	 * 
	 * 
	 * @Override public List<SupplierProductList> getSupplierProductList(int
	 * supplierId) throws Exception { return
	 * supplierOrderDao.getSupplierProductList(supplierId); }
	 * 
	 * @Override public List<Supplier> getTodaysSupplierOrderList() throws Exception
	 * {
	 * 
	 * String tdate = DateTimeUtil.getSysDate(); List<Supplier> supplierList =
	 * supplierDao.getSupplierListOrderDatewise(tdate); List<Supplier> supplierList1
	 * = new ArrayList<Supplier>();
	 * 
	 * List<SupplierOrder> supplierOrderList = null;
	 * 
	 * for (Supplier supplier2 : supplierList) {
	 * 
	 * long mobileNumber = supplier2.getSupplierContactNo(); supplierOrderList =
	 * supplierOrderDao.getSupplierOrderByMobile(mobileNumber, tdate);
	 * 
	 * List<SupplierOrder> supplierOrderList1 = new ArrayList<SupplierOrder>();
	 * 
	 * String orderStatusCss = null; for (SupplierOrder ord : supplierOrderList) {
	 * String orderStatus = ord.getOrderStatus(); String orderAmount =
	 * ord.getOrderAmount();
	 * 
	 * int productId = ord.getProductId(); String productName =
	 * productService.getProductNameById(productId);
	 * ord.setProductName(productName);
	 * 
	 * orderStatusCss = CommonUtil.getOrderStatusCss().get(orderStatus);
	 * ord.setOrderStatus(orderStatusCss);
	 * ord.setOrderAmount("<span class='fa fa-inr'>"+orderAmount+"</span>");
	 * 
	 * supplierOrderList1.add(ord); }
	 * 
	 * supplier2.setOrderStatus(orderStatusCss);
	 * supplier2.setOrderItemData(supplierOrderList1); supplierList1.add(supplier2);
	 * }
	 * 
	 * return supplierList1; }
	 */
	
	/*
	 * @Override public List<SupplierOrder> getTodaysSupplierOrderList() throws
	 * Exception {
	 * 
	 * String createdDateTime = DateTimeUtil.getSysDate(); List<SupplierOrder>
	 * supplierOrderList =
	 * supplierOrderDao.getTodaysSupplierOrderList(createdDateTime);
	 * List<SupplierOrder> supplierOrderList1 = new ArrayList<SupplierOrder>();
	 * 
	 * for (SupplierOrder ord : supplierOrderList) {
	 * 
	 * String orderStatus = ord.getOrderStatus(); String orderAmount =
	 * ord.getOrderAmount();
	 * 
	 * int productId = ord.getProductId(); String productName =
	 * productService.getProductNameById(productId);
	 * ord.setProductName(productName);
	 * 
	 * String orderStatusCss = CommonUtil.getOrderStatusCss().get(orderStatus);
	 * ord.setOrderStatus(orderStatusCss);
	 * ord.setOrderAmount("<span class='fa fa-inr'>"+orderAmount+"</span>");
	 * 
	 * supplierOrderList1.add(ord); }
	 * 
	 * return supplierOrderList1; }
	 */
	
}