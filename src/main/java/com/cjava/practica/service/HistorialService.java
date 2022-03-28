package com.cjava.practica.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjava.practica.model.Historial;
import com.cjava.practica.repository.HistorialRepository;

import reactor.core.publisher.Mono;

@Service
@Transactional
public class HistorialService {
	@Autowired
	private HistorialRepository historialRepo;
	
	public List<Historial> listAll(){
		return historialRepo.findAll();
	}
	
	public void save(Historial historial) {
		historialRepo.save(historial);
	}
	
	public Mono<Historial> get(String id_historial) {
		try {
			return Mono.just(historialRepo.findById(id_historial).get() );
		} catch (Exception e) {
			return Mono.empty();
		}
	}
	
	public void delete(String id_user) {
		historialRepo.deleteById(id_user);
	}
}
