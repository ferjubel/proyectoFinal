package com.harnina.tienda.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuloRepository extends JpaRepository<Modulo, Long> {

	List<Modulo> findByNombre(String nombre);

}