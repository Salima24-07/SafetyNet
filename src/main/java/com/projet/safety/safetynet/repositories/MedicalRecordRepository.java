package com.projet.safety.safetynet.repositories;

import com.projet.safety.safetynet.domain.MedicalRecord;
import com.projet.safety.safetynet.exceptions.BadRequestException;

public interface MedicalRecordRepository {
	
	Boolean create(MedicalRecord medicalRecord) throws BadRequestException;
	
	Boolean update(MedicalRecord medicalRecord) throws BadRequestException;
	
	Boolean delete(String firstName, String lastName) throws BadRequestException;

}
