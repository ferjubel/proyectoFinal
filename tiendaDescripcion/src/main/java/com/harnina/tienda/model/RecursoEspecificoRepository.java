package com.harnina.tienda.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecursoEspecificoRepository extends JpaRepository<RecursoEspecifico, Long> {

	List<RecursoEspecifico> findBySubModuloIdSubModulo(long idSubModulo);

}