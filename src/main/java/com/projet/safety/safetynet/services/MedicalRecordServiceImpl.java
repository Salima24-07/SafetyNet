package com.projet.safety.safetynet.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.safety.safetynet.domain.MedicalRecord;
import com.projet.safety.safetynet.exceptions.BadRequestException;
import com.projet.safety.safetynet.repositories.MedicalRecordRepository;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService{
	
	@Autowired
	MedicalRecordRepository medicalRecordRepository;

	@Override
	public Map<String, String> createMedicalRecord(MedicalRecord medicalRecord) throws BadRequestException {
		
		Map<String, String> response = new HashMap<String, String>();
		
		if (medicalRecordRepository.create(medicalRecord)) {
			response.put("message", "medicalrecord created successfully");
		} else {
			throw new BadRequestException("Failed to create firestation");
		}
		
		return response;
	}

	@Override
	public Map<String, String> updateMedicalRecord(MedicalRecord medicalRecord) throws BadRequestException {
		
		Map<String, String> response = new HashMap<String, String>();
		
		if (medicalRecordRepository.update(medicalRecord)) {
			response.put("message", "medicalrecord updated successfully");
		} else {
			throw new BadRequestException("Failed to update firestation");
		}
		
		return response;
	}

	@Override
	public Map<String, String> deleteMedicalRecord(String firstName, String lastName) throws BadRequestException {
		
		Map<String, String> response = new HashMap<String, String>();
		
		if (medicalRecordRepository.delete(firstName, lastName)) {
			response.put("message", "medicalrecord deleted successfully");
		} else {
			throw new BadRequestException("Failed to deleted firestation");
		}
		
		return response;
	}

}
