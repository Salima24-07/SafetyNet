package com.projet.safety.safetynet.domain;

import java.text.ParseException;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="medicalrecord")
public class MedicalRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int Id;

	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;

	@Column(name="birthdate")
	private Date birthdate;

	@Column(name="medications")
	private String[] medications;

	@Column(name="allergies")
	private String[] allergies;
	
	public MedicalRecord(String firstName, String lastName, String birthdate, String[] medications, String[] allergies) throws ParseException {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
		this.medications = medications;
		this.allergies = allergies;
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

	public String getBirthdate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(birthdate);
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

	public String[] getAllergies() {
		return allergies;
	}

	public void setAllergies(String[] allergies) {
		this.allergies = allergies;
	}

}
