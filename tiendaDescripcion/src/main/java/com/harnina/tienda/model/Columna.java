package com.harnina.tienda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.harnina.tienda.service.ParteService;

@Entity
public class Columna implements Parteable,Comparable<Parteable>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idParte;
	
	private String nombre;
	private String tipoDeDato;
	private String descripcion;
	
	public Columna() {
		super();
	}
	
	public Columna(String nombre,String tipoDeDato,String descripcion) {
		this.nombre = nombre;
		this.tipoDeDato = tipoDeDato;
		this.descripcion = descripcion;
	}

	public String getTipoDeDato() {
		return tipoDeDato;
	}

	public void setTipoDeDato(String tipoDeDato) {
		this.tipoDeDato = tipoDeDato;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setIdParte(long idParte) {
		this.idParte = idParte;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public long getIdParte() {
		return idParte;
	}
	
	@Override
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public String toString() {
		return "Nombre subModulo "+ nombre;
	}

	@Override
	public void guardar(ParteService parteService) {
		parteService.guardarParte(this);
	}

	@Override
	public void borrar(ParteService parteService) {
		parteService.borrarParte(this);
	}

	@Override
	public int compareTo(Parteable parte) {
		return this.getNombre().compareTo(parte.getNombre());
	}
	
	@Override
	public void asociarParte(ParteService servicio, FuncionProcedureMetodo recurso) {
		servicio.asociarParte(recurso , this);
	}

	@Override
	public void asociarParte(ParteService servicio, Tabla tabla) {
		servicio.asociarParte(tabla , this);
	}

}
