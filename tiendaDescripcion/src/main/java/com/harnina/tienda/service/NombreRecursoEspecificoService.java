package com.harnina.tienda.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.NombreRecursoEspecifico;
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
		comprobarLista();
		return this.nombresRecursoEspecifico;
	}

	private void comprobarLista() {
		if(this.nombresRecursoEspecifico==null){
			recargarLista();
		}
	}

	private void recargarLista() {
		this.nombresRecursoEspecifico = nombreRecursoEspecificoRepository.findAll();
	}

	public NombreRecursoEspecifico getNombreRecursoEspecifico(long idRecursoEspecifico) {
		comprobarLista();
		for (NombreRecursoEspecifico nombreRecursoEspecifico : nombresRecursoEspecifico) {
			if(nombreRecursoEspecifico.getIdNombreRecursoEspecifico() == idRecursoEspecifico){
				return nombreRecursoEspecifico;
			}
		}
		return null;
	}

	public boolean existNombreRecursoEspecifico(NombreRecursoEspecifico nombreRecursoEspecifico) {
		comprobarLista();
		return !this.nombreRecursoEspecificoRepository.findByNombre(nombreRecursoEspecifico.getNombre()).isEmpty();
	}

	public void guardarNombreRecursoEspecifico(NombreRecursoEspecifico nombreRecursoEspecifico) {
		this.nombreRecursoEspecificoRepository.save(nombreRecursoEspecifico);
		recargarLista();
	}

	public void borrarNombreRecursoEspecifico(NombreRecursoEspecifico nombreRecursoEspecifico) {
		this.nombreRecursoEspecificoRepository.delete(nombreRecursoEspecifico);
		recargarLista();
	}

}
