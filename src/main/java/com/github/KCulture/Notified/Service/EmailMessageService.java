package com.github.KCulture.Notified.Service;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.github.KCulture.Notified.Repository.EmailContactable;
import com.github.KCulture.Notified.Repository.Employee;
import com.github.KCulture.Notified.Repository.Responder;

public class EmailMessageService implements MessageService {
	private String scheme = "http://";
	private String host;
	private String path;

	public void sendMessage(Properties properties, List<EmailContactable> contacts) {
		String from = properties.getProperty("FROM");
		this.host = properties.getProperty("company.host.address",
		    "http://nohost.com");
		this.path = properties.getProperty("company.web.path", "noAppraisalForYou");

		Authenticator auth = createAuth();
		Session session = Session.getInstance(properties, auth);
		session.setDebug(true);
		try {
			for (EmailContactable contact : contacts) {
				Transport.send(createMessage(contact, from, session, properties)); // TODO:
																																					 // (Files1)
																																					 // change
																																					 // call
																																					 // once
																																					 // decided
																																					 // on
																																					 // other
																																					 // Files1
			}
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	private Authenticator createAuth() {
		class SMTPAuthenticator extends javax.mail.Authenticator {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("xx", "xx"); // password not displayed
																											 // here, but gave the
																											 // right password in my
																											 // actual code.
			}
		}
		return new SMTPAuthenticator();
	}

	/*
	 * TODO: (Files1)decide if we should use String(within Property file) or File
	 * (Given as a command argument)or both for body of email Till then make it
	 * easy with String in property file and then make function for file also
	 */
	private MimeMessage createMessage(EmailContactable contact, String from,
	    Session session, Properties properties) throws MessagingException,
	    AddressException, NullPointerException {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipients(Message.RecipientType.TO,
		    InternetAddress.parse(contact.getEmail()));
		message.setSubject(properties.getProperty("email.subject",
		    "remove this email"));
		String emailText = (contact instanceof Employee) ? substituteKeywordForValues(
		    properties, (Employee) contact) : (substituteKeywordForValues(
		    properties, (Responder) contact));
		message.setText(emailText);
		return message;
	}

	private String substituteKeywordForValues(Properties properties,
	    Employee employee) {

		return properties.getProperty("email.body", "No email for you").replace(
		    "AppraislURL", emailResolver(employee, properties));
	}

	private String substituteKeywordForValues(Properties properties,
	    Responder employee) {

		return properties.getProperty("email.body", "No email for you").replace(
		    "AppraislURL", emailResolver(employee, properties));
	}

	private String substituteKeywordForValues(List<String> property,
	    Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	private String emailResolver(Employee employee, Properties properties) {
		return this.scheme.concat(this.host).concat("/").concat(this.path)
		    .concat("/Employee/").concat(employee._id);

	}

	private String emailResolver(Responder contact, Properties properties) {
		return this.scheme.concat(this.host).concat("/").concat(this.path)
		    .concat("/Contacts/").concat(new MongoDatabaseService().findConactId(contact));

	}

}
