package com.harnina.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harnina.tienda.model.Columna;
import com.harnina.tienda.model.Parametro;

public interface ColumnaRepository extends JpaRepository<Columna, Long> {

	List<Parametro> findByNombre(String nombre);

}