package com.harnina.tienda.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.Modulo;
import com.harnina.tienda.repository.ModuloRepository;

@Component
public class ModuloService implements Serviceable{
	
	@Autowired
	private ModuloRepository moduloRepository;
	
	private List<Modulo> modulos;
	
	public ModuloService() {
		super();
	}

	public List<Modulo> getOpcionesModulo() {
		if(this.modulos==null){
			this.modulos = moduloRepository.findAll();
		}
		return this.modulos;
	}

	protected void recargarModulos() {
		this.moduloRepository.flush();
		this.modulos = moduloRepository.findAll();
	}
	
	public Modulo getModulo(long idModulo) {
		for (Modulo modulo : modulos) {
			if(modulo.getIdModulo() == idModulo){
				return modulo;
			}
		}
		return null;
	}

	public void guardarModulo(Modulo modulo) {
		this.moduloRepository.save(modulo);
		recargarModulos();
	}
	
	public void borrarModulo(Modulo modulo) {
		this.moduloRepository.delete(modulo);
		recargarModulos();
	}

	public boolean existModulo(Modulo modulo) {
		return !this.moduloRepository.findByNombre(modulo.getNombre()).isEmpty();
	}

	@Override
	public Thread cargarDatosEnRam() {
		Thread hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				recargarModulos();
			}
		});
		hilo.setPriority(Thread.MAX_PRIORITY);
		hilo.setName("modulo");
		return hilo;
	}

}
