package com.github.KCulture.Notified.Services;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.Calendar;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;



import com.github.KCulture.Notified.Repository.Employee;

public class MongoDatabaseService implements DatabaseService {
	private static final String COLLECTION = "employee";
	private static final String DATABASE = "test";
	MongoClient mongoClient = null;
	final private Calendar currentDate ;
	final private Date marchDate ;
	final private Date juneDate  ;
	final private Date septemberDate ;
	final private Date decemberDate ;
	public MongoDatabaseService(){
		this.currentDate = Calendar.getInstance();
		this.marchDate = this.getMarch();
		this.juneDate  =  this.getJune();
		this.septemberDate = this.getSeptember();
		this.decemberDate  = this.getDecember();
		
		
	}
	@Override
	public int connect() {
		this.initDatabase();
	  return (this.mongoClient != null)?1:-1;
	}
	
	private MongoClient initDatabase(){
		try{
			this.mongoClient = new MongoClient( "localhost" , 27017 );
			// TODO: Build an adapter to encapsulate client 
		}catch(UnknownHostException noHost){
			noHost.addSuppressed(noHost);
			System.out.println(noHost.getMessage());
		}
		return mongoClient;
	}
	
	public List<Employee> listOfAppraised(int quarter){
		List<Employee> employees = new ArrayList<>();
		this.initDatabase();
		//TODO Properties could Exchange some of these database parameter
		DB db = mongoClient.getDB(DATABASE);
	  DBCursor cursor = db.getCollection(COLLECTION).find();
	  try {
	     while(cursor.hasNext()) {
	    	 DBObject object = (DBObject) cursor.next();
	         Date date = (Date)object.get("hireDate");
	         if(isAppraisalDate(date)){
	        	 employees.add(this.toEmployee(object));
	         }
	     }
	  } finally {
	     cursor.close();
	  }
		return employees; 
	}
	
	private Employee toEmployee(DBObject mongoObj){
		
		return new Employee(((String)mongoObj.get("firstName")),((String)mongoObj.get("lastName")) ,((Date) mongoObj.get("hireDate")));
	}
	
	private boolean isAppraisalDate(Date date){
		return findRightQuarter(date) == findRightQuarter(this.currentDate);
	}
	
	private int findRightQuarter(Calendar cal){
		int month = cal.get(Calendar.MONTH);
		return (month / 3) +1;
	}
	
	public int findRightQuarter(Date date){
		if(this.marchDate.after(date))return 1;
		if(this.juneDate.after(date))return 2;
		if(this.septemberDate.after(date))return 3;
		if(this.decemberDate.after(date))return 4;
		return 0;	
	}
	private Date getMarch(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		cal.set(Calendar.HOUR, 11);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	private Date getJune(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 30);
		cal.set(Calendar.HOUR, 11);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	
	private Date getSeptember(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 30);
		cal.set(Calendar.HOUR, 11);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	
	private Date getDecember(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		cal.set(Calendar.HOUR, 11);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
}
