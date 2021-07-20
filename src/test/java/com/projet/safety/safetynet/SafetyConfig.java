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

			Person person6 = new Person("test_create_ko", "test_create_ko", "1509 Culver St", "Culver", "97451", "841-874-6544", "jaboyd@email.com");
			Person person7 = new Person("test_update_ok", "test_update_ok", "1509 Culver St", "Culver", "97451", "841-874-6544", "jaboyd@email.com");
			Person person8 = new Person("test_delete_ok", "test_delete_ok", "1509 Culver St", "Culver", "97451", "841-874-6544", "jaboyd@email.com");

			FireStation fs1 = new FireStation("1509 Culver St","3");
			FireStation fs2 = new FireStation("test_create_ko","10");
			FireStation fs3 = new FireStation("test_update_ok","11");
			FireStation fs4 = new FireStation("test_delete_ok","12");
			
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

			MedicalRecord mr6 = new MedicalRecord("test_create_ko", "test_create_ko",LocalDate.parse("1986-01-08", formatter),
				new ArrayList<String>(Arrays. asList("tetracyclaz:650mg")),
				new ArrayList<String>(Arrays. asList("xilliathal")));
			MedicalRecord mr7 = new MedicalRecord("test_update_ok", "test_update_ok",LocalDate.parse("1986-01-08", formatter),
				new ArrayList<String>(Arrays. asList("tetracyclaz:650mg")),
				new ArrayList<String>(Arrays. asList("xilliathal")));
			MedicalRecord mr8 = new MedicalRecord("test_delete_ok", "test_delete_ok",LocalDate.parse("1986-01-08", formatter),
				new ArrayList<String>(Arrays. asList("tetracyclaz:650mg")),
				new ArrayList<String>(Arrays. asList("xilliathal")));
			
			personRepo.saveAll(List.of(person1, person2, person3, person4, person5, person6, person7, person8));
			fsRepo.saveAll(List.of(fs1, fs2, fs3, fs4));
			mrRepo.saveAll(List.of(mr1, mr2, mr4, mr3, mr5, mr6, mr7, mr8));
		};
		
	}

}
