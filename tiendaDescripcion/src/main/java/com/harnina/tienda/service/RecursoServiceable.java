package com.harnina.tienda.service;

import java.util.List;

import com.harnina.tienda.model.FuncionProcedureMetodo;
import com.harnina.tienda.model.Recurseable;

public interface RecursoServiceable {
	List<Recurseable> getRecursos(long idRecursoEspecifico);
	
	List<Recurseable> getRecursos();

	Recurseable getRecurso(long id);

	void guardarRecurso(FuncionProcedureMetodo funcionProcedureMetodo);

	boolean existRecurso(FuncionProcedureMetodo funcionProcedureMetodo);

	void borrarRecurso(FuncionProcedureMetodo nombre);
}
