package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.Delivery;
import com.rest.model.DeliveryOrder;

@Repository
@Transactional
public class DeliveryDaoImpl implements DeliveryDao {

	@Autowired 
	SessionFactory sessionFactory;
	  
	Session session = null; 
	Transaction tx = null;
	
	@Override
	public int saveOrUpdateDelivery(Delivery delivery) throws Exception {
		// TODO Auto-generated method stub
		 int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(delivery); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag; 
	}

	@Override
	public Delivery getDeliveryById(int deliveryId) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();
		  Delivery delivery = (Delivery) session.load(Delivery.class, new Integer(deliveryId));
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return delivery; 
	}

	@Override
	public List<Delivery> getDeliveryList() throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Delivery> deliveryList = session.createCriteria(Delivery.class).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return deliveryList;
	}

	@Override
	public Delivery getDeliveryByMobileNo(long mobileNo) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();
		  tx = session.getTransaction();
		  
		  Delivery delivery = (Delivery) session.createCriteria(Delivery.class).add(Restrictions.eq("deliveryContactNo", mobileNo)).uniqueResult();
	      	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return delivery; 
	}
	
	@Override
	public int activateOrDeactivateDelivery(Delivery delivery) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update Delivery set isActive = :isActive where deliveryId = :deliveryId ")
				  			   .setParameter("isActive", delivery.getIsActive())
				  			   .setParameter("deliveryId", delivery.getDeliveryId());
		  
		  int count = query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
		  
		  return count; 
	}
	
	@Override
	public List<Delivery> deliveryListByFranchiseLocal(int localFranchiseId) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Delivery> deliveryList = session.createCriteria(Delivery.class)
				  .add(Restrictions.eq("localFranchiseId", localFranchiseId)).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return deliveryList;
	}
	
	@Override
	public List<Delivery> deliveryListByFranchiseTaluka(int talukaFranchiseId) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Delivery> deliveryList = session.createQuery("  select s.deliveryId as deliveryId, s.deliveryFullName as deliveryFullName, s.deliveryDob as deliveryDob, s.deliveryEmailId as deliveryEmailId, s.deliveryAddress as deliveryAddress, s.deliveryContactNo as deliveryContactNo, s.deliveryWhatsappNo as deliveryWhatsappNo, " + 
		  		" s.deliveryPincode as deliveryPincode, s.bankName as bankName, s.ifscCode as ifscCode, s.accountNumber as accountNumber, s.panCardNumber as panCardNumber, s.aadharCardNumber as aadharCardNumber, s.localFranchiseId as localFranchiseId, s.isActive as isActive " + 
		  		" from Delivery as s, FranchiseLocal as fl where s.localFranchiseId = fl.localFranchiseId	 and fl.talukaFranchiseId = :talukaFranchiseId ")
	  			   .setParameter("talukaFranchiseId", talukaFranchiseId).setResultTransformer(Transformers.aliasToBean(Delivery.class)).list();

		  tx.commit(); 
		  session.close();
		  
		  return deliveryList;
	}
	
	@Override
	public List<Delivery> deliveryListByFranchiseDistrict(int districtFranchiseId) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Delivery> deliveryList = session.createQuery("  select s.deliveryId as deliveryId, s.deliveryFullName as deliveryFullName, s.deliveryDob as deliveryDob, s.deliveryEmailId as deliveryEmailId, s.deliveryAddress as deliveryAddress, s.deliveryContactNo as deliveryContactNo, s.deliveryWhatsappNo as deliveryWhatsappNo, " + 
		  		" s.deliveryPincode as deliveryPincode, s.bankName as bankName, s.ifscCode as ifscCode, s.accountNumber as accountNumber, s.panCardNumber as panCardNumber, s.aadharCardNumber as aadharCardNumber, s.localFranchiseId as localFranchiseId, s.isActive as isActive " +  
		  		" from Delivery as s, FranchiseLocal as fl, FranchiseTaluka as ft where s.localFranchiseId = fl.localFranchiseId and fl.talukaFranchiseId = ft.talukaFranchiseId and ft.districtFranchiseId = :districtFranchiseId ")
	  			   .setParameter("districtFranchiseId", districtFranchiseId).setResultTransformer(Transformers.aliasToBean(Delivery.class)).list();

		  tx.commit(); 
		  session.close();
		  
		  return deliveryList;
	}
	
	@Override
	public List<DeliveryOrder> getDeliveryOrders(String todaysDate, int localFranchiseId) throws Exception {
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  		  
		  Query query = ((SQLQuery) session.createSQLQuery(" select sod.product_id as productId, p.product_title as productName, sod.order_unit as orderUnit, sod.order_quantity as orderQuantity, sod.order_amount as orderAmount, s.seller_full_name as sellerName, " + 
		  		" s.seller_address as sellerAddress, s.seller_contact_no as sellerContact, s.seller_pincode as sellerPincode, sp.supplier_full_name as supplierName, sp.supplier_address as supplierAddress, sp.supplier_contact_no as supplierContact, sp.supplier_pincode as supplierPincode " + 
		  		" from tbl_seller_order as so, tbl_seller as s, tbl_seller_order_details as sod, tbl_supplier as sp, tbl_product as p  " + 
		  		" where p.product_id = sod.product_id and sp.supplier_id = sod.supplier_id and so.mobile_number = s.seller_contact_no " + 
		  		" and so.seller_order_id = sod.seller_order_id and so.order_status = 'new' and so.order_date like :orderDate and s.local_franchise_id = :localFranchiseId ")
				  			   .setParameter("orderDate", todaysDate+"%")
				  			   .setParameter("localFranchiseId", localFranchiseId))
				    .addScalar("productId", new IntegerType())
					.addScalar("productName", new StringType())
					.addScalar("orderUnit", new StringType())
					.addScalar("orderQuantity", new IntegerType())
					.addScalar("orderAmount", new StringType())											
					.addScalar("sellerName", new StringType())
					.addScalar("sellerAddress", new StringType())
					.addScalar("sellerContact", new LongType())
					.addScalar("sellerPincode", new StringType())
					.addScalar("supplierName", new StringType())
					.addScalar("supplierAddress", new StringType())
					.addScalar("supplierContact", new LongType())
					.addScalar("supplierPincode", new StringType());
		  
		  @SuppressWarnings("unchecked")
		  List<DeliveryOrder> deliveryOrderList = (List<DeliveryOrder>) query.
		  
		  setResultTransformer(Transformers.aliasToBean(DeliveryOrder.class)).list();
		  
		  tx.commit(); 
		  session.close();
		  
		  return deliveryOrderList; 
	}
	
	@Override
	public Delivery getDeliveryByMobileAndPassword(long mobileNumber, String password) throws Exception {
		  
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.getTransaction();  
		  
		  Delivery flocal = (Delivery) session.createCriteria(Delivery.class).add(Restrictions.eq("deliveryContactNo", mobileNumber)).add(Restrictions.eq("deliveryPassword", password)).uniqueResult();
		  
		  session.beginTransaction();  
		  tx.commit();  
		  
		  return flocal;
	}
	
	
	@Override
	public int changePasswordByDeliveryMobile(Delivery delivery) throws Exception {
		
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  Query query = session.createQuery(" update Delivery set deliveryPassword= :deliveryPassword where deliveryContactNo = :deliveryContactNo ")
				    .setParameter("deliveryPassword", delivery.getDeliveryPassword())
				    .setParameter("deliveryContactNo", delivery.getDeliveryContactNo());
				   
		  int count = query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();	
		  
		  return count;		 
	}
	
	
	@Override
	public List<String> deliveryTokenByOrderSellerId(int sellerId) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<String> deliveryTokenList = session.createQuery(" SELECT d.token FROM Delivery as d, Seller as s WHERE s.localFranchiseId = d.localFranchiseId AND s.sellerId = :sellerId ")
	  			   .setParameter("sellerId", sellerId).list();

		  tx.commit(); 
		  session.close();
		  
		  return deliveryTokenList;
	}
	
	
	@Override
	public String getTokenByDeliveryId(int deliveryId) throws Exception {
		
		session    = sessionFactory.openSession();
		tx         = session.beginTransaction();
		String id     = "";
		
		Criteria criteria = session.createCriteria(Delivery.class)
				  .add(Restrictions.eq("deliveryId", deliveryId))
				  .setProjection(Projections.property("token"));
			
		if(criteria.uniqueResult() != null){
			 id     = (String) criteria.uniqueResult();
		}
		
	    tx.commit();
	    session.close();  
	   
		return  id;		
		
	}
	
	
	@Override 
	public int updateDeliveryToken(Delivery delivery) throws Exception {
		 
		  int count = 0; 
		  
			
			  session = sessionFactory.openSession(); tx = session.beginTransaction();
			  
			  Query query = session.
			  createQuery(" update Delivery set token = :token where deliveryId = :deliveryId "
			  ) .setParameter("token", delivery.getToken()) 
			  .setParameter("deliveryId",
			  delivery.getDeliveryId());
			  
			  count = query.executeUpdate();
			  
			  tx.commit(); session.close();
			 
		  
		  return count; 
	}
	
}