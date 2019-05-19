package com.harnina.tienda.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.Parametro;
import com.harnina.tienda.repository.ParametroRepository;

@Component
public class ParametroService {
	
	@Autowired
	private ParametroRepository nombreSubModuloRepository;
	
	private List<Parametro> nombresSubModulo;

	public ParametroService() {
		super();
	}

	public List<Parametro> getNombresSubModulo() {
		if(this.nombresSubModulo==null){
			this.nombresSubModulo = nombreSubModuloRepository.findAll();
		}
		return this.nombresSubModulo;
	}

	public Parametro getParametro(long idSubModulo) {
		for (Parametro nombreSubModulo : nombresSubModulo) {
			if(nombreSubModulo.getIdParametro() == idSubModulo){
				return nombreSubModulo;
			}
		}
		return null;
	}

	public boolean existParametro(Parametro nombreSubModulo) {
		return !this.nombreSubModuloRepository.findByNombre(nombreSubModulo.getNombre()).isEmpty();
	}

	public void guardarParametro(Parametro nombreSubModulo) {
		this.nombreSubModuloRepository.save(nombreSubModulo);
		recargarParametro();
	}

	private void recargarParametro() {
		this.nombresSubModulo = nombreSubModuloRepository.findAll();
	}
	
	public void borrarParametro(Parametro nombreSubModulo) {
		this.nombreSubModuloRepository.delete(nombreSubModulo);
		recargarParametro();
	}

	

}
