package com.cjava.practica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name="curso")
@AllArgsConstructor
@Getter @Setter
public class Curso {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private @NonNull String id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "creditos")
	private String creditos;
	
	public Curso() {
		
	}
}
