package com.harnina.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harnina.tienda.model.RecursoEspecifico;

public interface RecursoEspecificoRepository extends JpaRepository<RecursoEspecifico, Long> {

	List<RecursoEspecifico> findBySubModuloIdSubModulo(long idSubModulo);

	List<RecursoEspecifico> findByNombre(String nombre);

}