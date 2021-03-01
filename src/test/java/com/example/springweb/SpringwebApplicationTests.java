package com.example.springweb;

import com.example.springweb.domObj.AddressDO;
import com.example.springweb.domObj.StudentDO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class SpringwebApplicationTests {

	private WebApplicationContext context;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}


	@Test
	public void testGetAllStudent() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/students")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*].id").exists())
				.andExpect(jsonPath("$[*].name").exists())
				.andExpect(jsonPath("$[*].lastname").exists())
				.andExpect(jsonPath("$[*].department").exists());
	}

	@Test
	public void testGetStudentById() throws Exception{
		String studentId = "4";

		mockMvc.perform(get("/api/v1/students/{studentId}", studentId)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.lastname").exists())
				.andExpect(jsonPath("$.department").exists());

	}

	@Test
	public void testGetCustomerByName() throws Exception {
		String studentName = "defne";

		mockMvc.perform(get("/api/v1/students-by-name/{studentName}", studentName)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.lastname").exists())
				.andExpect(jsonPath("$.department").exists());
	}

	@Test
	public void testCreateStudent() throws Exception {
		StudentDO newStudent = new StudentDO();
		newStudent.setName("eflin");
		newStudent.setLastname("yavuz");
		newStudent.setDepartment("Makine Müh");
		AddressDO newAddress = new AddressDO();
		newAddress.setCity("Tokat");
		newAddress.setRegion("Yeter Sokak");
		newAddress.setPostCode("121260");
		newStudent.setAddress(newAddress);

		mockMvc.perform(post("/api/v1/student")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(newStudent)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.lastname").exists())
				.andExpect(jsonPath("$.department").exists())
				.andExpect(jsonPath("$.address").exists())
				.andExpect(jsonPath("$.address.id").exists())
				.andExpect(jsonPath("$.address.city").exists())
				.andExpect(jsonPath("$.address.region").exists())
				.andExpect(jsonPath("$.address.postCode").exists());
	}

	@Test
	public void testUpdateStudent() throws Exception {
		StudentDO newStudent = new StudentDO();
		newStudent.setId(1L);
		newStudent.setName("tugba");
		newStudent.setLastname("yavuz");
		newStudent.setDepartment("Bilg Müh");
		AddressDO newAddress = new AddressDO();
		newAddress.setCity("Tokat");
		newAddress.setRegion("Yet Sokak");
		newAddress.setPostCode("344326");
		newStudent.setAddress(newAddress);

		mockMvc.perform(put("/api/v1/student")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(newStudent)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.lastname").exists())
				.andExpect(jsonPath("$.department").exists())
				.andExpect(jsonPath("$.address").exists())
				.andExpect(jsonPath("$.address.city").exists())
				.andExpect(jsonPath("$.address.region").exists())
				.andExpect(jsonPath("$.address.postCode").exists());
	}

	@Test
	public void testDeleteStudent() throws Exception {
		String studentId = "6";

		mockMvc.perform(delete("/api/v1/students-delete/{studentId}", studentId)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	public static String asJsonString(final Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
