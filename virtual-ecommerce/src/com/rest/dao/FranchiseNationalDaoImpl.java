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

import com.rest.model.FranchiseNational;
import com.rest.model.States;

@Repository
@Transactional
public class FranchiseNationalDaoImpl implements FranchiseNationalDao {

	@Autowired 
	SessionFactory sessionFactory;
	  
	Session session = null; 
	Transaction tx = null;
	
	@Override
	public int saveOrUpdateFranchiseNational(FranchiseNational franchiseNational) throws Exception {
		// TODO Auto-generated method stub
		  int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(franchiseNational); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag; 
	}

	@Override
	public FranchiseNational getFranchiseNationalById(int nationalFranchiseId) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();
		  FranchiseNational franchise = (FranchiseNational) session.load(FranchiseNational.class, new Integer(nationalFranchiseId));
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return franchise; 
	}

	@Override
	public List<FranchiseNational> getFranchiseNationalList() throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<FranchiseNational> franchiseList = session.createCriteria(FranchiseNational.class).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return franchiseList;
	}

	@Override
	public int activateOrDeactivateFranchiseNational(FranchiseNational franchiseNational) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update FranchiseNational set isActive = :isActive where nationalFranchiseId = :nationalFranchiseId ")
				  			   .setParameter("isActive", franchiseNational.getIsActive())
				  			   .setParameter("nationalFranchiseId", franchiseNational.getNationalFranchiseId());
		  
		  int count = query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
		  
		  return count; 
	}

	/*
	 * @Override public List<SupplierOrder> getSupplierOrderByFranchiseNational(int
	 * nationalFranchiseId, String orderDate) throws Exception { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public List<SellerOrder> getSellerOrderByFranchiseNational(int
	 * nationalFranchiseId, String orderDate) throws Exception { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public List<SellerOrder> getDeliveryOrderByFranchiseNational(int
	 * nationalFranchiseId, String orderDate) throws Exception { // TODO
	 * Auto-generated method stub return null; }
	 */

	@Override
	public FranchiseNational getFranchiseNationalByMobileAndPassword(long mobileNumber, String password)
			throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.getTransaction();  
		  
		  FranchiseNational flocal = (FranchiseNational) session.createCriteria(FranchiseNational.class).add(Restrictions.eq("franchiseContactNo", mobileNumber)).add(Restrictions.eq("nationalFranchisePassword", password)).uniqueResult();
		  
		  session.beginTransaction();  
		  tx.commit();  
		  
		  return flocal;
	}
	
	@Override 
	public FranchiseNational getFranchiseNationalByMobileNo(long franchiseContactNo) throws Exception {
		
		  // TODO Auto-generated method stub 
		  session = sessionFactory.openSession();
		  tx = session.getTransaction();
		  
		  FranchiseNational franchiseNational = (FranchiseNational) session.createCriteria(FranchiseNational.class).add(Restrictions.eq("franchiseContactNo", franchiseContactNo)).uniqueResult();
	    	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return franchiseNational; 
	}

	@Override
	public String getFranchiseNameById(int nationalFranchiseId) throws Exception {
		
		session    = sessionFactory.openSession();
		tx         = session.beginTransaction();
		String franchise = "";
		
		Criteria criteria = session.createCriteria(FranchiseNational.class)
				  .add(Restrictions.eq("nationalFranchiseId", nationalFranchiseId))
				  .setProjection(Projections.property("fullName"));
			
		if(criteria.uniqueResult() != null){
			franchise     = (String) criteria.uniqueResult();
		}
		
	    tx.commit();
	    session.close();  
	   
		return  franchise;		
		
	}
	
	@Override
	public int getCategoryIdByNationalFranchiseId(int nationalFranchiseId) throws Exception {
		
		session    = sessionFactory.openSession();
		tx         = session.beginTransaction();
		int categoryId = 0;
		
		Criteria criteria = session.createCriteria(FranchiseNational.class)
				  .add(Restrictions.eq("nationalFranchiseId", nationalFranchiseId))
				  .setProjection(Projections.property("categoryId"));
			
		if(criteria.uniqueResult() != null){
			categoryId     = (int) criteria.uniqueResult();
		}
		
	    tx.commit();
	    session.close();  
	   
		return  categoryId;		
		
	}
		
	@Override
	public List<States> statesListByCategoryId(int categoryId) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createSQLQuery(" select state_id as stateId, state_code as stateCode, state_name as stateName from tbl_states where  state_code not in (select fd.state_code from tbl_franchise_national fn, tbl_franchise_district fd where fn.national_franchise_id = fd.national_franchise_id and fn.category_id= :categoryId) ")
				  			   .setParameter("categoryId", categoryId);
		  
		  @SuppressWarnings("unchecked")
		  List<States> statesList =  (List<States>)query.setResultTransformer(Transformers.aliasToBean(States.class)).list();
		  
		  tx.commit(); 
		  session.close();
		  
		  return statesList; 
	}	
}