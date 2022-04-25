package com.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;
import com.rest.model.Employee;

@Component
public interface EmployeeService {

	  public int saveOrUpdateEmployee(Employee employee) throws Exception;
	  
	  public Employee getEmployeeById(int employeeId) throws Exception;
	  
	  public List<Employee> getEmployeeList() throws Exception;
	  
	  public int activateOrDeactivateEmployee(Employee employee) throws Exception;
}
