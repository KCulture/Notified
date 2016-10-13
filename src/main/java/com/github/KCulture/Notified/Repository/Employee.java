package com.github.KCulture.Notified.Repository;

import java.util.Date;

public class Employee {
	private String firstName ;
	private String lastName;
	private Date hireDate ;
	
	public Employee(String fName,String lName, Date hireDate){
		this.firstName = fName;
		this.lastName = lName;
		this.hireDate = hireDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getHireDate() {
		return hireDate;
	}
}
