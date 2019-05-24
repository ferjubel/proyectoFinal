package com.harnina.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harnina.tienda.model.FuncionProcedureMetodo;

public interface FuncionProcedureMetodoRepository extends JpaRepository<FuncionProcedureMetodo, Long> {

	List<FuncionProcedureMetodo> findByNombre(String nombre);

}