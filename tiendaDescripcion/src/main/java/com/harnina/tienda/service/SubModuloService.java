package com.harnina.tienda.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.SubModulo;
import com.harnina.tienda.repository.SubModuloRepository;

@Component
public class SubModuloService {
	
	@Autowired
	private SubModuloRepository nombreSubModuloRepository;
	
	private List<SubModulo> subModulos;

	public SubModuloService() {
		super();
	}

	public List<SubModulo> getSubModulos() {
		if(this.subModulos==null){
			this.subModulos = nombreSubModuloRepository.findAll();
		}
		return this.subModulos;
	}
	
	public List<SubModulo> getSubModulos(long idModulo) {
		this.subModulos.clear();
		for (SubModulo subModulo : subModulos) {
			if(subModulo.getModulo().getIdModulo() == idModulo){
				this.subModulos.add(subModulo);
			}
		}
		return this.subModulos;
	}

	public SubModulo getSubModulo(long idSubModulo) {
		for (SubModulo nombreSubModulo : subModulos) {
			if(nombreSubModulo.getIdSubModulo() == idSubModulo){
				return nombreSubModulo;
			}
		}
		return null;
	}

	public boolean existSubModulo(SubModulo subModulo) {
		return !this.nombreSubModuloRepository.findByNombreSubModulo(subModulo.getNombreSubModulo()).isEmpty();
	}

	public void guardarSubModulo(SubModulo subModulo) {
		this.nombreSubModuloRepository.save(subModulo);
		recargarSubModulo();
	}
	
	private void recargarSubModulo() {
		this.subModulos = nombreSubModuloRepository.findAll();
	}
	
	public void borrarSubModulo(SubModulo nombreSubModulo) {
		this.nombreSubModuloRepository.delete(nombreSubModulo);
		recargarSubModulo();
	}

	

	

	

}
