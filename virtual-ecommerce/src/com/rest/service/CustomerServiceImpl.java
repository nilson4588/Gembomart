package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.CityDao;
import com.rest.dao.CustomerDao;
import com.rest.model.Address;
import com.rest.model.Customer;
import com.rest.utility.DateTimeUtil;

@Service
public class CustomerServiceImpl implements CustomerService {
		
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	CityDao cityDao;
	
	@Override
	public int saveOrUpdateCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		String dateTime    = DateTimeUtil.getSysDateTime();
		customer.setUpdatedDatetime(dateTime);
		customer.setCreatedDatetime(dateTime);
		
		return customerDao.saveOrUpdateCustomer(customer);
	} 

	@Override
	public Customer getCustomerById(int customerId) throws Exception {

		Customer cust = customerDao.getCustomerById(customerId);
		
		int cityId = cust.getCity();
		String cityName = cityDao.getDistrictName(cityId);
		
		int stateId = cust.getState();
		String stateName = cityDao.getStateName(stateId);
		
		int talukaId = cust.getTaluka();
		String talukaName = cityDao.getTalukaName(talukaId);
		
		cust.setStateName(stateName);
		cust.setCityName(cityName);
		cust.setTalukaName(talukaName);
		
		return cust;
	}
	
	@Override
	public Customer getCustomerByEmailAndPassword(String emailId, String password) throws Exception {
		
		Customer cust = customerDao.getCustomerByEmailAndPassword(emailId, password);
		
		int cityId = cust.getCity();
		String cityName = cityDao.getDistrictName(cityId);
		
		int stateId = cust.getState();
		String stateName = cityDao.getStateName(stateId);
		
		int talukaId = cust.getTaluka();
		String talukaName = cityDao.getTalukaName(talukaId);
		
		cust.setStateName(stateName);
		cust.setCityName(cityName);
		cust.setTalukaName(talukaName);
		
		return cust;
	}
	
	public Customer getCustomerByMobileAndPassword(String mobileno, String password) throws Exception {
		
		Customer cust = customerDao.getCustomerByMobileAndPassword(mobileno, password);
		
		int cityId = cust.getCity();
		String cityName = cityDao.getDistrictName(cityId);
		
		int stateId = cust.getState();
		String stateName = cityDao.getStateName(stateId);
		
		int talukaId = cust.getTaluka();
		String talukaName = cityDao.getTalukaName(talukaId);
		
		cust.setStateName(stateName);
		cust.setCityName(cityName);
		cust.setTalukaName(talukaName);
		
		return cust;
	}
	
	public Customer getCustomerByMobileNo(String mobileNo) throws Exception {
		
		Customer cust = customerDao.getCustomerByMobileNo(mobileNo);
		
		 if(cust != null) {
			 int cityId = cust.getCity();
				String cityName = cityDao.getDistrictName(cityId);
				
				int stateId = cust.getState();
				String stateName = cityDao.getStateName(stateId);
				
				int talukaId = cust.getTaluka();
				String talukaName = cityDao.getTalukaName(talukaId);
				
				cust.setStateName(stateName);
				cust.setCityName(cityName);
				cust.setTalukaName(talukaName);
		 }
		
		return cust; 
	}

	@Override
	public List<Customer> getCustomerList() throws Exception {
		// TODO Auto-generated method stub
		return customerDao.getCustomerList();
	}

	@Override
	public int activateOrDeactivateCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		String dateTime    = DateTimeUtil.getSysDateTime();
		customer.setUpdatedDatetime(dateTime);
		return customerDao.activateOrDeactivateCustomer(customer);
	}
	
	@Override
	public int changePasswordByMobileNumber(Customer customer) throws Exception {
		String dateTime    = DateTimeUtil.getSysDateTime();
		customer.setUpdatedDatetime(dateTime);
		return customerDao.changePasswordByMobileNumber(customer);
	}
	
	@Override
	public String getTokenByCustomerMobileNo(String mobileno) throws Exception {
		return customerDao.getTokenByCustomerMobileNo(mobileno);
	}
	
	@Override
	public int updateCustomerToken(Customer customer) throws Exception {
		return customerDao.updateCustomerToken(customer);
	}
	
	@Override
	public int addOrUpdateAddress(Address address) throws Exception {
		return customerDao.addOrUpdateAddress(address);
	}
	
	@Override
	public List<Address> getAddressList(int customerId) throws Exception {
				
		List<Address> addressList = customerDao.getAddressList(customerId);
		List<Address> addressList1 = new ArrayList<Address>();
		
		for (Address addr : addressList) {
			
			int cityId = addr.getDistrictCode();
			String cityName = cityDao.getDistrictName(cityId);
			
			int stateId = addr.getStateCode();
			String stateName = cityDao.getStateName(stateId);
			
			int talukaId = addr.getAreaCode();
			String talukaName = cityDao.getTalukaName(talukaId);
			
			addr.setStateName(stateName);
			addr.setCityName(cityName);
			addr.setAreaName(talukaName);
			
			addressList1.add(addr);
		}		
		
		return addressList1;
	}
	
	@Override
	public int removeAddress(int addressId) throws Exception {
		return customerDao.removeAddress(addressId);
	}
	
	@Override
	public Address getAddressById(int addressId) throws Exception {
		
		Address addr = customerDao.getAddressById(addressId);
		
		int cityId = addr.getDistrictCode();
		String cityName = cityDao.getDistrictName(cityId);
		
		int stateId = addr.getStateCode();
		String stateName = cityDao.getStateName(stateId);
		
		int talukaId = addr.getAreaCode();
		String talukaName = cityDao.getTalukaName(talukaId);
		
		addr.setStateName(stateName);
		addr.setCityName(cityName);
		addr.setAreaName(talukaName);
				
		return addr;
	}
}