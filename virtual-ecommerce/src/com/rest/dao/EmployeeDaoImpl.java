package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.Employee;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired 
	SessionFactory sessionFactory;
	  
	Session session = null; 
	Transaction tx = null;
	
	@Override
	public int saveOrUpdateEmployee(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		  int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(employee); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag; 
	}

	@Override
	public Employee getEmployeeById(int employeeId) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession();
		  Employee employee = (Employee) session.load(Employee.class, new Integer(employeeId));
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return employee; 
	}

	@Override
	public List<Employee> getEmployeeList() throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<Employee> employeeList = session.createCriteria(Employee.class).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return employeeList;
	}

	@Override
	public int activateOrDeactivateEmployee(Employee employee) throws Exception {
		  // TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update Employee set isActive= :isActive, updatedDatetime= :updatedDatetime  where employeeId = :employeeId ")
				  			   .setParameter("isActive", employee.getIsActive())
				  			   .setParameter("updatedDatetime", employee.getUpdatedDatetime())
				  			   .setParameter("employeeId", employee.getEmployeeId());
		  
		  int count = query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
		  
		  return count;
	}
}