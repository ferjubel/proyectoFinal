package com.harnina.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.harnina.tienda.model.SubModulo;
import com.harnina.tienda.service.DataService;

@Controller
public class AdminSubModuloController{
	
	@Autowired
	private DataService dataService;
	private String idSubModuloActual;
	
	@RequestMapping("/adminTools/subModulo/agregar")
	public String adminToolsSubModuloAgregar(Model model) {
		model.addAttribute("opcionesModulo", this.dataService.getOpcionesModulo());
		model.addAttribute("opcionesNombresSubModulo", this.dataService.getNombresSubModulo());
		return "adminToolsSubModuloAgregar_template";
	}
	
	@RequestMapping("/adminTools/subModulo/borrar")
	public String adminToolsSubModuloBorrar(Model model) {
		model.addAttribute("isBorrar","si");
		model.addAttribute("opcionesSubModulo",this.dataService.getSubModulos());
		return "adminToolsSubModulo_template";
	}
	
	@RequestMapping("/adminTools/subModulo/editar")
	public String adminToolsSubModuloEditar(Model model) {
		model.addAttribute("isEditar","si");
		model.addAttribute("opcionesSubModulo",this.dataService.getSubModulos());
		return "adminToolsSubModulo_template";
	}
	
	@RequestMapping("/adminTools/subModulo/borrar/{idSubModulo}")
	public String adminToolsSubModuloBorrarSubModulo(Model model,@PathVariable String idSubModulo) {
		model.addAttribute("nombre" , this.dataService.getSubModulo(idSubModulo).getNombreSubModulo().getNombre());
		this.idSubModuloActual = idSubModulo;
		return "adminToolsSubModuloBorrar_template";
	}
	
	@RequestMapping("/adminTools/subModulo/agregar/guardar")
	public String adminToolsSubModuloAgregarSubModuloGuardar(Model model,@RequestParam String idNombreSubModulo,
			@RequestParam String idModulo, @RequestParam String descripcion) {
		SubModulo subModulo = new SubModulo(dataService.getNombreSubModulo(idNombreSubModulo),dataService.getModulo(idModulo),descripcion);
		if(this.dataService.existSubModulo(subModulo)){
			model.addAttribute("mensaje" ,"El SubModulo ya existe");
		}
		else{
			this.dataService.guardarSubModulo(subModulo);
			model.addAttribute("mensaje" ,"SubModulo guardado");
		}
		return "adminTools_template";
	}
	
	@RequestMapping("/adminTools/subModulo/editar/{idSubModulo}")
	public String adminToolsSubModuloEditarSubModulo(Model model,@PathVariable String idSubModulo) {
		model.addAttribute("opcionesModulo", this.dataService.getOpcionesModulo());
		model.addAttribute("opcionesNombresSubModulo", this.dataService.getNombresSubModulo());
		model.addAttribute("opcionModuloSelected", this.dataService.getSubModulo(idSubModulo).getModulo().getNombre());
		model.addAttribute("opcionSubModuloSelected", this.dataService.getSubModulo(idSubModulo).getNombreSubModulo().getNombre());
		this.idSubModuloActual = idSubModulo;
		return "adminToolsSubModuloEditar_template";
	}
	
	@RequestMapping("/adminTools/subModulo/editar/guardar")
	public String adminToolsSubModuloEditarSubModuloGuardar(Model model,@RequestParam String idNombreSubModulo,
			@RequestParam String idModulo) {
		SubModulo subModulo = this.dataService.getSubModulo(this.idSubModuloActual);
		if(String.valueOf(subModulo.getNombreSubModulo().getIdNombreSubModulo()).equals(idNombreSubModulo) &&
				String.valueOf(subModulo.getModulo().getIdModulo()).equals(idModulo)){
			model.addAttribute("mensaje" ,"No modificado. No hay cambios");
		}
		else{
			subModulo.setNombreSubModulo(this.dataService.getNombreSubModulo(idNombreSubModulo));
			subModulo.setModulo(this.dataService.getModulo(idModulo));
			this.dataService.guardarSubModulo(subModulo);
			model.addAttribute("mensaje" ,"SubModulo actualizado");
		}
		return "adminTools_template";
	}
	
	@RequestMapping("/adminTools/subModulo/borrar/guardar")
	public String adminToolsSubModuloBorrarSubModuloGuardar(Model model) {
		SubModulo subModulo = this.dataService.getSubModulo(this.idSubModuloActual);
		if(!this.dataService.existSubModulo(subModulo)){
			model.addAttribute("mensaje" ,"No borrado. El SubModulo no existe");
		}
		else{
			this.dataService.borrarSubModulo(subModulo);
			model.addAttribute("mensaje" ,"SubModulo borrado");
		}
		return "adminTools_template";
	}
	
}
