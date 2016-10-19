package com.github.KCulture.Notified;

import org.junit.Test;
import org.junit.Assert;

import com.github.KCulture.Notified.Service.MongoDatabaseService;
import com.github.KCulture.Notified.application.Notified;
import com.mongodb.BasicDBObject;


public class TemplateTest {
//    @Test
//    public void configFileLoadTest() {
//    	EmailMessageService se = new EmailMessageService();
//    	Assert.assertTrue(se.loadProps().containsKey("mail.smtp.host"));
//    }
//    
//    @Test
//    public void MongoTest() {
//    	MongoDatabaseService mongoDBClient = new MongoDatabaseService();
//    	int status =  mongoDBClient.connect();
//    	Assert.assertTrue(status > -1);
//    }
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
//  @Test
//  public void findRightQuarterTest() {
//  	MongoDatabaseService mongoDBClient = new MongoDatabaseService();
//  	List<Employee> employees =  mongoDBClient.listOfAppraised(new QuarterlyStrategy());
//  	System.out.println(employees.get(0).hireDate);
//  	Assert.assertTrue( employees.size() == 1 );
//  }
//  
//  public void checknewDatabaseTest() {
//  	MongoDatabaseService mongoDBClient = new MongoDatabaseService();
//  	List<DBObject> employees =  mongoDBClient.writeAppraisableToStorage(new QuarterlyStrategy());
//  	 long count = mongoDBClient.getMongoDB().getCollection("appraised").count();
//  	Assert.assertTrue( employees.size() == count );
//  	mongoDBClient.getMongoDB().getCollection("appraised").remove(new BasicDBObject() );
//  }
//    @Test 
//  public void viewToAddresses() throws AddressException {
//  	EmailMessageService se = new EmailMessageService();	
//  	Employee e = new Employee("a", "", Calendar.getInstance().getTime(), "a@acme.com");
//  	Employee b = new Employee("a", "", Calendar.getInstance().getTime(), "b@acme.com");
//  	List<Employee> aa = new ArrayList<>();
//  	aa.add(e);aa.add(b);
//  	Assert.assertTrue(se.getEmployeeEmails(aa).length == 2); 
//  }
//    @Test
//  public void checkItem1() throws AddressException {
//  	EmailMessageService se = new EmailMessageService();	
//  	Employee e = new Employee("a", "", Calendar.getInstance().getTime(), "a@acme.com");
//  	Employee b = new Employee("a", "", Calendar.getInstance().getTime(), "b@acme.com");
//  	List<Employee> aa = new ArrayList<>();
//  	aa.add(e);aa.add(b);
//  	Assert.assertTrue(se.getEmployeeEmails(aa)[0].getAddress().equalsIgnoreCase("a@acme.com")); 
//  }
//    @Test
//    public void sendMail() throws AddressException {
//    	EmailMessageService se = new EmailMessageService();	
//    	Employee e = new Employee("a", "", Calendar.getInstance().getTime(), "a@acme.com");
//    	Employee b = new Employee("a", "", Calendar.getInstance().getTime(), "b@acme.com");
//    	List<Employee> aa = new ArrayList<>();
//    	aa.add(e);aa.add(b);
//    	se.sendMessage(se.loadProps(), aa); 
//    }
		@Test
		public void noCommandArgTest() {
			MongoDatabaseService mongo = new MongoDatabaseService();
			mongo.getMongoDB().getCollection("appraised").remove(new BasicDBObject() );
			String[] strang = {""}; 
			Notified.main(strang);
			Assert.assertTrue("",mongo.getMongoDB().getCollection("appraised").getCount() > 0);
			mongo.getMongoDB().getCollection("appraised").remove(new BasicDBObject() );
			
		}
}
