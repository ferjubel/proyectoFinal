package com.harnina.tienda.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.SubModulo;
import com.harnina.tienda.repository.SubModuloRepository;

@Component
public class SubModuloService {
	
	@Autowired
	private SubModuloRepository subModuloRepository;
	
	private List<SubModulo> subModulos;

	public SubModuloService() {
		super();
	}

	public List<SubModulo> getSubModulos() {
		if(this.subModulos==null)recargarSubModulo();
		return this.subModulos;
	}
	
	public List<SubModulo> getSubModulos(long idModulo) {
		if(this.subModulos==null)recargarSubModulo();
		List<SubModulo> retorno = new ArrayList<>();
		for (SubModulo subModulo : this.subModulos) {
			if(subModulo.getModulo().getIdModulo() == idModulo){
				retorno.add(subModulo);
			}
		}
		return retorno;
	}

	public SubModulo getSubModulo(long idSubModulo) {
		if(this.subModulos==null)recargarSubModulo();
		for (SubModulo subModulo : this.subModulos) {
			if(subModulo.getIdSubModulo() == idSubModulo){
				return subModulo;
			}
		}
		return null;
	}

	public boolean existSubModulo(SubModulo subModulo) {
		return !this.subModuloRepository.findByNombreSubModulo(subModulo.getNombreSubModulo()).isEmpty();
	}

	public void guardarSubModulo(SubModulo subModulo) {
		this.subModuloRepository.save(subModulo);
		recargarSubModulo();
	}
	
	private void recargarSubModulo() {
		this.subModulos = subModuloRepository.findAll();
	}
	
	public void borrarSubModulo(SubModulo nombreSubModulo) {
		this.subModuloRepository.delete(nombreSubModulo);
		recargarSubModulo();
	}

	

	

	

}
