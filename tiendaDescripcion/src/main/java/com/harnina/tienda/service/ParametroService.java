package com.harnina.tienda.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.Parametro;
import com.harnina.tienda.model.Parteable;
import com.harnina.tienda.repository.ParametroRepository;

@Component
public class ParametroService implements ParteServiceable{
	
	@Autowired
	private ParametroRepository parametroRepository;
	
	private List<Parametro> parametros;

	public ParametroService() {
		super();
	}

	public Parametro getParametro(long idParte) {
		for (Parametro parametro : parametros) {
			if(parametro.getIdParte() == idParte){
				return parametro;
			}
		}
		return null;
	}

	public boolean existParametro(Parametro parametro) {
		return !this.parametroRepository.findByNombre(parametro.getNombre()).isEmpty();
	}

	public void guardarParametro(Parametro parametro) {
		this.parametroRepository.save(parametro);
		recargarParametro();
	}

	private void recargarParametro() {
		this.parametros = parametroRepository.findAll();
	}
	
	public void borrarParametro(Parametro parametro) {
		this.parametroRepository.delete(parametro);
		recargarParametro();
	}

	@Override
	public Set<Parteable> getPartes() {
		if(this.parametros==null){
			recargarParametro();
		}
		Set<Parteable> retorno = new TreeSet<>();
		retorno.addAll(this.parametros);
		return retorno;
	}
}
