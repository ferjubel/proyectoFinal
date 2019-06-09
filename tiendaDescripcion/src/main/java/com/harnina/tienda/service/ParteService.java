package com.harnina.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.Parametro;
import com.harnina.tienda.model.Parteable;
import com.harnina.tienda.model.Recurseable;

@Component
public class ParteService{

	List <ParteServiceable> serviceLists;
	
	List<Parteable> partes;
	
	@Autowired
	private ParametroService parametroService;
	
	public ParteService() {
	}
	
	public Set<Parteable> getPartes() {
		comprobarListas();
		Set<Parteable> retorno = new TreeSet<>();
		for (ParteServiceable servicioParte : serviceLists) {
			retorno.addAll(servicioParte.getPartes());
		}
		return retorno;
	}
	
	private void comprobarListas() {
		if(this.serviceLists == null) iniciarListaServicios();
		if(this.partes == null) recargarPartes();
	}

	private void recargarPartes() {
		this.partes = new ArrayList<>();
		for (ParteServiceable servicioParte : serviceLists) {
			this.partes.addAll(servicioParte.getPartes());
		}
	}

	private void iniciarListaServicios() {
		this.serviceLists = new ArrayList<>();
		this.serviceLists.add(parametroService);
	}
	
	public boolean existParte(Parteable parte) {
		comprobarListas();
		for (Parteable parteTmp : partes) {
			if(parteTmp.equals(parte))return true;
		}
		return false;
	}
	
	public void guardarParte(Parteable parte) {
		if(!existParte(parte))parte.guardar(this);
	}

	public void borrarParte(Parteable parte) {
		if(existParte(parte))parte.borrar(this);
	}
	
	public void guardarParte(Parametro parametro) {
		parametroService.guardarParametro(parametro);
	}

	public Parteable getParte(String idParte, String nombreParte) {
		for (Parteable parte : partes) {
			if(parte.getIdParte() == Long.valueOf(idParte) && 
					parte.getNombre().equals(nombreParte)) return parte;
		}
		return null;
	}

	public void asociarParte(Recurseable recurso, Parteable parte) {
		parte.asociarParte(recurso);
	}
	

}
