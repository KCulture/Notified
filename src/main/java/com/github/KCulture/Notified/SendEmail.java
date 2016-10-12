package com.github.KCulture.Notified;

//File Name SendEmail.java

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {

private static final String FROM = "**";
private static final String TO = "**";

public static void main(String [] args) {    
   // Recipient's email ID needs to be mentioned.
   String to = TO;

   // Sender's email ID needs to be mentioned
   String from = FROM;

   // Assuming you are sending email from localhost
   String host = "smtp.gmail.com";

   // Get system properties
   Properties properties = System.getProperties();

   // Setup mail servertasks
   properties.setProperty("mail.smtp.host", host);
   
   //property for authentication 
   properties.setProperty("mail.smtp.user", "xx");
   properties.setProperty("mail.smtp.password", "**" );
   properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
   properties.setProperty("mail.smtp.ssl.enable", "true");
   properties.put("mail.smtp.starttls.enable","true");
   properties.setProperty("mail.smtp.auth", "true");
   properties.setProperty("mail.smtp.port", "465");
   
    class SMTPAuthenticator extends javax.mail.Authenticator {
  	 
  	 public PasswordAuthentication getPasswordAuthentication() {
  	 return new PasswordAuthentication ("xx", "xx"); // password not displayed here, but gave the right password in my actual code.
  	 }
   }
    Authenticator auth = new SMTPAuthenticator();
    Session session = Session.getInstance(properties, auth);
    session.setDebug(true);

   // Get the default Session object.
 //  Session session = Session.getDefaultInstance(properties);

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
   }catch (MessagingException mex) {
      mex.printStackTrace();
   }
}

public void getSomething(){
  //TODO: 10-11-16
	//will later config file so information can be added manually
	// new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/config.txt"))); 
}

public void saveProps(){
	try {
		
	//	Files.newBufferedReader(Paths.get("/config.txt"),Charset.defaultCharset());  
	// TODO: Not working but stuff to work on	
		Properties properties = new Properties();
		properties.setProperty("favoriteAnimal", "marmot");
		properties.setProperty("favoriteContinent", "Antarctica");
		properties.setProperty("favoritePerson", "Nicole");

		FileWriter file = new FileWriter("test2.properties");
		properties.store(file, "Favorite Things");
		file.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
