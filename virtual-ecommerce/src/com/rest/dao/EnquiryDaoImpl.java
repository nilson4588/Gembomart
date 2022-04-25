package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.Enquiry;

@Repository
@Transactional
public class EnquiryDaoImpl implements EnquiryDao {

	@Autowired 
	SessionFactory sessionFactory;
	  
	Session session = null; 
	Transaction tx = null;
	
	@Override
	public int saveOrUpdateEnquiry(Enquiry enquiry) throws Exception {
		// TODO Auto-generated method stub
		int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(enquiry); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag; 
	}

	@Override
	public Enquiry getEnquiryById(int enquiryId) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();
		  Enquiry enquiry = (Enquiry) session.load(Enquiry.class, new Integer(enquiryId));
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return enquiry; 
	}

	@Override
	public List<Enquiry> getEnquiryList() throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Enquiry> enquiryList = session.createCriteria(Enquiry.class).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return enquiryList;
	}

	@Override
	public int activateOrDeactivateEnquiry(Enquiry enquiry) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update Enquiry set isActive= :isActive  where enquiryId = :enquiryId ")
				  			   .setParameter("isActive", enquiry.getIsActive())
				  			   .setParameter("enquiryId", enquiry.getEnquiryId());
		  
		  int count = query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
		  
		  return count;
	}

}
