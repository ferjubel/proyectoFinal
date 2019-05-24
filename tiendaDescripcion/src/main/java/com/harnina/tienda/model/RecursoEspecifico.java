package com.harnina.tienda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class RecursoEspecifico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idRecursoEspecifico;
	
	@OneToOne
	 private SubModulo subModulo;
	
	@OneToOne
	 private NombreRecursoEspecifico nombreRecursoEspecifico;
	
	public RecursoEspecifico() {}
	
	public RecursoEspecifico(SubModulo subModulo,
			NombreRecursoEspecifico nombreRecursoEspecifico) {
		super();
		this.subModulo = subModulo;
		this.nombreRecursoEspecifico = nombreRecursoEspecifico;
	}


	public long getIdRecursoEspecifico() {
		return idRecursoEspecifico;
	}

	public void setIdRecursoEspecifico(long idRecursoEspecifico) {
		this.idRecursoEspecifico = idRecursoEspecifico;
	}

	public SubModulo getSubModulo() {
		return subModulo;
	}

	public void setSubModulo(SubModulo subModulo) {
		this.subModulo = subModulo;
	}

	public NombreRecursoEspecifico getNombreRecursoEspecifico() {
		return nombreRecursoEspecifico;
	}

	public void setNombreRecursoEspecifico(NombreRecursoEspecifico nombreRecursoEspecifico) {
		this.nombreRecursoEspecifico = nombreRecursoEspecifico;
	}

	@Override
	public String toString() {
		return "RecursoEspecifico" + nombreRecursoEspecifico.getNombre();
	}

}
