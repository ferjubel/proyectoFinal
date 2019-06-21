package com.harnina.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harnina.tienda.service.DataService;

@Controller
public class AdminController {
	
	@Autowired
	private DataService dataService;

	@RequestMapping("/adminTools")
	public String enlaceAdminTools() {
		return "adminTools_template";
	}
	
	@RequestMapping("/adminTools/modulo")
	public String adminToolsModulo() {
		return "adminToolsModulo_template";
	}
	
	@RequestMapping("/adminTools/subModulo")
	public String adminToolssubModulo() {
		return "adminToolsSubModulo_template";
	}
	
	@RequestMapping("/adminTools/recursoEspecifico")
	public String adminToolsRecursoEspecifico() {
		return "adminToolsRecursoEspecifico_template";
	}
	
	@RequestMapping("/adminTools/nombreSubModulo")
	public String adminToolsNombreSubModulo() {
		return "adminToolsNombreSubModulo_template";
	}
	
	@RequestMapping("/adminTools/nombreRecursoEspecifico")
	public String adminToolsNombreRecursoEspecifico() {
		return "adminToolsNombreRecursoEspecifico_template";
	}
	
	@RequestMapping("/adminTools/recurso")
	public String adminToolsRecurso() {
		return "adminToolsRecurso_template";
	}
	
	@RequestMapping("/adminTools/parte")
	public String adminToolsParte() {
		return "adminToolsParte_template";
	}
	
	@RequestMapping("/adminTools/asociarParte")
	public String adminToolsAsociarParte(Model model) {
		model.addAttribute("opcionesRecursoEspecifico", this.dataService.getRecursosEspecificos());
		model.addAttribute("opcionesRecurso", this.dataService.getRecursos());
		model.addAttribute("opcionesParte", this.dataService.getPartes());
		model.addAttribute("tiposParte", this.dataService.getNombrePartes());
		return "adminToolsAsociarParte_template";
	}
	
	@RequestMapping("/adminTools/diagrama")
	public String adminToolsDiagrama(Model model) {
		return "adminToolsDiagrama_template";
	}
	
	@RequestMapping("/adminTools/diagrama/asociar")
	public String adminToolsDiagramaAsociar(Model model) {
		model.addAttribute("opcionesSubModulo", this.dataService.getSubModulos());
		model.addAttribute("opcionesDiagrama", this.dataService.getDiagramas());
		return "adminToolsAsociarDiagrama_template";
	}
}
