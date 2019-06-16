package com.harnina.tienda.model;

import com.harnina.tienda.service.ParteService;

public interface Parteable{
	long getIdParte();
	String getNombre();
	void guardar(ParteService parteService);
	void borrar(ParteService parteService);
	void asociarParte(ParteService servicio, FuncionProcedureMetodo recurso);
	void asociarParte(ParteService servicio, Tabla tabla);
}
