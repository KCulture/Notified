package com.github.KCulture.Notified.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.github.KCulture.Notified.Repository.EmailContactable;
import com.github.KCulture.Notified.Repository.Responder;
import com.github.KCulture.Notified.application.Notified;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDatabaseService implements DatabaseService {

	private static MongoClient mongoClient;
	private static DB mongoDB = null;
	private static DBCursor cursor = null;
	private final Properties propsFile;

	public MongoDatabaseService() {
		//this.propsFile = new Properties();
		this.propsFile = loadProps();
		this.initDatabase();

	}

	public MongoDatabaseService(Properties propsFileLocation) {
		this.propsFile = propsFileLocation;
		this.initDatabase();

	}

	private MongoClient initDatabase() {
		try {
			if (this.propsFile != null) {
				mongoClient = new MongoClient(
				    this.propsFile.getProperty("mongo.server"),
				    Integer.valueOf(this.propsFile.getProperty("mongo.port")));
				mongoDB = mongoClient.getDB(this.propsFile
				    .getProperty("mongo.database"));
				cursor = mongoDB.getCollection(
				    this.propsFile.getProperty("mongo.collection")).find();
			}
		} catch (UnknownHostException noHost) {
			noHost.addSuppressed(noHost);
			System.out.println(noHost.getMessage());
		}
		return mongoClient;
	}

	public List<EmailContactable> listOfAppraised(
	    EmployeeSelectionStrategy selected) {
		List<EmailContactable> employees = new ArrayList<>();
		DBCursor localCursor = cursor.copy();
		employees.addAll(selected.getAppraisableEmployees(localCursor));
		return employees;
	}

	public String findConactId(Responder responder) {
		DBCursor localCursor = cursor.copy();
		String id = "";
		for (DBObject mongoObject : localCursor) {
			if (mongoObject.get(responder.getFname()) != null) {
				id = ((String) mongoObject.get(responder.getFname()));
			}
		}
		return id;
	}

	public List<DBObject> writeAppraisableToStorage(
	    EmployeeSelectionStrategy selected) {
		mongoDB.getCollection(
		    this.propsFile.getProperty("mongo.appraised.collection")).remove(
		    new BasicDBObject());
		List<DBObject> appraised = new ArrayList<>();
		DBCursor dbCopy = cursor.copy();
		appraised.addAll(selected.getAppraisableDBObjects(dbCopy));
		mongoDB.getCollection(
		    this.propsFile.getProperty("mongo.appraised.collection")).insert(
		    appraised);
		return appraised;
	}

	public DB getMongoDB() {
		return mongoDB;
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			mongoClient.close();
		} catch (Throwable t) {
			throw t;
		}
	}
	
//This should be removed once client is connecting to Notifed entrypoint 
	//TODO: Take it out if can.
	private static Properties loadProps() {
		Properties properties = new Properties();
		try {
			properties.load(Notified.class.getResourceAsStream("config.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out
			    .println("redownload or rebuild because default file is corrupted");
		}
		return properties;
	}

}
