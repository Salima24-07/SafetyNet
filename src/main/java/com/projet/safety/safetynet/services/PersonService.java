package com.projet.safety.safetynet.services;

import java.util.List;
import java.util.Map;

import com.projet.safety.safetynet.exceptions.BadRequestException;

public interface PersonService {
	
	Map<String, String> createPerson(String firstName, String lastName, String adress, String city, String zip, String phone,
			String email) throws BadRequestException;
	
	Map<String, String> updatePerson(String firstName, String lastName, String adress, String city, String zip, String phone,
			String email) throws BadRequestException;
	
	Map<String, String> deletePerson(String firstName, String lastName) throws BadRequestException;
	
	List<String> getEmails(String city);
	
	List<String> getPhoneNumbers(String station);
	
	List<Map<String, Object>> getPersonInfoByName(String firstName, String lastName) throws BadRequestException;
	
	List<Map<String, Object>> getPersonInfoByAddress(String address) throws BadRequestException;
	
	List<Map<String, Object>> getChildrenByAddress(String address) throws BadRequestException;
	
	Map<String, Object> getStationsInfo(String[] stations) throws BadRequestException;

}
