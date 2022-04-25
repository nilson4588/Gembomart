package com.rest.controller;
  
import java.util.List;
  
import org.apache.log4j.Logger; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.MediaType; 
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.bind.annotation.RestController;
  
import com.rest.model.Category; 
import com.rest.service.CategoryService; 
  
@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class CategoryController {
  
  @Autowired 
  CategoryService categoryService;
   
  static final Logger log = Logger.getLogger(CategoryController.class);
  
  @RequestMapping(value = "/saveOrUpdateCategory", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
  public void saveOrUpdateCategory(@RequestBody Category category) throws Exception {
  
	  try { 
		    categoryService.saveOrUpdateCategory(category);  
		  	log.info("Category record saved successfully.");		  	
  
	  } catch(Exception e) {  
		    log.error("Category failed to saved: "+ e.toString());
	  }
  }
  
  @RequestMapping(value = "/getCategory/{categoryId}", method = RequestMethod.GET) 
  public @ResponseBody Category  getCategoryById(@PathVariable("categoryId") int categoryId ) throws Exception {
  
	  Category category = null;
	  try {
	     log.info("Category Details fetched"); 
	     category = categoryService.getCategoryById(categoryId);
  
	  } catch(Exception e) {
  
		  log.info("Exception while fetching Category data : "+e.toString());
	  } 
	  return category;
  }
  
  @RequestMapping(value = "/categoryList", method = RequestMethod.GET)
  public @ResponseBody List<Category> getCategoryList() throws Exception {
  
	  List<Category> categoryList = null;
	  try { 
		  	categoryList = categoryService.getCategoryList();
		  	log.info("Display all Category List");
		  	
	  }catch(Exception e){
		    log.info("Exception while fetching Category data : "+e.toString()); 
	  } 
	  return categoryList;
  }
  
  
  @RequestMapping(value = "/activateOrDeactivateCategory", method = RequestMethod.POST) 
  public void activateOrDeactivateCategory(@RequestBody Category category) throws Exception {
  
	  try { 
		    categoryService.activateOrDeactivateCategory(category);
	        log.info("Category activation status changed successfully.");   
	  
	  } catch (Exception e) { 
		  e.printStackTrace();
		    log.info("Exception at Category activation status. "+e.toString());
	  } 
  } 
}
 