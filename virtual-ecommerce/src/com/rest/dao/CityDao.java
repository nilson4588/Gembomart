package com.rest.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.model.City;
import com.rest.model.Districts;
import com.rest.model.States;
import com.rest.model.Taluka;

@Component
public interface CityDao {

	  public int saveOrUpdateCity(City city) throws Exception;
	  
	  public City getCityById(int cityId) throws Exception;
	  
	  public List<City> getCityList() throws Exception;
	  
	  public int activateOrDeactivateCity(City city) throws Exception;
	  
	  public List<Taluka> getTalukaList(int talukaCode) throws Exception; 
	  
	  public List<Districts> getDistrictsList(int stateCode) throws Exception;
	  
	  public List<States> getStateList() throws Exception;
	  
	  public String getStateName(int stateCode) throws Exception;
	  
	  public String getDistrictName(int districtCode) throws Exception;
	  
	  public String getTalukaName(int talukaCode) throws Exception;
	  
	  public int getDeliveryChargesByTalukaCode(int talukaCode) throws Exception;
}