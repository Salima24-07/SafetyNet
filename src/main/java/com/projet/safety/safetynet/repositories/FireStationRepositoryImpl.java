package com.projet.safety.safetynet.repositories;

import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.projet.safety.safetynet.domain.FireStation;
import com.projet.safety.safetynet.exceptions.BadRequestException;

@Repository
public class FireStationRepositoryImpl implements FireStationRepository{

	private static final String SQL_CREATE = "INSERT INTO FIRESTATION(ADDRESS, "
			+"STATION) VALUES (?, ?)";
	
	private static final String SQL_UPDATE = "UPDATE FIRESTATION SET STATION = ? WHERE ADDRESS = ?";
	
	private static final String SQL_DELETE = "DELETE FROM FIRESTATION WHERE ADDRESS = ?";
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Boolean create(FireStation fireStation) throws BadRequestException {
		
		try {
			
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.
						prepareStatement(SQL_CREATE);
				ps.setString(1, fireStation.getAddress());
				ps.setString(2, fireStation.getStation());
				return ps;
			});
			
			return true;
			
		} catch (Exception e) {
			throw new BadRequestException("Invalid details. Failed to create firestation");
		}
	}

	@Override
	public Boolean update(FireStation fireStation) throws BadRequestException {
		
		try {
			
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.
						prepareStatement(SQL_UPDATE);
				ps.setString(1, fireStation.getStation());
				ps.setString(2, fireStation.getAddress());
				return ps;
			});
			
			return true;
			
		} catch (Exception e) {
			throw new BadRequestException("Invalid details. Failed to update firestation");
		}
	}

	@Override
	public Boolean delete(String address) throws BadRequestException {
		try {
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.
						prepareStatement(SQL_DELETE);
				ps.setString(1, address);
				return ps;
			});
			
			return true;
			
		} catch (Exception e) {
			throw new BadRequestException("Invalid details. Failed to update firestation");
		}
	}


}
