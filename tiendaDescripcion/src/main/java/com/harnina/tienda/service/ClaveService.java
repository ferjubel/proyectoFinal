package com.harnina.tienda.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.Clave;
import com.harnina.tienda.model.Parteable;
import com.harnina.tienda.repository.ClaveRepository;

@Component
public class ClaveService implements ParteServiceable{
	
	@Autowired
	private ClaveRepository claveRepository;
	
	private List<Clave> claves;

	public ClaveService() {
		super();
	}

	public Clave getClave(long idParte) {
		comprobarLista();
		for (Clave clave : claves) {
			if(clave.getIdParte() == idParte){
				return clave;
			}
		}
		return null;
	}

	public boolean existClave(Clave clave) {
		comprobarLista();
		return !this.claveRepository.findByNombre(clave.getNombre()).isEmpty();
	}

	public void guardarClave(Clave clave) {
		comprobarLista();
		this.claveRepository.save(clave);
		recargarClave();
	}

	private void recargarClave() {
		this.claves = claveRepository.findAll();
	}
	
	public void borrarClave(Clave clave) {
		comprobarLista();
		this.claveRepository.delete(clave);
		recargarClave();
	}

	@Override
	public List<Parteable> getPartes() {
		comprobarLista();
		List<Parteable> retorno = new ArrayList<>();
		retorno.addAll(this.claves);
		return retorno;
	}

	private void comprobarLista() {
		if(this.claves==null){
			recargarClave();
		}
	}
}
