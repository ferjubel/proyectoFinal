package com.harnina.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.harnina.tienda.model.NombreRecursoEspecifico;
import com.harnina.tienda.service.DataService;

@Controller
public class AdminNombreRecursoEspecificoController{
	
	@Autowired
	private DataService dataService;
	private String nombreRecursoEspecificoActual;
	
	@RequestMapping("/adminTools/nombreRecursoEspecifico/agregar")
	public String adminToolsNombreRecursoEspecificoAgregar() {
		return "adminToolsNombreRecursoEspecificoAgregar_template";
	}
	
	@RequestMapping("/adminTools/nombreRecursoEspecifico/borrar")
	public String adminToolsNombreRecursoEspecificoBorrar(Model model) {
		model.addAttribute("isBorrar","si");
		model.addAttribute("opcionesNombreRecursoEspecifico",this.dataService.getNombresRecursoEspecifico());
		return "adminToolsNombreRecursoEspecifico_template";
	}
	
	@RequestMapping("/adminTools/nombreRecursoEspecifico/editar")
	public String adminToolsNombreRecursoEspecificoEditar(Model model) {
		model.addAttribute("isEditar","si");
		model.addAttribute("opcionesNombreRecursoEspecifico",this.dataService.getNombresRecursoEspecifico());
		return "adminToolsNombreRecursoEspecifico_template";
	}
	
	@RequestMapping("/adminTools/nombreRecursoEspecifico/borrar/{idNombreRecursoEspecifico}")
	public String adminToolsNombreRecursoEspecificoBorrarNombreRecursoEspecifico(Model model,@PathVariable String idNombreRecursoEspecifico) {
		model.addAttribute("nombre" , this.dataService.getNombreRecursoEspecifico(idNombreRecursoEspecifico).getNombre());
		this.nombreRecursoEspecificoActual = idNombreRecursoEspecifico;
		return "adminToolsNombreRecursoEspecificoBorrar_template";
	}
	
	@RequestMapping("/adminTools/nombreRecursoEspecifico/editar/{idNombreRecursoEspecifico}")
	public String adminToolsModuloEditarModulo(Model model,@PathVariable String idNombreRecursoEspecifico) {
		model.addAttribute("nombre" ,this.dataService.getNombreRecursoEspecifico(idNombreRecursoEspecifico).getNombre());
		this.nombreRecursoEspecificoActual = idNombreRecursoEspecifico;
		return "adminToolsNombreRecursoEspecificoEditar_template";
	}
	
	@RequestMapping("/adminTools/nombreRecursoEspecifico/agregar/guardar")
	public String adminToolsNombreRecursoEspecificoAgregarNombreRecursoEspecificoGuardar(Model model,@RequestParam String nombre) {
		NombreRecursoEspecifico nombreRecursoEspecifico = new NombreRecursoEspecifico(nombre);
		if(this.dataService.existNombreRecursoEspecifico(nombreRecursoEspecifico)){
			model.addAttribute("mensaje" ,"El NombreRecursoEspecifico ya existe");
		}
		else{
			this.dataService.guardarNombreRecursoEspecifico(nombreRecursoEspecifico);
			model.addAttribute("mensaje" ,"NombreRecursoEspecifico guardado");
		}
		return "adminToolsNombreRecursoEspecifico_template";
	}
	
	@RequestMapping("/adminTools/nombreRecursoEspecifico/editar/guardar")
	public String adminToolsNombreRecursoEspecificoEditarNombreRecursoEspecificoGuardar(Model model,@RequestParam String nombre) {
		NombreRecursoEspecifico nombreRecursoEspecifico = this.dataService.getNombreRecursoEspecifico(this.nombreRecursoEspecificoActual);
		if(nombreRecursoEspecifico.getNombre().equals(nombre)){
			model.addAttribute("mensaje" ,"No modificado. No hay cambios");
		}
		else{
			nombreRecursoEspecifico.setNombre(nombre);
			this.dataService.guardarNombreRecursoEspecifico(nombreRecursoEspecifico);
			model.addAttribute("mensaje" ,"nombreRecursoEspecifico actualizado");
		}
		return "adminToolsNombreRecursoEspecifico_template";
	}
	
	@RequestMapping("/adminTools/nombreRecursoEspecifico/borrar/guardar")
	public String adminToolsNombreRecursoEspecificoBorrarNombreRecursoEspecificoGuardar(Model model) {
		NombreRecursoEspecifico nombreRecursoEspecifico = this.dataService.getNombreRecursoEspecifico(this.nombreRecursoEspecificoActual);
		if(!this.dataService.existNombreRecursoEspecifico(nombreRecursoEspecifico)){
			model.addAttribute("mensaje" ,"No borrado. El nombreRecursoEspecifico no existe");
		}
		else{
			try {
				this.dataService.borrarNombreRecursoEspecifico(nombreRecursoEspecifico);
				model.addAttribute("mensaje" ,"nombreRecursoEspecifico borrado");
			} catch (Exception e) {
				model.addAttribute("mensaje" ,"No se puede borrar el modulo por que hay subModulos"
						+ " utilizando ese nombre");
			}
		}
		return "adminToolsNombreRecursoEspecifico_template";
	}
	
}
