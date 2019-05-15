package com.harnina.tienda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NombreSubModulo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idNombreSubModulo;
	
	private String nombre;
	
	public NombreSubModulo() {
		super();
	}

	public NombreSubModulo(String nombre) {
		this.nombre = nombre;
	}

	public long getIdNombreSubModulo() {
		return idNombreSubModulo;
	}

	public void setIdNombreSubModulo(long idNombreSubModulo) {
		this.idNombreSubModulo = idNombreSubModulo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "Nombre subModulo "+ nombre;
	}
	
}
