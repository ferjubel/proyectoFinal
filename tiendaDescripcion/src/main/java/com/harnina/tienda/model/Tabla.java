package com.harnina.tienda.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import com.harnina.tienda.service.ParteService;
import com.harnina.tienda.service.RecursoService;

@Entity
public class Tabla implements Recurseable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idRecurso;
	private String rutaImagen;
	private String nombre;
	@Column(length = 65535, columnDefinition = "text")
	private String descripcion;

	@OneToOne
	 private RecursoEspecifico recursoEspecifico;
	
	@ManyToMany(fetch = FetchType.EAGER)
	 private List<Columna> columnas;

	public Tabla() {}
	
	public Tabla(String nombre, String rutaImagen, String descripcion, RecursoEspecifico recursoEspecifico,
			List<Columna> columnas) {
		super();
		this.nombre = nombre;
		this.rutaImagen = rutaImagen;
		this.descripcion = descripcion;
		this.recursoEspecifico = recursoEspecifico;
		this.columnas = columnas;
	}
	
	public Tabla(String nombre, String rutaImagen, String descripcion,RecursoEspecifico recursoEspecifico) {
		super();
		this.nombre = nombre;
		this.rutaImagen = rutaImagen;
		this.descripcion = descripcion;
		this.recursoEspecifico = recursoEspecifico;
		this.columnas = new ArrayList<>();
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public List<Columna> getColumnas() {
		return columnas;
	}

	public void setColumnas(List<Columna> columnas) {
		this.columnas = columnas;
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

	public void asociarParte(ParteService servicio,Parteable parte){
		parte.asociarParte(servicio, this);
	}
}
