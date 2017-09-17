package com.github.KCulture.Notified.Repository;

import java.util.List;

import com.mongodb.BasicDBObject;

public class Responder extends BasicDBObject implements EmailContactable {
	private static final long serialVersionUID = 1L;

	public Responder() {
	}

	public Responder(String fname, String lname, String email,
	    List<String> questions, List<String> answers) {
		super.put("fname", fname);
		super.put("email", email);
		super.put("lname", lname);
		super.put("questions", questions);
		super.put("answers", answers);

	}

	public String getFname() {
		return super.getString("fname");
	}

	public void setFname(String fname) {
		super.put("fname", fname);
	}

	public String getEmail() {
		return super.getString("email");
	}

	public void setEmail(String email) {
		super.put("email", email);
	}

}
