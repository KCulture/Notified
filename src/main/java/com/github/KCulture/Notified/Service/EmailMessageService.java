package com.github.KCulture.Notified.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
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

private static final String TO = "**";
//private boolean lastSentStatus = true; //TODO 10-17-16 remove today if no purpose is found 


public void sendMessage(Properties properties,List<Employee> employees) { 
 class SMTPAuthenticator extends javax.mail.Authenticator {
  	 
  	 public PasswordAuthentication getPasswordAuthentication() {
  	 return new PasswordAuthentication ("xx", "xx"); // password not displayed here, but gave the right password in my actual code.
  	 }
 }
   
   // Sender's email ID needs to be mentioned
   String from = properties.getProperty("FROM");
   
    Authenticator auth = new SMTPAuthenticator();
    Session session = Session.getInstance(properties, auth);
    session.setDebug(true);

   try {
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.addRecipients(Message.RecipientType.TO, this.getEmployeeEmails(employees));

      // Set Subject: header field
      message.setSubject("This is the Subject Line!");

      // Now set the actual message
      message.setText("This is actual message");

      // Send message
      Transport.send(message);
      System.out.println("Sent message successfully....");
//      this.lastSentStatus = true;
   }catch (MessagingException mex) {
      mex.printStackTrace();
//      this.lastSentStatus = false;
   }
}

public Properties loadProps(){
	Properties properties = new Properties();
	try {
		InputStream in = getClass().getResourceAsStream("config.txt");
		if(in !=null){
			properties.load(in);
			System.out.println("made it");
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	return properties;
}

//public boolean getLastStatus(){
//	return this.lastSentStatus;
//}

private InternetAddress[] getEmployeeEmails(List<Employee> employees) throws AddressException{
	StringBuilder addresses = new StringBuilder();
	for(Employee employee :employees){
		addresses.append(employee.email+",");
	}
	return InternetAddress.parse(addresses.toString());
}

}
