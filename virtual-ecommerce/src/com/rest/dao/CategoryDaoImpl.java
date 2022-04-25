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
  
import com.rest.model.Category;
  
@Repository  
@Transactional 
public class CategoryDaoImpl implements CategoryDao {
  
  @Autowired 
  SessionFactory sessionFactory;
  
  Session session = null; 
  Transaction tx = null;
  
  @Override 
  public int saveOrUpdateCategory(Category category) throws Exception { 
      // TODO Auto-generated method stub 	  
	  int successflag = 0;
	  
	  session = sessionFactory.openSession(); 
	  tx = session.beginTransaction();
	  
	  session.saveOrUpdate(category); 
	  
	  tx.commit(); 
	  session.close();
	  
	  successflag = 1;
	  
	  return successflag; 
  }
  
  @Override 
  public Category getCategoryById(int categoryId) throws Exception {
      // TODO Auto-generated method stub 
	  session = sessionFactory.openSession();
      Category category = (Category) session.load(Category.class, new Integer(categoryId));
      tx = session.getTransaction();
      
	  session.beginTransaction(); 
	  tx.commit(); 
	  
	  return category; 
  }
  
  @Override 
  public List<Category> getCategoryList() throws Exception { 
	  // TODO Auto-generated method stub 
	  session = sessionFactory.openSession(); 
	  tx = session.beginTransaction();
  
	  @SuppressWarnings("unchecked") 
	  List<Category> categoryList = session.createCriteria(Category.class)
			                        .add(Restrictions.eq("isActive", 1))
			                        .list(); 
	  
	  tx.commit(); 
	  session.close();
	  
	  return categoryList; 
  }
  
  @Override 
  public int activateOrDeactivateCategory(Category category) throws Exception {
	  // TODO Auto-generated method stub 
	  session = sessionFactory.openSession(); 
	  tx = session.beginTransaction();
	  
	  Query query = session.createQuery(" update Category set isActive= :isActive, updatedDatetime = :updatedDateTime where id = :categoryId ")
			  			   .setParameter("isActive", category.getIsActive())
			  			   .setParameter("updatedDateTime", category.getUpdatedDatetime())
			  			   .setParameter("categoryId", category.getId());
	  
	  int count = query.executeUpdate();
	  
	  tx.commit(); 
	  session.close();
	  
	  return count; 
  }  
  
  
  @Override
  public String getCategoryNameById(int categoryId) throws Exception {
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		String hql = " select categoryName as categoryName from Category where id = :categoryId ";
		
		String categoryName =  (String)session.createQuery(hql).setInteger("categoryId", categoryId).uniqueResult();
		
		tx.commit();
		session.close();
		
		return categoryName;
   }  
}