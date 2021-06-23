package com.projet.safety.safetynet.repositories;

import java.util.Map;

import com.projet.safety.safetynet.domain.Person;
import com.projet.safety.safetynet.exceptions.BadRequestException;

public interface PersonRepository {
	
	Map<String, String> create(Person person) throws BadRequestException;
	
	Boolean update(Person person) throws BadRequestException;
	
	Boolean delete(String firstName, String lastName) throws BadRequestException;

}
