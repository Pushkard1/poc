package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;
	

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public List<User> getUser() {
		
		return userRepository.findAll();
	}
	
	public List<User> getUserByName(String name) {
		List<User> UserOptional= 
				userRepository.findUserByName(name);
//		if(!UserOptional.isEmpty()) {
//			throw new IllegalStateException("user does not exist");
//		
//		}
		return UserOptional;
	}

	public List<User> getUserBySurname(String surname) {
		List<User> UserOptional= 
				userRepository.findUserBySurname(surname);
//		if(!UserOptional.isEmpty()) {
//			throw new IllegalStateException("user does not exist");
//		
//		}
		return UserOptional;
	}
	
	public void addNewUser(User user) {
		Optional<User> UserOptional= 
				userRepository.findUserByEmail(user.getEmail());
		if(UserOptional.isPresent()) {
			throw new IllegalStateException("email Taken");
		}
		userRepository.save(user);
	}

	public void deleteUser(Long userId) {
		boolean exists = userRepository.existsById(userId);
		if(!exists) {
			throw new IllegalStateException(
					"user with id " + userId + " doesn't exists" );
		} 
		userRepository.deleteById(userId);
		
	}

	public void updateUser(long userId, String name, String email) {
		User user = userRepository.findById(userId)
				.orElseThrow(()->new IllegalStateException("User with ID " + userId + " does not exist"));
		
		
	}
	public List<User> getuserbydob(){
		return userRepository.findUserByDob();
	}

}
