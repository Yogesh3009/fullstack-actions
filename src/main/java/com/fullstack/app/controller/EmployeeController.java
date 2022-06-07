package com.fullstack.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.crc.commonlib.exception.custom.JWTValidationTokenFailed;
import com.fullstack.app.dto.AuthUser;
import com.fullstack.app.dto.EmployeeDto;
import com.fullstack.app.dto.ResponseDto;
import com.fullstack.app.model.Employee;
import com.fullstack.app.repository.EmployeeRepo;
import com.fullstack.app.service.EmployeeService;
import com.fullstack.app.service.TokenService;
import com.fullstack.app.util.ReportUtils;

@RestController
public class EmployeeController {

	private Logger logger=LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	EmployeeService service;
	
	@Autowired
	EmployeeRepo repo;
	
	@Autowired
	ReportUtils utils;
	
	@Autowired
	TokenService tokenService;
	
	@PostMapping("/create/employee")
	public Employee save(@RequestBody Employee employee)
	{
		return service.saveEmployee(employee);
	}
	
//	@GetMapping("/details/employee")
//	public ResponseEntity<?> getDetails(@RequestHeader String authorizeToken)
//	{
//		String resp=tokenService.verifyToken(authorizeToken);
//		if(resp.equals("Token Verification Successful")) {
//		return new ResponseEntity<> (service.getAllEmplyee(),HttpStatus.OK);
//		}
//		else {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token Invalid");
//		}
//		
//	}
	
	@GetMapping("/details/employee")
	public ResponseEntity<?> getDetails()
	{
		List<Employee> list=service.getAllEmplyee();
		if(list!=null && !list.isEmpty()) {
		return new ResponseEntity<> (service.getAllEmplyee(),HttpStatus.OK);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Records Not found");
		}
		
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Employee employee = service.findById(id);
				
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/update/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employeeDetails) {
		Employee employee = service.findById(id);
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setMobileNo(employeeDetails.getMobileNo());
		
		Employee updatedEmployee = repo.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/remove/employee/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int id){
		Employee employee = service.findById(id);
		repo.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/employee/reports")
	public ResponseEntity<?> getEmployeeReport(HttpServletResponse response)
	{
		List<EmployeeDto> list=service.getEmplyeeReport();
		boolean result = utils.generateReport(response, list);
		if (result) {
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(404, "Record Not found"));
		}
	}
	
//	private AuthUser verifyAuthUser(String authorizeToken){
//		JWTDto jwtvalidate = new JWTDto();
//		AuthUser authUser = new AuthUser();
//		try {
//			 jwtvalidate = jwtService.getResponseData(authorizeToken);
//			if (jwtvalidate != null) {
//				authUser.setUserName(jwtvalidate.getPreferredUsername());
//				authUser.setVerifyStatus(true);
//			}else {
//				logger.info("Token not valid");
//				throw new JWTValidationTokenFailed("Token invalid!!");
//			}
//		} catch (Exception e) {
//			logger.error("Exception : ", e);
//			throw new JWTValidationTokenFailed("Token invalid!!");
//		}		
//		return authUser;
//	}
}
