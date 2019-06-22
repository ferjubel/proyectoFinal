package com.harnina.tienda.model;

import com.harnina.tienda.service.ParteService;
import com.harnina.tienda.service.RecursoService;

public interface Recurseable {
	long getIdRecurso();
	long getIdRecursoEspecifico();
	void guardar(RecursoService recursoService);
	void borrar(RecursoService recursoService);
	String getNombre();
	void asociarParte(ParteService parteService, Parteable parte);
	boolean has(String string);
}
