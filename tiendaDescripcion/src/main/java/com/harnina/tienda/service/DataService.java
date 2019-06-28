package com.harnina.tienda.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.Diagrama;
import com.harnina.tienda.model.Modulo;
import com.harnina.tienda.model.SubModulo;
import com.harnina.tienda.model.RecursoEspecifico;
import com.harnina.tienda.model.NombreRecursoEspecifico;
import com.harnina.tienda.model.NombreSubModulo;
import com.harnina.tienda.model.Parteable;
import com.harnina.tienda.model.Recurseable;

@Component
public class DataService {
	
	@Autowired
	private ModuloService moduloService;
	
	@Autowired
	private SubModuloService subModuloService;
	
	@Autowired
	private DiagramaService diagramaService;
	
	@Autowired
	private NombreSubModuloService nombreSubModuloService;

	@Autowired
	private RecursoEspecificoService recursoEspecificoService;

	@Autowired
	private NombreRecursoEspecificoService nombreRecursoEspecificoService;
	
	@Autowired
	private RecursoService recursoService;

	@Autowired
	private ParteService parteService;
	
	private List<Serviceable> servicios;

	private List<Thread> hilos;

	private Integer estadoCarga;

	private Integer limiteCarga;
	
	public DataService() {
		super();
	}
	
	@PostConstruct
	private void cargarDatos(){
		iniciarCargaDatosEnRAM();
	}

	
	//MODULO
	

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

	
	
	//SUBMODULO
	
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

	public List<Diagrama> getDiagramas (String idSubModulo) {
		return subModuloService.getDiagramas(idSubModulo);
	}
	
	//RECURSO ESPECIFICO
	
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
	
	
	
	
	//NOMBRE SUBMODULO
	
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
	
	
	
	
	//NOMBRE RECURSO ESPECIFICO
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
	
	
	

	
	//RECURSEABLE
	public List<Recurseable> getRecursos(String idRecursoEspecifico) {
		return this.recursoService.getRecursos(Long.valueOf(idRecursoEspecifico));
	}

	public List<Recurseable> getRecursos() {
		return this.recursoService.getRecursos();
	}
	
	public boolean existRecurso(Recurseable recurso) {
		return this.recursoService.existRecurso(recurso);
	}

	public void guardarRecurso(Recurseable recurso) {
		this.recursoService.guardarRecurso(recurso);
	}
	
	public void borrarRecurso(Recurseable recurso) {
		this.recursoService.borrarRecurso(recurso);
	}

	public List<Recurseable> getFunctionesProceduresMetodos() {
		return this.recursoService.getRecursos();
	}

	public Recurseable getRecurso(String idRecurso,String idRecursoEspecifico) {
		return this.recursoService.getRecurso(idRecurso,idRecursoEspecifico);
	}

	
	
	
	//PARTEABLE 
	public List<Parteable> getPartes() {
		return this.parteService.getPartes();
	}

	public Parteable getParte(String idParte, String nombreParte, String tipoParte) {
		return this.parteService.getParte(idParte, nombreParte,tipoParte);
	}

	public void guardarParte(Parteable parte) {
		parte.guardar(parteService);
	}

	public boolean existParte(Parteable parte) {
		return this.parteService.existParte(parte);
	}

	public void borrarParte(Parteable parte) {
		this.parteService.borrarParte(parte);
	}
	
	public void asociarParte(Recurseable recurso,Parteable parte){
		this.parteService.asociarParte(recurso,parte);
	}

	public List<String> getNombrePartes() {
		List<String> retorno = new ArrayList<>();
		retorno.add("parametro");
		retorno.add("columna");
		retorno.add("clave");
		retorno.add("vistaJsp");
		return retorno;
	}

	
	

	
	//DIAGRAMAS

	public boolean guardarDiagrama(Diagrama diagrama) {
		return diagramaService.guardarDiagrama(diagrama);
	}
	
	public boolean borrarDiagrama(Diagrama diagrama) {
		return diagramaService.borrarDiagrama(diagrama);
	}

	public List<Diagrama> getDiagramas() {
		return diagramaService.getDiagramas();
	}

	public void asociarDiagramaSubModulo(String idDiagrama, String idSubModulo) {
		this.subModuloService.asociarDiagrama(this.diagramaService.getDiagrama(idDiagrama),idSubModulo);
	}
	
	public void asociarDiagramaVista(String idDiagrama, String idVista) {
		this.recursoService.asociarDiagramaVista(this.diagramaService.getDiagrama(idDiagrama),idVista);
	}
	

	public boolean hasParametros(String idRecurso, String idRecursoEspecifico) {
		return getRecurso(idRecurso, idRecursoEspecifico).has("Parametro");
	}

	public boolean hasColumnas(String idRecurso, String idRecursoEspecifico) {
		return getRecurso(idRecurso, idRecursoEspecifico).has("Columna");
	}

	public boolean hasClaves(String idRecurso, String idRecursoEspecifico) {
		return getRecurso(idRecurso, idRecursoEspecifico).has("Clave");
	}

	public void iniciarCargaDatosEnRAM() {
		if(this.servicios == null)iniciarListaServicios(); 
		hilos = new ArrayList<>();
		for (Serviceable servicio : servicios) {
			hilos.add(servicio.cargarDatosEnRam());
		}
		for (Thread thread : hilos) {
			thread.start();
			System.out.println("iniciando" + thread.getName());
		}
	}
	
	 public Map<String,Integer> getEstadoCarga(){
		estadoCarga = 0;
		limiteCarga = servicios.size();
			for (int i = 0; i < hilos.size(); i++) {
				if(hilos.get(i).isAlive())estadoCarga ++;
			}
		Map<String, Integer> retorno = new HashMap<>();
		retorno.put("actualCarga", estadoCarga);
		retorno.put("limiteCarga", limiteCarga);
		return retorno;
	}
	
	public Thread datosListos(String nombreHilo){
		if(this.servicios == null)iniciarListaServicios();
		if(hilos == null)return null;
		else
		for (int i = 0; i < hilos.size(); i++) {
			if(hilos.get(i).getName().equals(nombreHilo))
				return hilos.get(i);
		}
		return null;
	}

	private void iniciarListaServicios() {
		this.servicios = new ArrayList<>();
		this.servicios.add(this.moduloService);
		this.servicios.add(this.subModuloService);
		this.servicios.add(this.nombreSubModuloService);
		this.servicios.add(this.recursoEspecificoService);
		this.servicios.add(this.nombreRecursoEspecificoService);
		this.servicios.add(this.recursoService);
		this.servicios.add(this.parteService);
	}

}
