package com.harnina.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harnina.tienda.model.Clave;

public interface ClaveRepository extends JpaRepository<Clave, Long> {

	List<Clave> findByNombre(String nombre);

}