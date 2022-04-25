package com.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.Supplier;

@Component
public interface SupplierService {

	 public int saveOrUpdateSupplier(Supplier supplier) throws Exception; 
	 
	 public Supplier getSupplierById(int supplierId) throws Exception;  
	 
	 public List<Supplier> getSupplierList() throws Exception; 
	 
	 public Supplier getSupplierByMobileNo(long mobileNo) throws Exception;
	 
	 public int activateOrDeactivateSupplier(Supplier supplier) throws Exception;
	 
	 public List<Supplier> supplierListByFranchiseLocal(int localFranchiseId) throws Exception;
	 
	 public List<Supplier> supplierListByFranchiseTaluka(int talukaFranchiseId) throws Exception;
	 
	 public List<Supplier> supplierListByFranchiseDistrict(int districtFranchiseId) throws Exception; 
	 
	 public Supplier getSupplierByMobileAndPassword(long mobileNumber, String password) throws Exception;
}