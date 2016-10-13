package com.github.KCulture.Notified.Services;

import java.util.List;

import com.github.KCulture.Notified.Repository.Employee;

public abstract class DataStore {
  protected DatabaseService dbService;
  
  public DataStore(DatabaseService dbService) {
	  this.dbService = dbService;
  }
  
  public List<Employee> getEmployees(){
  	return this.dbService.listOfAppraised(this.monthSelectionStrategy());
  	
  }

	protected abstract int monthSelectionStrategy();
  
}
