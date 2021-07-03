package com.projet.safety.safetynet.repositories;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
	
	private static final String SQL_GET_EMAILS_BY_CITY = "SELECT EMAIL FROM PERSON WHERE CITY = ?";

	private static final String SQL_GET_PHONE_NUMBERS_BY_STATION = "SELECT DISTINCT PHONE FROM PERSON P "
			+"INNER JOIN FIRESTATION FS ON FS.ADDRESS = P.ADDRESS WHERE STATION = ?";
	
	private static final String SQL_GET_PERSON_INFO_BY_NAME = "select p.first_name, p.last_name, "
			+ "concat(p.address, ', ', p.zip, ' ', p.city) address, "
			+ "extract(year from age(mr.birthdate)) age, "
			+ "p.email, mr.allergies, mr.medications "
			+ "from person p "
			+ "inner join medicalrecord mr on mr.first_name = p.first_name and mr.last_name = p.last_name "
			+ "where p.first_name = ? and p.last_name = ?";
	
	private static final String SQL_GET_PERSON_INFO_BY_ADDRESS = "select p.first_name, p.last_name, "
			+ "extract(year from age(mr.birthdate)) age, "
			+ "p.phone, mr.allergies, mr.medications "
			+ "from person p "
			+ "inner join medicalrecord mr on mr.first_name = p.first_name and mr.last_name = p.last_name "
			+ "where p.address = ?";
	
	private static final String SQL_GET_CHILDREN_BY_ADDRESS = "select t1.first_name, t1.last_name, "
			+ "extract(year from age(mr.birthdate)) age,\r\n"
			+ "array_agg(concat(t2.first_name, ' ', t2.last_name)) autres\r\n"
			+ "from person t1\r\n"
			+ "inner join medicalrecord mr on t1.first_name = mr.first_name\r\n"
			+ "and t1.last_name = mr.last_name\r\n"
			+ "left join person t2 on t1.address = t2.address\r\n"
			+ "and (t1.first_name <> t2.first_name\r\n"
			+ "or t1.last_name <> t2.last_name)\r\n"
			+ "where extract(year from age(mr.birthdate)) <= 18\r\n"
			+ "and t1.address = ? \r\n"
			+ "group by 1, 2, 3";
	
	private static final String SLQ_GET_STATIONS = "SELECT STATION, ADDRESS FROM FIRESTATION "
			+ "WHERE STATION = ? ;";
	
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
				ps.setString(3, person.getAddress());
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
				ps.setString(1, person.getAddress());
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

	@Override
	public List<String> getEmailsByCity(String city) throws BadRequestException {
		
		return jdbcTemplate.query(SQL_GET_EMAILS_BY_CITY, emailMapper, city);
	}
	
	@Override
	public List<String> getPhoneNumbers(String station) throws BadRequestException {
		
		return jdbcTemplate.query(SQL_GET_PHONE_NUMBERS_BY_STATION, phoneMapper, station);
	}
	
	@Override
	public List<Map<String, Object>> getPersonInfoByName(String firstName, String lastName) throws BadRequestException {
		
		return jdbcTemplate.query(SQL_GET_PERSON_INFO_BY_NAME, personInfoMapper, firstName, lastName);
	}
	
	@Override
	public List<Map<String, Object>> getPersonInfoByAddress(String address) throws BadRequestException {
		
		return jdbcTemplate.query(SQL_GET_PERSON_INFO_BY_ADDRESS, addressMapper, address);
	}
	
	@Override
	public List<Map<String, Object>> getChildrenByAddress(String address) throws BadRequestException {
		
		return jdbcTemplate.query(SQL_GET_CHILDREN_BY_ADDRESS, childMapper, address);
	}
	
	@Override
	public Map<String, Object> getStationInfo(String[] stations) throws BadRequestException {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		for (String station : stations) {
			result.put(station,
			jdbcTemplate.query(SLQ_GET_STATIONS, stationsMapper, station));
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
