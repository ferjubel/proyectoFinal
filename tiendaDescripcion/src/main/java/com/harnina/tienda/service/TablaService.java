package com.harnina.tienda.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.Recurseable;
import com.harnina.tienda.model.Tabla;
import com.harnina.tienda.repository.TablaRepository;

@Component
public class TablaService implements RecursoServiceable{
	
	@Autowired
	private TablaRepository tablaRepository;
	
	private List<Tabla> tablaList;
	
	public TablaService() {
		super();
	}
	
	@Override
	public List<Recurseable> getRecursos(long idRecursoEspecifico) {
		comprobarLista();
		List<Recurseable> retorno = new ArrayList<>();
		for (Tabla tabla : this.tablaList) {
			if(tabla.getRecursoEspecifico().getIdRecursoEspecifico() == idRecursoEspecifico){
				retorno.add(tabla);
			}
		}
		return retorno;
	}

	@Override
	public List<Recurseable> getRecursos() {
		comprobarLista();
		List<Recurseable> retorno = new ArrayList<>();
		retorno.addAll(this.tablaList);
		return retorno;
	}

	private void comprobarLista() {
		if(this.tablaList==null){
			recargarTabla();
		}
	}
	
	public void guardarRecurso(Tabla tabla) {
		this.tablaRepository.save(tabla);
		recargarTabla();
	}
	
	private void recargarTabla() {
		this.tablaList = tablaRepository.findAll();
	}
	
	public void borrarRecurso(Tabla nombre) {
		this.tablaRepository.delete(nombre);
		recargarTabla();
	}
}
