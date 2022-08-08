package com.edu.EmployeeApplication.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.EmployeeApplication.entity.Employee;
import com.edu.EmployeeApplication.service.EmployeeService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/employee")
	public String saveEmployee(@Valid @RequestBody Employee employee){
		
		return employeeService.saveEmployee(employee);
	}
	
	@GetMapping("/auth")
	public String login() {
		return "Logged in successfully";
	}

	@GetMapping("/employee")
	public List<Employee> getEmployeeList(){
		return employeeService.getEmployeeList();
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable long id) {
		return employeeService.getEmployeeById(id);
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @Valid @RequestBody Employee employee) {
		
		return new ResponseEntity<Employee>(employeeService.updateEmployee(id , employee), HttpStatus.OK);
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable long id){
		return new ResponseEntity<String>(employeeService.deleteEmployee(id), HttpStatus.OK);
	}
	
	// To get employee record by given joining date
	@GetMapping("/employee/getEmployeeByJoiningDate/{joiningDate}")
	public List<Employee> getEmployeeByJoiningDate(@PathVariable("joiningDate")  String joiningDate){
		return employeeService.getEmployeeByJoiningDate(LocalDate.parse(joiningDate));
	}
	
	// To get employee records by given department id
	@GetMapping("/employee/getEmployeeByDeptId/{deptId}")
	public List<Employee> getEmployeeByDepartmentId(@PathVariable("deptId") String deptId){
		List<Employee> emps = employeeService.getEmployeeByDepartmentId(deptId);
		System.out.println(emps);
		return emps;
		
	}
	
	// To get employees by given id range
	@GetMapping("/employee/getEmployeeByIdRange")
	public List<Employee> getEmployeeByIdRange(@RequestParam("startId") long startId, @RequestParam("endId") long endId){
		return employeeService.getEmployeeByIdRange(startId, endId);
	}
	
	@GetMapping("/employee/getEmployeeByName/{name}")
	public List<Employee> getEmployeeByName(@PathVariable("name") String name){
		System.out.println(name);
		List<Employee> emps = employeeService.getEmployeeByName(name);
		System.out.println(emps);
		return emps;
		
	}
	
	//
	
}
