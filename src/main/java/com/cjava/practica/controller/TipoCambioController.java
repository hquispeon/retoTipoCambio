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

import com.cjava.practica.model.Tipocambio;
import com.cjava.practica.service.TipoCambioService;

@RequestMapping(value = "/tipocambio/")
@RestController
public class TipoCambioController {
	@Autowired
	private TipoCambioService tipoCambioService;
	
	@GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
	public String getHealthCheck()
	{
		return "{ \"todoOk\" : true }";
	}
	
	@GetMapping("/tipocambios")
	public List<Tipocambio> gettipoCambio()
	{
		return tipoCambioService.listAll();
	}
	
	@GetMapping("/tipocambio/{id}")
	public ResponseEntity<Tipocambio> getUsuario(@PathVariable String id)
	{
		try {
			Tipocambio tipoCambio = tipoCambioService.get(id);
			return new ResponseEntity<Tipocambio>(tipoCambio, HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Tipocambio>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/tipocambio")
	public void add(@RequestBody Tipocambio tipoCambio) {
		tipoCambioService.save(tipoCambio);
	}
	
	@PutMapping("/tipocambio/{id}")
	public ResponseEntity<?> update(@RequestBody Tipocambio tipoCambio, @PathVariable String id) {
		try {
			Tipocambio tipoCambioLocal = tipoCambioService.get(id);
			tipoCambioService.save(tipoCambio);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Tipocambio>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/tipocambio/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		try {
			tipoCambioService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Tipocambio>(HttpStatus.NOT_FOUND);
		}
	}
}
