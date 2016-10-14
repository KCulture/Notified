package com.github.KCulture.Notified.Services;

import java.util.Calendar;
import java.util.Date;


public class SimpleDataStore extends DataStore{
	
	public SimpleDataStore() {
		super(new MongoDatabaseService(),new QuarterlyStrategy());
		
	}
		@Override
  protected int monthSelectionStrategy() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}
	
	
}
