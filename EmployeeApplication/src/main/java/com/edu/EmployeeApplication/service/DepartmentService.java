package com.edu.EmployeeApplication.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.edu.EmployeeApplication.entity.Department;

public interface DepartmentService {

	Department getDepartmentById(long id);

	List<Department> getDepartmentList();

	Map<Object, Object> getDepartmentGroupByLocation();

	String saveDepartment(Department department);

	Department updateDepartment(long id, @Valid Department employee);

	String deleteDepartment(long id);

	
}
