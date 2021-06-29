package com.example.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.NamingStrategy.SuffixingRandom.BaseNameResolver.ForGivenType;


@WebMvcTest(UserController.class)
public class UserControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserRepository userRepository;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void getUsersTest() throws Exception {
		User user = new User("pushde",
				"Deshmukh",
				"pushde@gmail.com",
				421202,
				LocalDate.of(2000, Month.JANUARY, 05),
				LocalDate.of(2010, Month.JANUARY, 05));
		List<User> allUsers = Arrays.asList(user);
		BDDMockito.given(userService.getUser()).willReturn(allUsers);
	
		mockMvc.perform(get("/api").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
		
	}
	@Test
	public void registerUser_test() throws Exception {
		User user = new User("Test1",
				"Deshmukh",
				"test1@gmail.com",
				421202,
				LocalDate.of(2000, Month.JANUARY, 05),
				LocalDate.of(2010, Month.JANUARY, 05));
		
		BDDMockito.given(userService.addNewUser(user)).willReturn(user);
		mockMvc.perform(post("/users")
		.contentType(MediaType.APPLICATION_JSON)
		.content(JsonUtil.toJson(user)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.name", is(user.getName())));
		}

	}
