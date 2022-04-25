package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.FranchiseLocal;
import com.rest.model.SellerOrder;
import com.rest.model.SupplierOrder;

@Repository
@Transactional
public class FranchiseLocalDaoImpl implements FranchiseLocalDao {

	@Autowired 
	SessionFactory sessionFactory;
	  
	Session session = null; 
	Transaction tx = null;
	
	@Override
	public int saveOrUpdateFranchiseLocal(FranchiseLocal franchiseLocal) throws Exception {
		// TODO Auto-generated method stub
		  int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(franchiseLocal); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag; 
	}

	@Override
	public FranchiseLocal getFranchiseLocalById(int localFranchiseId) throws Exception {
		  // TODO Auto-generated method stub
		
		  session = sessionFactory.openSession();  
		  tx = session.getTransaction();  
		  
		  FranchiseLocal flocal = (FranchiseLocal) session.createCriteria(FranchiseLocal.class)
				  .add(Restrictions.eq("localFranchiseId", localFranchiseId))
				  .uniqueResult();
		  
		  session.beginTransaction();  
		  tx.commit();  
		  
		  return flocal;
		
			/*
			 * session = sessionFactory.openSession(); tx = session.beginTransaction();
			 * 
			 * FranchiseLocal franchise = (FranchiseLocal)
			 * session.load(FranchiseLocal.class, new Integer(localFranchiseId));
			 * 
			 * tx.commit(); session.close();
			 * 
			 * return franchise;
			 */
	}

