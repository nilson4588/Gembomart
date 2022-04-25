package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.Order;
import com.rest.model.OrderDetails;
import com.rest.utility.DateTimeUtil;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

	@Autowired  
	SessionFactory sessionFactory;  
		  
	Session session = null;  
	Transaction tx = null;  
	
	@Override
	public void saveOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();  
		  
		  session.save(order);
		  
		  tx.commit();  
		  session.close(); 
	}

	@Override
	public void saveOrderDetails(OrderDetails orderDetails) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();  
		  
		  session.save(orderDetails);
		  
		  tx.commit();  
		  session.close(); 
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();  
		  
		  session.update(order);
		  
		  tx.commit();  
		  session.close(); 
	}

	@Override
	public void removeOrderDetails(int orderId) throws Exception {
		  // TODO Auto-generated method stub
		 session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  Query query = session.createQuery(" delete OrderDetails where orderId = :orderId ")
				    .setParameter("orderId", orderId);
				   
		  query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();	
	}

	@Override
	public Order getOrderById(int orderId) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  
		  Order order = (Order) session.createCriteria(Order.class).add(Restrictions.eq("orderId", orderId)).uniqueResult(); 
		  
		  tx.commit();  
		  session.close(); 
		  
		  return order;  
	}
	

	@Override
	public List<Order> getOrderList(Order order) throws Exception {
		  // TODO Auto-generated method stub
		 
		  String orderStatus = order.getOrderStatus();
		  String orderDate   = order.getOrderDate();
		  long   mobileNo    = order.getMobileNo();
		  int    deliveryBoyId = order.getDeliveryBoyId();
		
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  		  
		  Criteria criteria = session.createCriteria(Order.class);
		           
		           if(orderStatus.length() != 0) {
		        	   		        	   
		        	   if(deliveryBoyId>0 && orderStatus.equals("Assigned")) {
		        		   
		        		   Disjunction or = Restrictions.disjunction();
		        		   or.add(Restrictions.eq("orderStatus","Assigned"));
		        		   or.add(Restrictions.eq("orderStatus","Ready To Ship"));
		        		 		        		   
		        		   criteria.add(or);
		        	   }
		        	   else if(deliveryBoyId>0 && orderStatus.equals("Shipped")) {
		        		   
		        		   Disjunction or = Restrictions.disjunction();
		        		   or.add(Restrictions.eq("orderStatus","Shipped"));
		        		   or.add(Restrictions.eq("orderStatus","Verified"));
		        		 		        		   
		        		   criteria.add(or);
		        	   }
		        	   else {
		        		   criteria.add(Restrictions.eq("orderStatus", orderStatus));
		        	   }
		        	   
		        	   
		           }
			       if(mobileNo != 0) {
						criteria.add(Restrictions.eq("mobileNo", mobileNo));	
			       }		
				   if(orderDate.length() != 0 ) {
						criteria.add(Restrictions.ilike("orderDate", orderDate, MatchMode.START));	
				   }
				   if(deliveryBoyId != 0) {
						criteria.add(Restrictions.eq("deliveryBoyId", deliveryBoyId));	
			       }
		  
		  @SuppressWarnings("unchecked")
		  List<Order> OrderList = (List<Order>) criteria.addOrder(org.hibernate.criterion.Order.desc("orderId")).list();
		  
		  tx.commit();  
		  session.close(); 
		  
		  return OrderList;  
	}
		
	@Override
	public List<OrderDetails> getOrderDetailsListByOrderId(int orderId, int sellerId) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  
		  @SuppressWarnings("unchecked")
		  List<OrderDetails> OrderDetailsList = (List<OrderDetails>) session.createCriteria(OrderDetails.class)
		                .add(Restrictions.eq("orderId", orderId))
		                .add(Restrictions.eq("sellerId", sellerId)).list();  
		  		  
		  tx.commit();  
		  session.close();
		  
		  return OrderDetailsList;  		  
	}
	
	

	@Override
	public void changeOrderStatus(Order order) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  String orderStatus = order.getOrderStatus();
		  String sql = "";
		  Query query = null;
		  if(orderStatus.equals("Shipped")) {
			  sql = "update Order set orderStatus = :orderStatus, shippedDate = :tdate, deliveryBoyId = :deliveryBoyId where orderId = :orderId ";
		      			  
				 query = session.createQuery(sql)
						    .setParameter("orderStatus", order.getOrderStatus())
						    .setParameter("tdate", DateTimeUtil.getSysDateTime())
						    .setParameter("deliveryBoyId", order.getDeliveryBoyId())
						    .setParameter("orderId", order.getOrderId());
		  
		  } 		  
		  else if(orderStatus.equals("Verified")) {
			  
			  sql = "update Order set orderStatus = :orderStatus, shippedDate = :tdate, deliveryBoyId = :deliveryBoyId where orderId = :orderId ";
		      			  
				 query = session.createQuery(sql)
						    .setParameter("orderStatus", order.getOrderStatus())
						    .setParameter("tdate", DateTimeUtil.getSysDateTime())
						    .setParameter("deliveryBoyId", order.getDeliveryBoyId())
						    .setParameter("orderId", order.getOrderId());
		  
		  }
		  else if(orderStatus.equals("Delivered") && (order.getPaymentId().length() == 0)) {
			  sql = "update Order set orderStatus = :orderStatus, deliveredDate = :tdate, deliveryBoyId = :deliveryBoyId where orderId = :orderId ";
		  
			  query = session.createQuery(sql)
					    .setParameter("orderStatus", order.getOrderStatus())
					    .setParameter("tdate", DateTimeUtil.getSysDateTime())
					    .setParameter("deliveryBoyId", order.getDeliveryBoyId())
					    .setParameter("orderId", order.getOrderId());
	  			  
		  } 
		  else if(orderStatus.equals("Delivered") && (order.getPaymentId().length() > 0)) {
			  sql = "update Order set orderStatus = :orderStatus, deliveredDate = :tdate, deliveryBoyId = :deliveryBoyId, paymentId = :paymentId where orderId = :orderId ";
		  
			  query = session.createQuery(sql)
					    .setParameter("orderStatus", order.getOrderStatus())
					    .setParameter("tdate", DateTimeUtil.getSysDateTime())
					    .setParameter("deliveryBoyId", order.getDeliveryBoyId())
					    .setParameter("paymentId", order.getPaymentId())
					    .setParameter("orderId", order.getOrderId());
		  
		  }		  
		  else if(orderStatus.equals("Canceled")) {
			  sql = "update Order set orderStatus = :orderStatus, canceledDate = :tdate where orderId = :orderId ";
		 
			  query = session.createQuery(sql)
					    .setParameter("orderStatus", order.getOrderStatus())
					    .setParameter("tdate", DateTimeUtil.getSysDateTime())					    
					    .setParameter("orderId", order.getOrderId());
	  		 		  
		  }
		  else if(orderStatus.equals("Assigned")) {
			  sql = "update Order set orderStatus = :orderStatus, deliveryBoyId = :deliveryBoyId where orderId = :orderId ";
			  
			  query = session.createQuery(sql)
					    .setParameter("orderStatus", order.getOrderStatus())
					    .setParameter("deliveryBoyId", order.getDeliveryBoyId())
					    .setParameter("orderId", order.getOrderId());
			  
		  } 
		  else if(orderStatus.equals("Ready To Ship")) {
			  sql = "update Order set orderStatus = :orderStatus where orderId = :orderId ";
			  
			  query = session.createQuery(sql)
					    .setParameter("orderStatus", order.getOrderStatus())					    
					    .setParameter("orderId", order.getOrderId());
		  }
						   
		  query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();	
	}
	
	
	@Override
	public void changeOrderStatusCustom(int orderId, String orderStatus) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 	
		  String sql = "update Order set orderStatus = :orderStatus  where orderId = :orderId ";
		 		  
		  Query query = session.createQuery(sql)
				    .setParameter("orderStatus", orderStatus)
				    .setParameter("orderId", orderId);
				   
		  query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();	
	}
	
	
	@Override
	public void changeOrderDetailsStatus(OrderDetails orderDetails) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  String orderStatus = orderDetails.getOrderStatus();
		  String sql = "";
		  
		  if(orderStatus.equals("Ready To Ship")) {
			  sql = "update OrderDetails set orderStatus = :orderStatus, readyToShipDate = :tdate where orderDetailsId = :orderDetailsId ";
		  } else if(orderStatus.equals("Peak Up")) {
			  sql = "update OrderDetails set orderStatus = :orderStatus, peakUpDate = :tdate where orderDetailsId = :orderDetailsId ";
		  } else if(orderStatus.equals("Delivered")) {
			  sql = "update OrderDetails set orderStatus = :orderStatus, deliveredDate = :tdate where orderDetailsId = :orderDetailsId ";
		  } else if(orderStatus.equals("Canceled")) {
			  sql = "update OrderDetails set orderStatus = :orderStatus, canceledDate = :tdate where orderDetailsId = :orderDetailsId ";
		  } 
		  
		  Query query = session.createQuery(sql)
				    .setParameter("orderStatus", orderDetails.getOrderStatus())
				    .setParameter("tdate", DateTimeUtil.getSysDateTime())
				    .setParameter("orderDetailsId", orderDetails.getOrderDetailsId());
				   
		  query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();	
	}
	
	@Override
	public List<Order> getTodaysOrderList(int sellerId, String orderDate) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  		
		  String sql = " select distinct o from OrderDetails as od, Order as o where od.sellerId= :sellerId "
		  		+ "	and o.orderId = od.orderId and o.orderDate like :tdate order by o.orderId desc ";
		  
		  Query query = session.createQuery(sql)
				  .setParameter("sellerId", sellerId)
				    .setParameter("tdate", orderDate+'%');
				    
		  
		 // Criteria criteria = session.createCriteria(Order.class).add(Restrictions.ilike("orderDate", orderDate, MatchMode.START));	
							  
		  @SuppressWarnings("unchecked")
		  List<Order> OrderList = (List<Order>) query.list();
		  
		  //criteria.addOrder(org.hibernate.criterion.Order.desc("orderId")).list();
		  
		  tx.commit();  
		  session.close(); 
		  
		  return OrderList; 
	}
	
	@Override
	public List<Order> getOrderListBySellerId(int sellerId, String orderDate, String orderStatus) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  		
		  
		  String sql = " select distinct o from OrderDetails as od, Order as o where od.sellerId= :sellerId and o.orderId = od.orderId ";
		  if(orderStatus.length() != 0) {
			  
			if(orderStatus.equals("New")) {
				sql += " and (o.orderStatus = :orderStatus or o.orderStatus = :orderStatus1)";
			}else {
				sql += " and o.orderStatus = :orderStatus ";
			}
			   
          }
	      if(orderDate.length() != 0) {
			   sql += " and o.orderDate like :tdate ";
		  }	      
	      sql += " order by o.orderId desc";
	      
		  
		  Query query = session.createQuery(sql)
				  .setParameter("sellerId", sellerId);
				  
		  if(orderStatus.length() != 0) {
			  
			  if(orderStatus.equals("New")) {
				  query.setParameter("orderStatus", "New");
				  query.setParameter("orderStatus1", "Assigned");
				}else {
					query.setParameter("orderStatus", orderStatus);
				}
          }
	      if(orderDate.length() != 0) {
	    	  query.setParameter("tdate", orderDate+'%');
		  }	  
		  					  
		  @SuppressWarnings("unchecked")
		  List<Order> OrderList = (List<Order>) query.list();
		  
		  tx.commit();  
		  session.close(); 
		  
		  return OrderList; 
	}
	
	@Override
	public List<Order> getTodaysDeliveryOrderList(int deliveryId, String orderDate) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  		
		  String sql = " select distinct o from OrderDetails as od, Order as o, Seller as s, Delivery as d "
		  		+ "	where o.orderStatus = 'New' and od.sellerId= s.sellerId and s.localFranchiseId = d.localFranchiseId "
		  		+ "	and o.orderId = od.orderId and d.deliveryId = :deliveryId and o.orderDate like :tdate order by o.orderId desc ";
		  
		  Query query = session.createQuery(sql)
				  .setParameter("deliveryId", deliveryId)
				    .setParameter("tdate", orderDate+'%');
				    
		  
		 // Criteria criteria = session.createCriteria(Order.class).add(Restrictions.ilike("orderDate", orderDate, MatchMode.START));	
							  
		  @SuppressWarnings("unchecked")
		  List<Order> OrderList = (List<Order>) query.list();
		  
		  //criteria.addOrder(org.hibernate.criterion.Order.desc("orderId")).list();
		  
		  tx.commit();  
		  session.close(); 
		  
		  return OrderList; 
	}
	
	@Override
	public List<OrderDetails> getOrderDetailsByOrderId(int orderId) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  
		  @SuppressWarnings("unchecked")
		  List<OrderDetails> OrderDetailsList = (List<OrderDetails>) session.createCriteria(OrderDetails.class)
		                .add(Restrictions.eq("orderId", orderId)).list();  
		  		  
		  tx.commit();  
		  session.close();
		  
		  return OrderDetailsList;  		  
	}
	
	
	@Override
	public List<OrderDetails> getOrderDetailsListBySellerId(int sellerId) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  
		  @SuppressWarnings("unchecked")
		  List<OrderDetails> OrderDetailsList = (List<OrderDetails>) session.createCriteria(OrderDetails.class).add(Restrictions.eq("sellerId", sellerId)).list();  
		  		  
		  tx.commit();  
		  session.close();
		  
		  return OrderDetailsList;  		  
	}
	
	
	@Override
	public String getOrderStatusById(int orderId) throws Exception {
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		String orderStatus  = (String) session.createCriteria(Order.class)
				    .setProjection(Projections.property("orderStatus")) 
	                .add(Restrictions.eq("orderId", orderId)).uniqueResult();
		
		
		//String hql = " select orderStatus from Order where orderId = :orderId ";
		
		//String orderStatus =  (String)session.createQuery(hql).setInteger("orderId", orderId).uniqueResult();
		
		tx.commit();
		session.close();
		
		return orderStatus;
	}
	
	
	@Override
	public List<Order> getOrderListByLocalFranchiseId(int localFranchiseId, String orderDate, String orderStatus) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  		
		  String sql = " select distinct o from OrderDetails as od, Order as o, Seller as s where od.sellerId= s.sellerId and o.orderId = od.orderId and s.localFranchiseId = :localFranchiseId ";
			  
		  
		  if(!orderStatus.equals("all")) {
			   sql += " and o.orderStatus = :orderStatus ";
          }
	      if(orderDate.length() != 0) {
			   sql += " and o.orderDate like :tdate ";
		  }	
	      sql += " order by o.orderId desc ";
		  
		  Query query = session.createQuery(sql)
				  .setParameter("localFranchiseId", localFranchiseId);
				  
		  if(!orderStatus.equals("all")) {
			  query.setParameter("orderStatus", orderStatus);
          }
	      if(orderDate.length() != 0) {
	    	  query.setParameter("tdate", orderDate+'%');
		  }	  
	      
		  					  
		  @SuppressWarnings("unchecked")
		  List<Order> OrderList = (List<Order>) query.list();
		  
		  tx.commit();  
		  session.close(); 
		  
		  return OrderList; 
	}
	
	public Order orderOTPVerification(int orderId, String otp) throws Exception {
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		Order order  = (Order) session.createCriteria(Order.class)				   
	                .add(Restrictions.eq("orderId", orderId))
	                .add(Restrictions.eq("orderOtp", otp))
	                .uniqueResult();
				
		tx.commit();
		session.close();
		
		return order;
	}
	
	public void updateDeliveryBoyMargin(int orderId, double margin)  throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  Query query = session.createQuery(" update Order set deliveryBoyCommission = :margin where orderId = :orderId ")
				    .setParameter("margin", String.valueOf(margin))
				    .setParameter("orderId", orderId);
				   
		  query.executeUpdate();
		 		
		  tx.commit(); 
		  session.close();	
	}
}