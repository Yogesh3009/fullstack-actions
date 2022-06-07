package com.fullstack.app.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crc.commonlib.exception.custom.RequestFailedException;
import com.fullstack.app.dto.AuthUser;
import com.fullstack.app.dto.EmployeeDto;
import com.fullstack.app.model.Employee;
import com.fullstack.app.repository.EmployeeRepo;
import com.fullstack.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private Logger logger=LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepo repo;
	
	@Override
	public List<Employee> getAllEmplyee() {
		List<Employee> list=repo.findAll();
		for(Employee s: list)
		{
			s.setMobileNo(s.getMobileNo().replaceAll(".(?=.{4})","*"));
			s.setEmailId(s.getEmailId().replaceAll("(^[^@]{1}|(?!^)\\G)[^@]", "$1*"));
		}
		return repo.findAll();
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		try {
		String emailId=repo.checkExist(employee.getEmailId());
		if(emailId!=null) {
			throw new RequestFailedException("already present");
		}
		
	} catch (RequestFailedException e) {
		logger.error("Exception {}", e);
		throw new RequestFailedException(e.getMessage());
	}
		return repo.save(employee);
	}

	@Override
	public Employee findById(int id) {
		
		return repo.findById(id).get();
	}

	@Override
	public List<EmployeeDto> getEmplyeeReport() {
		return repo.getReports();
	}

}
