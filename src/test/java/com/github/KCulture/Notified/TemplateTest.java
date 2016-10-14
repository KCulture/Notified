package com.github.KCulture.Notified;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.github.KCulture.Notified.Repository.Employee;
import com.github.KCulture.Notified.Services.EmailMessageService;
import com.github.KCulture.Notified.Services.EmployeeSelectionStrategy;
import com.github.KCulture.Notified.Services.MongoDatabaseService;
import com.github.KCulture.Notified.Services.QuarterlyStrategy;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;


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
   // @Test
   // public void sendMessageTest() {
    	//TODO this will require setup of email account
    	// EmailMessageService email = new EmailMessageService();
    	// Assert.assertTrue(se.loadProps().containsKey("mail.smtp.host"));
//    }
    
//    @Test
//    public void returned2EmployeeTest() {
//    	MongoDatabaseService mongoDBClient = new MongoDatabaseService();
//    	List<Employee> employees =  mongoDBClient.listOfAppraised(6);
//    	Assert.assertTrue(employees.size() == 2);
//    }
    
//    @Test
//    public void findRightQuarterTest() {
//    	MongoDatabaseService mongoDBClient = new MongoDatabaseService();
//    	int quarter =  mongoDBClient.findRightQuarter(Calendar.getInstance());
//    	Assert.assertTrue( quarter == 3);
//    } will enable once I get something to test private methods
    
//    @Test
//    public void findRightQuarterTest() {
//    	MongoDatabaseService mongoDBClient = new MongoDatabaseService();
//    	boolean quarter =  mongoDBClient.isAppraisalDate(Calendar.getInstance());
//    	Assert.assertTrue( quarter );
//    }
//  @Test
//  public void findRightQuarterTest() {
//  	MongoDatabaseService mongoDBClient = new MongoDatabaseService();
//  	boolean quarter =  mongoDBClient.isAppraisalDate(Calendar.getInstance().getTime());
//  	Assert.assertTrue( quarter );
//  }
  @Test
  public void findRightQuarterTest() {
  	MongoDatabaseService mongoDBClient = new MongoDatabaseService();
  	List<Employee> employees =  mongoDBClient.listOfAppraised(new QuarterlyStrategy());
  	System.out.println(employees.get(0).hireDate);
  	Assert.assertTrue( employees.size() == 1 );
  }
  
  public void checknewDatabaseTest() {
  	MongoDatabaseService mongoDBClient = new MongoDatabaseService();
  	List<DBObject> employees =  mongoDBClient.writeAppraisableToStorage(new QuarterlyStrategy());
  	 long count = mongoDBClient.getMongoDB().getCollection("appraised").count();
  	Assert.assertTrue( employees.size() == count );
  	mongoDBClient.getMongoDB().getCollection("appraised").remove(new BasicDBObject() );
  }
}
