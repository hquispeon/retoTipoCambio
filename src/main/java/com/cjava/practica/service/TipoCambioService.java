package com.cjava.practica.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjava.practica.model.Historial;
import com.cjava.practica.repository.HistorialRepository;

@Service
@Transactional
public class TipoCambioService {
	@Autowired
	HistorialRepository historialRepo;
	
	public List<Historial> listAll(){
		return historialRepo.findAll();
	}
	
	public void save(Historial historial) {
		historialRepo.save(historial);
	}
	
	public Historial get(String id_historial) {
		return historialRepo.findById(id_historial).get();
	}
	
	public void delete(String id_historial) {
		historialRepo.deleteById(id_historial);
	}
}
