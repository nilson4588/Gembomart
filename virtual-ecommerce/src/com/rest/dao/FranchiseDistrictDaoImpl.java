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

import com.rest.model.Districts;
import com.rest.model.FranchiseDistrict;
import com.rest.model.FranchiseTaluka;
import com.rest.model.FranchiseType;
import com.rest.model.SellerOrder;
import com.rest.model.States;
//import com.rest.model.States;
import com.rest.model.SupplierOrder;

@Repository
@Transactional
public class FranchiseDistrictDaoImpl implements FranchiseDistrictDao {

	@Autowired 
	SessionFactory sessionFactory;
	  
	Session session = null; 
	Transaction tx = null;
	
	@Override
	public int saveOrUpdateFranchiseDistrict(FranchiseDistrict franchiseDistrict) throws Exception {
		  // TODO Auto-generated method stub
		  int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(franchiseDistrict); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag; 
	}

	@Override
	public FranchiseDistrict getFranchiseDistrictById(int districtFranchiseId) throws Exception {
		 // TODO Auto-generated method stub
		  session = sessionFactory.openSession();
		  FranchiseDistrict franchise = (FranchiseDistrict) session.load(FranchiseDistrict.class, new Integer(districtFranchiseId));
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return franchise; 
	}

	@Override
	public List<FranchiseDistrict> getFranchiseDistrictList() throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<FranchiseDistrict> franchiseList = session.createCriteria(FranchiseDistrict.class).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return franchiseList;
	}

	@Override
	public int activateOrDeactivateFranchiseDistrict(FranchiseDistrict franchiseDistrict) throws Exception {		
		  // TODO Auto-generated method stub 
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update FranchiseDistrict set isActive = :isActive where districtFranchiseId = :districtFranchiseId ")
				  			   .setParameter("isActive", franchiseDistrict.getIsActive())
				  			   .setParameter("districtFranchiseId", franchiseDistrict.getDistrictFranchiseId());
		  
		  int count = query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
		  
		  return count; 
	}
	
	@Override
	public List<FranchiseType> getfranchiseTypeList() throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<FranchiseType> franchiseList = session.createCriteria(FranchiseType.class).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return franchiseList;
	}
	
	@Override
	public List<FranchiseTaluka> getTalukaFranchiseListByDistrict(int districtFranchiseId) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<FranchiseTaluka> franchiseList = session.createCriteria(FranchiseTaluka.class)
				  .add(Restrictions.eq("districtFranchiseId", districtFranchiseId)).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return franchiseList;
	}
	
	@Override
	public List<SupplierOrder> getSupplierOrderByFranchiseDistrict(int districtFranchiseId, String orderDate) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  		  
		  Query query = session.createQuery(" SELECT o.supplierOrderId as supplierOrderId, o.mobileNumber as mobileNumber, s.supplierFullName as supplierName, s.supplierAddress as supplierAddress, o.orderDate as orderDate, o.orderStatus as orderStatus, o.orderType as orderType, o.totalAmount as totalAmount, o.paymentMethod as paymentMethod, o.expectedDeliveryDate as expectedDeliveryDate, o.deliveredDate as expectedDeliveryDate, o.shippedDate as shippedDate, o.canceledDate as canceledDate " 
				  	+" FROM Supplier AS s, FranchiseLocal AS f, FranchiseTaluka AS t, SupplierOrder AS o WHERE f.localFranchiseId = s.localFranchiseId "
				  	+" AND o.mobileNumber = s.supplierContactNo AND f.talukaFranchiseId = t.talukaFranchiseId and t.districtFranchiseId = :districtFranchiseId AND f.isActive = 1 and o.orderDate like :orderDate ")
				  			   .setParameter("districtFranchiseId", districtFranchiseId)
				  			   .setParameter("orderDate", orderDate+"%");
		  
		  @SuppressWarnings("unchecked")
		  List<SupplierOrder> supplierOrderList = (List<SupplierOrder>) query.setResultTransformer(Transformers.aliasToBean(SupplierOrder.class)).list();
		  
		  tx.commit(); 
		  session.close();
		  
		  return supplierOrderList; 
	}	
	
	@Override
	public List<SellerOrder> getSellerOrderByFranchiseDistrict(int districtFranchiseId, String orderDate) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  		  
		  Query query = session.createQuery(" SELECT o.sellerOrderId as sellerOrderId, o.mobileNumber as mobileNumber, s.sellerFullName as sellerName, s.sellerAddress as sellerAddress, o.orderDate as orderDate, o.orderStatus as orderStatus, o.orderType as orderType, o.totalAmount as totalAmount, o.paymentMethod as paymentMethod, o.expectedDeliveryDate as expectedDeliveryDate, o.deliveredDate as expectedDeliveryDate, o.shippedDate as shippedDate, o.canceledDate as canceledDate " 
				  	+" FROM Seller AS s, FranchiseLocal AS f, FranchiseTaluka AS t, SellerOrder AS o WHERE f.localFranchiseId = s.localFranchiseId "
				  	+" AND o.mobileNumber = s.sellerContactNo AND f.talukaFranchiseId = t.talukaFranchiseId and t.districtFranchiseId = :districtFranchiseId AND f.isActive = 1 and o.orderDate like :orderDate ")
				  			   .setParameter("districtFranchiseId", districtFranchiseId)
				  			   .setParameter("orderDate", orderDate+"%");
		  
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerOrderList = (List<SellerOrder>) query.setResultTransformer(Transformers.aliasToBean(SellerOrder.class)).list();
		  
		  tx.commit(); 
		  session.close();
		  
		  return sellerOrderList; 
	}	
	
	@Override
	public List<SellerOrder> getDeliveryOrderByFranchiseDistrict(int districtFranchiseId, String orderDate) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  		  
		  Query query = session.createQuery(" SELECT o.sellerOrderId as sellerOrderId, o.mobileNumber as mobileNumber, s.sellerFullName as sellerName, s.sellerAddress as sellerAddress, o.orderDate as orderDate, o.orderStatus as orderStatus, o.orderType as orderType, o.totalAmount as totalAmount, o.paymentMethod as paymentMethod, o.expectedDeliveryDate as expectedDeliveryDate, o.deliveredDate as expectedDeliveryDate, o.shippedDate as shippedDate, o.canceledDate as canceledDate " 
				  	+" FROM Seller AS s, FranchiseLocal AS f, FranchiseTaluka AS t, SellerOrder AS o WHERE f.localFranchiseId = s.localFranchiseId "
				  	+" AND o.mobileNumber = s.sellerContactNo AND f.talukaFranchiseId = t.talukaFranchiseId and t.districtFranchiseId = :districtFranchiseId AND f.isActive = 1 and o.orderDate like :orderDate ")
				  			   .setParameter("districtFranchiseId", districtFranchiseId)
				  			   .setParameter("orderDate", orderDate+"%");
		  
		  @SuppressWarnings("unchecked")
		  List<SellerOrder> sellerOrderList = (List<SellerOrder>) query.setResultTransformer(Transformers.aliasToBean(SellerOrder.class)).list();
		  
		  tx.commit(); 
		  session.close();
		  
		  return sellerOrderList; 
	}
	
	@Override
	public FranchiseDistrict getFranchiseDistrictByMobileAndPassword(long mobileNumber, String password) throws Exception {
		  
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.getTransaction();  
		  
		  FranchiseDistrict flocal = (FranchiseDistrict) session.createCriteria(FranchiseDistrict.class).add(Restrictions.eq("franchiseContactNo", mobileNumber)).add(Restrictions.eq("districtFranchisePassword", password)).uniqueResult();
		  
		  session.beginTransaction();  
		  tx.commit();  
		  
		  return flocal;
	}
	
	@Override 
	public FranchiseDistrict getFranchiseDistrictByMobileNo(long franchiseContactNo) throws Exception {
		
		  // TODO Auto-generated method stub 
		  session = sessionFactory.openSession();
		  tx = session.getTransaction();
		  
		  FranchiseDistrict franchiseLocal = (FranchiseDistrict) session.createCriteria(FranchiseDistrict.class).add(Restrictions.eq("franchiseContactNo", franchiseContactNo)).uniqueResult();
	    	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return franchiseLocal; 
	}
	
	@Override 
	public List<FranchiseDistrict> getFranchiseDistrictListByNationalId(int nationalFranchiseId) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<FranchiseDistrict> franchiseList = session.createCriteria(FranchiseDistrict.class)
				  .add(Restrictions.eq("nationalFranchiseId", nationalFranchiseId)).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return franchiseList;
	}
	
	
	@Override
	public List<Districts> districtListByCategoryIdStateCode(int categoryId, int stateCode) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createSQLQuery("  select district_id as districtId, district_code as districtCode, district_name as districtName, state_code as stateCode from tbl_districts where district_code not in ( "
		  		+ "	 select ft.district_code from tbl_franchise_national fn, tbl_franchise_district fd, tbl_franchise_taluka ft "
		  		+ "	 where fn.national_franchise_id = fd.national_franchise_id and fd.district_franchise_id = ft.district_franchise_id "
		  		+ "	 and fn.category_id= :categoryId )  and state_code = :stateCode  ")
				.setParameter("categoryId", categoryId)
				.setParameter("stateCode", stateCode);
		  
		  @SuppressWarnings("unchecked")
		  List<Districts> districtsList = (List<Districts>)query.setResultTransformer(Transformers.aliasToBean(Districts.class)).list();
		  
		  tx.commit(); 
		  session.close();
		  
		  return districtsList; 
	}		
	
	@Override
	public int getCategoryIdByTalukaFranchiseId(int talukaFranchiseId) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createSQLQuery("  select category_id from tbl_franchise_national fn, tbl_franchise_district fd, tbl_franchise_taluka ft where fn.national_franchise_id = fd.national_franchise_id "
		  		+ "and fd.district_franchise_id = ft.district_franchise_id and ft.taluka_franchise_id = :talukaFranchiseId ")
				.setParameter("talukaFranchiseId", talukaFranchiseId);
		  
		  int categoryId = (int) query.uniqueResult();
		  
		  tx.commit(); 
		  session.close();
		  
		  return categoryId; 
	}	
	
	
	@Override 
	public String getDistrictNameByCode(int districtCode) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  String districtName = (String) session.createCriteria(Districts.class)
				  .add(Restrictions.eq("districtCode", districtCode))
				  .setProjection(Projections.property("districtName"))
				  .uniqueResult();
		  
		  tx.commit(); 
		  session.close();
		  
		  return districtName;
	}
	
	@Override 
	public String getStateNameByCode(int stateCode) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  String stateName = (String) session.createCriteria(States.class)
				  .add(Restrictions.eq("stateCode", stateCode))
				  .setProjection(Projections.property("stateName"))
				  .uniqueResult();
		  
		  tx.commit(); 
		  session.close();
		  
		  return stateName;
	}
}