package com.harnina.tienda.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.harnina.tienda.model.Diagrama;

public interface DiagramaRepository extends JpaRepository<Diagrama, Long> {

	List<Diagrama> findByNombre(String nombre);

}