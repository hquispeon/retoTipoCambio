package com.cjava.practica.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name="historial")
@AllArgsConstructor
@Getter @Setter
public class Historial implements Serializable{
	@Id
	// @GeneratedValue(generator="system-uuid")
	// @GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "id_historial")
	private @NonNull String id_historial;
	@Column(name = "id_tipocambio")
	private @NonNull String id_tipocambio;
	@Column(name = "id_user")
	private @NonNull String id_user;
	@Column(name = "moneda_ori")
	private String moneda_ori;
	@Column(name = "moneda_des")
	private String moneda_des;
	@Column(name = "monto")
	private String monto;
	@Column(name = "monto_cambio")
	private String monto_cambio;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha")
	private Date fecha;
	
	public Historial() {
		
	}
}
