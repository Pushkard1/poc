package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.http.HttpHeaders;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(UserController.class)
public class UserControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private UserRepository userRepository;
	@MockBean
	private UserService userService;
	
	@Test
	public void getUser_Test() throws Exception {
		User user = new User("pushde",
				"Deshmukh",
				"pushde@gmail.com",
				421202,
				LocalDate.of(2000, Month.JANUARY, 05),
				LocalDate.of(2010, Month.JANUARY, 05));
		List<User> allUsers = Arrays.asList(user);
		Mockito.when(userService.getUser()).thenReturn(allUsers);
		MvcResult result = mockMvc.perform(get("/api")).andExpect(status().isOk()).andReturn();
		String actualJsonResponse = result.getResponse().getContentAsString();
		System.out.println(actualJsonResponse);
		
		String expectedJsonResponse = objectMapper.writeValueAsString(allUsers);
		assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
	}
	@Test
	public void registerUser_test() throws Exception {
		User user = new User("Test1",
				"Deshmukh",
				"test12@gmail.com",
				421202,
				LocalDate.of(2000, Month.JANUARY, 05),
				LocalDate.of(2010, Month.JANUARY, 05));
		
		when(userService.addNewUser(user)).thenReturn(user);
		
		MvcResult mvcResult = mockMvc.perform(post("/api")
		.contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(user))).andExpect(status().isOk()).andReturn();
		
		String mvcResultContent = mvcResult.getResponse().getContentAsString();
		User userTest = objectMapper.readValue(mvcResultContent, User.class);
		assertThat(user.getName()=="Test1");
		}

	}
