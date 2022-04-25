package com.rest.dao;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rest.model.User;

@Component
public class LoginDaoImpl implements LoginDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	// To get userId by email and password
	@Override
	public int getUserIdByEmailAndPassword(String username, String password) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		int userId = 0;

		Criteria criteria = session.createCriteria(User.class);

		if (username.contains("@")) {

			criteria.setProjection(Projections.property("userId")).add(Restrictions.eq("userEmail", username))
					.add(Restrictions.eq("userPassword", password));
			userId = (int) criteria.uniqueResult();

		} else {

			criteria.setProjection(Projections.property("userId")).add(Restrictions.eq("userMobile", username))
					.add(Restrictions.eq("userPassword", password));
			userId = (int) criteria.uniqueResult();
		}

		tx.commit();
		session.close();

		return userId;
	}

	// To get user status by email and password
	@Override
	public String getStatusByEmailAndPassword(String email, String password) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		String status = null;
		Criteria criteria = session.createCriteria(User.class).setProjection(Projections.property("userStatus"))
				.add(Restrictions.eq("userEmail", email)).add(Restrictions.eq("userPassword", password));
		status = (String) criteria.uniqueResult();

		tx.commit();
		session.close();

		return status;

	}

	// To get password by email
	@Override
	public String getPasswordByEmail(String email) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		String password = null;
		Criteria criteria = session.createCriteria(User.class).setProjection(Projections.property("userPassword"))
				.add(Restrictions.eq("userEmail", email));

		password = (String) criteria.uniqueResult();

		tx.commit();
		session.close();

		return password;

	}

	// To update random Password as user password
	@Override
	public void updateRandomPassword(String userPassword, int userId) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		String hqlUpdate = "update User set userPassword = :userPassword where userId = :userId";

		session.createQuery(hqlUpdate).setString("userPassword", userPassword).setInteger("userId", userId)
				.executeUpdate();

		tx.commit();
		session.close();
	}

	/*
	 * @Override public void setStatusAsValid(int registerId, String userStatus)
	 * throws Exception {
	 * 
	 * session = sessionFactory.openSession(); tx = session.beginTransaction();
	 * 
	 * String hqlUpdate =
	 * "update Register set userStatus = :userStatus where registerId = :registerId"
	 * ; session.createQuery(hqlUpdate) .setString("userStatus",userStatus)
	 * .setInteger("registerId", registerId) .executeUpdate();
	 * 
	 * 
	 * String
	 * sql="update tbl_register set user_status = '"+status+"' where register_id='"
	 * +id+"' "; SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
	 * query.executeUpdate();
	 * 
	 * 
	 * tx.commit(); session.close();
	 * 
	 * }
	 */

	// To update new password
	@Override
	public void updateNewPassword(String userPassword, int userId) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		String hqlUpdate = "update User set userPassword = :userPassword  where userId = :userId";

		session.createQuery(hqlUpdate).setString("userPassword", userPassword).setInteger("userId", userId)
				.executeUpdate();

		tx.commit();
		session.close();
	}

	// To verify user by email(Forgot password)
	@Override
	public boolean verifyUserByEmail(String userEmail) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(User.class).setProjection(Projections.property("userId"))
				.add(Restrictions.eq("userEmail", userEmail));

		tx.commit();
		session.close();

		return true;
	}

	// To get userId by Email
	@Override
	public int getUserIdByEmailOrMobile(String userEmailOrMobile, int flag) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		int userId = 0;

		Criteria criteria = session.createCriteria(User.class).setProjection(Projections.property("userId"));
		if (flag == 2) {
			criteria.add(Restrictions.eq("userEmail", userEmailOrMobile));
		} else if (flag == 1) {
			criteria.add(Restrictions.eq("userMobile", userEmailOrMobile));
		}

		if (criteria.list().size() == 1) {
			userId = (int) criteria.uniqueResult();
		}

		tx.commit();
		session.close();
		return userId;
	}

	// update OTP by userId (Forgot password)

	@Override
	public void updateOTPById(String userOTP, int userId) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		String hqlUpdate = "update User set userOTP = :userOTP where userId = :userId";
		session.createQuery(hqlUpdate).setString("userOTP", userOTP).setInteger("userId", userId).executeUpdate();

		tx.commit();
		session.close();

	}

	@Override
	public String getPasswordByUserId(int userId) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		String userpassword = null;
		Criteria criteria = session.createCriteria(User.class);
		criteria.setProjection(Projections.property("userPassword")).add(Restrictions.eq("userId", userId));

		userpassword = (String) criteria.uniqueResult();

		tx.commit();
		session.close();

		return userpassword;
	}

	// To get password by email or password (used in login)

	@Override
	public String getPasswordByEmailOrMobile(String username) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		String userpassword = null;
		Criteria criteria = session.createCriteria(User.class);
		if (username.contains("@")) {

			criteria.setProjection(Projections.property("userPassword")).add(Restrictions.eq("userEmail", username));
		} else {
			criteria.setProjection(Projections.property("userPassword")).add(Restrictions.eq("userMobile", username));
		}

		userpassword = (String) criteria.uniqueResult();

		tx.commit();
		session.close();
		return userpassword;
	}

	@Override
	public String getEndTimeOfOTP(String createdDateTime, String timeLimit) {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		String result = null;

		String sql = "SELECT ADDTIME('" + createdDateTime + "','" + timeLimit
				+ "') as end_time from tbl_user where updated_datetime='" + createdDateTime + "' ";

		SQLQuery query = (SQLQuery) session.createSQLQuery(sql);
		query.addScalar("end_time", StringType.INSTANCE);
		result = (String) query.uniqueResult();
		if (result == null) {
			tx.rollback();
			session.close();
		}
		return result;

	}
}
