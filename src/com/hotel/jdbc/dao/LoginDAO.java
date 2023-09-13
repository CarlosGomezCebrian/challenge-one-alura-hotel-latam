package com.hotel.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hotel.jdbc.modelo.Login;

public class LoginDAO {
	
	final private Connection con;
	
	public LoginDAO(Connection con) {
		this.con = con;
	}
	
	public void login(Login login) {
		
		try {
			final String query = "SELECT acceso FROM usuario WHERE usuario = ? and contraseña = ?";
			final PreparedStatement statement = con.prepareStatement(query);
			
			try (statement){
				statement.setString(1, login.getUsuario());
				statement.setString(2, login.getContraseña());
				
				statement.execute();
				
				ResultSet rst = statement.executeQuery();
				if (rst.next()) {
				    int acceso = rst.getInt("acceso");
				    login.setAcceso(acceso);
				    System.out.println("Igreso con éxito, el accceso es: " + acceso);
				} else {
					String acceso = "Datos incorrectos";
					System.out.println("Error de acceso " + acceso);
				}
				
				
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
