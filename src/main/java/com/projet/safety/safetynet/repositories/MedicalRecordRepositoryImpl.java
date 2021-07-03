package com.projet.safety.safetynet.repositories;

import java.sql.Array;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.projet.safety.safetynet.domain.MedicalRecord;
import com.projet.safety.safetynet.exceptions.BadRequestException;

@Repository
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository{
	
	private static final String SQL_CREATE = "INSERT INTO MEDICALRECORD(FIRST_NAME, LAST_NAME, BIRTHDATE, "
			+"MEDICATIONS, ALLERGIES) VALUES (?, ?, ?, ?, ?)";
	
	private static final String SQL_UPDATE = "UPDATE MEDICALRECORD SET BIRTHDATE = ?, MEDICATIONS = ?,"
			+"ALLERGIES = ? WHERE FIRST_NAME = ? AND LAST_NAME = ?";
	
	private static final String SQL_DELETE = "DELETE FROM MEDICALRECORD WHERE FIRST_NAME = ? AND LAST_NAME = ?";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Boolean create(MedicalRecord medicalRecord) throws BadRequestException {
		try {
			
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.
						prepareStatement(SQL_CREATE);
				Array medicationsArray = connection.createArrayOf("VARCHAR", medicalRecord.getMedications());
				Array allergiesArray = connection.createArrayOf("VARCHAR", medicalRecord.getAllergies());
				ps.setString(1, medicalRecord.getFirstName());
				ps.setString(2, medicalRecord.getLastName());
				try {
					ps.setDate(3, new Date(new SimpleDateFormat("dd/MM/yyyy").parse(medicalRecord.getBirthdate()).getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				ps.setArray(4, medicationsArray);
				ps.setArray(5, allergiesArray);
				return ps;
			});
			
			return true;
			
		} catch (Exception e) {
			throw new BadRequestException("Invalid details. Failed to create medicalrecord");
		}
	}

	@Override
	public Boolean update(MedicalRecord medicalRecord) throws BadRequestException {
		try {
			
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.
						prepareStatement(SQL_UPDATE);
				Array medicationsArray = connection.createArrayOf("VARCHAR", medicalRecord.getMedications());
				Array allergiesArray = connection.createArrayOf("VARCHAR", medicalRecord.getAllergies());
				try {
					ps.setDate(1, new Date(new SimpleDateFormat("dd/MM/yyyy").parse(medicalRecord.getBirthdate()).getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				ps.setArray(2, medicationsArray);
				ps.setArray(3, allergiesArray);
				ps.setString(4, medicalRecord.getFirstName());
				ps.setString(5, medicalRecord.getLastName());
				return ps;
			});
			
			return true;
			
		} catch (Exception e) {
			System.err.println(e);
			throw new BadRequestException("Invalid details. Failed to update medicalrecord");
		}
	}

	@Override
	public Boolean delete(String firstName, String lastName) throws BadRequestException {
		try {
			
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.
						prepareStatement(SQL_DELETE);
				ps.setString(1, firstName);
				ps.setString(2, lastName);
				return ps;
			});
			
			return true;
			
		} catch (Exception e) {
			throw new BadRequestException("Invalid details. Failed to update medicalrecord");
		}
	}

}
