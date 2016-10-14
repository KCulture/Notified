package com.github.KCulture.Notified.Services;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
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
	private static final String APPRAISED_COLLECTION = "appraised";
	private static final String COLLECTION = "employee";
	private static final String DATABASE = "test";
	
	
	
	static MongoClient mongoClient = null;
	static DB mongoDB = null;
	static DBCursor cursor = null;
	
	public MongoDatabaseService(){
   this.initDatabase();
	}
	@Override
	public int connect() {
		this.initDatabase();
	  return (mongoClient != null)?1:-1;
	}
	
	private MongoClient initDatabase(){
		try{
			mongoClient = new MongoClient( "localhost" , 27017 );
			mongoDB = mongoClient.getDB(DATABASE);
			cursor = mongoDB.getCollection(COLLECTION).find();
			//TODO Properties could Exchange some of these database parameter
		}catch(UnknownHostException noHost){
			noHost.addSuppressed(noHost);
			System.out.println(noHost.getMessage());
		}
		return mongoClient;
	}
	
	public List<Employee> listOfAppraised(EmployeeSelectionStrategy selected){
		List<Employee> employees = new ArrayList<>();
		DBCursor localCursor = cursor.copy();
			 employees.addAll(selected.getAppraisableEmployees(localCursor));
	  return employees; 
	}
	
	public void writeAppraisableToStorage(EmployeeSelectionStrategy selected){
		List<DBObject> appraised = new ArrayList<>();
		DBCursor dbCopy = cursor.copy();
	  appraised.addAll(selected.getAppraisableDBObjects(dbCopy)); 
	  mongoDB.getCollection(APPRAISED_COLLECTION).insert(appraised);
		return ; 
	}
	
	
	
}
