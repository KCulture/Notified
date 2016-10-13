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
	MongoClient mongoClient = null;
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
		Calendar rightNow = Calendar.getInstance();
	  rightNow.set(Calendar.MONTH,quarter);
		DB db = mongoClient.getDB("test");
	  DBCursor cursor = db.getCollection("employee").find();
	  try {
	     while(cursor.hasNext()) {
	    	 DBObject object = (DBObject) cursor.next();
	         Date date = (Date)object.get("hireDate");
	         if(date.before(rightNow.getTime())){
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

}
