package com.github.KCulture.Notified.application;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import com.github.KCulture.Notified.Repository.Employee;
import com.github.KCulture.Notified.Service.CmdTrigger;
import com.github.KCulture.Notified.Service.Commando;
import com.github.KCulture.Notified.Service.DataStore;
import com.github.KCulture.Notified.Service.MailCommando;
import com.github.KCulture.Notified.Service.SimpleDataStore;


public class Notified {
  //TODO allow property files to be supplied through CLI
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties propFile = loadProps(args[0]);
		DataStore simpleStore= new SimpleDataStore(propFile);
		List<Employee> employees = simpleStore.getEmployees();
		simpleStore.writeAppraisableEmployeesToStorage();
		Commando command = new MailCommando(employees,propFile);
		CmdTrigger cmdTrigger = new CmdTrigger();
		cmdTrigger.triggerCommand(command);
	}
	
	// Produces property file from fileName or default property file stored in jar 
	private static Properties loadProps(String fileName){
		Properties properties = new Properties();
		if(fileName != null ){
			try (InputStream in = new FileInputStream(fileName)){
				properties.load(in);
			} catch (IOException e) {
				System.out.println("file provide caused an error attempting to use default values");
				return loadProps();
			}
		}
		properties = loadProps();
		return properties;
	}
	
	private static Properties loadProps(){
		Properties properties = new Properties();
		try {
			properties.load(Notified.class.getResourceAsStream("config.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("redownload or rebuild because default file is corrupted");
		}
		return properties;
	}

}
