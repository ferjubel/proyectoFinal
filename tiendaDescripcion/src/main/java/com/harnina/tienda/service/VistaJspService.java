package com.harnina.tienda.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.Diagrama;
import com.harnina.tienda.model.Recurseable;
import com.harnina.tienda.model.VistaJsp;
import com.harnina.tienda.repository.VistaJspRepository;

@Component
public class VistaJspService implements RecursoServiceable{
	
	@Autowired
	private VistaJspRepository vistaJspRepository;
	
	private List<VistaJsp> vistaJspList;
	
	public VistaJspService() {
		super();
	}
	
	@Override
	public List<Recurseable> getRecursos(long idRecursoEspecifico) {
		comprobarLista();
		List<Recurseable> retorno = new ArrayList<>();
		for (VistaJsp vistaJsp : this.vistaJspList) {
			if(vistaJsp.getRecursoEspecifico().getIdRecursoEspecifico() == idRecursoEspecifico){
				retorno.add(vistaJsp);
			}
		}
		return retorno;
	}

	@Override
	public List<Recurseable> getRecursos() {
		comprobarLista();
		List<Recurseable> retorno = new ArrayList<>();
		retorno.addAll(this.vistaJspList);
		return retorno;
	}

	private void comprobarLista() {
		if(this.vistaJspList==null){
			recargarVistaJsp();
		}
	}
	
	public void guardarRecurso(VistaJsp vistaJsp) {
		this.vistaJspRepository.save(vistaJsp);
		recargarVistaJsp();
	}
	
	private void recargarVistaJsp() {
		this.vistaJspList = vistaJspRepository.findAll();
	}
	
	public void borrarRecurso(VistaJsp nombre) {
		this.vistaJspRepository.delete(nombre);
		recargarVistaJsp();
	}

	public void asociarDiagrama(Diagrama diagrama, String idVista) {
		VistaJsp vistaJsp = getVistaJsp(idVista);
		vistaJsp.getDiagrama().add(diagrama);
		guardarRecurso(vistaJsp);
	}
	
	public VistaJsp getVistaJsp(String idVista) {
		return getVistaJsp(Long.valueOf(idVista));
	}
	
	public VistaJsp getVistaJsp(long idVista) {
		if(this.vistaJspList==null)recargarVistaJsp();
		for (VistaJsp vistaJsp : this.vistaJspList) {
			if(vistaJsp.getIdRecurso() == idVista){
				return vistaJsp;
			}
		}
		return null;
	}
}
