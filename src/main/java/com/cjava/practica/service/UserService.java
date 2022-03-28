package com.cjava.practica.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjava.practica.repository.UserRepository;

import reactor.core.publisher.Mono;

import com.cjava.practica.model.User;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public List<User> listAll(){
		return userRepo.findAll();
	}
	
	public void save(User user) {
		userRepo.save(user);
	}
	
	public Mono<User> get(String id_user) {
		try {
			return Mono.just(userRepo.findById(id_user).get());
		} catch (Exception e) {
			return Mono.empty();
		}
	}
	
	public void delete(String id_user) {
		userRepo.deleteById(id_user);
	}
}
