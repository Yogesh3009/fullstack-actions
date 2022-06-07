package com.fullstack.app.service;

import java.util.List;

import com.fullstack.app.dto.AuthUser;
import com.fullstack.app.dto.EmployeeDto;
import com.fullstack.app.model.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmplyee();
	
	public Employee saveEmployee(Employee employee);
	
	public Employee findById(int id);
	
	public List<EmployeeDto> getEmplyeeReport();
}
