package com.github.KCulture.Notified;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.AddressException;

import org.junit.Assert;
import org.junit.Test;

import com.github.KCulture.Notified.Repository.Employee;
import com.github.KCulture.Notified.Service.EmailMessageService;
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
//    public void sendMail() throws AddressException, IOException {
//    	EmailMessageService se = new EmailMessageService();	
//    	Employee e = new Employee("32wefdsada","a", "", Calendar.getInstance().getTime(), "a@acme.com");
//    	Employee b = new Employee("32wefwaed","a", "", Calendar.getInstance().getTime(), "b@acme.com");
//    	Properties props = new Properties();
//    	props.load(Notified.class.getResourceAsStream("config.txt"));
//    	List<Employee> aa = new ArrayList<>();
//    	aa.add(e);aa.add(b);
//    	se.sendMessage(props, aa); 
//    }
//		@Test
//		public void noCommandArgTest() throws IOException{
//			MongoDatabaseService mongo = new MongoDatabaseService(getPropFile());
//			mongo.getMongoDB().getCollection("appraised").remove(new BasicDBObject() );
//			String[] strang = {""}; 
//			Notified.main(strang);
//			Assert.assertTrue("",mongo.getMongoDB().getCollection("appraised").getCount() > 0);
//			mongo.getMongoDB().getCollection("appraised").remove(new BasicDBObject() );
//			Files.delete(Paths.get(createpropFile()));
//		}
//		
//		@Test //Warning: This will remove everything from appraised collection. Consider before  
//		public void commandLineArgTest() throws IOException {
//			MongoDatabaseService mongo = new MongoDatabaseService(getPropFile());
//			mongo.getMongoDB().getCollection("appraised").remove(new BasicDBObject() );
//			String[] strang = new String[1];
//			strang[0] = createpropFile();
//			Notified.main(strang);
//			Assert.assertTrue("Nothing was added to appraised collection",mongo.getMongoDB().getCollection("appraised").getCount() > 0);
//			mongo.getMongoDB().getCollection("appraised").remove(new BasicDBObject() );
//			Files.delete(Paths.get(strang[0]));
//			
//		}
		
		private String createpropFile(){
			Properties prop = createPropValues();
			try (BufferedWriter br= Files.newBufferedWriter(Paths.get(System.getProperty("user.dir"),"propfile"),Charset.defaultCharset());){
	      prop.store(br, "setting propfile");
      } catch (IOException e) {
	      e.printStackTrace();
	      System.out.println("this is not working");
      }
			return System.getProperty("user.dir").concat("/propfile");
		}

		private Properties createPropValues() {
	    Properties prop = new Properties();
			prop.setProperty("mail.smtp.host", "localhost");
			prop.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			prop.setProperty("mail.smtp.ssl.enable",String.valueOf(false));
			prop.setProperty("mail.smtp.starttls.enable",String.valueOf(false));
			prop.setProperty("mail.smtp.auth",String.valueOf(false));
			prop.setProperty("mail.smtp.port","25");
			prop.setProperty("FROM","soso@localhost");
			prop.setProperty("mongo.server","localhost");
			prop.setProperty("mongo.database","test");
			prop.setProperty("mongo.collection","employee");
			prop.setProperty("mongo.port","27017");
			prop.setProperty("mongo.appraised.collection","appraised");
	    return prop;
    }
		private Properties getPropFile(){
			Properties prop = new Properties();
			try (BufferedReader br= Files.newBufferedReader(Paths.get(createpropFile()),Charset.defaultCharset());){
				prop.load(br);
			} catch (IOException e) {
			}
			return prop;
		}
}
