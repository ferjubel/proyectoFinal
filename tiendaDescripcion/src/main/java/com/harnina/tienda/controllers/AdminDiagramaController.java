package com.harnina.tienda.controllers;

import java.io.IOException;
import javax.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.harnina.tienda.model.Diagrama;
import com.harnina.tienda.service.DataService;
import com.harnina.tienda.service.UploadFileService;

@Controller
@MultipartConfig
public class AdminDiagramaController{
	
	@Autowired
	private UploadFileService uploadFileService;
	
	@Autowired
	private DataService dataService;
	
	@RequestMapping("/adminTools/diagrama/agregar")
	public String adminToolsDiagramaAgregar(Model model) {
		return "adminToolsDiagramaAgregar_template";
	}
	
	
	@RequestMapping("/adminTools/diagrama/agregar/guardar")
	public String adminToolsDiagramaAgregarGuardar(Model model,
			@RequestParam MultipartFile diagrama,
			@RequestParam String nombre) throws IllegalStateException, IOException {
		String ruta = "http://localhost:8080/img/" + diagrama.getOriginalFilename();
		this.uploadFileService.saveFile(diagrama); 
		Diagrama diagramaTemp = new Diagrama(nombre, ruta);
		if(this.dataService.guardarDiagrama(diagramaTemp))
			model.addAttribute("mensaje" ,"Diagrama guardado");
		return "adminToolsDiagrama_template";
	}

	
	@RequestMapping("/adminTools/diagrama/asociar/subModulo/guardar")
	public String adminToolsDiagramaAsociarSubModuloGuardar(Model model,
			@RequestParam String idDiagrama,@RequestParam String idSubModulo){
		this.dataService.asociarDiagramaSubModulo(idDiagrama,idSubModulo);
		model.addAttribute("mensaje" ,"Diagrama asociado");
		return "adminToolsAsociarDiagrama_template";
	}
	
	@RequestMapping("/adminTools/diagrama/asociar/vista/guardar")
	public String adminToolsDiagramaAsociarVistaGuardar(Model model,
			@RequestParam String idDiagrama,@RequestParam String idVista){
		this.dataService.asociarDiagramaVista(idDiagrama,idVista);
		model.addAttribute("mensaje" ,"Diagrama asociado");
		return "adminToolsAsociarDiagrama_template";
	}
	
}
