package com.harnina.tienda.model;

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

@Entity
public class SubModulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idSubModulo;
	
	@Column(length = 65535, columnDefinition = "text")
	private String descripcion;

	@OneToOne()
	private Modulo modulo;
	
	@OneToOne()
	private NombreSubModulo nombreSubModulo;
	
	@ManyToMany()
	@LazyCollection(LazyCollectionOption.FALSE)
	 private List<Diagrama> diagrama;
	
	public SubModulo() {}
	
	public SubModulo(Modulo modulo, NombreSubModulo nombreSubModulo,String descripcion) {
		this.modulo = modulo;
		this.nombreSubModulo = nombreSubModulo;
		this.descripcion = descripcion;
	}

	public SubModulo(NombreSubModulo nombreSubModulo,Modulo modulo,String descripcion) {
		this.nombreSubModulo = nombreSubModulo;
		this.modulo = modulo;
		this.descripcion = descripcion;
	}

	public long getIdSubModulo() {
		return idSubModulo;
	}

	public NombreSubModulo getNombreSubModulo() {
		return nombreSubModulo;
	}

	public void setNombreSubModulo(NombreSubModulo nombreSubModulo) {
		this.nombreSubModulo = nombreSubModulo;
	}

	public void setIdSubModulo(long idSubModulo) {
		this.idSubModulo = idSubModulo;
	}
	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public List<Diagrama> getDiagrama() {
		return diagrama;
	}

	public void setDiagrama(List<Diagrama> diagrama) {
		this.diagrama = diagrama;
	}

	
}
