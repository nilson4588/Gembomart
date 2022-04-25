package com.rest.service;
  
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;  
import com.rest.dao.CategoryDao; 
import com.rest.model.Category; 
import com.rest.utility.DateTimeUtil;
  
@Service 
public class CategoryServiceImpl implements CategoryService {
  
  @Autowired 
  CategoryDao categoryDao;
  
  @Override 
  public int saveOrUpdateCategory(Category category) throws Exception { 
	  // TODO Auto-generated method stub 
	  String dateTime = DateTimeUtil.getSysDateTime(); 
	  category.setUpdatedDatetime(dateTime);
      category.setCreatedDatetime(dateTime);
  
      return categoryDao.saveOrUpdateCategory(category); 
  }
  
  @Override 
  public Category getCategoryById(int categoryId) throws Exception {
	  // TODO Auto-generated method stub 
	  return categoryDao.getCategoryById(categoryId);
  }
  
  @Override
  public String getCategoryNameById(int categoryId) throws Exception {
	  return categoryDao.getCategoryNameById(categoryId);
  }
  
  @Override 
  public List<Category> getCategoryList() throws Exception { 
	  // TODO  Auto-generated method stub
	  return categoryDao.getCategoryList(); 
  }
  
  @Override public int activateOrDeactivateCategory(Category category) throws Exception { 
	  // TODO Auto-generated method stub 
	  String dateTime = DateTimeUtil.getSysDateTime(); 
	  category.setUpdatedDatetime(dateTime); 
	  return categoryDao.activateOrDeactivateCategory(category); 
  } 
}