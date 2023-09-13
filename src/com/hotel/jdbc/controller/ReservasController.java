package com.hotel.jdbc.controller;




import java.util.List;

import com.hotel.jdbc.dao.ReservasDAO;
import com.hotel.jdbc.factory.ConnectionFactory;
import com.hotel.jdbc.modelo.Reserva;

public class ReservasController {

	private ReservasDAO reservasDAO;
	
	public ReservasController() {
		this.reservasDAO = new ReservasDAO(new ConnectionFactory().recuperaConexion());
		
	}
	
	public void guardar(Reserva reserva) {		
		reservasDAO.guardar(reserva);
	}

	public List<Reserva> listar()  {
	return reservasDAO.listar();		
	}

	public int editar(Reserva editar) {
		return reservasDAO.editar(editar);
		
	}
	
	public int eliminar(Reserva eliminar) {
		return reservasDAO.eliminar(eliminar);
		
	}
	
	public List<Reserva> buscarReserva(String criterio)  {
	return reservasDAO.buscarReserva(criterio);	
	}
	
	public Object cargar(Reserva cargar) {
		return reservasDAO.cargar(cargar);
		
	}
}
