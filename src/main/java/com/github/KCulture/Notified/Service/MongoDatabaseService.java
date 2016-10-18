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
	
	public MongoDatabaseService(Properties propsFileLocation){
		this.propsFile = propsFileLocation;
		this.initDatabase();
		
	}
	
	private MongoClient initDatabase(){
		try{
			//TODO add property values in constructor
			if(this.propsFile != null){
				mongoClient = new MongoClient( this.propsFile.getProperty("mongo.server","localhost"), 
						Integer.valueOf(this.propsFile.getProperty("mongo.port",String.valueOf(27017))));
				mongoDB = mongoClient.getDB(this.propsFile.getProperty("mongo.database",DATABASE));
				cursor = mongoDB.getCollection(this.propsFile.getProperty("mongo.collection",COLLECTION)).find();
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
	
		@Override
    protected void finalize() throws Throwable {
        try{mongoClient.close();}
        catch(Throwable t){
          throw t;
        }		
	}
	
}
