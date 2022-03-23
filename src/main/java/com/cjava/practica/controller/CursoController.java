package com.cjava.practica.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cjava.practica.model.Curso;
import com.cjava.practica.repository.CursoRepository;

@RestController
public class CursoController {
	@Autowired
	CursoRepository cursoRepository;
	
	@GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
	public String getHealthCheck()
	{
		return "{ \"todoOk\" : true }";
	}
	
	@GetMapping("/cursos")
	public List<Curso> getEmployees()
	{
		List<Curso> employeesList = cursoRepository.findAll();
		return employeesList;
	}
	
	@GetMapping("/curso/{id}")
	public Optional<Curso> getEmployee(@PathVariable String id)
	{
		Optional<Curso> emp = cursoRepository.findById(id);
		return emp;
	}
	
	@PostMapping("/curso")
	public Curso addEmployee(@RequestBody Curso newCurso)
	{
		String id = String.valueOf(new Random().nextInt());
		Curso curso = new Curso(id, newCurso.getNombre(), newCurso.getCreditos());
		cursoRepository.save(curso);
		return curso;
	}
	
}
