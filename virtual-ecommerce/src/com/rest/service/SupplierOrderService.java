package com.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.ProductSalesPrice;
import com.rest.model.SupplierOrder;
import com.rest.model.SupplierOrderDetails;
import com.rest.model.SupplierProductList;

@Component
public interface SupplierOrderService {
	
	 public void createSupplierOrder(List<SupplierOrderDetails> supplierOrderDetails) throws Exception; 

	 public List<SupplierProductList> getSupplierProductList(int supplierId) throws Exception;
	 
	 public List<SupplierOrder> getTodaysSupplierOrderList() throws Exception;
	 
	 public void changeSupplierOrderStatus(SupplierOrder order) throws Exception;
	 
	 public void removeProductFromSupplierOrderDetails(int supplierOrderDetailsId) throws Exception;
	
	 public List<SupplierOrderDetails> getTodaysSupplierOrderByMobileNo(long mobileNumber) throws Exception;
	 
	 public List<SupplierOrderDetails> getSupplierOrderById(int SupplierOrderId) throws Exception;
	 
	 public List<ProductSalesPrice> getProductSalesPriceList(int productId) throws Exception;

	/*
	 * public void saveOrUpdateSupplierOrder(SupplierOrder supplierOrder) throws
	 * Exception;
	 * 
	 * public SupplierOrder getSupplierOrderById(int supplierOrderId) throws
	 * Exception;
	 * 
	 * public List<SupplierOrder> getSupplierOrderList() throws Exception;
	 * 
	 * public void activateOrDeactivateSupplierOrder(SupplierOrder supplierOrder)
	 * throws Exception;
	 * 
	 * public List<SupplierProductList> getSupplierProductList(int supplierId)
	 * throws Exception;
	 * 
	 * public List<Supplier> getTodaysSupplierOrderList() throws Exception;
	 * 
	 * public List<SupplierOrder> getTodaysSupplierOrderList() throws Exception;
	 * 
	 * public void changeSupplierOrderStatus(SupplierOrder order) throws Exception;
	 */
}
