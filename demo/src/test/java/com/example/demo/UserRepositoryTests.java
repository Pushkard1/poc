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
		User pushtest = new User("pushtest",
				"Deshmukh",
				"pushtest@gmail.com",
				421202,
				LocalDate.of(2000, Month.JANUARY, 05),
				LocalDate.of(2010, Month.JANUARY, 05));
		entitymanager.persist(pushtest);

		User pushtest2 = new User("pushtest",
				"Deshmukh",
				"pushtest2@gmail.com",
				421202,
				LocalDate.of(2000, Month.JANUARY, 05),
				LocalDate.of(2010, Month.JANUARY, 05));
		entitymanager.persist(pushtest2);
		
		User pushtest3 = new User("demotest",
				"Deshmukh",
				"demotest@gmail.com",
				421202,
				LocalDate.of(2000, Month.JANUARY, 05),
				LocalDate.of(2010, Month.JANUARY, 05));
		entitymanager.persist(pushtest3);
		
		Iterable<User> users = userRepository.findUserByName("pushtest");
		
		assertThat(users).hasSize(2).contains(pushtest,pushtest2);
	
	}
	
	@Test
	public void testfindUserBySurname() {
		User pushtest = new User("pushtest",
				"Deshmukh",
				"pushtest@gmail.com",
				421202,
				LocalDate.of(2000, Month.JANUARY, 05),
				LocalDate.of(2010, Month.JANUARY, 05));
		entitymanager.persist(pushtest);

		User pushtest2 = new User("pushtest",
				"Deshmukh",
				"pushtest2@gmail.com",
				421202,
				LocalDate.of(2000, Month.JANUARY, 05),
				LocalDate.of(2010, Month.JANUARY, 05));
		entitymanager.persist(pushtest2);
		
		User pushtest3 = new User("demotest",
				"test",
				"demotest@gmail.com",
				421202,
				LocalDate.of(2000, Month.JANUARY, 05),
				LocalDate.of(2010, Month.JANUARY, 05));
		entitymanager.persist(pushtest3);
		
		Iterable<User> users = userRepository.findUserBySurname("Deshmukh");
		
		assertThat(users).hasSize(2).contains(pushtest,pushtest2);
	
	}

}
