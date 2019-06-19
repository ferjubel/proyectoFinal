package com.harnina.tienda.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.Columna;
import com.harnina.tienda.model.Parteable;
import com.harnina.tienda.repository.ColumnaRepository;

@Component
public class ColumnaService implements ParteServiceable{
	
	@Autowired
	private ColumnaRepository parametroRepository;
	
	private List<Columna> parametros;

	public ColumnaService() {
		super();
	}

	public Columna getColumna(long idParte) {
		for (Columna parametro : parametros) {
			if(parametro.getIdParte() == idParte){
				return parametro;
			}
		}
		return null;
	}

	public boolean existColumna(Columna parametro) {
		return !this.parametroRepository.findByNombre(parametro.getNombre()).isEmpty();
	}

	public void guardarColumna(Columna parametro) {
		this.parametroRepository.save(parametro);
		recargarColumna();
	}

	private void recargarColumna() {
		this.parametros = parametroRepository.findAll();
	}
	
	public void borrarColumna(Columna parametro) {
		this.parametroRepository.delete(parametro);
		recargarColumna();
	}

	@Override
	public Set<Parteable> getPartes() {
		if(this.parametros==null){
			recargarColumna();
		}
		Set<Parteable> retorno = new TreeSet<>();
		retorno.addAll(this.parametros);
		return retorno;
	}
}
