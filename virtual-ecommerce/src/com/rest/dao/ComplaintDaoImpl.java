package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.Complaint;

@Repository
@Transactional
public class ComplaintDaoImpl implements ComplaintDao {

	@Autowired 
	SessionFactory sessionFactory;
	  
	Session session = null; 
	Transaction tx = null;
	
	@Override
	public int saveOrUpdateComplaint(Complaint complaint) throws Exception {
		// TODO Auto-generated method stub
		int successflag = 0;
		  
		session = sessionFactory.openSession(); 
		tx = session.beginTransaction();
		  
		session.saveOrUpdate(complaint); 
		  
		tx.commit(); 
		session.close();
		  
	    successflag = 1;
		  
		return successflag; 
	}

	@Override
	public Complaint getComplaintById(int complaintId) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		Complaint complaint = (Complaint) session.load(Complaint.class, new Integer(complaintId));
	    tx = session.getTransaction();
	      
		session.beginTransaction(); 
		tx.commit(); 
		  
		return complaint; 
	}

	@Override
	public List<Complaint> getComplaintList(Complaint complaint) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession(); 
		tx = session.beginTransaction();
		
		String complaintStatus = complaint.getComplaintStatus();
		String complaintDate   = complaint.getComplaintDatetime();
	  
		Criteria criteria = session.createCriteria(Complaint.class);
        if(complaintStatus.length() != 0){
        	criteria.add(Restrictions.eq("complaintStatus", complaintStatus));		
		}		
		if(complaintDate.length() != 0){
			criteria.add(Restrictions.ilike("complaintDatetime", complaintDate, MatchMode.START));
		}

		@SuppressWarnings("unchecked")
		List<Complaint> complaintList = (List<Complaint>) criteria.list();
		
		return complaintList;
	}
	
	@Override
	public List<Complaint> complaintList() throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();		
		  @SuppressWarnings("unchecked")
		  List<Complaint> complaintList = session.createCriteria(Complaint.class).addOrder(Order.desc("complaintId")).list();  
		  tx.commit();  
		  session.close();  
		  return complaintList;  
	}

	@Override
	public void changeComplaintStatus(Complaint complaint) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  Query query = session.createQuery(" update Complaint set complaintStatus = :complaintStatus, complaintSolvedDatetime = :complaintSolvedDatetime  where complaintId = :complaintId ")
				    .setParameter("complaintStatus", complaint.getComplaintStatus())
				    .setParameter("complaintSolvedDatetime", complaint.getComplaintSolvedDatetime())
				    .setParameter("complaintId", complaint.getComplaintId());
				   
		  query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();		
	}
}