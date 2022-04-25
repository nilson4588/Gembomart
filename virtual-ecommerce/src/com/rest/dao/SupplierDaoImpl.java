package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.Supplier;

@Repository
@Transactional
public class SupplierDaoImpl implements SupplierDao {

	@Autowired 
	SessionFactory sessionFactory;
	  
	Session session = null; 
	Transaction tx = null;
	
	@Override
	public int saveOrUpdateSupplier(Supplier supplier) throws Exception {
		// TODO Auto-generated method stub
		  int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(supplier); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag; 
	}

	@Override
	public Supplier getSupplierById(int supplierId) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();
		  Supplier supplier = (Supplier) session.load(Supplier.class, new Integer(supplierId));
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return supplier; 
	}

	@Override
	public List<Supplier> getSupplierList() throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Supplier> supplierList = session.createCriteria(Supplier.class).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return supplierList;
	}
	
	@Override 
	public Supplier getSupplierByMobileNo(long mobileNo) throws Exception {
		  // TODO Auto-generated method stub 
		  session = sessionFactory.openSession();
		  Supplier supplier = (Supplier) session.createCriteria(Supplier.class).add(Restrictions.eq("supplierContactNo", mobileNo)).uniqueResult();
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return supplier; 
	}
	
	@Override 
	public List<Supplier> getSupplierListOrderDatewise(String tdate) throws Exception {
		
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  String sql = " select supplier_id as supplierId, supplier_full_name as supplierFullName, supplier_email_id as supplierEmailId, supplier_dob as supplierDob, supplier_address as supplierAddress, supplier_contact_no as supplierContactNo, " + 
		  		"	supplier_whatsapp_no as supplierWhatsappNo, supplier_pincode as supplierPincode, bank_name as bankName, ifsc_code as ifscCode,account_number as accountNumber, pan_card_number as panCardNumber, aadhar_card_number as aadharCardNumber, supplier_products as supplierProducts, shop_name as shopName, latitude as latitude, longitude as longitude " + 
		  		"	from tbl_supplier where supplier_contact_no IN ( select distinct Mobile_number from tbl_supplier_order where SUBSTRING_INDEX(order_date,' ',1) = :tdate ) ";
		  
		  @SuppressWarnings("unchecked")
		  List<Supplier> supplierList = (List<Supplier>) ((SQLQuery) session.createSQLQuery(sql)
											.setParameter("tdate", tdate))	
				  							.addScalar("supplierId", new IntegerType())
											.addScalar("supplierFullName", new StringType())
											.addScalar("supplierEmailId", new StringType())
											.addScalar("supplierDob", new StringType())
											.addScalar("supplierAddress", new StringType())											
											.addScalar("supplierContactNo", new LongType())
											.addScalar("supplierWhatsappNo", new LongType())
											.addScalar("supplierPincode", new IntegerType())
											.addScalar("bankName", new StringType())
											.addScalar("ifscCode", new StringType())
											.addScalar("accountNumber", new StringType())
											.addScalar("aadharCardNumber", new StringType())
											.addScalar("supplierProducts", new StringType())
											.addScalar("shopName", new StringType())
											.addScalar("latitude", new StringType())
											.addScalar("longitude", new StringType())
											.setResultTransformer(Transformers.aliasToBean(Supplier.class))
											.list() ;		
		  tx.commit();  
		  session.close();	
		  
		  return supplierList;
	}
	
	@Override 
	public int activateOrDeactivateSupplier(Supplier supplier) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update Supplier set isActive = :isActive where supplierId = :supplierId ")
				  			   .setParameter("isActive", supplier.getIsActive())
				  			   .setParameter("supplierId", supplier.getSupplierId());
		  
		  int count = query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
		  
		  return count; 
	}
	
	
	@Override
	public List<Supplier> supplierListByFranchiseLocal(int localFranchiseId) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Supplier> supplierList = session.createCriteria(Supplier.class)
				  .add(Restrictions.eq("localFranchiseId", localFranchiseId)).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return supplierList;
	}
	
	
	@Override
	public List<Supplier> supplierListByFranchiseTaluka(int talukaFranchiseId) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Supplier> supplierList = session.createQuery("  select s.supplierId as supplierId, s.supplierFullName as supplierFullName, s.supplierDob as supplierDob, s.supplierEmailId as supplierEmailId, s.supplierAddress as supplierAddress, s.supplierContactNo as supplierContactNo, s.supplierWhatsappNo as supplierWhatsappNo, " + 
		  		" s.supplierPincode as supplierPincode, s.bankName as bankName, s.ifscCode as ifscCode, s.accountNumber as accountNumber, s.panCardNumber as panCardNumber, s.aadharCardNumber as aadharCardNumber, s.localFranchiseId as localFranchiseId, s.isActive as isActive, s.shopName as shopName, s.latitude as latitude, s.longitude as longitude " + 
		  		" from Supplier as s, FranchiseLocal as fl where s.localFranchiseId = fl.localFranchiseId	 and fl.talukaFranchiseId = :talukaFranchiseId ")
	  			   .setParameter("talukaFranchiseId", talukaFranchiseId).setResultTransformer(Transformers.aliasToBean(Supplier.class)).list();

		  tx.commit(); 
		  session.close();
		  
		  return supplierList;
	}
	
	@Override
	public List<Supplier> supplierListByFranchiseDistrict(int districtFranchiseId) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Supplier> supplierList = session.createQuery("  select s.supplierId as supplierId, s.supplierFullName as supplierFullName, s.supplierDob as supplierDob, s.supplierEmailId as supplierEmailId, s.supplierAddress as supplierAddress, s.supplierContactNo as supplierContactNo, s.supplierWhatsappNo as supplierWhatsappNo, " + 
		  		" s.supplierPincode as supplierPincode, s.bankName as bankName, s.ifscCode as ifscCode, s.accountNumber as accountNumber, s.panCardNumber as panCardNumber, s.aadharCardNumber as aadharCardNumber, s.localFranchiseId as localFranchiseId, s.isActive as isActive, s.shopName as shopName, s.latitude as latitude, s.longitude as longitude " + 
		  		" from Supplier as s, FranchiseLocal as fl, FranchiseTaluka as ft where s.localFranchiseId = fl.localFranchiseId and fl.talukaFranchiseId = ft.talukaFranchiseId and ft.districtFranchiseId = :districtFranchiseId ")
	  			   .setParameter("districtFranchiseId", districtFranchiseId).setResultTransformer(Transformers.aliasToBean(Supplier.class)).list();

		  tx.commit(); 
		  session.close();
		  
		  return supplierList;
	}
	
	@Override
	public Supplier getSupplierByMobileAndPassword(long mobileNumber, String password) throws Exception {
		  
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.getTransaction();  
		  
		  Supplier flocal = (Supplier) session.createCriteria(Supplier.class).add(Restrictions.eq("supplierContactNo", mobileNumber)).add(Restrictions.eq("supplierPassword", password)).uniqueResult();
		  
		  session.beginTransaction();  
		  tx.commit();  
		  
		  return flocal;
	}
}