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

import com.projet.safety.safetynet.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonResource {
	
	@Autowired
	PersonService personService;
	
	@PostMapping("")
	public ResponseEntity<Map<String, String>> registerPerson(@RequestBody Map<String, Object> personMap) {
		
		String firstName = (String) personMap.get("firstName");
		String lastName = (String) personMap.get("lastName");
		String adress = (String) personMap.get("address");
		String city = (String) personMap.get("city");
		String zip = (String) personMap.get("zip");
		String phone = (String) personMap.get("phone");
		String email= (String) personMap.get("email");
		
		Map<String, String> map = personService.createPerson(firstName, lastName, adress, city, zip, phone, email);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PutMapping("")
	ResponseEntity<Map<String, String>> updatePerson(@RequestBody Map<String, Object> personMap) {
		
		String firstName = (String) personMap.get("firstName");
		String lastName = (String) personMap.get("lastName");
		String adress = (String) personMap.get("address");
		String city = (String) personMap.get("city");
		String zip = (String) personMap.get("zip");
		String phone = (String) personMap.get("phone");
		String email= (String) personMap.get("email");
		
		Map<String, String> map = personService.updatePerson(firstName, lastName, adress, city, zip, phone, email);
		
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
