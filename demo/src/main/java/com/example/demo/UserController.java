package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@GetMapping
	public List<User> getUsers(){
		return userService.getUser();
	}
	
	@GetMapping("/{name}")
	public List<User> getUsersByname(@PathVariable("name") String name){
		//System.out.println(name);
		return userService.getUserByName(name);
	}
	
	@GetMapping("/asc")
	public List<User> getUsersbyDob(){
		return userService.getuserbydob();
	}
	
	
	@GetMapping("/api2/{surname}")
	public List<User> getUsersBySurname(@PathVariable("surname") String surname){
		return userService.getUserBySurname(surname);
	}
	
	
	@PostMapping
	public String registerNewUser(@RequestBody User user) {
		
		User userSaved = userService.addNewUser(user);
		return String.valueOf(userSaved.getName());
	}
	
	
	@DeleteMapping(path= "{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		
		userService.deleteUser(userId);
	}
	@PutMapping(path="{userId}")
	public void updateUser(
			@PathVariable("userId") long userId,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String email) {
		userService.updateUser(userId,name,email);
	}
	
}
