package com.projet.safety.safetynet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@SpringBootTest
@AutoConfigureMockMvc
class SafetyNetApplicationTests {

	private static String SET_UP_TESTS = "INSERT INTO PERSON(FIRST_NAME, "
		+"LAST_NAME, ADDRESS, CITY, ZIP, PHONE, EMAIL) "
		+"VALUES ('John', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'jaboyd@email.com'),"
		+" ('Jacob', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6513', 'drk@email.com'),"
		+" ('Tenley', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'tenz@email.com'),"
		+" ('Roger', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'jaboyd@email.com'),"
		+" ('Felicia', 'Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6544', 'jaboyd@email.com');\n"
		+"INSERT INTO FIRESTATION(ADDRESS, STATION) "
		+"VALUES('1509 Culver St','3');\n"
		+"INSERT INTO MEDICALRECORD (FIRST_NAME, LAST_NAME, BIRTHDATE, "
		+"MEDICATIONS, ALLERGIES) "
		+"VALUES ('John', 'Boyd', '1984-03-06', '{\"aznol:350mg\", \"hydrapermazol:100mg\"}', '{\"nillacilan\"}'),"
		+" ('Jacob', 'Boyd', '1989-03-06', '{\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"}', '{}'),"
		+" ('Tenley', 'Boyd', '2012-02-18', '{}', '{\"peanut\"}'),"
		+" ('Roger', 'Boyd', '2017-09-06', '{}', '{}'),"
		+" ('Felicia', 'Boyd','1986-01-08', '{\"tetracyclaz:650mg\"}', '{\"xilliathal\"}');";
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeAll
	private static void setUpDataBase() throws Exception {

		try {
		
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement(SET_UP_TESTS);
			ps.execute();
		} catch (Exception e) {
			throw new Exception("Erreur lors du set up de la base test" + e);
		}
		
	}
	
	@AfterAll
	private static void cleanUpDataBase() throws Exception {

		try {
		
			Connection connection = getConnection();
			PreparedStatement ps = connection.prepareStatement("delete from person; "
					+ "delete from firestation; "
					+ "delete from medicalrecord;");
			ps.execute();
		} catch (Exception e) {
			throw new Exception("Erreur lors du clean up de la base test" + e);
		}
		
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/safetynetTest","postgres", "Truc78370");
    }
	
	@Test
	public void testSafetyNetEmail() throws Exception {
		
		String url = "/communityEmail?city=Culver";
		
	    mockMvc.perform(MockMvcRequestBuilders.get(url))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testSafetyNetFireStation() throws Exception {
		
		String url = "/firestation?stationNumber=3";
		
	    mockMvc.perform(MockMvcRequestBuilders.get(url))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testSafetyNetPersonInfo() throws Exception {
		
		String url = "/personInfo?firstName=John&lastName=Boyd";
		
	    mockMvc.perform(MockMvcRequestBuilders.get(url))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testSafetyNetFlood() throws Exception {
		
		String url = "/flood/stations?stations=3,2";
		
	    mockMvc.perform(MockMvcRequestBuilders.get(url))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testSafetyNetFire() throws Exception {
		
		String url = "/fire?address=1509 Culver St";
		
	    mockMvc.perform(MockMvcRequestBuilders.get(url))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testSafetyNetChildAlert() throws Exception {
		
		String url = "/childAlert?address=1509 Culver St";
		
	    mockMvc.perform(MockMvcRequestBuilders.get(url))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testSafetyNetPhoneAlert() throws Exception {
		
		String url = "/phoneAlert?firestation=3";
		
	    mockMvc.perform(MockMvcRequestBuilders.get(url))
	            .andExpect(status().isOk());
	}

}
