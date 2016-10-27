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

import com.github.KCulture.Notified.Repository.Employee;

public class EmailMessageService implements MessageService {

public void sendMessage(Properties properties,List<Employee> employees) { 
   String from = properties.getProperty("FROM");
    Authenticator auth = createAuth();
    Session session = Session.getInstance(properties, auth);
    session.setDebug(true);
   try {
  	  Transport.send(createMessage(employees, from, session));
      System.out.println("Sent message successfully....");
   }catch (MessagingException mex) {
      mex.printStackTrace();
   }
}

private Authenticator createAuth(){
	class SMTPAuthenticator extends javax.mail.Authenticator {
 	 public PasswordAuthentication getPasswordAuthentication() {
 	 return new PasswordAuthentication ("xx", "xx"); // password not displayed here, but gave the right password in my actual code.
 	 }
  }
	return new SMTPAuthenticator();
}

private MimeMessage createMessage(List<Employee> employees, String from,
    Session session) throws MessagingException, AddressException {
	MimeMessage message = new MimeMessage(session);
	message.setFrom(new InternetAddress(from));
	message.addRecipients(Message.RecipientType.TO, this.getEmployeeEmails(employees));
	message.setSubject("This is the Subject Line!");
	message.setText("This is actual message");
	return message;
}

private InternetAddress[] getEmployeeEmails(List<Employee> employees) throws AddressException{
	StringBuilder addresses = new StringBuilder();
	for(Employee employee :employees){
		addresses.append(employee.email+",");
	}
	return InternetAddress.parse(addresses.toString());
}

}
