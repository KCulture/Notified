package com.github.KCulture.Notified.Services;

import java.util.List;

import com.github.KCulture.Notified.Repository.Employee;

public class MailCommando implements Commando {
	List<Employee> employees;
	EmailMessageService emailService = new EmailMessageService();
	
	MailCommando(List<Employee> employees){
		this.employees = employees;
	}
	@Override
  public void execute() {
		emailService.sendMessage(emailService.loadProps(),employees);
	}
	@Override
  public boolean status() {
	  emailService.getLastStatus();
	  return false;
  }
	
}
