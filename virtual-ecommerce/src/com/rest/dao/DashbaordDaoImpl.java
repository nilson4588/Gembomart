package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.BalanceSheet;
import com.rest.model.CommisionCalculation;
import com.rest.model.DashboardTotal;
import com.rest.model.DashboardTotalProductwise;
import com.rest.model.Order;
import com.rest.model.Referral;
import com.rest.model.ReferralCommision;
import com.rest.model.SellerOrder;
import com.rest.model.SellerProductList;
import com.rest.model.SupplierOrder;

@Repository
@Transactional
public class DashbaordDaoImpl implements DashboardDao {

	@Autowired  
	SessionFactory sessionFactory;  
	  
	Session session = null;  
	Transaction tx = null;  
	
	@Override
	public List<DashboardTotal> getSellerDashboardTotal(String date) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  String sql = " select s.product_id as productId, p.product_title as productName, s.order_unit as productUnit, SUM(s.order_quantity) as quantity, sum(s.order_amount) as amount "
		  		 +" from tbl_seller_order_details as s, tbl_seller_order as so, tbl_product as p " 
		  		 +" where so.order_status != 'Canceled' and s.is_active = 1 and SUBSTRING_INDEX(so.order_date,' ',1) = :tdate and s.seller_order_id = so.seller_order_id and s.product_id = p.product_id group by s.product_id ";
		  
		  @SuppressWarnings("unchecked")
		  List<DashboardTotal> dashboardTotalList = (List<DashboardTotal>) ((SQLQuery) session.createSQLQuery(sql)
											.setParameter("tdate", date))
				  							.addScalar("productId", new IntegerType())
											.addScalar("productName", new StringType())
											.addScalar("productUnit", new StringType())
											.addScalar("quantity", new IntegerType())
											.addScalar("amount", new DoubleType())
											.setResultTransformer(Transformers.aliasToBean(DashboardTotal.class))
											.list() ;
		
		  tx.commit();  
		  session.close();	
		  
