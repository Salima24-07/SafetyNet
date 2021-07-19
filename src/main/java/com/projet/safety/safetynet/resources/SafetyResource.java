package com.projet.safety.safetynet.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.safety.safetynet.services.FireStationService;
import com.projet.safety.safetynet.services.PersonService;

@RestController
@RequestMapping("")
public class SafetyResource {
	
	@Autowired
	PersonService personService;
	
	@Autowired
	FireStationService fireStationService;
	
	@GetMapping("/communityEmail")
	public ResponseEntity<List<String>> getEmails(HttpServletRequest request,
            @RequestParam("city") String city){
		
		List<String> response = personService.getEmails(city);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/phoneAlert")
	public ResponseEntity<List<String>> getPhoneNumbers(HttpServletRequest request,
            @RequestParam("firestation") String firestation){
		
		List<String> response = personService.getPhoneNumbers(firestation);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/personInfo")
	public ResponseEntity<Map<String, Object>> getPersonInfoByName(HttpServletRequest request,
            @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
		
		Map<String, Object> response = personService.getPersonInfoByName(firstName, lastName);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/fire")
	public ResponseEntity<Map<String, Object>> getPersonInfoByAddress(HttpServletRequest request,
            @RequestParam("address") String address){
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("stations", fireStationService.getStationByAddress(address)); 
		
		response.put("persons", personService.getPersonInfoByAddress(address));
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/childAlert")
	public ResponseEntity<List<Map<String, Object>>> getChildrenByAddress(HttpServletRequest request,
            @RequestParam("address") String address){
		
		List<Map<String, Object>> response = personService.getChildrenByAddress(address);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/flood/stations")
	public ResponseEntity<Map<String, Object>> getStationsInfo(HttpServletRequest request,
            @RequestParam("stations") String[] stations){
		
		Map<String, Object> response = personService.getStationsInfo(stations);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
