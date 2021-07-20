package com.projet.safety.safetynet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.projet.safety.safetynet.domain.FireStation;

@SpringBootTest
@AutoConfigureMockMvc
class FireStationResourcesTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testCreateFireStationOK() throws Exception {
		String url = "/firestation";
		
		FireStation fireStation = new FireStation("test_create_ok", "test_create_ok");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(fireStation);
	    mockMvc.perform(MockMvcRequestBuilders.post(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isCreated());
	}
	
	@Test
	public void testCreateFireStationKO() throws Exception {
		String url = "/firestation";
		
		FireStation fireStation = new FireStation("test_create_ko","10");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(fireStation);
	    mockMvc.perform(MockMvcRequestBuilders.post(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isBadRequest());
	}
	
	@Test
	public void testUpdateFireStationOK() throws Exception {
		String url = "/firestation";
		
		FireStation fireStation = new FireStation("test_update_ok","11");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(fireStation);
	    mockMvc.perform(MockMvcRequestBuilders.put(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateFireStationKO() throws Exception {
		String url = "/firestation";
		
		FireStation fireStation = new FireStation("test_update_ko", "test_update_ko");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(fireStation);
	    mockMvc.perform(MockMvcRequestBuilders.put(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isBadRequest());
	}
	
	@Test
	public void testDeleteFireStationOK() throws Exception {
		String url = "/firestation";
		
		FireStation fireStation = new FireStation("test_delete_ok","12");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(fireStation);
	    mockMvc.perform(MockMvcRequestBuilders.delete(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteFireStationKO() throws Exception {
		String url = "/firestation";
		
		FireStation fireStation = new FireStation("test_delete_ko", "test_delete_ko");
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(fireStation);
	    mockMvc.perform(MockMvcRequestBuilders.delete(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isBadRequest());
	}

}
