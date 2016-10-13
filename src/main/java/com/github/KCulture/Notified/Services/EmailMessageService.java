package com.github.KCulture.Notified.Services;

import java.util.List;
import java.util.Properties;

import java.io.IOException;
import javax.mail.*;
import javax.mail.internet.*;

import com.github.KCulture.Notified.Repository.Employee;

public class EmailMessageService implements MessageService {

private static final String TO = "**";
private boolean lastSentStatus = true; //TODO possibly need to make abstract class that makes status field 


public void sendMessage(Properties properties,List<Employee> employees) { 
 class SMTPAuthenticator extends javax.mail.Authenticator {
  	 
  	 public PasswordAuthentication getPasswordAuthentication() {
  	 return new PasswordAuthentication ("xx", "xx"); // password not displayed here, but gave the right password in my actual code.
  	 }
   }
   // TODO: the To will be generated from a database call
   String to = TO;

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
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      // Set Subject: header field
      message.setSubject("This is the Subject Line!");

      // Now set the actual message
      message.setText("This is actual message");

      // Send message
      Transport.send(message);
      System.out.println("Sent message successfully....");
      this.lastSentStatus = true;
   }catch (MessagingException mex) {
      mex.printStackTrace();
      this.lastSentStatus = false;
   }
}

public Properties loadProps(){
	Properties properties = new Properties();
	try {
		properties.load(getClass().getResourceAsStream("..//config.txt"));
	} catch (IOException e) {
		e.printStackTrace();
	}
	return properties;
}

public boolean getLastStatus(){
	return this.lastSentStatus;
}

}
