package com.hotel.jdbc.modelo;

import java.sql.Date;

public class Reserva {	

	private Integer id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private Integer total_noches;
	private String valor;
	private String formaDePago;
	
	public Reserva(Date fechaEntrada, Date fechaSalida, int total_noches, String valor, String formaDePago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.total_noches = total_noches;
		this.valor = valor;
		this.formaDePago = formaDePago;
	}
	
	
	
	public Reserva(int id, Date fechaEntrada, Date fechaSalida, int total_noches, String valor, String formaDePago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.total_noches = total_noches;
		this.valor = valor;
		this.formaDePago = formaDePago;
	}
	
	public Reserva(int id) {
		this.id = id;

	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}



	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}



	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}



	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}
	
	public Integer getTotalNoches() {
		return total_noches;
	}

	public String getValor() {
		return valor;
	}

	public String getFormaDePago() {
		return formaDePago;
	}


}
