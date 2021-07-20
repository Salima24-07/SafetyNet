package com.projet.safety.safetynet.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projet.safety.safetynet.domain.FireStation;
import com.projet.safety.safetynet.exceptions.BadRequestException;

public interface FireStationRepository  extends JpaRepository<FireStation, Long>{

	@Query("SELECT fs FROM FireStation fs WHERE station = ?1 and address = ?2")
	Optional<FireStation> getByStationNumberAndAddress(String station, String address);

	@Query("SELECT fs FROM FireStation fs WHERE station = ?1")
	Optional<List<FireStation>> getByStationNumber(String station);

	@Query("SELECT fs FROM FireStation fs WHERE address = ?1")
	Optional<List<FireStation>> getByAddress(String address);
	
	@Query("SELECT fs FROM FireStation fs WHERE address = ?1 or station = ?2")
	Optional<List<FireStation>> getByAddressOrStation(String address, String station);
	
	@Query("SELECT fs.station FROM FireStation fs WHERE address = ?1")
	List<String> getStationByAddress(String address);

}
