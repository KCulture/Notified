package com.github.KCulture.Notified.Repository;

import java.util.Date;
import java.util.List;

public class Employee implements EmailContactable {
	public String _id;
	public String firstName;
	public String lastName;
	public Date hireDate;
	public String email;
	public List<Responder> contacts;
	public Integer stage; // TODO change back to ENUM if using Jsonparser
	public List<String> superiors;

	public List<String> questions;
	public List<String> answers;
	public String link;

	public Employee(String _id, String fName, String lName, Date hireDate,
	    String email) {
		this._id = _id;
		this.firstName = fName;
		this.lastName = lName;
		this.hireDate = hireDate;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}
