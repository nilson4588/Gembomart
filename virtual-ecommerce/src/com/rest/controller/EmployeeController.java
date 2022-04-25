package com.rest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.model.Employee;
import com.rest.service.EmployeeService;
import com.rest.utility.ConstantsUtil;
import com.rest.utility.DateTimeUtil;
import com.rest.utility.SendSMS;


@RestController  
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class EmployeeController {

	 @Autowired
	 EmployeeService employeeService;
	
	 static final Logger log = Logger.getLogger(EmployeeController.class);

	  @CrossOrigin(origins = "*", allowedHeaders = "*")
	  @RequestMapping(value = "/saveOrUpdateEmployee", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
	  public void saveOrUpdateEmployee(@RequestParam(required = true, value= "employeeData") String employeeData, @RequestParam(required = true, value= "employeePhoto") MultipartFile employeePhoto) throws Exception {
	  
		  try {
			     String fileName = "";
    	         // System.out.println(productImage.getSize());			          
			     if(employeePhoto.getSize() > 0) {
			     
					  fileName =
					  "employee_"+DateTimeUtil.getTimeStampInMiliseconds()+employeePhoto.getOriginalFilename().substring(
							  employeePhoto.getOriginalFilename().lastIndexOf("."));
					  //System.out.println(fileName); 
					  File file = new File(ConstantsUtil.IMAGE_LOCATION+"employee\\"+fileName); 
					  try {
						  FileOutputStream fos = new FileOutputStream(file);
						  fos.write(employeePhoto.getBytes()); fos.close(); 
					  } catch (IOException e) {
						  e.printStackTrace(); 
					  }
			     }
			    
			     ObjectMapper objectMapper = new ObjectMapper();
		         Employee employee         = objectMapper.readValue(employeeData, Employee.class);
		         
		         int empId = employee.getEmployeeId();
		         
		         employee.setEmployeePhoto(fileName);
			     employeeService.saveOrUpdateEmployee(employee);
			    
			    if(empId == 0) {
			    	SendSMS.sendSms(String.valueOf(employee.getContactNumber()), 
				    		"Dear "+employee.getEmployeeName()+", You are successfully registered with GemboMart. Your Login Details are: Registration ID : "+employee.getContactNumber()+" Password : "+employee.getPassword()+" Thanks GemboMart");
				    		
				    SendSMS.sendSms(String.valueOf(employee.getContactNumber()), "Thanks for registration. Your Account will be active in next 24 hrs.");
			    }	
			   
			    log.info("Employee record saved successfully.");		  	
	  
		  } catch(Exception e) {  
			    log.error("Employee failed to saved: "+ e.toString());
		  }
	  }
	  
	  
	  @RequestMapping(value = "/getEmployee/{employeeId}", method = RequestMethod.GET) 
	  public @ResponseBody Employee getEmployeeById(@PathVariable("employeeId") int employeeId) throws Exception {
	  
		  Employee employee = null;
		  try {
		     log.info("Employee Details fetched"); 
		     employee = employeeService.getEmployeeById(employeeId);
	  
		  } catch(Exception e) {
	  
			  log.info("Exception while fetching Employee data : "+e.toString());
		  } 
		  return employee;
	  }
	  
	  @RequestMapping(value = "/employeeList", method = RequestMethod.GET)
	  public @ResponseBody List<Employee> getEmployeeList() throws Exception {
	  
		  List<Employee> employeeList = null;
		  try { 
			    employeeList = employeeService.getEmployeeList();
			  	log.info("Display all Employee List");
			  	
		  }catch(Exception e){
			    log.info("Exception while fetching Employee data : "+e.toString()); 
		  } 
		  return employeeList;
	  }
	  
	  
	  @RequestMapping(value = "/activateOrDeactivateEmployee", method = RequestMethod.POST) 
	  public void activateOrDeactivateEmployee(@RequestBody Employee employee) throws Exception {
	  
		  try { 
			    employeeService.activateOrDeactivateEmployee(employee) ;
		        log.info("Employee activation status changed successfully.");   
		  
		  } catch (Exception e) { 
			  e.printStackTrace();
			    log.info("Exception at Employee activation status. "+e.toString());
		  } 
	  } 
}
