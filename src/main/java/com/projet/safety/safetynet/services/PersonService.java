package com.projet.safety.safetynet.services;

import java.util.Map;

import com.projet.safety.safetynet.exceptions.BadRequestException;

public interface PersonService {
	
	Map<String, String> createPerson(String firstName, String lastName, String adress, String city, String zip, String phone,
			String email) throws BadRequestException;
	
	Map<String, String> updatePerson(String firstName, String lastName, String adress, String city, String zip, String phone,
			String email) throws BadRequestException;
	
	Map<String, String> deletePerson(String firstName, String lastName) throws BadRequestException;

}
