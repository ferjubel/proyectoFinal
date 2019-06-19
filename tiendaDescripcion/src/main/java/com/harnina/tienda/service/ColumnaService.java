package com.harnina.tienda.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.Columna;
import com.harnina.tienda.model.Parteable;
import com.harnina.tienda.repository.ColumnaRepository;

@Component
public class ColumnaService implements ParteServiceable{
	
	@Autowired
	private ColumnaRepository columnaRepository;
	
	private List<Columna> columnas;

	public ColumnaService() {
		super();
	}

	public Columna getColumna(long idParte) {
		comprobarLista();
		for (Columna columna : columnas) {
			if(columna.getIdParte() == idParte){
				return columna;
			}
		}
		return null;
	}

	public boolean existColumna(Columna columna) {
		comprobarLista();
		return !this.columnaRepository.findByNombre(columna.getNombre()).isEmpty();
	}

	public void guardarColumna(Columna columna) {
		comprobarLista();
		this.columnaRepository.save(columna);
		recargarColumna();
	}

	private void recargarColumna() {
		this.columnas = columnaRepository.findAll();
	}
	
	public void borrarColumna(Columna columna) {
		comprobarLista();
		this.columnaRepository.delete(columna);
		recargarColumna();
	}

	@Override
	public List<Parteable> getPartes() {
		comprobarLista();
		List<Parteable> retorno = new ArrayList<>();
		retorno.addAll(this.columnas);
		return retorno;
	}

	private void comprobarLista() {
		if(this.columnas==null){
			recargarColumna();
		}
	}
}
