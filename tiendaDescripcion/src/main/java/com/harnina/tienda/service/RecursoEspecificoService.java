package com.harnina.tienda.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.RecursoEspecifico;
import com.harnina.tienda.repository.RecursoEspecificoRepository;

@Component
public class RecursoEspecificoService implements Serviceable{
	
	@Autowired
	private RecursoEspecificoRepository recursoEspecificoRepository;
	
	private List<RecursoEspecifico> recursosEspecificos;

	public RecursoEspecificoService() {
		super();
	}

	public List<RecursoEspecifico> getRecursosEspecificos() {
		if(this.recursosEspecificos==null){
			this.recursosEspecificos = recursoEspecificoRepository.findAll();
		}
		return this.recursosEspecificos;
	}
	
	public List<RecursoEspecifico> getRecursosEspecificos(long idSubModulo) {
		comprobarLista();
		List<RecursoEspecifico> retorno = new ArrayList<>();
		for (RecursoEspecifico recursoEspecifico : this.recursosEspecificos) {
			if(recursoEspecifico.getSubModulo().getIdSubModulo() == idSubModulo){
				retorno.add(recursoEspecifico);
			}
		}
		return retorno;
	}

	public RecursoEspecifico getRecursoEspecifico(long idRecursoEspecifico) {
		comprobarLista();
		for (RecursoEspecifico recursoEspecifico : this.recursosEspecificos) {
			if(recursoEspecifico.getIdRecursoEspecifico() == idRecursoEspecifico){
				return recursoEspecifico;
			}
		}
		return null;
		
	}

	private void comprobarLista() {
		if(this.recursosEspecificos==null)recargarRecursosEspecificos();
	}

	public boolean existRecursoEspecifico(RecursoEspecifico recursoEspecifico) {
		comprobarLista();
		for (RecursoEspecifico recursoEspecificoTemp : recursosEspecificos) {
			if(recursoEspecificoTemp.getNombreRecursoEspecifico().equals(recursoEspecifico.getNombreRecursoEspecifico()) &&
					recursoEspecificoTemp.getSubModulo().equals(recursoEspecifico.getSubModulo())) return true;
		}
		return false;
	}

	public void guardarRecursoEspecifico(RecursoEspecifico recursoEspecifico) {
		this.recursoEspecificoRepository.save(recursoEspecifico);
		recargarRecursosEspecificos();
	}

	private void recargarRecursosEspecificos() {
		this.recursosEspecificos = recursoEspecificoRepository.findAll();
	}
	
	public void borrarRecursoEspecifico(RecursoEspecifico recursoEspecifico) {
		this.recursoEspecificoRepository.delete(recursoEspecifico);
		recargarRecursosEspecificos();
	}

	@Override
	public Thread cargarDatosEnRam() {
		Thread hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				recargarRecursosEspecificos();
			}
		});
		hilo.setName("recursoEspecifico");
		return hilo;
	}

	

}
