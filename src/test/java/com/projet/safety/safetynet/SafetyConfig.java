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

			Person person1 = new Person("test_create_ko", "test_create_ko", "1509 Culver St", "Culver", "97451", "841-874-6544", "jaboyd@email.com");
			Person person2 = new Person("test_update_ok", "test_update_ok", "1509 Culver St", "Culver", "97451", "841-874-6544", "jaboyd@email.com");
			Person person3 = new Person("test_delete_ok", "test_delete_ok", "1509 Culver St", "Culver", "97451", "841-874-6544", "jaboyd@email.com");

			FireStation fs1 = new FireStation("test_delete_ok","12");
			FireStation fs2 = new FireStation("test_create_ko","10");
			FireStation fs3 = new FireStation("test_update_ok","11");
			
			MedicalRecord mr1 = new MedicalRecord("test_create_ko", "test_create_ko",LocalDate.parse("1986-01-08", formatter),
				new ArrayList<String>(Arrays.asList("tetracyclaz:650mg")),
				new ArrayList<String>(Arrays.asList("xilliathal")));
			MedicalRecord mr2 = new MedicalRecord("test_update_ok", "test_update_ok",LocalDate.parse("1986-01-08", formatter),
				new ArrayList<String>(Arrays.asList("tetracyclaz:650mg")),
				new ArrayList<String>(Arrays.asList("xilliathal")));
			MedicalRecord mr3 = new MedicalRecord("test_delete_ok", "test_delete_ok",LocalDate.parse("1986-01-08", formatter),
				new ArrayList<String>(Arrays.asList("tetracyclaz:650mg")),
				new ArrayList<String>(Arrays.asList("xilliathal")));
			
			personRepo.saveAll(List.of(person1, person2, person3));
			fsRepo.saveAll(List.of(fs1, fs2, fs3));
			mrRepo.saveAll(List.of(mr1, mr2, mr3));
		};
		
	}

}
