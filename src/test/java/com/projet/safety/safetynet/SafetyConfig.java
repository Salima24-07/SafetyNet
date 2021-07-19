package com.projet.safety.safetynet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.projet.safety.safetynet.domain.FireStation;
import com.projet.safety.safetynet.domain.MedicalRecord;
import com.projet.safety.safetynet.domain.Person;
import com.projet.safety.safetynet.repositories.FireStationRepository;
import com.projet.safety.safetynet.repositories.MedicalRecordRepository;
import com.projet.safety.safetynet.repositories.PersonRepository;

@Configuration
public class SafetyConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(
			PersonRepository personRepo,
			FireStationRepository fsRepo,
			MedicalRecordRepository mrRepo) {
		
		return args -> {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			Person person1 = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
			Person person2 = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com");
			Person person3 = new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com");
			Person person4 = new Person("Roger", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
			Person person5 = new Person("Felicia", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6544", "jaboyd@email.com");

			FireStation fs1 = new FireStation("1509 Culver St","3");
			
			MedicalRecord mr1 = new MedicalRecord("John", "Boyd", LocalDate.parse("1984-03-06", formatter), 
				new ArrayList<String>(Arrays. asList("aznol:350mg", "hydrapermazol:100mg")), 
				new ArrayList<String>(Arrays. asList("nillacilan")));
			MedicalRecord mr2 = new MedicalRecord("Jacob", "Boyd", LocalDate.parse("1989-03-06", formatter),
				new ArrayList<String>(Arrays. asList("pharmacol:5000mg", "terazine:10mg")),
				new ArrayList<String>(Arrays. asList("noznazol:250mg")));
			MedicalRecord mr3 = new MedicalRecord("Tenley", "Boyd", LocalDate.parse("2012-02-18", formatter),
				new ArrayList<String>(Arrays. asList()),
				new ArrayList<String>(Arrays. asList("peanut")));
			MedicalRecord mr4 = new MedicalRecord("Roger", "Boyd", LocalDate.parse("2017-09-06", formatter),
				new ArrayList<String>(Arrays. asList()),
				new ArrayList<String>(Arrays. asList()));
			MedicalRecord mr5 = new MedicalRecord("Felicia", "Boyd",LocalDate.parse("1986-01-08", formatter),
				new ArrayList<String>(Arrays. asList("tetracyclaz:650mg")),
				new ArrayList<String>(Arrays. asList("xilliathal")));
			
			personRepo.saveAll(List.of(person1, person2, person3, person4, person5));
			fsRepo.save(fs1);
			mrRepo.saveAll(List.of(mr1, mr2, mr4, mr3, mr5));
		};
		
	}

}
