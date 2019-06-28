package com.harnina.tienda.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.Clave;
import com.harnina.tienda.model.Columna;
import com.harnina.tienda.model.FuncionProcedureMetodo;
import com.harnina.tienda.model.Parametro;
import com.harnina.tienda.model.Parteable;
import com.harnina.tienda.model.Recurseable;
import com.harnina.tienda.model.Tabla;

@Component
public class ParteService implements Serviceable{

	List <ParteServiceable> serviceLists;
	
	List<Parteable> partes;
	
	@Autowired
	private ParametroService parametroService;
	
	@Autowired
	private ColumnaService columnaService;
	
	@Autowired
	private RecursoService recursoService;

	@Autowired
	private ClaveService claveService;
	
	public ParteService() {
	}
	
	public List<Parteable> getPartes() {
		comprobarListas();
		List<Parteable> retorno = new ArrayList<>();
		for (ParteServiceable servicioParte : serviceLists) {
			retorno.addAll(servicioParte.getPartes());
		}
		return retorno;
	}
	
	private void comprobarListas() {
		if(this.serviceLists == null) iniciarListaServicios();
		if(this.partes == null) recargarPartes();
	}

	private void recargarPartes() {
		this.partes = new ArrayList<>();
		comprobarListas();
		for (ParteServiceable servicioParte : serviceLists) {
			this.partes.addAll(servicioParte.getPartes());
		}
	}

	private void iniciarListaServicios() {
		this.serviceLists = new ArrayList<>();
		this.serviceLists.add(parametroService);
		this.serviceLists.add(columnaService);
		this.serviceLists.add(claveService);
	}
	
	public boolean existParte(Parteable parte) {
		comprobarListas();
		for (Parteable parteTmp : partes) {
			if(parteTmp.equals(parte))return true;
		}
		return false;
	}
	
	public void guardarParte(Parteable parte) {
		comprobarListas();
		if(!existParte(parte)){
			parte.guardar(this);
			recargarPartes();
		}
	}

	public void borrarParte(Parteable parte) {
		if(existParte(parte)){
			parte.borrar(this);
			recargarPartes();
		}
	}
	
	public void guardarParte(Parametro parametro) {
		comprobarListas();
		parametroService.guardarParametro(parametro);
		recargarPartes();
	}
	
	public void guardarParte(Columna columna) {
		comprobarListas();
		columnaService.guardarColumna(columna);
		recargarPartes();
	}
	
	public void guardarParte(Clave clave) {
		comprobarListas();
		claveService.guardarClave(clave);
		recargarPartes();
	}

	public Parteable getParte(String idParte, String nombreParte,String tipoParte) {
		comprobarListas();
		for (Parteable parte : partes) {
			if(parte.getIdParte() == Long.valueOf(idParte) && 
					parte.getNombre().equals(nombreParte) && 
					parte.getClass().getSimpleName().equalsIgnoreCase(tipoParte)) return parte;
		}
		return null;
	}

	public void asociarParte(Recurseable recurso, Parteable parte) {
		recurso.asociarParte(this , parte);
	}
	
	public void asociarParte(FuncionProcedureMetodo recurso, Parametro parte) {
		comprobarListas();
		FuncionProcedureMetodo recursoTemp = (FuncionProcedureMetodo) this.recursoService.getRecurso(recurso.getIdRecurso(), recurso.getIdRecursoEspecifico());
		recursoTemp.getParametros().add(parte);
		recursoService.guardarRecurso(recursoTemp);
		recargarPartes();
	}
	
	public void asociarParte(Tabla recurso, Columna parte) {
		comprobarListas();
		Tabla tablaTemp = (Tabla) this.recursoService.getRecurso(recurso.getIdRecurso(), recurso.getIdRecursoEspecifico());
		tablaTemp.getColumnas().add(parte);
		recursoService.guardarRecurso(tablaTemp);
		recargarPartes();
	}
	
	public void asociarParte(Tabla recurso, Clave parte) {
		comprobarListas();
		Tabla tablaTemp = (Tabla) this.recursoService.getRecurso(recurso.getIdRecurso(), recurso.getIdRecursoEspecifico());
		tablaTemp.getClaves().add(parte);
		recursoService.guardarRecurso(tablaTemp);
		recargarPartes();
	}
	
	@Override
	public Thread cargarDatosEnRam() {
		Thread hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				recargarPartes();
			}
		});
		hilo.setName("partes");
		return hilo;
	}

}
