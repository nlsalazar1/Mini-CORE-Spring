package com.udla.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udla.models.entity.Vendedor;
import com.udla.models.repository.VendedorRepository;

@Service
public class VendedorServiceImpl implements IVendedorService {

	@Autowired
	private VendedorRepository vendedorRepository;
	
	//@Override
	public List<Vendedor> listaVendedores() {

		return (List<Vendedor>) vendedorRepository.findAll();
	}

	//@Override
	public void guardar(Vendedor vendedor) {

		vendedorRepository.save(vendedor);
	}

	//@Override
	public Vendedor buscarPorId(Long id) {
		return vendedorRepository.findById(id).orElse(null); //Controlamos con orElse que no nos retorne un ERROR en caso de que no exista
	}

	//@Override
	public void eliminar(Long id) {
		vendedorRepository.deleteById(id); 
	}

}
