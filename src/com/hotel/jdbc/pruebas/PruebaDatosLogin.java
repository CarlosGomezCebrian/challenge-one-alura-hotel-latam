package com.hotel.jdbc.pruebas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hotel.jdbc.factory.ConnectionFactory;

public class PruebaDatosLogin {

	public static void main(String[] args) throws SQLException {
		// Paso 1 Abrir la conexión por medio de la clase ConnectionFactory
		Connection con = new ConnectionFactory().recuperaConexion();
		
		// Paso 2 preparar la base de datos para recibir los datos
			
		String insert ="INSERT INTO usuario (usuario, contraseña, acceso,activo)" +
				       " VALUES (?,?,?,?)";
		PreparedStatement statement = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
		/*INSERT INTO usuario (usuario, contraseña, acceso,activo)
		 VALUES (?,?,?,?), Statement.RETURN_GENERATED_KEYS;
		 * 
		 * 
		 */
				
		//Paso 2.1 Set de los datos antes de enviar
		statement.setString(1, "fer");
		statement.setString(2, "fer");
		statement.setInt(3, 2);
		statement.setInt(4, 1);
		
					
		// Paso 3 executar			
		statement.execute();				
		
		// Paso 4 pedir retroalimentacion de la base de datos
		ResultSet rst = statement.getGeneratedKeys();
		if (rst.next()) {
		    int id = rst.getInt(1);
		    System.out.println("Datos guardados con éxito, el accceso es: " + id);
		} else {
			String acceso = " Datos incorrectos";
			System.out.println("Error de acceso " + acceso);
		}
		// Paso 5 cerrer la conexion
		con.close();
		System.out.println("Coneccion de PruebaDatosLoging cerrada");
	}
}
