
  package com.rest.service;
  
  import java.util.List;
  
  import org.springframework.stereotype.Component;
  
  import com.rest.model.Category;
  
  @Component 
  public interface CategoryService {
  
	  public int saveOrUpdateCategory(Category category) throws Exception;
	  
	  public Category getCategoryById(int categoryId) throws Exception;
	  
	  public List<Category> getCategoryList() throws Exception;
	  
	  public int activateOrDeactivateCategory(Category category) throws Exception;
  
	  public String getCategoryNameById(int categoryId) throws Exception;
  }