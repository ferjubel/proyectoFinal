package com.harnina.tienda.controllers;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.harnina.tienda.model.Parametro;
import com.harnina.tienda.model.Parteable;
import com.harnina.tienda.model.Recurseable;
import com.harnina.tienda.service.DataService;

@Controller
public class AdminParteController{
	
	@Autowired
	private DataService dataService;
	
	private String idParteActual;
	private String nombreParteActual;
	
	@RequestMapping("/adminTools/parte/agregar")
	public String adminToolsParteAgregar(Model model) {
		model.addAttribute("isAgregar","si");
		model.addAttribute("opcionesParte", this.dataService.getNombrePartes());
		return "adminToolsParte_template";
	}
	
	@RequestMapping("/adminTools/parte/borrar")
	public String adminToolsParteBorrar(Model model) {
		model.addAttribute("isBorrar","si");
		model.addAttribute("opcionesParte",this.dataService.getPartes());
		return "adminToolsParte_template";
	}
	
	@RequestMapping("/adminTools/parte/editar")
	public String adminToolsParteEditar(Model model) {
		model.addAttribute("isEditar","si");
		model.addAttribute("opcionesParte",this.dataService.getPartes());
		return "adminToolsParte_template";
	}
	
	@RequestMapping("/adminTools/parte/agregar/{tipoParte}")
	public String adminToolsRecursoAgregarParte(Model model,@PathVariable String tipoParte) {
		this.nombreParteActual = tipoParte;
		return getPlantilla(tipoParte, "Agregar");
	}
	
	@RequestMapping("/adminTools/parte/editar/{tipoParte}")
	public String adminToolsModuloEditarModulo(Model model,
		@RequestParam String idParte,@RequestParam String nombreParte) {
		
		model.addAttribute("parte", this.dataService.getParte(idParte,nombreParte));
		this.idParteActual = idParte;
		return getPlantilla(this.dataService.getParte(idParte,nombreParte).getNombre(),"Editar");
	}
	
	@RequestMapping("/adminTools/parte/borrar/{tipoParte}")
	public String adminToolsParteBorrarParte(Model model,
			@RequestParam String idParte,@RequestParam String nombreParte) {
		model.addAttribute("parte", this.dataService.getParte(idParte,nombreParte));
		this.idParteActual = idParte;
		return "adminToolsParteBorrar_template";
	}
	
	@RequestMapping("/adminTools/parte/agregar/parametro/guardar")
	public String adminToolsParametroAgregarParametroGuardar(Model model,Parametro parametro) {
		parametro.setFuncionProcedureMetodo(new ArrayList<>());
		this.dataService.guardarParte(parametro);
		model.addAttribute("mensaje" ,"parametro guardado");
		return "adminToolsParte_template";
	}

	@RequestMapping("/adminTools/parte/editar/guardarParametro")
	public String adminToolsParteEditarParteGuardar(Model model ,Parametro parametro) {
		this.dataService.guardarParte(parametro);
		model.addAttribute("mensaje" ,"parametro modificado");
		return "adminToolsParte_template";
	}
	
	@RequestMapping("/adminTools/parte/borrar/guardar")
	public String adminToolsParteBorrarParteGuardar(Model model) {
		Parteable parte = this.dataService.getParte(this.idParteActual,this.nombreParteActual);
		if(!this.dataService.existParte(parte)){
			model.addAttribute("mensaje" ,"No borrado. La parte no existe");
		}
		else{
			try {
				this.dataService.borrarParte(parte);
				model.addAttribute("mensaje" ,"parte borrado");
			} catch (Exception e) {
				model.addAttribute("mensaje" ,"No se puede borrar el Parte por que hay elementos asociados a este parte");
			}
		}
		return "adminToolsParte_template";
	}
	
	
	@RequestMapping("/adminTools/asociarParte/guardar")
	public String adminToolsAsociarParteGuardar(Model model,@RequestParam String idParte,@RequestParam String idRecurso,
			@RequestParam String idRecursoEspecifico) {
		Recurseable recurso = dataService.getRecurso(idRecurso, idRecursoEspecifico);
		Parteable parteable = dataService.getParte(idParte.substring(0,idParte.indexOf(',')), idParte.substring(idParte.indexOf(',')+1));
		dataService.asociarParte(recurso, parteable);
		model.addAttribute("mensaje" ,"parte asociada");
		return "adminToolsParte_template";
	}
	
	
	private String getPlantilla(String nombreParte,String accion){
		nombreParte = new String(nombreParte.replace(nombreParte.charAt(0),Character.toUpperCase(nombreParte.charAt(0))));
		return "adminTools"+nombreParte+accion+"_template";
	}
	
}
