
package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rest.model.CategoryWiseProduct;
import com.rest.model.Images;
import com.rest.model.Product;
import com.rest.model.ProductCategory;
import com.rest.model.ProductListByShop;
import com.rest.model.ShopListByProduct;
import com.rest.model.Variants;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	
	@Override
	public int saveOrUpdateProduct(Product product) throws Exception {
		  // TODO Auto-generated method stub
		  int successflag = 0;
		  
		  session = sessionFactory.openSession(); 
		  tx = session.beginTransaction();
		  
		  session.saveOrUpdate(product); 
		  
		  tx.commit(); 
		  session.close();
		  
		  successflag = 1;
		  
		  return successflag;
	}

	@Override
	public Product getProductById(int productId) throws Exception {
		
		session = sessionFactory.openSession();
		Product product = (Product) session.load(Product.class, new Integer(productId));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return product;
	}
	
	@Override
	public Product getProductByName(String productName) throws Exception {
		
		session = sessionFactory.openSession(); 
		tx = session.beginTransaction();
		
		Product product = (Product) session.createCriteria(Product.class)
				 .add(Restrictions.eq("title", productName)).uniqueResult();
		
		tx.commit();
		session.close();
		
		return product;
	}

	@Override public List<Product> getProductList() throws Exception { 
		
		session = sessionFactory.openSession(); 
		tx = session.beginTransaction();
  
		@SuppressWarnings("unchecked") 
		List<Product> productList = session.createCriteria(Product.class).list(); 
		
		tx.commit();
		session.close();
	    
		return productList; 
	}

	@Override
	public List<Variants> getVariantsList(int productId) throws Exception { 
		 
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Variants> variantsList = (List<Variants>) session.createCriteria(Variants.class)
						 					 .add(Restrictions.eq("id", productId))
						 					 .list();		
		tx.commit();
		session.close();
		
		return variantsList;
	}

	@Override
	public List<Images> getImagesList(int productId) throws Exception {
		 
		session = sessionFactory.openSession();
		tx  = session.beginTransaction();
  
	    @SuppressWarnings("unchecked") 
	    List<Images> imagesList = (List<Images>) session.createCriteria(Images.class)
							    		 .add(Restrictions.eq("id", productId))
							    		 .list(); 	  
	    tx.commit();
	    session.close();
	  
	    return imagesList;
	}

	@Override
	public List<String> getCollectionsList(int productId) throws Exception {
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		String hql = " select c.categoryName from Category as c, ProductCollection as pc where c.id = pc.categoryId and pc.productId = :productId ";

		@SuppressWarnings("unchecked")
		List<String> collectionList = (List<String>) session.createQuery(hql).setParameter("productId", productId).list();
		
		tx.commit();
		session.close();
		
		return collectionList;
	}

	@Override
	public List<String> getTagsList(int productId) throws Exception {
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		String hql = " select t.tagName from Tags as t, ProductTags as pt where t.tagId = pt.tagId and pt.productId = :productId ";

		@SuppressWarnings("unchecked")
		List<String> tagsList = (List<String>) session.createQuery(hql).setParameter("productId", productId).list();
		tx.commit();
		session.close();
		return tagsList;
	}

	@Override
	public List<Integer> getVariantListByImageId(int imageId) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		String hql = " select iv.variant_id from ImagesVariant as iv where iv.image_id = :imageId ";

		@SuppressWarnings("unchecked")
		List<Integer> variantList = (List<Integer>) session.createQuery(hql).setParameter("imageId", imageId).list();
		
		tx.commit();
		session.close();
		
		return variantList;
	}
	
	@Override
	public List<ProductCategory> getProductAndCategoryList() throws Exception {
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		String hql = " select id as productId, title as productName, category as productCategory from Product  ";
		
		@SuppressWarnings("unchecked")
		List<ProductCategory> productList = (List<ProductCategory>) session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(ProductCategory.class)).list();
		
		tx.commit();
		session.close();
		
		return productList;
	}
	
	@Override
	public String getProductNameById(int productId) throws Exception {
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		String hql = " select title as productName from Product where id = :productId ";
		
		String productName =  (String)session.createQuery(hql).setInteger("productId", productId).uniqueResult();
		
		tx.commit();
		session.close();
		
		return productName;
	}
	
	
	@Override
	public List<ProductCategory> getProductListByCategory(String categoryName) throws Exception {
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		String hql = " select id as productId, title as productName, category as productCategory from Product where category='"+categoryName+"'";
		
		@SuppressWarnings("unchecked")
		List<ProductCategory> productList = (List<ProductCategory>) session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(ProductCategory.class)).list();
		
		tx.commit();
		session.close();
		
		return productList;
	}
	
	@Override
	public List<CategoryWiseProduct> getCategoryWiseProductList(int stateCode, int districtCode, int talukaCode,
			String categoryName) throws Exception {
		
		 Session session = sessionFactory.openSession();  
		 Transaction tx  = session.beginTransaction();  
		 Query query = ((SQLQuery) session.createSQLQuery("CALL `product_list_category`(:stateCode, :districtCode, :talukaCode, :categoryName)")		
							  .setParameter("stateCode", stateCode)
							  .setParameter("districtCode", districtCode)
							  .setParameter("talukaCode", talukaCode)
							  .setParameter("categoryName", categoryName))
							  .addScalar("productId", new IntegerType())
							  .addScalar("productTitle", new StringType())
							  .addScalar("imageSource", new StringType())
							  .addScalar("productPackSize", new StringType())
							  .addScalar("productDescription", new StringType());		
			
		@SuppressWarnings("unchecked")
		List<CategoryWiseProduct> collectionList = query.setResultTransformer(Transformers.aliasToBean(CategoryWiseProduct.class)).list();
		
		tx.commit();  
		session.close(); 
		
		return collectionList;
	}
	
	@Override
	public List<ShopListByProduct> getShopListByProduct(int stateCode, int districtCode, int talukaCode, int productId) throws Exception {
		
		Session session = sessionFactory.openSession();  
		 Transaction tx  = session.beginTransaction();  
		 Query query = ((SQLQuery) session.createSQLQuery("CALL `shop_list_by_product`(:stateCode, :districtCode, :talukaCode, :productId)")		
							  .setParameter("stateCode", stateCode)
							  .setParameter("districtCode", districtCode)
							  .setParameter("talukaCode", talukaCode)
							  .setParameter("productId", productId))
							  .addScalar("sellerId", new IntegerType())
							  .addScalar("shopName", new StringType())
							  .addScalar("productId", new IntegerType())
							  .addScalar("productTitle", new StringType())
							  .addScalar("productPackSize", new StringType())
							  .addScalar("productDescription", new StringType())		
							  .addScalar("productPrice", new DoubleType())
							  .addScalar("productDiscount", new DoubleType());	
					
		@SuppressWarnings("unchecked")
		List<ShopListByProduct> collectionList = query.setResultTransformer(Transformers.aliasToBean(ShopListByProduct.class)).list();
		
		tx.commit();  
		session.close(); 
		
		return collectionList;
	}
	
	@Override
	public List<ProductListByShop> getProductListByShop(int sellerId) throws Exception {
		
		Session session = sessionFactory.openSession();  
		 Transaction tx  = session.beginTransaction();  
		 Query query = ((SQLQuery) session.createSQLQuery("CALL `product_by_shop`(:sellerId)")		
							  .setParameter("sellerId", sellerId))
							  .addScalar("sellerId", new IntegerType())
							  .addScalar("shopName", new StringType())
							  .addScalar("productId", new IntegerType())
							  .addScalar("productTitle", new StringType()) 
							  .addScalar("productCategory", new StringType())
							  .addScalar("productPackSize", new StringType())
							  .addScalar("productDescription", new StringType())		
							  .addScalar("productPrice", new DoubleType())
							  .addScalar("productDiscount", new DoubleType())
							  .addScalar("imageSource", new StringType());	
					
		@SuppressWarnings("unchecked")
		List<ProductListByShop> collectionList = query.setResultTransformer(Transformers.aliasToBean(ProductListByShop.class)).list();
		
		tx.commit();  
		session.close(); 
		
		return collectionList;
	}
	
}