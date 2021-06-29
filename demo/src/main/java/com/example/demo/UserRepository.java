package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>,CrudRepository<User, Long> {

//	@Query("SELECT u FROM User u WHERE u.email=?1")
//	Optional<User> findUserByEmail(String email);
	
	Optional<User> findUserByEmail(String email);
	
	//@Query("SELECT u FROM User u WHERE u.name=?1")
	List<User> findUserByName(String name);
	List<User> findUserBySurname(String surname);
	
	@Query("SELECT u from user u ORDER BY u.dob,u.doj asc")
	List<User> findUserByDob();
	


}