		  return dashboardTotalList;
	}

	@Override
	public List<DashboardTotal> getSupplierDashboardTotal(String date) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  String sql = " select s.product_id as productId, p.product_title as productName, s.order_unit as productUnit, SUM(s.order_quantity) as quantity, sum(s.order_amount) as amount "
		  		+" from tbl_supplier_order_details as s, tbl_supplier_order as so, tbl_product as p "  
		  		+" where so.order_status != 'Canceled' and s.is_active = 1 and SUBSTRING_INDEX(so.order_date,' ',1) = :tdate and s.product_id = p.product_id  and so.supplier_order_id = s.supplier_order_id group by s.product_id ";
		  
		  @SuppressWarnings("unchecked")
		  List<DashboardTotal> dashboardTotalList = (List<DashboardTotal>) ((SQLQuery) session.createSQLQuery(sql)
											.setParameter("tdate", date))
											.addScalar("productId", new IntegerType())
											.addScalar("productName", new StringType())
											.addScalar("productUnit", new StringType())
											.addScalar("quantity", new IntegerType())
											.addScalar("amount", new DoubleType())
											.setResultTransformer(Transformers.aliasToBean(DashboardTotal.class))
											.list() ;
		
		  tx.commit();  
		  session.close();	
		  
		  return dashboardTotalList;
	}

	@Override
	public List<DashboardTotalProductwise> getSellerDashboardTOtalProductwise(String date, int productId)
			throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  String sql = " select s.product_id as productId, p.product_title as productName, s.order_unit as productUnit, ts.seller_contact_no as mobileNumber,  " + 
		  		" ts.seller_full_name as fullName, s.order_quantity as quantity, s.order_amount as amount from tbl_seller_order_details as s, tbl_seller_order as so, " + 
		  		" tbl_seller as ts, tbl_product as p where so.order_status != 'Canceled' and s.is_active = 1 and s.seller_order_id = so.seller_order_id and SUBSTRING_INDEX(so.order_date,' ',1) = :tdate " + 
		  		" and so.mobile_number = ts.seller_contact_no and s.product_id = p.product_id and s.product_id = :productId  ";
		  
		  @SuppressWarnings("unchecked")
		  List<DashboardTotalProductwise> dashboardTotalList = (List<DashboardTotalProductwise>) ((SQLQuery) session.createSQLQuery(sql)
											.setParameter("tdate", date)
											.setParameter("productId", productId))
											.addScalar("productId", new IntegerType())
											.addScalar("productName", new StringType())
											.addScalar("productUnit", new StringType())
											.addScalar("mobileNumber", new LongType())
											.addScalar("fullName", new StringType())
											.addScalar("quantity", new IntegerType())
											.addScalar("amount", new DoubleType())
											.setResultTransformer(Transformers.aliasToBean(DashboardTotalProductwise.class))
											.list();
		
		  tx.commit();  
		  session.close();	
		  
		  return dashboardTotalList;
	}

	@Override
	public List<DashboardTotalProductwise> getSupplierDashboardTOtalProductwise(String date, int productId)
			throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  String sql = " select s.product_id as productId, p.product_title as productName, s.order_unit as productUnit, ts.supplier_contact_no as mobileNumber, ts.supplier_full_name as fullName, s.order_quantity as quantity, s.order_amount as amount " + 
		  		" from tbl_supplier_order_details as s, tbl_supplier_order as so, tbl_supplier as ts, tbl_product as p " + 
		  		" where so.order_status != 'Canceled' and s.is_active = 1 and so.supplier_order_id = s.supplier_order_id and SUBSTRING_INDEX(so.order_date,' ',1) = :tdate " + 
		  		" and so.mobile_number = ts.supplier_contact_no and s.product_id = p.product_id and s.product_id = :productId ";
		  
		  @SuppressWarnings("unchecked")
		  List<DashboardTotalProductwise> dashboardTotalList = (List<DashboardTotalProductwise>) ((SQLQuery) session.createSQLQuery(sql)
											.setParameter("tdate", date)
											.setParameter("productId", productId))
											.addScalar("productId", new IntegerType())
											.addScalar("productName", new StringType())
											.addScalar("productUnit", new StringType())
											.addScalar("mobileNumber", new LongType()) 
											.addScalar("fullName", new StringType())
											.addScalar("quantity", new IntegerType())
											.addScalar("amount", new DoubleType())
											.setResultTransformer(Transformers.aliasToBean(DashboardTotalProductwise.class))
											.list() ;
		
		  tx.commit();  
		  session.close();	
		  
		  return dashboardTotalList;
	}
	
	@Override
	public List<DashboardTotal> getCustomerDashboardTotal(String date) throws Exception {
		
		 session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  String sql = " select p.product_id as productId, p.product_title as productName, p.product_packsize as productUnit, SUM(od.quantity) as quantity, sum(od.amount) as amount " + 
		  		" from tbl_order_details as od, tbl_order as o, tbl_product as p	where o.order_status != 'Canceled' and SUBSTRING_INDEX(o.order_date,' ',1) = :tdate " + 
		  		" and od.product_id = p.product_id and od.order_id = o.order_id group by p.product_id ";
		  
		  @SuppressWarnings("unchecked")
		  List<DashboardTotal> dashboardTotalList = (List<DashboardTotal>) ((SQLQuery) session.createSQLQuery(sql)
											.setParameter("tdate", date))
				  							.addScalar("productId", new IntegerType())
											.addScalar("productName", new StringType())
											.addScalar("productUnit", new StringType())
											.addScalar("quantity", new IntegerType())
											.addScalar("amount", new DoubleType())
											.setResultTransformer(Transformers.aliasToBean(DashboardTotal.class))
											.list() ;
		
		  tx.commit();  
		  session.close();	
		  
		  return dashboardTotalList;
		
	}
	
	@Override
	public List<BalanceSheet> getBalanceSheet(String date) throws Exception {
		 
		 Session session = sessionFactory.openSession();  
		 Transaction tx  = session.beginTransaction();  
		 Query query = ((SQLQuery) session.createSQLQuery("CALL `proc_balance_sheet`(:date)")		
							  .setParameter("date", date))
							  .addScalar("supplierAmount", new DoubleType())
							  .addScalar("sellerAmount", new DoubleType())
							  .addScalar("customerAmount", new DoubleType())
							  .addScalar("profitAmount", new DoubleType());							  
		
		@SuppressWarnings("unchecked")
		List<BalanceSheet> collectionList = query.setResultTransformer(Transformers.aliasToBean(BalanceSheet.class)).list();
		
		tx.commit();  
		session.close(); 
		
		return collectionList;
	}
	
	@Override
	public List<BalanceSheet> getBalanceSheetBetweenDates(String fromDate, String toDate) throws Exception {
		 
		 Session session = sessionFactory.openSession();  
		 Transaction tx  = session.beginTransaction();  
		 Query query = ((SQLQuery) session.createSQLQuery("CALL proc_balance_sheet_between_dates(:fromDate, :toDate)")		
							  .setParameter("fromDate", fromDate)
				 			  .setParameter("toDate", toDate))
							  .addScalar("supplierAmount", new DoubleType())
							  .addScalar("sellerAmount", new DoubleType())
							  .addScalar("customerAmount", new DoubleType())
							  .addScalar("profitAmount", new DoubleType());							  
		
		@SuppressWarnings("unchecked")
		List<BalanceSheet> collectionList = query.setResultTransformer(Transformers.aliasToBean(BalanceSheet.class)).list();
		
		tx.commit();  
		session.close(); 
		
		return collectionList;
	}
	
	@Override
	public List<SellerProductList> getSellerProductCategorywiseTotal(String tdate) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  String sql = " select p.product_category as productName, sum(s.order_amount) as productId from tbl_seller_order_details as s, tbl_seller_order as so, tbl_product as p  " + 
		  		" where so.order_status != 'Canceled' and s.is_active = 1 and SUBSTRING_INDEX(so.order_date,' ',1) = :tdate and s.product_id = p.product_id group by p.product_category ";
		  
		  @SuppressWarnings("unchecked")
		  List<SellerProductList> sellerProudctList = (List<SellerProductList>) ((SQLQuery) session.createSQLQuery(sql)											
											.setParameter("tdate", tdate))
				  							.addScalar("productName", new StringType())
											.addScalar("productId", new StringType())
											.setResultTransformer(Transformers.aliasToBean(SellerProductList.class))
											.list() ;
		
		  tx.commit();  
		  session.close();	
		  
		  return sellerProudctList;
	}	
	
	@Override
	public List<SellerProductList> getSupplierProductCategorywiseTotal(String tdate) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  String sql = " select p.product_category as productName, sum(s.order_amount) as productId from `tbl_supplier_order_details` as s, `tbl_supplier_order` as so, tbl_product as p " + 
		  		" where so.order_status != 'Canceled' and s.is_active = 1 and SUBSTRING_INDEX(so.order_date,' ',1) = :tdate and s.product_id = p.product_id group by p.product_category  ";
		  
		  @SuppressWarnings("unchecked")
		  List<SellerProductList> sellerProudctList = (List<SellerProductList>) ((SQLQuery) session.createSQLQuery(sql)											
											.setParameter("tdate", tdate))
				  							.addScalar("productName", new StringType())
											.addScalar("productId", new StringType())
											.setResultTransformer(Transformers.aliasToBean(SellerProductList.class))
											.list() ;
		
		  tx.commit();  
		  session.close();	
		  
		  return sellerProudctList;
	}	
	
	@Override
	public List<SellerProductList> getCustomerProductCategorywiseTotal(String tdate) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  String sql = " select p.product_category as productName, sum(s.order_amount) as productId  from tbl_supplier_order_details as s, tbl_supplier_order as so, tbl_product as p " + 
		  		" where so.order_status != 'Canceled' and s.is_active = 1 and SUBSTRING_INDEX(so.order_date,' ',1) = :tdate and s.product_id = p.product_id group by p.product_category ";
		  
		  @SuppressWarnings("unchecked")
		  List<SellerProductList> sellerProudctList = (List<SellerProductList>) ((SQLQuery) session.createSQLQuery(sql)											
											.setParameter("tdate", tdate))
				  							.addScalar("productName", new StringType())
											.addScalar("productId", new StringType())
											.setResultTransformer(Transformers.aliasToBean(SellerProductList.class))
											.list() ;
		
		  tx.commit();  
		  session.close();	
		  
		  return sellerProudctList;
	}	
	
	@Override
	public List<SellerOrder> getNewSellerOrderList(String fromDate, String toDate) throws Exception {
	
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		
		  String sql = " SELECT seller_order_id as sellerOrderId, mobile_number as mobileNumber, order_date as orderDate, order_status as orderStatus, order_type as orderType, total_amount as totalAmount, " + 
		  		" payment_method as paymentMethod, delivered_date as deliveredDate, shipped_date as shippedDate, canceled_date as canceledDate, expected_delivery_date as expectedDeliveryDate " + 
		  		" FROM tbl_seller_order WHERE STR_TO_DATE(substring_index(order_date,' ', 1), '%d-%m-%Y') between STR_TO_DATE(:fromDate, '%d-%m-%Y') and STR_TO_DATE(:toDate, '%d-%m-%Y') " + 
		  		" and order_status = 'New' order by STR_TO_DATE(order_date, '%d-%m-%Y %h:%i:%s %p') desc";
		
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerOrderList = (List<SellerOrder>) ((SQLQuery) session.createSQLQuery(sql)											
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate))
					.addScalar("sellerOrderId", new IntegerType())
					.addScalar("mobileNumber", new LongType())
					.addScalar("orderDate", new StringType())
					.addScalar("orderStatus", new StringType())
					.addScalar("orderType", new StringType())
					.addScalar("totalAmount", new StringType())
					.addScalar("paymentMethod", new StringType())
					.addScalar("deliveredDate", new StringType())		
					.addScalar("shippedDate", new StringType())
					.addScalar("canceledDate", new StringType())
					.addScalar("expectedDeliveryDate", new StringType())
					.setResultTransformer(Transformers.aliasToBean(SellerOrder.class))
					.list() ;
		  
		  return sellerOrderList;
	}
	
	@Override
	public List<SellerOrder> getShippedSellerOrderList(String fromDate, String toDate) throws Exception {
		
		session = sessionFactory.openSession();  
		tx      = session.beginTransaction();  
		
		 String sql = " SELECT seller_order_id as sellerOrderId, mobile_number as mobileNumber, order_date as orderDate, order_status as orderStatus, order_type as orderType, total_amount as totalAmount, " + 
			  		" payment_method as paymentMethod, delivered_date as deliveredDate, shipped_date as shippedDate, canceled_date as canceledDate, expected_delivery_date as expectedDeliveryDate " + 
			  		" FROM tbl_seller_order WHERE STR_TO_DATE(substring_index(shipped_date,' ', 1), '%d-%m-%Y') between STR_TO_DATE(:fromDate, '%d-%m-%Y') and STR_TO_DATE(:toDate, '%d-%m-%Y') " + 
			  		" and order_status = 'Shipped' order by STR_TO_DATE(shipped_date, '%d-%m-%Y %h:%i:%s %p') desc";
			
			  @SuppressWarnings("unchecked")
			  List<SellerOrder> sellerOrderList = (List<SellerOrder>) ((SQLQuery) session.createSQLQuery(sql)											
						.setParameter("fromDate", fromDate)
						.setParameter("toDate", toDate))
						.addScalar("sellerOrderId", new IntegerType())
						.addScalar("mobileNumber", new LongType())
						.addScalar("orderDate", new StringType())
						.addScalar("orderStatus", new StringType())
						.addScalar("orderType", new StringType())
						.addScalar("totalAmount", new StringType())
						.addScalar("paymentMethod", new StringType())
						.addScalar("deliveredDate", new StringType())		
						.addScalar("shippedDate", new StringType())
						.addScalar("canceledDate", new StringType())
						.addScalar("expectedDeliveryDate", new StringType())
						.setResultTransformer(Transformers.aliasToBean(SellerOrder.class))
						.list() ;
		  
		 return sellerOrderList;
		
	}
	
	@Override
	public List<SellerOrder> getCanceledSellerOrderList(String fromDate, String toDate) throws Exception {

		session = sessionFactory.openSession();  
		tx      = session.beginTransaction();  
		
		String sql = " SELECT seller_order_id as sellerOrderId, mobile_number as mobileNumber, order_date as orderDate, order_status as orderStatus, order_type as orderType, total_amount as totalAmount, " + 
		  		" payment_method as paymentMethod, delivered_date as deliveredDate, shipped_date as shippedDate, canceled_date as canceledDate, expected_delivery_date as expectedDeliveryDate " + 
		  		" FROM tbl_seller_order WHERE STR_TO_DATE(substring_index(canceled_date,' ', 1), '%d-%m-%Y') between STR_TO_DATE(:fromDate, '%d-%m-%Y') and STR_TO_DATE(:toDate, '%d-%m-%Y') " + 
		  		" and order_status = 'Canceled' order by STR_TO_DATE(canceled_date, '%d-%m-%Y %h:%i:%s %p') desc";
		
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerOrderList = (List<SellerOrder>) ((SQLQuery) session.createSQLQuery(sql)											
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate))
					.addScalar("sellerOrderId", new IntegerType())
					.addScalar("mobileNumber", new LongType())
					.addScalar("orderDate", new StringType())
					.addScalar("orderStatus", new StringType())
					.addScalar("orderType", new StringType())
					.addScalar("totalAmount", new StringType())
					.addScalar("paymentMethod", new StringType())
					.addScalar("deliveredDate", new StringType())		
					.addScalar("shippedDate", new StringType())
					.addScalar("canceledDate", new StringType())
					.addScalar("expectedDeliveryDate", new StringType())
					.setResultTransformer(Transformers.aliasToBean(SellerOrder.class))
					.list() ;
		  
		  return sellerOrderList;		
	}
	
	@Override
	public List<SellerOrder> getDeliveredSellerOrderList(String fromDate, String toDate) throws Exception {

		session = sessionFactory.openSession();  
		tx      = session.beginTransaction();  
		
		String sql = " SELECT seller_order_id as sellerOrderId, mobile_number as mobileNumber, order_date as orderDate, order_status as orderStatus, order_type as orderType, total_amount as totalAmount, " + 
		  		" payment_method as paymentMethod, delivered_date as deliveredDate, shipped_date as shippedDate, canceled_date as canceledDate, expected_delivery_date as expectedDeliveryDate " + 
		  		" FROM tbl_seller_order WHERE STR_TO_DATE(substring_index(delivered_date,' ', 1), '%d-%m-%Y') between STR_TO_DATE(:fromDate, '%d-%m-%Y') and STR_TO_DATE(:toDate, '%d-%m-%Y') " + 
		  		" and order_status = 'Delivered' order by STR_TO_DATE(delivered_date, '%d-%m-%Y %h:%i:%s %p') desc";
		
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerOrderList = (List<SellerOrder>) ((SQLQuery) session.createSQLQuery(sql)											
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate))
					.addScalar("sellerOrderId", new IntegerType())
					.addScalar("mobileNumber", new LongType())
					.addScalar("orderDate", new StringType())
					.addScalar("orderStatus", new StringType())
					.addScalar("orderType", new StringType())
					.addScalar("totalAmount", new StringType())
					.addScalar("paymentMethod", new StringType())
					.addScalar("deliveredDate", new StringType())		
					.addScalar("shippedDate", new StringType())
					.addScalar("canceledDate", new StringType())
					.addScalar("expectedDeliveryDate", new StringType())
					.setResultTransformer(Transformers.aliasToBean(SellerOrder.class))
					.list() ;
		  
		  return sellerOrderList;		
	}
			
	@Override
	public List<SupplierOrder> getNewSupplierOrderList(String fromDate, String toDate) throws Exception {
		
		session = sessionFactory.openSession();  
		tx      = session.beginTransaction();  
		
		String sql = " SELECT supplier_order_id as supplierOrderId, mobile_number as mobileNumber, order_date as orderDate, order_status as orderStatus, order_type as orderType, total_amount as totalAmount, " + 
				" payment_method as paymentMethod, delivered_date as deliveredDate,shipped_date as shippedDate, canceled_date as canceledDate, expected_delivery_date as expectedDeliveryDate " + 
				" FROM tbl_supplier_order WHERE STR_TO_DATE(substring_index(order_date,' ', 1), '%d-%m-%Y') between STR_TO_DATE(:fromDate, '%d-%m-%Y') and STR_TO_DATE(:toDate, '%d-%m-%Y') "+
			    " and order_status = 'New' order by STR_TO_DATE(order_date, '%d-%m-%Y %h:%i:%s %p') desc ";
		
		@SuppressWarnings("unchecked")
		  List<SupplierOrder> supplierOrderList = (List<SupplierOrder>) ((SQLQuery) session.createSQLQuery(sql)											
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate))
					.addScalar("supplierOrderId", new IntegerType())
					.addScalar("mobileNumber", new LongType())
					.addScalar("orderDate", new StringType())
					.addScalar("orderStatus", new StringType())
					.addScalar("orderType", new StringType())
					.addScalar("totalAmount", new StringType())
					.addScalar("paymentMethod", new StringType())
					.addScalar("deliveredDate", new StringType())		
					.addScalar("shippedDate", new StringType())
					.addScalar("canceledDate", new StringType())
					.addScalar("expectedDeliveryDate", new StringType())				
					.setResultTransformer(Transformers.aliasToBean(SupplierOrder.class))
					.list() ;
		  
		  return supplierOrderList;	
	}
		
	@Override
	public List<SupplierOrder> getDeliveredSupplierOrderList(String fromDate, String toDate) throws Exception {
			
		session = sessionFactory.openSession();  
		tx      = session.beginTransaction();  
			
		String sql = " SELECT supplier_order_id as supplierOrderId, mobile_number as mobileNumber, order_date as orderDate, order_status as orderStatus, order_type as orderType, total_amount as totalAmount, " + 
				" payment_method as paymentMethod, delivered_date as deliveredDate,shipped_date as shippedDate, canceled_date as canceledDate, expected_delivery_date as expectedDeliveryDate " + 
				" FROM tbl_supplier_order WHERE STR_TO_DATE(substring_index(delivered_date,' ', 1), '%d-%m-%Y') between STR_TO_DATE(:fromDate, '%d-%m-%Y') and STR_TO_DATE(:toDate, '%d-%m-%Y') "+
			    " and order_status = 'Delivered' order by STR_TO_DATE(delivered_date, '%d-%m-%Y %h:%i:%s %p') desc ";
		
		@SuppressWarnings("unchecked")
		  List<SupplierOrder> supplierOrderList = (List<SupplierOrder>) ((SQLQuery) session.createSQLQuery(sql)											
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate))
					.addScalar("supplierOrderId", new IntegerType())
					.addScalar("mobileNumber", new LongType())
					.addScalar("orderDate", new StringType())
					.addScalar("orderStatus", new StringType())
					.addScalar("orderType", new StringType())
					.addScalar("totalAmount", new StringType())
					.addScalar("paymentMethod", new StringType())
					.addScalar("deliveredDate", new StringType())		
					.addScalar("shippedDate", new StringType())
					.addScalar("canceledDate", new StringType())
					.addScalar("expectedDeliveryDate", new StringType())				
					.setResultTransformer(Transformers.aliasToBean(SupplierOrder.class))
					.list() ;
			  
			  return supplierOrderList;	
	}
	
	
	@Override
	public List<SupplierOrder> getShippedSupplierOrderList(String fromDate, String toDate) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();  
		tx      = session.beginTransaction();  
			
		String sql = " SELECT supplier_order_id as supplierOrderId, mobile_number as mobileNumber, order_date as orderDate, order_status as orderStatus, order_type as orderType, total_amount as totalAmount, " + 
				" payment_method as paymentMethod, delivered_date as deliveredDate, shipped_date as shippedDate, canceled_date as canceledDate, expected_delivery_date as expectedDeliveryDate " + 
				" FROM tbl_supplier_order WHERE STR_TO_DATE(substring_index(shipped_date,' ', 1), '%d-%m-%Y') between STR_TO_DATE(:fromDate, '%d-%m-%Y') and STR_TO_DATE(:toDate, '%d-%m-%Y') "+
			    " and order_status = 'Shipped' order by STR_TO_DATE(shipped_date, '%d-%m-%Y %h:%i:%s %p') desc ";
		
		@SuppressWarnings("unchecked")
		  List<SupplierOrder> supplierOrderList = (List<SupplierOrder>) ((SQLQuery) session.createSQLQuery(sql)											
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate))
					.addScalar("supplierOrderId", new IntegerType())
					.addScalar("mobileNumber", new LongType())
					.addScalar("orderDate", new StringType())
					.addScalar("orderStatus", new StringType())
					.addScalar("orderType", new StringType())
					.addScalar("totalAmount", new StringType())
					.addScalar("paymentMethod", new StringType())
					.addScalar("deliveredDate", new StringType())		
					.addScalar("shippedDate", new StringType())
					.addScalar("canceledDate", new StringType())
					.addScalar("expectedDeliveryDate", new StringType())				
					.setResultTransformer(Transformers.aliasToBean(SupplierOrder.class))
					.list() ;
			  
			  return supplierOrderList;	
	}
	
	@Override	
	public List<SupplierOrder> getCanceledSupplierOrderList(String fromDate, String toDate) throws Exception {
			
		session = sessionFactory.openSession();  
		tx      = session.beginTransaction();  
			
		String sql = " SELECT supplier_order_id as supplierOrderId, mobile_number as mobileNumber, order_date as orderDate, order_status as orderStatus, order_type as orderType, total_amount as totalAmount, " + 
				" payment_method as paymentMethod, delivered_date as deliveredDate, shipped_date as shippedDate, canceled_date as canceledDate, expected_delivery_date as expectedDeliveryDate " + 
				" FROM tbl_supplier_order WHERE STR_TO_DATE(substring_index(canceled_date,' ', 1), '%d-%m-%Y') between STR_TO_DATE(:fromDate, '%d-%m-%Y') and STR_TO_DATE(:toDate, '%d-%m-%Y') "+
			    " and order_status = 'Canceled' order by STR_TO_DATE(canceled_date, '%d-%m-%Y %h:%i:%s %p') desc ";
		
		  @SuppressWarnings("unchecked")
		  List<SupplierOrder> supplierOrderList = (List<SupplierOrder>) ((SQLQuery) session.createSQLQuery(sql)											
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate))
					.addScalar("supplierOrderId", new IntegerType())
					.addScalar("mobileNumber", new LongType())
					.addScalar("orderDate", new StringType())
					.addScalar("orderStatus", new StringType())
					.addScalar("orderType", new StringType())
					.addScalar("totalAmount", new StringType())
					.addScalar("paymentMethod", new StringType())
					.addScalar("deliveredDate", new StringType())		
					.addScalar("shippedDate", new StringType())
					.addScalar("canceledDate", new StringType())
					.addScalar("expectedDeliveryDate", new StringType())				
					.setResultTransformer(Transformers.aliasToBean(SupplierOrder.class))
					.list() ;
			  
			  return supplierOrderList;			
	}
	
	@Override
	public List<Order> getNewCustomerOrderList(String fromDate, String toDate) throws Exception {
		
		session = sessionFactory.openSession();  
		tx      = session.beginTransaction();  
		
		String sql = " SELECT order_id as orderId, mobile_no as mobileNo, order_date as orderDate, order_status as orderStatus, order_type as orderType, total_amount as totalAmount, " + 
				" payment_method as paymentMethod, delivered_date as deliveredDate,shipped_date as shippedDate, canceled_date as canceledDate, expected_delivery_date as expectedDeliveryDate " + 
				" FROM tbl_order WHERE STR_TO_DATE(substring_index(order_date,' ', 1), '%d-%m-%Y') between STR_TO_DATE(:fromDate, '%d-%m-%Y') and STR_TO_DATE(:toDate, '%d-%m-%Y') " + 
				" and order_status = 'New' order by STR_TO_DATE(order_date, '%d-%m-%Y %h:%i:%s %p') desc";
		
		@SuppressWarnings("unchecked")
		  List<Order> orderList = (List<Order>) ((SQLQuery) session.createSQLQuery(sql)											
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate))
					.addScalar("orderId", new IntegerType())
					.addScalar("mobileNo", new LongType())
					.addScalar("orderDate", new StringType())
					.addScalar("orderStatus", new StringType())
					.addScalar("orderType", new StringType())
					.addScalar("totalAmount", new StringType())
					.addScalar("paymentMethod", new StringType())
					.addScalar("deliveredDate", new StringType())		
					.addScalar("shippedDate", new StringType())
					.addScalar("canceledDate", new StringType())
					.addScalar("expectedDeliveryDate", new StringType())					
					.setResultTransformer(Transformers.aliasToBean(Order.class))
					.list() ;
		  
		  return orderList;		
	}
		
	@Override
	public List<Order> getShippedCustomerOrderList(String fromDate, String toDate) throws Exception {
		
		session = sessionFactory.openSession();  
		tx      = session.beginTransaction();  
		

		String sql = " SELECT order_id as orderId, mobile_no as mobileNo, order_date as orderDate, order_status as orderStatus, order_type as orderType, total_amount as totalAmount, " + 
				" payment_method as paymentMethod, delivered_date as deliveredDate,shipped_date as shippedDate, canceled_date as canceledDate, expected_delivery_date as expectedDeliveryDate " + 
				" FROM tbl_order WHERE STR_TO_DATE(substring_index(shipped_date,' ', 1), '%d-%m-%Y') between STR_TO_DATE(:fromDate, '%d-%m-%Y') and STR_TO_DATE(:toDate, '%d-%m-%Y') " + 
				" and order_status = 'Shipped' order by STR_TO_DATE(shipped_date, '%d-%m-%Y %h:%i:%s %p') desc";
		
		@SuppressWarnings("unchecked")
		  List<Order> orderList = (List<Order>) ((SQLQuery) session.createSQLQuery(sql)											
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate))
					.addScalar("orderId", new IntegerType())
					.addScalar("mobileNo", new LongType())
					.addScalar("orderDate", new StringType())
					.addScalar("orderStatus", new StringType())
					.addScalar("orderType", new StringType())
					.addScalar("totalAmount", new StringType())
					.addScalar("paymentMethod", new StringType())
					.addScalar("deliveredDate", new StringType())		
					.addScalar("shippedDate", new StringType())
					.addScalar("canceledDate", new StringType())
					.addScalar("expectedDeliveryDate", new StringType())					
					.setResultTransformer(Transformers.aliasToBean(Order.class))
					.list() ;
		  
		return orderList;		
	}	
	
	@Override
	public List<Order> getDeliveredCustomerOrderList(String fromDate, String toDate) throws Exception {
			
		session = sessionFactory.openSession();  
		tx      = session.beginTransaction();  
		
		String sql = " SELECT order_id as orderId, mobile_no as mobileNo, order_date as orderDate, order_status as orderStatus, order_type as orderType, total_amount as totalAmount, " + 
				" payment_method as paymentMethod, delivered_date as deliveredDate,shipped_date as shippedDate, canceled_date as canceledDate, expected_delivery_date as expectedDeliveryDate " + 
				" FROM tbl_order WHERE STR_TO_DATE(substring_index(delivered_date,' ', 1), '%d-%m-%Y') between STR_TO_DATE(:fromDate, '%d-%m-%Y') and STR_TO_DATE(:toDate, '%d-%m-%Y') " + 
				" and order_status = 'Delivered' order by STR_TO_DATE(delivered_date, '%d-%m-%Y %h:%i:%s %p') desc";
		
		@SuppressWarnings("unchecked")
		List<Order> orderList = (List<Order>) ((SQLQuery) session.createSQLQuery(sql)											
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate))
					.addScalar("orderId", new IntegerType())
					.addScalar("mobileNo", new LongType())
					.addScalar("orderDate", new StringType())
					.addScalar("orderStatus", new StringType())
					.addScalar("orderType", new StringType())
					.addScalar("totalAmount", new StringType())
					.addScalar("paymentMethod", new StringType())
					.addScalar("deliveredDate", new StringType())		
					.addScalar("shippedDate", new StringType())
					.addScalar("canceledDate", new StringType())
					.addScalar("expectedDeliveryDate", new StringType())					
					.setResultTransformer(Transformers.aliasToBean(Order.class))
					.list() ;
		  
		  return orderList;		
	}
		
	@Override
	public List<Order> getCanceledCustomerOrderList(String fromDate, String toDate) throws Exception {
			
		session = sessionFactory.openSession();  
		tx      = session.beginTransaction();  
		
		String sql = " SELECT order_id as orderId, mobile_no as mobileNo, order_date as orderDate, order_status as orderStatus, order_type as orderType, total_amount as totalAmount, " + 
				" payment_method as paymentMethod, delivered_date as deliveredDate,shipped_date as shippedDate, canceled_date as canceledDate, expected_delivery_date as expectedDeliveryDate " + 
				" FROM tbl_order WHERE STR_TO_DATE(substring_index(canceled_date,' ', 1), '%d-%m-%Y') between STR_TO_DATE(:fromDate, '%d-%m-%Y') and STR_TO_DATE(:toDate, '%d-%m-%Y') " + 
				" and order_status = 'Canceled' order by STR_TO_DATE(canceled_date, '%d-%m-%Y %h:%i:%s %p') desc";
		
		@SuppressWarnings("unchecked")
		List<Order> orderList = (List<Order>) ((SQLQuery) session.createSQLQuery(sql)											
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate))
					.addScalar("orderId", new IntegerType())
					.addScalar("mobileNo", new LongType())
					.addScalar("orderDate", new StringType())
					.addScalar("orderStatus", new StringType())
					.addScalar("orderType", new StringType())
					.addScalar("totalAmount", new StringType())
					.addScalar("paymentMethod", new StringType())
					.addScalar("deliveredDate", new StringType())		
					.addScalar("shippedDate", new StringType())
					.addScalar("canceledDate", new StringType())
					.addScalar("expectedDeliveryDate", new StringType())					
					.setResultTransformer(Transformers.aliasToBean(Order.class))
					.list() ;
		  
		 return orderList;				
	}	
	
	@Override
	public List<Referral> getMyReferral(Long contactNo, String role) throws Exception {
		 
		 Session session = sessionFactory.openSession();  
		 Transaction tx  = session.beginTransaction();  
		 Query query = ((SQLQuery) session.createSQLQuery("CALL `proc_my_referral`(:contactNo, :role)")		
							  .setParameter("contactNo", contactNo)
							  .setParameter("role", role))
							  .addScalar("franchiseType", new StringType())
							  .addScalar("fullName", new StringType())
							  .addScalar("franchiseFirmName", new StringType())
							  .addScalar("franchiseContactNo", new LongType())
							  .addScalar("franchiseAddress", new StringType())
							  .addScalar("percentBenefit", new DoubleType());		
			
		@SuppressWarnings("unchecked")
		List<Referral> collectionList = query.setResultTransformer(Transformers.aliasToBean(Referral.class)).list();
		
		tx.commit();  
		session.close(); 
		
		return collectionList;
	}
	
	
	@Override
	public List<CommisionCalculation> getNationalCommisionBetweenDates(String fromDate, String toDate, int nationalFranchiseId) throws Exception {
		 
		 Session session = sessionFactory.openSession();  
		 Transaction tx  = session.beginTransaction();  
		 Query query = ((SQLQuery) session.createSQLQuery("CALL proc_national_franchise_commission(:fromDate, :toDate, :nationalFranchiseId)")		
							  .setParameter("fromDate", fromDate)
				 			  .setParameter("toDate", toDate)
				 			  .setParameter("nationalFranchiseId", nationalFranchiseId))
								 .addScalar("mobileNo", new StringType())
								 .addScalar("customerState", new IntegerType())
								 .addScalar("customerCity", new IntegerType())
								 .addScalar("customerTaluka", new IntegerType())
								 .addScalar("orderId", new IntegerType())
								 .addScalar("orderDate", new StringType())
								 .addScalar("productId", new IntegerType())
								 .addScalar("productTitle", new StringType())				 
					 			  .addScalar("productPrice", new DoubleType())
								  .addScalar("purchasePrice", new DoubleType())
								  .addScalar("profit", new DoubleType())
								  .addScalar("talukaFranchiseCommision", new DoubleType())
								  .addScalar("districtFranchiseCommision", new DoubleType())
								  .addScalar("stateFranchiseCommision", new DoubleType())
								  .addScalar("nationalFranchiseCommision", new DoubleType())
								  .addScalar("sellerId", new IntegerType())
									 .addScalar("shopName", new StringType());		
				
		@SuppressWarnings("unchecked")
		List<CommisionCalculation> collectionList = query.setResultTransformer(Transformers.aliasToBean(CommisionCalculation.class)).list();
		
		tx.commit();  
		session.close(); 
		
		return collectionList;
	}
	
	@Override
	public List<CommisionCalculation> getStateCommisionBetweenDates(String fromDate, String toDate, int stateCode, int stateFranchiseId) throws Exception {
		 
		 Session session = sessionFactory.openSession();  
		 Transaction tx  = session.beginTransaction();  
		 Query query = ((SQLQuery) session.createSQLQuery("CALL proc_state_franchise_commission(:fromDate, :toDate, :stateCode, :stateFranchiseId)")		
							  .setParameter("fromDate", fromDate)
				 			  .setParameter("toDate", toDate)
				 			  .setParameter("stateCode", stateCode)
				 			  .setParameter("stateFranchiseId", stateFranchiseId))
								 .addScalar("mobileNo", new StringType())
								 .addScalar("customerState", new IntegerType())
								 .addScalar("customerCity", new IntegerType())
								 .addScalar("customerTaluka", new IntegerType())
								 .addScalar("orderId", new IntegerType())
								 .addScalar("orderDate", new StringType())
								 .addScalar("productId", new IntegerType())
								 .addScalar("productTitle", new StringType())				 
					 			  .addScalar("productPrice", new DoubleType())
								  .addScalar("purchasePrice", new DoubleType())
								  .addScalar("profit", new DoubleType())
								  .addScalar("talukaFranchiseCommision", new DoubleType())
								  .addScalar("districtFranchiseCommision", new DoubleType())
								  .addScalar("stateFranchiseCommision", new DoubleType())
								  .addScalar("nationalFranchiseCommision", new DoubleType())
								  .addScalar("sellerId", new IntegerType())
									 .addScalar("shopName", new StringType());		
				
		@SuppressWarnings("unchecked")
		List<CommisionCalculation> collectionList = query.setResultTransformer(Transformers.aliasToBean(CommisionCalculation.class)).list();
		
		tx.commit();  
		session.close(); 
		
		return collectionList;
	}
	
	@Override
	public List<CommisionCalculation> getDistrictCommisionBetweenDates(String fromDate, String toDate, int districtCode, int districtFranchiseId) throws Exception {
		 
		 Session session = sessionFactory.openSession();  
		 Transaction tx  = session.beginTransaction();  
		 Query query = ((SQLQuery) session.createSQLQuery("CALL proc_district_franchise_commission(:fromDate, :toDate, :districtCode, :districtFranchiseId)")		
							  .setParameter("fromDate", fromDate)
				 			  .setParameter("toDate", toDate)
				 			  .setParameter("districtCode", districtCode)
				 			 .setParameter("districtFranchiseId", districtFranchiseId))
								 .addScalar("mobileNo", new StringType())
								 .addScalar("customerState", new IntegerType())
								 .addScalar("customerCity", new IntegerType())
								 .addScalar("customerTaluka", new IntegerType())
								 .addScalar("orderId", new IntegerType())
								 .addScalar("orderDate", new StringType())
								 .addScalar("productId", new IntegerType())
								 .addScalar("productTitle", new StringType())				 
					 			  .addScalar("productPrice", new DoubleType())
								  .addScalar("purchasePrice", new DoubleType())
								  .addScalar("profit", new DoubleType())
								  .addScalar("talukaFranchiseCommision", new DoubleType())
								  .addScalar("districtFranchiseCommision", new DoubleType())
								  .addScalar("stateFranchiseCommision", new DoubleType())
								  .addScalar("nationalFranchiseCommision", new DoubleType())
								  .addScalar("sellerId", new IntegerType())
									 .addScalar("shopName", new StringType());		
				
		@SuppressWarnings("unchecked")
		List<CommisionCalculation> collectionList = query.setResultTransformer(Transformers.aliasToBean(CommisionCalculation.class)).list();
		
		tx.commit();  
		session.close(); 
		
		return collectionList;
	}
	
	
	@Override
	public List<CommisionCalculation> getTalukaCommisionBetweenDates(String fromDate, String toDate, int talukaCode, int talukaFranchiseId) throws Exception {
		 
		 Session session = sessionFactory.openSession();  
		 Transaction tx  = session.beginTransaction();  
		 Query query = ((SQLQuery) session.createSQLQuery("CALL proc_taluka_franchise_commission(:fromDate, :toDate, :talukaCode, :talukaFranchiseId)")		
							  .setParameter("fromDate", fromDate)
				 			  .setParameter("toDate", toDate)
				 			  .setParameter("talukaCode", talukaCode)
				 			  .setParameter("talukaFranchiseId", talukaFranchiseId))
								 .addScalar("mobileNo", new StringType())
								 .addScalar("customerState", new IntegerType())
								 .addScalar("customerCity", new IntegerType())
								 .addScalar("customerTaluka", new IntegerType())
								 .addScalar("orderId", new IntegerType())
								 .addScalar("orderDate", new StringType())
								 .addScalar("productId", new IntegerType())
								 .addScalar("productTitle", new StringType())				 
					 			  .addScalar("productPrice", new DoubleType())
								  .addScalar("purchasePrice", new DoubleType())
								  .addScalar("profit", new DoubleType())
								  .addScalar("talukaFranchiseCommision", new DoubleType())
								  .addScalar("districtFranchiseCommision", new DoubleType())
								  .addScalar("stateFranchiseCommision", new DoubleType())
								  .addScalar("nationalFranchiseCommision", new DoubleType())
								  .addScalar("sellerId", new IntegerType())
									 .addScalar("shopName", new StringType());	 	
				
		@SuppressWarnings("unchecked")
		List<CommisionCalculation> collectionList = query.setResultTransformer(Transformers.aliasToBean(CommisionCalculation.class)).list();
		
		tx.commit();  
		session.close(); 
		
		return collectionList;
	}
	
	@Override
	public List<ReferralCommision> getReffaralCommisionBetweenDates(String fromDate, String toDate, String mobileNo) throws Exception {
		 
		 Session session = sessionFactory.openSession();  
		 Transaction tx  = session.beginTransaction();  
		 Query query = ((SQLQuery) session.createSQLQuery("CALL proc_referral_commision(:fromDate, :toDate, :mobileNo)")		
							  .setParameter("fromDate", fromDate)
				 			  .setParameter("toDate", toDate)
				 			  .setParameter("mobileNo", mobileNo))
								 .addScalar("mobileNo", new StringType())
								 .addScalar("customerState", new IntegerType())
								 .addScalar("customerCity", new IntegerType())
								 .addScalar("customerTaluka", new IntegerType())
								 .addScalar("orderId", new IntegerType())
								 .addScalar("orderDate", new StringType())
								 .addScalar("productId", new IntegerType())
								 .addScalar("productTitle", new StringType())				 
					 			 .addScalar("productPrice", new DoubleType())
								 .addScalar("per15Price", new DoubleType())
								 .addScalar("sellerId", new IntegerType())
								 .addScalar("shopName", new StringType())
								 .addScalar("referralId", new StringType())
								 .addScalar("referralRole", new StringType())
								 .addScalar("percentReferralCommision", new DoubleType());	 	
				
		@SuppressWarnings("unchecked")
		List<ReferralCommision> collectionList = query.setResultTransformer(Transformers.aliasToBean(ReferralCommision.class)).list();
		
		tx.commit();  
		session.close(); 
		
		return collectionList;
	}
}