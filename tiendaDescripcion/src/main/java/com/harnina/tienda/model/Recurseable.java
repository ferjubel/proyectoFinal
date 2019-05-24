package com.harnina.tienda.model;

import com.harnina.tienda.service.RecursoService;

public interface Recurseable {
	long getId();
	long getIdRecursoEspecifico();
	void guardar(RecursoService recursoService);
	void borrar(RecursoService recursoService);
}
