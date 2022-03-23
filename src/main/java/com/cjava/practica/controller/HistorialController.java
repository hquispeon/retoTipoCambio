package com.cjava.practica.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjava.practica.model.Historial;
import com.cjava.practica.model.Tipocambio;
import com.cjava.practica.model.User;
import com.cjava.practica.service.HistorialService;
import com.cjava.practica.service.TipoCambioService;
import com.cjava.practica.service.UserService;

@RequestMapping(value = "/change/")
@RestController
public class HistorialController {
	@Autowired
	private HistorialService historialService;
	@Autowired
	private TipoCambioService tipoCambioService;
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
	public String getHealthCheck()
	{
		return "{ \"todoOk\" : true }";
	}
	
	@GetMapping("/cambios")
	public List<Historial> getUser()
	{
		return historialService.listAll();
	}
	
	@GetMapping("/cambio/{id}")
	public ResponseEntity<Historial> getUsuario(@PathVariable String id)
	{
		try {
			Historial historial = historialService.get(id);
			return new ResponseEntity<Historial>(historial, HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<Historial>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/cambio")
	public void add(@RequestBody Historial historial) {
		historialService.save(historial);
	}
	
	@PostMapping("/reto/{id_tipocambio}/{id_user}/{monto}")
	public ResponseEntity<Historial> add(@PathVariable String id_tipocambio, @PathVariable String id_user, @PathVariable String monto) {
		try {
			Tipocambio tipoCambio = tipoCambioService.get(id_tipocambio);
			if (tipoCambio != null) {
				User user = userService.get(id_user);
				if (user != null) {
					BigDecimal montoLocal = new BigDecimal(monto);
					if (montoLocal.compareTo(BigDecimal.ZERO) == 1) {
						String id_historial = String.valueOf(new Random().nextInt());
						// Random random = new Random();
						// String id_historial = rx.Observable.just(random.nextInt());
						
						
						String moneda_ori = tipoCambio.getMoneda_ori();
						String moneda_des = tipoCambio.getMoneda_des();
						BigDecimal monto_cambio = montoLocal.multiply(new BigDecimal(tipoCambio.getMonto()));
						monto_cambio = monto_cambio.setScale(2, BigDecimal.ROUND_UP);
						String monto_final = monto_cambio.toString();
						Historial historialLocal = new Historial(id_historial, id_tipocambio, id_user, moneda_ori, moneda_des, monto, monto_final, new Date());
						
						historialService.save(historialLocal);
						
						Historial historialLocal2 = historialService.get(id_historial);
						return new ResponseEntity<Historial>(historialLocal2, HttpStatus.OK);
					} else {
						return new ResponseEntity<Historial>(HttpStatus.NOT_FOUND);
					}
				} else {
					return new ResponseEntity<Historial>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<Historial>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Historial>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/cambio/{id}")
	public ResponseEntity<?> update(@RequestBody Historial historial, @PathVariable String id) {
		try {
			Historial historialLocal = historialService.get(id);
			historialService.save(historial);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/cambio/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		try {
			historialService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
}
