package com.projet.safety.safetynet.repositories;

import java.util.List;
import java.util.Map;

import com.projet.safety.safetynet.domain.Person;
import com.projet.safety.safetynet.exceptions.BadRequestException;

public interface PersonRepository {
	
	Map<String, String> create(Person person) throws BadRequestException;
	
	Boolean update(Person person) throws BadRequestException;
	
	Boolean delete(String firstName, String lastName) throws BadRequestException;
	
	List<String> getEmailsByCity(String city) throws BadRequestException;
	
	List<String> getPhoneNumbers(String station) throws BadRequestException;
	
	List<Map<String, Object>> getPersonInfoByName(String firstName, String lastName) throws BadRequestException;
	
	List<Map<String, Object>> getPersonInfoByAddress(String address) throws BadRequestException;
	
	List<Map<String, Object>> getChildrenByAddress(String address) throws BadRequestException;
	
	Map<String, Object> getStationInfo(String[] stations) throws BadRequestException;

}
