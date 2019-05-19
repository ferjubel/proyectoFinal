package com.harnina.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harnina.tienda.model.Modulo;

public interface ModuloRepository extends JpaRepository<Modulo, Long> {

	List<Modulo> findByNombre(String nombre);

}