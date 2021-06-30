package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {
	@Bean
	CommandLineRunner commandLineRunner(UserRepository repository) {
		return args -> {
			User  push =new User(
					"push",
					"Deshmukh",
					"push@gmail.com",
					421202,
					LocalDate.of(2000, Month.JANUARY, 05),
					LocalDate.of(2010, Month.JANUARY, 05)
					
					);
			User  push2 =new User(
					"push",
					"Deshmukh",
					"pushd@gmail.com",
					421202,
					LocalDate.of(2000, Month.JANUARY, 05),
					LocalDate.of(2010, Month.JANUARY, 05)
					
					);
			User  Test =new User(
					"Test",
					"Demo",
					"Test@gmail.com",
					421202,
					LocalDate.of(2009, Month.FEBRUARY, 05),
					LocalDate.of(2020, Month.JANUARY, 05)
					
					);
			repository.saveAll(
					List.of(push,push2,Test)
					
					);
		};
	}

}
