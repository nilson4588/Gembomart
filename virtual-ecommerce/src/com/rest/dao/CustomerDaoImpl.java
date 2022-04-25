package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.Address;
import com.rest.model.Customer;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired  
	SessionFactory sessionFactory;  
	  
	Session session = null;  
	Transaction tx = null;  
	
	@Override
	public int saveOrUpdateCustomer(Customer customer) throws Exception {
		  // TODO Auto-generated method stub		
		  int successflag = 0;
		
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();  
		  session.saveOrUpdate(customer);  
		  tx.commit();  
		  session.close();  
		  
		  successflag = 1;
		  
		  return successflag;
	}
	
	@Override
	public int addOrUpdateAddress(Address address) throws Exception {
		  // TODO Auto-generated method stub		
		  int successflag = 0;
		
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();  
		  session.saveOrUpdate(address);  
		  tx.commit();  
		  session.close();  
		  
		  successflag = 1;
		  
		  return successflag;
	}
	
	@Override
	public List<Address> getAddressList(int customerId) throws Exception {
		
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();	
		  
		  @SuppressWarnings("unchecked")
		  List<Address> addressList = session.createCriteria(Address.class).add(Restrictions.eq("customerId", customerId)).list();
		  
		  tx.commit();  
		  session.close();  
		  
		  return addressList;  
	}
	
	
	@Override
	public int removeAddress(int addressId) throws Exception {
		  // TODO Auto-generated method stub		
		  int count = 0;
		
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();  
		  
		  Query query = session.createQuery(" delete from Address where addressId = :addressId ")
				    .setParameter("addressId", addressId);
				   
		  count = query.executeUpdate();
		  
		  tx.commit();  
		  session.close();  
		  
		  return count;
	}
	
	@Override
	public Customer getCustomerById(int customerId) throws Exception {
		
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  Customer customer = (Customer) session.load(Customer.class, new Integer(customerId));  
		  tx = session.getTransaction();  
		  session.beginTransaction();  
		  tx.commit();  
		  return customer;
	}
	
	@Override
	public Customer getCustomerByEmailAndPassword(String emailId, String password) throws Exception {
		  
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.getTransaction();  
		  
		  Customer customer = (Customer) session.createCriteria(Customer.class).add(Restrictions.eq("email", emailId)).add(Restrictions.eq("pwd", password)).uniqueResult();
		  
		  session.beginTransaction();  
		  tx.commit();  
		  
		  return customer;
	}
	
	
	@Override
	public Customer getCustomerByMobileAndPassword(String mobileno, String password) throws Exception {
		  
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.getTransaction();  
		  
		  Customer customer = (Customer) session.createCriteria(Customer.class).add(Restrictions.eq("mobileno", mobileno)).add(Restrictions.eq("pwd", password)).uniqueResult();
		  
		  session.beginTransaction();  
		  tx.commit();  
		  
		  return customer;
	}
	
	
	@Override 
	public Customer getCustomerByMobileNo(String mobileNo) throws Exception {
		
		  // TODO Auto-generated method stub 
		  session = sessionFactory.openSession();
		  tx = session.getTransaction();
		  
		  Customer customer = (Customer) session.createCriteria(Customer.class).add(Restrictions.eq("mobileno", mobileNo)).uniqueResult();
	    	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return customer; 
	}
	
	@Override
	public List<Customer> getCustomerList() throws Exception {
		
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();	
		  
		  @SuppressWarnings("unchecked")
		  List<Customer> customerList = session.createCriteria(Customer.class).addOrder(Order.desc("id")).list();
		  
		  tx.commit();  
		  session.close();  
		  
		  return customerList;  
	}
	
	@Override
	public int activateOrDeactivateCustomer(Customer customer) throws Exception {
		
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  Query query = session.createQuery(" update Customer set isActive= :isActive, updatedDatetime = :updatedDateTime where id = :customerId ")
				    .setParameter("isActive", customer.getIsActive())
				    .setParameter("updatedDateTime", customer.getUpdatedDatetime())
				    .setParameter("customerId", customer.getId());
				   
		  int count = query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();	
		  
		  return count;		 
	}
		
	@Override
	public int changePasswordByMobileNumber(Customer customer) throws Exception {
		
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  Query query = session.createQuery(" update Customer set pwd = :pwd, updatedDatetime = :updatedDateTime where mobileno = :mobileno ")
				    .setParameter("pwd", customer.getPwd())
				    .setParameter("updatedDateTime", customer.getUpdatedDatetime())
				    .setParameter("mobileno", customer.getMobileno());
				   
		  int count = query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();	
		  
		  return count;		 
	}
	
	@Override
	public String getTokenByCustomerMobileNo(String mobileno) throws Exception {
		
		session    = sessionFactory.openSession();
		tx         = session.beginTransaction();
		String id     = "";
		
		Criteria criteria = session.createCriteria(Customer.class)
				  .add(Restrictions.eq("mobileno", mobileno))
				  .setProjection(Projections.property("token"));
			
		if(criteria.uniqueResult() != null){
			 id     = (String) criteria.uniqueResult();
		}
		
	    tx.commit();
	    session.close();  
	   
		return  id;		
		
	}
	
	@Override 
	public int updateCustomerToken(Customer customer) throws Exception {
		
		  int count = 0; 
					
			  session = sessionFactory.openSession(); tx = session.beginTransaction();
			  
			  Query query = session.
			  createQuery(" update Customer set token = :token where id = :customerId ") 
			  .setParameter("token", customer.getToken()) 
			  .setParameter("customerId", customer.getId());
			  
			  count = query.executeUpdate();
			  
			  tx.commit(); session.close();
		  
		  return count; 
	}
	
	
	@Override
	public Address getAddressById(int addressId) throws Exception {
		
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();	
		  
		  Address address = (Address) session.createCriteria(Address.class).add(Restrictions.eq("addressId", addressId)).uniqueResult();
		  
		  tx.commit();  
		  session.close();  
		  
		  return address;  
	}
}