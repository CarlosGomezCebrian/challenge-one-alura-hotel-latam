package com.hotel.jdbc.pruebas;

import java.sql.Connection;
import java.sql.SQLException;

import com.hotel.jdbc.factory.ConnectionFactory;


public class PruebaConexion {

    public static void main(String[] args) throws SQLException {
    	Connection con = new ConnectionFactory().recuperaConexion();

        System.out.println("Cerrando la conexión PruebaConexion");

        con.close();
    }

}

