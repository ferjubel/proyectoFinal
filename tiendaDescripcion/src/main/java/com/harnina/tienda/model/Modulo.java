package com.harnina.tienda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Modulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idModulo;
	
	private String nombre;
	
	public Modulo() {}

	public Modulo(String nombre) {
		super();
		this.nombre = nombre;
	}

	public long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(long idModulo) {
		this.idModulo = idModulo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Modulo [nombre=" + nombre+"]";
	}

}
