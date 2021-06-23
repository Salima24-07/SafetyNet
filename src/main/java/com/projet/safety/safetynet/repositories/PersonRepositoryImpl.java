package com.projet.safety.safetynet.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.projet.safety.safetynet.domain.Person;
import com.projet.safety.safetynet.exceptions.BadRequestException;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
	
	private static final String SQL_CREATE = "INSERT INTO PERSON(FIRST_NAME, "
			+"LAST_NAME, ADDRESS, CITY, ZIP, PHONE, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_UPDATE = "UPDATE PERSON SET ADDRESS = ?, CITY = ?, ZIP = ?,"
			+"PHONE = ?, EMAIL = ? WHERE FIRST_NAME = ? AND LAST_NAME = ?";
	
	private static final String SQL_DELETE = "DELETE FROM PERSON WHERE FIRST_NAME = ? AND LAST_NAME = ?";
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Map<String, String> create(Person person) throws BadRequestException {
		
		try {
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.
						prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, person.getFirstName());
				ps.setString(2, person.getLastName());
				ps.setString(3, person.getAdress());
				ps.setString(4, person.getCity());
				ps.setString(5, person.getZip());
				ps.setString(6, person.getPhone());
				ps.setString(7, person.getEmail());
				return ps;
			}, keyHolder);
			
			Map<String,String> resultMap =new HashMap<String,String>();
			for (Map.Entry<String, Object> entry : keyHolder.getKeys().entrySet()) {
			       if(entry.getValue() instanceof String){
			    	   resultMap.put(entry.getKey(), (String) entry.getValue());
			          }
			 }
			
			return resultMap;
			
		} catch (Exception e) {
			throw new BadRequestException("Invalid details. Failed to create person");
		}
	}

	@Override
	public Boolean update(Person person) throws BadRequestException {
		
		try {
			
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.
						prepareStatement(SQL_UPDATE);
				ps.setString(1, person.getAdress());
				ps.setString(2, person.getCity());
				ps.setString(3, person.getZip());
				ps.setString(4, person.getPhone());
				ps.setString(5, person.getEmail());
				ps.setString(6, person.getFirstName());
				ps.setString(7, person.getLastName());
				return ps;
			});
			
			return true;
			
		} catch (Exception e) {
			throw new BadRequestException("Invalid details. Failed to update person");
		}
	}

	@Override
	public Boolean delete(String firstName, String lastName) throws BadRequestException {
		try {
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.
						prepareStatement(SQL_DELETE);
				ps.setString(1, firstName);
				ps.setString(2, lastName);
				return ps;
			});
			
			return true;
			
		} catch (Exception e) {
			throw new BadRequestException("Invalid details. Failed to update person");
		}
	}

}
