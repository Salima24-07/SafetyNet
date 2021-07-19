package com.projet.safety.safetynet.domain;

import java.text.ParseException;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(
	name="medicalrecord",
	uniqueConstraints = {
		@UniqueConstraint(name = "medical_record_unique", columnNames = {"first_name", "last_name"})
	}
	)
public class MedicalRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long Id;

	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;

	@Column(name="birthdate")
	private LocalDate birthdate;

	@Column(name="medications")
	private ArrayList<String> medications;

	@Column(name="allergies")
	private ArrayList<String> allergies;

	public MedicalRecord() {}
	
	public MedicalRecord(Long id, String firstName, String lastName, LocalDate birthdate, ArrayList<String> medications, ArrayList<String> allergies) throws ParseException {
		this.Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}
	
	public MedicalRecord(String firstName, String lastName, LocalDate birthdate, ArrayList<String> medications, ArrayList<String> allergies) throws ParseException {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

	public Long getId() {
		return Id;
	}
	
	public void setId(Long id) {
		this.Id = id;
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

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthDate(LocalDate birthdate) throws ParseException {
		this.birthdate = birthdate;
	}

	public ArrayList<String> getMedications() {
		return medications;
	}

	public void setMedications(ArrayList<String> medications) {
		this.medications = medications;
	}

	public ArrayList<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(ArrayList<String> allergies) {
		this.allergies = allergies;
	}

}
