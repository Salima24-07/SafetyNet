package com.projet.safety.safetynet.resources;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.safety.safetynet.domain.FireStation;
import com.projet.safety.safetynet.services.FireStationService;

@RestController
@RequestMapping("/firestation")
public class FireStationResource {
	
	private static final Logger logger = LogManager.getLogger("SafetyNet");
	
	@Autowired
	FireStationService fireStationService;
	
	@PostMapping("")
	public ResponseEntity<Map<String, String>> registerFireStation(@RequestBody FireStation fireStation) {
		
		Map<String, String> map = fireStationService.createFireStation(fireStation);
		
		return new ResponseEntity<>(map, HttpStatus.CREATED);
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
	
	@GetMapping("")
	ResponseEntity<Map<String, Object>> getByFireStation(HttpServletRequest request,
            @RequestParam("stationNumber") String station) {
		logger.info(request.getRequestURI());
		Map<String, Object> response_body = fireStationService.getPersonsByStation(station);
		ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(response_body, HttpStatus.OK);
		logger.info(response);
		return response;
	}

}
