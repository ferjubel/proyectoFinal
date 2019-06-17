package com.harnina.tienda.model;

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
public class FuncionProcedureMetodo implements Recurseable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idRecurso;
	
	private String nombre;
	@Column(length = 65535, columnDefinition = "text")
	private String codigo;
	@Column(length = 65535, columnDefinition = "text")
	private String descripcion;

	@OneToOne
	 private RecursoEspecifico recursoEspecifico;
	
	@ManyToMany(fetch = FetchType.EAGER)
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
