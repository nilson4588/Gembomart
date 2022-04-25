package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.City;
import com.rest.model.Districts;
import com.rest.model.States;
import com.rest.model.Taluka;

@Repository
@Transactional
public class CityDaoImpl implements CityDao {
	
	@Autowired 
	SessionFactory sessionFactory;
	  
	Session session = null; 
	Transaction tx = null;

	@Override
	public int saveOrUpdateCity(City city) throws Exception {
		// TODO Auto-generated method stub
		int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(city); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag; 
	}

	@Override
	public City getCityById(int cityId) throws Exception {
		// TODO Auto-generated method stub
		 session = sessionFactory.openSession();
	      City city = (City) session.load(City.class, new Integer(cityId));
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return city; 
	}

	@Override
	public List<City> getCityList() throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<City> cityList = session.createCriteria(City.class).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return cityList; 
	}

	@Override
	public int activateOrDeactivateCity(City city) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update City set isActive= :isActive  where cityId = :cityId ")
				  			   .setParameter("isActive", city.isActive())
				  			   .setParameter("cityId", city.getCityId());
		  
		  int count = query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
		  
		  return count; 
	}
	
	@Override
	public List<Taluka> getTalukaList(int districtCode) throws Exception {
		
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Taluka> talukaList =  session.createQuery(" from Taluka where districtCode = :districtCode order by talukaName").setParameter("districtCode", districtCode).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return talukaList; 
	}
	
	@Override
	public List<Districts> getDistrictsList(int stateCode) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Districts> cityList =  session.createQuery(" from Districts where stateCode = :stateCode ").setParameter("stateCode", stateCode).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return cityList; 
	}
	
	@Override
	public List<States> getStateList() throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<States> statesList = session.createCriteria(States.class).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return statesList; 
	}
	
	@Override
	public String getStateName(int stateCode) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  String stateName =  (String) session.createQuery("select stateName from States where stateCode = :stateCode ").setParameter("stateCode", stateCode).uniqueResult();
		  
		  tx.commit(); 
		  session.close();
		  
		  return stateName; 
	}
	
	
	@Override
	public String getDistrictName(int districtCode) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  String districtName =  (String) session.createQuery("select districtName from Districts where districtCode = :districtCode ").setParameter("districtCode", districtCode).uniqueResult();
		  
		  tx.commit(); 
		  session.close();
		  
		  return districtName; 
	}
	
	@Override
	public String getTalukaName(int talukaCode) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  String talukaName =  (String) session.createQuery("select talukaName from Taluka where talukaCode = :talukaCode ").setParameter("talukaCode", talukaCode).uniqueResult();
		  
		  tx.commit(); 
		  session.close();
		  
		  return talukaName; 
	}
	
	@Override
	public int getDeliveryChargesByTalukaCode(int talukaCode) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  int deliveryCharges =  (int) session.createQuery("select deliveryCharges from Taluka where talukaCode = :talukaCode ").setParameter("talukaCode", talukaCode).uniqueResult();
		  
		  tx.commit(); 
		  session.close();
		  
		  return deliveryCharges; 
	}
}