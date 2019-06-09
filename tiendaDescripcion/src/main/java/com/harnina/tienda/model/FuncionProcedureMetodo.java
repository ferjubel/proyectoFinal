package com.harnina.tienda.model;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import com.harnina.tienda.service.RecursoService;

@Entity
public class FuncionProcedureMetodo implements Recurseable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idRecurso;
	
	private String nombre;
	private String codigo;
	private String descripcion;

	@OneToOne
	 private RecursoEspecifico recursoEspecifico;
	
	@ManyToMany
	 private Set <Parametro> parametros;
	
	public FuncionProcedureMetodo() {}
	

	public FuncionProcedureMetodo(String nombre, String codigo, String descripcion, RecursoEspecifico recursoEspecifico,
			Set<Parametro> parametros) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.recursoEspecifico = recursoEspecifico;
		this.parametros = parametros;
	}
	
	public FuncionProcedureMetodo(String nombre, String codigo, String descripcion) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.descripcion = descripcion;
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

	public Set<Parametro> getParametros() {
		return parametros;
	}

	public void setParametros(Set<Parametro> parametros) {
		this.parametros = parametros;
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

	public void asociarParte(Parametro parte){
		if(this.parametros == null)this.parametros = new TreeSet<>();
		this.parametros.add(parte);
	}


	@Override
	public void asociarParte(Parteable parte) {
		this.asociarParte(parte);
	}

}
