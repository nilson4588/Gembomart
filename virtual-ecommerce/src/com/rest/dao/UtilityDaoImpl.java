
package com.rest.dao;

//import java.util.List;

import org.hibernate.Criteria;
//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rest.model.RegistrationCharges;

//import com.rest.model.FinanceYear;

@Component
public class UtilityDaoImpl implements UtilityDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@SuppressWarnings("rawtypes")
	@Override
	public Object getRecentObject(Class objclass, String pid) {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Object obj = null;

		Criteria criteria = session.createCriteria(objclass).addOrder(Order.desc(pid)).setMaxResults(1);
		obj = (Object) criteria.uniqueResult();

		tx.commit();
		session.close();

		return obj;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int getRecentId(Class objclass, String pid) {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		int id = 0;

		Criteria criteria = session.createCriteria(objclass).setProjection(Projections.property(pid))
				.addOrder(Order.desc(pid)).setMaxResults(1);
		id = (int) criteria.uniqueResult();

		tx.commit();
		session.close();

		return id;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isIdExists(Class objclass, String pid, int val) {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		boolean flag = false;

		Criteria criteria = session.createCriteria(objclass).add(Restrictions.eq(pid, val))
				.setProjection(Projections.property(pid));

		if (criteria.uniqueResult() != null) {
			int id = (int) criteria.uniqueResult();
			if (id > 0)
				flag = true;
		}

		tx.commit();
		session.close();

		return flag;
	}
	
	@Override
	public int getAmountByFranchiseName(String franchiseName) {
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		int amount = 0;
		
		Criteria criteria = session.createCriteria(RegistrationCharges.class)
				.add(Restrictions.eq("franchiseName", franchiseName))
				.setProjection(Projections.property("registrationAmount"));
		
		amount = (int) criteria.uniqueResult();
		
		tx.commit();
		session.close();
		
		return amount;
	}

	/*
	 * public FinanceYear getCurrentFinancialYear() {
	 * 
	 * session = sessionFactory.openSession(); tx = session.beginTransaction();
	 * 
	 * Criteria criteria =
	 * session.createCriteria(FinanceYear.class).add(Restrictions.eq(
	 * "financialYearActive", 1));
	 * 
	 * FinanceYear finYr = (FinanceYear) criteria.uniqueResult();
	 * 
	 * tx.commit(); session.close();
	 * 
	 * return finYr; }
	 * 
	 * public List<FinanceYear> getAllFinancialYears() {
	 * 
	 * session = sessionFactory.openSession(); tx = session.beginTransaction();
	 * 
	 * Query query = session.createQuery("from FinanceYear");
	 * 
	 * @SuppressWarnings("unchecked") List<FinanceYear> finYrList = query.list();
	 * 
	 * tx.commit(); session.close();
	 * 
	 * return finYrList; }
	 */
}
