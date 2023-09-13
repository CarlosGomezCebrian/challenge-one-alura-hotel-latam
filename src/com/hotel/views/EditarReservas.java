package com.hotel.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.hotel.jdbc.controller.ReservasController;
import com.hotel.jdbc.modelo.Reserva;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class EditarReservas extends JFrame {

	private JPanel contentPane;
	public static JLabel txtValor;
	public static JDateChooser txtFechaEntrada;
	public static JDateChooser txtFechaSalida;
	public static JComboBox<String> txtFormaPago;
	private JComboBox<Integer> comboId;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelAtras;

	private Calendar fecha = Calendar.getInstance();
	private Double resultado;
	private int dias;
	private ReservasController reservasController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarReservas frame = new EditarReservas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditarReservas() {
		super("Editar Reserva");
		// Iniciando la clase ReservaController
		this.reservasController = new ReservasController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(EditarReservas.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(248, 251, 255));
		panel.setBounds(0, 0, 430, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		// Código que crea los elementos de la interfáz gráfica

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 227, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 313, 289, 2);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(68, 400, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);

		JLabel lblCheckIn = new JLabel("EDITAR FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setHorizontalAlignment(SwingConstants.LEFT);
		lblCheckIn.setBounds(68, 166, 290, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);

		JLabel lblCheckOut = new JLabel("EDITAR FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setHorizontalAlignment(SwingConstants.LEFT);
		lblCheckOut.setBounds(68, 251, 289, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);

		JLabel lblFormaPago = new JLabel("EDITAR FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setHorizontalAlignment(SwingConstants.LEFT);
		lblFormaPago.setBounds(68, 412, 289, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);

		JLabel lblTitulo = new JLabel("EDITAR RESERVAS");
		lblTitulo.setBounds(68, 30, 290, 42);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);

		JLabel lblId = new JLabel("NUMERO DE RESERVA");
		lblId.setBounds(68, 90, 250, 20);
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setForeground(SystemColor.textInactiveText);
		lblId.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblId);

		comboId = new JComboBox<Integer>();
		comboId.setBounds(68, 120, 289, 36);
		comboId.setBackground(new Color(248, 251, 255));
		comboId.setForeground(new Color(12, 138, 199));
		comboId.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.add(comboId);
		cargarIdReservas();
		comboId.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarDatos();
			}
		});

		JSeparator separatorID = new JSeparator();
		separatorID.setForeground(SystemColor.textHighlight);
		separatorID.setBackground(SystemColor.textHighlight);
		separatorID.setBounds(68, 157, 289, 2);
		panel.add(separatorID);

		JLabel lblValor = new JLabel("NUEVO VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(68, 333, 290, 14);
		lblValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);

		// Componentes para dejar la interfaz con estilo Material Design
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				Busqueda busqueda = new Busqueda(0);
//				busqueda.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(377, 0, 53, 36);
		panel.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel header = new JPanel();
		header.setBounds(0, 0, 430, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(new Color(248, 251, 255));
		panel.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				Busqueda editarBusqueda = new Busqueda(0);
//				editarBusqueda.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(new Color(248, 251, 255));
				labelAtras.setForeground(new Color(12, 138, 199));
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(248, 251, 255));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

		txtValor = new JLabel();
		txtValor.setBackground(new Color(248, 251, 255));
		txtValor.setHorizontalAlignment(SwingConstants.LEFT);
		txtValor.setForeground(new Color(12, 138, 199));
		txtValor.setBounds(78, 358, 289, 40);
		txtValor.setVisible(true);
		txtValor.setFont(new Font("Roboto Black", Font.BOLD, 19));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);

		// Campos que guardaremos en la base de datos
		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.getCalendarButton();
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton()
				.setIcon(new ImageIcon(EditarReservas.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaEntrada.setBounds(68, 191, 290, 35);
		txtFechaEntrada.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaEntrada.setBackground(new Color(248, 251, 255));
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setCalendar(Calendar.getInstance());
		txtFechaEntrada.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaEntrada.setForeground(new Color(12, 138, 199));
		panel.add(txtFechaEntrada);

		fecha.add(Calendar.DAY_OF_MONTH, 1);
		txtFechaSalida = new JDateChooser();
		txtFechaSalida.setCalendar(fecha);
		txtFechaSalida.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					calcularValor();
				}
			}
		});
		txtFechaSalida.getCalendarButton()
				.setIcon(new ImageIcon(EditarReservas.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaSalida.setBounds(68, 276, 290, 35);
		txtFechaSalida.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaSalida.setBackground(new Color(248, 251, 255));
		txtFechaSalida.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaSalida.setForeground(new Color(12, 138, 199));
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaSalida.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtFechaSalida);
		
		txtFechaEntrada.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					calcularValor();
				}
			}
		});

		txtFormaPago = new JComboBox<String>();
		txtFormaPago.setBounds(68, 447, 289, 38);
		txtFormaPago.setForeground(new Color(12, 138, 199));
		txtFormaPago.setBackground(new Color(248, 251, 255));
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo" }));
		panel.add(txtFormaPago);

		JPanel btnsiguiente = new JPanel();
		btnsiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (EditarReservas.txtFechaEntrada.getDate() != null
						&& EditarReservas.txtFechaSalida.getDate() != null) {
					editarReserva();

				} else {
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
				}
			}
		});
		btnsiguiente.setLayout(null);
		btnsiguiente.setBackground(new Color(12, 138, 199));
		btnsiguiente.setBounds(115, 510, 200, 35);
		panel.add(btnsiguiente);
		btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel labelSiguiente = new JLabel("GUARDAR EDICIÓN");
		labelSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		labelSiguiente.setForeground(Color.WHITE);
		labelSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelSiguiente.setBounds(0, 0, 200, 35);
		btnsiguiente.add(labelSiguiente);

	}

	private void editarReserva() {

		try {
			Integer id = (Integer) comboId.getSelectedItem();
			String fechaEntrada = ((JTextField) txtFechaEntrada.getDateEditor().getUiComponent()).getText();
			String fechaSalida = ((JTextField) txtFechaSalida.getDateEditor().getUiComponent()).getText();
			Reserva editar = new Reserva(id, java.sql.Date.valueOf(fechaEntrada), java.sql.Date.valueOf(fechaSalida),
					dias, resultado.toString(), txtFormaPago.getSelectedItem().toString());
			reservasController.editar(editar);
			JOptionPane.showMessageDialog(contentPane, "Reserva " + editar.getId() + " Modificada con exito");
			Busqueda editarBusqueda = new Busqueda(0);
			editarBusqueda.setVisible(true);
			dispose();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "Algún dato es incorrecto o vacío");
		}
	}

	private void calcularValor() {
		LocalDate fechaEntrada = txtFechaEntrada.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fechaSalida = txtFechaSalida.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period periodo = Period.between(fechaEntrada, fechaSalida);
		if (periodo.getDays() < 0) {
			JOptionPane.showMessageDialog(contentPane, "La fecha de check out no puede ser menor a la fecha check in");
			cargarDatos();
//			Date fechaE = txtFechaEntrada.getDate();
//			txtFechaSalida.setDate(fechaE);
		}else if(periodo.getDays() == 0) {
			dias = 1;
		} else {
			dias = periodo.getDays();
		}
		Double valorDia = 243.53;
		resultado = dias * valorDia;
		if (dias == 1) {
			txtValor.setText(resultado.toString() + " USD. Por " + dias + " noche.");
		} else {
			txtValor.setText(resultado.toString() + " USD. Por " + dias + " noches.");
		}

	}

	private void cargarIdReservas() {
		List<Reserva> reserva = this.reservasController.listar();

		// Limpia el JComboBox antes de agregar nuevos elementos
		comboId.removeAllItems();

		// Agrega elementos desde la lista al JComboBox
		for (Reserva reservaList : reserva) {
			comboId.addItem(reservaList.getId());
		}
	}

	private void cargarDatos() {
		Integer id = (Integer) comboId.getSelectedItem();
		Reserva cargar = new Reserva(id);
		reservasController.cargar(cargar);
		Date fechaEntrada = cargar.getFechaEntrada();
		Date fechaSalida = cargar.getFechaSalida();
		txtFechaEntrada.setDate(fechaEntrada);
		txtFechaSalida.setDate(fechaSalida);
		txtFormaPago.setSelectedItem(cargar.getFormaDePago());
	}

	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
