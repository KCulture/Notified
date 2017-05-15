package com.github.KCulture.Notified.Service;

import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.github.KCulture.Notified.Repository.Employee;

public class MailCommando implements Commando {
	List<Employee> employees;
	EmailMessageService emailService = new EmailMessageService();
	final Properties propFile;
	
	
	public MailCommando(List<Employee> employees, Properties propFile){
		this.employees = employees;
		this.propFile = propFile;	
	}
	@Override
  public void execute() {
		emailService.sendMessage(this.propFile,employees);
	}
	
}
