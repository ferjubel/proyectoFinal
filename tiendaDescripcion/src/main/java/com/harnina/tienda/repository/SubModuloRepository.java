package com.harnina.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harnina.tienda.model.NombreSubModulo;
import com.harnina.tienda.model.SubModulo;

public interface SubModuloRepository extends JpaRepository<SubModulo, Long> {

	List<SubModulo> findByModuloIdModulo(long idModulo);

	List<SubModulo> findByNombreSubModulo(NombreSubModulo nombreSubModulo);
}