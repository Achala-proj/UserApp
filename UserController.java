package newSpringBoot.springBootExample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import newSpringBoot.springBootExample.dao.UserDAO;
import newSpringBoot.springBootExample.model.User;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/INfosys")
public class UserController {

	@Autowired
	UserDAO userDAO;
	
	/*create USer*/
	
	@PostMapping("users")
	public User createUser(@Valid @RequestBody User user) {
		return userDAO.create(user);
	}
	/*get all users*/
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userDAO.FindAll();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserbyId(@PathVariable(value="id") Long userId){
		User user = userDAO.findOne(userId);
		if(user==null) {
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok().body(user);
		
	}
	
	/*create User*/
	
	@PutMapping("/user/{id}")
	public ResponseEntity<User> createUser(@PathVariable(value="id") Long userId, @Valid @RequestBody User userDetails){
		User user = userDAO.findOne(userId);
		if(user==null) {
			return ResponseEntity.notFound().build();
			
		}
		user.setName(userDetails.getName());
		User create = userDAO.create(user);
		return ResponseEntity.ok().body(create);
	}
}
