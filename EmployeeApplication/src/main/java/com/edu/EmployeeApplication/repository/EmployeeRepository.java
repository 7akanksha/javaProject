package com.edu.EmployeeApplication.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.EmployeeApplication.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
   
	Optional<Employee> findByEmployeeId(String string);

	List<Employee> findByDateOfJoin(LocalDate joiningDate);

	@Query("select e from Employee e where e.department.departmentId = :id")
	List<Employee> getEmployeeByDepartmentId(@Param("id")String deptId);

	@Query("select e from Employee e where id between :start and :end")
	List<Employee> getEmployeeByIdRange(@Param("start")long startId, @Param("end")long endId);

	@Query("select e from Employee e where e.firstName = :name OR e.lastName = :name")
	List<Employee> getEmployeeByName(@Param("name")String name);

	Optional<Employee> findByEmail(String email);

	Optional<Employee> findByContactNo(String contactNo);
}
