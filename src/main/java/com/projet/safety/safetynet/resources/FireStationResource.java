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

import com.projet.safety.safetynet.services.FireStationService;

@RestController
@RequestMapping("/firestation")
public class FireStationResource {
	
	@Autowired
	FireStationService fireStationService;
	
	@PostMapping("")
	public ResponseEntity<Map<String, String>> registerFireStation(@RequestBody Map<String, Object> stationMap) {
		
		String address = (String) stationMap.get("address");
		String station = (String) stationMap.get("station");
		
		Map<String, String> map = fireStationService.createFireStation(address, station);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PutMapping("")
	ResponseEntity<Map<String, String>> updateFireStation(@RequestBody Map<String, Object> stationMap) {
		
		String address = (String) stationMap.get("address");
		String station = (String) stationMap.get("station");
		
		Map<String, String> map = fireStationService.updateFireStation(address, station);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	} 
	
	@DeleteMapping("")
	ResponseEntity<Map<String, String>> deleteFireStation(@RequestBody Map<String, Object> stationMap) {
		
		String address = (String) stationMap.get("address");
		
		Map<String, String> map = fireStationService.deleteFireStation(address);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	} 

}
