package com.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.EmployeeDao;
import com.rest.model.Employee;
import com.rest.utility.DateTimeUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public int saveOrUpdateEmployee(Employee employee) throws Exception {
		
		employee.setIsActive(1);
		employee.setCreatedDatetime(DateTimeUtil.getSysDateTime());
		employee.setUpdatedDatetime(DateTimeUtil.getSysDateTime());
		return employeeDao.saveOrUpdateEmployee(employee);
	}

	@Override
	public Employee getEmployeeById(int employeeId) throws Exception {
		// TODO Auto-generated method stub
		return employeeDao.getEmployeeById(employeeId);
	}

	@Override
	public List<Employee> getEmployeeList() throws Exception {
		// TODO Auto-generated method stub
		return employeeDao.getEmployeeList();
	}

	@Override
	public int activateOrDeactivateEmployee(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		employee.setUpdatedDatetime(DateTimeUtil.getSysDateTime());
		return employeeDao.activateOrDeactivateEmployee(employee);
	}
}