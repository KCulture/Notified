package com.github.KCulture.Notified.Service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.github.KCulture.Notified.Repository.Employee;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDatabaseService implements DatabaseService {
	private static final String APPRAISED_COLLECTION = "appraised001";
	private static final String COLLECTION = "employee";
	private static final String DATABASE = "test";
	
	
	
	private static  MongoClient mongoClient ;
	private static DB mongoDB = null;
	private static DBCursor cursor = null;
	private final Properties propsFile;
	
	public MongoDatabaseService() {
		this.propsFile = null;
		this.initDatabase();
	 
	}
	
	public MongoDatabaseService(Properties propsFile){
		this.propsFile = propsFile;
		this.initDatabase();
		
	}
	
	private MongoClient initDatabase(){
		try{
			if(this.propsFile != null){
				mongoClient = new MongoClient( "localhost" , 27017 );
				mongoDB = mongoClient.getDB(DATABASE);
				cursor = mongoDB.getCollection(COLLECTION).find();
				
			}
			else{
			mongoClient = new MongoClient( "localhost" , 27017 );
			mongoDB = mongoClient.getDB(DATABASE);
			cursor = mongoDB.getCollection(COLLECTION).find();
			//TODO Properties could Exchange some of these database parameter
			}
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
	//TODO: need to do some exception work in case duplicate key issues arises
	public List<DBObject> writeAppraisableToStorage(EmployeeSelectionStrategy selected){
		List<DBObject> appraised = new ArrayList<>();
		DBCursor dbCopy = cursor.copy();
	  appraised.addAll(selected.getAppraisableDBObjects(dbCopy)); 
	  mongoDB.getCollection(APPRAISED_COLLECTION).insert(appraised);
		return appraised; 
	}
	
	public DB getMongoDB(){
		return mongoDB;
	}
	
	
	
}
