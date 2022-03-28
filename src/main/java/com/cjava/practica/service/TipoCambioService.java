package com.cjava.practica.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjava.practica.model.Historial;
import com.cjava.practica.model.Tipocambio;
import com.cjava.practica.repository.HistorialRepository;
import com.cjava.practica.repository.TipoCambioRepository;

import reactor.core.publisher.Mono;

@Service
@Transactional
public class TipoCambioService {
	@Autowired
	TipoCambioRepository tipoCambioRepo;
	
	public List<Tipocambio> listAll(){
		return tipoCambioRepo.findAll();
	}
	
	public void save(Tipocambio tipoCambio) {
		tipoCambioRepo.save(tipoCambio);
	}
	
	public Mono<Tipocambio> get(String id_tipoCambio) {
		try {
			return Mono.just(tipoCambioRepo.findById(id_tipoCambio).get());
		} catch (Exception e) {
			return Mono.empty();
		}		
	}
	
	public void delete(String id_historial) {
		tipoCambioRepo.deleteById(id_historial);
	}
}
