package com.cjava.practica.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjava.practica.repository.UserRepository;
import com.cjava.practica.model.Historial;
import com.cjava.practica.model.User;

@Service
@Transactional
public class HistorialService {
	@Autowired
	private UserRepository historialRepo;
	
	public List<Historial> listAll(){
		return historialRepo.findAll();
	}
	
	public void save(Historial historial) {
		historialRepo.save(historial);
	}
	
	public Historial get(String id_user) {
		return historialRepo.findById(id_user).get();
	}
	
	public void delete(String id_user) {
		historialRepo.deleteById(id_user);
	}
}
