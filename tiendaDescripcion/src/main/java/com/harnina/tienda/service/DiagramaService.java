package com.harnina.tienda.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.Diagrama;
import com.harnina.tienda.repository.DiagramaRepository;

@Component
public class DiagramaService{
	
	@Autowired
	private DiagramaRepository diagramaRepository;
	
	private List<Diagrama> diagramaList;
	
	public DiagramaService() {
		super();
	}
	
	public List<Diagrama> getDiagramas() {
		comprobarLista();
		return this.diagramaList;
	}

	private void comprobarLista() {
		if(this.diagramaList==null){
			recargarDiagrama();
		}
	}
	
	private boolean existDiagrama(Diagrama diagrama) {
		return this.diagramaList.contains(diagrama);
	}
	
	public boolean guardarDiagrama(Diagrama diagrama) {
		comprobarLista();
		if(existDiagrama(diagrama))return false;
		else{
			this.diagramaRepository.save(diagrama);
			recargarDiagrama();
			return true;
		}
	}
	
	private void recargarDiagrama() {
		this.diagramaList = diagramaRepository.findAll();
	}
	
	public boolean borrarDiagrama(Diagrama diagrama) {
		comprobarLista();
		if( ! existDiagrama(diagrama))return false;
		else{
			this.diagramaRepository.delete(diagrama);
			recargarDiagrama();
			return true;
		}
	}

	public Diagrama getDiagrama(String idDiagrama) {
		comprobarLista();
		for (Diagrama diagrama : diagramaList) {
			if(diagrama.getIdDiagrama() == Long.valueOf(idDiagrama).longValue())
				return diagrama;
		}
		return null;
	}
}
