package com.github.KCulture.Notified.Services;

import java.util.List;

import com.github.KCulture.Notified.Repository.Employee;

public interface DatabaseService {
	
	public int connect();
	public List<Employee> listOfAppraised(EmployeeSelectionStrategy selected);

}
