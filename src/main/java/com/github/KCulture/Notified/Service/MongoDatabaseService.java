package com.github.KCulture.Notified.Service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.github.KCulture.Notified.Repository.Employee;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDatabaseService implements DatabaseService {
	
	private static  MongoClient mongoClient ;
	private static DB mongoDB = null;
	private static DBCursor cursor = null;
	private final Properties propsFile;
	
	
	public MongoDatabaseService() {
		this.propsFile = new Properties();
		this.initDatabase();
	 
	}
	
	public MongoDatabaseService(Properties propsFileLocation){
		this.propsFile = propsFileLocation;
		this.initDatabase();
		
	}
	
	private MongoClient initDatabase(){
		try{
			if(this.propsFile != null){
				mongoClient = new MongoClient( this.propsFile.getProperty("mongo.server"), 
						Integer.valueOf(this.propsFile.getProperty("mongo.port")));
				mongoDB = mongoClient.getDB(this.propsFile.getProperty("mongo.database"));
				cursor = mongoDB.getCollection(this.propsFile.getProperty("mongo.collection")).find();
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
	
	public List<DBObject> writeAppraisableToStorage(EmployeeSelectionStrategy selected){
		mongoDB.getCollection(this.propsFile.getProperty("mongo.appraised.collection")).remove(new BasicDBObject() );
		List<DBObject> appraised = new ArrayList<>();
		DBCursor dbCopy = cursor.copy();
	  appraised.addAll(selected.getAppraisableDBObjects(dbCopy)); 
	  mongoDB.getCollection(this.propsFile.getProperty("mongo.appraised.collection")).insert(appraised);
		return appraised; 
	}
	
	public DB getMongoDB(){
		return mongoDB;
	}
	
		@Override
  protected void finalize() throws Throwable {
    try{mongoClient.close();}
    catch(Throwable t){
      throw t;
    }		
	}
	
}
