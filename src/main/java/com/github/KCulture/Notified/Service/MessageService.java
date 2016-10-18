package com.github.KCulture.Notified.Service;

import java.util.List;
import java.util.Properties;

import com.github.KCulture.Notified.Repository.Employee;

public interface MessageService {
	
	public void sendMessage(Properties properties,List<Employee> employees);

}
