package com.udla.models.service;

import java.util.List;

import com.udla.models.entity.Vendedor;

public interface IVendedorService {

	public List<Vendedor> listaVendedores();
	public void guardar(Vendedor vendedor);
	public Vendedor buscarPorId(Long id);
	public void eliminar(Long id);
}
