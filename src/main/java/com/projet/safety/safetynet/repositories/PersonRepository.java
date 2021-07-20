package com.projet.safety.safetynet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.safety.safetynet.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	
	@Query("SELECT p FROM Person p WHERE firstName = ?1 and lastName = ?2")
	Optional<Person> getPersonByName(String firstName, String lastName);
	
	@Query("SELECT p.email FROM Person p WHERE city = ?1")
	List<String> getEmailsByCity(String city);
	
	@Query(
			  value = "SELECT DISTINCT PHONE FROM PERSON P "
			  		+ "INNER JOIN FIRESTATION FS ON FS.ADDRESS = P.ADDRESS WHERE STATION = ?1", 
			  nativeQuery = true)
	List<String> getPhoneNumbers(String station);
	
	//@Query("SELECT p FROM Person p WHERE address = ?1")
	List<Person> getByAddress(String address);
	
	@Query(
			  value = "select * from person where address = ?1 and (first_name <> ?2 or last_name <> ?3)", 
			  nativeQuery = true)
	List<Person> getChildCompany(String address, String firstName, String lastName);
	
	@Query(
			  value = "SELECT p.* FROM PERSON P "
			  		+ "INNER JOIN FIRESTATION FS ON FS.ADDRESS = P.ADDRESS WHERE STATION = ?1", 
			  nativeQuery = true)
	List<Person> getPersonInfoByStation(String station);
	
	@Query(
			  value = "SELECT p.* FROM PERSON P "
			  		+ "INNER JOIN FIRESTATION FS ON FS.ADDRESS = P.ADDRESS WHERE STATION in (?1)", 
			  nativeQuery = true)
	List<Person> getPersonInfoByStations(List<String> stations);

}
