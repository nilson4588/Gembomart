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

import com.rest.model.ProductSalesPrice;
import com.rest.model.SupplierOrder;
import com.rest.model.SupplierOrderDetails;
import com.rest.model.SupplierProductList;
import com.rest.utility.DateTimeUtil;

@Repository
@Transactional
public class SupplierOrderDaoImpl implements SupplierOrderDao {

	@Autowired 
	SessionFactory sessionFactory;
	  
	Session session = null; 
	Transaction tx = null;
	
	@Override
	public void saveOrUpdateSupplierOrder(SupplierOrder supplierOrder) throws Exception {
		// TODO Auto-generated method stub
		  		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(supplierOrder); 
		  
		  tx.commit(); 
		  session.close();		 
	}
	
	@Override
	public void saveOrUpdateSupplierOrderDetails(SupplierOrderDetails supplierOrderDetails) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(supplierOrderDetails); 
		  
		  tx.commit(); 
		  session.close();	
	}
	
	
	@Override
	public void updateSupplierOrderTotalAmount(int supplierOrderId, String totalAmount) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update SupplierOrder set totalAmount = :totalAmount where supplierOrderId = :supplierOrderId ")
				  			   .setParameter("totalAmount", totalAmount)
				  			   .setParameter("supplierOrderId", supplierOrderId);
		  
		  query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
	}
	
	
	@Override
	public List<SupplierProductList> getSupplierProductList(int supplierId) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  String sql = " select CAST(CONCAT(tp.product_id,'$',tpp.supplier_price,'$',tpp.supplier_unit) as char(50)) as productId, tp.product_title as productName " + 
		  		" from tbl_product tp, tbl_product_price tpp where tp.product_id = tpp.product_id " + 
		  		" and FIND_IN_SET(tp.product_id, (select replace( TRIM(LEADING '[' FROM (TRIM(TRAILING ']' FROM supplier_products))),', ',',' ) " + 
		  		" from tbl_supplier where supplier_id = :supplierId))>0 and tpp.is_active = 1 ";
		  
			/*
			 * CAST(CONCAT(tp.product_title,' - ', tpp.supplier_price,' per ',
			 * tpp.supplier_unit) as char(255))
			 */
		  
		  @SuppressWarnings("unchecked")
		  List<SupplierProductList> spplierProudctList = (List<SupplierProductList>) session.createSQLQuery(sql)
											.setParameter("supplierId", supplierId)
											.setResultTransformer(Transformers.aliasToBean(SupplierProductList.class))
											.list() ;
		
		  tx.commit();  
		  session.close();	
		  
		  return spplierProudctList;
	}
	
	@Override
	public List<SupplierOrder> getTodaysSupplierOrderList(String createdDateTime) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  		  
		  Criteria criteria = session.createCriteria(SupplierOrder.class).add(Restrictions.ilike("orderDate", createdDateTime, MatchMode.START));	
							  
		  @SuppressWarnings("unchecked")
		  List<SupplierOrder> supplierOrderList = (List<SupplierOrder>) criteria.addOrder(org.hibernate.criterion.Order.desc("supplierOrderId")).list();
		  
		  tx.commit();  
		  session.close(); 
		  
		  return supplierOrderList; 
	}
	
	@Override
	public List<SupplierOrder> getTodaysSupplierOrderListByMobileNumber(String createdDateTime, long mobileNumber) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  		  
		  Criteria criteria = session.createCriteria(SupplierOrder.class).add(Restrictions.ilike("orderDate", createdDateTime, MatchMode.START))
				  .add(Restrictions.eq("mobileNumber", mobileNumber));	
							  
		  @SuppressWarnings("unchecked")
		  List<SupplierOrder> supplierOrderList = (List<SupplierOrder>) criteria.addOrder(org.hibernate.criterion.Order.desc("supplierOrderId")).list();
		  
		  tx.commit();  
		  session.close(); 
		  
		  return supplierOrderList; 
	}
	
	@Override
	public List<SupplierOrderDetails> getSupplierOrderDetailsListBySellerOrderId(int supplierOrderId) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  
		  @SuppressWarnings("unchecked")
		  List<SupplierOrderDetails> supplierDetailsList = (List<SupplierOrderDetails>) session.createCriteria(SupplierOrderDetails.class)
															  .add(Restrictions.eq("supplierOrderId", supplierOrderId))
															  .add(Restrictions.eq("isActive", 1))
															  .list(); 
		  tx.commit();  
		  session.close();
		  
		  return supplierDetailsList;  		  
	}
	
	
	@Override
	public void removeProductFromSupplierOrderDetails(int supplierOrderDetailsId) throws Exception {
		
		 session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  Query query = session.createQuery(" update SupplierOrderDetails set isActive = :isActive where supplierOrderDetailsId = :supplierOrderDetailsId ")
				    .setParameter("isActive", 0)		            
				    .setParameter("supplierOrderDetailsId", supplierOrderDetailsId);
				   
		  query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();
	}
	
	
	@Override
	public void changeSupplierOrderStatus(SupplierOrder order) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  String orderStatus = order.getOrderStatus();
		  String sql = "";
		  if(orderStatus.equals("Shipped")) {
			  sql = "update SupplierOrder set orderStatus = :orderStatus, shippedDate = :tdate where supplierOrderId = :supplierOrderId  ";
		  } else if(orderStatus.equals("Delivered")) {
			  sql = "update SupplierOrder set orderStatus = :orderStatus, deliveredDate = :tdate where supplierOrderId = :supplierOrderId  ";
		  } else if(orderStatus.equals("Canceled")) {
			  sql = "update SupplierOrder set orderStatus = :orderStatus, canceledDate = :tdate where supplierOrderId = :supplierOrderId  ";
		  }
		  
		  Query query = session.createQuery(sql)
				    .setParameter("orderStatus", order.getOrderStatus())
				    .setParameter("tdate", DateTimeUtil.getSysDateTime())
				    .setParameter("supplierOrderId", order.getSupplierOrderId());
				   
		  query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();	
	}
	
	@Override
	public SupplierOrder getSupplierOrderById(int supplierOrderId) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();
		  SupplierOrder supplierOrder = (SupplierOrder) session.load(SupplierOrder.class, new Integer(supplierOrderId));
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return supplierOrder; 
	}

	
	@Override
	public SupplierOrderDetails getSupplierOrderDetailsById(int supplierOrderDetailsId) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();
		  SupplierOrderDetails supplierOrderDetails = (SupplierOrderDetails) session.load(SupplierOrderDetails.class, new Integer(supplierOrderDetailsId));
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return supplierOrderDetails;
	}
	
	@Override
	public List<ProductSalesPrice> getProductSalesPriceList(int productId, String todaysDate) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  String sql = " SELECT s.supplier_id as supplierId, s.supplier_full_name as supplierName, " + 
		  		" sod.product_id as productId, sod.product_rate as productRate, sod.sale_rate as saleRate " + 
		  		" from tbl_supplier_order as sd, tbl_supplier_order_details as sod, tbl_supplier as s  " + 
		  		" where sd.supplier_order_id = sod.supplier_order_id and s.supplier_contact_no = sd.mobile_number " + 
		  		" and mobile_number IN ( select supplier_contact_no from tbl_supplier where FIND_IN_SET(:productId, replace(TRIM(LEADING '[' FROM (TRIM(TRAILING ']' FROM supplier_products))),', ',',')) ) " + 
		  		" and sod.product_id = :productId and sd.order_date like :todaysDate ORDER BY sod.product_rate LIMIT 5  ";
		 		  
		  @SuppressWarnings("unchecked")
		  List<ProductSalesPrice> spplierProudctList = (List<ProductSalesPrice>) session.createSQLQuery(sql)
											.setParameter("productId", productId)
											.setParameter("todaysDate", todaysDate+'%')
											.setResultTransformer(Transformers.aliasToBean(ProductSalesPrice.class))
											.list() ;
		
		  tx.commit();  
		  session.close();	
		  
		  return spplierProudctList;
	}
	
	
	/*
	 * @Override public List<SupplierOrder> getSupplierOrderList() throws Exception
	 * { // TODO Auto-generated method stub session = sessionFactory.openSession();
	 * tx = session.beginTransaction();
	 * 
	 * @SuppressWarnings("unchecked") List<SupplierOrder> supplierOrderList =
	 * session.createCriteria(SupplierOrder.class).list();
	 * 
	 * tx.commit(); session.close();
	 * 
	 * return supplierOrderList; }
	 * 
	 * @Override public void activateOrDeactivateSupplierOrder(SupplierOrder
	 * supplierOrder) throws Exception { // TODO Auto-generated method stub session
	 * = sessionFactory.openSession(); tx = session.beginTransaction();
	 * 
	 * Query query = session.
	 * createQuery(" update SupplierOrder set isActive = :isActive where supplierOrderId = :supplierOrderId "
	 * ) .setParameter("isActive", supplierOrder.getIsActive())
	 * .setParameter("supplierOrderId", supplierOrder.getSupplierOrderId());
	 * 
	 * query.executeUpdate();
	 * 
	 * tx.commit(); session.close(); }
	 * 
	 * 
	 * 
	 * @Override public List<SupplierOrder> getSupplierOrderByMobile(long
	 * mobileNumber, String date) throws Exception {
	 * 
	 * session = sessionFactory.openSession(); tx = session.getTransaction();
	 * 
	 * Criteria criteria = session.createCriteria(SupplierOrder.class);
	 * if(mobileNumber != 0){ criteria.add(Restrictions.eq("mobileNumber",
	 * mobileNumber)); } if(!date.equals("")) {
	 * criteria.add(Restrictions.like("orderDate", date, MatchMode.START)); }
	 * 
	 * @SuppressWarnings("unchecked") List<SupplierOrder> supplierOrder =
	 * criteria.list();
	 * 
	 * session.beginTransaction(); tx.commit();
	 * 
	 * return supplierOrder; }
	 */	
}