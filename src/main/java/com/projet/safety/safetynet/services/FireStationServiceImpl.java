package com.projet.safety.safetynet.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.safety.safetynet.domain.FireStation;
import com.projet.safety.safetynet.exceptions.BadRequestException;
import com.projet.safety.safetynet.repositories.FireStationRepository;

@Service
@Transactional
public class FireStationServiceImpl implements FireStationService{
	
	@Autowired
	FireStationRepository fireStationRepository;

	@Override
	public Map<String, String> createFireStation(String address, String station) throws BadRequestException {
		
		FireStation fireStation = new FireStation(address, station);
		
		Map<String, String> response = new HashMap<String, String>();
		
		if (fireStationRepository.create(fireStation)) {
			response.put("message", "firestation created successfully");
		} else {
			throw new BadRequestException("Failed to create firestation");
		}
		
		return response;
	}

	@Override
	public Map<String, String> updateFireStation(String address, String station) throws BadRequestException {
		
		FireStation fireStation = new FireStation(address, station);
		
		Map<String, String> response = new HashMap<String, String>();
		
		if (fireStationRepository.update(fireStation)) {
			response.put("message", "firestation updated successfully");
		} else {
			throw new BadRequestException("Failed to update firestation");
		}
		
		return response;
	}

	@Override
	public Map<String, String> deleteFireStation(String address) throws BadRequestException {
		
		Map<String, String> response = new HashMap<String, String>();
		
		if (fireStationRepository.delete(address)) {
			response.put("message", "firestation deleted successfully");
		} else {
			throw new BadRequestException("Failed to delete firestation");
		}
		
		return response;
	}

}
