package com.harnina.tienda.service;

import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.Modulo;
import com.harnina.tienda.model.NombreRecursoEspecifico;
import com.harnina.tienda.model.NombreSubModulo;
import com.harnina.tienda.model.RecursoEspecifico;
import com.harnina.tienda.model.SubModulo;
import com.harnina.tienda.repository.ModuloRepository;
import com.harnina.tienda.repository.NombreRecursoEspecificoRepository;
import com.harnina.tienda.repository.NombreSubModuloRepository;
import com.harnina.tienda.repository.RecursoEspecificoRepository;
import com.harnina.tienda.repository.SubModuloRepository;

@Component
public class FuncionProcedureMetodoService {
	
	@Autowired
	private ModuloRepository moduloRepository;
	
	@Autowired
	private SubModuloRepository subModuloRepository;
	
	@Autowired
	private NombreSubModuloRepository nombreSubModuloRepository;

	@Autowired
	private RecursoEspecificoRepository recursoEspecificoRepository;

	@Autowired
	private NombreRecursoEspecificoRepository nombreRecursoEspecificoRepository;
	
	private List<Modulo> modulos;
	private List<SubModulo>subModulos;
	private List<RecursoEspecifico> recursosEspecificos;
	
	public FuncionProcedureMetodoService() {
		super();
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

	public List<Modulo> getOpcionesModulo() {
		if(this.modulos==null){
			this.modulos = moduloRepository.findAll();
		}
		return this.modulos;
	}

	public List<SubModulo> getSubModulos(long idModulo) {
		if(this.subModulos==null){
			this.subModulos = subModuloRepository.findByModuloIdModulo(idModulo);
		}
		return this.subModulos;
	}

	public List<RecursoEspecifico> getRecursosEspecificos(Long idSubModulo) {
		if(this.recursosEspecificos == null){
			this.recursosEspecificos = recursoEspecificoRepository.findBySubModuloIdSubModulo(idSubModulo);
					}
		return this.recursosEspecificos;
	}
	
	public void recargarModulos() {
		this.moduloRepository.flush();
		this.modulos = moduloRepository.findAll();
	}
	
	public void recargarSubModulos(long idModulo) {
		this.subModulos = subModuloRepository.findByModuloIdModulo(idModulo);
	}

	public void recargarRecursosEspecificos(Long idSubModulo) {
			this.recursosEspecificos = recursoEspecificoRepository.findBySubModuloIdSubModulo(idSubModulo);
	}

	public Modulo getModulo(long idModulo) {
		for (Modulo modulo : modulos) {
			if(modulo.getIdModulo() == idModulo){
				return modulo;
			}
		}
		return null;
	}
	
	public Modulo getModulo(String idModulo) {
		return getModulo(Long.valueOf(idModulo));
	}

	public void guardarModulo(Modulo modulo) {
		this.moduloRepository.save(modulo);
		recargarModulos();
	}
	
	public void borrarModulo(Modulo modulo) {
		this.moduloRepository.delete(modulo);
		recargarModulos();
	}

	public boolean existModulo(Modulo modulo) {
		return !this.moduloRepository.findByNombre(modulo.getNombre()).isEmpty();
	}

	public List<SubModulo> getSubModulos() {
		return this.subModuloRepository.findAll();
	}

	public SubModulo getSubModulo(Long idSubModuloActual) {
		for (SubModulo subModulo : subModulos) {
			if(subModulo.getIdSubModulo() == Long.valueOf(idSubModuloActual)){
				return subModulo;
			}
		}
		return null;
	}

	public boolean existSubModulo(SubModulo subModulo) {
		return !this.subModuloRepository.findByNombreSubModulo(subModulo.getNombreSubModulo()).isEmpty();
	}

	public void guardarSubModulo(SubModulo subModulo, String idModulo) {
		this.subModuloRepository.save(subModulo);
		recargarSubModulos(idModulo);
	}
	private void recargarSubModulos(String idModulo) {
		this.recargarSubModulos(Long.valueOf(idModulo));
	}

	public SubModulo getSubModulo(String idSubModulo) {
		return getSubModulo(Long.valueOf(idSubModulo));
	}

	

}
