package com.cjava.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjava.practica.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, String>{

}
