package com.edu.EmployeeApplication.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.edu.EmployeeApplication.entity.Employee;

public interface EmployeeService {

	String saveEmployee(Employee employee);

	List<Employee> getEmployeeList();

	Employee getEmployeeById(long id);

	Employee updateEmployee(long id, @Valid Employee employee);

	String deleteEmployee(long id);

	List<Employee> getEmployeeByJoiningDate(LocalDate joiningDate);

	List<Employee> getEmployeeByDepartmentId(String deptId);

	List<Employee> getEmployeeByIdRange(long startId, long endId);

	List<Employee> getEmployeeByName(String name);

}
