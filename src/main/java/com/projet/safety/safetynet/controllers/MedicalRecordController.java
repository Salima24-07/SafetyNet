package com.projet.safety.safetynet.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.safety.safetynet.domain.MedicalRecord;
import com.projet.safety.safetynet.exceptions.BadRequestException;
import com.projet.safety.safetynet.services.MedicalRecordService;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordResource {
	
	@Autowired
	MedicalRecordService medicalRecordService;
	
	@PostMapping("")
	public ResponseEntity<Map<String, String>> registerMedicalRecord(@RequestBody Map<String, Object> recordMap) {
		
		MedicalRecord medicalRecord = new MedicalRecord();
		
		medicalRecord.setFirstName((String) recordMap.get("firstName"));
		medicalRecord.setLastName((String) recordMap.get("lastName"));
		medicalRecord.setMedications((ArrayList<String>) recordMap.get("medications"));
		medicalRecord.setAllergies((ArrayList<String>) recordMap.get("allergies"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		try {
			medicalRecord.setBirthDate(LocalDate.parse((String) recordMap.get("birthdate"), formatter));
		} catch (ParseException e) {
			throw new BadRequestException("Birthdate format is not correct");
		}
		
		Map<String, String> map = medicalRecordService.createMedicalRecord(medicalRecord);
		
		
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}
	
	@PutMapping("")
	public ResponseEntity<Map<String, String>> updateMedicalRecord(@RequestBody Map<String, Object> recordMap) {
		
		MedicalRecord medicalRecord = new MedicalRecord();
		
		medicalRecord.setFirstName((String) recordMap.get("firstName"));
		medicalRecord.setLastName((String) recordMap.get("lastName"));
		medicalRecord.setMedications((ArrayList<String>) recordMap.get("medications"));
		medicalRecord.setAllergies((ArrayList<String>) recordMap.get("allergies"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
			medicalRecord.setBirthDate(LocalDate.parse((String) recordMap.get("birthdate"), formatter));
		} catch (ParseException e) {
			throw new BadRequestException("Birthdate format is not correct");
		}
		
		Map<String, String> map = medicalRecordService.updateMedicalRecord(medicalRecord);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@DeleteMapping("")
	public ResponseEntity<Map<String, String>> deleteMedicalRecord(@RequestBody Map<String, Object> recordMap) {
		
		String firstName = (String) recordMap.get("firstName");
		String lastName = (String) recordMap.get("lastName");
		
		Map<String, String> map = medicalRecordService.deleteMedicalRecord(firstName, lastName);
		
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
