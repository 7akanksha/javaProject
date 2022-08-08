package com.edu.EmployeeApplication.serviceTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.edu.EmployeeApplication.entity.Employee;
import com.edu.EmployeeApplication.exception.GivenIdNotFoundException;
import com.edu.EmployeeApplication.exception.NoRecordFoundException;
import com.edu.EmployeeApplication.exception.NoSuchRecordFoundException;
import com.edu.EmployeeApplication.exception.RecordAlreadyExistException;
import com.edu.EmployeeApplication.repository.EmployeeRepository;
import com.edu.EmployeeApplication.service.EmployeeServiceImpl;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@Autowired
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	//private Employee employee;
	
	private Employee employee1;
	private Employee employee2;
	List<Employee> employeeList;
	
	
	// Method to execute before each testcase execution
	// before each testcase
	@BeforeEach
	public void setUp() {
		employeeList = new ArrayList<>();
		
		employee1 = new Employee(11,"E1", "john","john@gmail.com");
		employee2 = new Employee(12,"E2", "jonny","jonny@gmail.com");
		
        employeeList.add(employee1);
        employeeList.add(employee2);
	}
	
	// Method to execute after each testcase execution

	@AfterEach
	public void afterTest() {
		employee1 = employee2= null;
		employeeList = null;
	}

	// To test saveEmployee() method
	@DisplayName("Test for saveEmployee() method")
	@Test
	public void givenEmployeeToAddShouldReturnAddedEmployee() {
		
		when(employeeRepository.save(employee1)).thenReturn(employee1);
		
		// when - behavior that we are going test
		
		Employee savedEmployee = employeeService.saveEmployee(employee1);
		
		System.out.println(savedEmployee);
		assertThat(savedEmployee).isNotNull();
		
	}
	
	// To test saveEmployee() method throws exception if given Record is already exist 
    @Test
    public void givenExistingIdWhenSaveEmployeeThenThrowsException(){
    	
    	Employee employee = new Employee(11,"E1","John","john@gmail.com");
    	
        when(employeeRepository.findById(employee.getId()))//11
                .thenReturn(Optional.of(employee));//11
        
        
       org.junit.jupiter.api.Assertions.assertThrows(RecordAlreadyExistException.class, () -> {
            employeeService.saveEmployee(employee);
        });

    }


    // To test getEmployeeList() method
	@Test
	public void givenGetAllEmployeesShouldReturnListOfAllEmployees()throws NoRecordFoundException {
		
		employeeRepository.saveAll(employeeList);
		
		when(employeeRepository.findAll()).thenReturn(employeeList);
		
		List<Employee> actualEmployeeList = employeeService.getEmployeeList();
		
		assertThat(actualEmployeeList).isEqualTo(employeeList);
		
	}
	
	@Test
	public void givenIdThenShouldReturnEmployeeOfThatId() throws GivenIdNotFoundException{
		
		when(employeeRepository.findById(11L)).thenReturn(Optional.ofNullable(employee1));
		assertThat(employeeService.getEmployeeById(employee1.getId())).isEqualTo(employee1);
		
	}
	
	@Test
	public void givenIdToDeleteThenShouldDeleteEmployeeOfThatId() {
		when(employeeRepository.findById(employee1.getId())).thenReturn(Optional.ofNullable(employee1));
		
		
		
        assertThat(employeeService.deleteEmployee(employee1.getId())).isEqualTo("Employee record is deleted successfully");
	}
	
	@Test
	public void givenIdToDeleteNotExistThenThrowsException()  {
		long employeeId = 1L;
	    org.junit.jupiter.api.Assertions.assertThrows(NoRecordFoundException.class, () -> {
        employeeService.deleteEmployee(employeeId);
});

	} 
	
    @DisplayName("JUnit test for updateEmployee method")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
    	long id = 11L;
        when(employeeRepository.save(employee1)).thenReturn(employee1);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee1));
        employee1.setEmail("ram@gmail.com");
        employee1.setFirstName("Ram");
        Employee updatedEmployee = employeeService.updateEmployee(id, employee1);

        assertThat(updatedEmployee.getEmail()).isEqualTo("ram@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Ram");
    }
    
    @Test
	public void givenIdToUpdateNotExistThenThrowsException()  {
		long employeeId = 1L;
		Employee employee = new Employee();
		employee1.setEmail("ram@gmail.com");
        employee1.setFirstName("Ram");
        
	    org.junit.jupiter.api.Assertions.assertThrows(NoSuchRecordFoundException.class, () -> {
        employeeService.updateEmployee(employeeId, employee);
});

	} 

    

}
