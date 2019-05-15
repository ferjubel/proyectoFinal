package com.harnina.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.harnina.tienda.model.Modulo;

@Controller
public class AdminModuloController{
	
	@Autowired
	private DataService dataService;
	private long idModuloActual;
	
	@RequestMapping("/adminTools/modulo/agregar")
	public String adminToolsModuloAgregar() {
		return "adminToolsModuloAgregar_template";
	}
	
	@RequestMapping("/adminTools/modulo/borrar")
	public String adminToolsModuloBorrar(Model model) {
		model.addAttribute("isBorrar","si");
		model.addAttribute("opcionesModulo",this.dataService.getOpcionesModulo());
		return "adminToolsModulo_template";
	}
	
	@RequestMapping("/adminTools/modulo/editar")
	public String adminToolsModuloEditar(Model model) {
		model.addAttribute("isEditar","si");
		model.addAttribute("opcionesModulo",this.dataService.getOpcionesModulo());
		return "adminToolsModulo_template";
	}
	
	@RequestMapping("/adminTools/modulo/borrar/{idModulo}")
	public String adminToolsModuloBorrarModulo(Model model,@PathVariable String idModulo) {
		model.addAttribute("nombre" , this.dataService.getModulo(Long.valueOf(idModulo)).getNombre());
		this.idModuloActual = Long.valueOf(idModulo);
		return "adminToolsModuloBorrar_template";
	}
	
	@RequestMapping("/adminTools/modulo/editar/{idModulo}")
	public String adminToolsModuloEditarModulo(Model model,@PathVariable String idModulo) {
		model.addAttribute("nombre" ,this.dataService.getModulo(Long.valueOf(idModulo)).getNombre());
		this.idModuloActual = Long.valueOf(idModulo);
		return "adminToolsModuloEditar_template";
	}
	
	@RequestMapping("/adminTools/modulo/agregar/guardar")
	public String adminToolsModuloAgregarModuloGuardar(Model model,@RequestParam String nombre) {
		Modulo modulo = new Modulo(nombre);
		if(this.dataService.existModulo(modulo)){
			model.addAttribute("mensaje" ,"El modulo ya existe");
		}
		else{
			this.dataService.guardarModulo(modulo);
			model.addAttribute("mensaje" ,"modulo guardado");
		}
		model.addAttribute("opcionesModulo" ,this.dataService.getOpcionesModulo());
		return "adminToolsModulo_template";
	}
	
	@RequestMapping("/adminTools/modulo/editar/guardar")
	public String adminToolsModuloEditarModuloGuardar(Model model,@RequestParam String nombre) {
		Modulo modulo = this.dataService.getModulo(this.idModuloActual);
		if(modulo.getNombre().equals(nombre)){
			model.addAttribute("mensaje" ,"No modificado. No hay cambios");
		}
		else{
			modulo.setNombre(nombre);
			this.dataService.guardarModulo(modulo);
			model.addAttribute("mensaje" ,"modulo actualizado");
		}
		model.addAttribute("opcionesModulo" ,this.dataService.getOpcionesModulo());
		return "adminToolsModulo_template";
	}
	
	@RequestMapping("/adminTools/modulo/borrar/guardar")
	public String adminToolsModuloBorrarModuloGuardar(Model model) {
		Modulo modulo = this.dataService.getModulo(this.idModuloActual);
		if(!this.dataService.existModulo(modulo)){
			model.addAttribute("mensaje" ,"No borrado. El modulo no existe");
		}
		else{
			this.dataService.borrarModulo(modulo);
			model.addAttribute("mensaje" ,"modulo borrado");
		}
		model.addAttribute("opcionesModulo" ,this.dataService.getOpcionesModulo());
		return "adminToolsModulo_template";
	}
	
}
