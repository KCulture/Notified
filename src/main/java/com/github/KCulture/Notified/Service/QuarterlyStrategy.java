package com.github.KCulture.Notified.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.github.KCulture.Notified.Repository.Employee;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class QuarterlyStrategy implements EmployeeSelectionStrategy {
	
	final private Calendar currentDate ;
	final private Date marchDate ;
	final private Date juneDate  ;
	final private Date septemberDate ;
	final private Date decemberDate ;
	final private int quater;

	public QuarterlyStrategy() {
		this.currentDate = Calendar.getInstance();
		this.marchDate = this.getMarch();
		this.juneDate  =  this.getJune();
		this.septemberDate = this.getSeptember();
		this.decemberDate  = this.getDecember();
		this.quater = findRightQuarter(this.currentDate);
	}

	
//TODO think of way to generalize 
	public List<? extends DBObject> getAppraisableDBObjects(DBCursor cursor) {
		List<DBObject> appraised = new ArrayList<>();
		for(DBObject mongoObject : cursor){
			Date date = (Date)mongoObject.get("hireDate");
        if(isAppraisalDate(date)){
       	 appraised.add(mongoObject);
        }
    }
		return appraised;
  }
	
	public List<? extends Employee> getAppraisableEmployees(DBCursor cursor) {
		List<Employee> employees = new ArrayList<>();
		for(DBObject mongoObject : cursor){
			Date date = (Date)mongoObject.get("hireDate");
        if(isAppraisalDate(date)){
       	 employees.add(this.toEmployee(mongoObject));
        }
    }
		return employees;
  }
	
	public boolean isAppraisalDate(Date date){
		return findRightQuarter(date) == this.quater;
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
		Calendar cal = generalCalendar(Calendar.MARCH);
		return cal.getTime();
	}
	private Date getJune(){
		Calendar cal = generalCalendar(Calendar.JUNE);
		return cal.getTime();
	}
	
	private Date getSeptember(){
		Calendar cal = generalCalendar(Calendar.SEPTEMBER);
		return cal.getTime();
	}
	
	private Date getDecember(){
		Calendar cal = generalCalendar(Calendar.DECEMBER);
		return cal.getTime();
	}
	
	private Calendar generalCalendar(int month){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, this.currentDate.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR, 11);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal;
	}
	private Employee toEmployee(DBObject mongoObj){
		
		return new Employee((String)mongoObj.get("_id"),((String)mongoObj.get("firstName")),((String)mongoObj.get("lastName")) ,
				((Date) mongoObj.get("hireDate")),((String)mongoObj.get("email")));
	}

}
