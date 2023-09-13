package com.hotel.jdbc.controller;

import java.util.List;

import com.hotel.jdbc.dao.HuespedDAO;
import com.hotel.jdbc.factory.ConnectionFactory;
import com.hotel.jdbc.modelo.Huesped;


public class HuespedController {
	
	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public void guardar(Huesped huesped) {		
		huespedDAO.guardar(huesped);
	}

	public List<Huesped> listar() {
		return huespedDAO.listar();
	}

	public int editar(Huesped editar) {
		return huespedDAO.editar(editar);
		
	}
	
	public int eliminar(Huesped eliminar) {
		return huespedDAO.eliminar(eliminar);
		
	}
	
	public List<Huesped> buscarHuesped(String criterio)  {
	return huespedDAO.buscarHuesped(criterio);	
	}

	public Object cargar(Huesped cargar) {
		return huespedDAO.cargar(cargar);
		
	}

}
