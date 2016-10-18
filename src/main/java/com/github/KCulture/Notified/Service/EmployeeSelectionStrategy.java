package com.github.KCulture.Notified.Service;

import java.util.List;

import com.github.KCulture.Notified.Repository.Employee;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public interface EmployeeSelectionStrategy {
	
	public List<? extends Employee> getAppraisableEmployees(DBCursor cursor);
	public List<? extends DBObject> getAppraisableDBObjects(DBCursor cursor);

}
