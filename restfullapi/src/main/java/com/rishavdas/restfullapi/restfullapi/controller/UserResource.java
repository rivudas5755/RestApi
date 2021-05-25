package com.rishavdas.restfullapi.restfullapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rishavdas.restfullapi.restfullapi.Bean.User;
import com.rishavdas.restfullapi.restfullapi.DAO.UserDaoService;
import com.rishavdas.restfullapi.restfullapi.Exceptions.UserNotFoundException;


@RestController
public class UserResource {

	@Autowired
	UserDaoService service;
	
	
	@GetMapping(path = "/users")
	public List<User> retrieveAllUser()
	{
		List<User> users = new ArrayList<User>();
		
		users= service.findAll();
		
		if(users==null) {
			throw new UserNotFoundException("NO USER PRESENT");
		}
		
		return users;
	}
	
	
	@GetMapping(path = "users/{id}")
	public User retrieveUSer(@PathVariable int id)
	{
		User user = service.findById(id);
		
		if(user == null)
			throw new UserNotFoundException("User Not Found with ID: "+id);
		
		return user;
				
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
	{
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id)
	{
		boolean result = service.deleteUser(id);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").build().toUri();
		
		if(result == false)
			throw new UserNotFoundException("User Not Found with ID: "+id);
		
		return ResponseEntity.created(location).build();
		//return ResponseEntity.created(URI.create("/user")).build();
	}
	
	
}
