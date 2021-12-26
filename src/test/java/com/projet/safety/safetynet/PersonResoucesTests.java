package com.projet.safety.safetynet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.projet.safety.safetynet.controllers.PersonResource;
import com.projet.safety.safetynet.domain.Person;
import com.projet.safety.safetynet.services.PersonService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonResourcesTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testCreatePersonOK() throws Exception {
		String url = "/person";
		
		Person person = new Person("test_create_ok", "test_create_ok","1509 Culver St", "Culver","97451","841-874-6512","jaboyd@email.com");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(person);
	    mockMvc.perform(MockMvcRequestBuilders.post(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isCreated());
	}
	
	@Test
	public void testCreatePersonKO() throws Exception {
		String url = "/person";
		
		Person person = new Person("test_create_ko", "test_create_ko", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(person);
	    mockMvc.perform(MockMvcRequestBuilders.post(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isBadRequest());
	}
	
	@Test
	public void testUpdatePersonOK() throws Exception {
		String url = "/person";
		
		Person person = new Person("test_update_ok", "test_update_ok", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(person);
	    mockMvc.perform(MockMvcRequestBuilders.put(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testUpdatePersonKO() throws Exception {
		String url = "/person";
		
		Person person = new Person("test_update_ko", "test_update_ko","1509 Culver St", "Culver","97451","841-874-6512","jaboyd@email.com");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(person);
	    mockMvc.perform(MockMvcRequestBuilders.put(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isBadRequest());
	}
	
	@Test
	public void testDeletePersonOK() throws Exception {
		String url = "/person";
		
		Person person = new Person("test_delete_ok", "test_delete_ok", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(person);
	    mockMvc.perform(MockMvcRequestBuilders.delete(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testDeletePersonKO() throws Exception {
		String url = "/person";
		
		Person person = new Person("test_delete_ko", "test_delete_ko", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(person);
	    mockMvc.perform(MockMvcRequestBuilders.delete(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isBadRequest());
	}

}
