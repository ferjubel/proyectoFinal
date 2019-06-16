package com.harnina.tienda.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.harnina.tienda.model.FuncionProcedureMetodo;
import com.harnina.tienda.model.Parametro;
import com.harnina.tienda.model.Recurseable;
import com.harnina.tienda.model.Tabla;
import com.harnina.tienda.service.DataService;
import com.harnina.tienda.service.UploadFileService;

@Controller
@MultipartConfig
public class AdminRecursoController{
	
	@Autowired
	private UploadFileService uploadFileService;
	
	@Autowired
	private DataService dataService;
	
	private String idRecursoActual;
	private String idRecursoEspecificoActual;
	
	@RequestMapping("/adminTools/recurso/agregar")
	public String adminToolsRecursoAgregar(Model model) {
		model.addAttribute("isAgregar","si");
		model.addAttribute("opcionesRecursoEspecifico", this.dataService.getRecursosEspecificos());
		return "adminToolsRecurso_template";
	}
	
	@RequestMapping("/adminTools/recurso/borrar")
	public String adminToolsRecursoBorrar(Model model) {
		model.addAttribute("isBorrar","si");
		model.addAttribute("opcionesRecurso",this.dataService.getRecursos());
		return "adminToolsRecurso_template";
	}
	
	@RequestMapping("/adminTools/recurso/editar")
	public String adminToolsRecursoEditar(Model model) {
		model.addAttribute("isEditar","si");
		model.addAttribute("opcionesRecurso",this.dataService.getRecursos());
		return "adminToolsRecurso_template";
	}
	
	@RequestMapping("/adminTools/recurso/agregar/{idRecursoEspecifico}")
	public String adminToolsRecursoAgregarRecurso(Model model,@PathVariable String idRecursoEspecifico) {
		model.addAttribute("nombreRecursoEspecifico" , this.dataService.getRecursoEspecifico(idRecursoEspecifico).getNombreRecursoEspecifico());
		this.idRecursoEspecificoActual = idRecursoEspecifico;
		return getPlantilla(this.dataService.getRecursoEspecifico(idRecursoEspecifico).getNombreRecursoEspecifico().getNombre(), "Agregar");
	}
	
	@RequestMapping("/adminTools/recurso/editar/{idRecurso}")
	public String adminToolsModuloEditarModulo(Model model,@PathVariable String idRecurso,@RequestParam String idRecursoEspecifico) {
		model.addAttribute("nombre" , this.dataService.getRecurso(idRecurso,idRecursoEspecifico).getNombre());
		model.addAttribute("nombreRecursoEspecifico" , this.dataService.getRecursoEspecifico(idRecursoEspecifico).getNombreRecursoEspecifico());
		model.addAttribute("recurso", this.dataService.getRecurso(idRecurso, idRecursoEspecifico));
		this.idRecursoActual = idRecurso;
		this.idRecursoEspecificoActual = idRecursoEspecifico;
		return getPlantilla(this.dataService.getRecursoEspecifico(idRecursoEspecifico).getNombreRecursoEspecifico().getNombre(), "Editar");
	}
	
	@RequestMapping("/adminTools/recurso/borrar/{idRecurso}")
	public String adminToolsRecursoBorrarRecurso(Model model,@PathVariable String idRecurso,@RequestParam String idRecursoEspecifico) {
		model.addAttribute("nombre" , this.dataService.getRecurso(idRecurso,idRecursoEspecifico).getNombre());
		model.addAttribute("nombreRecursoEspecifico" , this.dataService.getRecursoEspecifico(idRecursoEspecifico).getNombreRecursoEspecifico());
		this.idRecursoActual = idRecurso;
		this.idRecursoEspecificoActual = idRecursoEspecifico;
		return "adminToolsRecursoBorrar_template";
	}
	
	
	@RequestMapping("/adminTools/recurso/agregar/guardarFuncionProcedureMetodo")
	public String adminToolsRecursoAgregarRecursoGuardar(Model model,FuncionProcedureMetodo funcionProcedureMetodo) {
		funcionProcedureMetodo.setRecursoEspecifico(this.dataService.getRecursoEspecifico(idRecursoEspecificoActual));
		funcionProcedureMetodo.setParametros(new ArrayList<>());
		if(this.dataService.existRecurso(funcionProcedureMetodo)){
			model.addAttribute("mensaje" ,"El Recurso ya existe");
		}
		else{
			this.dataService.guardarRecurso(funcionProcedureMetodo);
			model.addAttribute("mensaje" ,"Recurso guardado");
		}
		return "adminToolsRecurso_template";
	}
	
