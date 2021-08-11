package com.projet.safety.safetynet.resources;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.safety.safetynet.domain.FireStation;
import com.projet.safety.safetynet.domain.MedicalRecord;
import com.projet.safety.safetynet.domain.Person;
import com.projet.safety.safetynet.exceptions.BadRequestException;
import com.projet.safety.safetynet.repositories.FireStationRepository;
import com.projet.safety.safetynet.repositories.MedicalRecordRepository;
import com.projet.safety.safetynet.repositories.PersonRepository;

@RestController
@RequestMapping("/init")
public class InitData {
	
	@Autowired
	PersonRepository personRepo;
	
	@Autowired
	FireStationRepository fsRepo;
	
	@Autowired
	MedicalRecordRepository mrRepo;
	
	@GetMapping("")
	public Map<String, List<Map<String, Object>>> init() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<Map<String, List<Map<String, Object>>>> typeReference = new TypeReference<Map<String, List<Map<String, Object>>>>(){};
		InputStream inputStream = new ClassPathResource(
			      "data.json").getInputStream();
		try {
			Map<String, List<Map<String, Object>>> data = mapper.readValue(inputStream,typeReference);
			List<Map<String, Object>> persons = data.get("persons");
			persons.forEach(person -> {
				Person person_to_create = new Person(
						(String) person.get("firstName"), (String) person.get("lastName"), (String) person.get("address"), (String) person.get("city"),
						(String) person.get("zip"), (String) person.get("phone"), (String) person.get("email"));
				
				try {
					personRepo.save(person_to_create);
				} catch (Exception e) {
					System.err.println("error while creating person " + (String) person.get("firstName") + " " + (String) person.get("lastName"));
				}
			});
			
			List<Map<String, Object>> stations = data.get("firestations");
			stations.forEach(station -> {
				FireStation station_to_create = new FireStation((String) station.get("address"), (String) station.get("station"));
				
				try {
					fsRepo.save(station_to_create);
				} catch (Exception e) {
					System.err.println("error while creating firestation " + (String) station.get("address") + " " + (String) station.get("station"));
				}
				
			});
			
			List<Map<String, Object>> medicalrecords = data.get("medicalrecords");
			medicalrecords.forEach(medicalrecord -> {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				try {
					MedicalRecord mr_to_create = new MedicalRecord((String) medicalrecord.get("firstName"), (String) medicalrecord.get("lastName"), 
							LocalDate.parse((String) medicalrecord.get("birthdate"), formatter),
							(ArrayList<String>) medicalrecord.get("medications"), (ArrayList<String>) medicalrecord.get("allergies"));
					mrRepo.save(mr_to_create);
				} catch (ParseException e) {
					throw new BadRequestException("Birthdate format is not correct");
				} catch (Exception e) {
					System.err.println("error while creating medicalRecord for person " + (String) medicalrecord.get("firstName") + " " + (String) medicalrecord.get("lastName"));
				}
			});
			
			return data;
		} catch (IOException e){
			System.out.println(e);
			return null;
		}
		
	}

}
