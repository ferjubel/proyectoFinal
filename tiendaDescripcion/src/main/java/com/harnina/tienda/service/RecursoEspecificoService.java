package com.harnina.tienda.service;

import java.util.ArrayList;
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
		if(this.recursosEspecificos==null)recargarRecursosEspecificos();
		List<RecursoEspecifico> retorno = new ArrayList<>();
		for (RecursoEspecifico recursoEspecifico : this.recursosEspecificos) {
			if(recursoEspecifico.getSubModulo().getIdSubModulo() == idSubModulo){
				retorno.add(recursoEspecifico);
			}
		}
		return retorno;
	}

	public RecursoEspecifico getRecursoEspecifico(long idSubModulo) {
		if(this.recursosEspecificos==null)recargarRecursosEspecificos();
		for (RecursoEspecifico recursoEspecifico : this.recursosEspecificos) {
			if(recursoEspecifico.getSubModulo().getIdSubModulo() == idSubModulo){
				return recursoEspecifico;
			}
		}
		return null;
		
	}

	public boolean existRecursoEspecifico(RecursoEspecifico recursoEspecifico) {
		return !this.recursoEspecificoRepository.findBySubModuloIdSubModulo(recursoEspecifico.getSubModulo().getIdSubModulo()).isEmpty() &&
				!this.recursoEspecificoRepository.findByNombreRecursoEspecificoIdNombreRecursoEspecifico(recursoEspecifico.getNombreRecursoEspecifico().getIdNombreRecursoEspecifico()).isEmpty();
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
