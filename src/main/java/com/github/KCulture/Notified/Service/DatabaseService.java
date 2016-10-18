package com.github.KCulture.Notified.Service;

import java.util.List;

import com.github.KCulture.Notified.Repository.Employee;
import com.mongodb.DBObject;

public interface DatabaseService {
	
	public List<Employee> listOfAppraised(EmployeeSelectionStrategy selected);
	public List<DBObject> writeAppraisableToStorage(EmployeeSelectionStrategy selected);

}
