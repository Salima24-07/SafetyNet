package com.projet.safety.safetynet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.projet.safety.safetynet.domain.MedicalRecord;
import com.projet.safety.safetynet.exceptions.BadRequestException;

@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordResourcesTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testCreateMedicalRecord() throws Exception {
		String url = "/medicalrecord";
		
		Map<String, Object> medicalRecord= new HashMap<String, Object>();
		
		medicalRecord.put("firstName", "test");
		medicalRecord.put("lastName", "test");
		medicalRecord.put("birthdate", "03/06/1984");
		medicalRecord.put("medications", new ArrayList<String>(Arrays. asList("aznol:350mg", "hydrapermazol:100mg")));
		medicalRecord.put("allergies", new ArrayList<String>(Arrays. asList("nillacilan")));
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(medicalRecord);
	    mockMvc.perform(MockMvcRequestBuilders.post(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isCreated());
	}
	
	@Test
	public void testUpdateMedicalRecord() throws Exception {
		String url = "/medicalrecord";
		
Map<String, Object> medicalRecord= new HashMap<String, Object>();
		
		medicalRecord.put("firstName", "test");
		medicalRecord.put("lastName", "test");
		medicalRecord.put("birthdate", "03/06/1984");
		medicalRecord.put("medications", new ArrayList<String>(Arrays. asList("aznol:350mg", "hydrapermazol:100mg")));
		medicalRecord.put("allergies", new ArrayList<String>(Arrays. asList("nillacilan")));
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(medicalRecord);
	    mockMvc.perform(MockMvcRequestBuilders.put(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteMedicalRecord() throws Exception {
		String url = "/medicalrecord";
		
		Map<String, Object> medicalRecord= new HashMap<String, Object>();
		
		medicalRecord.put("firstName", "test");
		medicalRecord.put("lastName", "test");
		medicalRecord.put("birthdate", "03/06/1984");
		medicalRecord.put("medications", new ArrayList<String>(Arrays. asList("aznol:350mg", "hydrapermazol:100mg")));
		medicalRecord.put("allergies", new ArrayList<String>(Arrays. asList("nillacilan")));
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(medicalRecord);
	    mockMvc.perform(MockMvcRequestBuilders.delete(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}

}
