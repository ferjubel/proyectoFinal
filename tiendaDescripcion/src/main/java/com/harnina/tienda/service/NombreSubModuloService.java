package com.harnina.tienda.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.NombreSubModulo;
import com.harnina.tienda.repository.NombreSubModuloRepository;

@Component
public class NombreSubModuloService implements Serviceable{
	
	@Autowired
	private NombreSubModuloRepository nombreSubModuloRepository;
	
	private List<NombreSubModulo> nombresSubModulo;

	public NombreSubModuloService() {
		super();
	}

	public List<NombreSubModulo> getNombresSubModulo() {
		if(this.nombresSubModulo==null){
			this.nombresSubModulo = nombreSubModuloRepository.findAll();
		}
		return this.nombresSubModulo;
	}

	public NombreSubModulo getNombreSubModulo(long idSubModulo) {
		for (NombreSubModulo nombreSubModulo : nombresSubModulo) {
			if(nombreSubModulo.getIdNombreSubModulo() == idSubModulo){
				return nombreSubModulo;
			}
		}
		return null;
	}

	public boolean existNombreSubModulo(NombreSubModulo nombreSubModulo) {
		return !this.nombreSubModuloRepository.findByNombre(nombreSubModulo.getNombre()).isEmpty();
	}

	public void guardarNombreSubModulo(NombreSubModulo nombreSubModulo) {
		this.nombreSubModuloRepository.save(nombreSubModulo);
		recargarNombreSubModulo();
	}

	private void recargarNombreSubModulo() {
		this.nombresSubModulo = nombreSubModuloRepository.findAll();
	}
	
	public void borrarNombreSubModulo(NombreSubModulo nombreSubModulo) {
		this.nombreSubModuloRepository.delete(nombreSubModulo);
		recargarNombreSubModulo();
	}
	@Override
	public Thread cargarDatosEnRam() {
		Thread hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				recargarNombreSubModulo();
			}
		});
		hilo.setPriority(Thread.MAX_PRIORITY);
		hilo.setName("nombreSubModulo");
		return hilo;
	}
	

}
