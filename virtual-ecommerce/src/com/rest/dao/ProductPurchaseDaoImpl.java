package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.ProductPurchase;

@Repository  
@Transactional 
public class ProductPurchaseDaoImpl implements ProductPurchaseDao {

	@Autowired 
	SessionFactory sessionFactory;
	  
	Session session = null; 
	Transaction tx = null;
	
	@Override
	public int saveOrUpdateProductPurchase(ProductPurchase productPurchase) throws Exception {
		// TODO Auto-generated method stub
		int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(productPurchase); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag;
	}

	@Override
	public ProductPurchase getProductPurchaseById(int purchaseId) throws Exception {
		// TODO Auto-generated method stub
		  session = sessionFactory.openSession();
		  ProductPurchase productPurchase = (ProductPurchase) session.load(ProductPurchase.class, new Integer(purchaseId));
	      tx = session.getTransaction();
	      
		  session.beginTransaction(); 
		  tx.commit(); 
		  
		  return productPurchase;
	}

	@Override
	public List<ProductPurchase> getProductPurchaseList() throws Exception {
		// TODO Auto-generated method stub 
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
	  
		  @SuppressWarnings("unchecked") 
		  List<ProductPurchase> productPurchaseList = session.createCriteria(ProductPurchase.class).list(); 
		  
		  tx.commit(); 
		  session.close();
		  
		  return productPurchaseList; 
	}

	@Override
	public int activateOrDeactivateProductPurchase(ProductPurchase productPurchase) throws Exception {
		// TODO Auto-generated method stub 
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  Query query = session.createQuery(" update ProductPurchase set isActive= :isActive where purchaseId = :productPurchaseId ")
				  			   .setParameter("isActive", productPurchase.getIsActive())
				  			   .setParameter("categoryId", productPurchase.getPurchaseId());
		  
		  int count = query.executeUpdate();
		  
		  tx.commit(); 
		  session.close();
		  
		  return count; 
	}
}