package com.udla.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udla.models.entity.Ventas;
import com.udla.models.repository.VentasRepository;


@Service
public class VentasServiceImpl implements IVentasService {

	@Autowired
	private VentasRepository ventasRepository; //Indicamos que es una inyeccion de dependencia
	
	//@Override
	public List<Ventas> listarTodos() {		
		return (List<Ventas>) ventasRepository.findAll();
	}

	//@Override
	public void guardar(Ventas ventas) {

		ventasRepository.save(ventas);
	}

	//@Override
	public Ventas buscarPorId(Long id) {
		return ventasRepository.findById(id).orElse(null); //Controlamos con orElse que no nos retorne un ERROR en caso de que no exista
	}

	//@Override
	public void eliminar(Long id) {
		ventasRepository.deleteById(id); 

	}

}
