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
@Table(name="tipocambio")
@AllArgsConstructor
@Getter @Setter
public class Tipocambio {
	@Id
	// @GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "id_tipocambio")
	private @NonNull String id_tipocambio;
	@Column(name = "moneda_ori")
	private String moneda_ori;
	@Column(name = "moneda_des")
	private String moneda_des;
	@Column(name = "monto")
	private String monto;
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "tipo")
	private String tipo;
	
	public Tipocambio() {
		
	}
}
