package com.edu.EmployeeApplication.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.EmployeeApplication.entity.Department;
import com.edu.EmployeeApplication.entity.Employee;
import com.edu.EmployeeApplication.exception.GivenIdNotFoundException;
import com.edu.EmployeeApplication.exception.NoRecordFoundException;
import com.edu.EmployeeApplication.exception.NoSuchRecordFoundException;
import com.edu.EmployeeApplication.exception.RecordAlreadyExistException;
import com.edu.EmployeeApplication.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public Department getDepartmentById(long id) {
		Optional<Department> employee = departmentRepository.findById(id);//4
		if(employee.isPresent()) {
			return employee.get();
		}
		else {
          
			throw new GivenIdNotFoundException();
		}
		
	}

	@Override
	public List<Department> getDepartmentList() {
		
		List<Department> departments = departmentRepository.findAll();
		if(departments.isEmpty()) {
		throw new NoRecordFoundException();
		}
        else {
        	int i = 0;
        	int[] noOfEmp = departmentRepository.getNumberOfEmployeesInDepartment();
        	
        	//System.out.println(noOfEmp[0] + noOfEmp[1]);
        	Iterator<Department> it = departments.iterator();
        	if( noOfEmp.length != 0) {
        	while(it.hasNext()) {
        		if(noOfEmp.length > i)
        		 		it.next().setNumberOfEmployees(noOfEmp[i]);
        		else
    		 		it.next().setNumberOfEmployees(0);

        			
            	i++;

        	}
        }
        }
        	return departments;
			}

	
	@Override
	public Map<Object, Object> getDepartmentGroupByLocation() {
		List<Object[]> objects =  departmentRepository.getDepartmentGroupByLocation();
		
		Map<Object, Object> map = new HashMap<>();
		
		for(Object[] obj : objects) {
			map.put(obj[0], obj[1]);
		}
		return map;
	}

	@Override
	public String saveDepartment(Department department) {
		Optional<Department> dept = departmentRepository.findByDepartmentId(department.getDepartmentId());

		if( dept.isPresent()) {
			
			throw new RecordAlreadyExistException();
		}
		else
			departmentRepository.save(dept);
		return "Record inserted successfully !";

		
		return departmentRepository.save(department);
	}

	@Override
	public Department updateDepartment(long id, @Valid Department dept) {
		
		Department  d;
		Optional<Department> currentDept = departmentRepository.findById(id);
		if(currentDept.isPresent()) {
			d = currentDept.get();
			
			d.setDepartmentId(dept.getDepartmentId());
			d.setDepartmentName(dept.getDepartmentName());
			d.setLocation(dept.getLocation());
			
			return departmentRepository.save(d);
		}
		else
			throw new NoSuchRecordFoundException();
		
	}

	@Override
	public String deleteDepartment(long id) {
		Optional<Department> currentDept = departmentRepository.findById(id);
		if(currentDept.isPresent()) {
			departmentRepository.deleteById(id);
			return "Department record is deleted successfully";
		}
		else
			throw new NoSuchRecordFoundException();
		}
		
	



}
