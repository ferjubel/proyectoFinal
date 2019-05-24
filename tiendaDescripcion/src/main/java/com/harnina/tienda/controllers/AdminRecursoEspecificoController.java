package com.harnina.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.harnina.tienda.model.RecursoEspecifico;
import com.harnina.tienda.service.DataService;

@Controller
public class AdminRecursoEspecificoController{
	
	@Autowired
	private DataService dataService;
	private String idRecursoEspecificoActual;
	
	@RequestMapping("/adminTools/recursoEspecifico/agregar")
	public String adminToolsRecursoEspecificoAgregar(Model model) {
		model.addAttribute("opcionesSubModulo", this.dataService.getSubModulos());
		model.addAttribute("opcionesNombreRecursoEspecifico", this.dataService.getNombresRecursoEspecifico());
		return "adminToolsRecursoEspecificoAgregar_template";
	}
	
	@RequestMapping("/adminTools/recursoEspecifico/borrar")
	public String adminToolsRecursoEspecificoBorrar(Model model) {
		model.addAttribute("isBorrar","si");
		model.addAttribute("opcionesRecursoEspecifico",this.dataService.getRecursosEspecificos());
		return "adminToolsRecursoEspecifico_template";
	}
	
	@RequestMapping("/adminTools/recursoEspecifico/editar")
	public String adminToolsRecursoEspecificoEditar(Model model) {
		model.addAttribute("isEditar","si");
		model.addAttribute("opcionesRecursoEspecifico",this.dataService.getRecursosEspecificos());
		return "adminToolsRecursoEspecifico_template";
	}
	
	@RequestMapping("/adminTools/recursoEspecifico/borrar/{idRecursoEspecifico}")
	public String adminToolsRecursoEspecificoBorrarRecursoEspecifico(Model model,@PathVariable String idRecursoEspecifico) {
		model.addAttribute("nombre" , this.dataService.getRecursoEspecifico(idRecursoEspecifico).getNombreRecursoEspecifico().getNombre());
		this.idRecursoEspecificoActual = idRecursoEspecifico;
		return "adminToolsRecursoEspecificoBorrar_template";
	}
	
	@RequestMapping("/adminTools/recursoEspecifico/editar/{idRecursoEspecifico}")
	public String adminToolsModuloEditarModulo(Model model,@PathVariable String idRecursoEspecifico) {
		model.addAttribute("nombre" ,this.dataService.getRecursoEspecifico(idRecursoEspecifico).getNombreRecursoEspecifico().getNombre());
		model.addAttribute("opcionesSubModulo", this.dataService.getSubModulos());
		model.addAttribute("opcionesNombreRecursoEspecifico", this.dataService.getNombresRecursoEspecifico());
		model.addAttribute("recursoEspecifico", this.dataService.getRecursoEspecifico(idRecursoEspecifico));
		this.idRecursoEspecificoActual = idRecursoEspecifico;
		return "adminToolsRecursoEspecificoEditar_template";
	}
	
	@RequestMapping("/adminTools/recursoEspecifico/agregar/guardar")
	public String adminToolsRecursoEspecificoAgregarRecursoEspecificoGuardar(Model model,
			@RequestParam String idSubModulo,@RequestParam String idNombreRecursoEspecifico) {
		RecursoEspecifico recursoEspecifico = new RecursoEspecifico(this.dataService.getSubModulo(idSubModulo),
				this.dataService.getNombreRecursoEspecifico(idNombreRecursoEspecifico));
		if(this.dataService.existRecursoEspecifico(recursoEspecifico)){
			model.addAttribute("mensaje" ,"El RecursoEspecifico ya existe");
		}
		else{
			this.dataService.guardarRecursoEspecifico(recursoEspecifico);
			model.addAttribute("mensaje" ,"RecursoEspecifico guardado");
		}
		return "adminToolsRecursoEspecifico_template";
	}
	
	@RequestMapping("/adminTools/recursoEspecifico/editar/guardar")
	public String adminToolsRecursoEspecificoEditarRecursoEspecificoGuardar(Model model ,
			@RequestParam String idSubModulo,@RequestParam String idNombreRecursoEspecifico) {
		RecursoEspecifico recursoEspecifico = this.dataService.getRecursoEspecifico(this.idRecursoEspecificoActual);
		if(recursoEspecifico.getSubModulo().getIdSubModulo() == Long.valueOf(idSubModulo).longValue() && 
				recursoEspecifico.getNombreRecursoEspecifico().getIdNombreRecursoEspecifico() == Long.valueOf(idNombreRecursoEspecifico).longValue()){
			model.addAttribute("mensaje" ,"No modificado. No hay cambios");
		}
		else{
			recursoEspecifico.setNombreRecursoEspecifico(this.dataService.getNombreRecursoEspecifico(idNombreRecursoEspecifico));
			recursoEspecifico.setSubModulo(this.dataService.getSubModulo(idSubModulo));
			this.dataService.guardarRecursoEspecifico(recursoEspecifico);
			model.addAttribute("mensaje" ,"recursoEspecifico actualizado");
		}
		return "adminToolsRecursoEspecifico_template";
	}
	
	@RequestMapping("/adminTools/recursoEspecifico/borrar/guardar")
	public String adminToolsRecursoEspecificoBorrarRecursoEspecificoGuardar(Model model) {
		RecursoEspecifico recursoEspecifico = this.dataService.getRecursoEspecifico(this.idRecursoEspecificoActual);
		if(!this.dataService.existRecursoEspecifico(recursoEspecifico)){
			model.addAttribute("mensaje" ,"No borrado. El recursoEspecifico no existe");
		}
		else{
			try {
				this.dataService.borrarRecursoEspecifico(recursoEspecifico);
				model.addAttribute("mensaje" ,"recursoEspecifico borrado");
			} catch (Exception e) {
				model.addAttribute("mensaje" ,"No se puede borrar el modulo por que hay elementos asociados a este recurso");
			}
		}
		return "adminToolsRecursoEspecifico_template";
	}
	
}
