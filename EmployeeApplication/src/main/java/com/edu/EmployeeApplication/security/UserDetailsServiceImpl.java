package com.edu.EmployeeApplication.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edu.EmployeeApplication.entity.Employee;
import com.edu.EmployeeApplication.repository.EmployeeRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	

	public UserDetailsServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
        Optional<Employee> optionalUser = employeeRepository.findByEmail(username);
        optionalUser.orElseThrow(()->new UsernameNotFoundException("Email Not Found"));
            return    optionalUser.map(UserDetailsImpl::new).get();

	}

}
