package com.projet.safety.safetynet.repositories;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.projet.safety.safetynet.domain.FireStation;
import com.projet.safety.safetynet.exceptions.BadRequestException;

@Repository
public class FireStationRepositoryImpl implements FireStationRepository{

	private static final String SQL_CREATE = "INSERT INTO FIRESTATION(ADDRESS, "
			+"STATION) VALUES (?, ?)";
	
	private static final String SQL_UPDATE = "UPDATE FIRESTATION SET STATION = ? WHERE ADDRESS = ?";
	
	private static final String SQL_DELETE = "DELETE FROM FIRESTATION WHERE ADDRESS = ?";
	
	private static final String SQL_GET_BY_STATION_NUMBER = "SELECT P.FIRST_NAME, P.LAST_NAME, P.ADDRESS, "
			+"P.PHONE FROM PERSON P INNER JOIN FIRESTATION FS ON FS.ADDRESS = P.ADDRESS "
			+ "WHERE FS.STATION = ?";
	
	private static final String SQL_COUNT_ADULTS_CHILDREN = "SELECT SUM(CASE WHEN MR.BIRTHDATE < CURRENT_DATE - INTERVAL '18 YEAR' "
			+ "THEN 1 ELSE 0 END) AS ADULTS, SUM(CASE WHEN MR.BIRTHDATE < CURRENT_DATE - INTERVAL '18 YEAR' "
			+ "THEN 0 ELSE 1 END) AS CHILDREN "
			+ "FROM MEDICALRECORD MR INNER JOIN PERSON P ON P.FIRST_NAME = MR.FIRST_NAME AND P.LAST_NAME = MR.LAST_NAME "
			+ "INNER JOIN FIRESTATION FS ON FS.ADDRESS = P.ADDRESS WHERE FS.STATION = ?";
	
	private static final String SQL_GET_STATION_NUMBER = "SELECT STATION FROM FIRESTATION WHERE ADDRESS = ?";
	
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

	@Override
	public Map<String, Object> getByStationNumber(String station) {
		
		List<Map<String, String>> persons =  jdbcTemplate.query(SQL_GET_BY_STATION_NUMBER, new Object[] {station}, personsMapper);
		
		Map<String, Object> response = jdbcTemplate.queryForMap(SQL_COUNT_ADULTS_CHILDREN, new Object[] {station});
		
		response.put("persons", persons);
		
		return response;
		
	}
	
	private RowMapper<Map<String, String>> personsMapper = ((rs, rowNum) -> {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("firstName", rs.getString("FIRST_NAME"));
		map.put("lastName", rs.getString("LAST_NAME"));
		map.put("address", rs.getString("ADDRESS"));
		map.put("phone", rs.getString("PHONE"));
		
		return map;
	});

	@Override
	public Map<String, Object> getStationNumberByAddress(String address) {
		
		return jdbcTemplate.queryForMap(SQL_GET_STATION_NUMBER, address);
	}


}
