package com.cjava.practica.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.cjava.practica.service.HistorialService;
import com.cjava.practica.service.TipoCambioService;
import com.cjava.practica.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
	public Mono<ResponseEntity<Flux<Historial>>> getUser()
	{
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(Flux.fromIterable(historialService.listAll())));
	}
	
	@GetMapping("/cambio/{id}")
	public Mono<Historial> getUsuario(@PathVariable String id)
	{
		return historialService.get(id);
	}
	
	@PostMapping("/cambio")
	public Mono<ResponseEntity<Void>> add(@RequestBody Historial historial) {
		historialService.save(historial);
		return Mono.just(new ResponseEntity<Void>(HttpStatus.OK));
	}
	
	/*
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
	*/
	
	@PostMapping("/reto/{id_tipocambio}/{id_user}/{monto}")
	public Mono<ResponseEntity<Mono<Historial>>> add(@PathVariable String id_tipocambio, @PathVariable String id_user, @PathVariable String monto) {
		return tipoCambioService.get(id_tipocambio)
				.flatMap(tipocambioRecuperado -> {					
					return userService.get(id_user)
							.flatMap(userRecuperado -> {
								BigDecimal montoLocal = new BigDecimal(monto);
								if (montoLocal.compareTo(BigDecimal.ZERO) == 1) {
									String id_historial = String.valueOf(new Random().nextInt());
									
									String moneda_ori = tipocambioRecuperado.getMoneda_ori();
									String moneda_des = tipocambioRecuperado.getMoneda_des();
									BigDecimal monto_cambio = montoLocal.multiply(new BigDecimal(tipocambioRecuperado.getMonto()));
									monto_cambio = monto_cambio.setScale(2, BigDecimal.ROUND_UP);
									String monto_final = monto_cambio.toString();
									
									Historial historialLocal = new Historial(id_historial, id_tipocambio, id_user, moneda_ori, moneda_des, monto, monto_final, new Date());
									
									historialService.save(historialLocal);
									
									Mono<Historial> historialLocal2 = historialService.get(id_historial);								
									return Mono.just(ResponseEntity.ok()
											.contentType(MediaType.APPLICATION_JSON)
											.body(historialLocal2));
									
								} else {
									// return Mono.just(new ResponseEntity<Historial>(HttpStatus.BAD_REQUEST));
									return Mono.just(ResponseEntity.badRequest()
											.contentType(MediaType.APPLICATION_JSON)
											.body(Mono.empty()));
								}
							});
				})
				//.defaultIfEmpty(ResponseEntity.badRequest()
				//		.contentType(MediaType.APPLICATION_JSON)
				//		.body(Mono.empty()))
				;
	}
	
	@PutMapping("/cambio/{id}")
	public Mono<ResponseEntity<Void>> update(@RequestBody Historial historial, @PathVariable String id) {
		/*
		try {
			Historial historialLocal = historialService.get(id);
			historialService.save(historial);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}}
		*/
		
		return historialService.get(id)
				.flatMap(historialRecuperado -> {
					historialService.save(historial);
					return Mono.just(new ResponseEntity<Void>(HttpStatus.OK));
				})
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));
	}
	
	@DeleteMapping("/cambio/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
		/*
		try {
			historialService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		*/
		return historialService.get(id)
				.flatMap(historialRecuperado -> {
					historialService.delete(id);
					return Mono.just(new ResponseEntity<Void>(HttpStatus.OK));
				})
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));
	}
}
