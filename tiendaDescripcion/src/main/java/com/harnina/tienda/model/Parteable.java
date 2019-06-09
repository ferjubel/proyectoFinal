package com.harnina.tienda.model;

import java.util.List;

import com.harnina.tienda.service.ParteService;

public interface Parteable{
	long getIdParte();
	String getNombre();
	void guardar(ParteService parteService);
	void borrar(ParteService parteService);
	List<Recurseable> getRescursos();
	void asociarParte(Recurseable recurso);
}
