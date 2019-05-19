package com.harnina.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harnina.tienda.model.NombreSubModulo;

public interface NombreSubModuloRepository extends JpaRepository<NombreSubModulo, Long> {

	List<NombreSubModulo> findByNombre(String nombre);

}