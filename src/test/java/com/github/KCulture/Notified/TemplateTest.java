package com.github.KCulture.Notified;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.KCulture.Notified.Repository.Employee;
import com.github.KCulture.Notified.Services.EmailMessageService;
import com.github.KCulture.Notified.Services.MongoDatabaseService;


public class TemplateTest {
    @Test
    public void configFileLoadTest() {
    	EmailMessageService se = new EmailMessageService();
    	Assert.assertTrue(se.loadProps().containsKey("mail.smtp.host"));
    }
    
    @Test
    public void MongoTest() {
    	MongoDatabaseService mongoDBClient = new MongoDatabaseService();
    	int status =  mongoDBClient.connect();
    	Assert.assertTrue(status > -1);
    }
    @Test
    public void sendMessageTest() {
    	//TODO this will require setup of email account
    	// EmailMessageService email = new EmailMessageService();
    	// Assert.assertTrue(se.loadProps().containsKey("mail.smtp.host"));
    }
    
    @Test
    public void returned2EmployeeTest() {
    	MongoDatabaseService mongoDBClient = new MongoDatabaseService();
    	List<Employee> employees =  mongoDBClient.listOfAppraised(6);
    	Assert.assertTrue(employees.size() == 2);
    }
}
