package com.harnina.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.harnina.tienda.model.FuncionProcedureMetodo;
import com.harnina.tienda.model.Modulo;
import com.harnina.tienda.model.NombreRecursoEspecifico;
import com.harnina.tienda.model.NombreSubModulo;
import com.harnina.tienda.model.Recurseable;
import com.harnina.tienda.model.RecursoEspecifico;
import com.harnina.tienda.model.SubModulo;

@Component
public class DataService {
	
	@Autowired
	private ModuloService moduloService;
	
	@Autowired
	private SubModuloService subModuloService;
	
	@Autowired
	private NombreSubModuloService nombreSubModuloService;

	@Autowired
	private RecursoEspecificoService recursoEspecificoService;

	@Autowired
	private NombreRecursoEspecificoService nombreRecursoEspecificoService;
	
	@Autowired
	private RecursoService recursoService;
	
	public DataService() {
		super();
	}

	public List<Modulo> getOpcionesModulo() {
		return this.moduloService.getOpcionesModulo();
	}

	public Modulo getModulo(long idModulo) {
		return this.moduloService.getModulo(idModulo);
	}
	
	public Modulo getModulo(String idModulo) {
		return getModulo(Long.valueOf(idModulo));
	}

	public void guardarModulo(Modulo modulo) {
		this.moduloService.guardarModulo(modulo);
	}
	
	public void borrarModulo(Modulo modulo) {
		this.moduloService.borrarModulo(modulo);
	}

	public boolean existModulo(Modulo modulo) {
		return this.moduloService.existModulo(modulo);
	}

	
	
	
	public List<SubModulo> getSubModulos(String idModuloActual) {
		return this.subModuloService.getSubModulos(Long.valueOf(idModuloActual));
	}
	
	public List<SubModulo> getSubModulos() {
		return this.subModuloService.getSubModulos();
	}

	public SubModulo getSubModulo(Long idSubModuloActual) {
		return this.subModuloService.getSubModulo(idSubModuloActual);
	}

	public SubModulo getSubModulo(String idSubModulo) {
		return getSubModulo(Long.valueOf(idSubModulo));
	}

	public boolean existSubModulo(SubModulo subModulo) {
		return this.subModuloService.existSubModulo(subModulo);
	}

	public void guardarSubModulo(SubModulo subModulo, String idModulo) {
		Modulo modulo = this.getModulo(idModulo);
		subModulo.setModulo(modulo);
		this.subModuloService.guardarSubModulo(subModulo);
	}
	
	public void guardarSubModulo(SubModulo subModulo) {
		this.subModuloService.guardarSubModulo(subModulo);
	}
	
	public void borrarSubModulo(SubModulo subModulo) {
		this.subModuloService.borrarSubModulo(subModulo);
	}

	
	
	public List<RecursoEspecifico> getRecursosEspecificos() {
		return this.recursoEspecificoService.getRecursosEspecificos();
	}

	public List<RecursoEspecifico> getRecursosEspecificos(String idSubModulo) {
		return this.recursoEspecificoService.getRecursosEspecificos(Long.valueOf(idSubModulo));
	}

	public RecursoEspecifico getRecursoEspecifico(String idRecursoEspecifico) {
		return this.recursoEspecificoService.getRecursoEspecifico(Long.valueOf(idRecursoEspecifico));
	}
	
	public boolean existRecursoEspecifico(RecursoEspecifico recursoEspecifico) {
		return this.recursoEspecificoService.existRecursoEspecifico(recursoEspecifico);
	}

	public void guardarRecursoEspecifico(RecursoEspecifico recursoEspecifico, String idSubModulo) {
		SubModulo subModulo = this.subModuloService.getSubModulo(Long.valueOf(idSubModulo));
		recursoEspecifico.setSubModulo(subModulo);
		guardarRecursoEspecifico(recursoEspecifico);
	}
	
	public void guardarRecursoEspecifico(RecursoEspecifico recursoEspecifico) {
		this.recursoEspecificoService.guardarRecursoEspecifico(recursoEspecifico);
	}
	
	public void borrarRecursoEspecifico(RecursoEspecifico subModulo) {
		this.recursoEspecificoService.borrarRecursoEspecifico(subModulo);
	}
	
	
	
	public List<NombreSubModulo> getNombresSubModulo() {
		return this.nombreSubModuloService.getNombresSubModulo();
	}

	public NombreSubModulo getNombreSubModulo(String idNombreSubModulo) {
		return this.nombreSubModuloService.getNombreSubModulo(Long.valueOf(idNombreSubModulo));
	}
	
	public boolean existNombreSubModulo(NombreSubModulo nombreSubModulo) {
		return this.nombreSubModuloService.existNombreSubModulo(nombreSubModulo);
	}

