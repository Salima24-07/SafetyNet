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
	
	@Autowired
	private MockMvc mockMvc;
	
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
