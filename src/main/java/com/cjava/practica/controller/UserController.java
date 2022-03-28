package com.cjava.practica.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjava.practica.model.User;
import com.cjava.practica.service.UserService;

import reactor.core.publisher.Mono;

@RequestMapping(value = "/user/")
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
	public String getHealthCheck()
	{
		return "{ \"todoOk\" : true }";
	}
	
	@GetMapping("/usuarios")
	public List<User> getUser()
	{
		return userService.listAll();
	}
	
	@GetMapping("/usuario/{id}")
	public Mono<User> getUsuario(@PathVariable String id)
	{
		/*
		try {
			User user = userService.get(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		*/
		
		return userService.get(id);
	}
	
	@PostMapping("/usuario")
	public void add(@RequestBody User user) {
		userService.save(user);
	}
	
	@PutMapping("/usuario/{id}")
	public Mono<ResponseEntity<Void>> update(@RequestBody User user, @PathVariable String id) {
		/*
		try {
			User userLocal = userService.get(id);
			userService.save(user);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		*/
		
		return userService.get(id)
				.flatMap(userRecuperado -> {
					userService.save(user);
					return Mono.just(new ResponseEntity<Void>(HttpStatus.OK));
				})
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));
	}
	
	@DeleteMapping("/usuario/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
		/*
		try {
			userService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		*/
		
		return userService.get(id)
				.flatMap(userRecuperado -> {
					userService.delete(id);
					return Mono.just(new ResponseEntity<Void>(HttpStatus.OK));
				})
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));
	}
}
