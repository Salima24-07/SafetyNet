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
import com.projet.safety.safetynet.domain.MedicalRecord;

@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordResourcesTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testCreateMedicalRecord() throws Exception {
		String url = "/medicalrecord";
		
		MedicalRecord MedicalRecord = new MedicalRecord("test", "test", "03/06/1984", new String[]{"aznol:350mg", "hydrapermazol:100mg"}, new String[]{"nillacilan"});
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(MedicalRecord);
	    mockMvc.perform(MockMvcRequestBuilders.post(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isCreated());
	}
	
	@Test
	public void testUpdateMedicalRecord() throws Exception {
		String url = "/medicalrecord";
		
		MedicalRecord MedicalRecord = new MedicalRecord("test", "test", "03/06/1984", new String[]{"aznol:350mg", "hydrapermazol:100mg"}, new String[]{"nillacilan"});
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(MedicalRecord);
	    mockMvc.perform(MockMvcRequestBuilders.put(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteMedicalRecord() throws Exception {
		String url = "/medicalrecord";
		
		MedicalRecord MedicalRecord = new MedicalRecord("test", "test", "03/06/1984", new String[]{"aznol:350mg", "hydrapermazol:100mg"}, new String[]{"nillacilan"});
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(MedicalRecord);
	    mockMvc.perform(MockMvcRequestBuilders.delete(url)
	            .content(requestJson)
	    		.contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}

}
