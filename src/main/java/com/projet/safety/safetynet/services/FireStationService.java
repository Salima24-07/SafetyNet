package com.projet.safety.safetynet.services;

import java.util.Map;

import com.projet.safety.safetynet.exceptions.BadRequestException;

public interface FireStationService {
	
	Map<String, String> createFireStation(String address, String station) throws BadRequestException;
	
	Map<String, String> updateFireStation(String address, String station) throws BadRequestException;
	
	Map<String, String> deleteFireStation(String address) throws BadRequestException;

}
