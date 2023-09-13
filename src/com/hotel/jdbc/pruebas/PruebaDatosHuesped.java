package com.hotel.jdbc.pruebas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hotel.jdbc.factory.ConnectionFactory;

public class PruebaDatosHuesped {

	public static void main(String[] args) throws SQLException {
		// Paso 1 Abrir la conexión por medio de la clase ConnectionFactory
		Connection conn = new ConnectionFactory().recuperaConexion();
		
		
		
	
		
		// Paso 2 preparar la base de datos para recibir los datos
		PreparedStatement stm = conn.prepareStatement("INSERT INTO registro_huesped_reserva(numero_de_reserva, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono )"
				+ "VALUES (?,?,?,?,?,?)");
		
		//Paso 2.1 Convercion de fecha YYYY-MES-AÑO
		
		Date fecha_de_nacimiento = Date.valueOf("1998-04-19");
		
		
		//Paso 2.2 Set de los datos antes de enviar
		stm.setInt(1, 5 );
		stm.setString(2, "Rodrigo");
		stm.setString(3, "Mendoza");
		stm.setDate(4, fecha_de_nacimiento);
		stm.setString(5, "mexicana");
		stm.setString(6, "5498-4091");		
		
		// Paso 3 executar
		stm.execute(); 		
		
		// Paso 4 pedir retroalimentacion de la base de datos
		ResultSet rst = stm.executeQuery("SELECT MAX(numero_de_reserva) FROM registro_huesped_reserva");
		if (rst.next()) {
		    int id = rst.getInt(1);
		    System.out.println("Datos guardados con éxito, el ID es: " + id);
		}
		// Paso 5 cerrer la conexion
		conn.close();
		System.out.println("Coneccion de PruebaDatosHuesped cerrada");
	}
}
