package com.edu.EmployeeApplication.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.edu.EmployeeApplication.entity.Employee;
import com.edu.EmployeeApplication.repository.EmployeeRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	private EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Employee> user = employeeRepository.findByEmail(email);
		System.out.println(user);
		user.orElseThrow(()-> new UsernameNotFoundException("Not found "+ email));
		return user.map(UserDetailsImpl::new).get();
		
	}
	

}
