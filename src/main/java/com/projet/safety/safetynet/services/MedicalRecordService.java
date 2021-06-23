package com.projet.safety.safetynet.services;

import java.util.Date;
import java.util.Map;

import com.projet.safety.safetynet.domain.MedicalRecord;
import com.projet.safety.safetynet.exceptions.BadRequestException;

public interface MedicalRecordService {
	
	Map<String, String> createMedicalRecord(MedicalRecord medicalRecord) throws BadRequestException;
	
	Map<String, String> updateMedicalRecord(MedicalRecord medicalRecord) throws BadRequestException;
	
	Map<String, String> deleteMedicalRecord(String firstName, String lastName) throws BadRequestException;

}
