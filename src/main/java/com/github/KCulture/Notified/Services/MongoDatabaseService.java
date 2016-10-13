package com.github.KCulture.Notified.Services;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import com.mongodb.MongoClient;

import com.github.KCulture.Notified.Repository.Employee;

public class MongoDatabaseService implements DatabaseService {

	@Override
	public int connect() {
		int signal = 0;
		try{
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			signal = mongoClient.hashCode();
			// TODO: Build an adapter to encapsulate client 
		}catch(UnknownHostException noHost){
			signal = -1;
		}
		return signal;
	}

	public List<Employee> listOfAppraised(int quarter){
		return null; // TODO Stub needs to be replaced with implementation 
	}

}
