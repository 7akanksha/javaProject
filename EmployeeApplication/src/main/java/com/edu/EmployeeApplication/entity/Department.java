package com.edu.EmployeeApplication.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Department {
	
	@Id
	@GeneratedValue(generator = "seq2", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq2", initialValue = 101)

	private long id;
	private String departmentId;	
    private String departmentName;
	private String location;
	@Transient
	private int numberOfEmployees;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}
	public void setNumberOfEmployees(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	@PrePersist
	void empNumbers() {
		numberOfEmployees = 0;
	}
	
	
	public Department(long id, String departmentId, String departmentName, String location, int numberOfEmployees
			) {
		super();
		this.id = id;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.location = location;
		this.numberOfEmployees = numberOfEmployees;
	}	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
