package com.hotel.jdbc.modelo;

import java.sql.Date;

public class Huesped {
	private Integer id;
	private Integer numero_de_reserva;
	private String nombre;
	private String apellido;
	private Date fechaN;
	private String nacionalidad;
	private String telefono;
	
	public Huesped( int numero_de_reserva, String nombre, String apellido, Date fechaN, String nacionalidad, String telefono) {	
		this.numero_de_reserva = numero_de_reserva;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaN = fechaN;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}

	
	
	public Huesped(int id, int numero_de_reserva, String nombre, String apellido, Date fechaN, String nacionalidad, String telefono) {
		this.id = id;
		this.numero_de_reserva = numero_de_reserva;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaN = fechaN;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}
	
	public Huesped(int id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getNumeroDeReserva() {
		return numero_de_reserva;
	}

	public void setNumero_de_reserva(Integer numero_de_reserva) {
		this.numero_de_reserva = numero_de_reserva;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public Date getFechaN() {
		return fechaN;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public void setFechaN(Date fechaN) {
		this.fechaN = fechaN;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}











	

}
