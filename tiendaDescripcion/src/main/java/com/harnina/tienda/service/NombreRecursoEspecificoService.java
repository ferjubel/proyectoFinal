package com.harnina.tienda.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.NombreRecursoEspecifico;
import com.harnina.tienda.model.Recurseable;
import com.harnina.tienda.repository.NombreRecursoEspecificoRepository;

@Component
public class NombreRecursoEspecificoService {
	
	@Autowired
	private NombreRecursoEspecificoRepository nombreRecursoEspecificoRepository;
	
	private List<NombreRecursoEspecifico> nombresRecursoEspecifico;

	public NombreRecursoEspecificoService() {
		super();
	}

	public List<NombreRecursoEspecifico> getNombresRecursoEspecifico() {
		if(this.nombresRecursoEspecifico==null){
			this.nombresRecursoEspecifico = nombreRecursoEspecificoRepository.findAll();
		}
		return this.nombresRecursoEspecifico;
	}

	public NombreRecursoEspecifico getNombreRecursoEspecifico(long idRecursoEspecifico) {
		for (NombreRecursoEspecifico nombreRecursoEspecifico : nombresRecursoEspecifico) {
			if(nombreRecursoEspecifico.getIdNombreRecursoEspecifico() == idRecursoEspecifico){
				return nombreRecursoEspecifico;
			}
		}
		return null;
	}

	public boolean existNombreRecursoEspecifico(NombreRecursoEspecifico nombreRecursoEspecifico) {
		return !this.nombreRecursoEspecificoRepository.findByNombre(nombreRecursoEspecifico.getNombre()).isEmpty();
	}

	public void guardarNombreRecursoEspecifico(NombreRecursoEspecifico nombreRecursoEspecifico) {
		this.nombreRecursoEspecificoRepository.save(nombreRecursoEspecifico);
		recargarNombreRecursoEspecifico();
	}

	private void recargarNombreRecursoEspecifico() {
		this.nombresRecursoEspecifico = nombreRecursoEspecificoRepository.findAll();
	}
	
	public void borrarNombreRecursoEspecifico(NombreRecursoEspecifico nombreRecursoEspecifico) {
		this.nombreRecursoEspecificoRepository.delete(nombreRecursoEspecifico);
		recargarNombreRecursoEspecifico();
	}

}
