package com.udla.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vendedor")
public class Vendedor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id //Le indicamos que esta es la clave principal de nuestra tabla
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Indicamos que se genera el id de manera autoincremental automatica
	private Long id_vendedor;
	
	private String nombre_vendedor;
	
	
	public Long getId_vendedor() {
		return id_vendedor;
	}
	public void setId_vendedor(Long id_vendedor) {
		this.id_vendedor = id_vendedor;
	}
	public String getNombre_vendedor() {
		return nombre_vendedor;
	}
	public void setNombre_vendedor(String nombre_vendedor) {
		this.nombre_vendedor = nombre_vendedor;
	}
	@Override
	public String toString() {
		return "Vendedor [id_vendedor=" + id_vendedor + ", nombre_vendedor=" + nombre_vendedor + "]";
	}
	
	
	
}
