package com.cjava.practica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name="user")
@AllArgsConstructor
@Getter @Setter
public class User {
	@Id
	// @GeneratedValue(generator="system-uuid")
	// @GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "id_user")
	private @NonNull String id_user;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "password")
	private String password;
	
	public User() {
		
	}
}
