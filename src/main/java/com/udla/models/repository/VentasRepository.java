package com.udla.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.udla.models.entity.Ventas;

@Repository //Indicamos que esta clase es un repository, aunque actualmente ya no es necesario indicarlo
public interface VentasRepository extends CrudRepository<Ventas, Long> {

	
}
