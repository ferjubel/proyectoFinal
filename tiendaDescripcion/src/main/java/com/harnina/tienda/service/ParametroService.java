package com.harnina.tienda.service;

import java.util.ArrayList;
import java.util.List;
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
		comprobarLista();
		for (Parametro parametro : parametros) {
			if(parametro.getIdParte() == idParte){
				return parametro;
			}
		}
		return null;
	}

	public boolean existParametro(Parametro parametro) {
		comprobarLista();
		return !this.parametroRepository.findByNombre(parametro.getNombre()).isEmpty();
	}

	public void guardarParametro(Parametro parametro) {
		comprobarLista();
		this.parametroRepository.save(parametro);
		recargarParametro();
	}

	private void recargarParametro() {
		this.parametros = parametroRepository.findAll();
	}
	
	public void borrarParametro(Parametro parametro) {
		comprobarLista();
		this.parametroRepository.delete(parametro);
		recargarParametro();
	}

	private void comprobarLista() {
		if(this.parametros == null)recargarParametro();
	}

	@Override
	public List<Parteable> getPartes() {
		comprobarLista();
		List<Parteable> retorno = new ArrayList<>();
		retorno.addAll(this.parametros);
		return retorno;
	}
}
