package com.harnina.tienda.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.harnina.tienda.service.ParteService;

@Entity
public class Parametro implements Parteable,Comparable<Parteable>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idParte;
	
	private String nombre;
	private String tipoDeDato;
	private String direccion;
	
	@ManyToMany(fetch = FetchType.EAGER)
	List<FuncionProcedureMetodo> funcionProcedureMetodo;
	
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

	public List<FuncionProcedureMetodo> getFuncionProcedureMetodo() {
		return funcionProcedureMetodo;
	}

	public void setFuncionProcedureMetodo(List<FuncionProcedureMetodo> funcionProcedureMetodo) {
		this.funcionProcedureMetodo = funcionProcedureMetodo;
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
	public List<Recurseable> getRescursos() {
		List<Recurseable> retorno = new ArrayList<>();
		retorno.addAll(this.funcionProcedureMetodo);
		return retorno;
	}

	@Override
	public int compareTo(Parteable parte) {
		return this.getNombre().compareTo(parte.getNombre());
	}

	@Override
	public void asociarParte(Recurseable recurso) {
		recurso.asociarParte((Parametro)this);
	}
	
}
