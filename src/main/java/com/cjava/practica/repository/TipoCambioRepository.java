package com.cjava.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjava.practica.model.Tipocambio;

@Repository
public interface TipoCambioRepository extends JpaRepository<Tipocambio, String>{

}
