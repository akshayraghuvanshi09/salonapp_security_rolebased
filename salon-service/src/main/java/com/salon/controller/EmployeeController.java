package com.salon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salon.model.Employee;
import com.salon.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/salon/emp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/getAllEmp")
//	@PreAuthorize("hasRole('ADMIN')")   
	@PreAuthorize("hasRole('STAF') or hasRole('RECEPTIONIST') or hasRole('ADMIN')")
	public List<Employee> getEmployees(){
		log.info("Inside the getEmployee method in EmployeeContorller");
		return	employeeService.getEmployees();
	}
	
	@GetMapping("/empById/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		log.info("Inside the getEmployeeById method in EmployeeContorller");
		return  employeeService.getEmployeeById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")  
	@PostMapping("/new")
	public Employee saveEmployee(@RequestBody Employee employee){
		log.info("Inside the saveEmployee method in EmployeeContorller");
		return employeeService.saveEmployee(employee);
	}
	
	@DeleteMapping("/delete")
	public String deleteEmp(@PathVariable("id") Long id) {
		log.info("Inside the deleteEmp method in EmployeeContorller");
		employeeService.deleteEmployee(id);
		return "employee is deleted successfully";
	}
	
}
