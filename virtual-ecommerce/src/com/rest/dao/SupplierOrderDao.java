package com.rest.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.ProductSalesPrice;
import com.rest.model.SupplierOrder;
import com.rest.model.SupplierOrderDetails;
import com.rest.model.SupplierProductList;

@Component
public interface SupplierOrderDao {
	
	 public void saveOrUpdateSupplierOrder(SupplierOrder supplierOrder) throws Exception; 
	 
	 public void saveOrUpdateSupplierOrderDetails(SupplierOrderDetails supplierOrderDetails) throws Exception;
	 
	 public void updateSupplierOrderTotalAmount(int supplierOrderId, String totalAmount) throws Exception; 
	 
	 public List<SupplierProductList> getSupplierProductList(int supplierId) throws Exception;
	 
	 public List<SupplierOrder> getTodaysSupplierOrderList(String createdDateTime) throws Exception;
	 
	 public List<SupplierOrder> getTodaysSupplierOrderListByMobileNumber(String createdDateTime, long mobileNumber) throws Exception;
	 
	 public List<SupplierOrderDetails> getSupplierOrderDetailsListBySellerOrderId(int supplierOrderId) throws Exception;
	 
	 public void removeProductFromSupplierOrderDetails(int supplierOrderDetailsId) throws Exception;
	 
	 public void changeSupplierOrderStatus(SupplierOrder order) throws Exception;
	 
	 public SupplierOrder getSupplierOrderById(int supplierOrderId) throws Exception;  
	 
	 public SupplierOrderDetails getSupplierOrderDetailsById(int supplierOrderDetailsId) throws Exception; 
	 
	 public List<ProductSalesPrice> getProductSalesPriceList(int productId, String todaysDate) throws Exception;
	
	 /*public void saveOrUpdateSupplierOrder(SupplierOrder supplierOrder) throws Exception; 
	 
	 public SupplierOrder getSupplierOrderById(int supplierOrderId) throws Exception;  
	 
	 public List<SupplierOrder> getSupplierOrderList() throws Exception; 
	
	 public void activateOrDeactivateSupplierOrder(SupplierOrder supplierOrder) throws Exception;
	 
	 public List<SupplierProudctList> getSupplierProductList(int supplierId) throws Exception;
	 
	 public List<SupplierOrder> getSupplierOrderByMobile(long mobileNumber, String date) throws Exception;
	 
	 public List<SupplierOrder> getTodaysSupplierOrderList(String createdDateTime) throws Exception;
	 
	 public void changeSupplierOrderStatus(SupplierOrder order) throws Exception;
	 */
}