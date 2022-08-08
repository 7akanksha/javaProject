package com.edu.EmployeeApplication.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.EmployeeApplication.entity.Employee;
import com.edu.EmployeeApplication.exception.GivenIdNotFoundException;
import com.edu.EmployeeApplication.exception.NoRecordFoundException;
import com.edu.EmployeeApplication.exception.NoSuchRecordFoundException;
import com.edu.EmployeeApplication.exception.RecordAlreadyExistException;
import com.edu.EmployeeApplication.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public String saveEmployee(Employee employee) {
		Optional<Employee> emp = employeeRepository.findByEmail(employee.getEmail());
		Optional<Employee> emp1 = employeeRepository.findByEmployeeId(employee.getEmployeeId());
		Optional<Employee> emp2 = employeeRepository.findByContactNo(employee.getContactNo());

		if( emp.isPresent() || emp1.isPresent() || emp2.isPresent()) {
			System.out.println(employee.getEmail());

			throw new RecordAlreadyExistException();
		}
		else
			employeeRepository.save(employee);
		return "Record inserted successfully !";

	}

	@Override
	public List<Employee> getEmployeeList() {
			List<Employee> employees = employeeRepository.findAll();
			if(employees.isEmpty()) {
			throw new NoRecordFoundException();
			}
	        else 
	        	return employees;
	}
	
	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = employeeRepository.findById(id);//4
		if(employee.isPresent()) {
			return employee.get();
		}
		else {
          
			throw new GivenIdNotFoundException();
		}
	}
	@Override
	public Employee updateEmployee(long id, @Valid Employee employee) {
		Employee  emp;;
		Optional<Employee> currentEmployee = employeeRepository.findById(id);
		if(currentEmployee.isPresent()) {
			emp = currentEmployee.get();
			
			emp.setFirstName(employee.getFirstName());
			emp.setLastName(employee.getLastName());		
			emp.setDateOfBirth(employee.getDateOfBirth());
			emp.setDepartment(employee.getDepartment());
			emp.setEmail(employee.getEmail());
			emp.setDesignation(employee.getDesignation());
			emp.setContactNo(employee.getContactNo());
			return employeeRepository.save(emp);
		}
		else
			throw new NoSuchRecordFoundException();
	}

	@Override
	public String deleteEmployee(long id) {
		Optional<Employee> currentEmployee = employeeRepository.findById(id);
		if(currentEmployee.isPresent()) {
			employeeRepository.deleteById(id);
			return "Employee record is deleted successfully";
		}
		else
			throw new NoSuchRecordFoundException();
		}

	@Override
	public List<Employee> getEmployeeByJoiningDate(LocalDate joiningDate) {
		System.out.println(joiningDate);
		return employeeRepository.findByDateOfJoin(joiningDate);
	}

	@Override
	public List<Employee> getEmployeeByDepartmentId(String deptId) {
		List<Employee> emps = employeeRepository.getEmployeeByDepartmentId(deptId);
		if(!emps.isEmpty()) {
			System.out.println(emps);
			}
			else
				throw new NoSuchRecordFoundException();
			return emps;
	}

	@Override
	public List<Employee> getEmployeeByIdRange(long startId, long endId) {
		return employeeRepository.getEmployeeByIdRange(startId, endId);
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		List<Employee> emps = employeeRepository.getEmployeeByName(name);
		
		if(!emps.isEmpty()) {
		System.out.println(emps);
		}
		else
			throw new NoSuchRecordFoundException();
		return emps;
	}

	
	
	

}
