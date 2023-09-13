 package com.hotel.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hotel.jdbc.modelo.Reserva;

public class ReservasDAO {
	
	final private Connection con;
	
	public ReservasDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Reserva reserva) {

		try {
			PreparedStatement statement = con.prepareStatement("INSERT INTO reserva(fechaEntrada, fechaSalida, total_noches, valor, formaDePago, activo)"
					+ "VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS );

			try (statement) {

				ejecutaRegisto(reserva, statement);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		}
	}
	
	private void ejecutaRegisto(Reserva reserva, PreparedStatement statement) throws SQLException {
		statement.setDate(1, reserva.getFechaEntrada());
		statement.setDate(2, reserva.getFechaSalida());
		statement.setInt(3, reserva.getTotalNoches());
		statement.setString(4, reserva.getValor());
		statement.setString(5, reserva.getFormaDePago());
		statement.setInt(6, 1);
		

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try (resultSet) {
			while (resultSet.next()) {
				reserva.setId(resultSet.getInt(1));
				 //System.out.println("Fue realizada la reserva numero: " + reserva.getId());
				//System.out.println(String.format("Fue insertado el producto %s", reserva));
			}
		}

	}
	
	
	public List<Reserva> listar() {
		List<Reserva> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con
					.prepareStatement("SELECT id, fechaEntrada, fechaSalida,total_noches, valor, formaDePago FROM reserva WHERE activo = 1");

			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet(); 

				try (resultSet) {
					while (resultSet.next()) {
						Reserva fila = new Reserva(resultSet.getInt("id"), resultSet.getDate("fechaEntrada"), resultSet.getDate("fechaSalida"), resultSet.getInt("total_noches") , resultSet.getString("valor"),
								resultSet.getString("formaDePago"));

						resultado.add(fila);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}
	
	public List<Reserva> buscarReserva(String criterio) {
	    List<Reserva> resultado = new ArrayList<>();
	  //  System.out.println("Criterio desde Dao " + criterio);
	    try {
	        final String query = "SELECT id, fechaEntrada, fechaSalida, total_noches, valor, formaDePago FROM reserva " +
	                             "WHERE (id LIKE ? OR fechaEntrada LIKE ? OR fechaSalida LIKE ? OR total_noches LIKE ? OR valor LIKE ? OR formaDePago LIKE ?) AND activo = 1";
	        final PreparedStatement statement = con.prepareStatement(query);

	        try (statement) {
	            String likeCriterio = "%" + criterio + "%";
	            for (int i = 1; i <= 6; i++) {
	                statement.setString(i, likeCriterio);
	            }

	            statement.execute();

	            final ResultSet resultSet = statement.getResultSet();

	            try (resultSet) {
	                while (resultSet.next()) {
	                    Reserva fila = new Reserva(resultSet.getInt("id"), resultSet.getDate("fechaEntrada"), resultSet.getDate("fechaSalida"), resultSet.getInt("total_noches"), resultSet.getString("valor"),
	                            resultSet.getString("formaDePago"));

	                    resultado.add(fila);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	    return resultado;
	}
	
	public int editar(Reserva editar ) {
		try {
				final PreparedStatement statement = con.prepareStatement("UPDATE reserva SET " + " fechaEntrada = ?, " + " fechaSalida = ?, " +
						" total_noches = ?, " + " valor = ?, " + " formaDePago = ?" + " WHERE id = ?");
				try (statement) {
					statement.setDate(1, editar.getFechaEntrada());
					statement.setDate(2, editar.getFechaSalida());
					statement.setInt(3, editar.getTotalNoches());
					statement.setString(4, editar.getValor());
					statement.setString(5, editar.getFormaDePago());
					statement.setInt(6, editar.getId());
					
					statement.execute();
				
					int updateCount = statement.getUpdateCount();
					return updateCount;
				}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public int eliminar(Reserva eliminar ) {
		try {
				 PreparedStatement statement = con.prepareStatement("UPDATE reserva SET "  + " activo = ?" + " WHERE id = ?");
				 PreparedStatement statementHuesped = con.prepareStatement("UPDATE registro_huesped_reserva SET "  + " numero_de_reserva = ?" + " WHERE numero_de_reserva = ?");
				
					statement.setInt(1, 0);
					statement.setInt(2, eliminar.getId());					
					statement.execute();
					
					int numeroReserva = eliminar.getId();
					statementHuesped.setInt(1, 0);
					statementHuesped.setInt(2, numeroReserva);
					
					statementHuesped.execute();
					
				
					int updateCount = statement.getUpdateCount();
					return updateCount;
				
					
					

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public Object cargar(Reserva cargar) {

		try {
			final String query = "SELECT id, fechaEntrada, fechaSalida, formaDePago FROM reserva where id= ? and activo = ?";
			final PreparedStatement statement = con.prepareStatement(query);

			try (statement) {
				statement.setInt(1, cargar.getId());
				statement.setInt(2, 1);

				statement.execute();

				ResultSet rst = statement.executeQuery();
				if (rst.next()) {
					cargar.setFechaEntrada(rst.getDate("fechaEntrada"));
					cargar.setFechaSalida(rst.getDate("fechaSalida"));
					cargar.setFormaDePago(rst.getString("formaDePago"));


				} else {
					System.out.println("Error en datos ");
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return cargar;
	}

	




	

	
}	