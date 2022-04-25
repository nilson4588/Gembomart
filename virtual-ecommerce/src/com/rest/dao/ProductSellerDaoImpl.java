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

import com.rest.model.ProductSeller;

@Repository  
@Transactional 
public class ProductSellerDaoImpl implements ProductSellerDao {
	  
	  @Autowired 
	  SessionFactory sessionFactory;
	  
	  Session session = null; 
	  Transaction tx = null;
		
	  @Override
	  public int saveOrUpdateProductSeller(ProductSeller productSeller) throws Exception {
		  
		  int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(productSeller); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag; 
	  }
	  
	  @Override
	  public ProductSeller getProductSellerById(int productSellerId) throws Exception {
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  ProductSeller productSeller = (ProductSeller) session.createQuery(" from ProductSeller where productSellerId = :productSellerId ").setParameter("productSellerId", productSellerId).uniqueResult();
		 
		  tx.commit(); 
		  session.close();
		  
		  return productSeller; 
	  }
	  
	  @Override
	  public List<ProductSeller> getProductSellerListBySellerId(int sellerId) throws Exception {
		  
		    session = sessionFactory.openSession();
			tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<ProductSeller> productSellerList = (List<ProductSeller>) session.createCriteria(ProductSeller.class)
			                .add(Restrictions.eq("sellerId", sellerId))
			                .add(Restrictions.eq("isActive", 1))
			                .addOrder(org.hibernate.criterion.Order.desc("productSellerId"))
			                .list();  
			
			tx.commit(); 
			session.close();
			  
			return productSellerList;
	  }
	  
	  @Override
	  public List<ProductSeller> getProductSellerListByDeactiveSellerId(int sellerId) throws Exception {
		  
		    session = sessionFactory.openSession();
			tx = session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<ProductSeller> productSellerList = (List<ProductSeller>) session.createCriteria(ProductSeller.class)
			                .add(Restrictions.eq("sellerId", sellerId))
			                .add(Restrictions.eq("isActive", 0))
			                .addOrder(org.hibernate.criterion.Order.desc("productSellerId"))
			                .list();  
			
			tx.commit(); 
			session.close();
			  
			return productSellerList;
	  }
	  
	  @Override
	  public int activateOrDeactivateProductSeller(ProductSeller productSeller) throws Exception {
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update ProductSeller set isActive = :isActive where productSellerId = :productSellerId ")
				  			   .setParameter("isActive", productSeller.getIsActive())
				  			   .setParameter("productSellerId", productSeller.getProductSellerId());
		  
		  int count = query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
		  
		  return count; 
	  }
	  
	  @Override
	  public ProductSeller getProductSellerByProductAndSeller(int sellerId, int productId) throws Exception {
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  ProductSeller productSeller = (ProductSeller) session.createQuery(" from ProductSeller where sellerId = :sellerId and productId = :productId ")
				   .setParameter("sellerId", sellerId)
				   .setParameter("productId", productId)
				   .uniqueResult();
		 
		  tx.commit(); 
		  session.close();
		  
		  return productSeller; 
	  }
}
