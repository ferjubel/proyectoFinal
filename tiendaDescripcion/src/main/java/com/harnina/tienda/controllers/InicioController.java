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

	private String idRecursoActual;
	
	@RequestMapping("/")
	public String inicio (Model model, HttpSession session) {

		cargarBienvenida(model,session);
		
		model.addAttribute("opcionesModulo" ,this.dataService.getOpcionesModulo());
		
		return "inicio_template";
	}
	
	@RequestMapping("/opcion/{idModulo}")
	public String enlaceModulo(Model model, @PathVariable String idModulo) {
		this.idModuloActual = idModulo;
		model.addAttribute("opcionesModulo" ,this.dataService.getOpcionesModulo());
		if(this.dataService.getSubModulos(this.idModuloActual).size()>0){
			model.addAttribute("subModulos", this.dataService.getSubModulos(this.idModuloActual));
			model.addAttribute("hasSubModulos", true );
		}
		return "inicio_template";
	}
	
	@RequestMapping("/opcionSubmodulo/{idSubModulo}")
	public String enlaceSubModulo(Model model, @PathVariable String idSubModulo) {
		this.idSubModuloActual = idSubModulo;
		model.addAttribute("opcionesModulo" ,this.dataService.getOpcionesModulo());
		model.addAttribute("subModulos", this.dataService.getSubModulos(this.idModuloActual));
		if(this.dataService.getRecursosEspecificos(idSubModulo).size()>0){
			model.addAttribute("hasSubModulos", true );
			model.addAttribute("hasRecursosEspecificos", true );
			model.addAttribute("recursosEspecificos", this.dataService.getRecursosEspecificos(this.idSubModuloActual));
		}
		return "inicio_template";
	}
	
	@RequestMapping("/opcionRecursoEspecifico/{idRecursoEspecifico}")
	public String enlaceRecursoEspecifico(Model model, @PathVariable String idRecursoEspecifico) {
		this.idRecursoEspecificoActual = idRecursoEspecifico;
		model.addAttribute("hasSubModulos", true );
		model.addAttribute("hasRecursosEspecificos", true );
		model.addAttribute("opcionesModulo" ,this.dataService.getOpcionesModulo());
		model.addAttribute("subModulos", this.dataService.getSubModulos(this.idModuloActual));
		model.addAttribute("recursosEspecificos", this.dataService.getRecursosEspecificos(this.idSubModuloActual));
		if(this.dataService.getRecursos(idRecursoEspecifico).size()>0){
			model.addAttribute("hasRecursos", true );
			model.addAttribute("recursos", this.dataService.getRecursos(idRecursoEspecificoActual));
		}
		return "inicio_template";
	}
	
	@RequestMapping("/opcionRecurso/{idRecurso}")
	public String enlaceRecurso(Model model, @PathVariable String idRecurso) {
		this.idRecursoActual = idRecurso;
		model.addAttribute("opcionesModulo" ,this.dataService.getOpcionesModulo());
		model.addAttribute("hasSubModulos", true );
		model.addAttribute("subModulos", this.dataService.getSubModulos(this.idModuloActual));
		model.addAttribute("hasRecursosEspecificos", true );
		model.addAttribute("recursosEspecificos", this.dataService.getRecursosEspecificos(this.idSubModuloActual));
		model.addAttribute("hasRecursos", true );
		model.addAttribute("recursos", this.dataService.getRecursos(idRecursoEspecificoActual));
		model.addAttribute("detallesRecurso", this.dataService.getRecurso(idRecursoActual, idRecursoEspecificoActual));
		return "inicio_template";
	}
	
	private void cargarBienvenida(Model model,HttpSession session) {
		model.addAttribute("bienvenida", session.isNew());
	}
	
}
