package com.github.KCulture.Notified.Service;

import java.util.List;
import java.util.Properties;

import com.github.KCulture.Notified.Repository.EmailContactable;

public class MailCommando implements Commando {
	List<EmailContactable> contacts;
	EmailMessageService emailService = new EmailMessageService();
	final Properties propFile;

	public MailCommando(List<EmailContactable> contacts, Properties propFile) {
		this.contacts = contacts;
		this.propFile = propFile;
	}

	@Override
	public void execute() {
		emailService.sendMessage(this.propFile, contacts);
	}

}
