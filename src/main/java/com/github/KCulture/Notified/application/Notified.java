package com.github.KCulture.Notified.application;
import java.util.List;

import com.github.KCulture.Notified.Repository.Employee;
import com.github.KCulture.Notified.Services.Commando;
import com.github.KCulture.Notified.Services.SimpleDataStore;
import com.github.KCulture.Notified.Services.MailCommando;
import com.github.KCulture.Notified.Services.CmdTrigger;


public class Notified {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Employee> employees = new SimpleDataStore().getEmployees();
		Commando command = new MailCommando(employees);
		CmdTrigger cmdTrigger = new CmdTrigger();
		cmdTrigger.triggerCommand(command);
	}

}
