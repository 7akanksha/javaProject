package com.edu.EmployeeApplication.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.edu.EmployeeApplication.entity.Employee;

public class UserDetailsImpl implements UserDetails{
	
	private String email;
	private String password;
	private boolean active;
	private List<GrantedAuthority> roles;
	
	
	
	public UserDetailsImpl() {
	}

	public UserDetailsImpl(Employee employee) {
		this.email = employee.getEmail();
		this.password = employee.getPassword();
		this.active = employee.isActive();
		this.roles = Arrays.stream(employee.getRole().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles ;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password ;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email ;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true ;
	}
	

}
