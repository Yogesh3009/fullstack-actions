package com.fullstack.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fullstack.app.dto.EmployeeDto;
import com.fullstack.app.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

	@Query(value = "select first_name as firstName,last_name as lastName,email_id as emailId,mobile_no as mobileNo from Employee", nativeQuery = true)
	public List<EmployeeDto> getReports ();

	@Query(value="select email_id from Employee where email_id=:emailId", nativeQuery = true)
	public String checkExist(String emailId);
}
