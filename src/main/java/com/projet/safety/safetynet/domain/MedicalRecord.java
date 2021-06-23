package com.projet.safety.safetynet.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MedicalRecord {
	
	private String firstName;
	private String lastName;
	private Date birthdate;
	private String[] medications;
	private String[] allegries;
	
	public MedicalRecord(String firstName, String lastName, String birthdate, String[] medications, String[] allegries) throws ParseException {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
		this.medications = medications;
		this.allegries = allegries;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthDate(String birthdate) throws ParseException {
		this.birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
	}

	public String[] getMedications() {
		return medications;
	}

	public void setMerications(String[] medications) {
		this.medications = medications;
	}

	public String[] getAllegries() {
		return allegries;
	}

	public void setAllegries(String[] allegries) {
		this.allegries = allegries;
	}

}
