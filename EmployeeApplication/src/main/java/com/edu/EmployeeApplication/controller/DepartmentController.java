package com.edu.EmployeeApplication.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.EmployeeApplication.entity.Department;
import com.edu.EmployeeApplication.service.DepartmentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/department")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@PostMapping
	public ResponseEntity<Department> saveDepartment( @RequestBody Department department){
		return new ResponseEntity<Department>(departmentService.saveDepartment(department), HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public Department getDepartmentById(@PathVariable("id") long id) {
		return departmentService.getDepartmentById(id);
	}
	
	
	@GetMapping
	public List<Department> getDepartmentList(){
		return departmentService.getDepartmentList();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Department> updateEmployee(@PathVariable("id") long id, @Valid @RequestBody Department employee) {
		
		return new ResponseEntity<Department>(departmentService.updateDepartment(id , employee), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable long id){
		return new ResponseEntity<String>(departmentService.deleteDepartment(id), HttpStatus.OK);
	}
	// To get  department group by location
	@GetMapping("/getDepartmentGoupByLocation")
	public ResponseEntity<Map<Object, Object>> getDepartmentGroupByLocation(){
		return new ResponseEntity<Map<Object, Object>>(departmentService.getDepartmentGroupByLocation(), HttpStatus.OK);
	}
	
	
}
