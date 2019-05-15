package com.harnina.tienda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Parametro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idParametro;
	
	private String nombre;
	private String tipoDeDato;
	private String direccion;
	
	public Parametro() {
		super();
	}
	
	public Parametro(String nombre,String tipoDeDato,String direccion) {
		this.nombre = nombre;
		this.tipoDeDato = tipoDeDato;
		this.direccion = direccion;
	}

	public String getTipoDeDato() {
		return tipoDeDato;
	}

	public void setTipoDeDato(String tipoDeDato) {
		this.tipoDeDato = tipoDeDato;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public long getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(long idParametro) {
		this.idParametro = idParametro;
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