	@Override
	public List<FranchiseLocal> getFranchiseLocalList() throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<FranchiseLocal> franchiseList = session.createCriteria(FranchiseLocal.class).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return franchiseList;
	}

	@Override
	public int activateOrDeactivateFranchiseLocal(FranchiseLocal franchiseLocal) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update FranchiseLocal set isActive = :isActive where localFranchiseId = :localFranchiseId ")
				  			   .setParameter("isActive", franchiseLocal.getIsActive())
				  			   .setParameter("localFranchiseId", franchiseLocal.getLocalFranchiseId());
		  
		  int count = query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
		  
		  return count; 
	}
	
	@Override
	public List<SupplierOrder> getSupplierOrderByFranchiseLocal(int localFranchiseId, String orderDate) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  		  
		  Query query = session.createQuery(" SELECT o.supplierOrderId as supplierOrderId, o.mobileNumber as mobileNumber, s.supplierFullName as supplierName, s.supplierAddress as supplierAddress, o.orderDate as orderDate, o.orderStatus as orderStatus, o.orderType as orderType, o.totalAmount as totalAmount, o.paymentMethod as paymentMethod, o.expectedDeliveryDate as expectedDeliveryDate, o.deliveredDate as expectedDeliveryDate, o.shippedDate as shippedDate, o.canceledDate as canceledDate " 
				  	+" FROM Supplier AS s, FranchiseLocal AS f, SupplierOrder AS o WHERE f.localFranchiseId = s.localFranchiseId "
				  	+" AND o.mobileNumber = s.supplierContactNo AND f.localFranchiseId = :localFranchiseId AND f.isActive = 1 and o.orderDate like :orderDate ")
				  			   .setParameter("localFranchiseId", localFranchiseId)
				  			   .setParameter("orderDate", orderDate+"%");
		  
		  @SuppressWarnings("unchecked")
		  List<SupplierOrder> supplierOrderList = (List<SupplierOrder>) query.setResultTransformer(Transformers.aliasToBean(SupplierOrder.class)).list();
		  
		  tx.commit(); 
		  session.close();
		  
		  return supplierOrderList; 
	}	
	
	@Override
	public List<SellerOrder> getSellerOrderByFranchiseLocal(int localFranchiseId, String orderDate) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  		  
		  Query query = session.createQuery(" SELECT o.sellerOrderId as sellerOrderId, o.mobileNumber as mobileNumber, s.sellerFullName as sellerName, s.sellerAddress as sellerAddress, o.orderDate as orderDate, o.orderStatus as orderStatus, o.orderType as orderType, o.totalAmount as totalAmount, o.paymentMethod as paymentMethod, o.expectedDeliveryDate as expectedDeliveryDate, o.deliveredDate as expectedDeliveryDate, o.shippedDate as shippedDate, o.canceledDate as canceledDate " 
				  	+" FROM Seller AS s, FranchiseLocal AS f, SellerOrder AS o WHERE f.localFranchiseId = s.localFranchiseId "
				  	+" AND o.mobileNumber = s.sellerContactNo AND f.localFranchiseId = :localFranchiseId AND f.isActive = 1 and o.orderDate like :orderDate ")
				  			   .setParameter("localFranchiseId", localFranchiseId)
				  			   .setParameter("orderDate", orderDate+"%");
		  
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerOrderList = (List<SellerOrder>) query.setResultTransformer(Transformers.aliasToBean(SellerOrder.class)).list();
		  
		  tx.commit(); 
		  session.close();
		  
		  return sellerOrderList; 
	}	
	
	@Override
	public List<SellerOrder> getDeliveryOrderByFranchiseLocal(int localFranchiseId, String orderDate) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  		  
		  Query query = session.createQuery(" SELECT o.sellerOrderId as sellerOrderId, o.mobileNumber as mobileNumber, s.sellerFullName as sellerName, s.sellerAddress as sellerAddress, o.orderDate as orderDate, o.orderStatus as orderStatus, o.orderType as orderType, o.totalAmount as totalAmount, o.paymentMethod as paymentMethod, o.expectedDeliveryDate as expectedDeliveryDate, o.deliveredDate as expectedDeliveryDate, o.shippedDate as shippedDate, o.canceledDate as canceledDate " 
				  	+" FROM Seller AS s, FranchiseLocal AS f, SellerOrder AS o WHERE f.localFranchiseId = s.localFranchiseId "
				  	+" AND o.mobileNumber = s.sellerContactNo AND f.localFranchiseId = :localFranchiseId AND f.isActive = 1 and o.orderDate like :orderDate ")
				  			   .setParameter("localFranchiseId", localFranchiseId)
				  			   .setParameter("orderDate", orderDate+"%");
		  
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerOrderList = (List<SellerOrder>) query.setResultTransformer(Transformers.aliasToBean(SellerOrder.class)).list();
		  
		  tx.commit(); 
		  session.close();
		  
		  return sellerOrderList; 
	}	
	
	@Override
	public FranchiseLocal getFranchiseLocalByMobileAndPassword(long mobileNumber, String password) throws Exception {
		  
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.getTransaction();  
		  
		  FranchiseLocal flocal = (FranchiseLocal) session.createCriteria(FranchiseLocal.class).add(Restrictions.eq("franchiseContactNo", mobileNumber)).add(Restrictions.eq("localFranchisePassword", password)).uniqueResult();
		  
		  session.beginTransaction();  
		  tx.commit();  
		  
		  return flocal;
	}
	
	@Override 
	public FranchiseLocal getFranchiseLocalByMobileNo(long franchiseContactNo) throws Exception {
		
		  // TODO Auto-generated method stub 
		  session = sessionFactory.openSession();
		  tx = session.getTransaction();
		  
		  FranchiseLocal franchiseLocal = (FranchiseLocal) session.createCriteria(FranchiseLocal.class).add(Restrictions.eq("franchiseContactNo", franchiseContactNo)).uniqueResult();
	    	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return franchiseLocal; 
	}
	
	@Override
	public String getCategoryNameByFranchiseLocal(int localFranchiseId) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  		  
		  Query query = session.createQuery("  SELECT c.categoryName FROM FranchiseNational as fn, FranchiseDistrict as fd, FranchiseTaluka as ft, FranchiseLocal as fl, Category as c "
		  		+ " where fn.categoryId = c.id and fd.nationalFranchiseId = fn.nationalFranchiseId and fd.districtFranchiseId = ft.districtFranchiseId "
		  		+ " and ft.talukaFranchiseId = fl.talukaFranchiseId and fl.localFranchiseId = :localFranchiseId ")
				  			   .setParameter("localFranchiseId", localFranchiseId);
				  			  
		  
		  String categoryName = (String) query.uniqueResult();
		  
		  tx.commit(); 
		  session.close();
		  
		  return categoryName; 
	}	
	
	@Override
	public List<FranchiseLocal> getFranchiseLocalListByTaluka(int talukaCode) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<FranchiseLocal> franchiseList = session.createCriteria(FranchiseLocal.class)
		  .add(Restrictions.eq("talukaCode", talukaCode))							
		  .list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return franchiseList;
	}
}