package com.harnina.tienda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NombreRecursoEspecifico {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idNombreRecursoEspecifico;
	
	private String nombre;
	
	public NombreRecursoEspecifico() {
		super();
	}

	public NombreRecursoEspecifico(String nombre) {
		super();
		this.nombre = nombre;
	}

	public long getIdNombreRecursoEspecifico() {
		return idNombreRecursoEspecifico;
	}

	public void setIdNombreRecursoEspecifico(long idNombreRecursoEspecifico) {
		this.idNombreRecursoEspecifico = idNombreRecursoEspecifico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "Recurso Especifico" + nombre;
	}
	
}
