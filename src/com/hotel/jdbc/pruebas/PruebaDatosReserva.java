package com.hotel.jdbc.pruebas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hotel.jdbc.factory.ConnectionFactory;

public class PruebaDatosReserva {

	public static void main(String[] args) throws SQLException {
		// Paso 1 Abrir la conexión por medio de la clase ConnectionFactory
		Connection conn = new ConnectionFactory().recuperaConexion();
		
		
		
		
		
		// Paso 2 preparar la base de datos para recibir los datos
		PreparedStatement stm = conn.prepareStatement("INSERT INTO reserva(fechaEntrada, fechaSalida,total_noches, valor, formaDePago)"
				+ "VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS );
		
		//Paso 2.1 Convercion de fecha YYYY-MES-AÑO
		//Paso 2.2 Set de los datos antes de enviaR
		Date fechaEntrada = Date.valueOf("2013-09-15");
		Date fechaSalida = Date.valueOf("2013-09-19");
		
		//Paso 2.2 Set de los datos antes de envia
		stm.setDate(1, fechaEntrada);
		stm.setDate(2, fechaSalida);
		stm.setInt(3, 4);
		stm.setString(4, "450");
		stm.setString(5, "Efectivo");
		// Paso 3 executar
		stm.execute(); 		
		
		// Paso 4 pedir retroalimentacion de la base de datos
		ResultSet rst = stm.getGeneratedKeys();
		
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("Reservación exitosa, su número de reservación es: " + id);
		}
		// Paso 5 cerrer la conexion
		conn.close();
		System.out.println("Coneccion de PruebaIngresardatos cerrada");
	}
}
