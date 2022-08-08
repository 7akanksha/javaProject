package com.edu.EmployeeApplication.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.EmployeeApplication.entity.Department;

public interface DepartmentRepository  extends JpaRepository<Department, Long>{

	@Query("select d.location , count(d.id) from Department d group by d.location")
	List<Object[]> getDepartmentGroupByLocation();

	@Query("select count(e.id) from Employee e group by e.department.departmentId")
	int[] getNumberOfEmployeesInDepartment();
	
	
	

}
