package com.harnina.tienda.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.Recurseable;
import com.harnina.tienda.model.Tabla;
import com.harnina.tienda.model.VistaJsp;
import com.harnina.tienda.model.Diagrama;
import com.harnina.tienda.model.FuncionProcedureMetodo;

@Component
public class RecursoService implements Serviceable{
	
	List <RecursoServiceable> serviceLists;
	
	List<Recurseable> recursos;
	
	@Autowired
	private FuncionProcedureMetodoService funcionProcedureMetodoService;
	
	@Autowired
	private TablaService tablaService;
	
	@Autowired
	private VistaJspService vistaJspService;
	
	public RecursoService() {
	}

	public List<Recurseable> getRecursos(long idRecursoEspecifico) {
		comprobarListas();
		List<Recurseable> retorno = new ArrayList<>();
		for (RecursoServiceable servicioRecurso : serviceLists) {
			retorno.addAll(servicioRecurso.getRecursos(idRecursoEspecifico));
		}
		return retorno;
	}

	public List<Recurseable> getRecursos() {
		comprobarListas();
		List<Recurseable> retorno = new ArrayList<>();
		for (RecursoServiceable servicioRecurso : serviceLists) {
			retorno.addAll(servicioRecurso.getRecursos());
		}
		return retorno;
	}
	
	private void comprobarListas() {
		if(this.serviceLists == null) iniciarListaServicios();
		if(this.recursos == null) recargarRecursos();
	}

	private void recargarRecursos() {
		this.recursos = new ArrayList<>();
		comprobarListas();
		for (RecursoServiceable recurso : serviceLists) {
			this.recursos.addAll(recurso.getRecursos());
		}
	}

	private void iniciarListaServicios() {
		this.serviceLists = new ArrayList<>();
		this.serviceLists.add(funcionProcedureMetodoService);
		this.serviceLists.add(tablaService);
		this.serviceLists.add(vistaJspService);
	}
	
	public boolean existRecurso(Recurseable recurso) {
		comprobarListas();
		for (Recurseable recurseable : recursos) {
			if(recurseable.equals(recurso))return true;
		}
		return false;
	}
	
	public void guardarRecurso(Recurseable recurso) {
		comprobarListas();
		recurso.guardar(this);
		recargarRecursos();
	}

	public void borrarRecurso(Recurseable recurso) {
		recurso.borrar(this);
		recargarRecursos();
	}
	
	public void guardarRecurso(Tabla tabla) {
		comprobarListas();
		tablaService.guardarRecurso(tabla);
		recargarRecursos();
	}
	
	public void guardarRecurso(FuncionProcedureMetodo funcionProcedureMetodo) {
		comprobarListas();
		funcionProcedureMetodoService.guardarRecurso(funcionProcedureMetodo);
		recargarRecursos();
	}
	
	public void guardarRecurso(VistaJsp vistaJsp) {
		comprobarListas();
		vistaJspService.guardarRecurso(vistaJsp);
		recargarRecursos();
	}

	public Recurseable getRecurso(String idRecurso,String idRecursoEspecifico) {
		comprobarListas();
		for (Recurseable recurseable : recursos) {
			if(recurseable.getIdRecurso() == Long.valueOf(idRecurso).longValue() && 
			recurseable.getIdRecursoEspecifico() == Long.valueOf(idRecursoEspecifico)){
				return recurseable;
			}
		}
		return null;
	}

	public Recurseable getRecurso(long idRecurso, long idRecursoEspecifico) {
		return getRecurso(String.valueOf(idRecurso) , String.valueOf(idRecursoEspecifico));
	}

	public void asociarDiagramaVista(Diagrama diagrama, String idVista) {
		this.vistaJspService.asociarDiagrama(diagrama,idVista);
	}
	
	@Override
	public Thread cargarDatosEnRam() {
		Thread hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				recargarRecursos();
			}
		});
		hilo.setName("recurso");
		return hilo;
	}

}
