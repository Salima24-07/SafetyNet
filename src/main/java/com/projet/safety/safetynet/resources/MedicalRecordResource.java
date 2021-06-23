package com.projet.safety.safetynet.resources;

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
import com.projet.safety.safetynet.services.MedicalRecordService;

@RestController
@RequestMapping("/medicalrecord")
public class MedicalRecordResource {
	
	@Autowired
	MedicalRecordService medicalRecordService;
	
	@PostMapping("")
	public ResponseEntity<Map<String, String>> registerMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		
		
		Map<String, String> map = medicalRecordService.createMedicalRecord(medicalRecord);
		
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<Map<String, String>> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		
		
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
