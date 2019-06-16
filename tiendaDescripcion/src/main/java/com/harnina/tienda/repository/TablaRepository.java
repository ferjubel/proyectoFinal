package com.harnina.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harnina.tienda.model.FuncionProcedureMetodo;
import com.harnina.tienda.model.Tabla;

public interface TablaRepository extends JpaRepository<Tabla, Long> {

	List<FuncionProcedureMetodo> findByNombre(String nombre);

}