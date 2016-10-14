package com.github.KCulture.Notified.Repository;

import java.util.Date;

public class Employee {
	public String firstName ;
	public String lastName;
	public Date hireDate ;
	public String email;
	
	
	public Employee(String fName,String lName, Date hireDate, String email){
		this.firstName = fName;
		this.lastName = lName;
		this.hireDate = hireDate;
		this.email = email;
	}
}
