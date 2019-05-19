package com.harnina.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harnina.tienda.model.NombreRecursoEspecifico;

public interface NombreRecursoEspecificoRepository extends JpaRepository<NombreRecursoEspecifico, Long> {

	List<NombreRecursoEspecifico> findByNombre(String nombre);

}