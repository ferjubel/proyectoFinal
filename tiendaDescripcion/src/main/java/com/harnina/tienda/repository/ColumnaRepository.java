package com.harnina.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harnina.tienda.model.Columna;

public interface ColumnaRepository extends JpaRepository<Columna, Long> {

	List<Columna> findByNombre(String nombre);

}