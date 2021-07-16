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
import com.projet.safety.safetynet.database_queries.FireStationQueries;

@Repository
public class FireStationRepositoryImpl implements FireStationRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Boolean create(FireStation fireStation) throws BadRequestException {
		
		try {
			
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.
						prepareStatement(FireStationQueries.SQL_CREATE);
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
						prepareStatement(FireStationQueries.SQL_UPDATE);
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
						prepareStatement(FireStationQueries.SQL_DELETE);
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
		
		List<Map<String, String>> persons =  jdbcTemplate.query(FireStationQueries.SQL_GET_BY_STATION_NUMBER, personsMapper, station);
		
		Map<String, Object> response = jdbcTemplate.queryForMap(FireStationQueries.SQL_COUNT_ADULTS_CHILDREN, new Object[] {station});
		
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
		
		return jdbcTemplate.queryForMap(FireStationQueries.SQL_GET_STATION_NUMBER, address);
	}


}
