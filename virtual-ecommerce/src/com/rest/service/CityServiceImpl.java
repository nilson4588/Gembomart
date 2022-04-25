package com.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.CityDao;
import com.rest.model.City;
import com.rest.model.Districts;
import com.rest.model.States;
import com.rest.model.Taluka;

@Service
public class CityServiceImpl implements CityService {

	  @Autowired
	  CityDao cityDao;
	
	  @Override
      public int saveOrUpdateCity(City city) throws Exception {
    	  return cityDao.saveOrUpdateCity(city);
      }
	  
	  @Override
	  public City getCityById(int cityId) throws Exception {
		  return cityDao.getCityById(cityId);
	  }
	  
	  @Override
	  public List<City> getCityList() throws Exception {
		  return cityDao.getCityList();
	  }
	  
	  @Override
	  public int activateOrDeactivateCity(City city) throws Exception {
		  return cityDao.activateOrDeactivateCity(city);
	  }
	  
	  @Override
	  public List<Taluka> getTalukaList(int talukaCode) throws Exception {
		  return cityDao.getTalukaList(talukaCode);
	  }
	  
	  @Override
	  public List<Districts> getDistrictsList(int stateCode) throws Exception{
		  return cityDao.getDistrictsList(stateCode);
	  }
	  
	  @Override
	  public List<States> getStateList() throws Exception {
		  return cityDao.getStateList();
	  }
	  
	  @Override
	  public String getStateName(int stateCode) throws Exception {
		  return cityDao.getStateName(stateCode);
	  }
	  
	  @Override
	  public String getDistrictName(int districtCode) throws Exception {
		  return cityDao.getDistrictName(districtCode);
	  }
	  
	  @Override
	  public String getTalukaName(int talukaCode) throws Exception {
		  return cityDao.getTalukaName(talukaCode);
	  }
	  
	  @Override
	  public int getDeliveryChargesByTalukaCode(int talukaCode) throws Exception {
		  return cityDao.getDeliveryChargesByTalukaCode(talukaCode);
	  }
}
