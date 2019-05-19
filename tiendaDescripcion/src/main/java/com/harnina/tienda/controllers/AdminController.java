package com.harnina.tienda.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
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
	
}
