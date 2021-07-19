package com.projet.safety.safetynet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projet.safety.safetynet.domain.MedicalRecord;

public interface MedicalRecordRepository  extends JpaRepository<MedicalRecord, Long>{

    @Query("SELECT mr FROM MedicalRecord mr WHERE firstName = ?1 and lastName = ?2")
	Optional<MedicalRecord> getMedicalRecordByName(String firstName, String lastName);
    
    @Query(
			  value = "SELECT mr.* "
			  		+ "FROM PERSON p "
			  		+ "INNER JOIN MEDICALRECORD mr on p.first_name = mr.first_name "
			  		+ "and p.last_name = mr.last_name "
			  		+ "WHERE p.address = ?1 and extract(year from age(mr.birthdate)) <= 18",
			  nativeQuery = true)
    List<MedicalRecord> getChildrenByAddress(String address);

}
