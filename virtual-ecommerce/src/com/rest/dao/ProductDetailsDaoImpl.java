package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.ProductDetails;
import com.rest.model.ProductDetailsSeller;

@Repository
@Transactional
public class ProductDetailsDaoImpl implements ProductDetailsDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	
	@Override
	public int saveProductDetails(ProductDetails productDetails) throws Exception {
		  // TODO Auto-generated method stub
		  int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(productDetails); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag;
	}
	
	@Override
	public List<ProductDetails> getProductDetailsBySupplierId(int supplierId) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  @SuppressWarnings("unchecked")
		  List<ProductDetails> productDetailsList = (List<ProductDetails>) session.createCriteria(ProductDetails.class)
							 					 .add(Restrictions.eq("supplierId", supplierId))
							 					 .list();	
		  
		  tx.commit(); 
		  session.close();  
		
		  return productDetailsList;
	}
	
	@Override
	public int saveProductDetailsSeller(ProductDetailsSeller productDetailsSeller) throws Exception {
		  // TODO Auto-generated method stub
		  int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(productDetailsSeller); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag;
	}
	
	@Override
	public List<ProductDetailsSeller> getProductDetailsBySellerId(int sellerId) throws Exception {
		
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  @SuppressWarnings("unchecked")
		  List<ProductDetailsSeller> productDetailsList = (List<ProductDetailsSeller>) session.createCriteria(ProductDetailsSeller.class)
							 					 .add(Restrictions.eq("sellerId", sellerId))
							 					 .list();	
		  
		  tx.commit(); 
		  session.close();  
		
		  return productDetailsList;
	}
}