package com.edu.EmployeeApplication.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Employee {
	@Id
	@GeneratedValue(generator = "seq1", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "seq1", initialValue = 11)

	private long id;
	@Column(nullable = false)
	@NotNull
	@NotBlank(message = "Employee Id is mandatory")
	private String employeeId;
	
	@NotNull
	@NotBlank(message = "First name is mandatory")
	private String firstName;
	@Column(nullable = false)
	@NotBlank(message = "Last name is mandatory")
	private String lastName;
    private String contactNo;
    private String designation;
	@Column(nullable = false)
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Invalid email id")

	private String email;
	//@NotBlank(message = "Password is mandatory")

	private String password;
	private String role;
	private int isActive;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfJoin;
    private LocalDateTime dateOfRegistration;
	
	@ManyToOne
	@JoinColumn(name = "deptId" )
	private Department department;

	
	/*@PrePersist
	public void addDateOfRegister() {
		this.dateOfRegistration = LocalDateTime.now();
		this.role = "EMPLOYEE";
		this.isActive = true;

	}*/
	
	public LocalDateTime getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(LocalDateTime dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	
	
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public String getContactNo() {
		return contactNo;
	}



	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}



	public String getDesignation() {
		return designation;
	}



	public void setDesignation(String designation) {
		this.designation = designation;
	}



	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


		public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getDateOfJoin() {
		return dateOfJoin;
	}

	public void setDateOfJoin(LocalDate dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	public Employee(long id, @NotNull @NotBlank(message = "First name is mandatory") String firstName,
			@NotBlank(message = "Last name is mandatory") String lastName,
			@NotBlank(message = "Email is mandatory") @Email String email, LocalDate dateOfBirth, LocalDate dateOfJoin,
		Department department) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoin = dateOfJoin;
		this.department = department;
	}
	
	public Employee(long id, @NotNull @NotBlank(message = "Employee Id is mandatory") String employeeId,
			@NotNull @NotBlank(message = "First name is mandatory") String firstName,
			@NotBlank(message = "Email is mandatory") @Email(message = "Invalid email id") String email) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.email = email;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", dateOfBirth=" + dateOfBirth + ", dateOfJoin=" + dateOfJoin 
				+ ", department=" + department + "]";
	}

}
