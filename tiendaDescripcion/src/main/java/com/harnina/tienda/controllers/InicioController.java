package com.harnina.tienda.controllers;


import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InicioController {
	
	@Autowired
	private DataService cargadorOpciones;
	
	private long idModuloActual;
	private long idSubModuloActual;
	
	@RequestMapping("/")
	public String inicio (Model model, HttpSession session) {

		cargarBienvenida(model,session);
		
		model.addAttribute("opcionesModulo" ,this.cargadorOpciones.getOpcionesModulo());
		
		return "inicio_template";
	}
	
	@RequestMapping("/opcion/{idModulo}")
	public String enlaceModulo(Model model, @PathVariable String idModulo) {
		if(this.idModuloActual!=Long.valueOf(idModulo)){
			this.cargadorOpciones.recargarSubModulos(Long.valueOf(idModulo));
		}
		this.idModuloActual = Long.valueOf(idModulo);
		model.addAttribute("opcionesModulo" ,this.cargadorOpciones.getOpcionesModulo());
		model.addAttribute("subModulos", this.cargadorOpciones.getSubModulos(this.idModuloActual) );
		model.addAttribute("hasSubModulos", true );
		return "inicio_template";
	}
	
	@RequestMapping("/opcionsubmodulo/{idSubModulo}")
	public String enlaceSubModulo(Model model, @PathVariable String idSubModulo) {
		if(this.idSubModuloActual!=Long.valueOf(idSubModulo)){
			this.cargadorOpciones.recargarSubModulos(Long.valueOf(idSubModulo));
		}
		this.idSubModuloActual = Long.valueOf(idSubModulo);
		model.addAttribute("hasRecursos", true );
		model.addAttribute("hasSubModulos", true );
		model.addAttribute("opcionesModulo" ,this.cargadorOpciones.getOpcionesModulo());
		model.addAttribute("subModulos", this.cargadorOpciones.getSubModulos(this.idModuloActual));
		model.addAttribute("recursosEspecificos", this.cargadorOpciones.getRecursosEspecificos(this.idSubModuloActual));
		return "inicio_template";
	}
	
	private void cargarBienvenida(Model model,HttpSession session) {
		model.addAttribute("bienvenida", session.isNew());
	}
	
}
