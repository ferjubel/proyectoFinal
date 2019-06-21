package com.harnina.tienda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Diagrama{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idDiagrama;
	
	private String nombre;
	private String rutaDiagrama;
	
	public Diagrama() {
		super();
	}
	
	public Diagrama(String nombre,String rutaDiagrama) {
		this.nombre = nombre;
		this.rutaDiagrama = rutaDiagrama;
	}

	public void setIdDiagrama(long idDiagrama) {
		this.idDiagrama = idDiagrama;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "Nombre subModulo "+ nombre;
	}

	public String getRutaDiagrama() {
		return rutaDiagrama;
	}

	public void setRutaDiagrama(String rutaDiagrama) {
		this.rutaDiagrama = rutaDiagrama;
	}

	public long getIdDiagrama() {
		return idDiagrama;
	}

	public String getNombre() {
		return nombre;
	}

	
}
