package com.github.KCulture.Notified.Services;

import java.util.List;

import com.github.KCulture.Notified.Repository.Employee;
import com.mongodb.DBObject;

public abstract class DataStore {
  protected DatabaseService dbService;
  protected QuarterlyStrategy quarterlyStrategy;
  
  public DataStore(DatabaseService dbService, QuarterlyStrategy quarterlyStrategy) {
	  this.dbService = dbService;
	  this.quarterlyStrategy = quarterlyStrategy;
  }
  
  public List<Employee> getEmployees(){
  	return this.dbService.listOfAppraised(this.quarterlyStrategy);
  	
  }

	protected abstract int monthSelectionStrategy();
	
	public abstract List<DBObject> writeAppraisableEmployeesToStorage();
  
}
