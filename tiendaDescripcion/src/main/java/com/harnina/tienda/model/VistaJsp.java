package com.harnina.tienda.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.harnina.tienda.service.ParteService;
import com.harnina.tienda.service.RecursoService;

@Entity
public class VistaJsp implements Recurseable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idRecurso;
	private String nombre;
	@Column(length = 65535, columnDefinition = "text")
	private String descripcion;

	@OneToOne
	 private RecursoEspecifico recursoEspecifico;
	
	@ManyToMany()
	@LazyCollection(LazyCollectionOption.FALSE)
	 private List<Diagrama> diagrama;

	public VistaJsp() {}
	
	public VistaJsp(String nombre, String descripcion, RecursoEspecifico recursoEspecifico) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.recursoEspecifico = recursoEspecifico;
		this.diagrama = new ArrayList<>();
	}
	
	public VistaJsp(String nombre, String descripcion, RecursoEspecifico recursoEspecifico,List<Diagrama> diagramas) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.recursoEspecifico = recursoEspecifico;
		this.diagrama = diagramas;
	}

	public void setIdRecurso(long idRecurso) {
		this.idRecurso = idRecurso;
	}

	public long getIdFuncionProcedureMetodo() {
		return idRecurso;
	}
	
	@Override
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public RecursoEspecifico getRecursoEspecifico() {
		return recursoEspecifico;
	}

	public void setRecursoEspecifico(RecursoEspecifico recursoEspecifico) {
		this.recursoEspecifico = recursoEspecifico;
	}
	
	public List<Diagrama> getDiagrama() {
		return diagrama;
	}

	public void setDiagrama(List<Diagrama> diagrama) {
		this.diagrama = diagrama;
	}

	@Override
	public String toString() {
		return "RecursoEspecifico" + nombre;
	}

	@Override
	public long getIdRecurso() {
		return this.idRecurso;
	}

	@Override
	public long getIdRecursoEspecifico() {
		return this.recursoEspecifico.getIdRecursoEspecifico();
	}

	@Override
	public void guardar(RecursoService recursoService) {
		recursoService.guardarRecurso(this);
	}

	@Override
	public void borrar(RecursoService recursoService) {
		recursoService.borrarRecurso(this);
	}

	@Override
	public boolean has(String nombre) {
		return (nombre == "VistaJsp");
	}

	@Override
	public void asociarParte(ParteService parteService, Parteable parte) {
		parte.asociarParte(parteService, this);
	}
}
