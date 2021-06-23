package com.projet.safety.safetynet.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.safety.safetynet.domain.Person;
import com.projet.safety.safetynet.exceptions.BadRequestException;
import com.projet.safety.safetynet.repositories.PersonRepository;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	PersonRepository personRepository;

	@Override
	public Map<String, String> createPerson(String firstName, String lastName, String adress, String city, String zip,
			String phone, String email) throws BadRequestException {
		
		Person person = new Person(firstName, lastName, adress, city, zip, phone, email);
		
		return personRepository.create(person);
	}

	@Override
	public Map<String, String> updatePerson(String firstName, String lastName, String adress, String city, String zip,
			String phone, String email) throws BadRequestException {
		
		Person person = new Person(firstName, lastName, adress, city, zip, phone, email);
		
		Map<String, String> response = new HashMap<String, String>();
		
		if (personRepository.update(person)) {
			response.put("message", "Person updated successfully");
		} else {
			throw new BadRequestException("Failed to update person");
		};
		
		return response;
	}

	@Override
	public Map<String, String> deletePerson(String firstName, String lastName) throws BadRequestException {
		
		Map<String, String> response = new HashMap<String, String>();
		
		if (personRepository.delete(firstName, lastName)) {
			response.put("message", "Person deleted successfully");
		} else {
			throw new BadRequestException("Failed to delete person");
		};
		
		return response;
		
	}

}
