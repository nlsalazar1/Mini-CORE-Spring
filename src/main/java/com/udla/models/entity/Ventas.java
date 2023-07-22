package com.udla.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;


@Entity
@Table(name="ventas")
public class Ventas implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id //Indicamos que es la clave de la tabla
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Indicamos que se genera la clave automaticamente
	private Long id_ventas;
	
	@NotEmpty
	private String producto;
	
	private int monto; 
	
	private Date fecha;
		
	@ManyToOne //Indicamos que la relacion con la otra tabla es de mnnuchos a uno
	@JoinColumn(name="id_vendedor") //indicamos el campo con el que esta relacionada la tabla
	private Vendedor vendedor;

	
	public Long getId_ventas() {
		return id_ventas;
	}

	public void setId_ventas(Long id_ventas) {
		this.id_ventas = id_ventas;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	@Override
	public String toString() {
		return "Ventas [id_ventas=" + id_ventas + ", producto=" + producto + ", monto=" + monto + ", fecha=" + fecha
				+ ", vendedor=" + vendedor + "]";
	}	
	
	
}

