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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.ProductPrice;

@Repository
@Transactional
public class ProductPriceDaoImpl implements ProductPriceDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	
	@Override
	public void saveOrUpdateProductPrice(ProductPrice productPrice) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(productPrice); 
		  
		  tx.commit(); 
		  session.close();		  
	}

	@Override
	public ProductPrice getProductPriceById(int productPriceId) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();
		  ProductPrice productPrice = (ProductPrice) session.load(ProductPrice.class, new Integer(productPriceId));
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return productPrice;
	}

	@Override
	public ProductPrice getProductPriceByProductId(int productId) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx = session.beginTransaction();
		  
		  ProductPrice productPrice = (ProductPrice) session.createCriteria(ProductPrice.class)
				  .add(Restrictions.eq("productId", productId))
				  .add(Restrictions.eq("isActive", 1))
				  .uniqueResult(); 
		  
		  tx.commit();  
		  session.close(); 
		  
		  return productPrice;  
	}

	@Override
	public List<ProductPrice> getProductPriceActiveList() throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<ProductPrice> productPriceList = (List<ProductPrice>) session.createCriteria(ProductPrice.class).add(Restrictions.eq("isActive", 1)).list();
		  
		  tx.commit(); 
		  session.close();
		  
		  return productPriceList;
	}

	@Override
	public List<ProductPrice> getProductPriceAllList() throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<ProductPrice> productPriceList = session.createCriteria(ProductPrice.class).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return productPriceList;
	}
	
	@Override
	public int getActiveProductPriceIdByProductId(int productId) throws Exception {
		
		session    = sessionFactory.openSession();
		tx         = session.beginTransaction();
		int id     = 0;
		
		Criteria criteria = session.createCriteria(ProductPrice.class)
				  .add(Restrictions.eq("productId", productId))
				  .add(Restrictions.eq("isActive", 1))
				  .setProjection(Projections.property("productPriceId"));
			
		if(criteria.uniqueResult() != null){
			 id     = (int) criteria.uniqueResult();
		}
		
	    tx.commit();
	    session.close();  
	   
		return  id;		
		
	}
	
	@Override
	public void changeProductPriceStatus(int productPriceId) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();  
		  tx      = session.beginTransaction();  
		 
		  Query query = session.createQuery(" update ProductPrice set isActive = :isActive  where productPriceId = :productPriceId ")
				    .setParameter("isActive", 0)
				    .setParameter("productPriceId", productPriceId);
				   
		  query.executeUpdate();
		 		
		  tx.commit();  
		  session.close();		
	}

}
