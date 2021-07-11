package com.projet.safety.safetynet.resources;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.safety.safetynet.domain.Person;
import com.projet.safety.safetynet.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonResource {
	
	@Autowired
	PersonService personService;
	
	@PostMapping("")
	public ResponseEntity<Map<String, String>> registerPerson(@RequestBody Person person) {
		
		Map<String, String> map = personService.createPerson(person);
		
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}
	
	@PutMapping("")
	ResponseEntity<Map<String, String>> updatePerson(@RequestBody Person person) {
		
		Map<String, String> map = personService.updatePerson(person);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	} 
	
	@DeleteMapping("")
	ResponseEntity<Map<String, String>> deletePerson(@RequestBody Map<String, Object> personMap) {
		
		String firstName = (String) personMap.get("firstName");
		String lastName = (String) personMap.get("lastName");
		
		Map<String, String> map = personService.deletePerson(firstName, lastName);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	} 
}
