package com.hotel.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
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
public class ReservasView extends JFrame {

	private JPanel contentPane;
	public static JLabel txtValor;
	public static JDateChooser txtFechaEntrada;
	public static JDateChooser txtFechaSalida;
	public static JComboBox<String> txtFormaPago;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelAtras;
	private Calendar fecha = Calendar.getInstance(); 
	private Double resultadoValor;
	private Double valorDia = 243.53;//Valor diario por noche por habitacion
	private int dias;
			 
	Calendar calendar = Calendar.getInstance();
	Date fechaActual = calendar.getTime();
	
	private ReservasController reservasController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservasView frame = new ReservasView();
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
	public ReservasView() {
		super("Reserva");
		//Iniciando la clase ReservaController
		this.reservasController = new ReservasController();
		
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 251, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		

		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(248, 251, 255));
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Código que crea los elementos de la interfáz gráfica
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 195, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);
		
		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 281, 289, 11);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);
		
		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setHorizontalAlignment(SwingConstants.LEFT);
		lblCheckIn.setBounds(68, 136, 269, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);
		
		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setHorizontalAlignment(SwingConstants.LEFT);
		lblCheckOut.setBounds(68, 221, 269, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);
		
		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setHorizontalAlignment(SwingConstants.LEFT);
		lblFormaPago.setBounds(68, 382, 270, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);
		
		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setBounds(68, 60, 290, 42);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/Ha-100px.png")));
		
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/reservas-img-3.png")));
		
		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 303, 250, 14);
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);
												
		// Componentes para dejar la interfaz con estilo Material Design
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario= new MenuUsuario();
				usuario.setVisible(true);
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
		btnexit.setBounds(429, 0, 53, 36);
		panel_1.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
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
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
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
		
		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);
		
		txtValor = new JLabel();	
		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.LEFT);
		txtValor.setForeground(new Color(12, 138, 199));
		txtValor.setBounds(78, 328, 289, 40);
		txtValor.setVisible(true);
		txtValor.setFont(new Font("Roboto Black", Font.BOLD, 18));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);		
		
		
		//Campos que guardaremos en la base de datos
		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.getCalendarButton();
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaEntrada.setBounds(68, 161, 290, 35);
		txtFechaEntrada.setForeground(new Color(12, 138, 199));
		txtFechaEntrada.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaEntrada.setBackground(new Color(248, 251, 255));
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setCalendar(Calendar.getInstance());
		txtFechaEntrada.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaEntrada.getDateEditor().setMinSelectableDate(fechaActual);
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
		txtFechaSalida.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaSalida.setBounds(68, 246, 290, 35);
		txtFechaSalida.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaSalida.setBackground(new Color(248, 251, 255));
		txtFechaSalida.setForeground(new Color(12, 138, 199));
		txtFechaSalida.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaSalida.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		txtFechaSalida.getDateEditor().setMinSelectableDate(fechaActual);
		panel.add(txtFechaSalida);
		
		txtFechaEntrada.addPropertyChangeListener(new PropertyChangeListener() {
		    public void propertyChange(PropertyChangeEvent evt) {
		        if ("date".equals(evt.getPropertyName())) {
		            calcularValor();
		        }
		    }
		});

		txtFormaPago = new JComboBox<String>();
		txtFormaPago.setBounds(68, 417, 289, 38);
		txtFormaPago.setBackground(new Color(248, 251, 255));
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setForeground(new Color(12, 138, 199));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel<String>(new String[] {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"}));
		panel.add(txtFormaPago);

		JPanel btnsiguiente = new JPanel();
		btnsiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ReservasView.txtFechaEntrada.getDate() != null && ReservasView.txtFechaSalida.getDate() != null) {
					guardarReserva();

				} else {
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
				}
			}						
		});
		btnsiguiente.setLayout(null);
		btnsiguiente.setBackground(SystemColor.textHighlight);
		btnsiguiente.setBounds(238, 493, 122, 35);
		panel.add(btnsiguiente);
		btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel labelSiguiente = new JLabel("SIGUIENTE");
		labelSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		labelSiguiente.setForeground(Color.WHITE);
		labelSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelSiguiente.setBounds(0, 0, 122, 35);
		btnsiguiente.add(labelSiguiente);


	}
	
	private void guardarReserva() {
		String fechaEntrada = ((JTextField)txtFechaEntrada.getDateEditor().getUiComponent()).getText();
		String fechaSalida = ((JTextField)txtFechaSalida.getDateEditor().getUiComponent()).getText();
		Reserva nuevaReserva = new Reserva(java.sql.Date.valueOf(fechaEntrada),java.sql.Date.valueOf(fechaSalida), dias, resultadoValor.toString(), txtFormaPago.getSelectedItem().toString());
		reservasController.guardar(nuevaReserva);
		
		JOptionPane.showMessageDialog(contentPane, "Reserva Guardada con exito numero: " + nuevaReserva.getId());
		System.out.println("El registro es " + nuevaReserva.getId());
		RegistroHuesped registro = new RegistroHuesped(nuevaReserva.getId());
		registro.setVisible(true);
		dispose();
	}
	
	private void calcularValor() {		
		LocalDate fechaEntrada = txtFechaEntrada.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fechaSalida = txtFechaSalida.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period periodo = Period.between(fechaEntrada, fechaSalida);
		if (periodo.getDays() < 0) {
			Date fechaE = txtFechaEntrada.getDate();
			txtFechaSalida.setDate(fechaE);
			dias = 1;
		} else {
			dias = periodo.getDays();
		}				
		
		resultadoValor = dias * valorDia;
		if(dias == 1 ) {
			txtValor.setText(resultadoValor.toString() + " USD. Por " + dias + " noche.");
		} else {
			txtValor.setText(resultadoValor.toString() + " USD. Por " + dias + " noches.");			
		}
	
	}
		
	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"	
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
