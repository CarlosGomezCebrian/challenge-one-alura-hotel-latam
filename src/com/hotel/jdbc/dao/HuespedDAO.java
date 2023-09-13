package com.hotel.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotel.jdbc.modelo.Huesped;


public class HuespedDAO {

	final private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped huesped) {

		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO registro_huesped_reserva(numero_de_reserva, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, activo)"
							+ "VALUES (?,?,?,?,?,?,?)");

			try (statement) {

				ejecutaRegisto(huesped, statement);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	private void ejecutaRegisto(Huesped huesped, PreparedStatement statement) throws SQLException {
		statement.setInt(1, huesped.getNumeroDeReserva());
		statement.setString(2, huesped.getNombre());
		statement.setString(3, huesped.getApellido());
		statement.setDate(4, huesped.getFechaN());
		statement.setString(5, huesped.getNacionalidad());
		statement.setString(6, huesped.getTelefono());
		statement.setInt(7, 1);

		statement.execute();

		final ResultSet resultSet = statement
				.executeQuery("SELECT MAX(numero_de_reserva) FROM registro_huesped_reserva");

		try (resultSet) {
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				// System.out.println("Datos guardados con éxito, el ID es: " + id);
				// System.out.println(String.format("Fue insertado el producto %s", reserva));
			}
		}

	}

	public List<Huesped> listar() {
		List<Huesped> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement("SELECT id, numero_de_reserva, nombre, apellido, "
					+ "fecha_de_nacimiento, nacionalidad, telefono FROM registro_huesped_reserva WHERE activo = 1");

			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						Huesped fila = new Huesped(resultSet.getInt("id"), resultSet.getInt("numero_de_reserva"),
								resultSet.getNString("nombre"), resultSet.getNString("apellido"),
								resultSet.getDate("fecha_de_nacimiento"), resultSet.getString("nacionalidad"),
								resultSet.getString("telefono"));

						resultado.add(fila);
					}
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	public List<Huesped> buscarHuesped(String criterio) {
		List<Huesped> resultado = new ArrayList<>();		
		try {
			final String query = "SELECT id, numero_de_reserva, nombre, apellido, "
					+ "fecha_de_nacimiento, nacionalidad, telefono FROM registro_huesped_reserva "
					+ "WHERE (id LIKE ? OR numero_de_reserva LIKE ? OR  nombre LIKE ? OR apellido LIKE ? OR fecha_de_nacimiento LIKE ? OR nacionalidad LIKE ? OR telefono LIKE ?) AND activo = 1";
			final PreparedStatement statement = con.prepareStatement(query);

			try (statement) {
				String likeCriterio = "%" + criterio + "%";
				for (int i = 1; i <= 7; i++) {
					statement.setString(i, likeCriterio);

				}

				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						Huesped fila = new Huesped(resultSet.getInt("id"), resultSet.getInt("numero_de_reserva"),
								resultSet.getNString("nombre"), resultSet.getNString("apellido"),
								resultSet.getDate("fecha_de_nacimiento"), resultSet.getString("nacionalidad"),
								resultSet.getString("telefono"));
						resultado.add(fila);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

	public int editar(Huesped editar) {
		try {
			final PreparedStatement statement = con
					.prepareStatement("UPDATE registro_huesped_reserva SET " + " nombre = ?, " + " apellido = ?, "
							+ " fecha_de_nacimiento = ?, " + " nacionalidad = ?, " + " telefono = ?" + " WHERE id = ?");
			try (statement) {
				statement.setString(1, editar.getNombre());
				statement.setString(2, editar.getApellido());
				statement.setDate(3, editar.getFechaN());
				statement.setString(4, editar.getNacionalidad());
				statement.setString(5, editar.getTelefono());
				statement.setInt(6, editar.getId());

				statement.execute();
				int updateCount = statement.getUpdateCount();
				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public int eliminar(Huesped eliminar) {
		try {
			PreparedStatement statement = con
					.prepareStatement("UPDATE registro_huesped_reserva SET  activo = ? WHERE id = ?");
			PreparedStatement query = con
					.prepareStatement("Select numero_de_reserva FROM registro_huesped_reserva WHERE id = ? ");
			PreparedStatement statementHuesped = con
					.prepareStatement("UPDATE reserva SET " + " activo = ?" + " WHERE id = ?");

			statement.setInt(1, 0);
			statement.setInt(2, eliminar.getId());

			statement.execute();			
			query.setInt(1, eliminar.getId());

			ResultSet rst = query.executeQuery();

			if (rst.next()) {
				int numero_de_reserva = rst.getInt("numero_de_reserva");
				eliminar.setNumero_de_reserva(numero_de_reserva);			
			} else {
				String numero_de_reserva = "Datos incorrectos";
				System.out.println("Error de acceso " + numero_de_reserva);
			}
			statementHuesped.setInt(1, 0);
			statementHuesped.setInt(2, eliminar.getNumeroDeReserva());		
			statementHuesped.execute();
			int updateCount = statement.getUpdateCount();
			return updateCount;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Object cargar(Huesped cargar) {

		try {
			final String query = "select nombre, apellido, fecha_de_nacimiento, nacionalidad,telefono from registro_huesped_reserva where id= ? and activo = ?";
			final PreparedStatement statement = con.prepareStatement(query);

			try (statement) {
				statement.setInt(1, cargar.getId());
				statement.setInt(2, 1);

				statement.execute();

				ResultSet rst = statement.executeQuery();
				if (rst.next()) {

					cargar.setNombre(rst.getString("nombre"));
					cargar.setApellido(rst.getString("apellido"));
					cargar.setFechaN(rst.getDate("fecha_de_nacimiento"));
					cargar.setNacionalidad(rst.getString("nacionalidad"));
					cargar.setTelefono(rst.getString("telefono"));

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
