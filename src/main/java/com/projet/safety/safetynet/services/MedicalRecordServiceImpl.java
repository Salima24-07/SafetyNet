package com.projet.safety.safetynet.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

		Optional<MedicalRecord> existingMR = medicalRecordRepository.getMedicalRecordByName(medicalRecord.getFirstName(), medicalRecord.getLastName());
		
		if (existingMR.isPresent()) {
			throw new BadRequestException("A medical record for the same firstname and lastname already exists");
		}
		
		medicalRecordRepository.save(medicalRecord);
		Map<String,String> resultMap =new HashMap<String,String>();
		resultMap.put("message", "Medical record created successfully");
		
		return resultMap;
	}

	@Override
	public Map<String, String> updateMedicalRecord(MedicalRecord medicalRecord) throws BadRequestException {
		
		Optional<MedicalRecord> existingMR = medicalRecordRepository.getMedicalRecordByName(medicalRecord.getFirstName(), medicalRecord.getLastName());
		
		if (existingMR.isEmpty()) {
			throw new BadRequestException("No medical record with the provided informations");
		}

		medicalRecord.setId(existingMR.get().getId());
		
		medicalRecordRepository.save(medicalRecord);
		Map<String,String> resultMap =new HashMap<String,String>();
		resultMap.put("message", "Medical record updated successfully");
		
		return resultMap;
	}

	@Override
	public Map<String, String> deleteMedicalRecord(String firstName, String lastName) throws BadRequestException {
		
		Optional<MedicalRecord> existingMR = medicalRecordRepository.getMedicalRecordByName(firstName, lastName);
		
		if (existingMR.isEmpty()) {
			throw new BadRequestException("No medical record with the provided informations");
		}
		
		medicalRecordRepository.delete(existingMR.get());
		Map<String,String> resultMap =new HashMap<String,String>();
		resultMap.put("message", "Medical record deleted successfully");
		
		return resultMap;
	}

}
