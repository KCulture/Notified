package com.github.KCulture.Notified.Services;

import java.util.Calendar;


public class SimpleDataStore extends DataStore{

	public SimpleDataStore() {
		super(new MongoDatabaseService());
	}

	@Override
  protected int monthSelectionStrategy() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}

}
