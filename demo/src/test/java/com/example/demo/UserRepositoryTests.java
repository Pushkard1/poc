package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
class UserRepositoryTests {

	@Autowired
	private TestEntityManager entitymanager;
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testsaveUser() {
		User pushtest = new User("pushtest",
					"Deshmukh",
					"pushtest@gmail.com",
					421202,
					LocalDate.of(2000, Month.JANUARY, 05),
					LocalDate.of(2010, Month.JANUARY, 05));
		//assertThat(userRepository.save(pushtest));
		userRepository.save(pushtest);
		//assertNotNull(userRepository);
		assertNotNull(pushtest.getId());
	}
	
	@Test
	public void testfindUserByName() {

		User user2 =   (User) userRepository.findUserByName("push");
		assertThat(user2.getName()).isEqualTo("push");
	}
	
	

}
