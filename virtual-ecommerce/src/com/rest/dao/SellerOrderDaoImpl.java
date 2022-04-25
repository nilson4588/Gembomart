package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.Comission;
import com.rest.model.SellerOrder;
import com.rest.model.SellerOrderDetails;
import com.rest.model.SellerProductList;
import com.rest.utility.DateTimeUtil;

@Repository
@Transactional
public class SellerOrderDaoImpl implements SellerOrderDao {

	@Autowired 
	SessionFactory sessionFactory;
	  
	Session session = null; 
	Transaction tx = null;
	
	@Override
	public void saveOrUpdateSellerOrder(SellerOrder sellerOrder) throws Exception {
		
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(sellerOrder); 
		  
		  tx.commit(); 
		  session.close();	
	}
		
	@Override
	public void saveOrUpdateSellerOrderDetails(SellerOrderDetails sellerOrderDetails) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(sellerOrderDetails); 
		  
		  tx.commit(); 
		  session.close();	
	}
	
	@Override
	public void updateSellerOrderTotalAmount(int sellerOrderId, String totalAmount) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update SellerOrder set totalAmount = :totalAmount where sellerOrderId = :sellerOrderId ")
				  			   .setParameter("totalAmount", totalAmount)
				  			   .setParameter("sellerOrderId", sellerOrderId);
		  
		  query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
	}
	
	@Override
	public List<SellerProductList> getSellerProductList() throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
			/*
			 * CAST(CONCAT(tp.product_title,' - ', tpp.seller_price,' per ',
			 * tpp.seller_unit) as char(255))
			 */
		  
		  String sql = " select CAST(CONCAT(tp.product_id,'$',tpp.seller_price,'$',tpp.seller_unit) as char(50)) as productId, tp.product_title as productName " + 
		  		" from tbl_product tp, tbl_product_price tpp where tp.product_id = tpp.product_id and tpp.is_active = 1 ";
		  
		  @SuppressWarnings("unchecked")
		  List<SellerProductList> sellerProudctList = (List<SellerProductList>) session.createSQLQuery(sql)											
											.setResultTransformer(Transformers.aliasToBean(SellerProductList.class))
											.list() ;
		
		  tx.commit();  
		  session.close();	
		  
		  return sellerProudctList;
	}	
	
	@Override
	public List<SellerOrder> getTodaysSellerOrderList(String createdDateTime) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  		  
		  Criteria criteria = session.createCriteria(SellerOrder.class).add(Restrictions.ilike("orderDate", createdDateTime, MatchMode.START));	
							  
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerOrderList = (List<SellerOrder>) criteria.addOrder(org.hibernate.criterion.Order.desc("sellerOrderId")).list();
		  
		  tx.commit();  
		  session.close(); 
		  
		  return sellerOrderList; 
	}
	
	@Override
	public List<SellerOrder> getTodaysSellerOrderListByMobileNumber(String createdDateTime, long mobileNumber) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  		  
		  Criteria criteria = session.createCriteria(SellerOrder.class).add(Restrictions.ilike("orderDate", createdDateTime, MatchMode.START))
				  .add(Restrictions.eq("mobileNumber", mobileNumber));	
							  
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerOrderList = (List<SellerOrder>) criteria.addOrder(org.hibernate.criterion.Order.desc("sellerOrderId")).list();
		  
		  tx.commit();  
		  session.close(); 
		  
		  return sellerOrderList; 
	}
	
	@Override
	public List<SellerOrderDetails> getSellerOrderDetailsListBySellerOrderId(int sellerOrderId) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  
		  @SuppressWarnings("unchecked")
		  List<SellerOrderDetails> sellerOrderDetailsList = (List<SellerOrderDetails>) session.createCriteria(SellerOrderDetails.class)
															  .add(Restrictions.eq("sellerOrderId", sellerOrderId))
															  .add(Restrictions.eq("isActive", 1))
															  .list(); 
		  tx.commit();  
		  session.close();
		  
		  return sellerOrderDetailsList;  		  
	}
	
	@Override
	public void removeProductFromSellerOrderDetails(int sellerOrderDetailsId) throws Exception {
		
		 session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  Query query = session.createQuery(" update SellerOrderDetails set isActive = :isActive where sellerOrderDetailsId = :sellerOrderDetailsId ")
				    .setParameter("isActive", 0)		            
				    .setParameter("sellerOrderDetailsId", sellerOrderDetailsId);
				   
		  query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();
	}
	
	@Override
	public void changeSellerOrderStatus(SellerOrder order) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  String orderStatus = order.getOrderStatus();
		  String sql = "";
		  if(orderStatus.equals("Shipped")) {
			  sql = "update SellerOrder set orderStatus = :orderStatus, shippedDate = :tdate where sellerOrderId = :sellerOrderId  ";
		  } else if(orderStatus.equals("Delivered")) {
			  sql = "update SellerOrder set orderStatus = :orderStatus, deliveredDate = :tdate where sellerOrderId = :sellerOrderId  ";
		  } else if(orderStatus.equals("Canceled")) {
			  sql = "update SellerOrder set orderStatus = :orderStatus, canceledDate = :tdate where sellerOrderId = :sellerOrderId  ";
		  }
		  
		  Query query = session.createQuery(sql)
				    .setParameter("orderStatus", order.getOrderStatus())
				    .setParameter("tdate", DateTimeUtil.getSysDateTime())
				    .setParameter("sellerOrderId", order.getSellerOrderId());
				   
		  query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();	
	}

	
	@Override
	public SellerOrder getSellerOrderById(int sellerOrderId) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();
		  SellerOrder sellerOrder = (SellerOrder) session.load(SellerOrder.class, new Integer(sellerOrderId));
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return sellerOrder;
	}
		
	@Override
	public SellerOrderDetails getSellerOrderDetailsById(int sellerOrderDetailsId) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();
		  SellerOrderDetails sellerOrderDetails = (SellerOrderDetails) session.load(SellerOrderDetails.class, new Integer(sellerOrderDetailsId));
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return sellerOrderDetails;
	}
	
	
	@Override
	public void saveOrUpdateComission(Comission comission) throws Exception {
		
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(comission); 
		  
		  tx.commit(); 
		  session.close();	
	}
	
	@Override
	public List<SellerOrder> getSellerOrderLocalFranchiseComission(int localFranchiseId) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 		  
		  String sql = " select s.seller_full_name as sellerName, so.seller_order_id as sellerOrderId, so.total_amount as totalAmount, c.franchise_local as franchiseLocalComission " + 
		  		"	from tbl_seller_order as so, tbl_seller as s, tbl_comission as c where so.mobile_number = s.seller_contact_no " + 
		  		"	and so.seller_order_id = c.seller_order_id 	and s.local_franchise_id = :localFranchiseId ";
		  
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerProudctList = (List<SellerOrder>) session.createSQLQuery(sql)
		  									.setInteger("localFranchiseId", localFranchiseId)
											.setResultTransformer(Transformers.aliasToBean(SellerOrder.class))
											.list() ;
		
		  tx.commit();  
		  session.close();	
		  
		  return sellerProudctList;
	}	
	
	@Override
	public List<SellerOrder> getSellerOrderTalukaFranchiseComission(int talukaFranchiseId) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 		  
		  String sql = " select s.seller_full_name as sellerName, so.seller_order_id as sellerOrderId, so.total_amount as totalAmount, c.franchise_taluka as franchiseTalukaComission " + 
		  		" from tbl_seller_order as so, tbl_seller as s, tbl_comission as c, tbl_franchise_local as fl where so.mobile_number = s.seller_contact_no  " + 
		  		" and so.seller_order_id = c.seller_order_id and fl.local_franchise_id = s.local_franchise_id and fl.taluka_franchise_id = :talukaFranchiseId ";
		  
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerProudctList = (List<SellerOrder>) session.createSQLQuery(sql)
		  									.setInteger("talukaFranchiseId", talukaFranchiseId)
											.setResultTransformer(Transformers.aliasToBean(SellerOrder.class))
											.list() ;
		
		  tx.commit();  
		  session.close();	
		  
		  return sellerProudctList;
	}	
	
	@Override
	public List<SellerOrder> getSellerOrderDistrictFranchiseComission(int districtFranchiseId) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 		  
		  String sql = " select s.seller_full_name as sellerName, so.seller_order_id as sellerOrderId, so.total_amount as totalAmount, c.franchise_district as franchiseDistrictComission " + 
		  		"from tbl_seller_order as so, tbl_seller as s, tbl_comission as c, tbl_franchise_local as fl, tbl_franchise_taluka as ft " + 
		  		"where so.mobile_number = s.seller_contact_no and so.seller_order_id = c.seller_order_id and fl.local_franchise_id = s.local_franchise_id " + 
		  		"and ft.taluka_franchise_id = fl.taluka_franchise_id and ft.district_franchise_id = :districtFranchiseId ";
		  
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerProudctList = (List<SellerOrder>) session.createSQLQuery(sql)
		  									.setInteger("districtFranchiseId", districtFranchiseId)
											.setResultTransformer(Transformers.aliasToBean(SellerOrder.class))
											.list() ;
		
		  tx.commit();  
		  session.close();	
		  
		  return sellerProudctList;
	}
	
	/******************  Old
	
	@Override
	public List<SellerOrder> getSellerOrderList() throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<SellerOrder> sellerOrderList = session.createCriteria(SellerOrder.class).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return sellerOrderList;
	}

	@Override
	public void activateOrDeactivateSellerOrder(SellerOrder sellerOrder) throws Exception {
		// TODO Auto-generated method stub
	  	  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update SellerOrder set isActive = :isActive where sellerOrderId = :sellerOrderId ")
				  			   .setParameter("isActive", sellerOrder.getIsActive())
				  			   .setParameter("sellerOrderId", sellerOrder.getSellerOrderId());
		  
		  query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
	}
	
	@Override
	public List<SellerOrder> getSellerOrderByMobile(long mobileNumber, String date) throws Exception {
		  
		  session = sessionFactory.openSession();
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerOrder = (List<SellerOrder>) session.createCriteria(SellerOrder.class)
										  .add(Restrictions.eq("mobileNumber", mobileNumber))
										  .add(Restrictions.like("orderDate", date, MatchMode.START))
										  .list();
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return sellerOrder; 
	}
	
	@Override
	public List<SellerOrder> getTodaysSellerOrderList(String createdDateTime) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  		  
		  Criteria criteria = session.createCriteria(SellerOrder.class).add(Restrictions.ilike("orderDate", createdDateTime, MatchMode.START));	
							  
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerOrderList = (List<SellerOrder>) criteria.addOrder(org.hibernate.criterion.Order.desc("sellerOrderId")).list();
		  
		  tx.commit();  
		  session.close(); 
		  
		  return sellerOrderList; 
	}
	
	
	 *******************/ 
}