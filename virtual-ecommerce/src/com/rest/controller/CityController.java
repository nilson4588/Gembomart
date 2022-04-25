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

import com.rest.model.City;
import com.rest.model.Districts;
import com.rest.model.States;
import com.rest.model.Taluka;
import com.rest.service.CityService;

@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class CityController {

	@Autowired
	CityService cityService;
	
	 static final Logger log = Logger.getLogger(CityController.class);
	  
	  @RequestMapping(value = "/saveOrUpdateCity", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) 
	  public void saveOrUpdateCity(@RequestBody City city) throws Exception {
	  
		  try { 
			    cityService.saveOrUpdateCity(city);  
			  	log.info("City record saved successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("City failed to saved: "+ e.toString());
		  }
	  }
	  
	  @RequestMapping(value = "/getCity/{cityId}", method = RequestMethod.GET) 
	  public @ResponseBody City  getCityById(@PathVariable("cityId") int cityId ) throws Exception {
	  
		  City city = null;
		  try {
		     log.info("City Details fetched"); 
		     city = cityService.getCityById(cityId);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching City data : "+e.toString());
		  } 
		  return city;
	  }
	  
	  @RequestMapping(value = "/cityList", method = RequestMethod.GET)
	  public @ResponseBody List<City> getCityList() throws Exception {
	  
		  List<City> cityList = null;
		  try { 
			    cityList = cityService.getCityList();
			  	log.info("Display all City List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching City data : "+e.toString()); 
		  } 
		  return cityList;
	  }
	  
	  
	  @RequestMapping(value = "/activateOrDeactivateCity", method = RequestMethod.POST) 
	  public void activateOrDeactivateCity(@RequestBody City city) throws Exception {
	  
		  try { 
			  cityService.activateOrDeactivateCity(city);
		        log.info("City activation status changed successfully.");   
		  
		  } catch (Exception e) { 
			  e.printStackTrace();
			    log.info("Exception at City activation status. "+e.toString());
		  } 
	  } 
	  
	  @RequestMapping(value = "/talukaListByDistrict/{districtCode}", method = RequestMethod.GET)
	  public @ResponseBody List<Taluka> talukaListByDistrict(@PathVariable("districtCode") int districtCode ) throws Exception {
	  
		  List<Taluka> talukaList = null;
		  try { 
			    talukaList = cityService.getTalukaList(districtCode);
			  	log.info("Display all taluka List By District");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching City data : "+e.toString()); 
		  } 
		  return talukaList;
	  }
	
	  
	  @RequestMapping(value = "/districtListByState/{stateCode}", method = RequestMethod.GET)
	  public @ResponseBody List<Districts> districtListByState(@PathVariable("stateCode") int stateCode ) throws Exception {
	  
		  List<Districts> districtsList = null;
		  try { 
			  districtsList = cityService.getDistrictsList(stateCode);
			  	log.info("Display district List By State");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching district List By State  : "+e.toString()); 
		  } 
		  return districtsList;
	  }
	  
	  @RequestMapping(value = "/stateList", method = RequestMethod.GET)
	  public @ResponseBody List<States> getStateList() throws Exception {
	  
		  List<States> stateList = null;
		  try { 
			    stateList = cityService.getStateList();
			  	log.info("Display all stateList List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching stateList data : "+e.toString()); 
		  } 
		  return stateList;
	  }
}
