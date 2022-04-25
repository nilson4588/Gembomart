package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.FranchiseLocal;
import com.rest.model.FranchiseTaluka;
import com.rest.model.SellerOrder;
import com.rest.model.SupplierOrder;
import com.rest.model.Taluka;

@Repository
@Transactional
public class FranchiseTalukaDaoImpl implements FranchiseTalukaDao {

	@Autowired 
	SessionFactory sessionFactory;
	  
	Session session = null; 
	Transaction tx = null;
	
	@Override
	public int saveOrUpdateFranchiseTaluka(FranchiseTaluka franchiseTaluka) throws Exception {
		// TODO Auto-generated method stub
		  int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(franchiseTaluka); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag; 
	}

	@Override
	public FranchiseTaluka getFranchiseTalukaById(int talukaFranchiseId) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();
		  FranchiseTaluka franchise = (FranchiseTaluka) session.load(FranchiseTaluka.class, new Integer(talukaFranchiseId));
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return franchise; 
	}

	@Override
	public List<FranchiseTaluka> getFranchiseTalukaList() throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<FranchiseTaluka> franchiseList = session.createCriteria(FranchiseTaluka.class).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return franchiseList;
	}

	@Override
	public int activateOrDeactivateFranchiseTaluka(FranchiseTaluka franchiseTaluka) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update FranchiseTaluka set isActive = :isActive where talukaFranchiseId = :talukaFranchiseId ")
				  			   .setParameter("isActive", franchiseTaluka.getIsActive())
				  			   .setParameter("talukaFranchiseId", franchiseTaluka.getTalukaFranchiseId());
		  
		  int count = query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
		  
		  return count; 
	}
	
	@Override
	public List<FranchiseLocal> getLocalFranchiseListByTaluka(int talukaFranchiseId) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<FranchiseLocal> franchiseList = session.createCriteria(FranchiseLocal.class)
				  .add(Restrictions.eq("talukaFranchiseId", talukaFranchiseId)).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return franchiseList;
	}
		
	@Override
	public List<SupplierOrder> getSupplierOrderByFranchiseTaluka(int talukaFranchiseId, String orderDate) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  		  
		  Query query = session.createQuery(" SELECT o.supplierOrderId as supplierOrderId, o.mobileNumber as mobileNumber, s.supplierFullName as supplierName, s.supplierAddress as supplierAddress, o.orderDate as orderDate, o.orderStatus as orderStatus, o.orderType as orderType, o.totalAmount as totalAmount, o.paymentMethod as paymentMethod, o.expectedDeliveryDate as expectedDeliveryDate, o.deliveredDate as expectedDeliveryDate, o.shippedDate as shippedDate, o.canceledDate as canceledDate " 
				  	+" FROM Supplier AS s, FranchiseLocal AS f, SupplierOrder AS o WHERE f.localFranchiseId = s.localFranchiseId "
				  	+" AND o.mobileNumber = s.supplierContactNo AND f.talukaFranchiseId = :talukaFranchiseId AND f.isActive = 1 and o.orderDate like :orderDate ")
				  			   .setParameter("talukaFranchiseId", talukaFranchiseId)
				  			   .setParameter("orderDate", orderDate+"%");
		  
		  @SuppressWarnings("unchecked")
		  List<SupplierOrder> supplierOrderList = (List<SupplierOrder>) query.setResultTransformer(Transformers.aliasToBean(SupplierOrder.class)).list();
		  
		  tx.commit(); 
		  session.close();
		  
		  return supplierOrderList; 
	}	
	
	@Override
	public List<SellerOrder> getSellerOrderByFranchiseTaluka(int talukaFranchiseId, String orderDate) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  		  
		  Query query = session.createQuery(" SELECT o.sellerOrderId as sellerOrderId, o.mobileNumber as mobileNumber, s.sellerFullName as sellerName, s.sellerAddress as sellerAddress, o.orderDate as orderDate, o.orderStatus as orderStatus, o.orderType as orderType, o.totalAmount as totalAmount, o.paymentMethod as paymentMethod, o.expectedDeliveryDate as expectedDeliveryDate, o.deliveredDate as expectedDeliveryDate, o.shippedDate as shippedDate, o.canceledDate as canceledDate " 
				  	+" FROM Seller AS s, FranchiseLocal AS f, SellerOrder AS o WHERE f.localFranchiseId = s.localFranchiseId "
				  	+" AND o.mobileNumber = s.sellerContactNo AND f.talukaFranchiseId = :talukaFranchiseId AND f.isActive = 1 and o.orderDate like :orderDate ")
				  			   .setParameter("talukaFranchiseId", talukaFranchiseId)
				  			   .setParameter("orderDate", orderDate+"%");
		  
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerOrderList = (List<SellerOrder>) query.setResultTransformer(Transformers.aliasToBean(SellerOrder.class)).list();
		  
		  tx.commit(); 
		  session.close();
		  
		  return sellerOrderList; 
	}	
	
	@Override
	public List<SellerOrder> getDeliveryOrderByFranchiseTaluka(int talukaFranchiseId, String orderDate) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  		  
		  Query query = session.createQuery(" SELECT o.sellerOrderId as sellerOrderId, o.mobileNumber as mobileNumber, s.sellerFullName as sellerName, s.sellerAddress as sellerAddress, o.orderDate as orderDate, o.orderStatus as orderStatus, o.orderType as orderType, o.totalAmount as totalAmount, o.paymentMethod as paymentMethod, o.expectedDeliveryDate as expectedDeliveryDate, o.deliveredDate as expectedDeliveryDate, o.shippedDate as shippedDate, o.canceledDate as canceledDate " 
				  	+" FROM Seller AS s, FranchiseLocal AS f, SellerOrder AS o WHERE f.localFranchiseId = s.localFranchiseId "
				  	+" AND o.mobileNumber = s.sellerContactNo AND f.talukaFranchiseId = :talukaFranchiseId AND f.isActive = 1 and o.orderDate like :orderDate ")
				  			   .setParameter("talukaFranchiseId", talukaFranchiseId)
				  			   .setParameter("orderDate", orderDate+"%");
		  
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerOrderList = (List<SellerOrder>) query.setResultTransformer(Transformers.aliasToBean(SellerOrder.class)).list();
		  
		  tx.commit(); 
		  session.close();
		  
		  return sellerOrderList; 
	}	
	
	
	@Override
	public FranchiseTaluka getFranchiseTalukaByMobileAndPassword(long mobileNumber, String password) throws Exception {
		  
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.getTransaction();  
		  
		  FranchiseTaluka flocal = (FranchiseTaluka) session.createCriteria(FranchiseTaluka.class).add(Restrictions.eq("franchiseContactNo", mobileNumber)).add(Restrictions.eq("talukaFranchisePassword", password)).uniqueResult();
		  
		  session.beginTransaction();  
		  tx.commit();  
		  
		  return flocal;
	}
	
	@Override 
	public FranchiseTaluka getFranchiseTalukaByMobileNo(long franchiseContactNo) throws Exception {
		
		  // TODO Auto-generated method stub 
		  session = sessionFactory.openSession();
		  tx = session.getTransaction();
		  
		  FranchiseTaluka franchiseTaluka = (FranchiseTaluka) session.createCriteria(FranchiseTaluka.class).add(Restrictions.eq("franchiseContactNo", franchiseContactNo)).uniqueResult();
	    	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return franchiseTaluka; 
	}
	
	
	@Override
	public List<Taluka> talukaListByCategoryIdDistrictCode(int categoryId, int districtCode) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createSQLQuery("  select taluka_id as talukaId, taluka_code as talukaCode, taluka_name as talukaName, state_code as stateCode, district_code as districtCode from tbl_taluka where taluka_code NOT IN ( "
		  		+ "  select fl.taluka_code from tbl_franchise_national fn, tbl_franchise_district fd, tbl_franchise_taluka ft, tbl_franchise_local fl "
		  		+ "	 where fn.national_franchise_id = fd.national_franchise_id and fd.district_franchise_id = ft.district_franchise_id  and ft.taluka_franchise_id = fl.taluka_franchise_id "
		  		+ "	 and fn.category_id= :categoryId ) and district_code = :districtCode ")
				.setParameter("categoryId", categoryId)
				.setParameter("districtCode", districtCode);
		  
		  @SuppressWarnings("unchecked")
		  List<Taluka> talukaList = (List<Taluka>)query.setResultTransformer(Transformers.aliasToBean(Taluka.class)).list();
		  
		  tx.commit(); 
		  session.close();
		  
		  return talukaList; 
	}	
	
	
	@Override 
	public String getTalukaNameByCode(int talukaCode) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  String talukaName = (String) session.createCriteria(Taluka.class)
				  .add(Restrictions.eq("talukaCode", talukaCode))
				  .setProjection(Projections.property("talukaName"))
				  .uniqueResult();
		  
		  tx.commit(); 
		  session.close();
		  
		  return talukaName;
	}
}