	@RequestMapping("/adminTools/recurso/agregar/guardarTabla")
	public String adminToolsRecursoAgregarRecursoGuardarTabla(Model model,@RequestParam MultipartFile imagenTabla,
			@RequestParam String nombre,@RequestParam String descripcion) throws IllegalStateException, IOException {
		String ruta = ".//src//main//resources//img//" + imagenTabla.getOriginalFilename();
		this.uploadFileService.saveFile(imagenTabla);
		Tabla tabla = new Tabla(nombre,ruta,descripcion, 
				this.dataService.getRecursoEspecifico(idRecursoEspecificoActual));
		tabla.setRecursoEspecifico(this.dataService.getRecursoEspecifico(idRecursoEspecificoActual));
		tabla.setColumnas(new ArrayList<>());
		if(this.dataService.existRecurso(tabla)){
			model.addAttribute("mensaje" ,"El Recurso ya existe");
		}
		else{
			this.dataService.guardarRecurso(tabla);
			model.addAttribute("mensaje" ,"Recurso guardado");
		}
		return "adminToolsRecurso_template";
	}

	@RequestMapping("/adminTools/recurso/editar/guardarFuncionProcedureMetodo")
	public String adminToolsRecursoEditarRecursoGuardar(Model model ,FuncionProcedureMetodo recurso) {
		recurso.setParametros((ArrayList<Parametro>)((FuncionProcedureMetodo)this.dataService.getRecurso(idRecursoActual, idRecursoEspecificoActual)).getParametros());
		recurso.setRecursoEspecifico(this.dataService.getRecursoEspecifico(String.valueOf(this.dataService.getRecurso(idRecursoActual, idRecursoEspecificoActual).getIdRecursoEspecifico())));
		this.dataService.guardarRecurso(recurso);
		model.addAttribute("mensaje" ,"recurso actualizado");
		return "adminToolsRecurso_template";
	}
	
	@RequestMapping("/adminTools/recurso/borrar/guardar")
	public String adminToolsRecursoBorrarRecursoGuardar(Model model) {
		Recurseable recurso = this.dataService.getRecurso(this.idRecursoActual,this.idRecursoEspecificoActual);
		if(!this.dataService.existRecurso(recurso)){
			model.addAttribute("mensaje" ,"No borrado. El recurso no existe");
		}
		else{
			try {
				this.dataService.borrarRecurso(recurso);
				model.addAttribute("mensaje" ,"recurso borrado");
			} catch (Exception e) {
				model.addAttribute("mensaje" ,"No se puede borrar el Recurso por que hay elementos asociados a este recurso");
			}
		}
		return "adminToolsRecurso_template";
	}
	
	private String getPlantilla(String nombreRecurso,String accion){
		if(nombreRecurso.equalsIgnoreCase("funcion") || 
				nombreRecurso.equalsIgnoreCase("procedure") ||
				nombreRecurso.equalsIgnoreCase("metodo")){
			nombreRecurso = new String("FuncionProcedureMetodo");
		}
		nombreRecurso = new String(nombreRecurso.replace(nombreRecurso.charAt(0),Character.toUpperCase(nombreRecurso.charAt(0))));
		return "adminTools"+nombreRecurso+accion+"_template";
	}
	
}
