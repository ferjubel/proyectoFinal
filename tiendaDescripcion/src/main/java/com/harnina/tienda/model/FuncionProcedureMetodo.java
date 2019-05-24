package com.harnina.tienda.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.harnina.tienda.service.RecursoService;

@Entity
public class FuncionProcedureMetodo implements Recurseable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idFuncionProcedureMetodo;
	
	private String nombre;
	private String codigo;
	private String descripcion;

	@OneToOne
	 private RecursoEspecifico recursoEspecifico;
	
	@OneToMany(cascade=CascadeType.ALL)
	 private List<Parametro> parametros;
	
	public FuncionProcedureMetodo() {}
	

	public FuncionProcedureMetodo(String nombre, String codigo, String descripcion, RecursoEspecifico recursoEspecifico,
			List<Parametro> parametros) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.recursoEspecifico = recursoEspecifico;
		this.parametros = parametros;
	}

	public long getIdFuncionProcedureMetodo() {
		return idFuncionProcedureMetodo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public List<Parametro> getParametros() {
		return parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	@Override
	public String toString() {
		return "RecursoEspecifico" + nombre;
	}

	@Override
	public long getId() {
		return this.idFuncionProcedureMetodo;
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
		// TODO Auto-generated method stub
		
	}

}
