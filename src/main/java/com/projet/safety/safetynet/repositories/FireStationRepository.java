package com.projet.safety.safetynet.repositories;

import com.projet.safety.safetynet.domain.FireStation;
import com.projet.safety.safetynet.exceptions.BadRequestException;

public interface FireStationRepository {
	
	Boolean create(FireStation fireStation) throws BadRequestException;
	
	Boolean update(FireStation fireStation) throws BadRequestException;
	
	Boolean delete(String address) throws BadRequestException;

}
