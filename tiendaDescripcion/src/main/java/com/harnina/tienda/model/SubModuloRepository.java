package com.harnina.tienda.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubModuloRepository extends JpaRepository<SubModulo, Long> {

	List<SubModulo> findByModuloIdModulo(long idModulo);

}