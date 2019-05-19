package com.harnina.tienda.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.RecursoEspecifico;
import com.harnina.tienda.repository.RecursoEspecificoRepository;

@Component
public class RecursoEspecificoService {
	
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
		this.recursosEspecificos.clear();
		for (RecursoEspecifico recursoEspecifico : recursosEspecificos) {
			if(recursoEspecifico.getSubModulo().getIdSubModulo() == idSubModulo){
				this.recursosEspecificos.add(recursoEspecifico);
			}
		}
		return this.recursosEspecificos;
	}

	public RecursoEspecifico getRecursoEspecifico(long idSubModulo) {
		for (RecursoEspecifico nombreSubModulo : recursosEspecificos) {
			if(nombreSubModulo.getIdRecursoEspecifico() == idSubModulo){
				return nombreSubModulo;
			}
		}
		return null;
	}

	public boolean existRecursoEspecifico(RecursoEspecifico nombreSubModulo) {
		return !this.recursoEspecificoRepository.findByNombre(nombreSubModulo.getNombre()).isEmpty();
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

	

}
