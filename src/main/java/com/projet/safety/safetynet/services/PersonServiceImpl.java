package com.projet.safety.safetynet.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.safety.safetynet.domain.MedicalRecord;
import com.projet.safety.safetynet.domain.Person;
import com.projet.safety.safetynet.exceptions.BadRequestException;
import com.projet.safety.safetynet.repositories.MedicalRecordRepository;
import com.projet.safety.safetynet.repositories.PersonRepository;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	MedicalRecordRepository medicalRecordRepository;

	@Override
	public Map<String, String> createPerson(Person person) throws BadRequestException {
		
		Optional<Person> existingPerson = personRepository.getPersonByName(person.getFirstName(), person.getLastName());
		
		if (existingPerson.isPresent()) {
			throw new BadRequestException("A person with the same firstname and lastname already exists");
		}
		
		personRepository.save(person);
		Map<String,String> resultMap =new HashMap<String,String>();
		resultMap.put("message", "Person created successfully");
		
		return resultMap;
	}

	@Override
	public Map<String, String> updatePerson(Person person) throws BadRequestException {
		
		Optional<Person> existingPerson = personRepository.getPersonByName(person.getFirstName(), person.getLastName());
		
		if (existingPerson.isEmpty()) {
			throw new BadRequestException("No person with provided informations");
		}
		
		person.setId(existingPerson.get().getId());
		
		personRepository.save(person);
		Map<String,String> resultMap =new HashMap<String,String>();
		resultMap.put("message", "Person updated successfully");
		
		return resultMap;
	}

	@Override
	public Map<String, String> deletePerson(String firstName, String lastName) throws BadRequestException {
		
		Optional<Person> existingPerson = personRepository.getPersonByName(firstName, lastName);
		
		if (existingPerson.isEmpty()) {
			throw new BadRequestException("No person with provided informations");
		}
		personRepository.delete(existingPerson.get());
		Map<String,String> resultMap =new HashMap<String,String>();
		resultMap.put("message", "Person deleted successfully");
		
		return resultMap;
		
	}

	@Override
	public List<String> getEmails(String city) {
		
		return personRepository.getEmailsByCity(city);
	}

	@Override
	public List<String> getPhoneNumbers(String station) {
		
		return personRepository.getPhoneNumbers(station);
	}

	@Override
	public Map<String,Object> getPersonInfoByName(String firstName, String lastName) throws BadRequestException {
		
		Optional<Person> existingPerson = personRepository.getPersonByName(firstName, lastName);
		Optional<MedicalRecord> existingMR = medicalRecordRepository.getMedicalRecordByName(firstName, lastName);
		
		if (existingPerson.isEmpty() || existingMR.isEmpty()) {
			throw new BadRequestException("A person with the same firstname and lastname already exists");
		}
		
		Person person = existingPerson.get();
		MedicalRecord mr = existingMR.get();
		
		Map<String,Object> resultMap =new HashMap<String,Object>();
		
		resultMap.put("firstName", person.getFirstName());
		resultMap.put("lastName", person.getLastName());
		resultMap.put("address", person.getAddress());
		resultMap.put("email", person.getEmail());
		resultMap.put("medications", mr.getMedications());
		resultMap.put("allergies", mr.getAllergies());
		resultMap.put("age", Period.between(mr.getBirthdate(), LocalDate.now()).getYears());
		
		return resultMap;
	}
	
	@Override
	public List<Map<String, Object>> getPersonInfoByAddress(String address) throws BadRequestException {
		
		List<Person> persons = personRepository.getByAddress(address);
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		persons.forEach(person -> {
			Optional<MedicalRecord> existingMR = medicalRecordRepository.getMedicalRecordByName(person.getFirstName(), person.getLastName());
			
			MedicalRecord mr = existingMR.get();
			
			Map<String,Object> resultMap =new HashMap<String,Object>();
			
			resultMap.put("firstName", person.getFirstName());
			resultMap.put("lastName", person.getLastName());
			resultMap.put("phone", person.getPhone());
			resultMap.put("medications", mr.getMedications());
			resultMap.put("allergies", mr.getAllergies());
			resultMap.put("age", Period.between(mr.getBirthdate(), LocalDate.now()).getYears());
			
			result.add(resultMap);
		});
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> getChildrenByAddress(String address) throws BadRequestException {
		
		List<MedicalRecord> children = medicalRecordRepository.getChildrenByAddress(address);
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		children.forEach(child -> {
			List<Person> persons = personRepository.getChildCompany(address, child.getFirstName(), child.getLastName());
			
			Map<String,Object> resultMap =new HashMap<String,Object>();
			
			resultMap.put("firstName", child.getFirstName());
			resultMap.put("lastName", child.getLastName());
			resultMap.put("age", Period.between(child.getBirthdate(), LocalDate.now()).getYears());
			resultMap.put("company", persons);
			
			result.add(resultMap);
		});
		
		return result;
	}

	@Override
	public Map<String, Object> getStationsInfo(String[] stations) throws BadRequestException {
		
		return null; //personRepository.getStationInfo(stations);
	}

}
