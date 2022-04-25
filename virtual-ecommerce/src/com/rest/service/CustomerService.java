package com.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.Address;
import com.rest.model.Customer;

@Component
public interface CustomerService {
	
 	 public int saveOrUpdateCustomer(Customer customer) throws Exception; 
	 
	 public Customer getCustomerById(int customerId) throws Exception;  
	 
	 public Customer getCustomerByEmailAndPassword(String emailId, String password) throws Exception;
	 
	 public Customer getCustomerByMobileAndPassword(String mobileno, String password) throws Exception;
	 
	 public List<Customer> getCustomerList() throws Exception; 
	 
	 public Customer getCustomerByMobileNo(String mobileNo) throws Exception;  
	 
	 public int activateOrDeactivateCustomer(Customer customer) throws Exception;
	 
	 public int changePasswordByMobileNumber(Customer customer) throws Exception;
	 
	 public String getTokenByCustomerMobileNo(String mobileno) throws Exception;
	 
	 public int updateCustomerToken(Customer customer) throws Exception; 
	 
	 public int addOrUpdateAddress(Address address) throws Exception;
	 
	 public List<Address> getAddressList(int customerId) throws Exception;
	 
	 public int removeAddress(int addressId) throws Exception; 
	 
	 public Address getAddressById(int addressId) throws Exception; 
}
