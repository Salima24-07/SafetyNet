package com.projet.safety.safetynet.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.safety.safetynet.domain.FireStation;
import com.projet.safety.safetynet.domain.Person;
import com.projet.safety.safetynet.exceptions.BadRequestException;
import com.projet.safety.safetynet.repositories.FireStationRepository;

@Service
@Transactional
public class FireStationServiceImpl implements FireStationService{
	
	@Autowired
	FireStationRepository fireStationRepository;

	@Override
	public Map<String, String> createFireStation(FireStation fireStation) throws BadRequestException {
		
		Optional<FireStation> existingFireStation = fireStationRepository.getByStationNumberAndAddress(
			fireStation.getStation(), fireStation.getAddress());
		
		if (existingFireStation.isPresent()) {
			throw new BadRequestException("A firestation with the same station number and address already exists");
		}
		
		fireStationRepository.save(fireStation);
		Map<String,String> resultMap =new HashMap<String,String>();
		resultMap.put("message", "FireStation created successfully");
		
		return resultMap;
	}

	@Override
	public Map<String, String> updateFireStation(String address, String station) throws BadRequestException {
		
		Optional<List<FireStation>> existingFireStation = fireStationRepository.getByAddress(address);
		
		if (existingFireStation.get().size() == 0) {
			throw new BadRequestException("No firestation with the provided informations");
		}
		
		existingFireStation.get().forEach(fireStation -> {
			fireStation.setStation(station);
			fireStationRepository.save(fireStation);
			});
		
		Map<String,String> resultMap =new HashMap<String,String>();
		resultMap.put("message", "FireStation updated successfully");
		
		return resultMap;
	}

	@Override
	public Map<String, String> deleteFireStation(String address, String station) throws BadRequestException {
		
		Optional<List<FireStation>> existingFireStation = fireStationRepository.getByAddressOrStation(address, station);
		
		if (existingFireStation.get().size() == 0) {
			throw new BadRequestException("No firestation with the provided informations");
		}

		existingFireStation.get().forEach(fireStation -> {
			fireStationRepository.delete(fireStation);
			});
		Map<String,String> resultMap =new HashMap<String,String>();
		resultMap.put("message", "FireStation deleted successfully");
		
		return resultMap;
	}

	@Override
	public List<String> getStationByAddress(String address) throws BadRequestException {
		
		return fireStationRepository.getStationByAddress(address);
	}

}
