package com.github.KCulture.Notified.Services;

import java.util.Calendar;
import java.util.List;

import com.mongodb.DBObject;


public class SimpleDataStore extends DataStore{
	
	public SimpleDataStore() {
		super(new MongoDatabaseService(),new QuarterlyStrategy());
		
	}
		@Override
  protected int monthSelectionStrategy() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}
		@Override
    public List<DBObject> writeAppraisableEmployeesToStorage() {
	   return this.dbService.writeAppraisableToStorage(this.quarterlyStrategy);
	    
    }
	
	
}
