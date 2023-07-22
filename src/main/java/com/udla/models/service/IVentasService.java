package com.udla.models.service;

import java.util.List;

import com.udla.models.entity.Ventas;

public interface IVentasService {
	
	public List<Ventas> listarTodos();
	public void guardar(Ventas venta);
	public Ventas buscarPorId(Long id);
	public void eliminar(Long id);

}
