package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.Seller;


@Repository
@Transactional
public class SellerDaoImpl implements SellerDao {

	@Autowired 
	SessionFactory sessionFactory;
	  
	Session session = null; 
	Transaction tx = null;
	
	@Override
	public int saveOrUpdateSeller(Seller seller) throws Exception {
		// TODO Auto-generated method stub
		  int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(seller); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag; 
	}

	@Override
	public Seller getSellerById(int sellerId) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();
		  Seller seller = (Seller) session.load(Seller.class, new Integer(sellerId));
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return seller; 
	}

	@Override
	public List<Seller> getSellerList() throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Seller> sellerList = session.createCriteria(Seller.class).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return sellerList;
	}
	
	@Override 
	public Seller getSellerByMobileNo(long mobileNo) throws Exception {
		  // TODO Auto-generated method stub 
		  session = sessionFactory.openSession();
		  tx = session.getTransaction();
		  
		  Seller seller = (Seller) session.createCriteria(Seller.class).add(Restrictions.eq("sellerContactNo", mobileNo)).uniqueResult();
	      	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return seller; 
	}
	
	@Override 
	public int activateOrDeactivateSeller(Seller seller) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update Seller set isActive = :isActive where sellerId = :sellerId ")
				  			   .setParameter("isActive", seller.getIsActive())
				  			   .setParameter("sellerId", seller.getSellerId());
		  
		  int count = query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
		  
		  return count; 
	}
	
	@Override
	public List<Seller> sellerListByFranchiseLocal(int localFranchiseId) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Seller> sellerList = session.createCriteria(Seller.class)
				  .add(Restrictions.eq("localFranchiseId", localFranchiseId)).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return sellerList;
	}
	
	
	@Override
	public List<Seller> sellerListByFranchiseTaluka(int talukaFranchiseId) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Seller> sellerList = session.createQuery("  select s.sellerId as sellerId, s.sellerFullName as sellerFullName, s.sellerDob as sellerDob, s.sellerEmailId as sellerEmailId, s.sellerAddress as sellerAddress, s.sellerContactNo as sellerContactNo, s.sellerWhatsappNo as sellerWhatsappNo, " + 
		  		" s.sellerPincode as sellerPincode, s.bankName as bankName, s.ifscCode as ifscCode, s.accountNumber as accountNumber, s.panCardNumber as panCardNumber, s.aadharCardNumber as aadharCardNumber, s.sellerCode as sellerCode, s.localFranchiseId as localFranchiseId, s.isActive as isActive, s.shopName as shopName, s.latitude as latitude, s.longitude as longitude " + 
		  		" from Seller as s, FranchiseLocal as fl where s.localFranchiseId = fl.localFranchiseId	 and fl.talukaFranchiseId = :talukaFranchiseId ")
	  			   .setParameter("talukaFranchiseId", talukaFranchiseId).setResultTransformer(Transformers.aliasToBean(Seller.class)).list();

		  tx.commit(); 
		  session.close();
		  
		  return sellerList;
	}
	
	@Override
	public List<Seller> sellerListByFranchiseDistrict(int districtFranchiseId) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Seller> sellerList = session.createQuery("  select s.sellerId as sellerId, s.sellerFullName as sellerFullName, s.sellerDob as sellerDob, s.sellerEmailId as sellerEmailId, s.sellerAddress as sellerAddress, s.sellerContactNo as sellerContactNo, s.sellerWhatsappNo as sellerWhatsappNo, " + 
		  		" s.sellerPincode as sellerPincode, s.bankName as bankName, s.ifscCode as ifscCode, s.accountNumber as accountNumber, s.panCardNumber as panCardNumber, s.aadharCardNumber as aadharCardNumber, s.sellerCode as sellerCode, s.localFranchiseId as localFranchiseId, s.isActive as isActive, s.shopName as shopName, s.latitude as latitude, s.longitude as longitude " +  
		  		" from Seller as s, FranchiseLocal as fl, FranchiseTaluka as ft where s.localFranchiseId = fl.localFranchiseId and fl.talukaFranchiseId = ft.talukaFranchiseId and ft.districtFranchiseId = :districtFranchiseId ")
	  			   .setParameter("districtFranchiseId", districtFranchiseId).setResultTransformer(Transformers.aliasToBean(Seller.class)).list();

		  tx.commit(); 
		  session.close();
		  
		  return sellerList;
	}
	
	@Override
	public Seller getSellerByMobileAndPassword(long mobileNumber, String password) throws Exception {
		  
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.getTransaction();  
		  
		  Seller flocal = (Seller) session.createCriteria(Seller.class).add(Restrictions.eq("sellerContactNo", mobileNumber)).add(Restrictions.eq("sellerPassword", password)).uniqueResult();
		  
		  session.beginTransaction();  
		  tx.commit();  
		  
		  return flocal;
	}
	
	
	@Override
	public String getShopNameBySellerId(int sellerId) throws Exception {
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		String hql = " select shopName as shopName from Seller where sellerId = :sellerId ";
		
		String shopName =  (String)session.createQuery(hql).setInteger("sellerId", sellerId).uniqueResult();
		
		tx.commit();
		session.close();
		
		return shopName;
	}
	
	
	@Override 
	public int updateSellerToken(Seller seller) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update Seller set token = :token where sellerId = :sellerId ")
				  			   .setParameter("token", seller.getToken())
				  			   .setParameter("sellerId", seller.getSellerId());
		  
		  int count = query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
		  
		  return count; 
	}
	
	
	@Override
	public String getTokenBySellerId(int sellerId) throws Exception {
		
		session    = sessionFactory.openSession();
		tx         = session.beginTransaction();
		String id     = "";
		
		Criteria criteria = session.createCriteria(Seller.class)
				  .add(Restrictions.eq("sellerId", sellerId))
				  .setProjection(Projections.property("token"));
			
		if(criteria.uniqueResult() != null){
			 id     = (String) criteria.uniqueResult();
		}
		
	    tx.commit();
	    session.close();  
	   
		return  id;	
	}
	
	@Override
	public int getSellerCurrentStatus(int sellerId) throws Exception {
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		int status = 0;		
		
		Criteria criteria = session.createCriteria(Seller.class)
				  .add(Restrictions.eq("sellerId", sellerId))
				  .setProjection(Projections.property("isActive"));
			
		if(criteria.uniqueResult() != null){
			status     = (int) criteria.uniqueResult();
		}
		
		tx.commit();
		session.close();
		
		return status;
	}
	
	
	@Override
	public List<Seller> getSellerListByArea(int talukaCode) throws Exception {
		  
		 session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Seller> sellerlist = session.createQuery(" select s.sellerId as sellerId, s.sellerFullName as sellerFullName, s.sellerDob as sellerDob, s.sellerEmailId as sellerEmailId, s.sellerAddress as sellerAddress, s.sellerContactNo as sellerContactNo, s.sellerWhatsappNo as sellerWhatsappNo, " + 
			  		" s.sellerPincode as sellerPincode, s.bankName as bankName, s.ifscCode as ifscCode, s.accountNumber as accountNumber, s.panCardNumber as panCardNumber, s.aadharCardNumber as aadharCardNumber, s.sellerCode as sellerCode, s.localFranchiseId as localFranchiseId, s.isActive as isActive, s.shopName as shopName, s.latitude as latitude, s.longitude as longitude "
				+ " FROM Seller as s, FranchiseLocal as t, Taluka as tk where s.localFranchiseId = t.localFranchiseId and  t.talukaCode = tk.talukaCode and s.isActive=1 and tk.talukaCode = :talukaCode  ")	  			 
	  			   .setParameter("talukaCode", talukaCode)
	  			   .setResultTransformer(Transformers.aliasToBean(Seller.class)).list();

		  tx.commit(); 
		  session.close();
		  
		  return sellerlist;
	}
	
	@Override
	public int getTalukaCodeBySellerId(int sellerId) throws Exception {
		  
		 session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  int talukaCode = (int) session.createQuery(
		  " select tk.talukaCode FROM Seller as s, FranchiseLocal as t, Taluka as tk where s.localFranchiseId = t.localFranchiseId and  t.talukaCode = tk.talukaCode and s.isActive=1 and s.sellerId = :sellerId  ")	  			 
	  			   .setParameter("sellerId", sellerId).uniqueResult();

		  tx.commit(); 
		  session.close();
		  
		  return talukaCode;
	}	
}