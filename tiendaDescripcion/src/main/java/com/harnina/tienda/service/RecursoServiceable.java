package com.harnina.tienda.service;

import java.util.List;

import com.harnina.tienda.model.Recurseable;

public interface RecursoServiceable {
	
	List<Recurseable> getRecursos(long idRecursoEspecifico);
	
	List<Recurseable> getRecursos();

}
