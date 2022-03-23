package com.cjava.practica.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjava.practica.repository.UserRepository;
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
	
	public User get(String id_user) {
		return userRepo.findById(id_user).get();
	}
	
	public void delete(String id_user) {
		userRepo.deleteById(id_user);
	}
}
