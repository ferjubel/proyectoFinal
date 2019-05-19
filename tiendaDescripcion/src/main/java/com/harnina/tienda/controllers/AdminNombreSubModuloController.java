package com.harnina.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.harnina.tienda.model.NombreSubModulo;
import com.harnina.tienda.service.DataService;

@Controller
public class AdminNombreSubModuloController{
	
	@Autowired
	private DataService dataService;
	private String nombreSubModuloActual;
	
	@RequestMapping("/adminTools/nombreSubModulo/agregar")
	public String adminToolsNombreSubModuloAgregar() {
		return "adminToolsNombreSubModuloAgregar_template";
	}
	
	@RequestMapping("/adminTools/nombreSubModulo/borrar")
	public String adminToolsNombreSubModuloBorrar(Model model) {
		model.addAttribute("isBorrar","si");
		model.addAttribute("opcionesNombreSubModulo",this.dataService.getNombresSubModulo());
		return "adminToolsNombreSubModulo_template";
	}
	
	@RequestMapping("/adminTools/nombreSubModulo/editar")
	public String adminToolsNombreSubModuloEditar(Model model) {
		model.addAttribute("isEditar","si");
		model.addAttribute("opcionesNombreSubModulo",this.dataService.getNombresSubModulo());
		return "adminToolsNombreSubModulo_template";
	}
	
	@RequestMapping("/adminTools/nombreSubModulo/borrar/{idNombreSubModulo}")
	public String adminToolsNombreSubModuloBorrarNombreSubModulo(Model model,@PathVariable String idNombreSubModulo) {
		model.addAttribute("nombre" , this.dataService.getNombreSubModulo(idNombreSubModulo).getNombre());
		this.nombreSubModuloActual = idNombreSubModulo;
		return "adminToolsNombreSubModuloBorrar_template";
	}
	
	@RequestMapping("/adminTools/nombreSubModulo/editar/{idNombreSubModulo}")
	public String adminToolsModuloEditarModulo(Model model,@PathVariable String idNombreSubModulo) {
		model.addAttribute("nombre" ,this.dataService.getNombreSubModulo(idNombreSubModulo).getNombre());
		this.nombreSubModuloActual = idNombreSubModulo;
		return "adminToolsNombreSubModuloEditar_template";
	}
	
	@RequestMapping("/adminTools/nombreSubModulo/agregar/guardar")
	public String adminToolsNombreSubModuloAgregarNombreSubModuloGuardar(Model model,@RequestParam String nombre) {
		NombreSubModulo nombreSubModulo = new NombreSubModulo(nombre);
		if(this.dataService.existNombreSubModulo(nombreSubModulo)){
			model.addAttribute("mensaje" ,"El NombreSubModulo ya existe");
		}
		else{
			this.dataService.guardarNombreSubModulo(nombreSubModulo);
			model.addAttribute("mensaje" ,"NombreSubModulo guardado");
		}
		return "adminToolsNombreSubModulo_template";
	}
	
	@RequestMapping("/adminTools/nombreSubModulo/editar/guardar")
	public String adminToolsNombreSubModuloEditarNombreSubModuloGuardar(Model model,@RequestParam String nombre) {
		NombreSubModulo nombreSubModulo = this.dataService.getNombreSubModulo(this.nombreSubModuloActual);
		if(nombreSubModulo.getNombre().equals(nombre)){
			model.addAttribute("mensaje" ,"No modificado. No hay cambios");
		}
		else{
			nombreSubModulo.setNombre(nombre);
			this.dataService.guardarNombreSubModulo(nombreSubModulo);
			model.addAttribute("mensaje" ,"nombreSubModulo actualizado");
		}
		return "adminToolsNombreSubModulo_template";
	}
	
	@RequestMapping("/adminTools/nombreSubModulo/borrar/guardar")
	public String adminToolsNombreSubModuloBorrarNombreSubModuloGuardar(Model model) {
		NombreSubModulo nombreSubModulo = this.dataService.getNombreSubModulo(this.nombreSubModuloActual);
		if(!this.dataService.existNombreSubModulo(nombreSubModulo)){
			model.addAttribute("mensaje" ,"No borrado. El nombreSubModulo no existe");
		}
		else{
			try {
				this.dataService.borrarNombreSubModulo(nombreSubModulo);
				model.addAttribute("mensaje" ,"nombreSubModulo borrado");
			} catch (Exception e) {
				model.addAttribute("mensaje" ,"No se puede borrar el modulo por que hay subModulos"
						+ " utilizando ese nombre");
			}
		}
		return "adminToolsNombreSubModulo_template";
	}
	
}
