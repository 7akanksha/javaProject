package programs;

import java.time.LocalDate;
import java.util.Scanner;

class Student{
	
	public int rollNo;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String email;
	
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
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
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
public class FirstClass {
	

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Student s1 = new Student();
		Student s2 = new Student();
       
		
		System.out.println("Enter roll No ");
		//s1.setRollNo(scanner.nextInt());
		System.out.println("Enter first Name");
		s1.setFirstName(scanner.next());
		
		
		s2.setRollNo(2);
		s2.setFirstName("xyz");
		
		System.out.println("name of s1  " + s1.getFirstName() );// abcd
		System.out.println("name of s2  " + s2.getFirstName());//xyz
		
		
		int a  ;
        System.out.println(a);
        
        System.out.println(s1.rollNo);
        
		
	}

}