	public void guardarNombreSubModulo(NombreSubModulo nombreSubModulo) {
		this.nombreSubModuloService.guardarNombreSubModulo(nombreSubModulo);
	}
	
	public void borrarNombreSubModulo(NombreSubModulo nombreSubModulo) {
		this.nombreSubModuloService.borrarNombreSubModulo(nombreSubModulo);
	}
	
	
	
	public List<NombreRecursoEspecifico> getNombresRecursoEspecifico() {
		return this.nombreRecursoEspecificoService.getNombresRecursoEspecifico();
	}

	public NombreRecursoEspecifico getNombreRecursoEspecifico(String idNombreRecursoEspecifico) {
		return this.nombreRecursoEspecificoService.getNombreRecursoEspecifico(Long.valueOf(idNombreRecursoEspecifico));
	}
	
	public boolean existNombreRecursoEspecifico(NombreRecursoEspecifico nombreRecursoEspecifico) {
		return this.nombreRecursoEspecificoService.existNombreRecursoEspecifico(nombreRecursoEspecifico);
	}

	public void guardarNombreRecursoEspecifico(NombreRecursoEspecifico nombreRecursoEspecifico) {
		this.nombreRecursoEspecificoService.guardarNombreRecursoEspecifico(nombreRecursoEspecifico);
	}
	
	public void borrarNombreRecursoEspecifico(NombreRecursoEspecifico nombreRecursoEspecifico) {
		this.nombreRecursoEspecificoService.borrarNombreRecursoEspecifico(nombreRecursoEspecifico);
	}
	
	
	

	public List<Recurseable> getRecursos(String idRecursoEspecifico) {
		return this.recursoService.getRecursos(Long.valueOf(idRecursoEspecifico));
	}

	public List<Recurseable> getRecursos() {
		return this.recursoService.getRecursos();
	}
	
	public boolean existRecurso(FuncionProcedureMetodo recurso) {
		return this.recursoService.existRecurso(recurso);
	}

	public void guardarRecurso(Recurseable recurso) {
		this.recursoService.guardarRecurso(recurso);
	}
	
	public void borrarRecurso(Recurseable recurso) {
		this.recursoService.borrarRecurso(recurso);
	}
	
	
	

	/*
	@PostConstruct
	public void init() {
		moduloRepository.save(new Modulo("base de datos"));
		moduloRepository.save(new Modulo("cliente"));
		moduloRepository.save(new Modulo("servidor"));
		
		nombreSubModuloRepository.save(new NombreSubModulo("pagina"));
		nombreSubModuloRepository.save(new NombreSubModulo("cliente"));
		
		subModuloRepository.save(new SubModulo(moduloRepository.findOne((long) 1), nombreSubModuloRepository.findOne((long) 1)));
		subModuloRepository.save(new SubModulo(moduloRepository.findOne((long) 1), nombreSubModuloRepository.findOne((long) 2)));
	
		nombreRecursoEspecificoRepository.save(new NombreRecursoEspecifico("procudure"));
		nombreRecursoEspecificoRepository.save(new NombreRecursoEspecifico("funciones"));
		nombreRecursoEspecificoRepository.save(new NombreRecursoEspecifico("tablas"));
		nombreRecursoEspecificoRepository.save(new NombreRecursoEspecifico("vistas"));
		nombreRecursoEspecificoRepository.save(new NombreRecursoEspecifico("modelo relacional"));
		
		recursoEspecificoRepository.save(new RecursoEspecifico("getBody", "BEGIN"+
			    "\t\tEND,",
				"TODO EXPLICAR PROCEDURE", subModuloRepository.findOne((long) 1), nombreRecursoEspecificoRepository.findOne((long) 1)));
		
		
		
		
		recursoEspecificoRepository.save(new RecursoEspecifico("getLogin", "Begin etc etc etc ect",
				"hace magia y devuelve el body", subModuloRepository.findOne((long) 1), nombreRecursoEspecificoRepository.findOne((long) 1)));
		
		recursoEspecificoRepository.save(new RecursoEspecifico("getNif", "Begin etc etc etc ect",
				"hace magia y devuelve el body", subModuloRepository.findOne((long) 1), nombreRecursoEspecificoRepository.findOne((long) 1)));
		
		recursoEspecificoRepository.save(new RecursoEspecifico("getCodigoPostal", "Begin etc etc etc ect",
				"hace magia y devuelve el body", subModuloRepository.findOne((long) 1), nombreRecursoEspecificoRepository.findOne((long) 1)));
		
		recursoEspecificoRepository.save(new RecursoEspecifico("getPersonalData", "Begin etc etc etc ect",
				"hace magia y devuelve el body", subModuloRepository.findOne((long) 1), nombreRecursoEspecificoRepository.findOne((long) 1)));
	}
*/

}
