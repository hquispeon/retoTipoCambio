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

import com.cjava.practica.model.Historial;
import com.cjava.practica.model.User;
import com.cjava.practica.service.HistorialService;
import com.cjava.practica.service.UserService;

@RequestMapping(value = "/change/")
@RestController
public class HistorialController {
	@Autowired
	private HistorialService historialService;
	
	@GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
	public String getHealthCheck()
	{
		return "{ \"todoOk\" : true }";
	}
	
	@GetMapping("/cambiar")
	public List<Historial> getUser()
	{
		return historialService.listAll();
	}
	
	@GetMapping("/cambiar/{id}")
	public ResponseEntity<Historial> getUsuario(@PathVariable String id)
	{
		try {
			Historial historial = historialService.get(id);
			return new ResponseEntity<Historial>(historial, HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Historial>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/usuario")
	public void add(@RequestBody Historial historial) {
		historialService.save(historial);
	}
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<?> update(@RequestBody Historial historial, @PathVariable String id) {
		try {
			Historial historialLocal = historialService.get(id);
			historialService.save(historial);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		try {
			historialService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
}
