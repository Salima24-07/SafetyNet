package com.projet.safety.safetynet.repositories;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.projet.safety.safetynet.domain.Person;
import com.projet.safety.safetynet.exceptions.BadRequestException;
import com.projet.safety.safetynet.database_queries.PersonQueries;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Map<String, String> create(Person person) throws BadRequestException {
		
		try {
			
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.
						prepareStatement(PersonQueries.SQL_CREATE);
				ps.setString(1, person.getFirstName());
				ps.setString(2, person.getLastName());
				ps.setString(3, person.getAddress());
				ps.setString(4, person.getCity());
				ps.setString(5, person.getZip());
				ps.setString(6, person.getPhone());
				ps.setString(7, person.getEmail());
				return ps;
			});
			
			Map<String,String> resultMap =new HashMap<String,String>();
			resultMap.put("message", "Person created successfully");
			
			return resultMap;
			
		} catch (Exception e) {
			throw new BadRequestException("Invalid details. Failed to create person");
		}
	}

	@Override
	public Map<String, String> update(Person person) throws BadRequestException {
		
		try {
			
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.
						prepareStatement(PersonQueries.SQL_UPDATE);
				ps.setString(1, person.getAddress());
				ps.setString(2, person.getCity());
				ps.setString(3, person.getZip());
				ps.setString(4, person.getPhone());
				ps.setString(5, person.getEmail());
				ps.setString(6, person.getFirstName());
				ps.setString(7, person.getLastName());
				return ps;
			});
			
			Map<String,String> resultMap =new HashMap<String,String>();
			resultMap.put("message", "Person updated successfully");
			
			return resultMap;
			
		} catch (Exception e) {
			throw new BadRequestException("Invalid details. Failed to update person");
		}
	}

	@Override
	public Map<String, String> delete(String firstName, String lastName) throws BadRequestException {
		try {
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.
						prepareStatement(PersonQueries.SQL_DELETE);
				ps.setString(1, firstName);
				ps.setString(2, lastName);
				return ps;
			});

			Map<String,String> resultMap =new HashMap<String,String>();
			resultMap.put("message", "Person deleted successfully");
			
			return resultMap;
			
		} catch (Exception e) {
			throw new BadRequestException("Invalid details. Failed to update person");
		}
	}


	@Override
	public List<String> getEmailsByCity(String city) throws BadRequestException {
		
		return jdbcTemplate.query(PersonQueries.SQL_GET_EMAILS_BY_CITY, emailMapper, city);
	}
	
	@Override
	public List<String> getPhoneNumbers(String station) throws BadRequestException {
		
		return jdbcTemplate.query(PersonQueries.SQL_GET_PHONE_NUMBERS_BY_STATION, phoneMapper, station);
	}
	
	@Override
	public List<Map<String, Object>> getPersonInfoByName(String firstName, String lastName) throws BadRequestException {
		
		return jdbcTemplate.query(PersonQueries.SQL_GET_PERSON_INFO_BY_NAME, personInfoMapper, firstName, lastName);
	}
	
	@Override
	public List<Map<String, Object>> getPersonInfoByAddress(String address) throws BadRequestException {
		
		return jdbcTemplate.query(PersonQueries.SQL_GET_PERSON_INFO_BY_ADDRESS, addressMapper, address);
	}
	
	@Override
	public List<Map<String, Object>> getChildrenByAddress(String address) throws BadRequestException {
		
		return jdbcTemplate.query(PersonQueries.SQL_GET_CHILDREN_BY_ADDRESS, childMapper, address);
	}
	
	@Override
	public Map<String, Object> getStationInfo(String[] stations) throws BadRequestException {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		for (String station : stations) {
			result.put(station,
			jdbcTemplate.query(PersonQueries.SLQ_GET_STATIONS, stationsMapper, station));
		}
		
		return result;
	}
	
	private RowMapper<String> emailMapper = ((rs, rowNum) -> {
		return rs.getString("EMAIL");
	});
	
	private RowMapper<String> phoneMapper = ((rs, rowNum) -> {
		return rs.getString("PHONE");
	});
	
	private RowMapper<Map<String, Object>> personInfoMapper = ((rs, rowNum) -> {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("firstName", rs.getString("FIRST_NAME"));
		map.put("lastName", rs.getString("LAST_NAME"));
		map.put("address", rs.getString("ADDRESS"));
		map.put("age", rs.getString("AGE"));
		map.put("email", rs.getString("EMAIL"));
		Array allergiesArray = rs.getArray("ALLERGIES");
		 if (allergiesArray != null) {
		  map.put("allergies", (String[]) allergiesArray.getArray());
		 }
		Array medicationsArray = rs.getArray("MEDICATIONS");
		 if (medicationsArray != null) {
		  map.put("medications", (String[]) medicationsArray.getArray());
		 }
		
		return map;
	});
	
	private RowMapper<Map<String, Object>> addressMapper = ((rs, rowNum) -> {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("firstName", rs.getString("FIRST_NAME"));
		map.put("lastName", rs.getString("LAST_NAME"));
		map.put("age", rs.getString("AGE"));
		map.put("phone", rs.getString("PHONE"));
		Array allergiesArray = rs.getArray("ALLERGIES");
		 if (allergiesArray != null) {
		  map.put("allergies", (String[]) allergiesArray.getArray());
		 }
		Array medicationsArray = rs.getArray("MEDICATIONS");
		 if (medicationsArray != null) {
		  map.put("medications", (String[]) medicationsArray.getArray());
		 }
		
		return map;
	});
	
	private RowMapper<Map<String, Object>> childMapper = ((rs, rowNum) -> {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("firstName", rs.getString("FIRST_NAME"));
		map.put("lastName", rs.getString("LAST_NAME"));
		map.put("age", rs.getString("AGE"));
		Array autresArray = rs.getArray("autres");
		 if (autresArray != null) {
		  map.put("membres du foyer", (String[]) autresArray.getArray());
		 }
		
		return map;
	});
	
	private RowMapper<Map<String, Object>> stationsMapper = ((rs, rowNum) -> {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put(rs.getString("address"), getPersonInfoByAddress(rs.getString("address")));
		
		return map;
	});

}
