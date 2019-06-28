package com.harnina.tienda.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.harnina.tienda.service.DataService;

@Controller
public class InicioController {
	
	@Autowired
	private DataService dataService;
	
	private String idModuloActual;
	private String idSubModuloActual;
	private String idRecursoEspecificoActual;
	
	@RequestMapping("/")
	public String inicio (Model model, HttpSession session) {
		//cargarBienvenida(model,session);
		comprobarSiDatosYaEnMemoria("modulo");
		addModuloList(model);
		return "inicio_template";
	}

	@RequestMapping("/opcion/{idModulo}")
	public String enlaceModulo(Model model, @PathVariable String idModulo) {
		this.idModuloActual = idModulo;
		addModuloList(model);
		addModuloAModel(model, idModulo);
		comprobarSiDatosYaEnMemoria("subModulo");
		if(hasSubModulos(idModulo))addSubModulosList(model,idModulo);
		return "inicio_template";
	}

	@RequestMapping("/opcionSubmodulo/{idSubModulo}")
	public String enlaceSubModulo(Model model, @PathVariable String idSubModulo) {
		this.idSubModuloActual = idSubModulo;
		addModuloList(model);
		addModuloAModel(model);
		addSubModulosList(model,idSubModulo);
		addSubModuloAModel(model,idSubModulo);
		addDiagramasAModel(model, idSubModulo);
		comprobarSiDatosYaEnMemoria("recursoEspecifico");
		if(hasRecursosEspecificos(idSubModulo))addRecursosEspecificosList(model,idSubModulo);
		return "inicio_template";
	}

	@RequestMapping("/opcionRecursoEspecifico/{idRecursoEspecifico}")
	public String enlaceRecursoEspecifico(Model model, @PathVariable String idRecursoEspecifico) {
		this.idRecursoEspecificoActual = idRecursoEspecifico;
		addModuloList(model);
		addModuloAModel(model);
		addSubModulosList(model);
		addSubModuloAModel(model);
		addRecursosEspecificosList(model);
		addRecursoEspecificoAModel(model, idRecursoEspecifico);
		comprobarSiDatosYaEnMemoria("recurso");
		if(hasRecursos(idRecursoEspecifico))addRecursoList(model,idRecursoEspecifico);
		return "inicio_template";
	}

	
	@RequestMapping("/opcionRecurso/{idRecurso}")
	public String enlaceRecurso(Model model, @PathVariable String idRecurso) {
		addModuloList(model);
		addModuloAModel(model);
		addSubModulosList(model);
		addSubModuloAModel(model);
		addRecursosEspecificosList(model);
		addRecursoEspecificoAModel(model);
		addRecursoList(model);
		addRecursosAModel(model,idRecurso);
		comprobarSiDatosYaEnMemoria("partes");
		addPartesDeRecursoAModel(model, idRecurso);
		return "inicio_template";
	}

	
	
	private void addModuloList(Model model){
		model.addAttribute("opcionesModulo" ,this.dataService.getOpcionesModulo());
	}
	
	private void addModuloAModel(Model model) {
		addModuloAModel(model,idModuloActual);
	}
	
	private void addModuloAModel(Model model, String idModulo) {
		model.addAttribute("moduloActual", this.dataService.getModulo(idModulo));
	}

	
	
	
	private void addSubModulosList(Model model) {
		addSubModulosList(model, idModuloActual);
	}
	
	private void addSubModulosList(Model model,String idSubModulo) {
		model.addAttribute("hasSubModulos", true );
		model.addAttribute("subModulos", this.dataService.getSubModulos(this.idModuloActual));
	}
	
	private void addSubModuloAModel(Model model) {
		addSubModuloAModel(model, idSubModuloActual);
	}
	
	private void addSubModuloAModel(Model model,String idSubModulo) {
		model.addAttribute("subModuloActual", this.dataService.getSubModulo(idSubModulo));
	}
	
	private boolean hasSubModulos(String idModulo) {
		return this.dataService.getSubModulos(idModulo).size()>0;
	}
	
	
	
	private void addRecursosEspecificosList(Model model) {
		addRecursosEspecificosList(model, idSubModuloActual);
	}
	
	private void addRecursosEspecificosList(Model model, String idSubModulo) {
		model.addAttribute("hasRecursosEspecificos", true);
		model.addAttribute("recursosEspecificos", this.dataService.getRecursosEspecificos(idSubModulo));
	}
	
	private void addRecursoEspecificoAModel(Model model) {
		addRecursoEspecificoAModel(model, idRecursoEspecificoActual);
	}
	
	private void addRecursoEspecificoAModel(Model model,String idRecursoEspecifico) {
		model.addAttribute("recursoEspecificoActual", this.dataService.getRecursoEspecifico(idRecursoEspecifico));
	}
	
	private boolean hasRecursosEspecificos(String idSubModulo) {
		return this.dataService.getRecursosEspecificos(idSubModulo).size()>0;
	}
	
	


	private void addRecursoList(Model model) {
		addRecursoList(model, idRecursoEspecificoActual);
	}
	
	private void addRecursoList(Model model,String idRecursoEspecifico) {
		model.addAttribute("hasRecursos", true );
		model.addAttribute("recursos", this.dataService.getRecursos(idRecursoEspecifico));
	}
	
	private void addRecursosAModel(Model model, String idRecurso){ 		
		model.addAttribute("rercursoActual", this.dataService.getRecurso(idRecurso, idRecursoEspecificoActual));
	}
	
	private boolean hasRecursos(String idRecursoEspecifico) {
		return this.dataService.getRecursos(idRecursoEspecifico).size()>0;
	}


	
	
	private void addPartesDeRecursoAModel(Model model, String idRecurso) {
		model.addAttribute("hasParametros", this.dataService.hasParametros(idRecurso, idRecursoEspecificoActual));
		model.addAttribute("hasColumnas", this.dataService.hasColumnas(idRecurso, idRecursoEspecificoActual) );
		model.addAttribute("hasClaves", this.dataService.hasClaves(idRecurso, idRecursoEspecificoActual) );
		model.addAttribute("detallesRecurso", this.dataService.getRecurso(idRecurso, idRecursoEspecificoActual));
	}
	
	
	
	
	private void comprobarSiDatosYaEnMemoria(String nombreModulo) {
		while(isCargandoDatosDe(nombreModulo));
	}
	
	private boolean isCargandoDatosDe(String nombreHilo){
		return this.dataService.datosListos(nombreHilo).isAlive();
	}
	
	private void addDiagramasAModel(Model model, String idSubModulo) {
		model.addAttribute("diagramas" , this.dataService.getDiagramas(idSubModulo));
	}
		
	
	/*private void cargarBienvenida(Model model,HttpSession session) {
		model.addAttribute("bienvenida", session.isNew());
	}*/
	
}
