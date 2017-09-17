package com.github.KCulture.Notified.application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.github.KCulture.Notified.Repository.EmailContactable;
import com.github.KCulture.Notified.Service.CmdTrigger;
import com.github.KCulture.Notified.Service.Commando;
import com.github.KCulture.Notified.Service.DataStore;
import com.github.KCulture.Notified.Service.MailCommando;
import com.github.KCulture.Notified.Service.SimpleDataStore;

public class Notified {

	private final static Options options = new Options();
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CommandLineParser parser = new DefaultParser();
		try{
			final CommandLine line = parser.parse(options,args);
		}catch(ParseException exp){
			//TODO add logging
			System.err.println("Parsing failed. Reason:"+ exp.getMessage());
		}
		// line.hasOption("optionName") states if this option was passed in
		// line.getOptionValue("optionName") gets the value
		// Create boolean options ex .Option help = new Option( "help", "print this message" );
		
		Properties propFile = null;
		if (args != null && args.length >= 1) {
			propFile = loadProps(args[0]);
		}
		if (args == null || args.length == 0) {
			propFile = loadProps();
		}

		DataStore simpleStore = new SimpleDataStore(propFile);
		List<EmailContactable> employees = simpleStore.getEmployees();
		simpleStore.writeAppraisableEmployeesToStorage();
		Commando command = new MailCommando(employees, propFile);
		CmdTrigger cmdTrigger = new CmdTrigger();
		cmdTrigger.triggerCommand(command);

	}
	
	private static Options makeOptions(){
		
		return null;
	}

	// Produces property file from fileName or default property file stored in jar
	private static Properties loadProps(String fileName) {
		Properties properties = new Properties();
		if (fileName != null) {
			try (InputStream in = new FileInputStream(fileName)) {
				properties.load(in);
			} catch (IOException e) {
				System.out
				    .println("file provide caused an error attempting to use default values");
				return loadProps();
			}
		}
		properties = loadProps();
		return properties;
	}

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
