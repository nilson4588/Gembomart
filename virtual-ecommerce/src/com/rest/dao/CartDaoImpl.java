package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.Cart;

@Repository  
@Transactional 
public class CartDaoImpl implements CartDao {

	@Autowired 
	SessionFactory sessionFactory;
	  
	Session session = null; 
	Transaction tx = null;
	  
	@Override
	public int saveCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		  int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(cart); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag; 
	}

	@Override
	public Cart getCartById(int cartId) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Cart cart = (Cart) session.createQuery(" from Cart where cartId = :cartId ").setParameter("cartId", cartId).uniqueResult();
		 
		  tx.commit(); 
		  session.close();
		  
		  return cart; 
	}
	
	@Override
	public Cart getCartByProductId(int productId, int customerId) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Cart cart = (Cart) session.createQuery(" from Cart where productId = :productId and customerId = :customerId")
				  .setParameter("productId", productId)
				  .setParameter("customerId", customerId)
				  .uniqueResult();
		 
		  
		  tx.commit(); 
		  session.close();
		  
		  return cart; 
	}

	@Override
	public List<Cart> getCartListByCustomerId(int customerId) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Cart> cartList = (List<Cart>) session.createCriteria(Cart.class)
		                .add(Restrictions.eq("customerId", customerId)).list();  
		
		tx.commit(); 
		session.close();
		  
		return cartList;
	}

	@Override
	public int deleteCartList(int customerId) throws Exception {
		  // TODO Auto-generated method stub
		  int successflag = 0;
		  
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  Query query = session.createQuery(" delete Cart where customerId = :customerId ")
				               .setParameter("customerId", customerId);
				   
		  query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();	
		  
          successflag = 1;
		  
		  return successflag; 
	}
	
	@Override
	public int deleteCartById(int cartId) throws Exception {
		
		  int successflag = 0;
		  
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  Query query = session.createQuery(" delete Cart where cartId = :cartId ")
				               .setParameter("cartId", cartId);
				   
		  query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();	
		  
          successflag = 1;
		  
		  return successflag; 
	}
	
	@Override
	public int getCartListCount(int customerId) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Cart> cartList = (List<Cart>) session.createCriteria(Cart.class)
		                .add(Restrictions.eq("customerId", customerId)).list();  
		
		tx.commit(); 
		session.close();
		  
		return cartList.size();
	}
	
	
	@Override
	public int updateCart(int customerId, int productId, int quantity) throws Exception {
		  // TODO Auto-generated method stub
		  int successflag = 0;
		  
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  Query query = session.createQuery(" update Cart set quantity = :quantity where customerId = :customerId and productId = :productId")
				               .setParameter("customerId", customerId).setParameter("productId", productId);
				   
		  query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();	
		  
          successflag = 1;
		  
		  return successflag; 
	}
}