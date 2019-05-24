package com.harnina.tienda.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.harnina.tienda.model.FuncionProcedureMetodo;
import com.harnina.tienda.model.Recurseable;
import com.harnina.tienda.repository.FuncionProcedureMetodoRepository;

@Component
public class FuncionProcedureMetodoService implements RecursoServiceable{
	
	@Autowired
	private FuncionProcedureMetodoRepository funcionProcedureMetodoRepository;
	
	private List<FuncionProcedureMetodo> funcionProcedureMetodoList;
	
	public FuncionProcedureMetodoService() {
		super();
	}
	
	@Override
	public List<Recurseable> getRecursos(long idRecursoEspecifico) {
		if(this.funcionProcedureMetodoList==null){
			this.funcionProcedureMetodoList = funcionProcedureMetodoRepository.findAll();
		}
		List<Recurseable> retorno = new ArrayList<>();
		for (FuncionProcedureMetodo funcionProcedureMetodo : this.funcionProcedureMetodoList) {
			if(funcionProcedureMetodo.getRecursoEspecifico().getIdRecursoEspecifico() == idRecursoEspecifico){
				retorno.add(funcionProcedureMetodo);
			}
		}
		return retorno;
	}

	@Override
	public List<Recurseable> getRecursos() {
		if(this.funcionProcedureMetodoList==null){
			this.funcionProcedureMetodoList = funcionProcedureMetodoRepository.findAll();
		}
		List<Recurseable> retorno = new ArrayList<>();
		retorno.addAll(this.funcionProcedureMetodoList);
		return retorno;
	}
	
	@Override
	public Recurseable getRecurso(long id) {
		if(this.funcionProcedureMetodoList==null)recargarFuncionProcedureMetodo();
		for (FuncionProcedureMetodo funcionProcedureMetodo : this.funcionProcedureMetodoList) {
			if(funcionProcedureMetodo.getId() == id){
				return funcionProcedureMetodo;
			}
		}
		return null;
	}

	@Override
	public boolean existRecurso(FuncionProcedureMetodo funcionProcedureMetodo) {
		return !this.funcionProcedureMetodoRepository.findByNombre(funcionProcedureMetodo.getNombre()).isEmpty();
	}

	@Override
	public void guardarRecurso(FuncionProcedureMetodo funcionProcedureMetodo) {
		this.funcionProcedureMetodoRepository.save(funcionProcedureMetodo);
		recargarFuncionProcedureMetodo();
	}
	
	private void recargarFuncionProcedureMetodo() {
		this.funcionProcedureMetodoList = funcionProcedureMetodoRepository.findAll();
	}
	
	@Override
	public void borrarRecurso(FuncionProcedureMetodo nombre) {
		this.funcionProcedureMetodoRepository.delete(nombre);
		recargarFuncionProcedureMetodo();
	}
}
